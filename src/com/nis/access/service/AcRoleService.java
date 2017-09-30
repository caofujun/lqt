package com.nis.access.service;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcRole;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface AcRoleService {
	void save(AcRole arg0);

	Result<String> i(String arg0);

	void update(AcRole arg0);

	AcRole get(String arg0);

	MyPage<AcRole> a(AcRole arg0);

	List<AcRole> getAll();

	List<AcMenu> a(AcAccount arg0, String arg1);

	List<AcRole> getRoleByUnitId(String arg0, String arg1);

	String getName(String arg0);
}