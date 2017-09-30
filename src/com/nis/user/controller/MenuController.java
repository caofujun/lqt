package com.nis.user.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.service.AcAccountService;
import com.nis.access.service.AcMenuService;
import com.nis.comm.constants.b;
import com.nis.comm.controller.BaseController;
import com.nis.comm.enums.n;
import com.nis.comm.utils.ab;
import com.nis.organization.entity.Unit;
import com.nis.organization.service.UnitService;
import com.nis.param.service.SysParamService;
import com.nis.user.utils.a;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenuController extends BaseController {
	private static final Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private AcMenuService k;
	@Autowired
	private UnitService se;
	@Autowired
	private SysParamService j;
	@Autowired
	private AcAccountService d;

	@RequestMapping({"/menu/f_view/to"})
	public String c(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String menuNo,
			String menuUrl) {
		AcAccount account = (AcAccount) this.b(request);
		String location = null;
		if (ab.isEmpty(menuUrl)) {
			AcMenu unitId = this.k.e(menuNo);
			menuUrl = unitId.getDestUrl();
		} else {
			menuUrl = menuUrl.replaceAll("~-~", "?");
			menuUrl = menuUrl.replaceAll("_~_", "&");
		}

		if (menuUrl.contains("$hospital$")) {
			String unitId1 = account.getUnitId();
			Unit unit = this.se.get((String) null);
			if (menuUrl.indexOf("?") != -1) {
				menuUrl = menuUrl + "&";
			} else {
				menuUrl = menuUrl + "?";
			}

			String time = String.valueOf(System.currentTimeMillis());
			String random = String.valueOf(this.getRandomNumber());
			String sign = DigestUtils.md5Hex(unit.getUnitId() + account.getUserId() + time + random);
			location = String.format("redirect:%s_city_id=%s&_unit_id=%s&_user_id=%s&_time=%s&_random=%s&_sign=%s",
					new Object[]{menuUrl, unit.getUnitId(), account.getUserId(), time, random, sign});
		} else if (menuUrl.contains("$unitId$")) {
			menuUrl = menuUrl.replaceAll("\\$unitId\\$", account.getUnitId());
			location = String.format("redirect:%s&username=%s&realname=%s&userId=%s",
					new Object[]{menuUrl, account.getUsername(), "csm", account.getUserId()});
		} else {
			location = "redirect:" + menuUrl;
		}

		logger.info("跳转地址：" + location);
		return location;
	}

	@RequestMapping({"/menu/f_json/quickMenu"})
	@ResponseBody
	public void w(HttpServletRequest request, HttpServletResponse response) {
		List menus = (List) this.b(request, "user_menus");
		AcAccount account = (AcAccount) this.b(request);
		StringBuffer menu = new StringBuffer();
		if (a.h(menus, "outp_rese_week")) {
			menu.append("<li><a data-menuNo=\"outp_rese_week\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=Schedule_~_a=week\" class=\"paibanguanli\"><span class=\"icon\"></span><span class=\"text\">排班管理</span></a></li>");
		}

		if (a.h(menus, "outp_rese_cfm")) {
			menu.append("<li><a data-menuNo=\"outp_rese_cfm\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=Schedule_~_a=cfm\" class=\"paibanshenhe\"><span class=\"icon\"></span><span class=\"text\">排班审核</span></a></li>");
		}

		if (a.h(menus, "outp_rese_yuyue")) {
			menu.append("<li><a data-menuNo=\"outp_rese_yuyue\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=Yuyue_~_a=index\" class=\"xianchangyuyue\"><span class=\"icon\"></span><span class=\"text\">现场预约</span></a></li>");
		}

		if (a.h(menus, "outp_rese_check")) {
			menu.append("<li><a data-menuNo=\"outp_rese_check\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=Yuyue_~_a=check\" class=\"yuyuehedui\"><span class=\"icon\"></span><span class=\"text\">预约核对</span></a></li>");
		}

		if (a.h(menus, "data_search_today")) {
			menu.append("<li><a data-menuNo=\"data_search_today\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=YuyueQuery_~_a=today\" class=\"yuyueqingdan\"><span class=\"icon\"></span><span class=\"text\">预约清单</span></a></li>");
		}

		if (a.h(menus, "data_search_black")) {
			menu.append("<li><a data-menuNo=\"data_search_black\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=Family_~_a=index_~_is_black=1\" class=\"heimingdanchaxun\"><span class=\"icon\"></span><span class=\"text\">黑名单查询</span></a></li>");
		}

		if (a.h(menus, "outp_unit_docentry")) {
			menu.append("<li><a data-menuNo=\"outp_unit_docentry\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=Doctor_~_a=index\" class=\"yishenguanli\"><span class=\"icon\"></span><span class=\"text\">医生管理</span></a></li>");
		}

		if (a.h(menus, "outp_unit_dep")) {
			menu.append("<li><a data-menuNo=\"outp_unit_dep\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=Dep_~_a=index\" class=\"keshiguanli\"><span class=\"icon\"></span><span class=\"text\">科室管理</span></a></li>");
		}

		if (a.h(menus, "data_search_depstat")) {
			menu.append("<li><a data-menuNo=\"data_search_depstat\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=YuyueQuery_~_a=depStat\" class=\"yuyuebaobiao\"><span class=\"icon\"></span><span class=\"text\">预约报表</span></a></li>");
		}

		if (n.gm.getValue().intValue() != account.getDataScope().intValue()) {
			menu.append("<li><a data-menuNo=\"pt_note_info\" href=\"").append(b.es).append(
					"/menu/f_view/to.shtml?menuUrl=http://h.91160.com/$hospital$/index.php~-~c=Feedback_~_a=form\" class=\"yijianfankui\"><span class=\"icon\"></span><span class=\"text\">意见反馈</span></a></li>");
		}

		this.a(response, menu.toString());
	}

	private int getRandomNumber() {
		double result = Math.random();
		result = result * 9000.0D + 10000.0D;
		return (int) result;
	}
}