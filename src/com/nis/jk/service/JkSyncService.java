package com.nis.jk.service;

import com.nis.comm.entity.MyPage;
import com.nis.jk.entity.JkSyncLog;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface JkSyncService {
	String bx(String arg0);

	List<JkSyncLog> by(String arg0);

	List<JkSyncLog> bz(String arg0);

	void bA(String arg0);

	MyPage<JkSyncLog> a(JkSyncLog arg0);
}