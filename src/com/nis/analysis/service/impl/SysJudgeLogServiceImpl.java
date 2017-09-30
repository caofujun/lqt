package com.nis.analysis.service.impl;

import com.nis.analysis.dao.SysJudgeLogDao;
import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.comm.utils.r;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysJudgeLogServiceImpl implements SysJudgeLogService {
	@Autowired
	private SysJudgeLogDao cB;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysLogService aV;

	public void save(SysJudgeLog sysJudgeLog) {
		this.cB.save(sysJudgeLog);
	}

	public void delete(String id) {
		this.cB.delete(id);
	}

	public void update(SysJudgeLog sysJudgeLog) {
		this.cB.update(sysJudgeLog);
	}

	public SysJudgeLog get(String id) {
		return this.cB.get(id);
	}

	public MyPage<SysJudgeLog> b(SysJudgeLog sysJudgeLog) {
		int total = this.cB.findSysJudgeLogCount(sysJudgeLog);
		List data = null;
		if (total > 0) {
			data = this.cB.findSysJudgeLog(sysJudgeLog);

			SysJudgeLog judgeLog;
			for (Iterator arg4 = data.iterator(); arg4.hasNext(); judgeLog
					.setStatusName(this.p.k("judgeStatus", judgeLog.getStatus(), (String) null))) {
				judgeLog = (SysJudgeLog) arg4.next();
				List sysLogList = this.aV.findbyBusinessId(judgeLog.getId());
				if (sysLogList.size() > 0) {
					judgeLog.setStatus("2");
				}
			}
		}

		return new MyPage(sysJudgeLog.getPage().intValue(), sysJudgeLog.getSize().intValue(), total, data);
	}

	public List<SysJudgeLog> getAll() {
		return this.cB.getAll();
	}

	public List<SysJudgeLog> o(String ids) {
		String[] idList = ids.split(",");
		List judgeLogList = this.cB.findList(idList);
		Iterator arg4 = judgeLogList.iterator();

		while (arg4.hasNext()) {
			SysJudgeLog judgeLog = (SysJudgeLog) arg4.next();
			judgeLog.setStatusName(this.p.k("judgeStatus", judgeLog.getStatus(), (String) null));
		}

		return judgeLogList;
	}

	public void a(SysJudgeLog sysJudgeLog) {
	}

	public void e() {
		Long rangeTime = Long.valueOf(15L);
		List dictList = this.p.u("judge_analy_range", (String) null);
		HashMap dictMap = new HashMap();
		Iterator sysJudgeLog = dictList.iterator();

		while (sysJudgeLog.hasNext()) {
			SysDict inExecList = (SysDict) sysJudgeLog.next();
			dictMap.put(inExecList.getDictCode(), r.f(inExecList.getExtParam1()));
		}

		List inExecList1 = this.cB.findInExecRecore();
		Iterator arg5 = inExecList1.iterator();

		while (arg5.hasNext()) {
			SysJudgeLog sysJudgeLog1 = (SysJudgeLog) arg5.next();
			Long range = rangeTime;
			if (dictMap.containsKey(sysJudgeLog1.getJudgeCode())) {
				range = (Long) dictMap.get(sysJudgeLog1.getJudgeCode());
			}

			range = Long.valueOf(range.longValue() * 60L * 1000L);
			Date curr = f.getCurDate();
			if (sysJudgeLog1.getEndTime() != null
					&& sysJudgeLog1.getEndTime().getTime() + range.longValue() < curr.getTime()) {
				sysJudgeLog1.setStatus("1");
				sysJudgeLog1.setEndTime(curr);
				this.cB.updateStatus(sysJudgeLog1);
			}
		}

	}

	public void f() {
		this.cB.updInExecToFinish(new Date());
	}
}