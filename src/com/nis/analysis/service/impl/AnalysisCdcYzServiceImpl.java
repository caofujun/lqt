package com.nis.analysis.service.impl;

import com.nis.analysis.model.a;
import com.nis.analysis.service.AnalysisCdcYzService;
import com.nis.cdc.entity.CtgSys009Yj;
import com.nis.cdc.service.CtgSys009YjService;
import com.nis.comm.utils.af;
import com.nis.dict.service.SysDictService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St004YzxxbService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnalysisCdcYzServiceImpl implements AnalysisCdcYzService {
	private static final Logger c = Logger.getLogger(AnalysisCdcYzServiceImpl.class);
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private CtgSys009YjService bi;
	@Autowired
	private SysDictService p;
	@Autowired
	private TaskJobService aU;

	public a c(boolean errExit) {
		a rs = new a();
		ArrayList errList = new ArrayList();
		int sucnum = 0;
		byte errnum = 0;
		byte totalcount = 0;

		try {
			boolean ex = false;
			TaskJob taskJob = this.aU.findByLink("analysis/cdcYz");
			if (taskJob == null || taskJob.getStatus().intValue() != 0) {
				ex = false;
				rs.setSuccess(false);
				rs.setInfo("传染病医嘱分析执行失败！原因：参数未启用！");
				System.err.println("传染病医嘱分析执行失败！原因：参数未启用！");
				return rs;
			}

			ex = true;
			if (ex) {
				Calendar calendar = Calendar.getInstance();
				St004Yzxxb yz = new St004Yzxxb();
				yz.setQueryEndDate(calendar.getTime());
				calendar.add(5, -3);
				yz.setQueryStartDate(calendar.getTime());
				List analyzisList = this.bu.findForYzAnalyzis(yz);
				if (!analyzisList.isEmpty()) {
					List ctg9Yz = this.h(analyzisList);

					CtgSys009Yj ctgSys009Yj;
					for (Iterator arg13 = ctg9Yz.iterator(); arg13.hasNext(); this.bi.save(ctgSys009Yj)) {
						ctgSys009Yj = (CtgSys009Yj) arg13.next();
						++sucnum;
						List exit = this.bi.isExit(ctgSys009Yj);
						if (exit != null && !exit.isEmpty()) {
							this.bi.delete(((CtgSys009Yj) exit.get(0)).getMasterid());
						}
					}
				} else {
					rs.setInfo("没有包含死亡字眼的医嘱数据！");
				}
			}

			rs.setSuccess(true);
		} catch (Exception arg15) {
			errList.add(arg15.getMessage());
			rs.setInfo("传染病病程分析执行失败！原因：" + arg15.getMessage());
			System.err.println("传染病病程分析执行失败！原因：" + arg15.getMessage());
			c.error("analysisCdcBc", arg15);
		}

		rs.setErrMsgList(errList);
		rs.setSucTotals(sucnum);
		rs.setErrTotals(errnum);
		rs.setTotals(totalcount);
		return rs;
	}

	public List<CtgSys009Yj> h(List<St004Yzxxb> yzList) {
		ArrayList list = new ArrayList();
		CtgSys009Yj yj = new CtgSys009Yj();
		if (yzList != null && yzList.size() > 0) {
			Iterator arg4 = yzList.iterator();

			while (arg4.hasNext()) {
				St004Yzxxb st4Yzxxb = (St004Yzxxb) arg4.next();
				St003Cryxxb st003Cryxxb = this.bg.get(st4Yzxxb.getZyid());
				if (st003Cryxxb != null) {
					yj.setMasterid(af.getUUID32());
					yj.setMzzyid(st003Cryxxb.getZyid());
					yj.setPatientId(st003Cryxxb.getPatientId());
					yj.setPatientName(st003Cryxxb.getPatientName());
					yj.setPatientType("ZY");
					yj.setYjcode("death");
					yj.setYjname("死亡");
					yj.setYjdt(new Date());
					yj.setYjsource("病程");
					yj.setYjcontent(st4Yzxxb.getOrderName());
					yj.setSourceId(st4Yzxxb.getId());
					list.add(yj);
				}
			}
		}

		return list;
	}
}