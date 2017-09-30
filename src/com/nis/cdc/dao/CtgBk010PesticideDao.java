package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk010Pesticide;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk010PesticideDao {
	void save(CtgBk010Pesticide arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk010Pesticide arg0);

	CtgBk010Pesticide get(@Param("masterid") String arg0);

	List<CtgBk010Pesticide> findCtgBk010Pesticide(CtgBk010Pesticide arg0);

	int findCtgBk010PesticideCount(CtgBk010Pesticide arg0);

	List<CtgBk010Pesticide> getAll();

	List<CtgBk010Pesticide> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk010Pesticide arg0);

	void retreat(CtgBk010Pesticide arg0);

	void cancel(CtgBk010Pesticide arg0);

	void remove(CtgBk010Pesticide arg0);

	void batchAudit(CtgBk010Pesticide arg0);

	void updatePrintFlag(@Param("masterid") String arg0);

	void updateNotes(@Param("masterid") String arg0, @Param("notes") String arg1);
}