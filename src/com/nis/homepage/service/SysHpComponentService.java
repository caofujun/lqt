package com.nis.homepage.service;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.homepage.entity.SysHpComponent;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysHpComponentService {
	Result<String> a(SysHpComponent arg0);

	void delete(String arg0);

	SysHpComponent get(String arg0);

	MyPage<SysHpComponent> b(SysHpComponent arg0);

	List<SysHpComponent> getAll();

	List<SysHpComponent> find(SysHpComponent arg0);

	void updateStatus(String arg0, String arg1);

	SysHpComponent getComponentCode(String arg0);
}