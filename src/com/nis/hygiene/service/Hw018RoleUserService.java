package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw018RoleUser;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw018RoleUserService {
	void save(Hw018RoleUser arg0);

	void delete(String arg0, String arg1);

	void update(Hw018RoleUser arg0);

	Hw018RoleUser get(String arg0, String arg1);

	MyPage<Hw018RoleUser> a(Hw018RoleUser arg0);

	List<Hw018RoleUser> getAll();
}