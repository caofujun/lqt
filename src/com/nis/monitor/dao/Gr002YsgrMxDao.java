package com.nis.monitor.dao;

import com.nis.comm.entity.TreeEntity;
import com.nis.monitor.entity.Gr002YsgrMx;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Gr002YsgrMxDao {
	void save(Gr002YsgrMx arg0);

	void delete(@Param("regId") String arg0);

	void update(Gr002YsgrMx arg0);

	void updateSpecified(@Param("gr002YsgrMx") Gr002YsgrMx arg0, @Param("updateAttrs") List<String> arg1);

	void updateByGr2Relid(Gr002YsgrMx arg0);

	Gr002YsgrMx get(@Param("regId") String arg0);

	List<Gr002YsgrMx> findGr002YsgrMx(Gr002YsgrMx arg0);

	int findGr002YsgrMxCount(Gr002YsgrMx arg0);

	List<Gr002YsgrMx> getAll();

	List<Gr002YsgrMx> findWarningResults(@Param("zyid") String arg0, @Param("regId") String arg1,
			@Param("gr2Relid") String arg2, @Param("infectTypeId") Integer arg3, @Param("qzValue") String arg4);

	List<Gr002YsgrMx> findWarningResults2(@Param("zyid") String arg0, @Param("infectCode") String arg1,
			@Param("regId") String arg2, @Param("infectTypeId") Integer arg3);

	List<Gr002YsgrMx> findInfectionCounts(Gr002YsgrMx arg0);

	List<Gr002YsgrMx> findInfectionDetail(Gr002YsgrMx arg0);

	List<Gr002YsgrMx> findConfirmInfection(Gr002YsgrMx arg0);

	int findConfirmInfectionCount(Gr002YsgrMx arg0);

	void updateForReport(Gr002YsgrMx arg0);

	void updateForDiagnosis1(@Param("zyid") String arg0, @Param("gr2Relid") String arg1);

	void updateForDiagnosis2(Gr002YsgrMx arg0);

	List<Gr002YsgrMx> findHasRuleOut(Gr002YsgrMx arg0);

	int findHasRuleOutCount(Gr002YsgrMx arg0);

	void updateStateByGr2Relid(@Param("gr2Relid") String arg0, @Param("state") Integer arg1);

	List<Gr002YsgrMx> findCasesReported(@Param("zyid") String arg0);

	Gr002YsgrMx getGr002YsgrMx(@Param("regId") String arg0);

	Gr002YsgrMx getByGr2Relid(@Param("gr2Relid") String arg0);

	void delByGr2Relid(@Param("gr2Relid") String arg0);

	void updGr2RelidNull(@Param("gr2Relid") String arg0);

	Gr002YsgrMx findGr002(Gr002YsgrMx arg0);

	List<TreeEntity> findAccordInfection(Gr002YsgrMx arg0);

	List<TreeEntity> findAccordCount(Gr002YsgrMx arg0);

	Gr002YsgrMx findInAndOutInfectCount(Gr002YsgrMx arg0);

	List<TreeEntity> findFavoritCount(Gr002YsgrMx arg0);

	List<TreeEntity> findFavorit(Gr002YsgrMx arg0);

	Gr002YsgrMx findInAndOutFavoritCount(Gr002YsgrMx arg0);

	void updSameInfectCode(@Param("refid") String arg0, @Param("zyid") String arg1,
			@Param("infectCodeList") List<String> arg2);

	List<Gr002YsgrMx> workloadStatistics(Gr002YsgrMx arg0);

	List<Gr002YsgrMx> workloadDetail(Gr002YsgrMx arg0);

	void deleteByZyidState(@Param("zyid") String arg0, @Param("tablename") String arg1);

	List<Gr002YsgrMx> findByZyidInfectCode(@Param("zyid") String arg0, @Param("infectCode") String arg1,
			@Param("tablename") String arg2, @Param("reportType") String arg3);

	List<Gr002YsgrMx> findByZyid(@Param("zyid") String arg0, @Param("tablename") String arg1);

	List<Map<String, String>> findLiveInfect(@Param("startDate") String arg0, @Param("endDate") String arg1);
}