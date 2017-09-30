package com.nis.patient.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.l;
import com.nis.dict.entity.MonitorOrder;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.monitor.entity.Gr016BkKjyw;
import com.nis.monitor.service.Gr016BkKjywService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.entity.St012Kjyw;
import com.nis.patient.service.St004YzxxbService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
public class St004YzxxbController extends BaseController {
	private static final Logger c = Logger.getLogger(St004YzxxbController.class);
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private Gr016BkKjywService uu;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/st004Yzxxb/f_view/toDocAdviceList"})
	public String K(HttpServletRequest request, ModelMap modelMap, String zyid) {
		return "patient/docAdviceList";
	}

	@RequestMapping({"/st004Yzxxb/f_view/getYzxx"})
	public String T(HttpServletRequest request, ModelMap modelMap, String zyId) {
		modelMap.put("zyId", zyId);
		return "patient/yzxxList";
	}

	@RequestMapping({"/st004Yzxxb/f_json/findYzxxDetail"})
	@ResponseBody
	@SqlLog(p = "患者隔离医嘱列表")
	public void ar(HttpServletRequest request, HttpServletResponse response, String zyId) {
		List page = this.bu.getYzxx(zyId);
		this.a(response, page);
	}

	@RequestMapping({"/st004Yzxxb/f_json/findDocAdviceList"})
	@ResponseBody
	@SqlLog(p = "患者信息--医嘱列表")
	public void a(HttpServletRequest request, HttpServletResponse response, St004Yzxxb st004Yzxxb,
			String orderTypeIns) {
		MyPage page = null;
		String orderBy = this.j.findByParamCode(Param.NIS_hzda_YZORDERBY);
		st004Yzxxb.setOrderBy(orderBy);
		if (st004Yzxxb != null && ab.isNotEmpty(st004Yzxxb.getZyid())) {
			if (ab.isNotEmpty(orderTypeIns)) {
				st004Yzxxb.setOrderTypeIn(new ArrayList(Arrays.asList(orderTypeIns.split(","))));
			}

			page = this.bu.b(st004Yzxxb);
		}

		this.a(response, page);
	}

	@RequestMapping({"/st004Yzxxb/f_view/toMonitorProject"})
	public String a(HttpServletRequest request, ModelMap modelMap, St004Yzxxb st004Yzxxb) {
		try {
			st004Yzxxb.setOrderName(URLDecoder.decode(st004Yzxxb.getOrderName(), "UTF-8"));
		} catch (UnsupportedEncodingException arg4) {
			arg4.printStackTrace();
		}

		modelMap.put("st004Yzxxb", st004Yzxxb);
		return "patient/monitorProjectEdit";
	}

