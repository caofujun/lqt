package com.nis.patient.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.f;
import com.nis.log.service.SysLogService;
import com.nis.param.service.SysParamService;
import com.nis.patient.service.InterfaceMonitorMsgService;
import com.nis.patient.service.InterfaceMonitorService;
import com.nis.patient.service.St002ZdxxbService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.patient.service.St006TwxxService;
import com.nis.patient.service.St008BcjlService;
import com.nis.patient.service.St009SjbbService;
import com.nis.patient.service.St010JcbytService;
import com.nis.patient.service.St011SyjgbService;
import com.nis.patient.service.St020ClinicPatientsService;

@Controller
public class PatientTaskController extends BaseController {
	private static final Logger logger = Logger.getLogger(PatientTaskController.class);
	@Autowired
	private InterfaceMonitorService wq;
	@Autowired
	private InterfaceMonitorMsgService wr;
	@Autowired
	private St002ZdxxbService bv;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private St006TwxxService bN;
	@Autowired
	private St008BcjlService aP;
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private St010JcbytService bF;
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/data/f_view/waringList"})
	public String W(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("queryStartDate", f.formatDate(f.b(f.getCurDate(), -1)));
		modelMap.put("queryEndDate", f.formatDate(f.getCurDate()));
		return "st/dataWarningList";
	}

	@RequestMapping({"/data/f_json/findDataWarning"})
	@ResponseBody
	@SqlLog(p = "数据接口监控--数据接口异常列表")
	public void b(HttpServletRequest request, HttpServletResponse response, Date queryStartDate, Date queryEndDate,
			String bizType) {
		ArrayList dataWarningList = new ArrayList();
		String[] bizTypes = bizType.split(",");
		String[] arg10 = bizTypes;
		int arg9 = bizTypes.length;

		for (int arg8 = 0; arg8 < arg9; ++arg8) {
			String tmpBizType = arg10[arg8];
			if (!StringUtils.isBlank(tmpBizType)) {
				if ("KETTLE".equals(tmpBizType)) {
					dataWarningList.addAll(this.aV.findKettleWarning(queryStartDate, queryEndDate));
				} else if ("MONITOR".equals(tmpBizType)) {
					dataWarningList.addAll(this.aV.findMonitorWarning(queryStartDate, queryEndDate));
				} else if ("ST003_CRYXXB".equals(tmpBizType)) {
					dataWarningList.addAll(this.bg.findPatentCryxxbWarning(queryStartDate, queryEndDate));
				} else if ("ST002_ZDXXB".equals(tmpBizType)) {
					dataWarningList.addAll(this.bv.findPatentZdxxbWarning(queryStartDate, queryEndDate));
				} else if ("ST004_YZXXB".equals(tmpBizType)) {
					dataWarningList.addAll(this.bu.findPatentYzxxbWarning(queryStartDate, queryEndDate));
				} else if ("ST005_SSXXB".equals(tmpBizType)) {
					dataWarningList.addAll(this.bO.findPatentSsxxbWarning(queryStartDate, queryEndDate));
				} else if ("ST006_TWXX".equals(tmpBizType)) {
					dataWarningList.addAll(this.bN.findPatentTwxxWarning(queryStartDate, queryEndDate));
				} else if ("ST008_BCJL".equals(tmpBizType)) {
					dataWarningList.addAll(this.aP.findPatentBcjlWarning(queryStartDate, queryEndDate));
				} else if ("ST009_SJBB".equals(tmpBizType)) {
					dataWarningList.addAll(this.bE.findPatentSjbbWarning(queryStartDate, queryEndDate));
				} else if ("ST010_JCBYT".equals(tmpBizType)) {
					dataWarningList.addAll(this.bF.findPatentJcbytWarning(queryStartDate, queryEndDate));
				} else if ("ST011_SYJGB".equals(tmpBizType)) {
					dataWarningList.addAll(this.bH.findPatentSyjgbWarning(queryStartDate, queryEndDate));
				} else if ("ST020_CLINIC_PATIENTS".equals(tmpBizType)) {
					dataWarningList.addAll(this.bh.findPatentClinicWarning(queryStartDate, queryEndDate));
				}
			}
		}

		this.b(response, dataWarningList);
	}

	@RequestMapping({"/f_task/patient/interface"})
   public void S(HttpServletRequest request, HttpServletResponse response) {
      Result result = new Result();
      logger.info("患者数据接口监控开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
      //TODO: (new Thread(new 1(this))).start();
      this.a(response, result);
   }
}