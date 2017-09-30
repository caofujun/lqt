package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.patient.entity.St004Yzxxb;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr018YsgrysService {
	void save(Gr018Ysgrys arg0);

	void batchInsert(List<Gr018Ysgrys> arg0);

	void saveList(List<Gr018Ysgrys> arg0);

	void delete(String arg0);

	void update(Gr018Ysgrys arg0);

	void bO(String arg0);

	void K(List<Gr018Ysgrys> arg0);

	Gr018Ysgrys bP(String arg0);

	MyPage<Gr018Ysgrys> a(Gr018Ysgrys arg0);

	List<Gr018Ysgrys> getAll();

	List<Gr018Ysgrys> findListByZyid(String arg0);

	List<Gr018Ysgrys> E(String arg0, String arg1);

	List<Gr018Ysgrys> findByFX(Gr018Ysgrys arg0);

	void bQ(String arg0);

	List<Gr018Ysgrys> findByState();

	int findByStateCount(Integer arg0);

	List<Gr018Ysgrys> findByZyid(String arg0, String arg1);

	void b(String arg0, Integer arg1);

	Date getMonitorPatientBcLastAt();

	int a(String arg0, Date arg1, String arg2, String arg3);

	St004Yzxxb c(String arg0, Date arg1);

	List<String> ac();

	int ad();

	int d(String arg0, Date arg1);

	List<Gr018Ysgrys> findTwList(Double arg0);

	int findTwCount(Double arg0);
}