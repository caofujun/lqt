package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn018Tsnymx;
import java.util.List;

public interface Xn018TsnymxDao {
	void save(Xn018Tsnymx arg0);

	void update(Xn018Tsnymx arg0);

	List<Xn018Tsnymx> findXn018Tsnymx(Xn018Tsnymx arg0);

	int findXn018TsnymxCount(Xn018Tsnymx arg0);

	List<Xn018Tsnymx> getAll();
}