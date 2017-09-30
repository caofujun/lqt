package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw017RoleRight;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw017RoleRightService {
	void save(Hw017RoleRight arg0);

	void delete(String arg0, String arg1);

	void update(Hw017RoleRight arg0);

	Hw017RoleRight get(String arg0, String arg1);

	MyPage<Hw017RoleRight> a(Hw017RoleRight arg0);

	List<Hw017RoleRight> getAll();

	List<Hw017RoleRight> findList(Hw017RoleRight arg0);

	void g(List<Hw017RoleRight> arg0, String arg1);
}