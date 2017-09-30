package com.nis.comm.service.impl;

import com.nis.comm.enums.Param;
import com.nis.comm.service.LicenseService;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.f;
import com.nis.param.service.SysParamService;
import com.nis.param.service.impl.SysParamServiceImpl;

public class LicenseServiceImpl implements LicenseService {
	public boolean t() {
		try {
			System.out.println("******************开始验证注册码！***************");
			SysParamService ex = (SysParamService) AppContextUtil.getInstance().getBean(SysParamServiceImpl.class);
			String license = EncryptUtils.V(ex.findByParamCode(Param.NIS_SYSTEM_HOSPTIAL_LICENSE));
			if (!license.contains("@@")) {
				System.out.println("******************验证码格式错误！***************");
				return false;
			} else if (f.formatDate(f.getCurDate()).compareTo(license.split("@@")[1]) > 0) {
				System.out.println("******************您的注册码已过期！***************");
				return false;
			} else {
				System.out.println("******************注册码验证成功！***************");
				return true;
			}
		} catch (Exception arg2) {
			System.out.println("******************验证码验证出错！***************");
			return false;
		}
	}
}