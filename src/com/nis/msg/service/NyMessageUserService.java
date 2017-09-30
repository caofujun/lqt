package com.nis.msg.service;

import com.nis.comm.entity.MyPage;
import com.nis.msg.entity.NyMessageUser;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface NyMessageUserService {
	NyMessageUser a(NyMessageUser arg0);

	void delete(String arg0);

	void update(NyMessageUser arg0);

	NyMessageUser get(String arg0);

	MyPage<NyMessageUser> b(NyMessageUser arg0);

	List<NyMessageUser> getAll();

	List<NyMessageUser> getByThemeId(String arg0);

	List<NyMessageUser> getbyList(String[] arg0);

	NyMessageUser getByUserIdAndMid(String arg0, String arg1);
}