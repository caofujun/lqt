package com.nis.msg.dao;

import com.nis.msg.entity.NyMessageUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyMessageUserDao {
	void save(NyMessageUser arg0);

	void delete(@Param("msgUserId") String arg0);

	void update(NyMessageUser arg0);

	NyMessageUser get(@Param("msgUserId") String arg0);

	List<NyMessageUser> findNyMessageUser(NyMessageUser arg0);

	int findNyMessageUserCount(NyMessageUser arg0);

	List<NyMessageUser> getAll();

	List<NyMessageUser> getByThemeId(@Param("themeId") String arg0);

	List<NyMessageUser> getbyList(@Param("msgUserId") String[] arg0);

	NyMessageUser getByUserIdAndMid(@Param("userId") String arg0, @Param("themeId") String arg1);

	NyMessageUser getByDeptIdAndMid(@Param("deptId") String arg0, @Param("userId") String arg1,
			@Param("themeId") String arg2);
}