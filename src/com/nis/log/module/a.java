package com.nis.log.module;

import com.nis.comm.enums.ap;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.z;
import com.nis.log.cache.SqlLogCache;
import com.nis.log.entity.SysOperateLog;
import com.nis.log.service.SysOperateLogService;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class a {
	private String tK;
	private SqlLogCache tL;
	private ap tM;
	private String unitId;
	private String username;
	private String ip;
	private String content;

	public a() {
		this.init();
	}

	public a(ap operateAction, String ip, String unitId, String username) {
		this.tM = operateAction;
		this.ip = ip;
		this.unitId = unitId;
		this.username = username;
		this.init();
	}

	private void init() {
		this.tK = UUID.randomUUID().toString();
		Thread.currentThread().setName(this.tK);
		this.tL = (SqlLogCache) AppContextUtil.getInstance().getBean(SqlLogCache.class);
	}

	public String getUniqueId() {
		return this.tK;
	}

	private List<String> getSqls() {
		List sqls = this.tL.bH(this.getUniqueId());
		return sqls;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSqlString() {
		List sqls = this.getSqls();
		StringBuffer sqlBuffer = new StringBuffer();
		if (sqls != null) {
			Iterator arg3 = sqls.iterator();

			while (arg3.hasNext()) {
				String sql = (String) arg3.next();
				sqlBuffer.append(sql).append("\t");
			}
		}

		return sqlBuffer.toString();
	}

	public void save() {
		String sqls = this.getSqlString();
		this.clear();
		SysOperateLogService logService = (SysOperateLogService) AppContextUtil.getInstance()
				.getBean(SysOperateLogService.class);
		SysOperateLog sysOperateLog = new SysOperateLog(this.unitId, this.username, this.tM.getValue(), this.content,
				sqls, this.ip);
		sysOperateLog.setId(z.a(bg.mp));
		logService.save(sysOperateLog);
	}

	public void clear() {
		this.tL.bJ(this.getUniqueId());
	}
}