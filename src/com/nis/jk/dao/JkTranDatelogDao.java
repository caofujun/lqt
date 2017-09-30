package com.nis.jk.dao;

import com.nis.jk.entity.JkTranDatelog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkTranDatelogDao {
	void save(JkTranDatelog arg0);

	void delete(@Param("syncCode") String arg0);

	void update(JkTranDatelog arg0);

	JkTranDatelog get(@Param("syncCode") String arg0);

	List<JkTranDatelog> findJkTranDatelog(JkTranDatelog arg0);

	int findJkTranDatelogCount(JkTranDatelog arg0);

	List<JkTranDatelog> getAll();
}