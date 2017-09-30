package com.nis.organization.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.access.service.AcRoleService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.n;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.organization.cache.DoctorCache;
import com.nis.organization.dao.DepDocLinkDao;
import com.nis.organization.dao.DoctorDao;
import com.nis.organization.entity.Dep;
import com.nis.organization.entity.DepDocLink;
import com.nis.organization.entity.Doctor;
import com.nis.organization.service.DepService;
import com.nis.organization.service.DoctorService;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorDao vR;
	@Autowired
	private DepDocLinkDao vY;
	@Autowired
	private DoctorCache vZ;
	@Autowired
	private DepService e;
	@Autowired
	private SysDictService wa;
	@Autowired
	private SysDictService p;
	@Autowired
	private AcRoleService g;

	public void save(Doctor doctor) {
		doctor.setId(doctor.getEmployeeId());
		String pinyin = t.aE(doctor.getEmployeeName());
		doctor.setSpCode(pinyin);
		doctor.setLastAt(f.getCurDate());
		if (StringUtils.isNoneBlank(new CharSequence[]{doctor.getDeptId()})) {
			String[] deptIds = doctor.getDeptId().split(",");
			new DepDocLink();
			String[] arg7 = deptIds;
			int arg6 = deptIds.length;

			for (int arg5 = 0; arg5 < arg6; ++arg5) {
				String deptId = arg7[arg5];
				DepDocLink depDocLink = new DepDocLink();
				depDocLink.setId(z.b(bg.mI));
				depDocLink.setDoctorId(doctor.getId());
				depDocLink.setDeptId(deptId);
				this.vY.save(depDocLink);
			}
		}

		this.vR.save(doctor);
		this.vZ.a(doctor.getId());
	}

	public List<Doctor> listDoctor(String hospId, List<String> doctorIdList) {
		return this.vR.listDoctor(hospId, doctorIdList);
	}

	public List<Doctor> findDoctorListByDept(String hospId, List<String> deptIds, String deptId) {
		return this.vR.findDoctorListByDept(hospId, deptIds, deptId);
	}

	public void delete(String hospId, String depId, String doctorId) {
		this.vR.delete(doctorId);
		this.vY.delete(doctorId);
		this.vZ.a(doctorId);
	}

	public void update(Doctor doctor) {
		String pinyin = t.aE(doctor.getEmployeeName());
		doctor.setSpCode(pinyin);
		this.vR.update(doctor);
		this.vY.delete(doctor.getId());
		if (StringUtils.isNoneBlank(new CharSequence[]{doctor.getDeptId()})) {
			String[] deptIds = doctor.getDeptId().split(",");
			new DepDocLink();
			String[] arg7 = deptIds;
			int arg6 = deptIds.length;

			for (int arg5 = 0; arg5 < arg6; ++arg5) {
				String deptId = arg7[arg5];
				DepDocLink depDocLink = new DepDocLink();
				depDocLink.setId(z.b(bg.mI));
				depDocLink.setDoctorId(doctor.getId());
				depDocLink.setDeptId(deptId);
				this.vY.save(depDocLink);
			}
		}

		this.vZ.a(doctor.getId());
	}

	public MyPage<Doctor> a(Doctor doctor) {
		int total = this.vR.findDoctorCount(doctor);
		List data = null;
		if (total > 0) {
			data = this.vR.findDoctor(doctor);
			this.a(data);
		}

		return new MyPage(doctor.getPage().intValue(), doctor.getSize().intValue(), total, data);
	}

	public MyPage<Doctor> b(Doctor doctor) {
		int total = this.vR.findDoctorCount(doctor);
		List data = null;
		if (total > 0) {
			data = this.vR.findDoctor(doctor);
		}

		return new MyPage(doctor.getPage().intValue(), doctor.getSize().intValue(), total, data);
	}

	public List<Doctor> a(List<Doctor> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			Doctor doctor = (Doctor) arg2.next();
			if (!ab.aM(doctor.getDeptId())) {
				String[] depts = doctor.getDeptId().split(",");
				String[] deptnames = new String[depts.length];

				for (int i = 0; i < depts.length; ++i) {
					Dep dep = this.e.get(depts[i]);
					if (dep != null) {
						deptnames[i] = dep.getDeptName();
					}
				}

				doctor.setShowDeptId(StringUtils.join(deptnames, ","));
			}

			if (StringUtils.isNotBlank(doctor.getCclass())) {
				doctor.setShowCclass(this.p.k("doctor_type", doctor.getCclass(), (String) null));
			}

			if (StringUtils.isNotBlank(doctor.getDrLinetype())) {
				doctor.setDrLinetypeName(this.p.k("dr_linetype", doctor.getDrLinetype(), (String) null));
			}
		}

		return data;
	}

	public List<Doctor> getAll(String hospId) {
		return this.vR.getAll(hospId);
	}

	public List<Doctor> findLike(String hospId, String deptId, String name) {
		return this.vR.findLike(hospId, deptId, name);
	}

	public Doctor get(String id) {
		return this.vR.get(id);
	}

	public AcAccount a(String hospId, String employeeId, String pwd, boolean base64) {
		if (base64) {
			pwd = EncryptUtils.aj(pwd);
		}

		if (ab.isNotEmpty(employeeId)) {
			List doctorList = this.vR.login(hospId, employeeId, pwd);
			if (doctorList != null && !doctorList.isEmpty()) {
				Doctor doctor = (Doctor) doctorList.get(0);
				AcAccount acAccount = this.c(doctor);
				return acAccount;
			}
		}

		return null;
	}

	public AcAccount c(Doctor doctor) {
		AcAccount acAccount = null;
		if (doctor != null) {
			acAccount = new AcAccount();
			acAccount.setUserId(doctor.getId());
			acAccount.setPasswd(doctor.getAuthCode());
			acAccount.setUsername(doctor.getEmployeeId());
			acAccount.setRealname(doctor.getEmployeeName());
			acAccount.setDoctorId(doctor.getId());
			acAccount.setDocNo(doctor.getEmployeeId());
			acAccount.setScopeInfo(doctor.getDeptId());
			acAccount.setUnitId(doctor.getHospId());
			acAccount.setErrorTimes(new Long(0L));
			acAccount.setDataScope(n.gh.getValue());
			acAccount.setRoleCur(this.g.get(doctor.getRoleId()));
		}

		return acAccount;
	}

	public String q(String hospId, String depId, String employeeId) {
		Doctor doc = this.get(employeeId);
		return doc == null ? null : doc.getEmployeeName();
	}

	public List<Doctor> findExtis(Doctor doctor) {
		return this.vR.findExtis(doctor);
	}

	public MyPage<Doctor> a(Doctor doctor, String depId) {
		int total = this.vR.findNosetDepDoctorCount(doctor, depId);
		List data = null;
		if (total > 0) {
			data = this.vR.findNosetDepDoctor(doctor, depId);
			this.a(data);
		}

		return new MyPage(doctor.getPage().intValue(), doctor.getSize().intValue(), total, data);
	}

	public Doctor ck(String id) {
		return this.vR.get(id);
	}

	public Doctor getDoctorByHisDocNo(Doctor doctor) {
		return this.vR.getDoctorByHisDocNo(doctor);
	}

	public void updatePwd(String hospId, String doctorId, String pwd) {
		this.vR.updatePwd(hospId, doctorId, pwd);
	}

	public List<Doctor> queryToSelect(Doctor doctor) {
		return this.vR.queryToSelect(doctor);
	}

	public List<Doctor> queryToSelect1(Doctor doctor) {
		return this.vR.queryToSelect1(doctor);
	}

	public void r(String id, String havegrants, String delDepIds) {
		String scopeInfo = "";
		Doctor doctor = this.get(id);
		if (StringUtils.isNotBlank(doctor.getDeptId())) {
			scopeInfo = doctor.getDeptId();
		}

		String[] delDepId;
		if (StringUtils.isNotBlank(havegrants)) {
			delDepId = havegrants.split(",");

			for (int depId = 0; depId < delDepId.length; ++depId) {
				if ((scopeInfo + ",").indexOf(delDepId[depId] + ",") < 0) {
					if (!scopeInfo.endsWith(",") && scopeInfo.length() != 0) {
						scopeInfo = scopeInfo + "," + delDepId[depId];
					} else {
						scopeInfo = scopeInfo + delDepId[depId];
					}
				}
			}
		}

		if (StringUtils.isNotBlank(scopeInfo) && StringUtils.isNotBlank(delDepIds)) {
			delDepId = delDepIds.split(",");
			if (delDepId != null && delDepId.length > 0) {
				scopeInfo = "," + scopeInfo + ",";
				String[] arg9 = delDepId;
				int arg8 = delDepId.length;

				for (int arg7 = 0; arg7 < arg8; ++arg7) {
					String arg10 = arg9[arg7];
					if (scopeInfo.indexOf("," + arg10 + ",") >= 0) {
						scopeInfo = scopeInfo.replace("," + arg10 + ",", ",");
					}
				}

				if (scopeInfo.length() > 1) {
					scopeInfo = scopeInfo.substring(scopeInfo.indexOf(",") + 1, scopeInfo.lastIndexOf(","));
				} else {
					scopeInfo = "";
				}
			}
		}

		doctor.setDeptId(scopeInfo);
		this.vR.update(doctor);
		this.vZ.a(doctor.getId());
	}
}