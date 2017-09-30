package com.nis.comm.init;

import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.constants.b;
import com.nis.comm.enums.Param;
import com.nis.comm.service.impl.LicenseServiceImpl;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.ag;
import com.nis.comm.utils.f;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.organization.service.UnitService;
import com.nis.param.service.SysParamService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Init extends HttpServlet {
	private static final long serialVersionUID = -2274726206362496315L;

	public void init(ServletConfig config) throws ServletException {
		ab.setNisHttpUrl("http://" + ag.getLocalIP() + ":8080");
		System.out.println(ag.getLocalIP());
		System.out.println(ab.getNisHttpUrl());
		b.es = config.getServletContext().getContextPath();
		config.getServletContext().setAttribute("webroot", b.es);
		String version = String.format("?version=%s", new Object[]{f.c(new Date(), "yyyyMMddHHmm")});
		SysParamService sysParamService = (SysParamService) AppContextUtil.getInstance().getBean(SysParamService.class);
		config.getServletContext().setAttribute("IEflashPlayerUrl", sysParamService
				.findByParamCode(Param.NIS_IE_FLASHPLAYER_URL, (String) null, (String) null, (String) null));
		config.getServletContext().setAttribute("ChromeflashPlayerUrl", sysParamService
				.findByParamCode(Param.NIS_CHROME_FLASHPLAYER_URL, (String) null, (String) null, (String) null));
		config.getServletContext().setAttribute("nisHttpUrl",
				sysParamService.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null));
		config.getServletContext().setAttribute("nisMsgToken",
				sysParamService.findByParamCode(Param.NIS_MSG_TOKEN, (String) null, (String) null, (String) null));
		config.getServletContext().setAttribute("nisGkDeptid",
				sysParamService.findByParamCode(Param.NIS_GK_DEPTID, (String) null, (String) null, (String) null));
		config.getServletContext().setAttribute("nisMsgUrl",
				sysParamService.findByParamCode(Param.NIS_MSG_URL, (String) null, (String) null, (String) null));
		config.getServletContext().setAttribute("nisMsgOcxUrl",
				sysParamService.findByParamCode(Param.NIS_MSG_OCX_URL, (String) null, (String) null, (String) null));
		config.getServletContext().setAttribute("nisCzscMUrl",
				sysParamService.findByParamCode(Param.NIS_CZSC_M_URL, (String) null, (String) null, (String) null));
		config.getServletContext().setAttribute("nisCzscCUrl",
				sysParamService.findByParamCode(Param.NIS_CZSC_C_URL, (String) null, (String) null, (String) null));
		config.getServletContext().setAttribute("themeStyle",
				sysParamService.findByParamCode(Param.NIS_SYSTEM_THEME_STYLE));
		config.getServletContext().setAttribute("version", version);
		config.getServletContext().setAttribute("reportUrl", sysParamService.findByParamCode(Param.NIS_REPORT_URL));
		config.getServletContext().setAttribute("imcIsShow", sysParamService.findByParamCode(Param.NIS_IMC_IS_SHOW));
		config.getServletContext().setAttribute("isPatientList",
				sysParamService.findByParamCode(Param.NIS_SY_PATIENT_LIST));
		config.getServletContext().setAttribute("defaultPwd",
				sysParamService.findByParamCode(Param.NIS_DOC_DEFAULT_PWD));
		DepService depService = (DepService) AppContextUtil.getInstance().getBean(DepService.class);
		Dep serachDep = new Dep();
		serachDep.setIfmzoffice(new Long(1L));
		serachDep.setFlag(new Long(1L));
		if (depService.c(serachDep).intValue() > 0) {
			config.getServletContext().setAttribute("handDepFlag", "1");
		} else {
			config.getServletContext().setAttribute("handDepFlag", "0");
		}

		LicenseServiceImpl licenseService = new LicenseServiceImpl();
		boolean licenseFlag = licenseService.t();

		while (!licenseFlag) {
			;
		}

		try {
			SysDictService e = (SysDictService) AppContextUtil.getInstance().getBean(SysDictService.class);
			List cdcCardList = e.u("cdc_card_type", (String) null);
			String cdcScope = "";

			SysDict patientNoTitle;
			for (Iterator patientZyTitle = cdcCardList.iterator(); patientZyTitle
					.hasNext(); cdcScope = cdcScope + patientNoTitle.getDictCode() + ",") {
				patientNoTitle = (SysDict) patientZyTitle.next();
			}

			config.getServletContext().setAttribute("cdcScope", cdcScope);
			String patientNoTitle1 = sysParamService.findByParamCode(Param.NIS_PATIENT_NO_TITLE);
			config.getServletContext().setAttribute("patientNoTitle", patientNoTitle1);
			String patientZyTitle1 = sysParamService.findByParamCode(Param.NIS_PATIENT_ZY_TITLE);
			config.getServletContext().setAttribute("patientZyTitle", patientZyTitle1);
			String patientZyValue = sysParamService.findByParamCode(Param.NIS_PATIENT_ZY_VALUE);
			config.getServletContext().setAttribute("patientZyValue", patientZyValue);
			String browserUrl = sysParamService.findByParamCode(Param.NIS_BROWSER_DOWNLOAD_URL);
			config.getServletContext().setAttribute("browserUrl", browserUrl);
			config.getServletContext().setAttribute("browserIe8x86Url",
					sysParamService.findByParamCode(Param.NIS_BROWSER_IE8X86_DOWNLOAD_URL));
			config.getServletContext().setAttribute("browserIe8x64Url",
					sysParamService.findByParamCode(Param.NIS_BROWSER_IE8X64_DOWNLOAD_URL));
			String license = sysParamService.findByParamCode(Param.NIS_SYSTEM_HOSPTIAL_LICENSE);
			String licenseNo = EncryptUtils.V(license);
			b.hospName = licenseNo.split("@@")[0];
			config.getServletContext().setAttribute("hospName", b.hospName);
			if (licenseNo.split("@@").length > 2) {
				UnitService sysJudgeLogService = (UnitService) AppContextUtil.getInstance().getBean(UnitService.class);
				sysJudgeLogService.I(licenseNo.split("@@")[2], licenseNo.split("@@")[0]);
				config.getServletContext().setAttribute("systemScope", licenseNo.split("@@")[3]);
				if (licenseNo.split("@@").length > 4) {
					config.getServletContext().setAttribute("cdcScope", licenseNo.split("@@")[4]);
				}
			}

			SysJudgeLogService sysJudgeLogService1 = (SysJudgeLogService) AppContextUtil.getInstance()
					.getBean(SysJudgeLogService.class);
			sysJudgeLogService1.f();
		} catch (Exception arg17) {
			arg17.printStackTrace();
		}

	}
}