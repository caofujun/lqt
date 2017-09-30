package com.nis.msg.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.av;
import com.nis.comm.enums.u;
import com.nis.comm.utils.ab;
import com.nis.intervene.entity.FxPatientZb;
import com.nis.intervene.service.FxPatientZbService;
import com.nis.msg.entity.NyMessageDetail;
import com.nis.msg.entity.NyMessageTheme;
import com.nis.msg.entity.NyMessageUser;
import com.nis.msg.entity.NyMessageUserDetail;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.msg.service.NyMessageThemeService;
import com.nis.msg.service.NyMessageUserDetailService;
import com.nis.msg.service.NyMessageUserService;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.service.St003CryxxbService;
import java.util.ArrayList;
import java.util.Date;
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
public class NyMessageDetailController extends BaseController {
	private static final Logger c = Logger.getLogger(NyMessageDetailController.class);
	@Autowired
	private NyMessageUserDetailService cT;
	@Autowired
	private NyMessageDetailService uR;
	@Autowired
	private NyMessageThemeService uS;
	@Autowired
	private NyMessageUserService uT;
	@Autowired
	private FxPatientZbService ss;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private DepService uU;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/nyMessageDetail/f_json/testMsg"})
	@ResponseBody
	public void P(HttpServletRequest request, HttpServletResponse response, String userId, String content) {
		Result result = new Result();
		String appid = this.j.findByParamCode(Param.NIS_MSG_WEBCATE_APPID);
		String url = this.j.findByParamCode(Param.NIS_MSG_WEBCATE_URL);

		try {
			this.uR.j(appid, url, userId, content);
		} catch (Exception arg8) {
			c.error("调用失败", arg8);
			result.setResult("error");
			result.setData("调用失败");
		}

		result.setResult("success");
		result.setData("调用成功");
		this.a(response, result);
	}

	@RequestMapping({"/nyMessageDetail/f_json/testMsgDD"})
	@ResponseBody
	public void Q(HttpServletRequest request, HttpServletResponse response, String userId, String content) {
		Result result = new Result();
		String appid = this.j.findByParamCode(Param.NIS_MSG_DD_APPID);
		String color = this.j.findByParamCode(Param.NIS_MSG_DD_COLOR);
		String qname = this.j.findByParamCode(Param.NIS_MSG_DD_QNAME);
		String url = this.j.findByParamCode(Param.NIS_MSG_DD_URL);

		try {
			this.uR.a(appid, qname, url, color, userId, "", content);
		} catch (Exception arg10) {
			c.error("调用失败", arg10);
			result.setResult("error");
			result.setData("调用失败");
		}

		result.setResult("success");
		result.setData("调用成功");
		this.a(response, result);
	}

