package com.nis.monitor.service;

import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Gr002YsgrMx;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface Bk002GrzdService {
	void save(Bk002Grzd arg0);

	void delete(String arg0);

	void update(Bk002Grzd arg0);

	void updateSpecified(Bk002Grzd arg0, List<String> arg1);

	Bk002Grzd get(String arg0);

	MyPage<Bk002Grzd> a(Bk002Grzd arg0);

	List<Bk002Grzd> getAll();

	List<Bk002Grzd> getReportInfect(String arg0, String arg1);

	Bk002Grzd getInfectInfo(String arg0);

	void a(Bk002Grzd arg0, Bk001Sbk arg1, String[] arg2, String[] arg3, String arg4);

	void a(Bk002Grzd arg0, Bk001Sbk arg1, String[] arg2, String[] arg3, LoginUser arg4, Gr002YsgrMx arg5);

	void a(List<Bk002Grzd> arg0, Integer arg1, Bk001Sbk arg2, LoginUser arg3);

	int findUnAuditCount(String arg0);

	void bN(String arg0);

	void a(Bk002Grzd arg0, Bk001Sbk arg1, LoginUser arg2);

	void a(Gr002YsgrMx arg0, Bk001Sbk arg1, Bk002Grzd arg2);

	void b(Gr002YsgrMx arg0, Bk001Sbk arg1, Bk002Grzd arg2);

	void a(Bk001Sbk arg0, Bk002Grzd arg1, LoginUser arg2, String arg3, String arg4, String arg5, String arg6,
			String arg7);

	List<Map<String, Object>> findqrxczxgxgrlcs(String arg0, String arg1, String arg2);

	List<Bk002Grzd> getbyOperRelid(String arg0);

	Bk002Grzd getOneInfectByRefid(String arg0);

	List<Map<String, Object>> findMainInfectionParts(Date arg0, Date arg1);

	List<Map<String, Object>> findMainIncidence(Date arg0, Date arg1);

	List<Map<String, Object>> findBloodInfections(Date arg0, Date arg1);

	List<Bk002Grzd> queryGrPosition();

	List<Bk002Grzd> getLastReport(String arg0);

	void updateOutcome(Bk002Grzd arg0);

	void returnCard(Bk002Grzd arg0);
}