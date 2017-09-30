package com.nis.questions.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.bm;
import com.nis.comm.enums.n;
import com.nis.comm.utils.ab;
import com.nis.questions.entity.QsRepoOptions;
import com.nis.questions.entity.QsRepoTopic;
import com.nis.questions.service.QsRepoOptionsService;
import com.nis.questions.service.QsRepoTopicService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class QsRepoTopicController extends BaseController {
	private static final Logger c = Logger.getLogger(QsRepoTopicController.class);
	@Autowired
	private QsRepoTopicService xx;
	@Autowired
	private QsRepoOptionsService xy;

	@RequestMapping({"/qsRepoTopic/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("username", acAccount.getUsername());
		return "questions/qsRepoTopicList";
	}

	@RequestMapping({"/qsRepoTopic/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsRepoTopic qsRepoTopic) {
		AcAccount account = (AcAccount) this.b(request);
		qsRepoTopic.setTlevel(account.getDataScope().toString());
		qsRepoTopic.setUnitId(account.getUnitId());
		qsRepoTopic.setDepNo(account.getDepNo());
		if (account.getDataScope() == n.gj.getValue()) {
			qsRepoTopic.setTlevel(n.gk.getValue().toString());
		} else if (account.getDataScope() == n.gh.getValue()) {
			qsRepoTopic.setCreateUser(account.getUsername());
		} else if (account.getDataScope() == n.gm.getValue() && ab.isNotEmpty(account.getUnitId())) {
			qsRepoTopic.setTlevel(n.gk.getValue().toString());
		}

		MyPage page = this.xx.a(qsRepoTopic);
		this.a(response, page);
	}

	@RequestMapping({"/qsRepoTopic/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			QsRepoTopic qsRepoTopic = this.xx.get(id);
			modelMap.put("qsRepoTopic", qsRepoTopic);
			List options = this.xy.findByRtid(id);
			modelMap.put("options", options);
		}

		return "questions/qsRepoTopicEdit";
	}

	@RequestMapping({"/qsRepoTopic/f_json/save"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsRepoTopic qsRepoTopic, String[] optName,
			String[] optValue, String[] allowInput) {
		Result result = null;

		try {
			LoginUser e = this.d(request);
			ArrayList options = new ArrayList();
			if (optName != null) {
				for (int sdf = 0; sdf < optName.length; ++sdf) {
					options.add(new QsRepoOptions(optName[sdf], optValue[sdf], allowInput[sdf], e.getUnitId(),
							Long.valueOf((long) sdf)));
				}
			}

			result = new Result();
			qsRepoTopic.setUnitId(e.getUnitId());
			qsRepoTopic.setState(bm.oA.getValue());
			qsRepoTopic.setAttr(Integer.valueOf(1));
			if (ab.isEmpty(qsRepoTopic.getTid())) {
				qsRepoTopic.setTlevel(e.getDataScope().toString());
				qsRepoTopic.setDepNo(e.getDepNo());
				if (e.getDataScope() == n.gj.getValue()) {
					qsRepoTopic.setTlevel(n.gk.getValue().toString());
				} else if (e.getDataScope() == n.gm.getValue() && ab.isNotEmpty(e.getUnitId())) {
					qsRepoTopic.setTlevel(n.gk.getValue().toString());
				}

				qsRepoTopic.setCreateUser(e.getUsername());
			} else {
				QsRepoTopic arg12 = this.xx.get(qsRepoTopic.getTid());
				if (!e.getUsername().equals(arg12.getCreateUser())) {
					c.error("没有修改此题库的权限!");
					result = new Result("fail", "没有修改此题库的权限");
					this.a(response, result);
					return;
				}
			}

			SimpleDateFormat arg13 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updTimeStr = arg13.format(new Date());
			qsRepoTopic.setUpdTime(updTimeStr);
			this.xx.a(qsRepoTopic, options);
			result.setResult("success");
		} catch (Exception arg11) {
			c.error("获取信息异常!", arg11);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsRepoTopic/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			AcAccount e = (AcAccount) this.b(request);
			QsRepoTopic temp = this.xx.get(id);
			if (!e.getUsername().equals(temp.getCreateUser())) {
				c.error("没有删除此题库的权限!");
				result = new Result("fail", "没有删除此题库的权限");
				this.a(response, result);
				return;
			}

			result = new Result();
			this.xx.delete(id);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsRepoTopic/f_view/select"})
	public String o(HttpServletRequest request, ModelMap modelMap) {
		return "questions/qsRepoTopicSelect";
	}

	@RequestMapping({"/qsRepoTopic/f_json/selectList"})
	@ResponseBody
	@SqlLog(p = "评估单--题库列表")
	public void b(HttpServletRequest request, HttpServletResponse response, QsRepoTopic qsRepoTopic) {
		AcAccount account = (AcAccount) this.b(request);
		qsRepoTopic.setTlevel(account.getDataScope().toString());
		qsRepoTopic.setUnitId(account.getUnitId());
		qsRepoTopic.setDepNo(account.getDepNo());
		if (account.getDataScope() == n.gj.getValue()) {
			qsRepoTopic.setTlevel(n.gk.getValue().toString());
		} else if (account.getDataScope() == n.gh.getValue()) {
			qsRepoTopic.setCreateUser(account.getUsername());
		} else if (account.getDataScope() == n.gm.getValue() && ab.isNotEmpty(account.getUnitId())) {
			qsRepoTopic.setTlevel(n.gk.getValue().toString());
		}

		MyPage page = this.xx.b(qsRepoTopic);
		this.a(response, page);
	}
}