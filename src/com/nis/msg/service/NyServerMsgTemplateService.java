package com.nis.msg.service;

import com.nis.comm.entity.MyPage;
import com.nis.msg.entity.NyServerMsgTemplate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface NyServerMsgTemplateService {
	void save(NyServerMsgTemplate arg0);

	void delete(String arg0);

	void update(NyServerMsgTemplate arg0);

	NyServerMsgTemplate get(String arg0);

	MyPage<NyServerMsgTemplate> a(NyServerMsgTemplate arg0);

	List<NyServerMsgTemplate> getAll();
}