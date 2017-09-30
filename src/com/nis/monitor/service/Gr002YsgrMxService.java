package com.nis.monitor.service;

import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.TreeEntity;
import com.nis.monitor.entity.Gr002YsgrMx;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public interface Gr002YsgrMxService {
	void save(Gr002YsgrMx arg0);

	void delete(String arg0);

	void update(Gr002YsgrMx arg0);

	void updateSpecified(Gr002YsgrMx arg0, List<String> arg1);

	void updateByGr2Relid(Gr002YsgrMx arg0);

	Gr002YsgrMx get(String arg0);

	MyPage<Gr002YsgrMx> a(Gr002YsgrMx arg0);

	List<Gr002YsgrMx> getAll();

	List<Gr002YsgrMx> a(String arg0, String arg1, String arg2, Integer arg3);

	List<Gr002YsgrMx> findWarningResults2(String arg0, String arg1, String arg2, Integer arg3);

	List<Gr002YsgrMx> findInfectionCounts(Gr002YsgrMx arg0);

	List<Gr002YsgrMx> findInfectionDetail(Gr002YsgrMx arg0);

	MyPage<Gr002YsgrMx> b(Gr002YsgrMx arg0);

	void a(Gr002YsgrMx arg0, String arg1, LoginUser arg2);

	void a(Gr002YsgrMx arg0, String arg1, List<String> arg2, LoginUser arg3);

	MyPage<Gr002YsgrMx> c(Gr002YsgrMx arg0);

	void updateStateByGr2Relid(String arg0, Integer arg1);

	void a(Gr002YsgrMx arg0, Integer arg1);

	List<Gr002YsgrMx> findCasesReported(String arg0);

	Gr002YsgrMx getGr002YsgrMx(String arg0);

	Gr002YsgrMx getByGr2Relid(String arg0);

	void delByGr2Relid(String arg0);

	void updGr2RelidNull(String arg0);

	Gr002YsgrMx findGr002(Gr002YsgrMx arg0);

	List<TreeEntity> a(Gr002YsgrMx arg0, String arg1);

	Gr002YsgrMx b(Gr002YsgrMx arg0, String arg1);

	List<TreeEntity> c(Gr002YsgrMx arg0, String arg1);

	void updSameInfectCode(String arg0, String arg1, List<String> arg2);

	List<Gr002YsgrMx> workloadStatistics(Gr002YsgrMx arg0);

	List<Gr002YsgrMx> workloadDetail(Gr002YsgrMx arg0);

	void a(HttpServletResponse arg0, Gr002YsgrMx arg1);

	void deleteByZyidState(String arg0);

	List<Gr002YsgrMx> p(String arg0, String arg1, String arg2);

	List<Gr002YsgrMx> findByZyid(String arg0);

	List<Map<String, String>> findLiveInfect(String arg0, String arg1);
}