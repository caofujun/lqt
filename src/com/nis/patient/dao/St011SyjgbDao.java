package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.St011Syjgb;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St011SyjgbDao {
	void save(St011Syjgb arg0);

	void delete(@Param("id") String arg0);

	void update(St011Syjgb arg0);

	St011Syjgb get(@Param("id") String arg0);

	List<St011Syjgb> findSt011Syjgb(St011Syjgb arg0);

	int findSt011SyjgbCount(St011Syjgb arg0);

	List<St011Syjgb> getAll();

	List<St011Syjgb> findSt011SyjgbList(St011Syjgb arg0);

	List<St011Syjgb> findByBDB(@Param("zyid") String arg0, @Param("dybw") String arg1, @Param("zbName") String arg2);

	List<St011Syjgb> findByBXB(@Param("zyid") String arg0, @Param("dybw") String arg1,
			@Param("strList") List<String> arg2);

	List<St011Syjgb> findDrugAllergytList(@Param("refid") String arg0);

	List<St011Syjgb> findByOrderNoAndSn(@Param("testOrderNo") String arg0, @Param("pathogenSn") String arg1);

	List<St011Syjgb> queryForYJ(@Param("testOrderNo") String arg0, @Param("sql") String arg1);

	List<St011Syjgb> queryForYJByZyid(@Param("zyid") String arg0, @Param("sql") String arg1);

	List<St011Syjgb> queryForYJByMzid(@Param("mzid") String arg0, @Param("sql") String arg1);

	List<DataWarning> findPatentSyjgbWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	List<St011Syjgb> findByZyidTestNo(@Param("zyid") String arg0, @Param("testOrderNo") String arg1);

	List<St011Syjgb> findWaitAnalysis(@Param("pathogenSn") String arg0, @Param("testOrderNo") String arg1);

	List<St011Syjgb> findSt011Syjgbqst(St011Syjgb arg0);
}