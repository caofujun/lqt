package com.nis.mdr.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.f;
import com.nis.comm.utils.g;
import com.nis.comm.utils.r;
import com.nis.comm.utils.z;
import com.nis.log.interceptor.SqlLogInterceptor;
import com.nis.mdr.dao.Xn011DclymxDao;
import com.nis.mdr.entity.ViewMdr;
import com.nis.mdr.entity.Xn011Dclymx;
import com.nis.mdr.service.Xn011DclymxService;
import com.nis.param.service.SysParamService;
import java.text.SimpleDateFormat;
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
public class Xn011DclymxServiceImpl implements Xn011DclymxService {
	@Autowired
	private Xn011DclymxDao ue;
	@Autowired
	private SysParamService j;
	@Autowired
	private SqlLogInterceptor uf;

	public void save(Xn011Dclymx xn011Dclymx) {
		xn011Dclymx.setId(z.a(bg.ne));
		xn011Dclymx.setTablename(this.j.findByParamCode(Param.NIS_YJ_IS_VERSION));
		this.ue.save(xn011Dclymx);
	}

	public void batchInsert(List<Xn011Dclymx> dclymxList) {
		if (dclymxList != null && dclymxList.size() > 0) {
			String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
			this.ue.batchInsert(dclymxList, yjVersion);
		}

	}

	public void delete(Date dt) {
		this.ue.delete(dt);
	}

	public void update(Xn011Dclymx xn011Dclymx) {
		this.ue.update(xn011Dclymx);
	}

	public void updateByTestOrderNo(Xn011Dclymx xn011Dclymx) {
		this.ue.updateByTestOrderNo(xn011Dclymx);
	}

	public Xn011Dclymx get(Date dt) {
		return this.ue.get(dt);
	}

	public Xn011Dclymx a(String zyid, String testOrderNo, Integer itemType, String itemCode, String pathoCode) {
		return this.ue.get1(zyid, testOrderNo, itemType, itemCode, pathoCode);
	}

	public MyPage<Xn011Dclymx> b(Xn011Dclymx xn011Dclymx) {
		int total = this.ue.findXn011DclymxCount(xn011Dclymx);
		List data = null;
		if (total > 0) {
			data = this.ue.findXn011Dclymx(xn011Dclymx);
		}

		return new MyPage(xn011Dclymx.getPage().intValue(), xn011Dclymx.getSize().intValue(), total, data);
	}

	public List<Xn011Dclymx> getAll() {
		return this.ue.getAll();
	}

	public MyPage<ViewMdr> a(ViewMdr viewMdr) {
		int total = this.ue.findViewMdrCount(viewMdr);
		List data = null;
		Object gl = null;
		if (total > 0) {
			data = this.ue.findViewMdr(viewMdr);
		}

		return new MyPage(viewMdr.getPage().intValue(), viewMdr.getSize().intValue(), total, data);
	}

	public HSSFWorkbook b(ViewMdr viewMdr) {
		HSSFWorkbook workbook = null;
		List mdrList = this.ue.findViewMdrList(viewMdr);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg13 = e.createRow(0);
			g.a(arg13, 0, style, 1, "审核");
			g.a(arg13, 1, style, 1, "隔离情况");
			String patientZyTitle = this.j.findByParamCode(Param.NIS_PATIENT_ZY_TITLE);
			g.a(arg13, 2, style, 1, patientZyTitle);
			g.a(arg13, 3, style, 1, "姓名");
			g.a(arg13, 4, style, 1, "床号");
			g.a(arg13, 5, style, 1, "送检日期");
			g.a(arg13, 6, style, 1, "所在科室");
			g.a(arg13, 7, style, 1, "送检科室");
			g.a(arg13, 8, style, 1, "送检标本");
			g.a(arg13, 9, style, 1, "送检单号");
			g.a(arg13, 10, style, 1, "检出日期");
			g.a(arg13, 11, style, 1, "检出结果");
			g.a(arg13, 12, style, 1, "所属菌属");
			g.a(arg13, 13, style, 1, "多耐类型");
			g.a(arg13, 14, style, 1, "特殊耐药");
			g.a(arg13, 15, style, 1, "感染类型");
			g.a(arg13, 16, style, 1, "esbl");
			Integer index = Integer.valueOf(1);
			if (mdrList != null && mdrList.size() > 0) {
				String patientZyValue = this.j.findByParamCode(Param.NIS_PATIENT_ZY_VALUE);

				for (Iterator arg10 = mdrList.iterator(); arg10
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					ViewMdr mdr = (ViewMdr) arg10.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, mdr.getIsAudited());
					g.a(row, 1, style, 1, "1".equals(mdr.getGl()) ? "隔离结束" : ("2".equals(mdr.getGl()) ? "正在隔离" : ""));
					if ("zyid".equals(patientZyValue)) {
						g.a(row, 2, style, 1, mdr.getZyid());
					} else {
						g.a(row, 2, style, 1, mdr.getPatientId());
					}

					g.a(row, 3, style, 1, mdr.getPatientName());
					g.a(row, 4, style, 1, mdr.getBedNo());
					g.a(row, 5, style, 1, f.formatDate(mdr.getSubmiAt()));
					g.a(row, 6, style, 1, mdr.getSurveyDeptName());
					g.a(row, 7, style, 1, mdr.getDeptName());
					g.a(row, 8, style, 1, mdr.getLisitemName());
					g.a(row, 9, style, 1, mdr.getTestOrderNo());
					g.a(row, 10, style, 1, f.formatDate(mdr.getDt()));
					g.a(row, 11, style, 1, mdr.getLispathoName());
					g.a(row, 12, style, 1, mdr.getBactGenusIdName());
					g.a(row, 13, style, 1, mdr.getResPropName());
					g.a(row, 14, style, 1, mdr.getSpecDescribes());
					g.a(row, 15, style, 1, mdr.getInfectTypeName());
					g.a(row, 16, style, 1, mdr.getEsbl());
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg12) {
			arg12.printStackTrace();
		}

		return workbook;
	}

