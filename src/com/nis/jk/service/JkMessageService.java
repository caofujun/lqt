package com.nis.jk.service;

import com.nis.comm.entity.MyPage;
import com.nis.jk.entity.JkMessage;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface JkMessageService {
	void save(JkMessage arg0);

	void b(String arg0, Date arg1);

	void delete(String arg0);

	void update(JkMessage arg0);

	JkMessage get(String arg0);

	MyPage<JkMessage> a(JkMessage arg0);

	List<JkMessage> getAll();
}