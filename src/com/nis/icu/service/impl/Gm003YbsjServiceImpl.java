package com.nis.icu.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.g;
import com.nis.icu.dao.Gm003YbsjDao;
import com.nis.icu.entity.Gm003Ybsj;
import com.nis.icu.entity.IcuCount;
import com.nis.icu.service.Gm003YbsjService;
import com.nis.icu.service.Gm004JcmxService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gm003YbsjServiceImpl implements Gm003YbsjService {
	@Autowired
	private Gm003YbsjDao so;
	@Autowired
	private SysParamService j;
	@Autowired
	private DepService e;
	@Autowired
	private Gm004JcmxService bP;

	public void save(Gm003Ybsj gm003Ybsj) {
		this.so.save(gm003Ybsj);
	}

	public void delete(Date creationdate) {
		this.so.delete(creationdate);
	}

	public void update(Gm003Ybsj gm003Ybsj) {
		this.so.update(gm003Ybsj);
	}

	public Gm003Ybsj get(Date creationdate) {
		return this.so.get(creationdate);
	}

	public MyPage<Gm003Ybsj> a(Gm003Ybsj gm003Ybsj) {
		int total = this.so.findGm003YbsjCount(gm003Ybsj);
		List data = null;
		if (total > 0) {
			data = this.so.findGm003Ybsj(gm003Ybsj);
		}

		return new MyPage(gm003Ybsj.getPage().intValue(), gm003Ybsj.getSize().intValue(), total, data);
	}

	public List<Gm003Ybsj> getAll() {
		return this.so.getAll();
	}

	public List<IcuCount> c(String deptId, String startDate, String endDate, String unitId) {
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		return this.so.findIcuCount(deptId, startDate, endDate, tws, unitId);
	}

	public List<IcuCount> d(String deptId, String startDate, String endDate, String unitId) {
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		return this.so.findMonitorByMonth(deptId, startDate, endDate, tws, unitId);
	}

	public List<IcuCount> e(String deptId, String startDate, String endDate, String unitId) {
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		return this.so.findBedMonitorByMonth(deptId, startDate, endDate, tws, unitId);
	}

	public IcuCount f(String deptId, String startDate, String endDate, String unitId) {
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		return this.so.findMonitorByMonthSum(deptId, startDate, endDate, tws, unitId);
	}

	public HSSFWorkbook g(String deptId, String startDate, String endDate, String unitId) {
		HSSFWorkbook workbook = null;
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		new ArrayList();
		Dep dep = this.e.get(deptId);
		List icuList;
		if (dep.getIfbedicu() != null && dep.getIfbedicu().longValue() == 1L) {
			icuList = this.bP.i(deptId, startDate, endDate, unitId);
		} else {
			icuList = this.so.findIcuCount(deptId, startDate, endDate, tws, unitId);
		}

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg17 = e.createRow(0);
			g.a(arg17, 0, style, 1, "日期");
			g.a(arg17, 1, style, 1, "新进患者数");
			g.a(arg17, 2, style, 1, "当前所在患者数");
			g.a(arg17, 3, style, 1, "发热人数");
			g.a(arg17, 4, style, 1, "呼吸机插管患者数");
			g.a(arg17, 5, style, 1, "泌尿道插管患者数");
			g.a(arg17, 6, style, 1, "中心静脉插管患者数");
			Integer index = Integer.valueOf(1);
			if (icuList != null && icuList.size() > 0) {
				for (Iterator arg14 = icuList.iterator(); arg14
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					IcuCount icu = (IcuCount) arg14.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, icu.getStrDate());
					g.a(row, 1, style, 0, icu.getNewCount());
					g.a(row, 2, style, 0, icu.getInCount());
					g.a(row, 3, style, 0, icu.getFrCount());
					g.a(row, 4, style, 0, icu.getHxjCount());
					g.a(row, 5, style, 0, icu.getMndCount());
					g.a(row, 6, style, 0, icu.getZxjmCount());
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg16) {
			arg16.printStackTrace();
		}

		return workbook;
	}

	public HSSFWorkbook h(String deptId, String startDate, String endDate, String unitId) {
		HSSFWorkbook workbook = null;
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		List icuList = this.so.findMonitorByMonth(deptId, startDate, endDate, tws, unitId);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg16 = e.createRow(0);
			g.a(arg16, 0, style, 1, "月份");
			g.a(arg16, 1, style, 1, "新住进患者数");
			g.a(arg16, 2, style, 1, "在院患者数");
			g.a(arg16, 3, style, 1, "发热人数");
			g.a(arg16, 4, style, 1, "呼吸机插管患者数");
			g.a(arg16, 5, style, 1, "泌尿道插管患者数");
			g.a(arg16, 6, style, 1, "中心静脉插管患者数");
			Integer index = Integer.valueOf(1);
			if (icuList != null && icuList.size() > 0) {
				for (Iterator arg13 = icuList.iterator(); arg13
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					IcuCount icu = (IcuCount) arg13.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, icu.getStrDate());
					g.a(row, 1, style, 0, icu.getNewCount());
					g.a(row, 2, style, 0, icu.getInCount());
					g.a(row, 3, style, 0, icu.getFrCount());
					g.a(row, 4, style, 0, icu.getHxjCount());
					g.a(row, 5, style, 0, icu.getMndCount());
					g.a(row, 6, style, 0, icu.getZxjmCount());
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg15) {
			arg15.printStackTrace();
		}

		return workbook;
	}

	public List<IcuCount> c(String deptId, String startDate, String endDate, String deptType, String unitId) {
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		return this.so.findDayCount(deptId, startDate, endDate, deptType, tws, unitId);
	}

	public HSSFWorkbook d(String deptId, String startDate, String endDate, String deptType, String unitId) {
		HSSFWorkbook workbook = null;
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		List icuList = this.so.findDayCount(deptId, startDate, endDate, deptType, tws, unitId);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg17 = e.createRow(0);
			g.a(arg17, 0, style, 1, "科室");
			g.a(arg17, 1, style, 1, "新进患者数");
			g.a(arg17, 2, style, 1, "当前所在患者数");
			g.a(arg17, 3, style, 1, "发热人数");
			g.a(arg17, 4, style, 1, "呼吸机插管患者数");
			g.a(arg17, 5, style, 1, "泌尿道插管患者数");
			g.a(arg17, 6, style, 1, "中心静脉插管患者数");
			Integer index = Integer.valueOf(1);
			if (icuList != null && icuList.size() > 0) {
				for (Iterator arg14 = icuList.iterator(); arg14
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					IcuCount icu = (IcuCount) arg14.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, icu.getDeptName());
					g.a(row, 1, style, 0, icu.getNewCount());
					g.a(row, 2, style, 0, icu.getInCount());
					g.a(row, 3, style, 0, icu.getFrCount());
					g.a(row, 4, style, 0, icu.getHxjCount());
					g.a(row, 5, style, 0, icu.getMndCount());
					g.a(row, 6, style, 0, icu.getZxjmCount());
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg16) {
			arg16.printStackTrace();
		}

		return workbook;
	}

	public MyPage<Gm003Ybsj> b(Gm003Ybsj gm003Ybsj) {
		int total = this.so.findGm003YbsjKfjcCount(gm003Ybsj);
		List data = null;
		if (total > 0) {
			data = this.so.findGm003YbsjKfjc(gm003Ybsj);
		}

		return new MyPage(gm003Ybsj.getPage().intValue(), gm003Ybsj.getSize().intValue(), total, data);
	}

	public void kfjcDelete(Gm003Ybsj gm003Ybsj) {
		this.so.kfjcDelete(gm003Ybsj);
	}

	public List<Gm003Ybsj> findkfjcByCreationdate(Gm003Ybsj gm003Ybsj) {
		return this.so.findkfjcByCreationdate(gm003Ybsj);
	}

	@SqlLog(p = "院感端首页--咳嗽趋势")
	public List<Map<String, Object>> findMainKs(Date startDate, Date endDate) {
		return this.so.findMainKs(startDate, endDate);
	}

	@SqlLog(p = "院感端首页--腹泻趋势")
	public List<Map<String, Object>> findMainFx(Date startDate, Date endDate) {
		return this.so.findMainFx(startDate, endDate);
	}
}