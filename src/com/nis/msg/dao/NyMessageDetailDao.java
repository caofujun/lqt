package com.nis.msg.dao;

import com.nis.msg.entity.NyMessageDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyMessageDetailDao {
	void save(NyMessageDetail arg0);

	void delete(@Param("mid") String arg0);

	void update(NyMessageDetail arg0);

	NyMessageDetail get(@Param("mid") String arg0);

	List<NyMessageDetail> findNyMessageDetail(NyMessageDetail arg0);

	int findNyMessageDetailCount(NyMessageDetail arg0);

	List<NyMessageDetail> getAll();

	List<NyMessageDetail> getbyThemeId(@Param("themeId") String arg0);

	List<NyMessageDetail> getByUserId(@Param("userId") String arg0);

	int getPageByUserIdCount(@Param("userId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2);

	List<NyMessageDetail> getPageByUserId(@Param("userId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2, @Param("orderType") Integer arg3, @Param("orderBy") String arg4,
			@Param("orclBegNum") Integer arg5, @Param("orclEndNum") Integer arg6);

	List<NyMessageDetail> findByCaseId(@Param("caseId") String arg0);

	List<NyMessageDetail> findListByCaseId(String arg0);

	int getMsgsCount(NyMessageDetail arg0);

	int getUnreadMsgsCountForInterFace(NyMessageDetail arg0);

	List<NyMessageDetail> getUnreadMsgsForInterFace(NyMessageDetail arg0);

	List<NyMessageDetail> getMsgs(NyMessageDetail arg0);
}