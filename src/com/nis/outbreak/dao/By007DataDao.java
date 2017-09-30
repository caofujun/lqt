package com.nis.outbreak.dao;

import com.nis.outbreak.entity.By007Data;
import java.util.List;

public interface By007DataDao {
	void save(By007Data arg0);

	void delete();

	void update(By007Data arg0);

	By007Data get();

	List<By007Data> findBy007Data(By007Data arg0);

	int findBy007DataCount(By007Data arg0);

	List<By007Data> getAll();
}