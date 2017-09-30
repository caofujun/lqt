package com.nis.msg.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.msg.dao.NyMessageThemeDao;
import com.nis.msg.entity.NyMessageTheme;
import com.nis.msg.service.NyMessageThemeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NyMessageThemeServiceImpl implements NyMessageThemeService {
	@Autowired
	private NyMessageThemeDao va;

	public void save(NyMessageTheme nyMessageTheme) {
		nyMessageTheme.setThemeId(z.a(bg.nk));
		this.va.save(nyMessageTheme);
	}

	public void delete(String themeId) {
		this.va.delete(themeId);
	}

	public void update(NyMessageTheme nyMessageTheme) {
		this.va.update(nyMessageTheme);
	}

	public NyMessageTheme get(String themeId) {
		return this.va.get(themeId);
	}

	@SqlLog(p = "干预消息--消息列表")
	public MyPage<NyMessageTheme> a(NyMessageTheme nyMessageTheme) {
		int total = this.va.findNyMessageThemeCount(nyMessageTheme);
		List data = null;
		if (total > 0) {
			data = this.va.findNyMessageTheme(nyMessageTheme);
		}

		return new MyPage(nyMessageTheme.getPage().intValue(), nyMessageTheme.getSize().intValue(), total, data);
	}

	public List<NyMessageTheme> getAll() {
		return this.va.getAll();
	}

	public NyMessageTheme getByZyid(String zyid) {
		return this.va.getByZyid(zyid);
	}

	public NyMessageTheme getByCreateUser(String createUser) {
		return this.va.getByCreateUser(createUser);
	}

	public NyMessageTheme getByMzid(String mzid) {
		return this.va.getByMzid(mzid);
	}
}