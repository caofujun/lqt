package com.nis.cdc.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.dao.CtgBk001CrbafpcardDao;
import com.nis.cdc.dao.CtgBk001CrbdiseaseDao;
import com.nis.cdc.dao.CtgBk001CrbhbvcardDao;
import com.nis.cdc.dao.CtgBk001CrbmasterDao;
import com.nis.cdc.dao.CtgBk001CrbstdcardDao;
import com.nis.cdc.dao.CtgBk001ZzdDao;
import com.nis.cdc.entity.CtgBk001Crbafpcard;
import com.nis.cdc.entity.CtgBk001Crbdisease;
import com.nis.cdc.entity.CtgBk001Crbhbvcard;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk001Crbstdcard;
import com.nis.cdc.entity.CtgBk001Zzd;
import com.nis.cdc.service.CtgBk001CrbmasterService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.comm.utils.ah;
import com.nis.comm.utils.f;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.param.service.SysParamService;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
public class CtgBk001CrbmasterServiceImpl implements CtgBk001CrbmasterService {
	private static final Logger c = Logger.getLogger(CtgBk001CrbmasterServiceImpl.class);
	@Autowired
	private CtgBk001CrbmasterDao dM;
	@Autowired
	private CtgBk001CrbdiseaseDao dK;
	@Autowired
	private CtgBk001CrbafpcardDao dJ;
	@Autowired
	private CtgBk001CrbhbvcardDao dL;
	@Autowired
	private CtgBk001CrbstdcardDao dN;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private ApplicationContext dO;
	@Autowired
	private SysParamService j;
	@Autowired
	private CtgBk001ZzdDao dP;

	public void save(CtgBk001Crbmaster ctgBk001Crbmaster) {
		this.dM.save(ctgBk001Crbmaster);
	}

	public void delete(String masterid) {
		this.dM.delete(masterid);
	}

	public void update(CtgBk001Crbmaster ctgBk001Crbmaster) {
		this.dM.update(ctgBk001Crbmaster);
	}

	public CtgBk001Crbmaster get(String masterid) {
		return this.dM.get(masterid);
	}

	public MyPage<CtgBk001Crbmaster> b(CtgBk001Crbmaster ctgBk001Crbmaster) {
		int total = this.dM.findCtgBk001CrbmasterCount(ctgBk001Crbmaster);
		List data = null;
		if (total > 0) {
			data = this.dM.findCtgBk001Crbmaster(ctgBk001Crbmaster);
		}

		return new MyPage(ctgBk001Crbmaster.getPage().intValue(), ctgBk001Crbmaster.getSize().intValue(), total, data);
	}

	public List<CtgBk001Crbmaster> getAll() {
		return this.dM.getAll();
	}

	public List<CtgBk001Crbmaster> getMZBK(CtgBk001Crbmaster ctgBk001Crbmaster) {
		return this.dM.getMZBK(ctgBk001Crbmaster);
	}

	public List<CtgBk001Crbmaster> getZYBK(CtgBk001Crbmaster ctgBk001Crbmaster) {
		return this.dM.getZYBK(ctgBk001Crbmaster);
	}

	public List<CtgBk001Crbmaster> getAllBK(String id, String cardType) {
		return this.dM.getAllBK(id, cardType);
	}

