package com.nis.follow.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.follow.dao.FoPatientDao;
import com.nis.follow.entity.FoPatient;
import com.nis.follow.service.FoPatientService;
import com.nis.patient.entity.St003Cryxxb;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FoPatientServiceImpl implements FoPatientService {
	@Autowired
	private FoPatientDao qX;

	@SqlLog(p = "设置关注患者")
	public void save(FoPatient foPatient) {
		foPatient.setFoId(z.a(bg.nQ));
		this.qX.save(foPatient);
	}

	public void delete(String foId) {
		this.qX.delete(foId);
	}

	public void update(FoPatient foPatient) {
		this.qX.update(foPatient);
	}

	public FoPatient get(String foId) {
		return this.qX.get(foId);
	}

	public MyPage<FoPatient> a(FoPatient foPatient) {
		int total = this.qX.findFoPatientCount(foPatient);
		List data = null;
		if (total > 0) {
			data = this.qX.findFoPatient(foPatient);
		}

		return new MyPage(foPatient.getPage().intValue(), foPatient.getSize().intValue(), total, data);
	}

	public List<FoPatient> getAll() {
		return this.qX.getAll();
	}

	public void deleteByPatientId(String patientId, String followName) {
		this.qX.deleteByPatientId(patientId, followName);
	}

	public List<FoPatient> getByPatientId(String patientId) {
		return this.qX.getByPatientId(patientId);
	}

	public List<St003Cryxxb> findByDeptIdDetail(FoPatient foPatient) {
		return this.qX.findByDeptIdDetail(foPatient);
	}
}