package com.nis.outbreak.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.g;
import com.nis.mdr.entity.Xn014Liskjyw;
import com.nis.mdr.service.Xn014LiskjywService;
import com.nis.outbreak.dao.By007ShowDao;
import com.nis.outbreak.entity.By007GraphData;
import com.nis.outbreak.entity.By007Show;
import com.nis.outbreak.service.By007ShowService;
import com.nis.patient.entity.St003Cryxxb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class By007ShowServiceImpl implements By007ShowService {
	@Autowired
	private By007ShowDao wm;
	@Autowired
	private Xn014LiskjywService wn;

	public void save(By007Show by007Show) {
		this.wm.save(by007Show);
	}

	public void delete(String id) {
		this.wm.delete(id);
	}

	public void update(By007Show by007Show) {
		this.wm.update(by007Show);
	}

	public By007Show get(String id) {
		return this.wm.get(id);
	}

	public MyPage<By007Show> a(By007Show by007Show) {
		int total = this.wm.findBy007ShowCount(by007Show);
		List data = null;
		if (total > 0) {
			data = this.wm.findBy007Show(by007Show);
		}

		return new MyPage(by007Show.getPage().intValue(), by007Show.getSize().intValue(), total, data);
	}

	public List<By007Show> getAll() {
		return this.wm.getAll();
	}

	public List<Map<String, Object>> cm(String startDate) {
		HashMap paramMap = new HashMap();
		paramMap.put("startDate", startDate);
		this.wm.callPrStatBfyjAbsoluteWarn(paramMap);
		List list = (List) paramMap.get("result");
		return list;
	}

	public List<Map<String, Object>> cn(String startDate) {
		HashMap paramMap = new HashMap();
		paramMap.put("startDate", startDate);
		this.wm.callPrStatBfyjAbsoluteDept(paramMap);
		List list = (List) paramMap.get("result");
		return list;
	}

	public List<By007GraphData> o(String startDate, String endDate, String id, String deptId) {
		HashMap paramMap = new HashMap();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("id", id);
		paramMap.put("deptId", deptId);
		this.wm.callPrStatMasterAbsolute(paramMap);
		List list = (List) paramMap.get("result");
		return list;
	}

	public List<By007Show> findShowFields(Integer showType) {
		return this.wm.findShowFields(showType);
	}

	public List<Map<String, Object>> co(String startDate) {
		HashMap paramMap = new HashMap();
		paramMap.put("startDate", startDate);
		this.wm.callPrStatBfyjRelativeWarn(paramMap);
		List list = (List) paramMap.get("result");
		return list;
	}

	public List<Map<String, Object>> cp(String startDate) {
		HashMap paramMap = new HashMap();
		paramMap.put("startDate", startDate);
		this.wm.callPrStatBfyjRelativeDept(paramMap);
		List list = (List) paramMap.get("result");
		return list;
	}

	public List<By007GraphData> p(String startDate, String endDate, String id, String deptId) {
		HashMap paramMap = new HashMap();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("id", id);
		paramMap.put("deptId", deptId);
		this.wm.callPrStatMasterRelative(paramMap);
		List list = (List) paramMap.get("result");
		return list;
	}

	@SqlLog(p = "暴发预警--暴发记录患者列表")
	public List<St003Cryxxb> findPatientInfo(By007Show by007Show) {
		return this.wm.findPatientInfo(by007Show);
	}

	@SqlLog(p = "暴发记录--血培养人员列表")
	public List<St003Cryxxb> findBloodCulturePatient(By007Show by007Show) {
		return this.wm.findBloodCulturePatient(by007Show);
	}

	public List<Map<String, Object>> b(By007Show by007Show) {
		HashMap paramMap = new HashMap();
		paramMap.put("queryStartDate", f.formatDate(by007Show.getQueryStartDate()));
		paramMap.put("queryEndDate", f.formatDate(by007Show.getQueryEndDate()));
		paramMap.put("id", by007Show.getId());
		paramMap.put("deptId", by007Show.getDeptId());
		this.wm.callPrStatBy0007Detail(paramMap);
		List list = (List) paramMap.get("result");
		Xn014Liskjyw xn014Liskjyw = null;
		if (list != null && !list.isEmpty() && list.size() > 0) {
			for (int i = 0; i < list.size(); ++i) {
				Set keys = ((Map) list.get(i)).keySet();
				HashMap addMap = new HashMap();
				Iterator arg8 = keys.iterator();

				while (arg8.hasNext()) {
					String key = (String) arg8.next();
					if (key.toUpperCase().contains("K")) {
						xn014Liskjyw = this.wn.get(key.substring(1));
						if (xn014Liskjyw != null) {
							addMap.put(xn014Liskjyw.getDrugname().replace("/", "|"), ((Map) list.get(i)).get(key));
						}
					}
				}

				((Map) list.get(i)).putAll(addMap);
			}
		}

		return list;
	}

	@SqlLog(p = "暴发预警--暴发患者导出")
	public HSSFWorkbook c(By007Show by007Show) {
		HSSFWorkbook workbook = null;
		List st3List = this.wm.findPatientInfo(by007Show);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg11 = e.createRow(0);
			g.a(arg11, 0, style, 1, "住院号");
			g.a(arg11, 1, style, 1, "住院次数");
			g.a(arg11, 2, style, 1, "床号");
			g.a(arg11, 3, style, 1, "患者姓名");
			g.a(arg11, 4, style, 1, "年龄");
			g.a(arg11, 5, style, 1, "性别");
			g.a(arg11, 6, style, 1, "当前科室");
			g.a(arg11, 7, style, 1, "入院日期");
			g.a(arg11, 8, style, 1, "入院科室");
			g.a(arg11, 9, style, 1, "出院日期");
			g.a(arg11, 10, style, 1, "出院科室");
			g.a(arg11, 11, style, 1, "主管医生");
			Integer index = Integer.valueOf(1);
			if (st3List != null && st3List.size() > 0) {
				for (Iterator arg8 = st3List.iterator(); arg8
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					St003Cryxxb st003 = (St003Cryxxb) arg8.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, st003.getZyid());
					g.a(row, 1, style, 1, st003.getVisitId());
					g.a(row, 2, style, 1, st003.getBedNo());
					g.a(row, 3, style, 1, st003.getPatientName());
					g.a(row, 4, style, 1, st003.getAge() + st003.getAgeUnit());
					g.a(row, 5, style, 1, st003.getSex());
					g.a(row, 6, style, 1, st003.getDeptName());
					g.a(row, 7, style, 1, f.formatDate(st003.getInHospAt()));
					g.a(row, 8, style, 1, st003.getInDeptName());
					g.a(row, 9, style, 1, f.formatDate(st003.getOutAt()));
					g.a(row, 10, style, 1, st003.getOutDeptName());
					g.a(row, 11, style, 1, st003.getChargeDrName());
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg10) {
			arg10.printStackTrace();
		}

		return workbook;
	}

	@SqlLog(p = "暴发记录--血培养患者列表导出")
	public HSSFWorkbook d(By007Show by007Show) {
		HSSFWorkbook workbook = null;
		List st3List = this.wm.findBloodCulturePatient(by007Show);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg11 = e.createRow(0);
			g.a(arg11, 0, style, 1, "住院号");
			g.a(arg11, 1, style, 1, "住院次数");
			g.a(arg11, 2, style, 1, "患者姓名");
			g.a(arg11, 3, style, 1, "年龄");
			g.a(arg11, 4, style, 1, "性别");
			g.a(arg11, 5, style, 1, "当前科室");
			g.a(arg11, 6, style, 1, "送检日期");
			g.a(arg11, 7, style, 1, "送检科室");
			g.a(arg11, 8, style, 1, "检验项目");
			g.a(arg11, 9, style, 1, "检出日期");
			g.a(arg11, 10, style, 1, "检出结果");
			Integer index = Integer.valueOf(1);
			if (st3List != null && st3List.size() > 0) {
				for (Iterator arg8 = st3List.iterator(); arg8
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					St003Cryxxb st003 = (St003Cryxxb) arg8.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, st003.getZyid());
					g.a(row, 1, style, 1, st003.getVisitId());
					g.a(row, 2, style, 1, st003.getPatientName());
					g.a(row, 3, style, 1, st003.getAge() + st003.getAgeUnit());
					g.a(row, 4, style, 1, st003.getSex());
					g.a(row, 5, style, 1, st003.getDeptName());
					g.a(row, 6, style, 1, f.formatDate(st003.getSubmiAt()));
					g.a(row, 7, style, 1, st003.getSubmiDeptName());
					g.a(row, 8, style, 1, st003.getItemTypeName());
					g.a(row, 9, style, 1, f.formatDate(st003.getTestDate()));
					g.a(row, 10, style, 1, st003.getPathoName());
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg10) {
			arg10.printStackTrace();
		}

		return workbook;
	}

	public HSSFWorkbook J(String dataType, String startDate) {
		HSSFWorkbook workbook = null;
		List showList;
		List list;
		if ("contrast".equals(dataType)) {
			showList = this.findShowFields(Integer.valueOf(3));
			list = this.cp(startDate);
		} else {
			showList = this.findShowFields(Integer.valueOf(2));
			list = this.cn(startDate);
		}

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg15 = e.createRow(0);
			if (showList != null && showList.size() > 0) {
				By007Show by007 = new By007Show();
				by007.setId("DEPT_NAME");
				by007.setName("科室");
				showList.add(0, by007);

				int index;
				for (index = 0; index < showList.size(); ++index) {
					By007Show map = (By007Show) showList.get(index);
					g.a(arg15, index, style, 1, map.getName());
				}

				index = 1;

				for (Iterator arg11 = list.iterator(); arg11.hasNext(); ++index) {
					Map arg16 = (Map) arg11.next();
					HSSFRow row = e.createRow(index);

					for (int i = 0; i < showList.size(); ++i) {
						g.a(row, i, style, 1, arg16.get(((By007Show) showList.get(i)).getId()));
					}
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg14) {
			arg14.printStackTrace();
		}

		return workbook;
	}

	public HSSFWorkbook e(By007Show by007Show) {
		HSSFWorkbook workbook = null;
		List list = this.b(by007Show);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg15 = e.createRow(0);
			if (list != null && list.size() > 0) {
				Map showFields = (Map) list.get(0);
				ArrayList fields = new ArrayList();
				Iterator index = showFields.entrySet().iterator();

				while (index.hasNext()) {
					Entry titleIndex = (Entry) index.next();
					if (ab.bb((String) titleIndex.getKey())) {
						fields.add((String) titleIndex.getKey());
					}
				}

				g.a(arg15, 0, style, 1, "住院号");
				g.a(arg15, 1, style, 1, "姓名");
				g.a(arg15, 2, style, 1, "性别");
				g.a(arg15, 3, style, 1, "年龄");
				g.a(arg15, 4, style, 1, "当前科室");
				g.a(arg15, 5, style, 1, "送检日期");
				g.a(arg15, 6, style, 1, "送检科室");
				g.a(arg15, 7, style, 1, "送检项目");
				g.a(arg15, 8, style, 1, "检出日期");
				int arg16 = 9;

				for (Iterator map = fields.iterator(); map.hasNext(); ++arg16) {
					String arg17 = (String) map.next();
					g.a(arg15, arg16, style, 1, arg17);
				}

				int arg18 = 1;

				for (Iterator arg11 = list.iterator(); arg11.hasNext(); ++arg18) {
					Map arg19 = (Map) arg11.next();
					HSSFRow row = e.createRow(arg18);
					g.a(row, 0, style, 1, arg19.get("ZYID"));
					g.a(row, 1, style, 1, arg19.get("PATIENT_NAME"));
					g.a(row, 2, style, 1, arg19.get("SEX"));
					g.a(row, 3, style, 1, arg19.get("AGE"));
					g.a(row, 4, style, 1, arg19.get("CURRENT_DEPT"));
					g.a(row, 5, style, 1, arg19.get("SUBMIT_AT"));
					g.a(row, 6, style, 1, arg19.get("SUBMIT_DEPT"));
					g.a(row, 7, style, 1, arg19.get("ITEM_TYPE_NAME"));
					g.a(row, 8, style, 1, ab.c(ab.g(arg19.get("RESULT_DATE")), 10));

					for (int i = 0; i < fields.size(); ++i) {
						g.a(row, i + 9, style, 1, arg19.get(fields.get(i)));
					}
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg14) {
			arg14.printStackTrace();
		}

		return workbook;
	}

	@SqlLog(p = "院感端首页--病原体人员列表")
	public List<St003Cryxxb> findPathogenPatient(By007Show by007Show) {
		return this.wm.findPathogenPatient(by007Show);
	}

	@SqlLog(p = "院感端首页--感染部位列表")
	public List<St003Cryxxb> findInfectionSiteList(By007Show by007Show) {
		return this.wm.findInfectionSiteList(by007Show);
	}
}