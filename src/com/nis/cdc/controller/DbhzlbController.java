package com.nis.cdc.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.service.CtgSys011MarkService;
import com.nis.comm.controller.BaseController;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St020ClinicPatientsService;
import com.nis.zg.service.Zg031SqksService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DbhzlbController extends BaseController {
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

	@RequestMapping({"/cdc/f_view/dbhzlb"})
	public String b(HttpServletRequest request, ModelMap modelMap, String patientType, String dateType,
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
		return "dbPatientList";
	}
}