	public List<Map<String, Object>> findwswbbfb(String startDate, String endDate, String ificu) {
		return this.ue.findwswbbfb(startDate, endDate, ificu);
	}

	public List<Map<String, Object>> findwswjcqk(String startDate, String endDate, String ificu) {
		return this.ue.findwswjcqk(startDate, endDate, ificu);
	}

	public List<Map<String, Object>> dcnyfbt(String startDate, String endDate, String ificu) {
		return this.ue.dcnyfbt(startDate, endDate, ificu);
	}

	public List<Map<String, Object>> grblfbt(String startDate, String endDate, String ificu) {
		return this.ue.grblfbt(startDate, endDate, ificu);
	}

	public Map<String, Object> b(String date, String specType, List<String> deptIdIn) {
		Map map = this.ue.findByDateCount(date, deptIdIn);
		Map map2 = this.ue.findByDateAndTypeCount(date, specType, deptIdIn);
		map.putAll(map2);
		return map;
	}

	public List<ViewMdr> c(ViewMdr viewMdr) {
		return this.ue.findViewMdrDay(viewMdr);
	}

	public HSSFWorkbook d(ViewMdr viewMdr) {
		HSSFWorkbook workbook = null;
		viewMdr.setOrclBegNum((Integer) null);
		viewMdr.setOrclEndNum((Integer) null);
		List mdrList = this.ue.findViewMdr(viewMdr);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg13 = e.createRow(0);
			g.a(arg13, 0, style, 1, "审核");
			g.a(arg13, 1, style, 1, "隔离情况");
			String patientZyTitle = this.j.findByParamCode(Param.NIS_PATIENT_ZY_TITLE);
			g.a(arg13, 2, style, 1, patientZyTitle);
			g.a(arg13, 3, style, 1, "姓名");
			g.a(arg13, 4, style, 1, "床号");
			g.a(arg13, 5, style, 1, "送检日期");
			g.a(arg13, 6, style, 1, "所在科室");
			g.a(arg13, 7, style, 1, "送检科室");
			g.a(arg13, 8, style, 1, "送检标本");
			g.a(arg13, 9, style, 1, "送检单号");
			g.a(arg13, 10, style, 1, "检出日期");
			g.a(arg13, 11, style, 1, "检出菌");
			g.a(arg13, 12, style, 1, "所属菌属");
			g.a(arg13, 13, style, 1, "多耐类型");
			g.a(arg13, 14, style, 1, "特殊耐药");
			g.a(arg13, 15, style, 1, "esbl");
			g.a(arg13, 16, style, 1, "感染类型");
			g.a(arg13, 17, style, 1, "感染时间");
			g.a(arg13, 18, style, 1, "感染部位");
			Integer index = Integer.valueOf(1);
			if (mdrList == null || mdrList.size() == 0) {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
				return workbook;
			}

			String patientZyValue = this.j.findByParamCode(Param.NIS_PATIENT_ZY_VALUE);

			for (Iterator arg10 = mdrList.iterator(); arg10.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
				ViewMdr mdr = (ViewMdr) arg10.next();
				HSSFRow row = e.createRow(index.intValue());
				g.a(row, 0, style, 1, mdr.getIsAudited());
				g.a(row, 1, style, 1, mdr.getIsolation());
				if ("zyid".equals(patientZyValue)) {
					g.a(row, 2, style, 1, mdr.getZyid());
				} else {
					g.a(row, 2, style, 1, mdr.getPatientId());
				}

				g.a(row, 2, style, 1, mdr.getPatientId());
				g.a(row, 3, style, 1, mdr.getPatientName());
				g.a(row, 4, style, 1, mdr.getBedNo());
				g.a(row, 5, style, 1, f.formatDate(mdr.getSubmiAt()));
				g.a(row, 6, style, 1, mdr.getSurveyDeptName());
				g.a(row, 7, style, 1, mdr.getDeptName());
				g.a(row, 8, style, 1, mdr.getLisitemName());
				g.a(row, 9, style, 1, mdr.getTestOrderNo());
				g.a(row, 10, style, 1, f.formatDate(mdr.getDt()));
				g.a(row, 11, style, 1, mdr.getLispathoName());
				g.a(row, 12, style, 1, mdr.getBactGenusIdName());
				g.a(row, 13, style, 1, mdr.getResPropName());
				g.a(row, 14, style, 1, mdr.getSpecDescribes());
				g.a(row, 15, style, 1, mdr.getEsbl());
				g.a(row, 16, style, 1, mdr.getInfectTypeName());
				g.a(row, 17, style, 1, mdr.getGrsj());
				g.a(row, 18, style, 1, mdr.getGrbw());
			}
		} catch (Exception arg12) {
			arg12.printStackTrace();
		}

