package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.TreeEntity;
import com.nis.zg.entity.Zg002Byks;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg002ByksService {
	void save(Zg002Byks arg0);

	void delete(String arg0);

	void update(Zg002Byks arg0);

	Zg002Byks get(String arg0);

	Zg002Byks getByDeptId(String arg0);

	MyPage<Zg002Byks> a(Zg002Byks arg0);

	List<Zg002Byks> getAll();

	List<TreeEntity> getRoot(String arg0);

	List<TreeEntity> getLeaf(String arg0, String arg1);
}