	@RequestMapping({"/st004Yzxxb/f_json/setMonitorProject"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, MonitorOrder monitorOrder, String st004Id) {
		Result result = null;

		try {
			result = new Result();
			if (!ab.isNotEmpty(st004Id)) {
				throw new Exception("关键参数缺失!");
			}

			this.bu.a(monitorOrder, st004Id);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st004Yzxxb/f_view/toAntimicrobial"})
	public String b(HttpServletRequest request, ModelMap modelMap, St004Yzxxb st004Yzxxb) {
		try {
			st004Yzxxb.setOrderName(URLDecoder.decode(st004Yzxxb.getOrderName(), "UTF-8"));
		} catch (UnsupportedEncodingException arg4) {
			arg4.printStackTrace();
		}

		modelMap.put("st004Yzxxb", st004Yzxxb);
		return "patient/antimicrobialEdit";
	}

	@RequestMapping({"/st004Yzxxb/f_json/setAntimicrobial"})
	@ResponseBody
	@SqlLog(p = "抗菌药物--设置抗菌药物")
	public void a(HttpServletRequest request, HttpServletResponse response, St012Kjyw st012Kjyw, String st004Id) {
		Result result = null;

		try {
			result = new Result();
			if (!ab.isNotEmpty(st004Id)) {
				throw new Exception("关键参数缺失!");
			}

			this.bu.a(st012Kjyw, st004Id);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st004Yzxxb/f_json/findDrugSituation"})
	@ResponseBody
	@SqlLog(p = "患者手术详情--患者用药列表")
	public void c(HttpServletRequest request, HttpServletResponse response, St004Yzxxb st004Yzxxb) {
		List st004YzxxbList = null;
		if (st004Yzxxb != null && ab.isNotEmpty(st004Yzxxb.getZyid()) && ab.isNotEmpty(st004Yzxxb.getOperTypeId())
				&& ab.isNotEmpty(st004Yzxxb.getRefid()) && st004Yzxxb.getIsselect() != null) {
			st004YzxxbList = this.bu.findDrugSituation(st004Yzxxb);
		} else {
			st004YzxxbList = this.bu.findDrugSituationByPatient(st004Yzxxb);
		}

		String yzIdStr = st004Yzxxb.getYzIdStr();
		if (yzIdStr != null) {
			String[] split = yzIdStr.split(",");

			for (int i = 0; i < st004YzxxbList.size(); ++i) {
				if (split.length > 0) {
					for (int j = 0; j < split.length; ++j) {
						if (split[j].equals(((St004Yzxxb) st004YzxxbList.get(i)).getId())) {
							((St004Yzxxb) st004YzxxbList.get(i)).setIsselect(Integer.valueOf(1));
						}
					}
				}
			}
		}

		this.a(response, st004YzxxbList);
	}

	@RequestMapping({"/st004Yzxxb/f_json/findDrugSituationTemp"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, St004Yzxxb st004Yzxxb) {
		List st004YzxxbList = null;
		String yzIdStr = st004Yzxxb.getYzIdStr();
		st004YzxxbList = this.bu.findDrugSituationByPatientTemp(st004Yzxxb);
		if (ab.isNotEmpty(yzIdStr)) {
			String[] split = yzIdStr.split(",");

			for (int i = 0; i < st004YzxxbList.size(); ++i) {
				if (split.length > 0) {
					for (int j = 0; j < split.length; ++j) {
						if (split[j].equals(((St004Yzxxb) st004YzxxbList.get(i)).getId())) {
							((St004Yzxxb) st004YzxxbList.get(i)).setIsselect(Integer.valueOf(1));
						}
					}
				}
			}
		}

		this.a(response, st004YzxxbList);
	}

	@RequestMapping({"st004Yzxxb/f_json/saveDrugSituation"})
	@ResponseBody
	@SqlLog(p = "患者手术详情--添加手术用药")
	public void a(HttpServletRequest request, HttpServletResponse response, St004Yzxxb st004Yzxxb, String[] yzid) {
		Result result = new Result();

		try {
			this.bu.a(st004Yzxxb, yzid);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("保存授权信息异常!", arg6);
			result = new Result("error", "保存授权信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st004Yzxxb/f_view/toAddDrug"})
	public String c(HttpServletRequest request, ModelMap modelMap, St004Yzxxb st004Yzxxb) {
		modelMap.put("st004Yzxxb", st004Yzxxb);
		return "surgery/addDrug";
	}

	@RequestMapping({"st004Yzxxb/f_json/saveKjywSituation"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, St004Yzxxb st004Yzxxb, String[] yzid) {
		Result result = new Result();

		try {
			this.bu.b(st004Yzxxb, yzid);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("保存授权信息异常!", arg6);
			result = new Result("error", "保存授权信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st004Yzxxb/f_view/toAddKjyw"})
	public String d(HttpServletRequest request, ModelMap modelMap, St004Yzxxb st004Yzxxb) {
		modelMap.put("st004Yzxxb", st004Yzxxb);
		List list3 = this.p.u("medication_purpose", (String) null);
		LinkedList yymdList3 = new LinkedList();
		Iterator yzId = list3.iterator();

		while (yzId.hasNext()) {
			SysDict yymd3 = (SysDict) yzId.next();
			HashMap yzIdStr = new HashMap();
			yzIdStr.put("value", yymd3.getDictCode());
			yzIdStr.put("text", yymd3.getDictName() == null ? "" : yymd3.getDictName());
			yymdList3.add(yzIdStr);
		}

		String arg9 = l.toString(yymdList3);
		modelMap.put("yymd", arg9);
		String[] arg10 = st004Yzxxb.getYzId();
		String arg11 = "";

		for (int i = 0; i < arg10.length; ++i) {
			if (i == arg10.length - 1) {
				arg11 = arg11 + arg10[i];
			} else {
				arg11 = arg11 + arg10[i] + ",";
			}
		}

		modelMap.put("yzIdStr", arg11);
		return "surgery/addKjyw";
	}

	@RequestMapping({"/st004Yzxxb/f_view/toAddEditKjyw"})
	public String e(HttpServletRequest request, ModelMap modelMap, St004Yzxxb st004Yzxxb) {
		modelMap.put("st004Yzxxb", st004Yzxxb);
		List list3 = this.p.u("medication_purpose", (String) null);
		LinkedList yymdList3 = new LinkedList();
		Iterator yzId = list3.iterator();

		while (yzId.hasNext()) {
			SysDict yymd3 = (SysDict) yzId.next();
			HashMap yzIdStr = new HashMap();
			yzIdStr.put("value", yymd3.getDictCode());
			yzIdStr.put("text", yymd3.getDictName() == null ? "" : yymd3.getDictName());
			yymdList3.add(yzIdStr);
		}

		String arg9 = l.toString(yymdList3);
		modelMap.put("yymd", arg9);
		String[] arg10 = st004Yzxxb.getYzId();
		String arg11 = "";

		for (int i = 0; i < arg10.length; ++i) {
			if (i == arg10.length - 1) {
				arg11 = arg11 + arg10[i];
			} else {
				arg11 = arg11 + arg10[i] + ",";
			}
		}

		modelMap.put("yzIdStr", arg11);
		return "surgery/addEditKjyw";
	}

	@RequestMapping({"/st004Yzxxb/f_json/findKjywDrugSituation"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String[] yzid) {
		ArrayList st004YzxxbList = new ArrayList();
		List findDrugSituation = null;
		St004Yzxxb st004Yzxxb = new St004Yzxxb();
		if (yzid != null) {
			String[] arg9 = yzid;
			int arg8 = yzid.length;

			for (int arg7 = 0; arg7 < arg8; ++arg7) {
				String id = arg9[arg7];
				st004Yzxxb.setId(id);
				findDrugSituation = this.bu.findDrug(st004Yzxxb);
				if (findDrugSituation.size() > 0) {
					Date orderAt = ((St004Yzxxb) findDrugSituation.get(0)).getOrderAt();
					Date stopAt = ((St004Yzxxb) findDrugSituation.get(0)).getStopAt();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					cal.setTime(orderAt);
					long time1 = cal.getTimeInMillis();
					long time2 = 0L;
					if (stopAt != null) {
						cal.setTime(stopAt);
						time2 = cal.getTimeInMillis();
					} else {
						try {
							cal.setTime(df.parse(df.format(new Date())));
							time2 = cal.getTimeInMillis();
						} catch (ParseException arg21) {
							arg21.printStackTrace();
						}
					}

					long between_days = (time2 - time1) / 86400000L;
					int days = Integer.parseInt(String.valueOf(between_days));
					((St004Yzxxb) findDrugSituation.get(0)).setDays(String.valueOf(days));
					st004YzxxbList.add((St004Yzxxb) findDrugSituation.get(0));
				}
			}
		}

		this.a(response, st004YzxxbList);
	}

	@RequestMapping({"/st004Yzxxb/f_json/saveKjyw"})
	@ResponseBody
	public void e(HttpServletRequest request, HttpServletResponse response, St004Yzxxb st004Yzxxb) {
		List findDrugSituation = null;
		Result result = new Result("error", "获取信息异常");
		Gr016BkKjyw gr016BkKjyw = new Gr016BkKjyw();
		String splitRefid = st004Yzxxb.getRefid();
		if (st004Yzxxb.getYzId() != null) {
			for (int i = 0; i < st004Yzxxb.getYzId().length; ++i) {
				st004Yzxxb.setId(st004Yzxxb.getYzId()[i]);
				findDrugSituation = this.bu.findDrug(st004Yzxxb);
				if (findDrugSituation.size() > 0) {
					String orderType = ((St004Yzxxb) findDrugSituation.get(0)).getOrderTypeName();
					gr016BkKjyw.setOrderType(orderType);
					String orderName = ((St004Yzxxb) findDrugSituation.get(0)).getOrderName();
					gr016BkKjyw.setOrderName(orderName);
					Date orderAt = ((St004Yzxxb) findDrugSituation.get(0)).getOrderAt();
					gr016BkKjyw.setOrderAt(orderAt);
					Date stopAt = ((St004Yzxxb) findDrugSituation.get(0)).getStopAt();
					gr016BkKjyw.setStopAt(stopAt);
					Double dosage = ((St004Yzxxb) findDrugSituation.get(0)).getDosage();
					gr016BkKjyw.setDose(dosage);
					String dosageUnit = ((St004Yzxxb) findDrugSituation.get(0)).getDosageUnit();
					gr016BkKjyw.setDosageUnit(dosageUnit);
					String adminRouteName = ((St004Yzxxb) findDrugSituation.get(0)).getAdminRouteName();
					gr016BkKjyw.setAdminRouteName(adminRouteName);
					String sypc = ((St004Yzxxb) findDrugSituation.get(0)).getSypc();
					gr016BkKjyw.setSypc(sypc);
					String dateSection = ((St004Yzxxb) findDrugSituation.get(0)).getDateSection();
					gr016BkKjyw.setDateSection(dateSection);
					gr016BkKjyw.setIsselect(1);
					gr016BkKjyw.setRefid(splitRefid);
					gr016BkKjyw.setYzId(st004Yzxxb.getYzId()[i]);
					this.uu.save(gr016BkKjyw);
				}
			}

			result.setResult("success");
			gr016BkKjyw.setRefid(splitRefid);
		}

		this.a(response, result);
	}

	@RequestMapping({"/st004Yzxxb/f_json/findKjywDrugSituationTemp"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String id, String[] yzId, String refid) {
		St004Yzxxb st004Yzxxb = new St004Yzxxb();
		String[] yzIds = new String[yzId.length - 1];
		int index = 0;
		ArrayList listTemp = new ArrayList();

		int list3;
		for (list3 = 0; list3 < yzId.length; ++list3) {
			listTemp.add(yzId[list3]);
			if (yzId[list3].equals(id)) {
				index = list3;
			}
		}

		listTemp.remove(index);

		for (list3 = 0; list3 < listTemp.size(); ++list3) {
			yzIds[list3] = (String) listTemp.get(list3);
		}

		List arg14 = this.p.u("medication_purpose", (String) null);
		LinkedList yymdList3 = new LinkedList();
		Iterator arg12 = arg14.iterator();

		while (arg12.hasNext()) {
			SysDict yymd3 = (SysDict) arg12.next();
			HashMap map = new HashMap();
			map.put("value", yymd3.getDictCode());
			map.put("text", yymd3.getDictName() == null ? "" : yymd3.getDictName());
			yymdList3.add(map);
		}

		String arg15 = l.toString(yymdList3);
		st004Yzxxb.setYzId(yzIds);
		st004Yzxxb.setRefid(refid);
		st004Yzxxb.setId(id);
		st004Yzxxb.setYymd(arg15);
		this.a(response, st004Yzxxb);
	}
}