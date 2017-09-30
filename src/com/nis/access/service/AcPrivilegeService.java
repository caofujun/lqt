package com.nis.access.service;

import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcPrivilege;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface AcPrivilegeService {
	void a(String arg0, String[] arg1, String[] arg2);

	void save(List<AcPrivilege> arg0);

	List<AcPrivilege> findByRoleid(String arg0);

	List<AcMenu> findMenuByRoleid(String arg0, String arg1);

	void saveList(List<AcPrivilege> arg0, String arg1);
}