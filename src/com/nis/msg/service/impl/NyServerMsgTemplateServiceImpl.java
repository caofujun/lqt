package com.nis.msg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.comm.utils.z;
import com.nis.msg.dao.NyServerMsgTemplateDao;
import com.nis.msg.entity.NyServerMsgTemplate;
import com.nis.msg.service.NyServerMsgTemplateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NyServerMsgTemplateServiceImpl implements NyServerMsgTemplateService {
	@Autowired
	private NyServerMsgTemplateDao vc;

	public void save(NyServerMsgTemplate nyServerMsgTemplate) {
		nyServerMsgTemplate.setId(z.a(bg.nf));
		nyServerMsgTemplate.setSpCode(t.aE(nyServerMsgTemplate.getTitle()));
		if (nyServerMsgTemplate.getCreateAt() == null) {
			nyServerMsgTemplate.setCreateAt(f.getCurDate());
		}

		this.vc.save(nyServerMsgTemplate);
	}

	public void delete(String id) {
		this.vc.delete(id);
	}

	public void update(NyServerMsgTemplate nyServerMsgTemplate) {
		nyServerMsgTemplate.setSpCode(t.aE(nyServerMsgTemplate.getTitle()));
		this.vc.update(nyServerMsgTemplate);
	}

	public NyServerMsgTemplate get(String id) {
		return this.vc.get(id);
	}

	public MyPage<NyServerMsgTemplate> a(NyServerMsgTemplate nyServerMsgTemplate) {
		int total = this.vc.findNyServerMsgTemplateCount(nyServerMsgTemplate);
		List data = null;
		if (total > 0) {
			data = this.vc.findNyServerMsgTemplate(nyServerMsgTemplate);
		}

		return new MyPage(nyServerMsgTemplate.getPage().intValue(), nyServerMsgTemplate.getSize().intValue(), total,
				data);
	}

	public List<NyServerMsgTemplate> getAll() {
		return this.vc.getAll();
	}
}