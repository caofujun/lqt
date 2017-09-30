package com.nis.monitor.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.access.service.AcAccountService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.TreeEntity;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.g;
import com.nis.comm.utils.z;
import com.nis.monitor.dao.Bk002GrzdDao;
import com.nis.monitor.dao.Gr002YsgrMxDao;
import com.nis.monitor.dao.Gr011BytDao;
import com.nis.monitor.dao.Gr012YmsyDao;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Gr002YsgrMx;
import com.nis.monitor.service.Bk001SbkService;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.monitor.service.Gr002YsgrMxService;
import com.nis.organization.entity.Doctor;
import com.nis.organization.service.DoctorService;
import com.nis.param.service.SysParamService;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Gr002YsgrMxServiceImpl implements Gr002YsgrMxService {
	@Autowired
	private Gr002YsgrMxDao uG;
	@Autowired
	private Bk001SbkService bW;
	@Autowired
	private Gr011BytDao uH;
	@Autowired
	private Gr012YmsyDao uI;
	@Autowired
	private Bk002GrzdDao uD;
	@Autowired
	private Bk002GrzdService us;
	@Autowired
	private SysParamService cs;
	@Autowired
	private AcAccountService uJ;
	@Autowired
	private DoctorService f;

	public void save(Gr002YsgrMx gr002YsgrMx) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		gr002YsgrMx.setTablename(yjVersion);
		gr002YsgrMx.setRegId(z.a(bg.mX));
		this.uG.save(gr002YsgrMx);
	}

	public void delete(String regId) {
		this.uG.delete(regId);
	}

	public void update(Gr002YsgrMx gr002YsgrMx) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		gr002YsgrMx.setTablename(yjVersion);
		this.uG.update(gr002YsgrMx);
	}

	public void updateSpecified(Gr002YsgrMx gr002YsgrMx, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.uG.updateSpecified(gr002YsgrMx, updateAttrs);
		}

	}

	public void updateByGr2Relid(Gr002YsgrMx gr002YsgrMx) {
		this.uG.updateByGr2Relid(gr002YsgrMx);
	}

	public Gr002YsgrMx get(String regId) {
		return this.uG.get(regId);
	}

	public MyPage<Gr002YsgrMx> a(Gr002YsgrMx gr002YsgrMx) {
		int total = this.uG.findGr002YsgrMxCount(gr002YsgrMx);
		List data = null;
		if (total > 0) {
			data = this.uG.findGr002YsgrMx(gr002YsgrMx);
		}

		return new MyPage(gr002YsgrMx.getPage().intValue(), gr002YsgrMx.getSize().intValue(), total, data);
	}

	public List<Gr002YsgrMx> getAll() {
		return this.uG.getAll();
	}

	public List<Gr002YsgrMx> a(String zyid, String regId, String gr2Relid, Integer infectTypeId) {
		String qzValue = this.cs.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		return this.uG.findWarningResults(zyid, regId, gr2Relid, infectTypeId, qzValue);
	}

	public List<Gr002YsgrMx> findWarningResults2(String zyid, String infectCode, String regId, Integer infectTypeId) {
		return this.uG.findWarningResults2(zyid, infectCode, regId, infectTypeId);
	}

	public List<Gr002YsgrMx> findInfectionCounts(Gr002YsgrMx gr002YsgrMx) {
		String qzValue = this.cs.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		gr002YsgrMx.setQzValue(qzValue);
		return this.uG.findInfectionCounts(gr002YsgrMx);
	}

	public List<Gr002YsgrMx> findInfectionDetail(Gr002YsgrMx gr002YsgrMx) {
		return this.uG.findInfectionDetail(gr002YsgrMx);
	}

	public MyPage<Gr002YsgrMx> b(Gr002YsgrMx gr002YsgrMx) {
		int total = this.uG.findConfirmInfectionCount(gr002YsgrMx);
		List data = null;
		if (total > 0) {
			data = this.uG.findConfirmInfection(gr002YsgrMx);
		}

		return new MyPage(gr002YsgrMx.getPage().intValue(), gr002YsgrMx.getSize().intValue(), total, data);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Gr002YsgrMx gr002YsgrMx, String delReason, LoginUser user) {
		Bk001Sbk bk001Sbk = new Bk001Sbk();
		bk001Sbk.setRelid(gr002YsgrMx.getRelid());
		bk001Sbk.setIsOk(Integer.valueOf(3));
		bk001Sbk.setIsDel(Integer.valueOf(1));
		bk001Sbk.setDelReason(delReason);
		ArrayList updateAttrs = new ArrayList();
		updateAttrs.add("isOk");
		updateAttrs.add("isDel");
		updateAttrs.add("delReason");
		this.bW.updateSpecified(bk001Sbk, updateAttrs);
		Bk002Grzd bk002Grzd = new Bk002Grzd();
		bk002Grzd.setAuthAt(new Date());
		bk002Grzd.setAuthStatus(com.nis.comm.enums.z.hC.getValue());
		bk002Grzd.setAuthUserid(user.getUsername());
		bk002Grzd.setAuthUsername(user.getRealname());
		bk002Grzd.setDelReason(delReason);
		bk002Grzd.setRefid(gr002YsgrMx.getRelid());
		this.uD.updStatusByRefid(bk002Grzd);
		this.uH.deleteByRelid(gr002YsgrMx.getRelid(), (String) null);
		this.uI.deleteByRelid(gr002YsgrMx.getRelid(), (String) null);
		this.uG.updateForReport(gr002YsgrMx);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Gr002YsgrMx gr002YsgrMx, String delReason, List<String> dataList, LoginUser user) {
		int num = this.uD.getReportInfect(gr002YsgrMx.getRelid(), (String) null).size();
		if (num > 1) {
			Iterator bk001Sbk = dataList.iterator();

			while (bk001Sbk.hasNext()) {
				String stateList = (String) bk001Sbk.next();
				String updateAttrs = "authAt,authUserid,authUsername,authStatus,delReason";
				List updateAttrs1 = Arrays.asList(updateAttrs.split(","));
				Bk002Grzd bk002Grzd = new Bk002Grzd();
				bk002Grzd.setAuthAt(new Date());
				bk002Grzd.setAuthStatus(com.nis.comm.enums.z.hC.getValue());
				bk002Grzd.setAuthUserid(user.getUsername());
				bk002Grzd.setAuthUsername(user.getRealname());
				bk002Grzd.setDelReason(delReason);
				bk002Grzd.setRelid(stateList);
				this.us.updateSpecified(bk002Grzd, updateAttrs1);
				this.uH.deleteByRelid(gr002YsgrMx.getRelid(), stateList);
				this.uI.deleteByRelid(gr002YsgrMx.getRelid(), stateList);
				this.uG.updateForDiagnosis1(gr002YsgrMx.getZyid(), stateList);
			}

			List stateList1 = this.uD.findStatusByRefid(gr002YsgrMx.getRelid());
			if (stateList1 != null && !stateList1.contains(Integer.valueOf(0))
					&& !stateList1.contains(Integer.valueOf(1))) {
				Bk001Sbk bk001Sbk1 = new Bk001Sbk();
				bk001Sbk1.setRelid(gr002YsgrMx.getRelid());
				ArrayList updateAttrs2 = new ArrayList();
				updateAttrs2.add("isOk");
				if (stateList1.contains(Integer.valueOf(2))) {
					bk001Sbk1.setIsOk(Integer.valueOf(2));
				} else {
					bk001Sbk1.setIsOk(Integer.valueOf(3));
					bk001Sbk1.setIsDel(Integer.valueOf(1));
					bk001Sbk1.setDelReason(delReason);
					updateAttrs2.add("isDel");
					updateAttrs2.add("delReason");
				}

				this.bW.updateSpecified(bk001Sbk1, updateAttrs2);
			}
		} else {
			this.a(gr002YsgrMx, delReason, user);
		}

	}

	public MyPage<Gr002YsgrMx> c(Gr002YsgrMx gr002YsgrMx) {
		int total = this.uG.findHasRuleOutCount(gr002YsgrMx);
		List data = null;
		if (total > 0) {
			data = this.uG.findHasRuleOut(gr002YsgrMx);
		}

		return new MyPage(gr002YsgrMx.getPage().intValue(), gr002YsgrMx.getSize().intValue(), total, data);
	}

	public void updateStateByGr2Relid(String gr2Relid, Integer state) {
		this.uG.updateStateByGr2Relid(gr2Relid, state);
	}

	@SqlLog(p = "感染预警--感染预警排除")
	public void a(Gr002YsgrMx gr002YsgrMx, Integer excludeType) {
		String attrStr = "operator,remark,lastoperDate,excludeName,testOrderNos";
		gr002YsgrMx.setLastoperDate(new Date());
		if (excludeType == null) {
			attrStr = attrStr + ",state";
			gr002YsgrMx.setState(Integer.valueOf(3));
		} else {
			if (excludeType.intValue() != 1 && excludeType.intValue() != 2) {
				attrStr = attrStr + ",infectTypeId";
				gr002YsgrMx.setInfectTypeId(Integer.valueOf(2));
			} else {
				attrStr = attrStr + ",state";
				gr002YsgrMx.setState(Integer.valueOf(3));
			}

			switch (excludeType.intValue()) {
				case 1 :
					gr002YsgrMx.setExcludeName("定植菌");
					break;
				case 2 :
					gr002YsgrMx.setExcludeName("非感染");
					break;
				case 3 :
					gr002YsgrMx.setExcludeName("不确定");
			}
		}

		List updateAttrs = Arrays.asList(attrStr.split(","));
		this.updateSpecified(gr002YsgrMx, updateAttrs);
	}

	public List<Gr002YsgrMx> findCasesReported(String zyid) {
		List gr002List = this.uG.findCasesReported(zyid);
		Iterator arg3 = gr002List.iterator();

		while (arg3.hasNext()) {
			Gr002YsgrMx gr002 = (Gr002YsgrMx) arg3.next();
			Doctor doctor = this.f.get(gr002.getReportDrId());
			AcAccount account = this.uJ.b(gr002.getReportDrId());
			if (doctor != null) {
				gr002.setReportTypeName("医生上报");
			} else if (account != null) {
				gr002.setReportTypeName("院感上报");
			} else {
				gr002.setReportTypeName("系统预警");
			}
		}

		return gr002List;
	}

	public Gr002YsgrMx getGr002YsgrMx(String regId) {
		return this.uG.getGr002YsgrMx(regId);
	}

	public Gr002YsgrMx getByGr2Relid(String gr2Relid) {
		return this.uG.getByGr2Relid(gr2Relid);
	}

	public void delByGr2Relid(String gr2Relid) {
		this.uG.delByGr2Relid(gr2Relid);
	}

	public void updGr2RelidNull(String gr2Relid) {
		this.uG.updGr2RelidNull(gr2Relid);
	}

	public Gr002YsgrMx findGr002(Gr002YsgrMx gr002YsgrMx) {
		return this.uG.findGr002(gr002YsgrMx);
	}

	@SqlLog(p = "感染预警--感染预警患者树")
	public List<TreeEntity> a(Gr002YsgrMx gr002YsgrMx, String id) {
		String qzValue = this.cs.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		gr002YsgrMx.setQzValue(qzValue);
		if (StringUtils.isNotBlank(id)) {
			gr002YsgrMx.setDeptId(id);
			return this.uG.findAccordInfection(gr002YsgrMx);
		} else {
			return this.uG.findAccordCount(gr002YsgrMx);
		}
	}

	@SqlLog(p = "感染预警--感染预警患者数量")
	public Gr002YsgrMx b(Gr002YsgrMx gr002YsgrMx, String type) {
		String qzValue = this.cs.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		gr002YsgrMx.setQzValue(qzValue);
		return "favorit".equals(type)
				? this.uG.findInAndOutFavoritCount(gr002YsgrMx)
				: this.uG.findInAndOutInfectCount(gr002YsgrMx);
	}

	@SqlLog(p = "感染预警--感染预警患者树(收藏)")
	public List<TreeEntity> c(Gr002YsgrMx gr002YsgrMx, String id) {
		String qzValue = this.cs.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		gr002YsgrMx.setQzValue(qzValue);
		if (StringUtils.isNotBlank(id)) {
			gr002YsgrMx.setDeptId(id);
			return this.uG.findFavorit(gr002YsgrMx);
		} else {
			return this.uG.findFavoritCount(gr002YsgrMx);
		}
	}

	public void updSameInfectCode(String refid, String zyid, List<String> infectCodeList) {
		this.uG.updSameInfectCode(refid, zyid, infectCodeList);
	}

	public List<Gr002YsgrMx> workloadStatistics(Gr002YsgrMx gr002YsgrMx) {
		return this.uG.workloadStatistics(gr002YsgrMx);
	}

	public List<Gr002YsgrMx> workloadDetail(Gr002YsgrMx gr002YsgrMx) {
		return this.uG.workloadDetail(gr002YsgrMx);
	}

	public void a(HttpServletResponse response, Gr002YsgrMx gr002YsgrMx) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[][] tableTitle = new String[][]{{"人员", "审卡例次数", "预警处理例次数", "", "", ""},
				{"", "", "确认院感", "定植菌", "列为社感", "排除"}};
		List list = this.uG.workloadStatistics(gr002YsgrMx);
		String[][] tableData = new String[list.size()][];

		for (int marginColumns = 0; marginColumns < list.size(); ++marginColumns) {
			String[] wb = new String[]{((Gr002YsgrMx) list.get(marginColumns)).getPcr(),
					((Gr002YsgrMx) list.get(marginColumns)).getClzrs(),
					((Gr002YsgrMx) list.get(marginColumns)).getYgyqr(),
					((Gr002YsgrMx) list.get(marginColumns)).getDzj(), ((Gr002YsgrMx) list.get(marginColumns)).getLwsg(),
					((Gr002YsgrMx) list.get(marginColumns)).getPc()};
			tableData[marginColumns] = wb;
		}

		int[][] arg13 = new int[][]{{0, 1, 0, 0}, {0, 1, 1, 1}, {0, 0, 2, 5}};
		HSSFWorkbook arg14 = g.a("感染病例工作量统计", tableTitle, tableData, arg13);
		HSSFSheet sheet = arg14.getSheet("数据");
		HSSFRow row = sheet.getRow(1);
		row.createCell(0).setCellValue("处理时间");
		row.createCell(1).setCellValue(
				gr002YsgrMx.getQueryStartDate() == null ? "" : sdf.format(gr002YsgrMx.getQueryStartDate()));
		row.createCell(2)
				.setCellValue(gr002YsgrMx.getQueryEndDate() == null ? "" : sdf.format(gr002YsgrMx.getQueryEndDate()));

		try {
			String e = URLEncoder.encode("感染病例工作量统计", "UTF-8");
			ServletOutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + e + ".xls");
			response.setContentType("application/msexcel");
			arg14.write(output);
			output.close();
		} catch (IOException arg12) {
			arg12.printStackTrace();
		}

	}

	public void deleteByZyidState(String zyid) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.uG.deleteByZyidState(zyid, yjVersion);
	}

	public List<Gr002YsgrMx> p(String zyid, String infectCode, String reportType) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.uG.findByZyidInfectCode(zyid, infectCode, yjVersion, reportType);
	}

	public List<Gr002YsgrMx> findByZyid(String zyid) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.uG.findByZyid(zyid, yjVersion);
	}

	public List<Map<String, String>> findLiveInfect(String startDate, String endDate) {
		return this.uG.findLiveInfect(startDate, endDate);
	}
}