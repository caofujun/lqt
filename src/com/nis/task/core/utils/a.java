package com.nis.task.core.utils;

import com.nis.comm.enums.at;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.l;
import com.nis.comm.utils.o;
import com.nis.task.entity.TaskProject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class a {
	public static Map<String, String> a(TaskProject project) {
		Object params = new HashMap();
		if (project != null) {
			params = (Map) l.ar(project.getSignstring());
			String signString = "";
			String signParam = null;
			Iterator entryKeyIterator = ((Map) params).entrySet().iterator();

			while (true) {
				while (entryKeyIterator.hasNext()) {
					Entry e = (Entry) entryKeyIterator.next();
					String value = (String) e.getValue();
					if ("theCurrentTimestamp".equals(value)) {
						value = String.valueOf(System.currentTimeMillis());
						((Map) params).put((String) e.getKey(), value);
					} else if ("encryptionParameters".equals(value)) {
						signParam = (String) e.getKey();
						continue;
					}

					signString = signString + value;
				}

				if (ab.isNotEmpty(signParam)) {
					((Map) params).put(signParam, o.getInstance().at(signString).toLowerCase());
				}

				((Map) params).remove("token");
				break;
			}
		}

		return (Map) params;
	}

	public static String a(String link, TaskProject project) {
		if (!ab.isEmpty(project.getSignstring()) && !at.kZ.getCode().equals(project.getSign())) {
			link = link + (link.indexOf("?") == -1 ? "?" : "&");
			Map params = (Map) l.ar(project.getSignstring());
			String signString = "";
			String signParam = null;
			Iterator entryKeyIterator = params.entrySet().iterator();

			while (true) {
				while (entryKeyIterator.hasNext()) {
					Entry e = (Entry) entryKeyIterator.next();
					String value = (String) e.getValue();
					if ("theCurrentTimestamp".equals(value)) {
						value = String.valueOf(System.currentTimeMillis());
					} else if ("encryptionParameters".equals(value)) {
						signParam = (String) e.getKey();
						continue;
					}

					signString = signString + value;
					if (!"token".equals(e.getKey())) {
						link = link + (String) e.getKey() + "=" + value + "&";
					}
				}

				if (ab.isNotEmpty(signParam)) {
					link = link + signParam + "=";
				}

				link = link + o.getInstance().at(signString).toLowerCase();
				return link;
			}
		} else {
			return link;
		}
	}
}