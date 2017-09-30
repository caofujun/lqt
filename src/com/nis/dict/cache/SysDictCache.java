package com.nis.dict.cache;

import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.d;
import com.nis.dict.dao.SysDictDao;
import com.nis.dict.entity.SysDict;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysDictCache {
	private static Map<String, Map<String, Set<String>>> qt = new HashMap();
	@Autowired
	private SysDictDao qu;

	private void g(String dictTypeCode, String unitId, String depNo) {
		Object unitMap = (Map) qt.get(dictTypeCode);
		if (unitMap == null) {
			unitMap = new HashMap();
		}

		Object set = (Set) ((Map) unitMap).get(unitId);
		if (set == null) {
			set = new HashSet();
		}

		((Set) set).add(depNo);
		((Map) unitMap).put(unitId, set);
		qt.put(dictTypeCode, (Map<String, Set<String>>) unitMap);
	}

	private String h(String dictTypeCode, String unitId, String depNo) {
		if (ab.isEmpty(dictTypeCode)) {
			dictTypeCode = "";
		}

		if (unitId == null) {
			unitId = "0";
		}

		if (depNo == null) {
			depNo = "0";
		}

		this.g(dictTypeCode, unitId, depNo);
		return d.a(bg.ms, dictTypeCode + "_" + unitId + "_" + depNo);
	}

	private String a(String dictTypeCode, String dictCode, String unitId, String depNo) {
		if (ab.isEmpty(dictTypeCode)) {
			dictTypeCode = "";
		}

		if (ab.isEmpty(dictCode)) {
			dictCode = "";
		}

		if (unitId == null) {
			unitId = "0";
		}

		if (depNo == null) {
			depNo = "0";
		}

		this.g(dictTypeCode, unitId, depNo);
		return d.a(bg.ms, dictTypeCode + "_" + dictCode + "_" + unitId + "_" + depNo);
	}

	public List<SysDict> findByDictTypeCode(String dictTypeCode, String unitId, String depNo) {
		String key = this.h(dictTypeCode, unitId, depNo);
		List data = (List) d.get(key);
		if (data == null || data.size() == 0) {
			if (unitId == null) {
				data = this.qu.findByDictTypeCode(dictTypeCode, unitId, (String) null);
				d.set(key, data);
				return data;
			}

			if (depNo != null) {
				data = this.qu.findByDictTypeCode(dictTypeCode, unitId, depNo);
				if (data != null && !data.isEmpty()) {
					d.set(key, data);
					return data;
				}

				data = this.qu.findByDictTypeCode(dictTypeCode, unitId, (String) null);
				if (data != null && !data.isEmpty()) {
					d.set(key, data);
					return data;
				}

				data = this.qu.findByDictTypeCode(dictTypeCode, (String) null, (String) null);
				d.set(key, data);
			} else {
				data = this.qu.findByDictTypeCode(dictTypeCode, unitId, (String) null);
				if (data != null && !data.isEmpty()) {
					d.set(key, data);
					return data;
				}

				data = this.qu.findByDictTypeCode(dictTypeCode, (String) null, (String) null);
				d.set(key, data);
			}
		}

		return data;
	}

	public SysDict findByDictCode(String dictTypeCode, String dictCode, String unitId, String depNo) {
		String key = this.a(dictTypeCode, dictCode, unitId, depNo);
		SysDict data = null;

		try {
			data = (SysDict) d.get(key);
		} catch (Exception arg7) {
			arg7.printStackTrace();
		}

		if (data == null) {
			if (unitId == null) {
				data = this.qu.findByDictCode(dictCode, dictTypeCode, unitId, (String) null);
				d.set(key, data);
				return data;
			}

			if (depNo != null) {
				data = this.qu.findByDictCode(dictCode, dictTypeCode, unitId, depNo);
				if (data != null) {
					d.set(key, data);
					return data;
				}

				data = this.qu.findByDictCode(dictCode, dictTypeCode, unitId, (String) null);
				if (data != null) {
					d.set(key, data);
					return data;
				}

				data = this.qu.findByDictCode(dictCode, dictTypeCode, (String) null, (String) null);
				d.set(key, data);
			} else {
				data = this.qu.findByDictCode(dictCode, dictTypeCode, unitId, (String) null);
				if (data != null) {
					d.set(key, data);
					return data;
				}

				data = this.qu.findByDictCode(dictCode, dictTypeCode, (String) null, (String) null);
				d.set(key, data);
			}
		}

		return data;
	}

	public void i(String dictTypeCode, String dictCode, String unitId) {
		Map unitMap = (Map) qt.get(dictTypeCode);
		if (unitMap != null) {
			Iterator arg5 = unitMap.entrySet().iterator();

			while (arg5.hasNext()) {
				Entry entry = (Entry) arg5.next();
				String kUnitId = (String) entry.getKey();
				Set set = (Set) unitMap.get(kUnitId);
				Iterator arg9 = set.iterator();

				while (arg9.hasNext()) {
					String depNo = (String) arg9.next();
					d.a(this.h(dictTypeCode, kUnitId, depNo));
					d.a(this.a(dictTypeCode, dictCode, kUnitId, depNo));
				}
			}
		}

	}

	public void s(String dictTypeCode, String unitId) {
	}
}