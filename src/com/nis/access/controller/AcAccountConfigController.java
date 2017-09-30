package com.nis.access.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcAccountConfig;
import com.nis.access.service.AcAccountConfigService;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AcAccountConfigController extends BaseController {
	@Autowired
	private AcAccountConfigService b;

	@RequestMapping({"/acAccountConfig/f_json/save"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String userId, String configKey,
			String configValue) {
		Result result = null;

		try {
			if (userId == null) {
				AcAccount e = (AcAccount) this.b(request);
				userId = e.getUserId();
			}

			AcAccountConfig e1 = this.b.getByKey(userId, configKey);
			if (e1 == null) {
				e1 = new AcAccountConfig();
				e1.setUserId(userId);
				e1.setConfigKey(configKey);
				e1.setConfigValue(configValue);
				this.b.save(e1);
			} else {
				e1.setConfigValue(configValue);
				this.b.update(e1);
			}

			result = new Result("success");
		} catch (Exception arg7) {
			result = new Result("error", "保存用户配置信息失败!");
			this.a(response, result);
			return;
		}

		this.a(response, result);
	}
}