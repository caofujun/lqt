package com.nis.intervene.dao;

import com.nis.intervene.entity.JkDicAll;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkDicAllDao {
	void save(JkDicAll arg0);

	void delete(@Param("orderCode") String arg0);

	void update(JkDicAll arg0);

	JkDicAll get(@Param("orderCode") String arg0);

	List<JkDicAll> findJkDicAll(JkDicAll arg0);

	int findJkDicAllCount(JkDicAll arg0);

	List<JkDicAll> getAll();

	List<JkDicAll> getByClassCode(@Param("classCode") String arg0);

	List<JkDicAll> findDictType();

	List<JkDicAll> findDict(JkDicAll arg0);
}