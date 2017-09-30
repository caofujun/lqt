package com.nis.homepage.service;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.homepage.entity.SysHpLayout;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysHpLayoutService {
	Result<String> a(SysHpLayout arg0);

	void delete(String arg0);

	SysHpLayout get(String arg0);

	MyPage<SysHpLayout> b(SysHpLayout arg0);

	List<SysHpLayout> getAll();

	List<SysHpLayout> find(SysHpLayout arg0);

	void updateStatus(String arg0, String arg1);

	SysHpLayout getLayoutCode(String arg0);
}