package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.Zg032Sjxmpp;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg032SjxmppService {
	void save(Zg032Sjxmpp arg0);

	void delete(String arg0);

	void update(Zg032Sjxmpp arg0);

	Zg032Sjxmpp get(String arg0);

	MyPage<Zg032Sjxmpp> a(Zg032Sjxmpp arg0);

	List<Zg032Sjxmpp> getAll();

	List<Zg032Sjxmpp> findJoinGrys();
}