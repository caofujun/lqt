package com.nis.monitor.dao;

import com.nis.monitor.entity.Bk001Sbk;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Bk001SbkDao {
	void save(Bk001Sbk arg0);

	void delete(@Param("relid") String arg0);

	void update(Bk001Sbk arg0);

	void updateSpecified(@Param("bk001Sbk") Bk001Sbk arg0, @Param("updateAttrs") List<String> arg1);

	Bk001Sbk get(@Param("relid") String arg0);

	List<Bk001Sbk> findBk001Sbk(Bk001Sbk arg0);

	int findBk001SbkCount(Bk001Sbk arg0);

	List<Bk001Sbk> getAll();

	List<Bk001Sbk> findInfectionsCards(Bk001Sbk arg0);

	List<Bk001Sbk> findReportCards(Bk001Sbk arg0);

	Bk001Sbk findCardInfo(@Param("relid") String arg0);

	int findReportNum(@Param("zyid") String arg0);

	List<Bk001Sbk> findByZyid(@Param("zyid") String arg0);

	List<Bk001Sbk> getByTestNoAndPathoId(@Param("testno") String arg0, @Param("pathoid") String arg1);

	List<Bk001Sbk> getByTestNoAndPathoIdnew(@Param("testno") String arg0, @Param("pathoid") String arg1);

	List<Bk001Sbk> isReportBefore(@Param("zyid") String arg0, @Param("idi") String arg1, @Param("ift") String arg2);

	List<Bk001Sbk> findByZyidAndCode(@Param("zyid") String arg0, @Param("idi") String arg1);

	List<Bk001Sbk> findByZyidState(@Param("zyid") String arg0);

	void returnCard(Bk001Sbk arg0);

	List<Bk001Sbk> getReportHistoryDetailsByZyid(@Param("zyid") String arg0);
}