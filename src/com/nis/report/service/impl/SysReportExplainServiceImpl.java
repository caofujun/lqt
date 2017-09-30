package com.nis.report.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.report.dao.SysReportExplainDao;
import com.nis.report.entity.SysReportExplain;
import com.nis.report.service.SysReportExplainService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysReportExplainServiceImpl implements SysReportExplainService {
	@Autowired
	private SysReportExplainDao xG;
	@Autowired
	private SysDictService p;

	public void save(SysReportExplain sysReportExplain) {
		sysReportExplain.setReportId(z.a(bg.mr));
		this.xG.save(sysReportExplain);
	}

	public void delete(String reportId) {
		this.xG.delete(reportId);
	}

	public void update(SysReportExplain sysReportExplain) {
		this.xG.update(sysReportExplain);
	}

	public SysReportExplain get(String reportId) {
		return this.xG.get(reportId);
	}

	public MyPage<SysReportExplain> a(SysReportExplain sysReportExplain) {
		int total = this.xG.findSysReportExplainCount(sysReportExplain);
		List data = null;
		if (total > 0) {
			data = this.xG.findSysReportExplain(sysReportExplain);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				SysReportExplain report = (SysReportExplain) arg4.next();
				report.setReportShowName(this.p.k("report_type", report.getReportName(), (String) null));
			}
		}

		return new MyPage(sysReportExplain.getPage().intValue(), sysReportExplain.getSize().intValue(), total, data);
	}

	public List<SysReportExplain> getAll() {
		return this.xG.getAll();
	}

	public List<SysReportExplain> findByName(String reportName) {
		List reportExplainList = this.xG.findByName(reportName);
		Iterator arg3 = reportExplainList.iterator();

		while (arg3.hasNext()) {
			SysReportExplain report = (SysReportExplain) arg3.next();
			String rule = report.getReportRule();
			if (ab.isNotEmpty(rule)) {
				report.setReportRules(rule.split("%"));
			}
		}

		return reportExplainList;
	}
}