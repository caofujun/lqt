package com.nis.patient.dao;

import com.nis.patient.entity.St014Pacs;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St014PacsDao {
	void save(St014Pacs arg0);

	void delete(@Param("id") String arg0);

	void update(St014Pacs arg0);

	void updateAnalFlag(@Param("zyid") String arg0, @Param("analFlag") String arg1);

	St014Pacs get(@Param("id") String arg0);

	List<St014Pacs> findSt014Pacs(St014Pacs arg0);

	int findSt014PacsCount(St014Pacs arg0);

	List<St014Pacs> getAll();

	List<St014Pacs> findImageResultsList(St014Pacs arg0);

	void updateAnalResultTest(St014Pacs arg0);

	List<St014Pacs> getPacsTestList(@Param("rownum") int arg0, @Param("tablename") String arg1);

	int getPacsCount(@Param("tablename") String arg0);

	List<St014Pacs> getPacsListForCDC(@Param("rownum") int arg0);

	Date getMonitorPatientPacsLastAt();

	Date getMonitorPatientZkxxsLastAt();

	void updateAnalFlagAndDate(St014Pacs arg0);
}