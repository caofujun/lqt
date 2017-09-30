package com.nis.icu.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.comm.utils.g;
import com.nis.icu.dao.Gm001DjpdmDao;
import com.nis.icu.dao.Gm002DjpddDao;
import com.nis.icu.entity.Gm001Djpdm;
import com.nis.icu.service.Gm001DjpdmService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gm001DjpdmServiceImpl implements Gm001DjpdmService {
	@Autowired
	private Gm001DjpdmDao sl;
	@Autowired
	private Gm002DjpddDao sm;
	@Autowired
	private DepService e;

	public void save(Gm001Djpdm gm001Djpdm) {
		this.sl.save(gm001Djpdm);
	}

	public void update(Gm001Djpdm gm001Djpdm) {
		this.sl.update(gm001Djpdm);
	}

	public MyPage<Gm001Djpdm> a(Gm001Djpdm gm001Djpdm) {
		int total = this.sl.findGm001DjpdmCount(gm001Djpdm);
		List data = null;
		if (total > 0) {
			data = this.sl.findGm001Djpdm(gm001Djpdm);
		}

		return new MyPage(gm001Djpdm.getPage().intValue(), gm001Djpdm.getSize().intValue(), total, data);
	}

	public void a(Integer dtYear, Integer dtMonth, Integer weeknumber, String deptId, String startdt, String enddt,
			String adjusterid, String adjustername) {
		Gm001Djpdm gm001Djpdm = this.sm.getByWeekAndDeptId(deptId, dtYear, dtMonth, weeknumber);
		if (gm001Djpdm != null) {
			gm001Djpdm.setDtYear(dtYear);
			gm001Djpdm.setDtMonth(dtMonth);
			gm001Djpdm.setWeeknumber(weeknumber.toString());
			gm001Djpdm.setDeptId(deptId);
			gm001Djpdm.setStartdt(f.Y(startdt));
			gm001Djpdm.setAdjusterid(adjusterid);
			gm001Djpdm.setAdjustername(adjustername);
			gm001Djpdm.setEvaluatedt(new Date());
			gm001Djpdm.setEnddt(f.k(enddt + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			gm001Djpdm.setSumscore(Double.valueOf(gm001Djpdm.getAcount().doubleValue() * 1.0D
					+ gm001Djpdm.getBcount().doubleValue() * 2.0D + gm001Djpdm.getCcount().doubleValue() * 3.0D
					+ gm001Djpdm.getDcount().doubleValue() * 4.0D + gm001Djpdm.getEcount().doubleValue() * 5.0D));
			gm001Djpdm.setAvgscore(
					Double.valueOf(a(gm001Djpdm.getSumscore().doubleValue() / (gm001Djpdm.getAcount().doubleValue()
							+ gm001Djpdm.getBcount().doubleValue() + gm001Djpdm.getCcount().doubleValue()
							+ gm001Djpdm.getDcount().doubleValue() + gm001Djpdm.getEcount().doubleValue()))));
			Gm001Djpdm djpdm = this.sl.getByDeptIdAndDate(dtYear, dtMonth, deptId, weeknumber.toString());
			if (djpdm != null) {
				this.sl.update(gm001Djpdm);
			} else {
				this.sl.save(gm001Djpdm);
			}
		}

	}

	public static double a(double d) {
		BigDecimal bg = (new BigDecimal(d)).setScale(2, RoundingMode.UP);
		return bg.doubleValue();
	}

	public List<Gm001Djpdm> getAll() {
		return this.sl.getAll();
	}

	public HSSFWorkbook z(String monthDate, String deptId) throws Exception {
		HSSFWorkbook workbook = null;
		List gm001DjpdmList = this.y(monthDate, deptId);
		Integer aSum = Integer.valueOf(0);
		Integer bSum = Integer.valueOf(0);
		Integer cSum = Integer.valueOf(0);
		Integer dSum = Integer.valueOf(0);
		Integer eSum = Integer.valueOf(0);
		Double avgscoreSum = Double.valueOf(0.0D);
		Integer sumscoreSum = Integer.valueOf(0);
		Iterator sheet = gm001DjpdmList.iterator();

		Gm001Djpdm gm001Djpdm;
		while (sheet.hasNext()) {
			gm001Djpdm = (Gm001Djpdm) sheet.next();
			if (gm001Djpdm.getAcount() != null) {
				aSum = Integer.valueOf(aSum.intValue() + gm001Djpdm.getAcount().intValue());
			}

			if (gm001Djpdm.getBcount() != null) {
				bSum = Integer.valueOf(bSum.intValue() + gm001Djpdm.getBcount().intValue());
			}

			if (gm001Djpdm.getCcount() != null) {
				cSum = Integer.valueOf(cSum.intValue() + gm001Djpdm.getCcount().intValue());
			}

			if (gm001Djpdm.getDcount() != null) {
				dSum = Integer.valueOf(dSum.intValue() + gm001Djpdm.getDcount().intValue());
			}

			if (gm001Djpdm.getEcount() != null) {
				eSum = Integer.valueOf(eSum.intValue() + gm001Djpdm.getEcount().intValue());
			}

			if (gm001Djpdm.getAvgscore() != null) {
				avgscoreSum = Double.valueOf(avgscoreSum.doubleValue() + gm001Djpdm.getAvgscore().doubleValue());
			}

			if (gm001Djpdm.getSumscore() != null) {
				sumscoreSum = Integer.valueOf(sumscoreSum.intValue() + gm001Djpdm.getSumscore().intValue());
			}
		}

		gm001Djpdm = new Gm001Djpdm();
		gm001Djpdm.setWeeknumber("合计");
		gm001Djpdm.setAcount(Double.valueOf(aSum.doubleValue()));
		gm001Djpdm.setBcount(Double.valueOf(bSum.doubleValue()));
		gm001Djpdm.setCcount(Double.valueOf(cSum.doubleValue()));
		gm001Djpdm.setDcount(Double.valueOf(dSum.doubleValue()));
		gm001Djpdm.setEcount(Double.valueOf(eSum.doubleValue()));
		gm001Djpdm.setAvgscore(avgscoreSum);
		gm001Djpdm.setSumscore(Double.valueOf(sumscoreSum.doubleValue()));
		gm001DjpdmList.add(gm001Djpdm);
		workbook = new HSSFWorkbook();
		HSSFSheet arg19 = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
		HSSFCellStyle style = g.c(workbook);

		for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
			arg19.setColumnWidth(rowTitle, 5000);
		}

		HSSFRow arg20 = arg19.createRow(0);
		g.a(arg20, 0, style, 1, "周数");
		g.a(arg20, 1, style, 1, "开始时间");
		g.a(arg20, 2, style, 1, "结束时间");
		g.a(arg20, 3, style, 1, "A");
		g.a(arg20, 4, style, 1, "B");
		g.a(arg20, 5, style, 1, "C");
		g.a(arg20, 6, style, 1, "D");
		g.a(arg20, 7, style, 1, "E");
		g.a(arg20, 8, style, 1, "合计");
		g.a(arg20, 9, style, 1, "平均等级");
		g.a(arg20, 10, style, 1, "评定人");
		g.a(arg20, 11, style, 1, "评定时间");
		Integer index = Integer.valueOf(1);
		if (gm001DjpdmList != null && gm001DjpdmList.size() > 0) {
			for (Iterator arg17 = gm001DjpdmList.iterator(); arg17
					.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
				Gm001Djpdm djpdm = (Gm001Djpdm) arg17.next();
				HSSFRow row = arg19.createRow(index.intValue());
				g.a(row, 0, style, 1, djpdm.getWeeknumber());
				g.a(row, 1, style, 1, f.c(djpdm.getStartdt(), "yyyy-MM-dd"));
				g.a(row, 2, style, 1, f.c(djpdm.getEnddt(), "yyyy-MM-dd"));
				g.a(row, 3, style, 0, djpdm.getAcount());
				g.a(row, 4, style, 0, djpdm.getBcount());
				g.a(row, 5, style, 0, djpdm.getCcount());
				g.a(row, 6, style, 0, djpdm.getDcount());
				g.a(row, 7, style, 0, djpdm.getEcount());
				g.a(row, 8, style, 0, djpdm.getSumscore());
				g.a(row, 9, style, 0, djpdm.getAvgscore());
				g.a(row, 10, style, 1, djpdm.getAdjustername());
				g.a(row, 11, style, 1, f.c(djpdm.getEvaluatedt(), "yyyy-MM-dd HH:mm:ss"));
			}
		} else {
			g.a(arg19.createRow(1), 0, style, 1, "查无资料");
		}

		return workbook;
	}

	public List<Gm001Djpdm> y(String monthDate, String deptId) throws Exception {
		ArrayList djpdmList = new ArrayList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date1 = dateFormat.parse(monthDate);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date1);
		int days = calendar.getActualMaximum(5);
		int count = 0;

		for (int gm001Djpdm = 1; gm001Djpdm <= days; ++gm001Djpdm) {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dep = dateFormat1.parse(monthDate + "-" + gm001Djpdm);
			calendar.clear();
			calendar.setTime(dep);
			int k = (new Integer(calendar.get(7))).intValue();
			Gm001Djpdm djpdm;
			Gm001Djpdm gm001djpdm;
			if (k == 1) {
				djpdm = new Gm001Djpdm();
				djpdm.setDtYear(Integer.valueOf(calendar.get(1)));
				djpdm.setDtMonth(Integer.valueOf(calendar.get(2) + 1));
				++count;
				djpdm.setWeeknumber(String.valueOf(count));
				if (gm001Djpdm - 6 <= 1) {
					djpdm.setStartdt(f.k(monthDate + "-" + 1 + " 00:00", "yyyy-MM-dd HH:ss"));
				} else {
					djpdm.setStartdt(f.k(monthDate + "-" + (gm001Djpdm - 6) + " 00:00", "yyyy-MM-dd HH:ss"));
				}

				djpdm.setEnddt(f.k(monthDate + "-" + gm001Djpdm + " 23:59", "yyyy-MM-dd"));
				djpdm.setDeptId(deptId);
				gm001djpdm = this.sl.getByDeptIdAndDate(djpdm.getDtYear(), djpdm.getDtMonth(), djpdm.getDeptId(),
						djpdm.getWeeknumber());
				if (gm001djpdm != null) {
					djpdmList.add(gm001djpdm);
				} else {
					djpdmList.add(djpdm);
				}
			}

			if (k != 1 && gm001Djpdm == days) {
				djpdm = new Gm001Djpdm();
				djpdm.setDtYear(Integer.valueOf(calendar.get(1)));
				djpdm.setDtMonth(Integer.valueOf(calendar.get(2) + 1));
				++count;
				djpdm.setWeeknumber(String.valueOf(count));
				djpdm.setStartdt(f.k(monthDate + "-" + (gm001Djpdm - k + 2) + " 00:00", "yyyy-MM-dd HH:mm"));
				djpdm.setEnddt(f.k(monthDate + "-" + gm001Djpdm + " 23:59", "yyyy-MM-dd HH:mm"));
				djpdm.setDeptId(deptId);
				gm001djpdm = this.sl.getByDeptIdAndDate(djpdm.getDtYear(), djpdm.getDtMonth(), djpdm.getDeptId(),
						djpdm.getWeeknumber());
				if (gm001djpdm != null) {
					djpdmList.add(gm001djpdm);
				} else {
					djpdmList.add(djpdm);
				}
			}
		}

		Iterator arg15 = djpdmList.iterator();

		while (true) {
			while (arg15.hasNext()) {
				Gm001Djpdm arg14 = (Gm001Djpdm) arg15.next();
				Dep arg16 = this.e.get(deptId);
				if (arg16.getIfbedicu() != null && arg16.getIfbedicu().longValue() == 1L) {
					arg14.setNoScore(Integer.valueOf(this.sm.getBedCountByDateAndDeptId(arg14.getDeptId(),
							f.formatDate(arg14.getStartdt()), f.formatDate(arg14.getEnddt()), arg14.getDtYear(),
							arg14.getDtMonth(), Integer.valueOf(Integer.parseInt(arg14.getWeeknumber())))));
				} else {
					arg14.setNoScore(Integer.valueOf(this.sm.getCountByDateAndDeptId(arg14.getDeptId(),
							f.formatDate(arg14.getStartdt()), f.formatDate(arg14.getEnddt()), arg14.getDtYear(),
							arg14.getDtMonth(), Integer.valueOf(Integer.parseInt(arg14.getWeeknumber())))));
				}
			}

			return djpdmList;
		}
	}
}