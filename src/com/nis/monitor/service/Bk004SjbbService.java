package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Bk004Sjbb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Bk004SjbbService {
	void save(Bk004Sjbb arg0);

	void delete(Bk004Sjbb arg0);

	void update(Bk004Sjbb arg0);

	Bk004Sjbb get(Bk004Sjbb arg0);

	MyPage<Bk004Sjbb> a(Bk004Sjbb arg0);

	List<Bk004Sjbb> getAll();

	int findNumBytestNo(Bk004Sjbb arg0);

	void a(Bk002Grzd arg0, Bk001Sbk arg1, String[] arg2, String arg3);

	List<Bk004Sjbb> findBk004ByRefid(String arg0);

	List<Bk004Sjbb> findMultiDrugResis(String arg0);

	List<Bk004Sjbb> findPathogenDetection(Bk004Sjbb arg0);

	int findPathogenDetectionCount(Bk004Sjbb arg0);

	void b(Bk002Grzd arg0, Bk001Sbk arg1, String[] arg2, String arg3);

	void delByRefid(String arg0);

	Integer getInfectTypeByTestNo(String arg0);
}