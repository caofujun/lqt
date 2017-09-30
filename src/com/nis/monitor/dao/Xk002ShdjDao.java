package com.nis.monitor.dao;

import com.nis.monitor.entity.Xk002Shdj;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xk002ShdjDao {
	void save(Xk002Shdj arg0);

	void delete(@Param("shNo") String arg0);

	void update(Xk002Shdj arg0);

	Xk002Shdj get(@Param("shNo") String arg0);

	List<Xk002Shdj> findXk002Shdj(Xk002Shdj arg0);

	int findXk002ShdjCount(Xk002Shdj arg0);

	List<Xk002Shdj> getAll(@Param("searchString") String arg0);

	void setStatus(@Param("shNo") String arg0, @Param("useFlag") String arg1);
}