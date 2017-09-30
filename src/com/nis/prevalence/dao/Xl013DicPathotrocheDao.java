package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl013DicPathotroche;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl013DicPathotrocheDao {
	void save(Xl013DicPathotroche arg0);

	void delete(@Param("pathotrocheid") String arg0);

	void update(Xl013DicPathotroche arg0);

	Xl013DicPathotroche get(@Param("pathotrocheid") String arg0);

	List<Xl013DicPathotroche> findXl013DicPathotroche(Xl013DicPathotroche arg0);

	int findXl013DicPathotrocheCount(Xl013DicPathotroche arg0);

	List<Xl013DicPathotroche> getAll();
}