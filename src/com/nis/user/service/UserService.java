package com.nis.user.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.Result;
import com.nis.organization.entity.Unit;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
	Result<AcAccount> a(String arg0, String arg1, boolean arg2);

	Result<AcAccount> a(String arg0, String arg1, boolean arg2, String arg3);

	Result<AcAccount> b(String arg0, String arg1, String arg2, boolean arg3);

	Result<AcAccount> P(String arg0, String arg1);

	Result<AcAccount> a(String arg0, String arg1, boolean arg2, String arg3, String arg4);

	Result<AcAccount> cG(String arg0);

	Result<AcAccount> e(AcAccount arg0);

	boolean u(String arg0, String arg1, String arg2);

	Result<AcAccount> cH(String arg0);

	Result<AcAccount> cI(String arg0);

	Result<Unit> cJ(String arg0);

	void Q(String arg0, String arg1);

	boolean cK(String arg0);

	List<AcAccount> get(String arg0);

	Result<AcAccount> c(String arg0, String arg1, String arg2, boolean arg3);

	void f(AcAccount arg0);
}