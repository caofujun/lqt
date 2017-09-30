package com.nis.access.service;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcRole;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.entity.TreeEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface AcMenuService {
	Result<String> a(AcMenu arg0);

	void delete(String arg0);

	void update(AcMenu arg0);

	AcMenu get(String arg0);

	MyPage<AcMenu> b(AcMenu arg0);

	List<AcMenu> getAll();

	List<AcMenu> a();

	List<AcMenu> c(AcMenu arg0);

	List<AcMenu> findByOwnership(AcMenu arg0);

	List<AcMenu> d(AcMenu arg0);

	List<AcMenu> getAllPatentMenu(String arg0);

	AcMenu e(String arg0);

	void a(AcMenu arg0, AcRole arg1);

	List<AcMenu> getMenuByParentNo(String arg0, String arg1);

	void deleteByMenuNo(String arg0);

	List<AcMenu> getAllMenu(AcMenu arg0);

	List<AcMenu> getMenuNos(String[] arg0, String arg1);

	List<AcMenu> findMenuNos(String[] arg0);

	List<AcMenu> f(String arg0);

	List<AcMenu> g(String arg0);

	List<TreeEntity> h(String arg0);

	List<TreeEntity> getReportMenuLeaf(String arg0, String arg1);

	List<AcMenu> a(AcAccount arg0, String arg1, String arg2);
}