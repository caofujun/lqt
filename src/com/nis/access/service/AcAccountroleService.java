package com.nis.access.service;

import com.nis.access.entity.AcAccountrole;
import com.nis.access.entity.AcRole;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface AcAccountroleService {
	void a(String arg0, List<String> arg1);

	void delete(String arg0);

	void update(AcAccountrole arg0);

	AcAccountrole get(String arg0);

	MyPage<AcAccountrole> a(AcAccountrole arg0);

	List<AcAccountrole> getAll();

	List<AcRole> findByUserid(String arg0);

	int getByRoleId(String arg0);
}