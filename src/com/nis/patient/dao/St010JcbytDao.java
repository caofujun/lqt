package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.St010Jcbyt;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St010JcbytDao {
	void save(St010Jcbyt arg0);

	void delete(@Param("id") String arg0);

	void update(St010Jcbyt arg0);

	void batchUpdAnalFlag(@Param("st010List") List<St010Jcbyt> arg0);

	void updAnalFlag(St010Jcbyt arg0);

	void updAnalDt(St010Jcbyt arg0);

	St010Jcbyt get(@Param("id") String arg0);

	List<St010Jcbyt> findSt010Jcbyt(St010Jcbyt arg0);

	int findSt010JcbytCount(St010Jcbyt arg0);

	List<St010Jcbyt> getAll();

	List<St010Jcbyt> findSt010JcbytList(St010Jcbyt arg0);

	List<St010Jcbyt> queryForYJ(@Param("testOrderNo") String arg0, @Param("sql") String arg1);

	List<St010Jcbyt> queryForYJByZyid(@Param("zyid") String arg0, @Param("sql") String arg1);

	List<St010Jcbyt> queryForYJByMzid(@Param("mzid") String arg0, @Param("sql") String arg1);

	List<DataWarning> findPatentJcbytWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	List<St010Jcbyt> findByZyidTestNo(@Param("zyid") String arg0, @Param("testOrderNo") String arg1);
}