package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys007Dictcontagion;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface CtgSys007DictcontagionDao {
	void save(CtgSys007Dictcontagion arg0);

	void delete(@Param("diseaseid") String arg0);

	void update(CtgSys007Dictcontagion arg0);

	CtgSys007Dictcontagion get(@Param("diseaseid") String arg0);

	List<CtgSys007Dictcontagion> findCtgSys007Dictcontagion(CtgSys007Dictcontagion arg0);

	int findCtgSys007DictcontagionCount(CtgSys007Dictcontagion arg0);

	List<CtgSys007Dictcontagion> getAll();

	String getNeedFKDiseasis();

	List<CtgSys007Dictcontagion> search(@Param("keyword") String arg0);

	List<CtgSys007Dictcontagion> getByParentId(CtgSys007Dictcontagion arg0);

	String getNeedFKDiseasisByParentId(@Param("parentId") String arg0);

	String getMDDiseaseId();

	List<CtgSys007Dictcontagion> getByIds(@Param("ids") String arg0);

	Integer getAvailableDiseaseId();

	List<CtgSys007Dictcontagion> getDiseaseParent();

	void updateStatus(CtgSys007Dictcontagion arg0);

	List<CtgSys007Dictcontagion> getClassify();

	List<CtgSys007Dictcontagion> getDiseaseParentLevel(CtgSys007Dictcontagion arg0);

	Map<String, Object> getSelfAndSonsByParentId(CtgSys007Dictcontagion arg0);

	void delByParentId(@Param("parentId") String arg0);
}