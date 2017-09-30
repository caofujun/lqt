package com.nis.msg.dao;

import com.nis.msg.entity.NyMessageTheme;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyMessageThemeDao {
	void save(NyMessageTheme arg0);

	void delete(@Param("themeId") String arg0);

	void update(NyMessageTheme arg0);

	NyMessageTheme get(@Param("themeId") String arg0);

	List<NyMessageTheme> findNyMessageTheme(NyMessageTheme arg0);

	int findNyMessageThemeCount(NyMessageTheme arg0);

	List<NyMessageTheme> getAll();

	NyMessageTheme getByZyid(@Param("zyid") String arg0);

	NyMessageTheme getByCreateUser(@Param("createUser") String arg0);

	NyMessageTheme getByMzid(@Param("mzid") String arg0);
}