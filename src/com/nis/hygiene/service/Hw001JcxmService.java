package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw001Jcxm;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw001JcxmService {
	void save(Hw001Jcxm arg0);

	void delete(String arg0);

	void delByClassIdPclassId(String arg0);

	void update(Hw001Jcxm arg0);

	void updateSpecified(Hw001Jcxm arg0, List<String> arg1);

	Hw001Jcxm get(String arg0);

	MyPage<Hw001Jcxm> a(Hw001Jcxm arg0);

	List<Hw001Jcxm> getAll();

	List<Hw001Jcxm> queryTree(Hw001Jcxm arg0);

	List<Hw001Jcxm> findList(Hw001Jcxm arg0);

	void b(Hw001Jcxm arg0);

	void c(Hw001Jcxm arg0);

	List<Hw001Jcxm> queryList(Hw001Jcxm arg0);

	List<Hw001Jcxm> findSubclass(String arg0);
}