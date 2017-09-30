package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.St008Bcjl;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St008BcjlDao {
	void save(St008Bcjl arg0);

	void delete(@Param("id") String arg0);

	void update(St008Bcjl arg0);

	void updateAnalFlag(@Param("zyid") String arg0, @Param("analFlag") String arg1, @Param("tablename") String arg2);

	void updateAnalResultTest(St008Bcjl arg0);

	St008Bcjl get(@Param("id") String arg0);

	List<St008Bcjl> findSt008Bcjl(St008Bcjl arg0);

	int findSt008BcjlCount(St008Bcjl arg0);

	List<St008Bcjl> getAll();

	List<St008Bcjl> getBcListTest(@Param("curr") Date arg0, @Param("rownum") int arg1, @Param("tablename") String arg2);

	List<St008Bcjl> getBcListTestV6(@Param("curr") Date arg0, @Param("rownum") int arg1,
			@Param("tablename") String arg2);

	List<St008Bcjl> getBcListByZyid(@Param("zyids") List<String> arg0, @Param("curr") Date arg1,
			@Param("tablename") String arg2);

	List<St008Bcjl> getBcListByZyidV6(@Param("zyids") List<String> arg0, @Param("curr") Date arg1,
			@Param("tablename") String arg2);

	List<St008Bcjl> findDisAnalysisList(St008Bcjl arg0);

	Date getMonitorPatientBcjlLastAt();

	Date getMonitorPatientBcjlLastAt6();

	List<DataWarning> findPatentBcjlWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	List<DataWarning> findPatentBcjlWarning6(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	List<St008Bcjl> findByZyid(@Param("zyid") String arg0);

	List<String> getBcZyidListv6(@Param("curr") Date arg0, @Param("rownum") int arg1, @Param("tablename") String arg2);

	List<String> getBcZyidList(@Param("curr") Date arg0, @Param("rownum") int arg1, @Param("tablename") String arg2);

	int getBcZyidCountv6(@Param("curr") Date arg0, @Param("tablename") String arg1);

	int getBcZyidCount(@Param("curr") Date arg0, @Param("tablename") String arg1);

	void deleteAnalData(@Param("dealSql") String arg0);

	void updateCdcAnalFlag(St008Bcjl arg0);

	List<St008Bcjl> getBcListForCDC(int arg0);
}