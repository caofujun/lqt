package com.nis.param.cache;

import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.d;
import com.nis.param.dao.SysParamDao;
import com.nis.param.entity.SysParam;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysParamCache {
	private static Map<String, Set<String>> qt = new HashMap();
	@Autowired
	private SysParamDao wo;

	private void q(String paramCode, String unitId, String depNo, String userName) {
		Object set = (Set) qt.get(paramCode);
		if (set == null) {
			set = new HashSet();
		}

		((Set) set).add(d.a(bg.mq, paramCode + unitId + depNo + userName));
		qt.put(paramCode, (Set<String>)set);
	}

	private String r(String paramCode, String unitId, String depNo, String userName) {
		if (ab.isEmpty(paramCode)) {
			paramCode = "";
		}

		if (ab.isEmpty(depNo)) {
			depNo = "";
		}

		if (ab.isEmpty(userName)) {
			userName = "";
		}

		if (ab.isEmpty(unitId)) {
			unitId = "0";
		}

		this.q(paramCode, unitId, depNo, userName);
		return d.a(bg.mq, paramCode + unitId + depNo + userName);
	}

	public SysParam findByParamCode(String paramCode, String unitId, String depNo, String userName) {
		String key = this.r(paramCode, unitId, depNo, userName);
		SysParam data = (SysParam) d.get(key);
		if (data == null) {
			data = this.wo.findByParamCode(paramCode, unitId, depNo, userName);
			d.set(key, data);
		}

		return data;
	}

	public void a(SysParam param) {
		Set set = (Set) qt.get(param.getParamCode());
		if (set != null) {
			Iterator arg3 = set.iterator();

			while (arg3.hasNext()) {
				String key = (String) arg3.next();
				d.a(key);
			}
		}

	}
}