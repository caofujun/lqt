package com.nis.homepage.service;

import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.homepage.entity.SysHpStyle;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface SysHpStyleService {
	void save(SysHpStyle arg0);

	void delete(String arg0);

	void update(SysHpStyle arg0);

	SysHpStyle get(String arg0);

	MyPage<SysHpStyle> a(SysHpStyle arg0);

	List<SysHpStyle> getAll();

	SysHpStyle a(Long arg0, String arg1, String arg2, String arg3);

	SysHpStyle a(int arg0, String arg1, String arg2, String arg3);

	String a(String arg0, String arg1, LoginUser arg2, String arg3, List<Map<String, Object>> arg4);
}