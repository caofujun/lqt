package com.nis.organization.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.organization.cache.DepCache;
import com.nis.organization.dao.DepDao;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.organization.service.StandDeptService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepServiceImpl implements DepService {
	@Autowired
	private DepDao vQ;
	@Autowired
	private DepCache vW;
	@Autowired
	private StandDeptService vV;
	@Autowired
	private SysDictService p;

	public Result<String> f(Dep dep) {
		Result result = new Result();
		List deps = this.g(dep);
		if (deps != null && deps.size() > 0) {
			result.setResult("error_extis");
			result.setMsg("HIS科室编码、或科室名已存在！");
		} else {
			dep.setSpCode(t.aE(dep.getDeptName()));
			if (StringUtils.isBlank(dep.getId())) {
				dep.setId(z.b(bg.mJ));
				this.vQ.save(dep);
			} else {
				dep.setLastAt(new Date());
				this.vQ.update(dep);
			}

			this.vW.s(dep.getHospId(), dep.getDeptId());
			result.setResult("success");
		}

		this.p.updateLastTimebyCode("data_init", "KSXX", f.getCurDate());
		return result;
	}

	public List<Dep> g(Dep dep) {
		return this.vQ.findDepExtis(dep);
	}

	public void delete(String hospId, String id) {
		Dep dep = this.F(hospId, id);
		this.vQ.delete(dep.getId());
		this.vW.s(hospId, id);
		this.p.updateLastTimebyCode("data_init", "KSXX", f.getCurDate());
	}

	public MyPage<Dep> b(Dep dep) {
		int total = this.vQ.findDepCount(dep);
		List data = null;
		if (total > 0) {
			data = this.vQ.findDep(dep);
			data = this.a(data);
		}

		return new MyPage(dep.getPage().intValue(), dep.getSize().intValue(), total, data);
	}

	public Integer c(Dep dep) {
		return Integer.valueOf(this.vQ.findDepCount(dep));
	}

	private List<Dep> a(List<Dep> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			Dep dep = (Dep) arg2.next();
			dep.setDeptClassifyName(this.p.k("dept_classify", dep.getDeptClassify(), (String) null));
		}

		return data;
	}

	public List<Dep> getAll() {
		return this.vQ.getAll();
	}

	public List<Dep> getDepList(Dep dep) {
		return this.vQ.getDepList(dep);
	}

	public List<Dep> d(Dep searchDep) {
		return this.vQ.getDepList(searchDep);
	}

	public List<Dep> getDepByUnitIdAndDepTypeExt(String hospId, String depType, String dgsType, Long isClosed) {
		return this.vQ.getDepByUnitIdAndDepTypeExt(hospId, depType, dgsType, isClosed);
	}

	public List<Dep> findLike(String hospId, String name) {
		return this.vQ.findLike(hospId, name);
	}

	public Dep F(String hospId, String id) {
		return this.vW.F(hospId, id);
	}

	public String H(String hospId, String id) {
		Dep dep = this.F(hospId, id);
		return dep != null ? dep.getDeptName() : null;
	}

	public String G(String hospId, String ids) {
		StringBuffer names = new StringBuffer();
		String[] idArr = ids.split(",");
		String[] arg7 = idArr;
		int arg6 = idArr.length;

		for (int arg5 = 0; arg5 < arg6; ++arg5) {
			String id = arg7[arg5];
			if (ab.isNotEmpty(id)) {
				names.append(this.H(hospId, id.trim())).append(",");
			}
		}

		if (names.length() > 0) {
			names.setCharAt(names.length() - 1, ' ');
		}

		return names.toString();
	}

	public Dep get(String id) {
		return this.vQ.get(id);
	}

	public String getName(String id) {
		Dep dep = this.get(id);
		return dep != null ? dep.getDeptName() : null;
	}

	public List<Dep> getByDeptIds(List<String> deptIds) {
		return this.vQ.getByDeptIds(deptIds);
	}

	public List<Dep> getDept() {
		return this.vQ.getDept();
	}

	public List<Dep> e(Dep dep) {
		return this.vQ.findDep(dep);
	}

	public Result<String> a(Dep dep) {
		Result result = new Result();
		this.vQ.updateBaseInfect(dep);
		result.setResult("success");
		return result;
	}

	public String getDeptType(String deptId) {
		return this.vQ.getDeptType(deptId);
	}

	public Result<String> k(String yzName, String nicuId, String nicuName, String day) {
		Result result = new Result();
		this.vQ.excuteNicu(yzName, nicuId, nicuName, day);
		result.setResult("success");
		return result;
	}

	public Result<String> m(String yzName, String xsrId, String xsrName, String day) {
		Result result = new Result();
		this.vQ.excuteXsr(yzName, xsrId, xsrName, day);
		result.setResult("success");
		return result;
	}

	public Result<String> l(String yzName, String ccuId, String ccuName, String day) {
		Result result = new Result();
		this.vQ.excuteCcu(yzName, ccuId, ccuName, day);
		result.setResult("success");
		return result;
	}

	public Result<String> a(String yzName, String icuId, String icuName, String day, String[] deptId) {
		Result result = new Result();
		this.vQ.excuteIcu(yzName, icuId, icuName, day, deptId);
		result.setResult("success");
		return result;
	}

	public Result<String> n(String yzName, String xccuId, String xccuName, String day) {
		Result result = new Result();
		this.vQ.excuteXCcu(yzName, xccuId, xccuName, day);
		result.setResult("success");
		return result;
	}

	public Result<String> f(String orderAt, String zyid, String nicuId, String day, String yzName) {
		Result result = new Result();
		this.vQ.excuteNicuBack(orderAt, zyid, nicuId, day, yzName);
		result.setResult("success");
		return result;
	}

	public Result<String> g(String orderAt, String zyid, String ccuId, String day, String yzName) {
		Result result = new Result();
		this.vQ.excuteCcuBack(orderAt, zyid, ccuId, day, yzName);
		result.setResult("success");
		return result;
	}

	public List<Map<String, String>> getNicuListBack(String yzName, String day) {
		return this.vQ.getNicuListBack(yzName, day);
	}

	public List<Map<String, Object>> getICUList(String yzName, String day) {
		return this.vQ.getICUList(yzName, day);
	}

	public List<Map<String, Object>> getXsrList(String day) {
		return this.vQ.getXsrList(day);
	}

	public void updateYzxxb(String deptId, String deptName, String zyid, Date orderAt) {
		this.vQ.updateYzxxb(deptId, deptName, zyid, orderAt);
	}

	public void updateSjbb(String deptId, String deptName, String zyid, Date orderAt) {
		this.vQ.updateSjbb(deptId, deptName, zyid, orderAt);
	}

	public void updateSsxxb(String deptId, String deptName, String zyid, Date orderAt) {
		this.vQ.updateSsxxb(deptId, deptName, zyid, orderAt);
	}

	public void updateZkjl(String deptId, String deptName, String zyid, Date orderAt) {
		this.vQ.updateZkjl(deptId, deptName, zyid, orderAt);
	}
}