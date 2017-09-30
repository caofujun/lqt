package com.nis.patient.service.impl;

import com.nis.comm.constants.b;
import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.g;
import com.nis.comm.utils.r;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.param.service.SysParamService;
import com.nis.patient.dao.St005SsxxbDao;
import com.nis.patient.entity.St005Ssxxb;
import com.nis.patient.service.St005SsxxbService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St005SsxxbServiceImpl implements St005SsxxbService {
	@Autowired
	private St005SsxxbDao tv;
	@Autowired
	private Bk002GrzdService us;
	@Autowired
	private SysParamService j;

	public void save(St005Ssxxb st005Ssxxb) {
		this.tv.save(st005Ssxxb);
	}

	public void delete(String id) {
		this.tv.delete(id);
	}

	public void update(St005Ssxxb st005Ssxxb) {
		this.tv.update(st005Ssxxb);
	}

	public void updateSpecified(St005Ssxxb st005Ssxxb, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.tv.updateSpecified(st005Ssxxb, updateAttrs);
		}

	}

	public St005Ssxxb get(String id) {
		return this.tv.get(id);
	}

	public MyPage<St005Ssxxb> a(St005Ssxxb st005Ssxxb) {
		int total = this.tv.findSt005SsxxbCount(st005Ssxxb);
		List data = null;
		if (total > 0) {
			data = this.tv.findSt005Ssxxb(st005Ssxxb);
		}

		return new MyPage(st005Ssxxb.getPage().intValue(), st005Ssxxb.getSize().intValue(), total, data);
	}

	public List<St005Ssxxb> getAll() {
		return this.tv.getAll();
	}

	public List<St005Ssxxb> findListByZyid(String zyid) {
		return this.tv.findListByZyid(zyid);
	}

	public List<St005Ssxxb> findListByPatientId(String patientId, Date operAt) {
		return this.tv.findListByPatientId(patientId, operAt);
	}

	public List<St005Ssxxb> findListByName(String[] operNames) {
		return this.tv.findListByName(operNames);
	}

	public Integer findGradeType(String zyid) {
		return this.tv.findGradeType(zyid);
	}

	public MyPage<St005Ssxxb> b(St005Ssxxb st005Ssxxb) {
		int total = this.tv.findSurgeryListCount(st005Ssxxb);
		List data = null;
		if (total > 0) {
			data = this.tv.findSurgeryList(st005Ssxxb);
		}

		return new MyPage(st005Ssxxb.getPage().intValue(), st005Ssxxb.getSize().intValue(), total, data);
	}

	public void f(St005Ssxxb st005Ssxxb) {
		ArrayList updateAttrs = new ArrayList();
		updateAttrs.add("status");
		updateAttrs.add("lastModDate");
		updateAttrs.add("lastModUserid");
		updateAttrs.add("lastLog");
		this.updateSpecified(st005Ssxxb, updateAttrs);
	}

	public void e(St005Ssxxb st005Ssxxb) {
		ArrayList updateAttrs = new ArrayList();
		updateAttrs.add("viewFlag");
		this.updateSpecified(st005Ssxxb, updateAttrs);
	}

	public void M(List<St005Ssxxb> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			St005Ssxxb st005 = (St005Ssxxb) arg2.next();
			this.a(st005, true);
		}

	}

	public void a(St005Ssxxb st005Ssxxb, boolean isFind) {
		Integer riskRateType = Integer.valueOf(r.d(this.j.findByParamCode(Param.NIS_OPERA_RISK_RATE)));
		Integer nnistemp;
		if (isFind && (new Integer(1)).equals(riskRateType)) {
			st005Ssxxb.setRiskRateType(riskRateType);
			nnistemp = null;
			if (StringUtils.isNotBlank(st005Ssxxb.getImpOpeId())) {
				nnistemp = this.getNnisPer(Integer.valueOf(1), st005Ssxxb.getImpOpeId());
			} else if (StringUtils.isNotBlank(st005Ssxxb.getOpepartkindid())) {
				nnistemp = this.getNnisPer(Integer.valueOf(0), st005Ssxxb.getOpepartkindid());
			}

			st005Ssxxb.setPercBitValue(nnistemp);
		}

		nnistemp = Integer.valueOf(0);
		if ("III类".equals(st005Ssxxb.getIncisionGrade()) || "IV类".equals(st005Ssxxb.getIncisionGrade())) {
			nnistemp = Integer.valueOf(nnistemp.intValue() + 1);
		}

		if ((st005Ssxxb.getAsa() == null ? 0 : st005Ssxxb.getAsa().intValue()) >= 3) {
			nnistemp = Integer.valueOf(nnistemp.intValue() + 1);
		}

		if ((new Integer(1)).equals(st005Ssxxb.getRiskRateType())) {
			if ((st005Ssxxb.getOperLengTime() == null
					? 0
					: st005Ssxxb.getOperLengTime().intValue()) > (st005Ssxxb.getPercBitValue() == null
							? 0
							: st005Ssxxb.getPercBitValue().intValue())) {
				nnistemp = Integer.valueOf(nnistemp.intValue() + 1);
			}

			st005Ssxxb.setNnis(nnistemp);
		} else {
			if ((st005Ssxxb.getOperLengTime() == null ? 0 : st005Ssxxb.getOperLengTime().intValue()) / 60 >= 3) {
				nnistemp = Integer.valueOf(nnistemp.intValue() + 1);
			}

			st005Ssxxb.setNnis(nnistemp);
		}

	}

	public void L(String archived, String nnis) {
		if (ab.isEmpty(archived)) {
			archived = "0";
		}

		if (ab.isEmpty(nnis)) {
			nnis = "isnull";
		}

		List st005SsxxbList = this.tv.findWaitCalcNnis(archived, nnis);
		Iterator arg4 = st005SsxxbList.iterator();

		while (arg4.hasNext()) {
			St005Ssxxb st005 = (St005Ssxxb) arg4.next();
			this.a(st005, true);
			this.tv.updateNnis(st005);
		}

	}

	public List<St005Ssxxb> findSurgeryDayList(St005Ssxxb st005Ssxxb) {
		List data = this.tv.findSurgeryDayList(st005Ssxxb);
		Iterator arg3 = data.iterator();

		while (arg3.hasNext()) {
			St005Ssxxb ssxxb = (St005Ssxxb) arg3.next();
			List bkList = this.us.getbyOperRelid(ssxxb.getRelid());
			if (bkList.size() > 0) {
				ssxxb.setInfectTypeId("" + ((Bk002Grzd) bkList.get(0)).getInfectType());
			}
		}

		return data;
	}

	public Map<String, Object> g(St005Ssxxb st005Ssxxb) {
		Map map = this.tv.findSurgeryQkCount(st005Ssxxb);
		Map map2 = this.tv.findSurgeryZdCount(st005Ssxxb);
		map.putAll(map2);
		if (ab.isNotEmpty(st005Ssxxb.getIsZdjc())) {
			boolean wwh = false;
			int wwh1;
			if ("zd".equals(st005Ssxxb.getIsZdjc())) {
				wwh1 = Integer.parseInt(map.get("ZDCOUNT").toString())
						- (Integer.parseInt(map.get("LINGCOUNT").toString())
								+ Integer.parseInt(map.get("YICOUNT").toString())
								+ Integer.parseInt(map.get("ERCOUNT").toString())
								+ Integer.parseInt(map.get("SANCOUNT").toString())
								+ Integer.parseInt(map.get("SICOUNT").toString()));
			} else if ("fzd".equals(st005Ssxxb.getIsZdjc())) {
				wwh1 = Integer.parseInt(map.get("FZDCOUNT").toString())
						- (Integer.parseInt(map.get("LINGCOUNT").toString())
								+ Integer.parseInt(map.get("YICOUNT").toString())
								+ Integer.parseInt(map.get("ERCOUNT").toString())
								+ Integer.parseInt(map.get("SANCOUNT").toString())
								+ Integer.parseInt(map.get("SICOUNT").toString()));
			} else {
				wwh1 = Integer.parseInt(map.get("QBCOUNT").toString())
						- (Integer.parseInt(map.get("LINGCOUNT").toString())
								+ Integer.parseInt(map.get("YICOUNT").toString())
								+ Integer.parseInt(map.get("ERCOUNT").toString())
								+ Integer.parseInt(map.get("SANCOUNT").toString())
								+ Integer.parseInt(map.get("SICOUNT").toString()));
			}

			map.put("WWHCOUNT", Integer.valueOf(wwh1));
		}

		return map;
	}

	public HSSFWorkbook c(St005Ssxxb st005Ssxxb) {
		HSSFWorkbook workbook = null;
		st005Ssxxb.setOrclBegNum((Integer) null);
		st005Ssxxb.setOrclEndNum((Integer) null);
		List st5List = this.tv.findSurgeryList(st005Ssxxb);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFPrintSetup hps = e.getPrintSetup();
			hps.setPaperSize(9);
			hps.setLandscape(true);
			e.setHorizontallyCenter(true);
			HSSFCellStyle style = g.c(workbook);

			for (int titleRow = 0; titleRow < 9; ++titleRow) {
				e.setColumnWidth(titleRow, 5000);
			}

			HSSFRow arg19 = e.createRow(0);
			arg19.setHeightInPoints(30.0F);
			e.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
			e.addMergedRegion(new CellRangeAddress(1, 1, 0, 12));
			e.addMergedRegion(new CellRangeAddress(2, 2, 0, 12));
			HSSFCellStyle titleStyle = g.a(workbook);
			g.a(arg19, 0, titleStyle, 1, b.hospName);
			HSSFRow subTitleRow = e.createRow(1);
			subTitleRow.setHeightInPoints(20.0F);
			HSSFCellStyle subTitleStyle = g.b(workbook);
			g.a(subTitleRow, 0, subTitleStyle, 1, "手术切口感染明细表");
			HSSFRow dateRow = e.createRow(2);
			g.a(dateRow, 0, (HSSFCellStyle) null, 1, "统计日期：" + f.formatDate(st005Ssxxb.getQueryStartDate()) + "~"
					+ f.formatDate(st005Ssxxb.getQueryEndDate()));
			HSSFRow rowTitle = e.createRow(3);
			e.setColumnWidth(0, 1280);
			e.setColumnWidth(1, 5120);
			e.setColumnWidth(2, 3072);
			e.setColumnWidth(3, 2560);
			e.setColumnWidth(4, 5120);
			e.setColumnWidth(5, 3072);
			e.setColumnWidth(6, 5120);
			e.setColumnWidth(7, 3072);
			e.setColumnWidth(8, 5120);
			e.setColumnWidth(9, 5120);
			e.setColumnWidth(10, 2560);
			e.setColumnWidth(11, 2560);
			e.setColumnWidth(12, 2560);
			e.setColumnWidth(13, 2560);
			e.setColumnWidth(14, 3840);
			e.setColumnWidth(15, 3072);
			e.setColumnWidth(16, 2560);
			e.setColumnWidth(17, 5120);
			e.setColumnWidth(18, 3072);
			e.setColumnWidth(19, 10240);
			g.a(rowTitle, 0, style, 1, "序号");
			g.a(rowTitle, 1, style, 1, "科室");
			g.a(rowTitle, 2, style, 1, "床号");
			String patientZyTitle = this.j.findByParamCode(Param.NIS_PATIENT_ZY_TITLE);
			g.a(rowTitle, 3, style, 1, patientZyTitle);
			g.a(rowTitle, 4, style, 1, "患者");
			g.a(rowTitle, 5, style, 1, "入院诊断");
			g.a(rowTitle, 6, style, 1, "出院日期");
			g.a(rowTitle, 7, style, 1, "手术名称");
			g.a(rowTitle, 8, style, 1, "手术日期");
			g.a(rowTitle, 9, style, 1, "手术开始时间");
			g.a(rowTitle, 10, style, 1, "手术结束时间");
			g.a(rowTitle, 11, style, 1, "切口类型");
			g.a(rowTitle, 12, style, 1, "持续时间");
			g.a(rowTitle, 13, style, 1, "ASA");
			g.a(rowTitle, 14, style, 1, "NNIS");
			g.a(rowTitle, 15, style, 1, "手术医生");
			g.a(rowTitle, 16, style, 1, "感染日期");
			g.a(rowTitle, 17, style, 1, "感染类型");
			g.a(rowTitle, 18, style, 1, "感染诊断");
			g.a(rowTitle, 19, style, 1, "术后24小时停药");
			g.a(rowTitle, 20, style, 1, "备注");
			Integer index = Integer.valueOf(4);
			if (st5List == null || st5List.size() == 0) {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
				return workbook;
			}

			String patientZyValue = this.j.findByParamCode(Param.NIS_PATIENT_ZY_VALUE);

			for (Iterator arg16 = st5List.iterator(); arg16.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
				St005Ssxxb ssxxb = (St005Ssxxb) arg16.next();
				this.a(ssxxb, true);
				HSSFRow row = e.createRow(index.intValue());
				g.a(row, 0, style, 1, Integer.valueOf(index.intValue() - 3));
				g.a(row, 1, style, 1, ssxxb.getDeptName());
				g.a(row, 2, style, 1, ssxxb.getBedNo());
				if ("zyid".equals(patientZyValue)) {
					g.a(row, 3, style, 1, ssxxb.getZyid());
				} else {
					g.a(row, 3, style, 1, ssxxb.getPatientId());
				}

				g.a(row, 4, style, 1, ssxxb.getPatientName() + "(" + ssxxb.getSex() + "," + ssxxb.getAge()
						+ ssxxb.getAgeUnit() + ")");
				g.a(row, 5, style, 1, ssxxb.getDiagnosisName());
				g.a(row, 6, style, 1, f.formatDate(ssxxb.getOutAt()));
				g.a(row, 7, style, 1, ssxxb.getOperName());
				g.a(row, 8, style, 1, f.formatDate(ssxxb.getOperAt()));
				g.a(row, 9, style, 1, f.g(ssxxb.getOperAtStart()));
				g.a(row, 10, style, 1, f.g(ssxxb.getOperAtEnd()));
				g.a(row, 11, style, 1, ssxxb.getIncisionGrade());
				g.a(row, 12, style, 1, ssxxb.getOperLengTime());
				g.a(row, 13, style, 1, ssxxb.getAsa());
				g.a(row, 14, style, 1, ssxxb.getNnis());
				g.a(row, 15, style, 1, ssxxb.getOpedocName());
				g.a(row, 16, style, 1, f.X(ssxxb.getInfectDate()));
				g.a(row, 17, style, 1, ssxxb.getInfectTypeName());
				g.a(row, 18, style, 1, ssxxb.getInfectDiagnName());
				if (ssxxb.getAfterGreater24() != null && ssxxb.getAfterGreater24().intValue() > 0) {
					g.a(row, 19, style, 1, "否");
				} else {
					g.a(row, 19, style, 1, "是");
				}

				g.a(row, 20, style, 1, ssxxb.getMemo());
			}
		} catch (Exception arg18) {
			arg18.printStackTrace();
		}

		return workbook;
	}

	public HSSFWorkbook d(St005Ssxxb st005Ssxxb) {
		HSSFWorkbook workbook = null;
		List st5List = this.tv.findSurgeryDayListExcel(st005Ssxxb);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg13 = e.createRow(0);
			g.a(arg13, 0, style, 1, "科室");
			String patientZyTitle = this.j.findByParamCode(Param.NIS_PATIENT_ZY_TITLE);
			g.a(arg13, 1, style, 1, patientZyTitle);
			g.a(arg13, 2, style, 1, "患者");
			g.a(arg13, 3, style, 1, "床号");
			g.a(arg13, 4, style, 1, "手术名称");
			g.a(arg13, 5, style, 1, "手术日期");
			g.a(arg13, 6, style, 1, "类型");
			g.a(arg13, 7, style, 1, "持续时间");
			g.a(arg13, 8, style, 1, "ASA评分");
			g.a(arg13, 9, style, 1, "NNIS评分");
			g.a(arg13, 10, style, 1, "感染类型");
			g.a(arg13, 11, style, 1, "感染部位");
			g.a(arg13, 12, style, 1, "状态");
			Integer index = Integer.valueOf(1);
			if (st5List != null && st5List.size() > 0) {
				String patientZyValue = this.j.findByParamCode(Param.NIS_PATIENT_ZY_VALUE);

				for (Iterator arg10 = st5List.iterator(); arg10
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					St005Ssxxb ssxxb = (St005Ssxxb) arg10.next();
					this.a(ssxxb, true);
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, ssxxb.getDeptName());
					if ("zyid".equals(patientZyValue)) {
						g.a(row, 1, style, 1, ssxxb.getZyid());
					} else {
						g.a(row, 1, style, 1, ssxxb.getPatientId());
					}

					g.a(row, 2, style, 1, ssxxb.getPatientName() + "(" + ssxxb.getSex() + "," + ssxxb.getAge()
							+ ssxxb.getAgeUnit() + ")");
					g.a(row, 3, style, 1, ssxxb.getBedNo());
					g.a(row, 4, style, 1, ssxxb.getOperName());
					g.a(row, 5, style, 1, f.formatDate(ssxxb.getOperAt()));
					g.a(row, 6, style, 1, ssxxb.getIncisionGrade());
					g.a(row, 7, style, 1, ssxxb.getOperLengTime());
					g.a(row, 8, style, 1, ssxxb.getAsa());
					g.a(row, 9, style, 1, ssxxb.getNnis());
					if ("1".equals(ssxxb.getInfectTypeId())) {
						g.a(row, 10, style, 1, "院感");
					} else if ("2".equals(ssxxb.getInfectTypeId())) {
						g.a(row, 10, style, 1, "社感");
					}

					g.a(row, 11, style, 1, ssxxb.getInfectDiagnName());
					g.a(row, 12, style, 1, ssxxb.getStatusName());
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg12) {
			arg12.printStackTrace();
		}

		return workbook;
	}

	public Date getMonitorPatientSsxxLastAt(String deptId) {
		return this.tv.getMonitorPatientSsxxLastAt(deptId);
	}

	public List<DataWarning> findPatentSsxxbWarning(Date queryStartDate, Date queryEndDate) {
		return this.tv.findPatentSsxxbWarning(queryStartDate, queryEndDate);
	}

	public Date getNumBefore(String zyid, Date updDate, int day) {
		return this.tv.getNumBefore(zyid, updDate, day);
	}

	public Integer getNnisPer(Integer impType, String impOpeId) {
		return this.tv.getNnisPer(impType, impOpeId);
	}

	public St005Ssxxb getByRelid(String relid) {
		return this.tv.getByRelid(relid);
	}

	public Date getRecentOperAt(String zyid, Date operAt) {
		return this.tv.getRecentOperAt(zyid, operAt);
	}
}