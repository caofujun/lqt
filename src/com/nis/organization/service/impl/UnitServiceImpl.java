package com.nis.organization.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.dict.service.SysDictService;
import com.nis.organization.cache.UnitCache;
import com.nis.organization.dao.UnitDao;
import com.nis.organization.entity.Unit;
import com.nis.organization.service.UnitService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitServiceImpl implements UnitService {
	@Autowired
	private UnitDao vS;
	@Autowired
	private UnitCache wd;
	@Autowired
	private SysDictService p;

	public void save(Unit unit) {
		unit.setCreateAt(new Date());
		if (ab.isEmpty(unit.getSpCode())) {
			unit.setSpCode(t.aE(unit.getHospName()));
		}

		this.vS.save(unit);
		this.wd.a(unit.getUnitId());
	}

	public void delete(String unitId) {
		this.vS.delete(unitId);
		this.wd.a(unitId);
	}

	public void update(Unit unit) {
		unit.setLastAt(new Date());
		if (ab.isEmpty(unit.getSpCode())) {
			unit.setSpCode(t.aE(unit.getHospName()));
		}

		this.vS.update(unit);
		this.wd.a(unit.getUnitId());
	}

	public Unit get(String unitId) {
		return unitId == null ? null : this.wd.get(unitId);
	}

	public MyPage<Unit> a(Unit unit) {
		int total = this.vS.findUnitCount(unit);
		List data = null;
		if (total > 0) {
			data = this.vS.findUnit(unit);
			data = this.a(data);
		}

		return new MyPage(unit.getPage().intValue(), unit.getSize().intValue(), total, data);
	}

	private List<Unit> a(List<Unit> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			Unit unit = (Unit) arg2.next();
			if (StringUtils.isNotBlank(unit.getHospNature())) {
				unit.setShowHospNature(this.p.k("hosp_nature", unit.getHospNature(), (String) null));
			}

			if (StringUtils.isNotBlank(unit.getHospLevel())) {
				unit.setShowHospLevel(this.p.k("hosp_level", unit.getHospLevel(), (String) null));
			}

			if (StringUtils.isNotBlank(unit.getHospType())) {
				unit.setShowHospType(this.p.k("hosp_type", unit.getHospType(), (String) null));
			}

			if (unit.getFlag() != null) {
				unit.setShowFlag(this.p.k("enable_status", String.valueOf(unit.getFlag()), (String) null));
			}

			if (unit.getIfbranch() != null) {
				unit.setShowIfbranch(this.p.k("boolean", String.valueOf(unit.getIfbranch()), (String) null));
			}

			if (unit.getCreateAt() != null) {
				unit.setShowCreateAt(f.formatDate(unit.getCreateAt()));
			}

			if (unit.getLastAt() != null) {
				unit.setShowLastAt(f.formatDate(unit.getLastAt()));
			}
		}

		return data;
	}

	public List<Unit> getAll() {
		return this.vS.getAll();
	}

	public Unit findUnitByUnitName(String unitName) {
		return this.vS.findUnitByUnitName(unitName);
	}

	public List<Unit> findLike(String name) {
		return this.vS.findLike(name);
	}

	public void updateUnitState(String id, String code) {
		this.vS.updateUnitState(id, code);
	}

	public List<Unit> cl(String unitIds) {
		String[] ids = unitIds.split(",");
		ArrayList list = new ArrayList();
		String[] arg6 = ids;
		int arg5 = ids.length;

		for (int arg4 = 0; arg4 < arg5; ++arg4) {
			String id = arg6[arg4];
			if (!ab.isEmpty(id)) {
				list.add(this.get(id));
			}
		}

		return list;
	}

	public String getName(String unitId) {
		Unit unit = this.get(unitId);
		return unit == null ? null : unit.getHospName();
	}

	public void I(String unitId, String unitName) {
		Unit unit = this.get(unitId);
		if (unit == null) {
			try {
				this.vS.updateAllTableUnitId1(unitId, unitName);
				this.vS.updateAllTableUnitId2(unitId, unitName);
				this.vS.updateAllTableUnitId3(unitId, unitName);
				this.vS.updateAllTableUnitId4(unitId, unitName);
				this.vS.updateAllTableUnitId5(unitId, unitName);
				this.vS.updateAllTableUnitId6(unitId, unitName);
			} catch (Exception arg11) {
				arg11.printStackTrace();
			}

			try {
				this.vS.updateAllTableUnitId7(unitId, unitName);
				this.vS.updateAllTableUnitId8(unitId, unitName);
				this.vS.updateAllTableUnitId9(unitId, unitName);
				this.vS.updateAllTableUnitId10(unitId, unitName);
				this.vS.updateAllTableUnitId11(unitId, unitName);
				this.vS.updateAllTableUnitId12(unitId, unitName);
			} catch (Exception arg10) {
				arg10.printStackTrace();
			}

			try {
				this.vS.updateAllTableUnitId13(unitId, unitName);
			} catch (Exception arg9) {
				arg9.printStackTrace();
			}

			try {
				this.vS.updateAllTableUnitId14(unitId, unitName);
			} catch (Exception arg8) {
				arg8.printStackTrace();
			}

			try {
				this.vS.updateAllTableUnitId15(unitId, unitName);
			} catch (Exception arg7) {
				arg7.printStackTrace();
			}

			try {
				this.vS.updateAllTableUnitId16(unitId, unitName);
			} catch (Exception arg6) {
				arg6.printStackTrace();
			}

			try {
				this.vS.updateAllTableUnitId17(unitId, unitName);
			} catch (Exception arg5) {
				arg5.printStackTrace();
			}

			try {
				this.vS.updateAllTableUnitId18(unitId, unitName);
			} catch (Exception arg4) {
				arg4.printStackTrace();
			}

		}
	}
}