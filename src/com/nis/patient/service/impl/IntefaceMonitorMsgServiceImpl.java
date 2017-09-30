package com.nis.patient.service.impl;

import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.i;
import com.nis.comm.utils.l;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.msg.service.NyMessageUserDetailService;
import com.nis.organization.entity.Unit;
import com.nis.organization.service.UnitService;
import com.nis.param.service.SysParamService;
import com.nis.patient.controller.PatientTaskController;
import com.nis.patient.service.InterfaceMonitorMsgService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntefaceMonitorMsgServiceImpl implements InterfaceMonitorMsgService {
	private static final Logger logger = Logger.getLogger(PatientTaskController.class);
	@Autowired
	private SysParamService j;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private NyMessageUserDetailService cT;
	@Autowired
	private UnitService se;

	public void cq(String msgContent) {
		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_MSG_TYPE);
			if ("local".equalsIgnoreCase(ex)) {
				logger.info("发送本地消息!");
				this.cu(msgContent);
			} else {
				logger.info("发送远程消息!");
				this.cv(msgContent);
			}
		} catch (Exception arg2) {
			logger.error("接口监控异常消息发送失败: \n" + arg2.getMessage());
			arg2.printStackTrace();
		}

	}

	private void cu(String msgContent) {
		try {
			String ex = this.j.findByParamCode(Param.NIS_GLY_ID);
			String sendUserName = this.j.findByParamCode(Param.NIS_GLY_NAME);
			String receiveDeptId = this.j.findByParamCode(Param.NIS_GK_DEPTID);
			this.cV.a((String) null, (String) null, ex, sendUserName, msgContent, (String[]) null,
					new String[]{receiveDeptId}, al.jt.getValue(), (String) null);
		} catch (Exception arg4) {
			logger.error("接口监控异常消息发送失败: \n" + arg4.getMessage());
			arg4.printStackTrace();
		}

	}

	private void cv(String msgContent) {
		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_REMOTE_URL);
			List unitList = this.se.getAll();
			HashMap msgData = new HashMap();
			msgData.put("msgTime", f.formatDate(f.getCurDate(), "yyyy-MM-dd HH:mm:ss"));
			msgData.put("msgContent", msgContent);
			if (unitList != null && !unitList.isEmpty()) {
				msgData.put("unitId", ((Unit) unitList.get(0)).getUnitId());
				msgData.put("unitName", ((Unit) unitList.get(0)).getHospName());
			}

			DefaultHttpClient httpclient = i.getHttpClient();
			String res = i.a(httpclient, msgData, ex);
			if (ab.isEmpty(res)) {
				logger.error("发送患者接口监控消息失败：未返回任何结果!");
				return;
			}

			Map backResul = (Map) l.ar(res);
			String result = "" + backResul.get("result");
			if (!"success".equals(result)) {
				logger.error("发送患者接口监控消息失败：" + backResul.get("msg"));
			}
		} catch (Exception arg8) {
			logger.error("接口监控异常消息发送失败: " + arg8.getMessage());
			arg8.printStackTrace();
		}

	}
}