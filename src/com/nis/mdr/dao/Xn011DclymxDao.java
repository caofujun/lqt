package com.nis.mdr.dao;

import com.nis.mdr.entity.ViewMdr;
import com.nis.mdr.entity.Xn011Dclymx;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Xn011DclymxDao {
	void save(Xn011Dclymx arg0);

	void batchInsert(@Param("dclymxList") List<Xn011Dclymx> arg0, @Param("tablename") String arg1);

	void delete(@Param("dt") Date arg0);

	void update(Xn011Dclymx arg0);

	void updateByTestOrderNo(Xn011Dclymx arg0);

	Xn011Dclymx get(@Param("dt") Date arg0);

	Xn011Dclymx get1(@Param("zyid") String arg0, @Param("testOrderNo") String arg1, @Param("itemType") Integer arg2,
			@Param("itemCode") String arg3, @Param("pathoCode") String arg4);

	List<Xn011Dclymx> findXn011Dclymx(Xn011Dclymx arg0);

	int findXn011DclymxCount(Xn011Dclymx arg0);

	List<Xn011Dclymx> getAll();

	List<ViewMdr> findViewMdr(ViewMdr arg0);

	int findViewMdrCount(ViewMdr arg0);

	int findViewMdrDayCount(ViewMdr arg0);

	List<ViewMdr> findViewMdrDay(ViewMdr arg0);

	List<ViewMdr> findViewMdrList(ViewMdr arg0);

	List<ViewMdr> findViewMdrDayList(ViewMdr arg0);

	Map<String, Object> findByDateAndTypeCount(@Param("date") String arg0, @Param("specType") String arg1,
			@Param("deptIdIn") List<String> arg2);

	Map<String, Object> findByDateCount(@Param("date") String arg0, @Param("deptIdIn") List<String> arg1);

	List<Map<String, Object>> findwswbbfb(@Param("startDate") String arg0, @Param("endDate") String arg1,
			@Param("ificu") String arg2);

	List<Map<String, Object>> findwswjcqk(@Param("startDate") String arg0, @Param("endDate") String arg1,
			@Param("ificu") String arg2);

	List<Map<String, Object>> dcnyfbt(@Param("startDate") String arg0, @Param("endDate") String arg1,
			@Param("ificu") String arg2);

	List<Map<String, Object>> grblfbt(@Param("startDate") String arg0, @Param("endDate") String arg1,
			@Param("ificu") String arg2);

	List<Map<String, Object>> findMainSamples(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findMainFocusBacteria(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findMainMoreResistant(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<ViewMdr> findCheckOutbacteria(@Param("zyid") String arg0);

	void updateMdrType(Xn011Dclymx arg0);

	void updateMdrInfo(Xn011Dclymx arg0);

	void updateInfectTypeId(Xn011Dclymx arg0);

	Date getMonitorPatientMdrLastAt();

	void updateIT(Xn011Dclymx arg0);

	Xn011Dclymx getByPrimaryKeys(Xn011Dclymx arg0);

	List<ViewMdr> getIsGl(@Param("patientId") String arg0, @Param("zyId") String arg1);

	List<ViewMdr> getQueryMDRo(ViewMdr arg0);

	int getQueryMDRoCount(ViewMdr arg0);

	void clearInfectTypeByCardId(@Param("cardId") String arg0);

	long getMaxOrderno(@Param("tablename") String arg0);

	void updateMdrCheck(Xn011Dclymx arg0);
}