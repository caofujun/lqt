package com.nis.patient.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St006Twxx;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface St006TwxxService {
	void save(St006Twxx arg0);

	void delete(String arg0);

	void update(St006Twxx arg0);

	St006Twxx get(String arg0);

	St006Twxx getMaxATByZY(String arg0);

	MyPage<St006Twxx> a(St006Twxx arg0);

	List<St006Twxx> findTwxxList(St006Twxx arg0);

	List<St006Twxx> getAll();

	List<Map<String, Object>> h(Date arg0, Date arg1);

	List<St006Twxx> findAbnormalByZyid(String arg0);

	Date getMonitorPatientTwxxLastAt();

	List<DataWarning> findPatentTwxxWarning(Date arg0, Date arg1);

	int getNumBeforeFever(String arg0, Date arg1, Double arg2);

	void updateAnalFlag(String arg0, Integer arg1, Date arg2);
}