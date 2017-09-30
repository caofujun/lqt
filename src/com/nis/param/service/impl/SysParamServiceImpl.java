package com.nis.param.service.impl;

import com.nis.comm.enums.Param;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.param.cache.SysParamCache;
import com.nis.param.dao.SysParamDao;
import com.nis.param.entity.SysParam;
import com.nis.param.service.SysParamService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysParamServiceImpl implements SysParamService {
	private static final Logger logger = Logger.getLogger(SysParamServiceImpl.class);
	@Autowired
	private SysParamDao wo;
	@Autowired
	private SysParamCache wp;
	@Autowired
	private SysDictService p;

	public void save(SysParam sysParam) {
		sysParam.setId(z.a(bg.mq));
		this.wo.save(sysParam);
		this.wp.a(sysParam);
		this.p.updateLastTimebyCode("data_init", "CSCSH", f.getCurDate());
	}

	public void delete(String id) {
		SysParam sysParam = this.get(id);
		this.wo.delete(id);
		this.wp.a(sysParam);
		this.p.updateLastTimebyCode("data_init", "CSCSH", f.getCurDate());
	}

	public void update(SysParam sysParam) {
		this.wo.update(sysParam);
		this.wp.a(sysParam);
		this.p.updateLastTimebyCode("data_init", "CSCSH", f.getCurDate());
	}

	public SysParam get(String id) {
		return this.wo.get(id);
	}

	public List<SysParam> pageQuery(SysParam sysParam) {
		List data = this.wo.findSysParam(sysParam);
		ArrayList listTemp = new ArrayList();
		Iterator arg4 = data.iterator();

		SysParam sysParam2;
		while (arg4.hasNext()) {
			sysParam2 = (SysParam) arg4.next();
			sysParam2.setParamType(this.p.k("param_type", sysParam2.getParamType(), (String) null));
			sysParam2.setScopeLevelName(this.p.k("data_scope", sysParam2.getScopeLevel(), (String) null));
			if (sysParam2.getParamType() != null) {
				listTemp.add(sysParam2);
			}
		}

		arg4 = data.iterator();

		while (arg4.hasNext()) {
			sysParam2 = (SysParam) arg4.next();
			if (sysParam2.getParamType() == null) {
				sysParam2.setParamType("未分配类型参数");
				listTemp.add(sysParam2);
			}
		}

		return listTemp;
	}

	public String findByParamCode(Param param, String unitId, String depNo, String userName) {
		String value = this.s(param.getCode(), unitId, depNo, userName);
		if (ab.isEmpty(value)) {
			value = param.getValue();
		}

		String value2;
		if (param.getCode().equals(Param.NIS_HTTP_URL.getCode())) {
			if ("0".equals(value)) {
				value = ab.getNisHttpUrl();
			}

			value2 = this.s(Param.NIS_PROJECT_NAME.getCode(), unitId, depNo, userName);
			if (ab.isEmpty(value2)) {
				value2 = Param.NIS_PROJECT_NAME.getValue();
			}

			return value + "/" + value2;
		} else if (param.getCode().equals(Param.NIS_REPORT_URL.getCode())) {
			value2 = this.s(Param.NIS_HTTP_URL.getCode(), unitId, depNo, userName);
			if (ab.isEmpty(value2)) {
				value2 = param.getValue();
			}

			return value2 + "/" + value;
		} else if (param.getCode().equals(Param.NIS_MSG_OCX_URL.getCode())) {
			value2 = this.s(Param.NIS_HTTP_URL.getCode(), unitId, depNo, userName);
			if (ab.isEmpty(value2)) {
				value2 = param.getValue();
			}

			return value2 + "/" + value;
		} else if (param.getCode().equals(Param.NIS_MSG_URL.getCode())) {
			value2 = this.s(Param.NIS_HTTP_URL.getCode(), unitId, depNo, userName);
			if (ab.isEmpty(value2)) {
				value2 = param.getValue();
			}

			return value2 + "/" + value;
		} else if (param.getCode().equals(Param.NIS_BROWSER_DOWNLOAD_URL.getCode())) {
			value2 = this.s(Param.NIS_HTTP_URL.getCode(), unitId, depNo, userName);
			if (ab.isEmpty(value2)) {
				value2 = param.getValue();
			}

			return value2 + "/" + value;
		} else if (param.getCode().equals(Param.NIS_CZSC_M_URL.getCode())) {
			value2 = this.s(Param.NIS_HTTP_URL.getCode(), unitId, depNo, userName);
			if (ab.isEmpty(value2)) {
				value2 = param.getValue();
			}

			return value2 + "/" + value;
		} else if (param.getCode().equals(Param.NIS_CZSC_C_URL.getCode())) {
			value2 = this.s(Param.NIS_HTTP_URL.getCode(), unitId, depNo, userName);
			if (ab.isEmpty(value2)) {
				value2 = param.getValue();
			}

			return value2 + "/" + value;
		} else if (param.getCode().equals(Param.NIS_IE_FLASHPLAYER_URL.getCode())) {
			value2 = this.s(Param.NIS_HTTP_URL.getCode(), unitId, depNo, userName);
			if (ab.isEmpty(value2)) {
				value2 = param.getValue();
			}

			return value2 + "/" + value;
		} else if (param.getCode().equals(Param.NIS_CHROME_FLASHPLAYER_URL.getCode())) {
			value2 = this.s(Param.NIS_HTTP_URL.getCode(), unitId, depNo, userName);
			if (ab.isEmpty(value2)) {
				value2 = param.getValue();
			}

			return value2 + "/" + value;
		} else {
			return value;
		}
	}

	private String s(String code, String unitId, String depNo, String userName) {
		SysParam sysParam = null;

		try {
			sysParam = this.wp.findByParamCode(code, unitId, depNo, userName);
		} catch (Exception arg6) {
			logger.error("查询系统参数失败；失败原因：" + arg6.getMessage());
			sysParam = null;
		}

		return sysParam != null ? sysParam.getParamValue() : null;
	}

	public String findByParamCode(Param param) {
		return this.findByParamCode(param, (String) null, (String) null, (String) null);
	}

	public List<SysParam> getAll() {
		return this.wo.getAll();
	}

	public List<SysParam> findSysParamList(SysParam sysParam) {
		return this.wo.findSysParamList(sysParam);
	}

	public String findByParamCode(Param param, String unitId) {
		return this.findByParamCode(param, unitId, (String) null, (String) null);
	}

	public SysParam getByParamCode(String id) {
		return this.wo.findByParamCode(id, (String) null, (String) null, (String) null);
	}
}