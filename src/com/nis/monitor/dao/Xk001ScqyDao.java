package com.nis.monitor.dao;

import com.nis.monitor.entity.Xk001Scqy;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xk001ScqyDao {
	void save(Xk001Scqy arg0);

	void delete(@Param("qyNo") String arg0);

	void update(Xk001Scqy arg0);

	Xk001Scqy get(@Param("qyNo") String arg0);

	List<Xk001Scqy> findXk001Scqy(Xk001Scqy arg0);

	int findXk001ScqyCount(Xk001Scqy arg0);

	List<Xk001Scqy> getAll(@Param("searchString") String arg0);

	void setStatus(@Param("qyNo") String arg0, @Param("useFlag") String arg1);

	List<Xk001Scqy> queryQyList(@Param("qyType") String arg0);
}