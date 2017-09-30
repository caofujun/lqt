package com.nis.patient.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St011Syjgb;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St011SyjgbService {
	void save(St011Syjgb arg0);

	void delete(String arg0);

	void update(St011Syjgb arg0);

	St011Syjgb get(String arg0);

	MyPage<St011Syjgb> a(St011Syjgb arg0);

	List<St011Syjgb> getAll();

	List<St011Syjgb> findSt011SyjgbList(St011Syjgb arg0);

	List<St011Syjgb> findByBDB(String arg0, String arg1, String arg2);

	List<St011Syjgb> findByBXB(String arg0, String arg1, List<String> arg2);

	List<St011Syjgb> findDrugAllergytList(String arg0);

	List<St011Syjgb> findByOrderNoAndSn(String arg0, String arg1);

	List<St011Syjgb> queryForYJ(String arg0, String arg1);

	List<St011Syjgb> t(String arg0, String arg1, String arg2);

	List<DataWarning> findPatentSyjgbWarning(Date arg0, Date arg1);

	List<St011Syjgb> findByZyidTestNo(String arg0, String arg1);

	List<St011Syjgb> findWaitAnalysis(String arg0, String arg1);

	List<St011Syjgb> findSt011Syjgbqst(St011Syjgb arg0);
}