package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw008Xmsq;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw008XmsqService {
	void save(Hw008Xmsq arg0);

	void delete(String arg0, String arg1);

	void update(Hw008Xmsq arg0);

	Hw008Xmsq get(String arg0, String arg1);

	MyPage<Hw008Xmsq> a(Hw008Xmsq arg0);

	List<Hw008Xmsq> getAll();

	List<Hw008Xmsq> findXmsqList(Hw008Xmsq arg0);

	void b(Hw008Xmsq arg0);

	int judgeResultsPermissions(String arg0, String arg1, String arg2, String arg3);

	int judgeReportPermissions(String arg0, String arg1, String arg2, String arg3);
}