package com.nis.msg.dao;

import com.nis.msg.entity.NyMessageTheme;
import com.nis.msg.entity.NyMessageUserDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyMessageUserDetailDao {
	void save(NyMessageUserDetail arg0);

	void delete(@Param("mudId") String arg0);

	void update(NyMessageUserDetail arg0);

	NyMessageUserDetail get(@Param("mudId") String arg0);

	List<NyMessageUserDetail> findNyMessageUserDetail(NyMessageUserDetail arg0);

	int findNyMessageUserDetailCount(NyMessageUserDetail arg0);

	List<NyMessageUserDetail> getAll();

	void saveList(@Param("messageUserDetailList") List<NyMessageUserDetail> arg0);

	List<NyMessageTheme> getByUserId(@Param("userId") String arg0);

	void updateByThemeIdAndUserId(@Param("themeId") String arg0, @Param("userId") String arg1);

	void updateByCaseIdAndUserId(@Param("caseId") String arg0, @Param("userId") String arg1);

	Integer unreadMsgCount(@Param("userId") String arg0);

	void markAsRead(@Param("mid") String arg0, @Param("themeId") String arg1, @Param("userId") String arg2);
}