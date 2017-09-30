package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk005Syjc;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface CtgBk005SyjcDao {
	void save(CtgBk005Syjc arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk005Syjc arg0);

	void updatePrintFlag(@Param("masterid") String arg0);

	CtgBk005Syjc get(@Param("masterid") String arg0);

	List<CtgBk005Syjc> findCtgBk005Syjc(CtgBk005Syjc arg0);

	int findCtgBk005SyjcCount(CtgBk005Syjc arg0);

	List<CtgBk005Syjc> getAll();

	List<CtgBk005Syjc> getByMastertid(@Param("masterid") String arg0);

	List<CtgBk005Syjc> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk005Syjc arg0);

	void retreat(CtgBk005Syjc arg0);

	void cancel(CtgBk005Syjc arg0);

	void remove(CtgBk005Syjc arg0);

	void updateNotes(@Param("masterid") String arg0, @Param("notes") String arg1);

	void batchAudit(CtgBk005Syjc arg0);

	Map<String, String> getHospInfo4Excel();

	Map<String, String> getAddrInfo4Excel(@Param("areacode") String arg0);
}