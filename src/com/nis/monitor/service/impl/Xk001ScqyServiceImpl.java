package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.g;
import com.nis.monitor.dao.Xk001ScqyDao;
import com.nis.monitor.entity.Xk001Scqy;
import com.nis.monitor.service.Xk001ScqyService;
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
public class Xk001ScqyServiceImpl implements Xk001ScqyService {
	@Autowired
	private Xk001ScqyDao uO;

	public void save(Xk001Scqy xk001Scqy) {
		this.uO.save(xk001Scqy);
	}

	public void delete(String qyNo) {
		this.uO.delete(qyNo);
	}

	public void update(Xk001Scqy xk001Scqy) {
		this.uO.update(xk001Scqy);
	}

	public Xk001Scqy get(String qyNo) {
		return this.uO.get(qyNo);
	}

	public MyPage<Xk001Scqy> a(Xk001Scqy xk001Scqy) {
		int total = this.uO.findXk001ScqyCount(xk001Scqy);
		List data = null;
		if (total > 0) {
			data = this.uO.findXk001Scqy(xk001Scqy);
		}

		return new MyPage(xk001Scqy.getPage().intValue(), xk001Scqy.getSize().intValue(), total, data);
	}

	public List<Xk001Scqy> getAll(String searchString) {
		return this.uO.getAll(searchString);
	}

	public void setStatus(String qyNo, String useFlag) {
		this.uO.setStatus(qyNo, useFlag);
	}

	public List<Xk001Scqy> queryQyList(String qyType) {
		return this.uO.queryQyList(qyType);
	}

	public void a(HttpServletResponse response, String searchString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[][] exlTitle = new String[][]{{"企业编号", "企业类型", "企业名称", "地址", "法人", "电话（传真）", "CDC检验报告", "营业执照",
				"营业执照有效期", "医疗器械生产许可证", "器械生产许可有效期", "医疗器械注册证", "器械注册有效期", "卫生许可证", "卫生许可有效期", "卫生许可批件", "许可批件有效期",
				"医疗器械经营许可证", "医疗器械经营有效期", "使用状态"}};
		List list = this.uO.getAll(searchString);
		String[][] tableData = new String[list.size()][];
		Xk001Scqy xk001 = null;

		for (int wb = 0; wb < list.size(); ++wb) {
			xk001 = (Xk001Scqy) list.get(wb);
			String[] output = new String[]{xk001.getQyNo(), xk001.getQyType(), xk001.getQyName(), xk001.getAddress(),
					xk001.getQyFr(), xk001.getTel(), xk001.getCdcBg(), xk001.getYyzz(),
					xk001.getYyzzDate() == null ? "" : sdf.format(xk001.getYyzzDate()), xk001.getYlscXk(),
					xk001.getYlscDate() == null ? "" : sdf.format(xk001.getYlscDate()), xk001.getYlZc(),
					xk001.getYlzcDate() == null ? "" : sdf.format(xk001.getYlzcDate()), xk001.getWsXk(),
					xk001.getWsxkDate() == null ? "" : sdf.format(xk001.getWsxkDate()), xk001.getWsXkpj(),
					xk001.getWspjDate() == null ? "" : sdf.format(xk001.getWspjDate()), xk001.getYljyXkz(),
					xk001.getJyxkDate() == null ? "" : sdf.format(xk001.getJyxkDate()),
					"1".equals(xk001.getUseFlag()) ? "启用" : "停用"};
			tableData[wb] = output;
		}

		HSSFWorkbook arg11 = g.a("企业信息管理", exlTitle, tableData, (int[][]) null);

		try {
			String e = URLEncoder.encode("企业信息管理" + (new Date()).getTime(), "UTF-8");
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