package com.nis.organization.service;

import com.nis.comm.entity.MyPage;
import com.nis.organization.entity.DepDocLink;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface DepDocLinkService {
	void save(DepDocLink arg0);

	void delete(Long arg0);

	void a(DepDocLink arg0);

	DepDocLink a(Long arg0);

	MyPage<DepDocLink> b(DepDocLink arg0);

	List<DepDocLink> getAll();

	void a(DepDocLink arg0, String[] arg1);
}