package com.nis.msg.service;

import com.nis.comm.entity.MyPage;
import com.nis.msg.entity.NyMessageDetail;
import com.nis.msg.entity.NyMessageUserDetail;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface NyMessageUserDetailService {
	void save(NyMessageUserDetail arg0);

	void delete(String arg0);

	void update(NyMessageUserDetail arg0);

	NyMessageUserDetail get(String arg0);

	MyPage<NyMessageUserDetail> a(NyMessageUserDetail arg0);

	List<NyMessageUserDetail> getAll();

	List<NyMessageUserDetail> a(String arg0, NyMessageDetail arg1);

	List<NyMessageDetail> getByUserId(String arg0);

	void updateByThemeIdAndUserId(String arg0, String arg1);

	void updateByCaseIdAndUserId(String arg0, String arg1);

	MyPage<NyMessageDetail> a(String arg0, Integer arg1, Integer arg2, String arg3, String arg4, Integer arg5,
			String arg6);

	Integer unreadMsgCount(String arg0);

	void markAsRead(String arg0, String arg1, String arg2);
}