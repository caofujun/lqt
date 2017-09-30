package com.nis.patient.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.MonitorOrder;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.entity.St012Kjyw;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St004YzxxbService {
	void save(St004Yzxxb arg0);

	void delete(String arg0);

	void update(St004Yzxxb arg0);

	void updateSpecified(St004Yzxxb arg0, List<String> arg1);

	St004Yzxxb get(String arg0);

	MyPage<St004Yzxxb> a(St004Yzxxb arg0);

	List<St004Yzxxb> getAll();

	MyPage<St004Yzxxb> b(St004Yzxxb arg0);

	void a(MonitorOrder arg0, String arg1);

	List<St004Yzxxb> likeOrderName(St004Yzxxb arg0);

	List<St004Yzxxb> findInOrderName(String arg0, String arg1);

	void a(St012Kjyw arg0, String arg1);

	List<St004Yzxxb> findListByzyidName(String[] arg0);

	List<St004Yzxxb> findDrugSituation(St004Yzxxb arg0);

	List<St004Yzxxb> findDrugSituationByPatient(St004Yzxxb arg0);

	List<St004Yzxxb> findByZyid(St004Yzxxb arg0);

	Date getMonitorPatientYzxxLastAt(String arg0);

	void a(St004Yzxxb arg0, String[] arg1);

	List<DataWarning> findPatentYzxxbWarning(Date arg0, Date arg1);

	List<St004Yzxxb> getYzxx(String arg0);

	List<St003Cryxxb> findKjywWaitAnaly(Integer arg0);

	int findKjywWaitCount();

	List<St004Yzxxb> findKjywByZyid(String arg0);

	void batchUpdAnalFlag(List<St004Yzxxb> arg0);

	void b(St004Yzxxb arg0, String[] arg1);

	List<St004Yzxxb> findDrug(St004Yzxxb arg0);

	List<St004Yzxxb> findDrugSituationByPatientTemp(St004Yzxxb arg0);

	void updAnalFlag(St004Yzxxb arg0);

	void updKeepToWaitState();

	int getUseDrugNum(String arg0, Date arg1, Date arg2, String arg3, String arg4);

	int getUseDrugNumNo(String arg0, Date arg1, Date arg2, String arg3);

	int getUseUnLimitNum(String arg0, Date arg1, Date arg2);

	int getUseLimitNum(String arg0, Date arg1, Date arg2);

	Date getOrderAtNearStart(String arg0, Date arg1, Date arg2);

	int getDrugNumTheDay(String arg0, Date arg1, Date arg2);

	List<St004Yzxxb> findDrugbyZyid(St004Yzxxb arg0);

	List<St004Yzxxb> findDrugAddbyZyid(St004Yzxxb arg0);

	List<St004Yzxxb> findForYzAnalyzis(St004Yzxxb arg0);
}