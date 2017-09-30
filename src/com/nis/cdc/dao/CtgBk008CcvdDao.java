package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk008Ccvd;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk008CcvdDao {
	void save(CtgBk008Ccvd arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk008Ccvd arg0);

	void updatePrintFlag(@Param("masterid") String arg0);

	CtgBk008Ccvd get(@Param("masterid") String arg0);

	List<CtgBk008Ccvd> findCtgBk008Ccvd(CtgBk008Ccvd arg0);

	int findCtgBk008CcvdCount(CtgBk008Ccvd arg0);

	List<CtgBk008Ccvd> getAll();

	List<CtgBk008Ccvd> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk008Ccvd arg0);

	void retreat(CtgBk008Ccvd arg0);

	void cancel(CtgBk008Ccvd arg0);

	void remove(CtgBk008Ccvd arg0);

	void updateNotes(@Param("masterid") String arg0, @Param("notes") String arg1);

	void batchAudit(CtgBk008Ccvd arg0);
}