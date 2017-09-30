package com.nis.zb.dao;

import com.nis.zb.entity.ZbRecord;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ZbRecordDao {
	List<ZbRecord> findZbRecordList(ZbRecord arg0);

	List<HashMap<String, Object>> findOuthospitalinputZBList(ZbRecord arg0);

	void updateOuthospitalinputZBFlag(@Param("id") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	List<HashMap<String, Object>> findSt005SsxxbZBMapList(ZbRecord arg0);

	List<HashMap<String, Object>> findSt005SsxxbZBMapListDatail(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> caseRegist(ZbRecord arg0);

	void updateSt005SsxxbZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	List<HashMap<String, Object>> findInfectstatusZBList(ZbRecord arg0);

	List<HashMap<String, Object>> findInfectFactorZBList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findExemplarZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findPathogenyZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findUseAntibZBMapList(@Param("idTemps") List<String> arg0);

	void updateBk002GrzdZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	List<HashMap<String, Object>> findNeedLeMasterZBMapList(ZbRecord arg0);

	List<HashMap<String, Object>> findNeedlestickZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findBloodZBMapList(@Param("idTemps") List<String> arg0);

	void updateBl002SjdjZBFlag(@Param("blId") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	List<HashMap<String, Object>> findIcuInfectZBMapList(ZbRecord arg0);

	List<HashMap<String, Object>> findIcuSampleZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findIcuIllBodyZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findIcuAntiZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findIcuPotiantLogZBMapList(ZbRecord arg0);

	List<HashMap<String, Object>> findIcuDangerGradeZBMapList(ZbRecord arg0);

	void updateBk002GrzdICUZBResult(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	List<HashMap<String, Object>> findNicuInfectZBMapList(ZbRecord arg0);

	List<HashMap<String, Object>> findNicuSampleZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findNicuIllBodyZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findNicuAntiZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findNicuLogZBMapList(ZbRecord arg0);

	void updateBk002GrzdNICUZBResult(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	List<HashMap<String, Object>> findAntibInputZBMapList(ZbRecord arg0);

	List<HashMap<String, Object>> findNowUseAntibZBMapList(@Param("idTemps") List<String> arg0);

	void updateJkYzLhyyZBFlag(@Param("zyid") String arg0, @Param("tranFlag") String arg1, @Param("tranDate") Date arg2);

	List<HashMap<String, Object>> findJcReportMasterZBMapList(ZbRecord arg0);

	List<HashMap<String, Object>> findJcReportDetailZBMapList(@Param("idTemps") List<String> arg0);

	void updateHw101JcdjZBFlag(@Param("djid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	List<HashMap<String, Object>> findYcxjymasterZBMapList(ZbRecord arg0);

	List<HashMap<String, Object>> findycxjydetailZBMapList(@Param("idTemps") List<String> arg0);

	List<HashMap<String, Object>> findWsclmasterZBMapList(ZbRecord arg0);

	List<HashMap<String, Object>> findWscldetailZBMapList(@Param("idTemps") List<String> arg0);

	void updateBk003YgysZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateSt005SsxxbDetailZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateBl002SjdjZBFlag1(@Param("blId") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateBl002SjdjZBFlag2(@Param("blId") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateSt003CryxxbZBFlag(@Param("zyid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void excutePrOut(Map<String, Object> arg0);

	void updateBk004Sjbb3ICUZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateBk004Sjbb5ICUZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateBk004Sjbb4ICUZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateGm001DjpdmICUZBFlag(@Param("startDate") Date arg0, @Param("endDate") Date arg1,
			@Param("tranFlag") String arg2, @Param("tranDate") Date arg3);

	void updateGm003YbsjICUZBFlag(@Param("startDate") Date arg0, @Param("endDate") Date arg1,
			@Param("tranFlag") String arg2, @Param("tranDate") Date arg3);

	List<ZbRecord> findZbxhlList(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	void updateBk004Sjbb6NICUZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateBk004Sjbb7NICUZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateBk004Sjbb8NICUZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateGm004JcmxNICUZBFlag(@Param("startDate") Date arg0, @Param("endDate") Date arg1,
			@Param("tranFlag") String arg2, @Param("tranDate") Date arg3);

	void updateSt015YzxxbKjywZBFlag(@Param("zyid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateHw102JcdmxZBFlag(@Param("djid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateBk004SjbbZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	void updateBk004Sjbb1ZBFlag(@Param("relid") String arg0, @Param("tranFlag") String arg1,
			@Param("tranDate") Date arg2);

	List<ZbRecord> findZbRecord(ZbRecord arg0);

	List<ZbRecord> getRate(ZbRecord arg0);

	List<ZbRecord> findCaseRegistCount(ZbRecord arg0);
}