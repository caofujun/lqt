package com.nis.patient.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.r;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St006Twxx;
import com.nis.patient.service.St006TwxxService;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class St006TwxxController extends BaseController {
	@Autowired
	private St006TwxxService bN;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/st006Twxx/f_view/toTwxxList"})
	@SqlLog(p = "患者信息--体温列表")
	public String a(HttpServletRequest request, ModelMap modelMap, String zyid, Date dataDate) {
		St006Twxx st006Twxx = this.bN.getMaxATByZY(zyid);
		if (st006Twxx != null) {
			if (dataDate != null) {
				st006Twxx.setQueryEndDate(f.a(dataDate, 3));
				st006Twxx.setQueryStartDate(f.a(dataDate, -3));
			} else {
				st006Twxx.setQueryEndDate(st006Twxx.getEndDate());
				st006Twxx.setQueryStartDate(f.a(st006Twxx.getEndDate(), -6));
			}
		}

		modelMap.put("st006Twxx", st006Twxx);
		return "patient/twxxList";
	}

	@RequestMapping({"/st006Twxx/f_json/findTwxxList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, St006Twxx st006Twxx) {
		Result result = null;
		List st006TwxxList = this.bN.findTwxxList(st006Twxx);
		result = new Result("success");
		result.setData(st006TwxxList);
		this.b(response, result);
	}

	@RequestMapping({"/st006Twxx/f_json/findMainFever"})
	@ResponseBody
	public void as(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			if (ab.isEmpty(dataRange)) {
				dataRange = "-6";
			}

			Date e = f.p(f.a(f.getCurDate(), -1));
			Date startDate = f.q(f.a(e, Integer.parseInt(dataRange)));
			LinkedList fever = new LinkedList();
			LinkedList fever48 = new LinkedList();
			LinkedList feveryw = new LinkedList();
			LinkedList xAxisData = new LinkedList();
			LinkedList legendData = new LinkedList();
			LinkedList series = new LinkedList();
			List list = this.bN.h(startDate, e);
			String ywfr = this.j.findByParamCode(Param.NIS_JCRB_IS_KFJC);
			Iterator resultMap = list.iterator();

			while (resultMap.hasNext()) {
				Map tw = (Map) resultMap.next();
				xAxisData.add(f.formatDate((Date) tw.get("datetime")));
				fever.add(Integer.valueOf(r.d(tw.get("fever"))));
				fever48.add(Integer.valueOf(r.d(tw.get("fever48"))));
				if ("1".equals(ywfr)) {
					feveryw.add(Integer.valueOf(r.d(tw.get("feveryw"))));
				}
			}

			series.add(fever);
			series.add(fever48);
			series.add(feveryw);
			String tw1 = this.j.findByParamCode(Param.NIS_TW_FR);
			legendData.add("发热>=" + tw1 + "℃");
			legendData.add("入院48H后发热");
			if ("1".equals(ywfr)) {
				legendData.add("医务人员发热");
			}

			HashMap resultMap1 = new HashMap();
			resultMap1.put("legendData", legendData);
			resultMap1.put("xAxisData", xAxisData);
			resultMap1.put("series", series);
			result = new Result("success");
			result.setData(resultMap1);
		} catch (Exception arg16) {
			result = new Result("error", arg16.getMessage());
			arg16.printStackTrace();
		}

		this.a(response, result);
	}
}