package com.nis.jk.dao;

import com.nis.jk.entity.JkNisDicAll;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkNisDicAllDao {
	void save(JkNisDicAll arg0);

	void delete(@Param("id") String arg0);

	void update(JkNisDicAll arg0);

	JkNisDicAll get(@Param("id") String arg0);

	List<JkNisDicAll> findJkNisDicAll(JkNisDicAll arg0);

	int findJkNisDicAllCount(JkNisDicAll arg0);

	List<JkNisDicAll> getAll();
}