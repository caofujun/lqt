package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.dict.dao.Zg032SjxmppDao;
import com.nis.dict.entity.Zg032Sjxmpp;
import com.nis.dict.service.Zg032SjxmppService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg032SjxmppServiceImpl implements Zg032SjxmppService {
	@Autowired
	private Zg032SjxmppDao qO;

	public void save(Zg032Sjxmpp zg032Sjxmpp) {
		zg032Sjxmpp.setId(z.a(bg.nT));
		this.qO.save(zg032Sjxmpp);
	}

	public void delete(String id) {
		this.qO.delete(id);
	}

	public void update(Zg032Sjxmpp zg032Sjxmpp) {
		this.qO.update(zg032Sjxmpp);
	}

	public Zg032Sjxmpp get(String id) {
		return this.qO.get(id);
	}

	public MyPage<Zg032Sjxmpp> a(Zg032Sjxmpp zg032Sjxmpp) {
		int total = this.qO.findZg032SjxmppCount(zg032Sjxmpp);
		List data = null;
		if (total > 0) {
			data = this.qO.findZg032Sjxmpp(zg032Sjxmpp);
		}

		return new MyPage(zg032Sjxmpp.getPage().intValue(), zg032Sjxmpp.getSize().intValue(), total, data);
	}

	public List<Zg032Sjxmpp> getAll() {
		return this.qO.getAll();
	}

	public List<Zg032Sjxmpp> findJoinGrys() {
		return this.qO.findJoinGrys();
	}
}