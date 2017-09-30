package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbdisease;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk001CrbdiseaseDao {
	void save(CtgBk001Crbdisease arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk001Crbdisease arg0);

	List<CtgBk001Crbdisease> get(@Param("masterid") String arg0);

	List<CtgBk001Crbdisease> findCtgBk001Crbdisease(CtgBk001Crbdisease arg0);

	int findCtgBk001CrbdiseaseCount(CtgBk001Crbdisease arg0);

	List<CtgBk001Crbdisease> getAll();

	void audit(CtgBk001Crbdisease arg0);

	void retreat(CtgBk001Crbdisease arg0);

	void cancel(CtgBk001Crbdisease arg0);

	void remove(CtgBk001Crbdisease arg0);

	void updateDiseaseNotes(@Param("subid") String arg0, @Param("diseasenotes") String arg1);

	void batchAudit(CtgBk001Crbdisease arg0);

	void updateZBFlag(@Param("subids") String arg0);
}