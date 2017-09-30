package com.nis.homepage.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.m;
import com.nis.comm.utils.z;
import com.nis.homepage.dao.SysHpComponentDao;
import com.nis.homepage.entity.SysHpComponent;
import com.nis.homepage.service.SysHpComponentService;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysHpComponentServiceImpl implements SysHpComponentService {
	@Autowired
	private SysHpComponentDao rk;

	public Result<String> a(SysHpComponent sysHpComponent) {
		Result result = new Result();
		if ((!StringUtils.isBlank(sysHpComponent.getId())
				|| this.getComponentCode(sysHpComponent.getComponentCode()) == null)
				&& (!StringUtils.isNotBlank(sysHpComponent.getId())
						|| this.getComponentCode(sysHpComponent.getComponentCode()) == null
						|| this.getComponentCode(sysHpComponent.getComponentCode()).getId()
								.equals(sysHpComponent.getId()))) {
			if (StringUtils.isBlank(sysHpComponent.getId())) {
				sysHpComponent.setId(z.a(bg.mu));
				sysHpComponent.setLayoutStatus(m.ge.getValue());
				this.rk.save(sysHpComponent);
			} else {
				this.rk.update(sysHpComponent);
			}

			result.setResult("success");
			return result;
		} else {
			result.setResult("error_exist");
			result.setMsg("该编码下已存在组件");
			return result;
		}
	}

	public void delete(String id) {
		this.rk.delete(id);
	}

	public SysHpComponent get(String id) {
		return this.rk.get(id);
	}

	public SysHpComponent getComponentCode(String componentCode) {
		return this.rk.getComponentCode(componentCode);
	}

	public MyPage<SysHpComponent> b(SysHpComponent sysHpComponent) {
		int total = this.rk.findSysHpComponentCount(sysHpComponent);
		List data = null;
		if (total > 0) {
			data = this.rk.findSysHpComponent(sysHpComponent);
		}

		return new MyPage(sysHpComponent.getPage().intValue(), sysHpComponent.getSize().intValue(), total, data);
	}

	public List<SysHpComponent> getAll() {
		return this.rk.getAll();
	}

	public List<SysHpComponent> find(SysHpComponent sysHpComponent) {
		return this.rk.find(sysHpComponent);
	}

	public void updateStatus(String id, String status) {
		this.rk.updateStatus(id, status);
	}
}