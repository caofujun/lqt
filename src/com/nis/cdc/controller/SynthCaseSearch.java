package com.nis.cdc.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgSys011Mark;
import com.nis.cdc.service.CtgSys011MarkService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St020ClinicPatientsService;
import com.nis.zg.service.Zg031SqksService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SynthCaseSearch extends BaseController {
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private CtgSys011MarkService dE;
	@Autowired
	private Zg031SqksService dF;
	@Autowired
	private DepService e;
	@Autowired
	private SysParamService j;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping({"/cdc/f_view/suspectedCase"})
	public String h(HttpServletRequest request, ModelMap modelMap, String patientType, String dateType,
			String queryStartDate, String deptType, String patientName) {
		modelMap.put("queryStartDate", this.sdf.format(Long.valueOf((new Date()).getTime() - 604800000L)));
		modelMap.put("queryEndDate", this.sdf.format(Long.valueOf((new Date()).getTime())));
		if (ab.isNotEmpty(patientType)) {
			modelMap.put("patientType", patientType);
		}

		if (ab.isNotEmpty(dateType)) {
			modelMap.put("dateType", dateType);
		}

		if (ab.isNotEmpty(queryStartDate)) {
			modelMap.put("queryStartDate", queryStartDate);
		}

		if (ab.isNotEmpty(deptType)) {
			modelMap.put("deptType", deptType);
		}

		if (ab.isNotEmpty(patientName)) {
			modelMap.put("patientName", patientName);
		}

		AcAccount account = (AcAccount) this.b(request);
		if (!"hospital".equals(account.getAcType())) {
			modelMap.put("acType", "doctor");
			byte mz = 0;
			byte zy = 0;
			Dep dep = new Dep();
			dep.setChargeManId(account.getDocNo());
			dep.setFlag(Long.valueOf(1L));
			List CD = this.e.e(dep);
			Iterator arg13 = CD.iterator();

			while (arg13.hasNext()) {
				Dep d = (Dep) arg13.next();
				if ("1".equals(d.getDeptTypeId())) {
					zy = 1;
				} else if ("2".equals(d.getDeptTypeId())) {
					mz = 2;
				}
			}

			modelMap.put("chargeLevel", String.valueOf(zy + mz));
		} else {
			modelMap.put("acType", "hospital");
		}

		modelMap.put("OR", this.j.findByParamCode(Param.NIS_CDC_OPEN_REPORT));
		modelMap.put("ISDB", this.j.findByParamCode(Param.NIS_CDC_INTERFACE_IS_RBA_ENABLED));
		return "synthCaseSearch";
	}

	@RequestMapping({"/cdc/f_json/suspectedCaseData"})
	@ResponseBody
	@SqlLog(p = "预警病历传染病--患者列表")
	public void a(HttpServletRequest request, HttpServletResponse response, St020ClinicPatients st020ClinicPatients) {
		AcAccount user = (AcAccount) this.b(request);
		st020ClinicPatients.setUserId(user.getUserId());
		if (st020ClinicPatients.getPatientName() != null && !"".equals(st020ClinicPatients.getPatientName())) {
			st020ClinicPatients.setPatientName(ab.aR(st020ClinicPatients.getPatientName()));
		}

		MyPage page = this.bh.g(st020ClinicPatients);
		this.a(response, page);
	}

	@RequestMapping({"/cdc/f_json/confirmANDexclude"})
	@ResponseBody
	@SqlLog(p = "预警病历传染病--确认排除")
	public void b(HttpServletRequest request, HttpServletResponse response, String mzzyid, String diseaseid,
			Long status) {
		Result r = new Result();
		AcAccount account = (AcAccount) this.b(request);
		if (!ab.aM(mzzyid) && status != null) {
			try {
				if ("-1".equals(status)) {
					this.dE.deleteByOtherField(mzzyid, diseaseid);
				} else {
					this.dE.deleteByOtherField(mzzyid, diseaseid);
					CtgSys011Mark e = new CtgSys011Mark();
					e.setMasterid(af.getUUID32());
					e.setDiseaseid(diseaseid);
					e.setMzzyid(mzzyid);
					e.setFlag(status);
					if ("hospital".equals(account.getAcType())) {
						e.setOptId(account.getUsername());
					} else {
						e.setOptId(account.getDocNo());
					}

					e.setOptName(account.getRealname());
					e.setOptDate(new Date());
					this.dE.save(e);
				}

				r.setResult("success");
				r.setMsg("操作成功！");
			} catch (Exception arg8) {
				r.setResult("error");
				r.setMsg("抱歉，出错了，操作失败！");
				r.setExtraValue(arg8.getMessage());
			}
		} else {
			r.setResult("error");
			r.setMsg("未获取到必要参数，操作失败！");
		}

		this.a(response, r);
	}

	@RequestMapping({"/cdc/f_view/YSBLSearch"})
	public String j(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("queryStartDate", this.sdf.format(Long.valueOf((new Date()).getTime() - 604800000L)));
		modelMap.put("queryEndDate", this.sdf.format(Long.valueOf((new Date()).getTime())));
		modelMap.put("OR", this.j.findByParamCode(Param.NIS_CDC_OPEN_REPORT));
		return "suspectedCase";
	}

	@RequestMapping({"/cdc/f_view/YSBLSearchDate"})
	@SqlLog(p = "传染病疑似病例搜索--病疑似病例列表")
	public void b(HttpServletRequest request, HttpServletResponse response, St020ClinicPatients st020ClinicPatients) {
		AcAccount user = (AcAccount) this.b(request);
		st020ClinicPatients.setUserId(user.getUserId());
		MyPage page;
		if ("1".equals(st020ClinicPatients.getPatientType())) {
			if (st020ClinicPatients.getPatientName() != null && !"".equals(st020ClinicPatients.getPatientName())) {
				st020ClinicPatients.setPatientName(ab.aR(st020ClinicPatients.getPatientName()));
			}

			if (st020ClinicPatients.getDiagnosisName() != null && !"".equals(st020ClinicPatients.getDiagnosisName())) {
				st020ClinicPatients.setDiagnosisName(ab.aR(st020ClinicPatients.getDiagnosisName()));
			}

			if (st020ClinicPatients.getImageQueryStr() != null && !"".equals(st020ClinicPatients.getImageQueryStr())) {
				st020ClinicPatients.setImageQueryStr(ab.aR(st020ClinicPatients.getImageQueryStr()));
			}

			page = this.bh.e(st020ClinicPatients);
			this.a(response, page);
		} else if ("2".equals(st020ClinicPatients.getPatientType())) {
			page = this.bg.a(st020ClinicPatients);
			this.a(response, page);
		}

	}
}