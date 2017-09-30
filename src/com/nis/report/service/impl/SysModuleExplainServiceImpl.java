package com.nis.report.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.report.dao.SysModuleExplainDao;
import com.nis.report.entity.SysModuleExplain;
import com.nis.report.service.SysModuleExplainService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysModuleExplainServiceImpl implements SysModuleExplainService {
	@Autowired
	private SysModuleExplainDao xF;

	public void save(SysModuleExplain sysModuleExplain) {
		this.xF.save(sysModuleExplain);
	}

	public void delete(String mid) {
		this.xF.delete(mid);
	}

	public void update(SysModuleExplain sysModuleExplain) {
		this.xF.update(sysModuleExplain);
	}

	public SysModuleExplain get(String mid) {
		return this.xF.get(mid);
	}

	public MyPage<SysModuleExplain> a(SysModuleExplain sysModuleExplain) {
		int total = this.xF.findSysModuleExplainCount(sysModuleExplain);
		List data = null;
		if (total > 0) {
			data = this.xF.findSysModuleExplain(sysModuleExplain);
		}

		return new MyPage(sysModuleExplain.getPage().intValue(), sysModuleExplain.getSize().intValue(), total, data);
	}

	public List<SysModuleExplain> getAll() {
		return this.xF.getAll();
	}

	public List<SysModuleExplain> findByName(String mkName) {
		return this.xF.findByName(mkName);
	}
}