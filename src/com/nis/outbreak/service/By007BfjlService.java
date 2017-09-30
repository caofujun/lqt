package com.nis.outbreak.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.outbreak.entity.By007Bfjl;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface By007BfjlService {
	void save(By007Bfjl arg0);

	void delete(String arg0);

	void update(By007Bfjl arg0);

	void updateSpecified(By007Bfjl arg0, List<String> arg1);

	By007Bfjl get(String arg0);

	MyPage<By007Bfjl> a(By007Bfjl arg0);

	List<By007Bfjl> getAll();

	MyPage<By007Bfjl> b(By007Bfjl arg0);

	void a(By007Bfjl arg0, AcAccount arg1);

	HSSFWorkbook c(By007Bfjl arg0);

	List<By007Bfjl> findListByDept(By007Bfjl arg0);

	void updAuditFlag(By007Bfjl arg0);

	void updMemo(By007Bfjl arg0);
}