	@Transactional
	public Result<String> a(CtgBk001Crbmaster ctgBk001Crbmaster, AcAccount account) {
		Result r = new Result();
		StringBuffer allDN = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean isUpdate = false;

		try {
			if (ab.aM(ctgBk001Crbmaster.getMasterid())) {
				String e = af.getUUID32();
				ctgBk001Crbmaster.setMasterid(e);
				ctgBk001Crbmaster.setCardid("C" + sdf.format(new Date()));
				if (ctgBk001Crbmaster.getFilldate() == null) {
					ctgBk001Crbmaster.setFilldate(new Date());
				}

				this.dM.save(ctgBk001Crbmaster);
				isUpdate = false;
			} else {
				this.dM.update(ctgBk001Crbmaster);
				isUpdate = true;
			}

			List e1 = ctgBk001Crbmaster.getDiseaseList();
			this.dK.delete(ctgBk001Crbmaster.getMasterid());
			String yg;
			if (e1 != null) {
				Iterator hiv = e1.iterator();

				while (hiv.hasNext()) {
					CtgBk001Crbdisease isShowfjhZzd = (CtgBk001Crbdisease) hiv.next();
					if (isShowfjhZzd.getDiseaseid() != null) {
						allDN.append(" " + isShowfjhZzd.getDiseasename());
						yg = af.getUUID32();
						isShowfjhZzd.setMasterid(ctgBk001Crbmaster.getMasterid());
						isShowfjhZzd.setSubid(yg);
						isShowfjhZzd.setFilldate(new Date());
						isShowfjhZzd.setReportdeptid(ctgBk001Crbmaster.getReportdeptid());
						isShowfjhZzd.setReportdeptname(ctgBk001Crbmaster.getReportdeptname());
						isShowfjhZzd.setReportdoctorid(ctgBk001Crbmaster.getReportdoctorid());
						isShowfjhZzd.setReportdoctorname(ctgBk001Crbmaster.getReportdoctorname());
						isShowfjhZzd.setFlag(new Long(0L));
						this.dK.save(isShowfjhZzd);
					}
				}
			}

			this.dP.delete(ctgBk001Crbmaster.getMasterid());
			String isShowfjhZzd1 = this.j.findByParamCode(Param.NIS_CRB_FJHZZD);
			if ("0".equals(isShowfjhZzd1)) {
				CtgBk001Zzd hiv1 = ctgBk001Crbmaster.getFjh();
				if (hiv1 != null) {
					yg = af.getUUID32();
					hiv1.setMasterid(ctgBk001Crbmaster.getMasterid());
					hiv1.setSubid(yg);
					this.dP.save(hiv1);
				}
			}

			this.dN.delete(ctgBk001Crbmaster.getMasterid());
			CtgBk001Crbstdcard hiv2 = ctgBk001Crbmaster.getHiv();
			if (hiv2 != null) {
				yg = af.getUUID32();
				hiv2.setMasterid(ctgBk001Crbmaster.getMasterid());
				hiv2.setSubid(yg);
				hiv2.setContactflag("0");
				hiv2.setReportdoctorid(ctgBk001Crbmaster.getReportdoctorid());
				hiv2.setReportdoctorname(ctgBk001Crbmaster.getReportdoctorname());
				hiv2.setReportdate(new Date());
				this.dN.save(hiv2);
			}

			this.dL.delete(ctgBk001Crbmaster.getMasterid());
			CtgBk001Crbhbvcard yg1 = ctgBk001Crbmaster.getYg();
			if (yg1 != null) {
				String afp = af.getUUID32();
				yg1.setSubid(afp);
				yg1.setMasterid(ctgBk001Crbmaster.getMasterid());
				this.dL.save(yg1);
			}

			this.dJ.delete(ctgBk001Crbmaster.getMasterid());
			CtgBk001Crbafpcard afp1 = ctgBk001Crbmaster.getAfp();
			String gkkCode;
			if (afp1 != null) {
				gkkCode = af.getUUID32();
				afp1.setSubid(gkkCode);
				this.dJ.save(afp1);
			}

			gkkCode = this.j.findByParamCode(Param.NIS_GK_DEPTID);
			String content = ctgBk001Crbmaster.getReportdoctorname() + "（" + ctgBk001Crbmaster.getReportdeptname()
					+ "）上报了 传染病报卡 ( " + allDN.toString() + " ) ";
			this.cV.a(ctgBk001Crbmaster.getZyid(), ctgBk001Crbmaster.getMzid(), account.getUsername(),
					account.getRealname(), content, (String[]) null, new String[]{gkkCode}, al.ju.getValue(),
					ctgBk001Crbmaster.getMasterid());
			r.setResult("success");
			r.setMsg("保存成功！");
		} catch (Exception arg14) {
			arg14.printStackTrace();
			c.error("保存数据失败！", arg14);
			r.setResult("error");
			r.setExtraValue(arg14.getMessage());
			r.setMsg("保存失败！");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return r;
	}

	public List<CtgBk001Crbmaster> findCardsForAdmin(CtgBk001Crbmaster ctgBk001Crbmaster) {
		return this.dM.findCardsForAdmin(ctgBk001Crbmaster);
	}

	public void updateDelReason(CtgBk001Crbmaster ctgBk001Crbmaster) {
		this.dM.updateDelReason(ctgBk001Crbmaster);
	}

	public MyPage<CtgBk001Crbmaster> c(CtgBk001Crbmaster ctgBk001Crbmaster) {
		int count = 0;
		List list = null;
		if (!ab.aM(ctgBk001Crbmaster.getCardType())) {
			count = this.dM.reportHistoryQueryCount(ctgBk001Crbmaster);
			if (count > 0) {
				list = this.dM.reportHistoryQuery(ctgBk001Crbmaster);
			}
		}

		return new MyPage(ctgBk001Crbmaster.getPage().intValue(), ctgBk001Crbmaster.getSize().intValue(), count, list);
	}

	public Result<String> c(String searchKey, String startTime, String endTime) {
		Result r = new Result();

		try {
			SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd");
			Date st = e.parse(startTime);
			Date et = e.parse(endTime);
			DataSource dataSource = (DataSource) this.dO.getBean("dataSource");
			Connection conn = dataSource.getConnection();
			System.err.println("准备调用存储过程“P_GetMZPatientByID”");
			System.err.println("相关条件   id=" + searchKey + " . st=" + startTime + "  . et=" + endTime);
			CallableStatement c = conn.prepareCall("{call P_GETMZPATIENTBYID(?,?,?,?,?)}");
			c.setString(1, searchKey);
			c.setTimestamp(2, new Timestamp(st.getTime()));
			c.setTimestamp(3, new Timestamp(et.getTime()));
			c.registerOutParameter(4, 4);
			c.registerOutParameter(5, 12);
			c.execute();
			int exeCode = c.getInt(4);
			String errMsg = c.getString(5);
			System.err.println("存储过程返回：" + exeCode);
			if (exeCode == -1) {
				r.setResult("error");
				r.setExtraValue(errMsg);
			} else if (exeCode == 0) {
				r.setResult("success");
				r.setMsg("未获取到符合条件的病人信息！");
			} else if (exeCode == 1) {
				r.setResult("success");
			}

			c.close();
			conn.close();
		} catch (Exception arg12) {
			arg12.printStackTrace();
			CtgBk001CrbmasterServiceImpl.c.error("获取病人信息失败！", arg12);
			r.setResult("error");
			r.setMsg(arg12.getMessage());
		}

		return r;
	}

	public Result<String> d(String searchKey, String startTime, String endTime) {
		Result r = new Result();

		try {
			SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd");
			Date st = e.parse(startTime);
			Date et = e.parse(endTime);
			DataSource dataSource = (DataSource) this.dO.getBean("dataSource");
			Connection conn = dataSource.getConnection();
			CallableStatement c = conn.prepareCall("{call P_GETZYPATIENTBYID(?,?,?,?,?)}");
			c.setString(1, searchKey);
			c.setTimestamp(2, new Timestamp(st.getTime()));
			c.setTimestamp(3, new Timestamp(et.getTime()));
			c.registerOutParameter(4, 4);
			c.registerOutParameter(5, 12);
			c.execute();
			int exeCode = c.getInt(4);
			String errMsg = c.getString(5);
			if (exeCode == -1) {
				r.setResult("error");
				r.setExtraValue(errMsg);
			} else if (exeCode == 0) {
				r.setResult("success");
				r.setMsg("未获取到符合条件的病人信息！");
			} else if (exeCode == 1) {
				r.setResult("success");
			}

			c.close();
			conn.close();
		} catch (Exception arg12) {
			arg12.printStackTrace();
			CtgBk001CrbmasterServiceImpl.c.error("获取病人信息失败！", arg12);
			r.setResult("error");
			r.setMsg(arg12.getMessage());
		}

		return r;
	}

	public List<Map<String, Object>> classesDataForChart(String currentDate) {
		return this.dM.classesDataForChart(currentDate);
	}

	public List<Map<String, Object>> unAuditCards() {
		return this.dM.unAuditCards();
	}

	public List<Map<String, Object>> diseaseTypeDataForChart(String currentDate) {
		return this.dM.diseaseTypeDataForChart(currentDate);
	}

	public List<Map<String, Object>> areaDataForChart(String currentDate) {
		return this.dM.areaDataForChart(currentDate);
	}

	public List<Map<String, Object>> reportDataForChart(String currentDate) {
		return this.dM.reportDataForChart(currentDate);
	}

	public List<Map<String, Object>> yearDataForChart(String startMonth, String endMonth) {
		return this.dM.yearDataForChart(startMonth, endMonth);
	}

	public List<Map<String, Object>> curDayYJBL() {
		return this.dM.curDayYJBL();
	}

	public void updatePrintFlag(String masterid) {
		this.dM.updatePrintFlag(masterid);
	}

	public void a(HttpServletResponse response, CtgBk001Crbmaster ctgBk001Crbmaster) {
		List list = this.dM.findExportCards(ctgBk001Crbmaster);
		if (ab.isNotEmpty(ctgBk001Crbmaster.getSubid())) {
			this.dK.updateZBFlag(ctgBk001Crbmaster.getSubid());
		} else {
			StringBuffer xml = new StringBuffer();
			Iterator root = list.iterator();

			while (root.hasNext()) {
				CtgBk001Crbmaster doc = (CtgBk001Crbmaster) root.next();
				if (ab.isNotEmpty(doc.getSubid())) {
					xml.append("\'" + doc.getSubid() + "\',");
				}
			}

			this.dK.updateZBFlag(xml.substring(0, xml.length() - 1));
		}

		String xml1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><data></data>";
		Document doc1 = ah.bh(xml1);
		Element root1 = doc1.getRootElement();
		Element element = (Element) root1.selectNodes("//data").get(0);
		Element row = null;
		Iterator e = list.iterator();

		while (e.hasNext()) {
			CtgBk001Crbmaster output = (CtgBk001Crbmaster) e.next();
			row = element.addElement("row");
			row.addAttribute("PATIENT_NAME", output.getPatientName());
			row.addAttribute("PARENT_NAME", output.getParentName());
			row.addAttribute("ID", output.getIdcard());
			row.addAttribute("SEX1", output.getSexid());
			row.addAttribute("SEX1_CODEVALUE", output.getSexname());
			row.addAttribute("BIRTHDAY_DATE",
					output.getBirtyday() == null ? "" : f.formatDate(output.getBirtyday(), "yyyy-MM-dd HH:mm:ss"));
			row.addAttribute("age_1", "" + output.getAge());
			row.addAttribute("age_unit", output.getAgeunit());
			row.addAttribute("UNIT", output.getUnit());
			row.addAttribute("telp", output.getTelp());
			row.addAttribute("AREATYPE", output.getAreatypeid());
			row.addAttribute("AREATYPE_CODEVALUE_addr", output.getAreatypename());
			row.addAttribute("ADDRCODE", output.getAddrcode());
			row.addAttribute("ADDR", output.getAddr());
			row.addAttribute("GROUP_ID", output.getProfessionid());
			row.addAttribute("GROUP_ID_CODEVALUE", output.getProfessionname());
			row.addAttribute("CASETYPE", output.getCasetypeid());
			row.addAttribute("CASETYPE_CODEVALUE", output.getCasetypename());
			row.addAttribute("CASETYPE2", output.getCasetypeid2());
			row.addAttribute("CASETYPE2_CODEVALUE", output.getCasetypename2());
			row.addAttribute("START_DATE",
					output.getStartdate() == null ? "" : f.formatDate(output.getStartdate(), "yyyy-MM-dd HH:mm:ss"));
			row.addAttribute("DIAGNOSEDATE",
					output.getDiagnosedate() == null
							? ""
							: f.formatDate(output.getDiagnosedate(), "yyyy-MM-dd HH:mm:ss"));
			row.addAttribute("DEADDATE",
					output.getDeaddate() == null ? "" : f.formatDate(output.getDeaddate(), "yyyy-MM-dd HH:mm:ss"));
			row.addAttribute("DISEASE_ID1", output.getDiseaseId());
			row.addAttribute("DISEASE_ID1_CODEVALUE", output.getDiseaseName());
			row.addAttribute("INPUTDOCTOR", output.getReportdoctorname());
			row.addAttribute("FILLTIME",
					output.getFilldate() == null ? "" : f.formatDate(output.getFilldate(), "yyyy-MM-dd HH:mm:ss"));
			row.addAttribute("CLINICAL_DIAGNOSIS1", output.getLabresult());
			row.addAttribute("CONTACTFLAG", output.getContactflag() == null ? "" : output.getContactflag());
			row.addAttribute("HBsAgTime", output.getHbsAgTime());
			row.addAttribute("HBsAgTime_CODEVALUE_New", output.getHbsAgTimeName());
			row.addAttribute("HBVDATE_TIME",
					output.getHbvDateTime() == null
							? ""
							: f.formatDate(output.getHbvDateTime(), "yyyy-MM-dd HH:mm:ss"));
			row.addAttribute("ALT", output.getAlt());
			row.addAttribute("HBC", output.getHbc());
			row.addAttribute("HBC_CODEVALUE_new", output.getHbcName());
			row.addAttribute("HBV", output.getHbv());
			row.addAttribute("HBV_CODEVALUE_new", output.getHbvName());
			row.addAttribute("hbv_7_code", output.getHbv7Code());
			row.addAttribute("hbv_7_Value_new", output.getHbv7CodeName());
			row.addAttribute("MARRIAGE_1", output.getMarriageid());
			row.addAttribute("MARRIAGE_CODEVALUE_1", output.getMarriageName());
			row.addAttribute("NATION", output.getNationId());
			row.addAttribute("NATION_CODEVALUE_1", output.getNationName());
			row.addAttribute("EDUCATIONBACK", output.getEducationId());
			row.addAttribute("EDUCATIONBACK_CODEVALUE", output.getEducationName());
			row.addAttribute("addrcode_2", output.getRegaddrCode());
			row.addAttribute("AREATYPE_2", output.getRegaddrtypeId());
			row.addAttribute("ADDR_2", output.getRegaddr());
			row.addAttribute("INTOUCHHIS", output.getIntouchhis());
			row.addAttribute("INTOUCHHIS_CODEVALUE", output.getIntouchhisName());
			row.addAttribute("SMCOUNT", output.getUrningcount());
			row.addAttribute("INJECTCOUNT", output.getInjectcount());
			row.addAttribute("NONWEBCOUNT", output.getNonWebCount());
			row.addAttribute("Business", output.getBusiness());
			row.addAttribute("TOUOTHERS", output.getContacthistoryother());
			row.addAttribute("VENEREALHISTORY", output.getStdhistoryId());
			row.addAttribute("VENEREALHISTORY_CODEVALUE", output.getStdhistoryName());
			row.addAttribute("INFECTROUTE", output.getInfectionId());
			row.addAttribute("INFECTROUTE_CODEVALUE", output.getInfectionName());
			row.addAttribute("INFECOTHERS", output.getInfectionother());
			row.addAttribute("SAMPLEORIGIN", output.getSamplesourceId());
			row.addAttribute("SAMPLEORIGIN_CODEVALUE", output.getSamplesourceName());
			row.addAttribute("SRCOTHERS", output.getSrcothers());
			row.addAttribute("LABCONCLUSION", output.getLabconclusionId());
			row.addAttribute("LABCONCLUSION_CODEVALUE", output.getLabconclusionName());
			row.addAttribute("AFFIRMMASDATE",
					output.getAffirmdate() == null ? "" : f.formatDate(output.getAffirmdate(), "yyyy-MM-dd HH:mm:ss"));
			row.addAttribute("AFFIRMMASORG", output.getAffirmunit());
			row.addAttribute("DT_DIAGNOSE",
					output.getDiagnosedt() == null ? "" : f.formatDate(output.getDiagnosedt(), "yyyy-MM-dd HH:mm:ss"));
			row.addAttribute("notes", output.getNotes());
			row.addAttribute("sinfect", output.getSinfect());
		}

		try {
			String e1 = URLEncoder.encode("导出文件", "UTF-8");
			ServletOutputStream output1 = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + e1 + ".cds");
			response.setContentType("application/xml");
			StringWriter stringWriter = new StringWriter();
			OutputFormat xmlFormat = new OutputFormat("\t", true);
			xmlFormat.setEncoding("UTF-8");
			XMLWriter xmlWriter = new XMLWriter(stringWriter, xmlFormat);
			xmlWriter.write(doc1);
			xmlWriter.close();
			String xml2 = stringWriter.toString().substring(39);
			xml2 = "<xml version=\"1.0\" encoding=\"utf-8\">" + xml2 + "</xml>";
			output1.write(xml2.getBytes("utf-8"));
			output1.close();
		} catch (IOException arg14) {
			arg14.printStackTrace();
		}

	}
}