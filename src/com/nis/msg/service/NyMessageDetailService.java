package com.nis.msg.service;

import com.nis.comm.entity.MyPage;
import com.nis.msg.entity.NyMessageDetail;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface NyMessageDetailService {
	void save(NyMessageDetail arg0);

	void delete(String arg0);

	void update(NyMessageDetail arg0);

	NyMessageDetail get(String arg0);

	MyPage<NyMessageDetail> a(NyMessageDetail arg0);

	List<NyMessageDetail> getAll();

	void j(String arg0, String arg1, String arg2, String arg3);

	void a(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6);

	List<NyMessageDetail> getbyThemeId(String arg0);

	void a(String arg0, String arg1, String arg2, String arg3, String arg4, String[] arg5, String[] arg6, String arg7,
			String arg8);

	List<NyMessageDetail> getByUserId(String arg0);

	List<NyMessageDetail> findListByCaseId(String arg0);

	int getPageByUserIdCount(String arg0, String arg1, String arg2);

	List<NyMessageDetail> getPageByUserId(String arg0, String arg1, String arg2, Integer arg3, String arg4,
			Integer arg5, Integer arg6);

	int getMsgsCount(NyMessageDetail arg0);

	int getUnreadMsgsCountForInterFace(NyMessageDetail arg0);

	List<NyMessageDetail> getUnreadMsgsForInterFace(NyMessageDetail arg0);

	MyPage<NyMessageDetail> b(NyMessageDetail arg0);

	MyPage<NyMessageDetail> c(NyMessageDetail arg0);
}