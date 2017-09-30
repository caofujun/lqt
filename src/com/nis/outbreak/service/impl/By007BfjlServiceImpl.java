package com.nis.outbreak.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.az;
import com.nis.comm.utils.f;
import com.nis.comm.utils.g;
import com.nis.outbreak.dao.By007BfjlDao;
import com.nis.outbreak.entity.By007Bfjl;
import com.nis.outbreak.service.By007BfjlService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class By007BfjlServiceImpl implements By007BfjlService {
	@Autowired
	private By007BfjlDao wi;

	public void save(By007Bfjl by007Bfjl) {
		this.wi.save(by007Bfjl);
	}

	public void delete(String id) {
		this.wi.delete(id);
	}

	public void update(By007Bfjl by007Bfjl) {
		this.wi.update(by007Bfjl);
	}

	public void updateSpecified(By007Bfjl by007Bfjl, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.wi.updateSpecified(by007Bfjl, updateAttrs);
		}

	}

	public By007Bfjl get(String id) {
		return this.wi.get(id);
	}

	public MyPage<By007Bfjl> a(By007Bfjl by007Bfjl) {
		int total = this.wi.findBy007BfjlCount(by007Bfjl);
		List data = null;
		if (total > 0) {
			data = this.wi.findBy007Bfjl(by007Bfjl);
		}

		return new MyPage(by007Bfjl.getPage().intValue(), by007Bfjl.getSize().intValue(), total, data);
	}

	public List<By007Bfjl> getAll() {
		return this.wi.getAll();
	}

	@SqlLog(p = "暴发记录--暴发记录明细")
	public MyPage<By007Bfjl> b(By007Bfjl by007Bfjl) {
		int total = this.wi.findListCount(by007Bfjl);
		List data = null;
		if (total > 0) {
			data = this.wi.findList(by007Bfjl);
		}

		return new MyPage(by007Bfjl.getPage().intValue(), by007Bfjl.getSize().intValue(), total, data);
	}

	public void a(By007Bfjl by007Bfjl, AcAccount account) {
		ArrayList updateAttrs = new ArrayList();
		if (az.lH.getValue() == by007Bfjl.getReadFlag()) {
			updateAttrs.add("readFlag");
			updateAttrs.add("auditId");
			updateAttrs.add("auditDate");
			updateAttrs.add("auditName");
			updateAttrs.add("auditFlag");
			by007Bfjl.setAuditDate(new Date());
			by007Bfjl.setAuditId(account.getUsername());
			by007Bfjl.setAuditName(account.getRealname());
			by007Bfjl.setAuditFlag(Integer.valueOf(1));
			this.updateSpecified(by007Bfjl, updateAttrs);
		} else if (az.lI.getValue() == by007Bfjl.getReadFlag()) {
			updateAttrs.add("readFlag");
			this.updateSpecified(by007Bfjl, updateAttrs);
		} else if (az.lG.getValue() == by007Bfjl.getReadFlag()) {
			updateAttrs.add("readFlag");
			updateAttrs.add("auditDate");
			updateAttrs.add("auditFlag");
			by007Bfjl.setAuditDate(new Date());
			by007Bfjl.setAuditFlag(Integer.valueOf(0));
			this.updateSpecified(by007Bfjl, updateAttrs);
		}

	}

	public HSSFWorkbook c(By007Bfjl by007Bfjl) {
		HSSFWorkbook workbook = null;
		List st3List = this.wi.findList(by007Bfjl);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg11 = e.createRow(0);
			g.a(arg11, 0, style, 1, "状态");
			g.a(arg11, 1, style, 1, "起始日期");
			g.a(arg11, 2, style, 1, "截止日期");
			g.a(arg11, 3, style, 1, "间隔天数");
			g.a(arg11, 4, style, 1, "科室编号");
			g.a(arg11, 5, style, 1, "科室");
			g.a(arg11, 6, style, 1, "出现疑似暴发");
			g.a(arg11, 7, style, 1, "出现例数");
			g.a(arg11, 8, style, 1, "统计P值");
			g.a(arg11, 9, style, 1, "最少人数");
			g.a(arg11, 10, style, 1, "监测日期");
			g.a(arg11, 11, style, 1, "计算时间");
			g.a(arg11, 12, style, 1, "审核人");
			g.a(arg11, 13, style, 1, "审核时间");
			g.a(arg11, 14, style, 1, "暴发类型");
			Integer index = Integer.valueOf(1);
			if (st3List != null && st3List.size() > 0) {
				for (Iterator arg8 = st3List.iterator(); arg8
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					By007Bfjl bfjl = (By007Bfjl) arg8.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, bfjl.getReadFlagName());
					g.a(row, 1, style, 1, f.formatDate(bfjl.getBreakStartDate()));
					g.a(row, 2, style, 1, f.formatDate(bfjl.getBreakEndDate()));
					g.a(row, 3, style, 1, bfjl.getObserveDay());
					g.a(row, 4, style, 1, bfjl.getDeptId());
					g.a(row, 5, style, 1, bfjl.getDeptName());
					g.a(row, 6, style, 1, bfjl.getTypeName());
					g.a(row, 7, style, 1, bfjl.getBreakCount());
					g.a(row, 8, style, 1, bfjl.getObserveLine());
					g.a(row, 9, style, 1, bfjl.getMulriple());
					g.a(row, 10, style, 1, f.formatDate(bfjl.getMoniDate()));
					g.a(row, 11, style, 1, f.c(bfjl.getCreateDate(), "yyyy-MM-dd HH:mm"));
					g.a(row, 12, style, 1, bfjl.getAuditName());
					g.a(row, 13, style, 1, f.c(bfjl.getAuditDate(), "yyyy-MM-dd HH:mm:ss"));
					g.a(row, 14, style, 1, bfjl.getBreakTypeName());
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg10) {
			arg10.printStackTrace();
		}

		return workbook;
	}

	@SqlLog(p = "暴发预警--暴发记录列表")
	public List<By007Bfjl> findListByDept(By007Bfjl by007Bfjl) {
		return this.wi.findListByDept(by007Bfjl);
	}

	@SqlLog(p = "暴发预警--暴发预警审核")
	public void updAuditFlag(By007Bfjl by007Bfjl) {
		this.wi.updAuditFlag(by007Bfjl);
	}

	@SqlLog(p = "暴发预警--设置备注")
	public void updMemo(By007Bfjl by007Bfjl) {
		this.wi.updMemo(by007Bfjl);
	}
}