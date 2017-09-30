package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys007Dictcontagion;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys007DictcontagionService {
	void save(CtgSys007Dictcontagion arg0);

	void delete(String arg0);

	void update(CtgSys007Dictcontagion arg0);

	CtgSys007Dictcontagion get(String arg0);

	List<CtgSys007Dictcontagion> search(String arg0);

	List<CtgSys007Dictcontagion> a(CtgSys007Dictcontagion arg0);

	List<CtgSys007Dictcontagion> getAll();

	String getNeedFKDiseasis();

	List<CtgSys007Dictcontagion> getByParentId(CtgSys007Dictcontagion arg0);

	String getNeedFKDiseasisByParentId(String arg0);

	String getMDDiseaseId();

	List<CtgSys007Dictcontagion> getByIds(String arg0);

	Integer getAvailableDiseaseId();

	List<CtgSys007Dictcontagion> getDiseaseParent();

	void updateStatus(CtgSys007Dictcontagion arg0);

	List<CtgSys007Dictcontagion> getClassify();

	List<CtgSys007Dictcontagion> getDiseaseParentLevel(CtgSys007Dictcontagion arg0);

	List<CtgSys007Dictcontagion> b(CtgSys007Dictcontagion arg0);

	Map<String, Object> getSelfAndSonsByParentId(CtgSys007Dictcontagion arg0);

	void delByParentId(String arg0);
}