package com.nis.jk.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.ab;
import com.nis.comm.utils.f;
import com.nis.jk.controller.JkSyncController.1;
import com.nis.jk.controller.JkSyncController.2;
import com.nis.jk.entity.JkMessage;
import com.nis.jk.entity.JkSyncLog;
import com.nis.jk.service.JkMessageService;
import com.nis.jk.service.JkSyncService;
import com.nis.jk.utils.a;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JkSyncController extends BaseController {
	private static final Logger logger = Logger.getLogger(JkSyncController.class);
	@Autowired
	private JkSyncService sP;
	@Autowired
	private JkMessageService sQ;

	@RequestMapping({"/f_task/jk/syncJkData"})
   public void L(HttpServletRequest request, HttpServletResponse response, String tables) {
      Result result = new Result();
      logger.info("患者就诊信息中间库导入业务库开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
      String tabless = ab.getString();
      this.sP.by(tabless);
      (new Thread(new 1(this, tables))).start();
      this.a(response, result);
   }

	@RequestMapping({"/jk/f_view/syncList"})
	public String x(HttpServletRequest request, ModelMap modelMap) {
		return "jk/syncList";
	}

	@RequestMapping({"/jk/f_view/sync"})
	public String B(HttpServletRequest request, ModelMap modelMap, String tables) {
		modelMap.put("jkSyncInfo", this.sP.by(tables));
		return "jk/sync";
	}

	@RequestMapping({"/jk/f_view/syncLogList"})
	public String y(HttpServletRequest request, ModelMap modelMap) {
		return "jk/syncLogList";
	}

	@RequestMapping({"/jk/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "中间库数据同步记录")
	public void a(HttpServletRequest request, HttpServletResponse response, JkSyncLog jkSyncLog) {
		MyPage page = this.sP.a(jkSyncLog);
		this.b(response, page);
	}

	@RequestMapping({"/jk/f_json/logPageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, JkMessage jkMessage) {
		MyPage page = this.sQ.a(jkMessage);
		this.b(response, page);
	}

	@RequestMapping({"/jk/f_json/currentSyncJkDataInfo"})
	public void M(HttpServletRequest request, HttpServletResponse response, String tables) {
		List jkSyncLogList = this.sP.bz(tables);
		Collections.sort(jkSyncLogList, new a());
		MyPage page = new MyPage((new Integer(1)).intValue(), (new Integer(100)).intValue(), jkSyncLogList.size(),
				jkSyncLogList);
		this.b(response, page);
	}

	@RequestMapping({"/jk/f_json/syncJkData"})
   public void N(HttpServletRequest request, HttpServletResponse response, String tables) {
      Result result = new Result();
      (new Thread(new 2(this, tables))).start();
      this.a(response, result);
   }

	@RequestMapping({"/jk/f_json/stopSyncJkDataByTable"})
	public void O(HttpServletRequest request, HttpServletResponse response, String table) {
		Result result = new Result();
		this.sP.bA(table);
		this.a(response, result);
	}

	@RequestMapping({"/jk/f_json/initSyncJkDataInfo"})
	public void P(HttpServletRequest request, HttpServletResponse response, String tables) {
		Result result = new Result();
		result.setData(this.sP.by(tables));
		this.a(response, result);
	}
}