package com.nis.organization.service;

import com.nis.comm.entity.MyPage;
import com.nis.organization.entity.Pubcat;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface PubcatService {
	void save(Pubcat arg0);

	void delete(Long arg0);

	void update(Pubcat arg0);

	Pubcat get(Long arg0);

	MyPage<Pubcat> a(Pubcat arg0);

	List<Pubcat> getAll();
}