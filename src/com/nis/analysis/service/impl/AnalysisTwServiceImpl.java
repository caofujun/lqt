package com.nis.analysis.service.impl;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.model.a;
import com.nis.analysis.service.AnalysisTwService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ah;
import com.nis.log.service.SysLogService;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.param.service.SysParamService;
import com.nis.patient.service.St006TwxxService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AnalysisTwServiceImpl implements AnalysisTwService {
	private static final Logger c = Logger.getLogger(AnalysisTwServiceImpl.class);
	private Double tw = Double.valueOf(38.0D);
	private int bw = 0;
	private int bx = 0;
	private int cp = 0;
	@Autowired
	private St006TwxxService bN;
	@Autowired
	private Gr018YsgrysService aR;
	@Autowired
	private TaskJobService aU;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysJudgeLogService J;

	public a c(boolean errExit, String id) {
		a rs = new a();
		boolean runflag = true;
		SysJudgeLog sysJudgeLog = this.J.get(id);
		TaskJob taskJob = this.aU.findByLink("f_task/tw.shtml");
		if (taskJob != null && taskJob.getStatus().intValue() == 50) {
			sysJudgeLog.setStatus("3");
			sysJudgeLog.setEndTime(new Date());
			this.J.update(sysJudgeLog);
			return rs;
		} else {
			this.tw = Double.valueOf(Double.parseDouble(this.j.findByParamCode(Param.NIS_TW_FR)));
			int total = this.aR.findTwCount(this.tw);
			sysJudgeLog.setTotalCount(Integer.valueOf(total));
			this.J.update(sysJudgeLog);

			while (runflag && this.cp < 4500) {
				taskJob = this.aU.findByLink("f_task/tw.shtml");
				if (taskJob != null && taskJob.getStatus().intValue() == 50) {
					runflag = false;
					sysJudgeLog.setStatus("3");
				} else {
					runflag = true;
					++this.cp;
					List gr018List = this.aR.findTwList(this.tw);
					if (gr018List.size() > 0) {
						Iterator arg9 = gr018List.iterator();

						while (arg9.hasNext()) {
							Gr018Ysgrys gr018 = (Gr018Ysgrys) arg9.next();
							this.a(gr018, sysJudgeLog);
						}
					} else {
						runflag = false;
					}
				}
			}

			rs.setSucTotals(this.bw);
			rs.setErrTotals(this.bx);
			rs.setTotals(total);
			if (this.bw > total) {
				this.bw = total;
			}

			sysJudgeLog.setSuccessCount(Integer.valueOf(this.bw));
			sysJudgeLog.setFailCount(Integer.valueOf(this.bx));
			sysJudgeLog.setEndTime(new Date());
			if (this.bx == 0) {
				sysJudgeLog.setStatus("1");
			}

			this.J.update(sysJudgeLog);
			return rs;
		}
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Gr018Ysgrys gr018, SysJudgeLog sysJudgeLog) {
		try {
			int ex = this.aR.a(gr018.getZyid(), gr018.getDataDate(), gr018.getElementId(), gr018.getDataForm());
			if (ex == 0) {
				this.aR.save(gr018);
			} else {
				this.aR.update(gr018);
			}

			this.bN.updateAnalFlag(gr018.getSjId(), Integer.valueOf(1), new Date());
			++this.bw;
			sysJudgeLog.setSuccessCount(Integer.valueOf(this.bw));
			sysJudgeLog.setEndTime(new Date());
			this.J.update(sysJudgeLog);
		} catch (Exception arg3) {
			++this.bx;
			sysJudgeLog.setFailCount(Integer.valueOf(this.bx));
			sysJudgeLog.setStatus("2");
			this.J.update(sysJudgeLog);
			c.error("预警分析体温异常", arg3);
			this.bN.updateAnalFlag(gr018.getSjId(), Integer.valueOf(9), new Date());
			this.aV.a(ah.iG, "预警分析体温异常AnalysisTwServiceImpl.analysisTw，zyid=" + gr018.getZyid(), sysJudgeLog.getId(),
					arg3.getMessage());
		}

	}
}