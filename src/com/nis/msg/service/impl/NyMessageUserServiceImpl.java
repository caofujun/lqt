package com.nis.msg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.msg.dao.NyMessageUserDao;
import com.nis.msg.entity.NyMessageUser;
import com.nis.msg.service.NyMessageUserService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NyMessageUserServiceImpl implements NyMessageUserService {
	@Autowired
	private NyMessageUserDao vb;

	public NyMessageUser a(NyMessageUser nyMessageUser) {
		NyMessageUser messageUser = this.getByDeptIdAndMid(nyMessageUser.getDeptId(), nyMessageUser.getUserId(),
				nyMessageUser.getThemeId());
		if (messageUser == null) {
			nyMessageUser.setMsgUserId(z.a(bg.nm));
			this.vb.save(nyMessageUser);
			return nyMessageUser;
		} else {
			return messageUser;
		}
	}

	public void delete(String msgUserId) {
		this.vb.delete(msgUserId);
	}

	public void update(NyMessageUser nyMessageUser) {
		this.vb.update(nyMessageUser);
	}

	public NyMessageUser get(String msgUserId) {
		return this.vb.get(msgUserId);
	}

	public MyPage<NyMessageUser> b(NyMessageUser nyMessageUser) {
		int total = this.vb.findNyMessageUserCount(nyMessageUser);
		List data = null;
		if (total > 0) {
			data = this.vb.findNyMessageUser(nyMessageUser);
		}

		return new MyPage(nyMessageUser.getPage().intValue(), nyMessageUser.getSize().intValue(), total, data);
	}

	public List<NyMessageUser> getAll() {
		return this.vb.getAll();
	}

	public List<NyMessageUser> getByThemeId(String themeId) {
		List messageUserList = this.vb.getByThemeId(themeId);
		Iterator arg3 = messageUserList.iterator();

		while (arg3.hasNext()) {
			NyMessageUser messageUser = (NyMessageUser) arg3.next();
			if (messageUser.getDeptName() != null) {
				messageUser.setName(messageUser.getDeptName());
			} else if (messageUser.getUserName() != null) {
				messageUser.setName(messageUser.getUserName());
			} else if (messageUser.getDoctorName() != null) {
				messageUser.setName(messageUser.getDoctorName());
			}
		}

		return messageUserList;
	}

	public List<NyMessageUser> getbyList(String[] msgUserId) {
		return this.vb.getbyList(msgUserId);
	}

	public NyMessageUser getByUserIdAndMid(String userId, String themeId) {
		return this.vb.getByUserIdAndMid(userId, themeId);
	}

	public NyMessageUser getByDeptIdAndMid(String deptId, String userId, String themeId) {
		return this.vb.getByDeptIdAndMid(deptId, userId, themeId);
	}
}