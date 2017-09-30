package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk002Sybk;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk002SybkDao {
	void save(CtgBk002Sybk arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk002Sybk arg0);

	void updatePrintFlag(@Param("masterid") String arg0);

	CtgBk002Sybk get(@Param("masterid") String arg0);

	List<CtgBk002Sybk> findCtgBk002Sybk(CtgBk002Sybk arg0);

	int findCtgBk002SybkCount(CtgBk002Sybk arg0);

	List<CtgBk002Sybk> getAll();

	List<CtgBk002Sybk> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk002Sybk arg0);

	void retreat(CtgBk002Sybk arg0);

	void cancel(CtgBk002Sybk arg0);

	void remove(CtgBk002Sybk arg0);

	void updateNotes(@Param("masterid") String arg0, @Param("notes") String arg1);

	void batchAudit(CtgBk002Sybk arg0);
}