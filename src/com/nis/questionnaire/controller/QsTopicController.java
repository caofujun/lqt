package com.nis.questionnaire.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.questionnaire.entity.QsTopic;
import com.nis.questionnaire.entity.QsTopicOption;
import com.nis.questionnaire.service.QsTopicOptionService;
import com.nis.questionnaire.service.QsTopicService;
import java.util.ArrayList;
import java.util.Iterator;
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
public class QsTopicController extends BaseController {
	private static final Logger c = Logger.getLogger(QsTopicController.class);
	@Autowired
	private QsTopicService xj;
	@Autowired
	private QsTopicOptionService xk;

	@RequestMapping({"/qsTopic/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "questionnaire/qsTopicList";
	}

	@RequestMapping({"/qsTopic/f_json/findByQid"})
	@ResponseBody
	@SqlLog(p = "评估单--题目列表")
	public void X(HttpServletRequest request, HttpServletResponse response, String qid, String rid) {
		List list = this.xj.N(qid, rid);
		this.b(response, list);
	}

	@RequestMapping({"/qsTopic/f_view/toedit"})
	@SqlLog(p = "评估单--题目详情")
	public String e(HttpServletRequest request, ModelMap modelMap, String id, String qid) {
		QsTopic qsTopic = null;
		if (id != null) {
			qsTopic = this.xj.get(id);
			List options = this.xk.findByTid(id);
			modelMap.put("options", options);
		} else {
			qsTopic = new QsTopic();
			qsTopic.setQid(qid);
		}

		modelMap.put("qsTopic", qsTopic);
		return "questionnaire/qsTopicEdit";
	}

	@RequestMapping({"/qsTopic/f_json/save"})
	@ResponseBody
	@SqlLog(p = "评估单--保存题目信息")
	public void a(HttpServletRequest request, HttpServletResponse response, QsTopic qsTopic, String[] repoTids,
			String[] optId, String[] optName, String[] optValue, String[] allowInput, String[] isSelect) {
		Result result = null;

		try {
			AcAccount e = (AcAccount) this.b(request);
			result = new Result();
			qsTopic.setUnitId(e.getUnitId());
			ArrayList options = new ArrayList();
			if (optName != null) {
				for (int i = 0; i < optName.length; ++i) {
					options.add(new QsTopicOption(optId[i], optName[i], optValue[i], allowInput[i], isSelect[i],
							e.getUnitId(), Long.valueOf((long) i)));
				}
			}

			if (ab.isEmpty(qsTopic.getTid())) {
				if (repoTids != null) {
					this.xj.a(qsTopic, repoTids);
				} else {
					this.xj.a(qsTopic, options, e);
				}
			} else {
				this.xj.a(qsTopic, options);
			}

			result.setResult("success");
		} catch (Exception arg13) {
			c.error("获取信息异常!", arg13);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsTopic/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "评估单--删除题目")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.xj.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsTopic/f_json/scoreUpdate"})
	@ResponseBody
	@SqlLog(p = "评估单--更新题目")
	public void Y(HttpServletRequest request, HttpServletResponse response, String tid, String weight) {
		Result result = null;

		try {
			result = new Result();
			QsTopic e = this.xj.get(tid);
			if (e != null) {
				e.setWeight(weight);
			}

			this.xj.updateScore(e);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsTopic/f_json/updateSort"})
	@ResponseBody
	@SqlLog(p = "评估单--更新题目排列顺序")
	public void s(HttpServletRequest request, HttpServletResponse response, String params) {
		Result result = null;

		try {
			result = new Result();
			String[] e = params.split("#");
			ArrayList qts = new ArrayList();
			String[] arg9 = e;
			int arg8 = e.length;

			for (int arg7 = 0; arg7 < arg8; ++arg7) {
				String qs = arg9[arg7];
				if (ab.isNotEmpty(qs)) {
					String[] qsArr = qs.split(",");
					QsTopic qt = new QsTopic();
					qt.setTid(qsArr[0]);
					qt.setSortNo(Long.valueOf(qsArr[1]));
					qts.add(qt);
				}
			}

			this.xj.z(qts);
			result.setResult("success");
		} catch (Exception arg12) {
			c.error("获取信息异常!", arg12);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsTopic/f_json/findTopicEasyUi"})
	@ResponseBody
	public void aC(HttpServletRequest request, HttpServletResponse response, String qid) {
		if (qid != null) {
			QsTopic qsTopic = new QsTopic();
			qsTopic.setQid(qid);
			List list = this.xj.findTopicInfo(qsTopic);
			ArrayList kvList = new ArrayList();
			boolean index = false;

			KvEntity kv;
			for (Iterator arg8 = list.iterator(); arg8.hasNext(); kvList.add(kv)) {
				QsTopic top = (QsTopic) arg8.next();
				kv = new KvEntity();
				StringBuilder sb = new StringBuilder(top.getTid() + "#");
				Iterator arg12 = top.getOptions().iterator();

				while (arg12.hasNext()) {
					QsTopicOption op = (QsTopicOption) arg12.next();
					sb.append(op.getOptId()).append("@").append(op.getOptName()).append("&");
				}

				if (sb.indexOf("&") > 0) {
					sb.delete(sb.length() - 1, sb.length());
				}

				kv.setKey(sb.toString());
				kv.setValue(top.getTitle());
				if (!index) {
					kv.setSelected(true);
				}
			}

			this.b(response, kvList);
		}
	}
}