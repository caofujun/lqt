package com.nis.zg.controller;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.service.AnalysisBcService;
import com.nis.analysis.service.AnalysisCheckoutService;
import com.nis.analysis.service.AnalysisKjywService;
import com.nis.analysis.service.AnalysisModelService;
import com.nis.analysis.service.AnalysisPacsService;
import com.nis.analysis.service.AnalysisResistantService;
import com.nis.analysis.service.AnalysisTwService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.ae;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.icu.service.Gm004JcmxService;
import com.nis.param.service.SysParamService;
import com.nis.zg.controller.ZgController.1;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ZgController extends BaseController {
	@Autowired
	private AnalysisBcService cL;
	@Autowired
	private AnalysisPacsService cK;
	@Autowired
	private AnalysisTwService cM;
	@Autowired
	private AnalysisModelService D;
	@Autowired
	private SysJudgeLogService J;
	@Autowired
	private AnalysisKjywService H;
	@Autowired
	private SysParamService j;
	@Autowired
	private AnalysisCheckoutService G;
	@Autowired
	private AnalysisResistantService I;
	@Autowired
	private Gm004JcmxService bP;

	@RequestMapping({"/zg/f_view/yjList"})
	public String av(HttpServletRequest request, ModelMap modelMap) {
		return "zg/yjList";
	}

	@RequestMapping({"/zg/f_view/flashDown"})
	public String aC(HttpServletRequest request, ModelMap modelMap) {
		return "comm/flashDown";
	}

	@RequestMapping({"/zg/f_view/judgeYj"})
	public String aD(HttpServletRequest request, ModelMap modelMap) {
		return "zg/judgeYj";
	}

	@RequestMapping({"/zg/f_json/ajaxJudgeYj"})
   public void aM(HttpServletRequest request, HttpServletResponse response, String tables) {
      Result result = new Result();
      String id = z.a(bg.my);
      SysJudgeLog sysJudgeLog = new SysJudgeLog();
      sysJudgeLog.setId(id);
      sysJudgeLog.setStartTime(new Date());
      sysJudgeLog.setJudgeCode(tables);
      sysJudgeLog.setStatus("0");
      this.J.save(sysJudgeLog);
      result.setData(id);
      (new Thread(new 1(this, tables, id))).start();
      result.setResult("success");
      this.a(response, result);
   }

	@RequestMapping({"/zg/f_json/judgeType"})
	public void Y(HttpServletRequest request, HttpServletResponse response) {
		List kvList = ae.getList();
		this.a(response, kvList);
	}

	@RequestMapping({"/zg/f_json/judgeLogList"})
	public void aN(HttpServletRequest request, HttpServletResponse response, String id) {
		List sysJudgeLogList = this.J.o(id);
		this.a(response, sysJudgeLogList);
	}

	@RequestMapping({"/zg/f_view/judgeLogIndex"})
	public String aE(HttpServletRequest request, ModelMap modelMap) {
		return "zg/judgeLogList";
	}

	@RequestMapping({"/zg/f_json/judgeLogPage"})
	public void a(HttpServletRequest request, HttpServletResponse response, SysJudgeLog sysJudgeLog) {
		MyPage sysJudgeLogPage = this.J.b(sysJudgeLog);
		this.a(response, sysJudgeLogPage);
	}
}