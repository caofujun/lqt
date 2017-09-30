package com.nis.jk.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.jk.dao.JkMessageDao;
import com.nis.jk.entity.JkMessage;
import com.nis.jk.service.JkMessageService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JkMessageServiceImpl implements JkMessageService {
	@Autowired
	private JkMessageDao sT;

	public void save(JkMessage jkMessage) {
		this.sT.save(jkMessage);
	}

	public void b(String msgContent, Date createTime) {
		JkMessage jkMessage = new JkMessage();
		jkMessage.setId(z.a(bg.mL));
		jkMessage.setMsgType("SYNC");
		jkMessage.setMsgContent(msgContent);
		jkMessage.setFlag("1");
		jkMessage.setCreateTime(createTime);
	}

	public void delete(String id) {
		this.sT.delete(id);
	}

	public void update(JkMessage jkMessage) {
		this.sT.update(jkMessage);
	}

	public JkMessage get(String id) {
		return this.sT.get(id);
	}

	public MyPage<JkMessage> a(JkMessage jkMessage) {
		int total = this.sT.findJkMessageCount(jkMessage);
		List data = null;
		if (total > 0) {
			data = this.sT.findJkMessage(jkMessage);
		}

		return new MyPage(jkMessage.getPage().intValue(), jkMessage.getSize().intValue(), total, data);
	}

	public List<JkMessage> getAll() {
		return this.sT.getAll();
	}
}