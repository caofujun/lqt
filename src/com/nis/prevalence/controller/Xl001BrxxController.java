package com.nis.prevalence.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.e;
import com.nis.comm.enums.y;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.l;
import com.nis.param.service.SysParamService;
import com.nis.prevalence.entity.RegistryForm;
import com.nis.prevalence.entity.Xl001Brxx;
import com.nis.prevalence.entity.Xl002Grxx;
import com.nis.prevalence.entity.Xl003Byt;
import com.nis.prevalence.entity.Xl004Kjyw;
import com.nis.prevalence.service.Xl001BrxxService;
import com.nis.prevalence.service.Xl002GrxxService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xl001BrxxController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl001BrxxController.class);
	@Autowired
	private Xl001BrxxService wH;
	@Autowired
	private Xl002GrxxService wI;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/xl001Brxx/f_view/toRegistryFormEdit"})
	@SqlLog(p = "横断面调查--横断面调查患者详情")
	public String b(HttpServletRequest request, ModelMap modelMap, String brid, Date surveyDate) {
		String xhlVersion = this.j.findByParamCode(Param.NIS_REGISTRY_VERSION);
		modelMap.put("xhlVersion", xhlVersion);
		Xl001Brxx xl001Brxx;
		LoginUser user;
		if (ab.isNotEmpty(brid)) {
			xl001Brxx = this.wH.get(brid);
			if (ab.isEmpty(xl001Brxx.getVoteid())) {
				user = this.d(request);
				xl001Brxx.setVoteid(user.getUsername());
				xl001Brxx.setVotename(user.getRealname());
			}

			modelMap.put("xl001Brxx", xl001Brxx);
			List user1 = this.wI.queryByBrid(brid);
			LinkedList untiXl2List = new LinkedList();
			LinkedList commXl2List = new LinkedList();
			Iterator arg10 = user1.iterator();

			while (arg10.hasNext()) {
				Xl002Grxx xl002 = (Xl002Grxx) arg10.next();
				if (xl002.getInfectType() == y.hu.getValue()) {
					untiXl2List.add(xl002);
				} else {
					commXl2List.add(xl002);
				}
			}

			modelMap.put("untiXl2List", untiXl2List);
			modelMap.put("commXl2List", commXl2List);
		} else {
			modelMap.put("isAdd", Integer.valueOf(1));
			xl001Brxx = new Xl001Brxx();
			user = this.d(request);
			xl001Brxx.setVoteid(user.getUsername());
			xl001Brxx.setVotename(user.getRealname());
			xl001Brxx.setVotedate(new Date());
			if (surveyDate != null) {
				xl001Brxx.setVotedate(surveyDate);
			}

			modelMap.put("xl001Brxx", xl001Brxx);
		}

		return "prevalence/registryFormEdit";
	}

	@RequestMapping({"/xl001Brxx/f_json/saveRegistryForm"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, RegistryForm registryForm,
			String unitInfectMap, String commInfectMap, Xl001Brxx xl001Brxx, String isGrade) {
		Result result = null;

		try {
			result = new Result();
			xl001Brxx.setIsGrade1(Integer.valueOf(0));
			xl001Brxx.setIsGrade2(Integer.valueOf(0));
			xl001Brxx.setIsGrade3(Integer.valueOf(0));
			xl001Brxx.setIsGrade4(Integer.valueOf(0));
			if ("0".equals(isGrade)) {
				xl001Brxx.setIsGrade1(Integer.valueOf(1));
			} else if ("1".equals(isGrade)) {
				xl001Brxx.setIsGrade2(Integer.valueOf(1));
			} else if ("2".equals(isGrade)) {
				xl001Brxx.setIsGrade3(Integer.valueOf(1));
			} else if ("3".equals(isGrade)) {
				xl001Brxx.setIsGrade4(Integer.valueOf(1));
			}

			if (xl001Brxx.getIsCa() == null) {
				xl001Brxx.setIsCa(Integer.valueOf(0));
			}

			if (xl001Brxx.getIsHa() == null) {
				xl001Brxx.setIsHa(Integer.valueOf(0));
			}

			List e = registryForm.getXl2ListU();
			List xl2ListComm = registryForm.getXl2ListC();
			Map unitMap = (Map) l.ar(unitInfectMap);
			Map commMap = (Map) l.ar(commInfectMap);
			ArrayList xl2ListU = new ArrayList();
			ArrayList xl2ListC = new ArrayList();
			ArrayList gridNotIn = new ArrayList();
			ArrayList bytidNotIn = new ArrayList();
			ArrayList yjywidNotIn = new ArrayList();
			Entry entry;
			Iterator arg18;
			Xl002Grxx xl002;
			List xl3List;
			List xl3s;
			ArrayList xl003List;
			String xl3;
			Iterator arg24;
			Xl003Byt xl003;
			List xl4List;
			Xl004Kjyw xl004;
			Iterator arg28;
			if (xl001Brxx.getIsCa().intValue() == 1) {
				arg18 = unitMap.entrySet().iterator();

				label136 : while (arg18.hasNext()) {
					entry = (Entry) arg18.next();
					xl002 = (Xl002Grxx) e.get(Integer.parseInt((String) entry.getKey()));
					if (ab.isNotEmpty(xl002.getGrid())) {
						gridNotIn.add(xl002.getGrid());
					}

					xl3List = xl002.getXl003List();
					xl3s = (List) entry.getValue();
					xl003List = new ArrayList();
					arg24 = xl3s.iterator();

					while (true) {
						do {
							do {
								do {
									if (!arg24.hasNext()) {
										xl002.setXl003List(xl003List);
										xl2ListU.add(xl002);
										continue label136;
									}

									xl3 = (String) arg24.next();
									xl003 = (Xl003Byt) xl3List.get(Integer.parseInt(xl3));
								} while (!StringUtils.isNotBlank(xl003.getInfectPathoId()));

								if (ab.isNotEmpty(xl003.getBytid())) {
									bytidNotIn.add(xl003.getBytid());
								}

								xl003List.add(xl003);
								xl4List = xl003.getXl004List();
							} while (xl4List == null);
						} while (xl4List.size() <= 0);

						arg28 = xl4List.iterator();

						while (arg28.hasNext()) {
							xl004 = (Xl004Kjyw) arg28.next();
							if (ab.isNotEmpty(xl004.getYjywid())) {
								yjywidNotIn.add(xl004.getYjywid());
							}
						}
					}
				}
			}

			if (xl001Brxx.getIsHa().intValue() == 1) {
				arg18 = commMap.entrySet().iterator();

				label104 : while (arg18.hasNext()) {
					entry = (Entry) arg18.next();
					xl002 = (Xl002Grxx) xl2ListComm.get(Integer.parseInt((String) entry.getKey()));
					if (ab.isNotEmpty(xl002.getGrid())) {
						gridNotIn.add(xl002.getGrid());
					}

					xl3List = xl002.getXl003List();
					xl3s = (List) entry.getValue();
					xl003List = new ArrayList();
					arg24 = xl3s.iterator();

					while (true) {
						do {
							do {
								do {
									if (!arg24.hasNext()) {
										xl002.setXl003List(xl003List);
										xl2ListC.add(xl002);
										continue label104;
									}

									xl3 = (String) arg24.next();
									xl003 = (Xl003Byt) xl3List.get(Integer.parseInt(xl3));
								} while (!StringUtils.isNotBlank(xl003.getInfectPathoId()));

								if (ab.isNotEmpty(xl003.getBytid())) {
									bytidNotIn.add(xl003.getBytid());
								}

								xl003List.add(xl003);
								xl4List = xl003.getXl004List();
							} while (xl4List == null);
						} while (xl4List.size() <= 0);

						arg28 = xl4List.iterator();

						while (arg28.hasNext()) {
							xl004 = (Xl004Kjyw) arg28.next();
							if (ab.isNotEmpty(xl004.getYjywid())) {
								yjywidNotIn.add(xl004.getYjywid());
							}
						}
					}
				}
			}

			if (ab.isNotEmpty(xl001Brxx.getBrid())) {
				this.wH.a(xl001Brxx, xl2ListU, xl2ListC, gridNotIn, bytidNotIn, yjywidNotIn);
			} else {
				this.wH.a(xl001Brxx, xl2ListU, xl2ListC);
			}

			result.setResult("success");
			result.setData(xl001Brxx.getBrid());
		} catch (Exception arg29) {
			c.error("获取信息异常!", arg29);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findAddRegistryForm"})
	@ResponseBody
	public void aA(HttpServletRequest request, HttpServletResponse response, String zyid) {
		Map map = null;
		if (ab.isNotEmpty(zyid)) {
			map = this.wH.cw(zyid);
		}

		this.a(response, map);
	}

	@RequestMapping({"/xl001Brxx/f_view/toList"})
	@SqlLog(p = "横断面调查--横断面调查患者列表")
	public String t(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("currDate", f.c(f.a(new Date(), -1), "yyyy-MM-dd"));
		AcAccount ac = (AcAccount) this.b(request);
		if (e.fE.getValue().equals(ac.getRoleCur().getRoleType())) {
			modelMap.put("clinical", "1");
			modelMap.put("reportDeptId", this.b(request, "zg_dept"));
		}

		List xl001BrxxList = this.wH.findVoteDateList();
		modelMap.put("xl001BrxxList", xl001BrxxList);
		return "prevalence/caseStudyList";
	}

	@RequestMapping({"/xl001Brxx/f_json/findcaseStudyList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Xl001Brxx xl001Brxx) {
		AcAccount ac = (AcAccount) this.b(request);
		if (e.fE.getValue().equals(ac.getRoleCur().getRoleType())) {
			xl001Brxx.setDeptId("" + this.b(request, "zg_dept"));
		}

		if (xl001Brxx.getSearchString() != null && !"".equals(xl001Brxx.getSearchString())) {
			xl001Brxx.setSearchString(ab.aR(xl001Brxx.getSearchString()));
		}

		MyPage page = this.wH.b(xl001Brxx);
		this.a(response, page);
	}

	@RequestMapping({"/xl001Brxx/f_json/findOne"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Xl001Brxx xl001Brxx) {
		Xl001Brxx data = this.wH.c(xl001Brxx);
		this.a(response, data);
	}

	@RequestMapping({"/xl001Brxx/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "横断面调查--删除横断面调查患者信息")
	public void c(HttpServletRequest request, HttpServletResponse response, String brids) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isNotEmpty(brids)) {
				List e = Arrays.asList(brids.split(","));
				this.wH.N(e);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xl001Brxx/f_json/callPNis6TaskXhl"})
	@ResponseBody
	public void aB(HttpServletRequest request, HttpServletResponse response, String surveyDate) {
		Result result = null;

		try {
			result = new Result("error", "");
			if (ab.isNotEmpty(surveyDate)) {
				String e = this.wH.A(f.Z(surveyDate));
				if (e.indexOf("error") > -1) {
					result.setMsg(e);
				} else {
					result.setResult("success");
				}
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xl001Brxx/f_json/findWaitRegister"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, Xl001Brxx xl001Brxx) {
		MyPage page = this.wH.d(xl001Brxx);
		this.a(response, page);
	}
}