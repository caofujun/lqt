package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw004Cybb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw004CybbDao {
	void save(Hw004Cybb arg0);

	void delete(@Param("sampleId") String arg0);

	void update(Hw004Cybb arg0);

	Hw004Cybb get(@Param("sampleId") String arg0);

	Hw004Cybb isExist(@Param("sampleId") String arg0);

	List<Hw004Cybb> findHw004Cybb(Hw004Cybb arg0);

	int findHw004CybbCount(Hw004Cybb arg0);

	List<Hw004Cybb> getAll();

	List<Hw004Cybb> queryList(Hw004Cybb arg0);

	List<Hw004Cybb> findList(Hw004Cybb arg0);

	String findMaxSampleId();

	void updFlag(Hw004Cybb arg0);

	Hw004Cybb getHw004Cybb(@Param("sampleId") String arg0);
}