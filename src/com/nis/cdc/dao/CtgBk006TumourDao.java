package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk006Tumour;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk006TumourDao {
	void save(CtgBk006Tumour arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk006Tumour arg0);

	void updatePrintFlag(@Param("masterid") String arg0);

	CtgBk006Tumour get(@Param("masterid") String arg0);

	List<CtgBk006Tumour> findCtgBk006Tumour(CtgBk006Tumour arg0);

	int findCtgBk006TumourCount(CtgBk006Tumour arg0);

	List<CtgBk006Tumour> getAll();

	List<CtgBk006Tumour> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk006Tumour arg0);

	void retreat(CtgBk006Tumour arg0);

	void cancel(CtgBk006Tumour arg0);

	void remove(CtgBk006Tumour arg0);

	void batchAudit(CtgBk006Tumour arg0);
}