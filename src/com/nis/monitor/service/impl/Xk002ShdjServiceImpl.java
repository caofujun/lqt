package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.g;
import com.nis.monitor.dao.Xk002ShdjDao;
import com.nis.monitor.entity.Xk002Shdj;
import com.nis.monitor.service.Xk002ShdjService;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xk002ShdjServiceImpl implements Xk002ShdjService {
	@Autowired
	private Xk002ShdjDao uP;

	public void save(Xk002Shdj xk002Shdj) {
		this.uP.save(xk002Shdj);
	}

	public void delete(String shNo) {
		this.uP.delete(shNo);
	}

	public void update(Xk002Shdj xk002Shdj) {
		this.uP.update(xk002Shdj);
	}

	public Xk002Shdj get(String shNo) {
		return this.uP.get(shNo);
	}

	public MyPage<Xk002Shdj> a(Xk002Shdj xk002Shdj) {
		int total = this.uP.findXk002ShdjCount(xk002Shdj);
		List data = null;
		if (total > 0) {
			data = this.uP.findXk002Shdj(xk002Shdj);
		}

		return new MyPage(xk002Shdj.getPage().intValue(), xk002Shdj.getSize().intValue(), total, data);
	}

	public List<Xk002Shdj> getAll(String searchString) {
		return this.uP.getAll(searchString);
	}

	public void setStatus(String shNo, String useFlag) {
		this.uP.setStatus(shNo, useFlag);
	}

	public void b(HttpServletResponse response, String searchString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[][] exlTitle = new String[][]{{"审核编号", "审核类型", "审核者", "审核日期", "审核结果", "采购部门", "采购人员", "生产企业编号", "生产企业名称",
				"经营企业编号", "经营企业名称", "生产对经营授权有效期", "经营对个人授权有效期", "生产企业年度", "经营企业检验时间", "使用状态"}};
		List list = this.uP.getAll(searchString);
		String[][] tableData = new String[list.size()][];
		Xk002Shdj xk002 = null;

		for (int wb = 0; wb < list.size(); ++wb) {
			xk002 = (Xk002Shdj) list.get(wb);
			String[] output = new String[]{xk002.getShNo(), xk002.getShType(), xk002.getShName(),
					xk002.getShDate() == null ? "" : sdf.format(xk002.getShDate()), xk002.getShJg(), xk002.getDepCg(),
					xk002.getCgRy(), xk002.getScqyNo(), xk002.getScqyName(), xk002.getJyqyNo(), xk002.getJyqyName(),
					xk002.getScjyDate() == null ? "" : sdf.format(xk002.getScjyDate()),
					xk002.getJygrDate() == null ? "" : sdf.format(xk002.getJygrDate()),
					xk002.getScNd() == null ? "" : sdf.format(xk002.getScNd()),
					xk002.getJyJydate() == null ? "" : sdf.format(xk002.getJyJydate()),
					"1".equals(xk002.getUseFlag()) ? "启用" : "停用"};
			tableData[wb] = output;
		}

		HSSFWorkbook arg11 = g.a("审核信息管理", exlTitle, tableData, (int[][]) null);

		try {
			String e = URLEncoder.encode("审核信息管理" + (new Date()).getTime(), "UTF-8");
			ServletOutputStream arg12 = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + e + ".xls");
			response.setContentType("application/msexcel");
			arg11.write(arg12);
			arg12.close();
		} catch (IOException arg10) {
			arg10.printStackTrace();
		}

	}
}