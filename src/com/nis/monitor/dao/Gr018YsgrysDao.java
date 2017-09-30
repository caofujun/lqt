package com.nis.monitor.dao;

import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.patient.entity.St004Yzxxb;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr018YsgrysDao {
	void save(Gr018Ysgrys arg0);

	void batchInsert(@Param("gr018YsgrysList") List<Gr018Ysgrys> arg0, @Param("tablename") String arg1);

	void saveList(@Param("gr018YsgrysList") List<Gr018Ysgrys> arg0, @Param("tablename") String arg1);

	void delete(@Param("id") String arg0);

	void update(Gr018Ysgrys arg0);

	void updateByZyid(@Param("zyid") String arg0, @Param("tablename") String arg1);

	void updateList(@Param("gr018YsgrysList") List<Gr018Ysgrys> arg0, @Param("tablename") String arg1);

	Gr018Ysgrys get(@Param("id") String arg0, @Param("tablename") String arg1);

	List<Gr018Ysgrys> findGr018Ysgrys(Gr018Ysgrys arg0);

	int findGr018YsgrysCount(Gr018Ysgrys arg0);

	List<Gr018Ysgrys> getAll();

	List<Gr018Ysgrys> findListByZyid(@Param("zyid") String arg0, @Param("tablename") String arg1,
			@Param("isPacs") String arg2);

	List<Gr018Ysgrys> findListByDataDate(@Param("zyid") String arg0, @Param("DataDate") String arg1,
			@Param("tablename") String arg2);

	List<Gr018Ysgrys> findByFX(Gr018Ysgrys arg0);

	void deleteGrysByZyid(@Param("zyid") String arg0, @Param("tablename") String arg1);

	List<Gr018Ysgrys> findByState();

	List<Gr018Ysgrys> findByZyid(@Param("zyid") String arg0, @Param("dataDate") String arg1);

	Date getMonitorPatientBcLastAt();

	void updateStateByZyid(@Param("zyid") String arg0, @Param("state") Integer arg1, @Param("tablename") String arg2);

	int findByStateCount(@Param("state") Integer arg0);

	int findcountByKey(@Param("zyid") String arg0, @Param("dataDate") Date arg1, @Param("elementId") String arg2,
			@Param("dataForm") String arg3, @Param("tablename") String arg4);

	St004Yzxxb getLastUseKjyw(@Param("zyid") String arg0, @Param("orderAt") Date arg1, @Param("tablename") String arg2);

	List<String> findZyidByState(@Param("tablename") String arg0, @Param("isPacs") String arg1);

	int findZyidByStateCount(@Param("tablename") String arg0, @Param("isPacs") String arg1);

	int getUnionDrugNum(@Param("zyid") String arg0, @Param("dataDate") Date arg1, @Param("tablename") String arg2);

	List<Gr018Ysgrys> findTwList(@Param("tw") Double arg0);

	int findTwCount(@Param("tw") Double arg0);
}