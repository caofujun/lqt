package com.nis.patient.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St002Zdxxb;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St002ZdxxbService {
	void save(St002Zdxxb arg0);

	void delete(String arg0);

	void update(St002Zdxxb arg0);

	St002Zdxxb get(String arg0);

	MyPage<St002Zdxxb> a(St002Zdxxb arg0);

	List<St002Zdxxb> getAll();

	List<St002Zdxxb> findListByZyid(String arg0);

	List<St002Zdxxb> findListByZyidAndName(String[] arg0);

	String findDiagnosisName(String arg0);

	List<St002Zdxxb> findByZyid(String arg0);

	List<St002Zdxxb> findZyZdList(int arg0);

	List<DataWarning> findPatentZdxxbWarning(Date arg0, Date arg1);
}