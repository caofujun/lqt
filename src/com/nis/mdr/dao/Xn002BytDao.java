package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn002Byt;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xn002BytDao {
	void save(Xn002Byt arg0);

	void delete(@Param("pathogenId") String arg0);

	void update(Xn002Byt arg0);

	Xn002Byt get(@Param("pathogenId") String arg0);

	List<Xn002Byt> findXn002Byt(Xn002Byt arg0);

	int findXn002BytCount(Xn002Byt arg0);

	List<Xn002Byt> getAll();

	int findXn002BytCountTemp(Xn002Byt arg0);

	List<Xn002Byt> findXn002BytTemp(Xn002Byt arg0);

	List<Xn002Byt> findXn002BytIndex(Xn002Byt arg0);

	int findXn002BytIndexCount(Xn002Byt arg0);

	List<Xn002Byt> getTreeLeafOne(Xn002Byt arg0);

	List<Xn002Byt> getTreeLeafTwo(Xn002Byt arg0);

	List<HashMap<String, Object>> getGlsfl();

	Xn002Byt findXn002BytEdit(Xn002Byt arg0);
}