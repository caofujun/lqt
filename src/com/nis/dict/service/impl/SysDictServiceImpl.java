package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.a;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.dict.cache.SysDictCache;
import com.nis.dict.dao.SysDictDao;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysDictServiceImpl implements SysDictService {
	@Autowired
	private SysDictDao qu;
	@Autowired
	private SysDictCache qK;

	public void save(SysDict sysDict) {
		sysDict.setId(z.a(bg.ms));
		if (sysDict.getUpdateTime() == null || sysDict.getUpdateTime().equals("")) {
			sysDict.setUpdateTime(new Date());
		}

		this.qu.save(sysDict);
		this.qK.i(sysDict.getDictTypeCode(), sysDict.getDictCode(), sysDict.getUnitId());
	}

	public void delete(String id) {
		SysDict dict = this.get(id);
		this.qu.delete(id);
		this.qK.i(dict.getDictTypeCode(), dict.getDictCode(), dict.getUnitId());
	}

	public void deleteByTypeCode(String dictTypeCode) {
		this.qu.deleteByTypeCode(dictTypeCode);
	}

	public void update(SysDict sysDict) {
		if (sysDict.getUpdateTime() == null || sysDict.getUpdateTime().equals("")) {
			sysDict.setUpdateTime(new Date());
		}

		this.qu.update(sysDict);
		this.qK.i(sysDict.getDictTypeCode(), sysDict.getDictCode(), sysDict.getUnitId());
	}

	public SysDict get(String id) {
		SysDict dict = this.qu.get(id);
		dict.setScopelevelName(this.k("data_scope", dict.getScopeLevel(), (String) null));
		dict.setDictStatusName(this.k("enable_status", dict.getDictStatus(), (String) null));
		return dict;
	}

	public MyPage<SysDict> a(SysDict sysDict) {
		int total = this.qu.findSysDictCount(sysDict);
		List data = null;
		if (total > 0) {
			data = this.qu.findSysDict(sysDict);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				SysDict dict = (SysDict) arg4.next();
				dict.setScopelevelName(this.k("data_scope", dict.getScopeLevel(), (String) null));
				dict.setDictStatusName(this.k("enable_status", dict.getDictStatus(), (String) null));
			}
		}

		return new MyPage(sysDict.getPage().intValue(), sysDict.getSize().intValue(), total, data);
	}

	public List<SysDict> getAll(SysDict sysDict) {
		new ArrayList();
		List dictList;
		if (sysDict.getUnitId() == null) {
			dictList = this.qu.getAll(sysDict);
			this.A(dictList);
			return dictList;
		} else {
			dictList = this.qu.getAll(sysDict);
			if (dictList != null && !dictList.isEmpty()) {
				this.A(dictList);
				return dictList;
			} else {
				sysDict.setUnitId((String) null);
				dictList = this.qu.getAll(sysDict);
				this.A(dictList);
				return dictList;
			}
		}
	}

	public List<SysDict> c(SysDict sysDict) {
		new ArrayList();
		List dictList;
		if (sysDict.getUnitId() == null) {
			dictList = this.qu.getAll(sysDict);
			this.A(dictList);
			return dictList;
		} else {
			dictList = this.qu.getAll(sysDict);
			if (dictList != null && !dictList.isEmpty()) {
				this.A(dictList);
				return dictList;
			} else {
				return dictList;
			}
		}
	}

	private void A(List<SysDict> list) {
		Iterator arg2 = list.iterator();

		while (arg2.hasNext()) {
			SysDict dict = (SysDict) arg2.next();
			dict.setText(dict.getDictName());
		}

	}

	public List<SysDict> b(SysDict sysDict) {
		List dictList = this.qu.getAllTop(sysDict);
		Iterator arg3 = dictList.iterator();

		while (arg3.hasNext()) {
			SysDict dict = (SysDict) arg3.next();
			dict.setText(dict.getDictName());
			SysDict dc = new SysDict();
			dc.setParentCode(dict.getDictCode());
			dc.setDictTypeCode(dict.getDictTypeCode());
			List dicts = this.getAll(dc);
			dict.setChildren(dicts);
		}

		return dictList;
	}

	public List<SysDict> u(String dictTypeCode, String unitId) {
		return this.qK.findByDictTypeCode(dictTypeCode, unitId, (String) null);
	}

	public String k(String dictTypeCode, String dictCode, String unitId) {
		if (ab.isNotEmpty(dictCode)) {
			SysDict dict = this.qK.findByDictCode(dictTypeCode, dictCode, unitId, (String) null);
			if (dict != null) {
				return dict.getDictName();
			}
		}

		return dictCode;
	}

	public String b(String dictTypeCode, String dictCode, String unitId, String defValue) {
		SysDict dict = this.qK.findByDictCode(dictTypeCode, dictCode, unitId, (String) null);
		return dict != null ? dict.getDictName() : defValue;
	}

	public String l(String dictTypeCode, String dictCode, String unitId) {
		SysDict dict = this.qK.findByDictCode(dictTypeCode, dictCode, (String) null, (String) null);
		if (dict != null) {
			return dict.getDictName();
		} else {
			SysDict dict1 = this.qK.findByDictCode(dictTypeCode, dictCode, unitId, (String) null);
			return dict1 != null ? dict1.getDictName() : dictCode;
		}
	}

	public List<SysDict> m(String dictTypeCode, String dictCode, String unitId) {
		SysDict dict = this.qK.findByDictCode(dictTypeCode, dictCode, unitId, (String) null);
		if (dict == null) {
			return Collections.emptyList();
		} else {
			new ArrayList();
			List dictList;
			if (unitId == null) {
				dictList = this.qu.findByParentId(dict.getDictCode(), dict.getDictTypeCode(), unitId);
				return dictList;
			} else {
				dictList = this.qu.findByParentId(dict.getDictCode(), dict.getDictTypeCode(), unitId);
				return dictList != null && !dictList.isEmpty()
						? dictList
						: this.qu.findByParentId(dict.getDictCode(), dict.getDictTypeCode(), (String) null);
			}
		}
	}

	public SysDict j(String dictTypeCode, String dictCode, String unitId) {
		return this.qK.findByDictCode(dictTypeCode, dictCode, unitId, (String) null);
	}

	public SysDict getMaxCodeAndSeq(String dictTypeCode, String unitId) {
		return this.qu.getMaxCodeAndSeq(dictTypeCode, unitId);
	}

	public List<SysDict> findByDictTypeCode(String dictTypeCode, String unitId, String depNo) {
		return this.qK.findByDictTypeCode(dictTypeCode, unitId, depNo);
	}

	public List<SysDict> findTop(String dictTypeCode, String unitId) {
		return this.qu.findTop(dictTypeCode, unitId);
	}

	public void updateLastTimebyCode(String dictTypeCode, String dictCode, Date updateTime) {
		this.qu.updateLastTimebyCode(dictTypeCode, dictCode, updateTime);
	}

	public List<SysDict> getAllSpecDescribes(SysDict sysDict) {
		return this.qu.getAllSpecDescribes(sysDict);
	}

	public String v(String str, String type)
			throws UnsupportedEncodingException, InvocationTargetException, ArrayIndexOutOfBoundsException {
		return type.equals("1") ? a.encode(str.getBytes()) : new String(a.decode(str));
	}

	public void z(List<SysDict> sysDictList) {
		Iterator arg2 = sysDictList.iterator();

		while (arg2.hasNext()) {
			SysDict sd = (SysDict) arg2.next();
			this.qu.updateSort(sd.getId(), "" + sd.getSequenceNumber());
		}

	}
}