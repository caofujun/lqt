package com.nis.access.service;

import com.nis.access.entity.AcPwdFind;
import com.nis.comm.entity.MyPage;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface AcPwdFindService {
	void save(AcPwdFind arg0);

	void delete(String arg0);

	void update(AcPwdFind arg0);

	AcPwdFind get(String arg0);

	AcPwdFind findAcPwdFindByUserNameEmailVDate(String arg0, String arg1, Date arg2);

	void updateState(String arg0, Long arg1);

	MyPage<AcPwdFind> a(AcPwdFind arg0);

	List<AcPwdFind> getAll();
}