		return workbook;
	}

	@SqlLog(p = "院感端首页--送检标本构成")
	public List<Map<String, Object>> findMainSamples(Date startDate, Date endDate) {
		List list = this.ue.findMainSamples(startDate, endDate);
		boolean others = false;
		int temp = 0;
		Map othersMap = null;
		if (list.size() == 1) {
			list.clear();
		}

		Iterator arg7 = list.iterator();

		while (arg7.hasNext()) {
			Map map = (Map) arg7.next();
			if (!"总计".equals(map.get("name"))) {
				temp += r.d(map.get("value"));
			} else {
				int others1 = r.d(map.get("value")) - temp;
				if (others1 != 0) {
					map.put("name", "其他");
					map.put("value", Integer.valueOf(others1));
				} else {
					othersMap = map;
				}
			}
		}

		if (othersMap != null) {
			list.remove(othersMap);
		}

		return list;
	}

	@SqlLog(p = "院感端首页--MDRO构成")
	public List<Map<String, Object>> findMainFocusBacteria(Date startDate, Date endDate) {
		List list = this.ue.findMainFocusBacteria(startDate, endDate);
		boolean others = false;
		int temp = 0;
		Map othersMap = null;
		if (list.size() == 1) {
			list.clear();
		}

		Iterator arg7 = list.iterator();

		while (arg7.hasNext()) {
			Map map = (Map) arg7.next();
			if (!"总计".equals(map.get("name"))) {
				temp += r.d(map.get("value"));
			} else {
				int others1 = r.d(map.get("value")) - temp;
				if (others1 != 0) {
					map.put("name", "其他");
					map.put("value", Integer.valueOf(others1));
				} else {
					othersMap = map;
				}
			}
		}

		if (othersMap != null) {
			list.remove(othersMap);
		}

		return list;
	}

	@SqlLog(p = "院感端首页--多耐菌检出率趋势")
	public List<Map<String, Object>> findMainMoreResistant(Date startDate, Date endDate) {
		return this.ue.findMainMoreResistant(startDate, endDate);
	}

	public List<ViewMdr> findCheckOutbacteria(String zyid) {
		return this.ue.findCheckOutbacteria(zyid);
	}

	public void updateMdrType(Xn011Dclymx xn011Dclymx) {
		this.ue.updateMdrType(xn011Dclymx);
	}

	public void updateMdrInfo(Xn011Dclymx xn011Dclymx) {
		this.ue.updateMdrInfo(xn011Dclymx);
	}

	public Date getMonitorPatientMdrLastAt() {
		return this.ue.getMonitorPatientMdrLastAt();
	}

	public void updateIT(Xn011Dclymx xn011Dclymx) {
		this.ue.updateIT(xn011Dclymx);
	}

	public Xn011Dclymx getByPrimaryKeys(Xn011Dclymx xn011Dclymx) {
		return this.ue.getByPrimaryKeys(xn011Dclymx);
	}

	public MyPage<ViewMdr> e(ViewMdr viewMdr) {
		int total = this.ue.getQueryMDRoCount(viewMdr);
		List data = null;
		if (total > 0) {
			data = this.ue.getQueryMDRo(viewMdr);
		}

		return new MyPage(viewMdr.getPage().intValue(), viewMdr.getSize().intValue(), total, data);
	}

	public void updateInfectTypeId(Xn011Dclymx xn011Dclymx) {
		this.ue.updateInfectTypeId(xn011Dclymx);
	}

	public long getMaxOrderno() {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.ue.getMaxOrderno(yjVersion);
	}

	public void clearInfectTypeByCardId(String cardId) {
		this.ue.clearInfectTypeByCardId(cardId);
	}

	public void updateMdrCheck(Xn011Dclymx xn011Dclymx) {
		this.ue.updateMdrCheck(xn011Dclymx);
	}
}