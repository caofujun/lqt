package com.nis.access.service.impl;

import com.nis.access.dao.AcPwdFindDao;
import com.nis.access.entity.AcPwdFind;
import com.nis.access.service.AcPwdFindService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcPwdFindServiceImpl implements AcPwdFindService {
	@Autowired
	private AcPwdFindDao u;

	public void save(AcPwdFind acPwdFind) {
		acPwdFind.setId(z.a(bg.mF));
		this.u.save(acPwdFind);
	}

	public void delete(String id) {
		this.u.delete(id);
	}

	public void update(AcPwdFind acPwdFind) {
		this.u.update(acPwdFind);
	}

	public AcPwdFind get(String id) {
		return this.u.get(id);
	}

	public MyPage<AcPwdFind> a(AcPwdFind acPwdFind) {
		int total = this.u.findAcPwdFindCount(acPwdFind);
		List data = null;
		if (total > 0) {
			data = this.u.findAcPwdFind(acPwdFind);
		}

		return new MyPage(acPwdFind.getPage().intValue(), acPwdFind.getSize().intValue(), total, data);
	}

	public List<AcPwdFind> getAll() {
		return this.u.getAll();
	}

	public AcPwdFind findAcPwdFindByUserNameEmailVDate(String username, String email, Date validDate) {
		return this.u.findAcPwdFindByUserNameEmailVDate(username, email, validDate);
	}

	public void updateState(String id, Long status) {
		this.u.updateState(id, status);
	}
}