package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.St006Twxx;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface St006TwxxDao {
	void save(St006Twxx arg0);

	void delete(@Param("id") String arg0);

	void update(St006Twxx arg0);

	St006Twxx get(@Param("id") String arg0);

	St006Twxx getMaxATByZY(@Param("zyid") String arg0);

	List<St006Twxx> findSt006Twxx(St006Twxx arg0);

	List<St006Twxx> findTwxxList(St006Twxx arg0);

	int findSt006TwxxCount(St006Twxx arg0);

	List<St006Twxx> getAll();

	List<Map<String, Object>> findMainFever(@Param("startDate") Date arg0, @Param("endDate") Date arg1,
			@Param("tw") Double arg2);

	List<St006Twxx> findAbnormalByZyid(@Param("zyid") String arg0);

	Date getMonitorPatientTwxxLastAt();

	List<DataWarning> findPatentTwxxWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	int getNumBeforeFever(@Param("zyid") String arg0, @Param("recordingAt") Date arg1, @Param("twValues") Double arg2);

	void updateAnalFlag(@Param("id") String arg0, @Param("twAnalFlag") Integer arg1, @Param("twAnalDt") Date arg2);
}