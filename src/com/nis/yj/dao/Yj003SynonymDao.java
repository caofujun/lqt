package com.nis.yj.dao;

import com.nis.yj.entity.Yj003Synonym;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Yj003SynonymDao {
	void save(Yj003Synonym arg0);

	void delete(@Param("id") String arg0);

	void update(Yj003Synonym arg0);

	void match();

	Map<String, String> queryMatched();

	Yj003Synonym get(@Param("id") String arg0);

	List<Yj003Synonym> findYj003Synonym(Yj003Synonym arg0);

	int findYj003SynonymCount(Yj003Synonym arg0);

	List<Yj003Synonym> getAll();

	List<Yj003Synonym> findYj003SynonymList(Yj003Synonym arg0);

	int findYj003SynonymListCount(Yj003Synonym arg0);
}