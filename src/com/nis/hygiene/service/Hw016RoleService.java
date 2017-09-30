package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw016Role;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw016RoleService {
	void save(Hw016Role arg0);

	void delete(String arg0);

	void update(Hw016Role arg0);

	Hw016Role get(String arg0);

	MyPage<Hw016Role> a(Hw016Role arg0);

	List<Hw016Role> getAll();

	List<Hw016Role> findList(Hw016Role arg0);

	void updHw016Role(Hw016Role arg0);

	void bu(String arg0);

	List<Hw016Role> findListJoinHw018(String arg0);
}