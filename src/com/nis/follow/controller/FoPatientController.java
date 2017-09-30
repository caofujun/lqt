package com.nis.follow.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.follow.entity.FoPatient;
import com.nis.follow.service.FoPatientService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoPatientController extends BaseController {
	@Autowired
	private FoPatientService qW;

	@RequestMapping({"/foPatient/f_json/save"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, FoPatient foPatient) {
		Result result = null;

		try {
			result = new Result();
			AcAccount e = (AcAccount) this.b(request);
			foPatient.setFollowTime(new Date());
			foPatient.setFollowName(e.getUsername());
			this.qW.save(foPatient);
			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/foPatient/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String patientId) {
		Result result = null;

		try {
			result = new Result();
			this.qW.deleteByPatientId(patientId, (String) null);
			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/foPatient/f_json/findByDeptIdDetail"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, FoPatient foPatient) {
		List cryxxbList = null;
		if (foPatient != null) {
			cryxxbList = this.qW.findByDeptIdDetail(foPatient);
		}

		this.a(response, cryxxbList);
	}
}