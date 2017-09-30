package com.nis.outbreak.dao;

import com.nis.outbreak.entity.By007Config;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface By007ConfigDao {
	void save(By007Config arg0);

	void delete(@Param("typeId") String arg0);

	void update(By007Config arg0);

	By007Config get(@Param("typeId") String arg0);

	List<By007Config> findBy007Config(By007Config arg0);

	int findBy007ConfigCount(By007Config arg0);

	List<By007Config> getAll();

	By007Config getByShowId(@Param("id") String arg0);

	void batchUpdAbsolute(List<By007Config> arg0);

	void batchUpdRelative(List<By007Config> arg0);

	void saveGraded(@Param("typeIdList") List<String> arg0);

	void saveUnGraded();
}