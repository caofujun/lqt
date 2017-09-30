package com.nis.homepage.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.m;
import com.nis.comm.utils.z;
import com.nis.homepage.dao.SysHpLayoutDao;
import com.nis.homepage.entity.SysHpLayout;
import com.nis.homepage.service.SysHpLayoutService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysHpLayoutServiceImpl implements SysHpLayoutService {
	@Autowired
	private SysHpLayoutDao rl;

	public Result<String> a(SysHpLayout sysHpLayout) {
		Result result = new Result();
		SysHpLayout orgLayout = this.rl.getLayoutCode(sysHpLayout.getLayoutCode());
		if (sysHpLayout.getId() == null) {
			if (orgLayout != null) {
				result.setResult("error_exist");
				result.setMsg("该编码已使用!");
				return result;
			}

			sysHpLayout.setId(z.a(bg.mv));
			sysHpLayout.setLayoutStatus(m.ge.getValue());
			this.rl.save(sysHpLayout);
		} else {
			if (orgLayout != null && !orgLayout.getId().equals(sysHpLayout.getId())) {
				result.setResult("error_exist");
				result.setMsg("该编码已使用!");
				return result;
			}

			this.rl.update(sysHpLayout);
		}

		result.setResult("success");
		return result;
	}

	public void delete(String id) {
		this.rl.delete(id);
	}

	public SysHpLayout get(String id) {
		return this.rl.get(id);
	}

	public MyPage<SysHpLayout> b(SysHpLayout sysHpLayout) {
		int total = this.rl.findSysHpLayoutCount(sysHpLayout);
		List data = null;
		if (total > 0) {
			data = this.rl.findSysHpLayout(sysHpLayout);
		}

		return new MyPage(sysHpLayout.getPage().intValue(), sysHpLayout.getSize().intValue(), total, data);
	}

	public List<SysHpLayout> getAll() {
		return this.rl.getAll();
	}

	public List<SysHpLayout> find(SysHpLayout sysHpLayout) {
		return this.rl.find(sysHpLayout);
	}

	public void updateStatus(String id, String status) {
		this.rl.updateStatus(id, status);
	}

	public SysHpLayout getLayoutCode(String layoutCode) {
		return this.rl.getLayoutCode(layoutCode);
	}
}