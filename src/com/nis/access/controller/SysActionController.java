package com.nis.access.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.SysAction;
import com.nis.access.service.SysActionService;
import com.nis.comm.controller.BaseController;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.bj;
import com.nis.comm.utils.l;
import com.nis.comm.utils.z;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysActionController extends BaseController {
	@Autowired
	private SysActionService l;

	@RequestMapping({"sysAction/f_json/report_saveOrUpdate"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String reportName, String depNo,
			String reportType, String depName) {
		AcAccount ac = (AcAccount) this.b(request);
		SysAction sysAction = new SysAction();
		String id = z.a(bg.mx);
		sysAction.setId(id);
		sysAction.setSourceHospital(ac.getUnitId());
		sysAction.setSourceDepno(ac.getDepNo() != null && !"".equals(ac.getDepNo()) ? ac.getDepNo() : "0");
		sysAction.setSourceUser(ac.getUsername());
		sysAction.setFuncType(bj.om.getValue());
		sysAction.setFuncNo(reportName);
		HashMap map = new HashMap();
		map.put("depNo", depNo);
		map.put("depName", "全院".equals(depName) ? "" : depName);
		map.put("reportType", reportType);
		String sysactionjson = com.nis.comm.utils.l.toString(map);
		SysAction sysAction1 = this.l.findSysAction(sysAction);
		if (sysAction1 != null && sysAction1.getId() != null) {
			sysAction1.setUpdateTime(new Date());
			sysAction1.setContent(sysactionjson);
			this.l.update(sysAction1);
		} else {
			sysAction.setAddTime(new Date());
			sysAction.setContent(sysactionjson);
			this.l.save(sysAction);
		}

	}

	@RequestMapping({"sysAction/f_json/questShareDepNo_saveOrUpdate"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String funNo, String depNo,
			String depName) {
		AcAccount ac = (AcAccount) this.b(request);
		SysAction sysAction = new SysAction();
		String id = z.a(bg.mx);
		sysAction.setId(id);
		sysAction.setSourceHospital(ac.getUnitId());
		sysAction.setSourceDepno(ac.getDepNo() != null && !"".equals(ac.getDepNo()) ? ac.getDepNo() : "0");
		sysAction.setSourceUser(ac.getUsername());
		sysAction.setFuncType(bj.on.getValue());
		sysAction.setFuncNo(funNo);
		HashMap map = new HashMap();
		map.put("depNo", depNo);
		map.put("depName", depName);
		String sysactionjson = com.nis.comm.utils.l.toString(map);
		SysAction sysAction1 = this.l.findSysAction(sysAction);
		if (sysAction1 != null) {
			sysAction1.setUpdateTime(new Date());
			sysAction1.setContent(sysactionjson);
			this.l.update(sysAction1);
		} else {
			sysAction.setAddTime(new Date());
			sysAction.setContent(sysactionjson);
			this.l.save(sysAction);
		}

	}
}