package com.nis.jk.utils;

import com.nis.jk.entity.JkSyncLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class a implements Comparator<JkSyncLog> {
	public int compare(JkSyncLog o1, JkSyncLog o2) {
		return o1 == null && o2 == null
				? 0
				: (o2 == null
						? -1
						: (o1 == null
								? 1
								: (o1.getSortNo() != null && o2.getSortNo() != null
										? o1.getSortNo().compareTo(o2.getSortNo())
										: (o1.getSortNo() != null ? -1 : 1))));
	}

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		JkSyncLog jkSyncLog = new JkSyncLog();
		jkSyncLog.setTableName("abc2");
		jkSyncLog.setSortNo(new Integer(2));
		list.add(jkSyncLog);
		jkSyncLog = new JkSyncLog();
		jkSyncLog.setTableName("abcd");
		jkSyncLog.setSortNo(new Integer(1));
		list.add(jkSyncLog);
		jkSyncLog = new JkSyncLog();
		jkSyncLog.setTableName("ab");
		jkSyncLog.setSortNo(new Integer(2));
		list.add(jkSyncLog);
		jkSyncLog = new JkSyncLog();
		jkSyncLog.setTableName("abc");
		jkSyncLog.setSortNo(new Integer(3));
		list.add(jkSyncLog);
		Collections.sort(list, new a());
		Iterator arg3 = list.iterator();

		while (arg3.hasNext()) {
			JkSyncLog di = (JkSyncLog) arg3.next();
			System.out.println(di.getTableName());
		}

	}
}