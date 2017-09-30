package com.nis.intervene.dao;

import com.nis.intervene.entity.FxPatientZb;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FxPatientZbDao {
	void save(FxPatientZb arg0);

	void delete(@Param("pzId") String arg0);

	void update(FxPatientZb arg0);

	FxPatientZb get(@Param("pzId") String arg0);

	List<FxPatientZb> findFxPatientZb(FxPatientZb arg0);

	int findFxPatientZbCount(FxPatientZb arg0);

	List<FxPatientZb> getAll();

	FxPatientZb findByZyidAndZbId(@Param("zyid") String arg0, @Param("zbId") String arg1,
			@Param("startDate") Date arg2);

	List<FxPatientZb> findByzyid(@Param("zyid") String arg0);

	List<FxPatientZb> findGrTgByzyid(@Param("zyid") String arg0);

	List<FxPatientZb> findGrYjByzyid(@Param("zyid") String arg0);

	List<FxPatientZb> findQsByzyid(@Param("zyid") String arg0);

	void saveList(@Param("fxPatientList") List<FxPatientZb> arg0);

	List<FxPatientZb> findByZyidAndZbIdAndDate(@Param("zyid") String arg0, @Param("zbId") String arg1,
			@Param("startDate") String arg2);
}