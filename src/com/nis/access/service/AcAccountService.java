package com.nis.access.service;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface AcAccountService {
	void save(AcAccount arg0);

	void a(AcAccount arg0, boolean arg1);

	String a(AcAccount arg0);

	void delete(String arg0);

	void update(AcAccount arg0);

	void updateLoginInfo(String arg0, Long arg1);

	AcAccount get(String arg0);

	MyPage<AcAccount> b(AcAccount arg0);

	List<AcMenu> a(AcAccount arg0, String arg1);

	List<AcAccount> findByUnitId(String arg0);

	AcAccount b(String arg0);

	AcAccount a(String arg0, String arg1);

	List<AcAccount> c(String arg0);

	List<AcAccount> findRealname(String arg0);

	AcAccount findAccountByRealnameNameAndEmail(String arg0, String arg1);

	AcAccount findAccountByMobilenum(String arg0);

	AcAccount d(String arg0);

	void updatePwdByUserId(String arg0, String arg1);

	Result<String> a(AcAccount arg0, AcAccount arg1, String arg2);

	List<AcAccount> c(AcAccount arg0);

	String getName(String arg0);

	List<AcAccount> findAccoutByCondition(AcAccount arg0);

	void a(String arg0, String arg1, String arg2);

	int checkUniqueExtis(AcAccount arg0);

	List<AcAccount> findByDeptIds(List<String> arg0);
}