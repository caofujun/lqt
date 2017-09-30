package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl012DicTroche;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl012DicTrocheDao {
	void save(Xl012DicTroche arg0);

	void delete(@Param("trocheid") String arg0);

	void update(Xl012DicTroche arg0);

	Xl012DicTroche get(@Param("trocheid") String arg0);

	List<Xl012DicTroche> findXl012DicTroche(Xl012DicTroche arg0);

	int findXl012DicTrocheCount(Xl012DicTroche arg0);

	List<Xl012DicTroche> getAll();
}