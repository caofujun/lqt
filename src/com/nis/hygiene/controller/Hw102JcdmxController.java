package com.nis.hygiene.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.hygiene.entity.Hw102Jcdmx;
import com.nis.hygiene.service.Hw008XmsqService;
import com.nis.hygiene.service.Hw102JcdmxService;
import com.nis.hygiene.service.Hw103JcdjgService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hw102JcdmxController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw102JcdmxController.class);
	@Autowired
	private Hw102JcdmxService rE;
	@Autowired
	private Hw103JcdjgService rG;
	@Autowired
	private Hw008XmsqService rx;

	@RequestMapping({"/hw102Jcdmx/f_json/delSampleInfo"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除采样信息")
	public void A(HttpServletRequest request, HttpServletResponse response, String id, String djDeptId) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(id)) {
				AcAccount e = (AcAccount) this.b(request);
				Hw102Jcdmx hw102Jcdmx = this.rE.get(id);
				int count = this.rx.judgeReportPermissions(e.getUsername(), e.getDepNo(), djDeptId,
						hw102Jcdmx.getClassId().substring(0, 2));
				if (count == 0) {
					result = new Result("error", "没有权限");
				} else {
					this.rE.bw(id);
					result = new Result("success");
				}
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw102Jcdmx/f_json/copySampleInfo"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--复制并新增采样信息")
	public void B(HttpServletRequest request, HttpServletResponse response, String id, String djDeptId) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(id)) {
				AcAccount e = (AcAccount) this.b(request);
				Hw102Jcdmx hw102Jcdmx = this.rE.get(id);
				if (hw102Jcdmx == null) {
					result = new Result("error", "数据不存在");
				} else {
					int count = this.rx.judgeReportPermissions(e.getUsername(), e.getDepNo(), djDeptId,
							hw102Jcdmx.getClassId().substring(0, 2));
					if (count == 0) {
						result = new Result("error", "没有权限");
					} else {
						this.rE.d(hw102Jcdmx);
						result = new Result("success");
						result.setData(hw102Jcdmx.getId());
					}
				}
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw102Jcdmx/f_json/getDjIdById"})
	@ResponseBody
	public void D(HttpServletRequest request, HttpServletResponse response, String id) {
		if (ab.isNotEmpty(id)) {
			Hw102Jcdmx jcdmx = this.rE.get(id);
			if (jcdmx != null) {
				this.a(response, jcdmx.getDjId());
			}
		}

	}
}