	@SqlLog(p = "干预消息--发送消息")
	@RequestMapping({"/nyMessageDetail/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, NyMessageDetail nyMessageDetail) {
		Result result = null;
		String appid = this.j.findByParamCode(Param.NIS_MSG_WEBCATE_APPID);
		String url = this.j.findByParamCode(Param.NIS_MSG_WEBCATE_URL);
		String ddAppid = this.j.findByParamCode(Param.NIS_MSG_DD_APPID);
		String ddUrl = this.j.findByParamCode(Param.NIS_MSG_DD_URL);
		String qname = this.j.findByParamCode(Param.NIS_MSG_DD_QNAME);
		String color = this.j.findByParamCode(Param.NIS_MSG_DD_COLOR);

		try {
			result = new Result();
			String e = nyMessageDetail.getMsgUserIds();
			nyMessageDetail.setSendTime(new Date());
			nyMessageDetail.setSendUserId(this.d(request).getUsername());
			nyMessageDetail.setSendUserName(this.d(request).getRealname());
			nyMessageDetail.setSendDeptId((String) this.b(request, "zg_dept"));
			if (ab.isNotEmpty((String) this.b(request, "zg_dept"))) {
				nyMessageDetail
						.setSendDeptName(this.uU.F((String) null, (String) this.b(request, "zg_dept")).getDeptName());
			}

			this.uR.save(nyMessageDetail);
			NyMessageUser nyMessageUser = new NyMessageUser();
			nyMessageUser.setUserId(this.d(request).getUsername());
			nyMessageUser.setThemeId(nyMessageDetail.getThemeId());
			this.uT.a(nyMessageUser);
			NyMessageTheme nyMessageTheme = this.uS.get(nyMessageDetail.getThemeId());
			nyMessageTheme.setLastMid(nyMessageDetail.getMid());
			this.uS.update(nyMessageTheme);
			System.out.println("--------------------测试-----------------------" + appid);
			List userDetailList = this.cT.a(e, nyMessageDetail);
			String userIds = "";
			NyMessageUserDetail fxPatientZb;
			if (!"0".equals(appid)) {
				for (Iterator st003Cryxxb = userDetailList.iterator(); st003Cryxxb
						.hasNext(); userIds = userIds + fxPatientZb.getUserId() + "|") {
					fxPatientZb = (NyMessageUserDetail) st003Cryxxb.next();
					this.uR.j(appid, url, fxPatientZb.getUserId(), nyMessageDetail.getContent());
				}
			}

			if (!"0".equals(ddAppid)) {
				this.uR.a(ddAppid, qname, ddUrl, color, userIds, "", nyMessageDetail.getContent());
			}

			nyMessageDetail.setQbCount(Integer.valueOf(userDetailList.size()));
			result.setData(nyMessageDetail);
			FxPatientZb fxPatientZb1 = this.ss.get(nyMessageDetail.getPzId());
			if (fxPatientZb1 != null) {
				fxPatientZb1.setPzStatus(av.lk.getValue());
				this.ss.update(fxPatientZb1);
				St003Cryxxb st003Cryxxb1 = this.bg.get(fxPatientZb1.getZyId());
				if (st003Cryxxb1 != null) {
					st003Cryxxb1.setGyStatus(u.gZ.getValue());
					this.bg.update(st003Cryxxb1);
				}
			}

			result.setResult("success");
		} catch (Exception arg17) {
			c.error("获取信息异常!", arg17);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/nyMessageDetail/f_view/index"})
	public String p(HttpServletRequest request, ModelMap modelMap, String zyid) {
		return "msg/nyMessageDetailList";
	}

	@RequestMapping({"/nyMessageDetail/f_json/list"})
	@ResponseBody
	@SqlLog(p = "患者干预消息列表")
	public void ag(HttpServletRequest request, HttpServletResponse response, String zyid) {
		NyMessageTheme nyMessageTheme = this.uS.getByZyid(zyid);
		Object page = new ArrayList();
		if (nyMessageTheme != null) {
			page = this.uR.getbyThemeId(nyMessageTheme.getThemeId());
		}

		this.a(response, page);
	}

	@RequestMapping({"/nyMessageDetail/f_json/pageQuery"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, NyMessageDetail nyMessageDetail) {
		MyPage page = this.uR.a(nyMessageDetail);
		this.a(response, page);
	}

	@RequestMapping({"/msg/f_json/unread"})
	@ResponseBody
	public void R(HttpServletRequest request, HttpServletResponse response, String employeeId, String deptId) {
		Result r = new Result();

		try {
			if (ab.isEmpty(employeeId)) {
				r.setResult("error");
				r.setMsg("请传入职工编号！");
			} else {
				NyMessageDetail e = new NyMessageDetail();
				e.setSendDeptId(deptId);
				e.setUserId(employeeId);
				int msgsCount = this.uR.getUnreadMsgsCountForInterFace(e);
				r.setData(String.valueOf(msgsCount));
				r.setResult("success");
			}
		} catch (Exception arg7) {
			arg7.printStackTrace();
			c.error("获取未读消息接口出错！", arg7);
			r.setResult("error");
			r.setMsg("获取未读消息接口出错！");
		}

		this.a(response, r);
	}

	@RequestMapping({"/msg/f_view/list"})
	public String y(HttpServletRequest request, ModelMap modelMap, String employeeId, String deptId) {
		modelMap.put("employeeId", employeeId);
		modelMap.put("deptId", deptId);
		return "msg/nyUnreadMessageListForInterface";
	}

	@RequestMapping({"/msg/f_view/pageQueryForInterface"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, NyMessageDetail nyMessageDetail) {
		MyPage page = this.uR.c(nyMessageDetail);
		this.a(response, page);
	}
}