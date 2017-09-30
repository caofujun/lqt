package com.nis.monitor.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Gr002YsgrMx;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Bk001SbkService {
	void save(Bk001Sbk arg0);

	void delete(String arg0);

	void update(Bk001Sbk arg0);

	void updateSpecified(Bk001Sbk arg0, List<String> arg1);

	Bk001Sbk get(String arg0);

	MyPage<Bk001Sbk> a(Bk001Sbk arg0);

	List<Bk001Sbk> getAll();

	MyPage<Bk001Sbk> b(Bk001Sbk arg0);

	List<Bk001Sbk> findReportCards(Bk001Sbk arg0);

	Bk001Sbk findCardInfo(String arg0);

	void c(Bk001Sbk arg0);

	void d(Bk001Sbk arg0);

	void a(Bk001Sbk arg0, Gr002YsgrMx arg1, LoginUser arg2, String arg3, String arg4, String arg5);

	void a(Bk002Grzd arg0, LoginUser arg1, String arg2, String arg3, String arg4, Gr002YsgrMx arg5, Bk001Sbk arg6);

	List<Bk001Sbk> findByZyid(String arg0);

	List<Bk001Sbk> getByTestNoAndPathoId(String arg0, String arg1);

	List<Bk001Sbk> D(String arg0, String arg1);

	List<Bk001Sbk> isReportBefore(String arg0, String arg1, String arg2);

	List<Bk001Sbk> findByZyidAndCode(String arg0, String arg1);

	List<Bk001Sbk> findByZyidState(String arg0);

	void b(String arg0, AcAccount arg1);

	List<Bk001Sbk> getReportHistoryDetailsByZyid(String arg0);
}