package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk004Syycbk;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk004SyycbkDao {
	void save(CtgBk004Syycbk arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk004Syycbk arg0);

	void updatePrintFlag(@Param("masterid") String arg0);

	CtgBk004Syycbk get(@Param("masterid") String arg0);

	List<CtgBk004Syycbk> findCtgBk004Syycbk(CtgBk004Syycbk arg0);

	int findCtgBk004SyycbkCount(CtgBk004Syycbk arg0);

	List<CtgBk004Syycbk> getAll();

	List<CtgBk004Syycbk> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk004Syycbk arg0);

	void retreat(CtgBk004Syycbk arg0);

	void cancel(CtgBk004Syycbk arg0);

	void remove(CtgBk004Syycbk arg0);

	void batchAudit(CtgBk004Syycbk arg0);
}