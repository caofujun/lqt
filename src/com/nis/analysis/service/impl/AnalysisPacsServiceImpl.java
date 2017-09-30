package com.nis.analysis.service.impl;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.model.a;
import com.nis.analysis.model.d;
import com.nis.analysis.service.AnalysisPacsService;
import com.nis.analysis.service.AnalysisTextService;
import com.nis.analysis.service.NyUnanalyzeBcService;
import com.nis.analysis.service.NyUnanalyzeDictService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.analysis.service.Zg007DictService;
import com.nis.comm.enums.ah;
import com.nis.comm.utils.f;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.patient.entity.St014Pacs;
import com.nis.patient.service.St014PacsService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AnalysisPacsServiceImpl implements AnalysisPacsService {
	private static final Logger c = Logger.getLogger(AnalysisPacsServiceImpl.class);
	private int bw = 0;
	private int bx = 0;
	@Autowired
	private Zg007DictService X;
	@Autowired
	private Gr018YsgrysService aR;
	@Autowired
	private St014PacsService bj;
	@Autowired
	private NyUnanalyzeBcService aT;
	@Autowired
	private NyUnanalyzeDictService ba;
	@Autowired
	private AnalysisTextService bb;
	@Autowired
	private SysDictService p;
	@Autowired
	private TaskJobService aU;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysJudgeLogService J;

	public a b(boolean errExit, String id) {
		a rs = new a();
		ArrayList errList = new ArrayList();
		int findcs = 0;
		SysJudgeLog sysJudgeLog = this.J.get(id);
		TaskJob taskJob = this.aU.findByLink("f_task/ysgrysPacs.shtml");
		if (taskJob != null && taskJob.getStatus().intValue() == 50) {
			sysJudgeLog.setStatus("3");
			this.J.update(sysJudgeLog);
			return rs;
		} else {
			try {
				int ex = this.bj.getPacsCount();
				sysJudgeLog.setTotalCount(Integer.valueOf(ex));
				sysJudgeLog.setSuccessCount(Integer.valueOf(0));
				this.J.update(sysJudgeLog);
				String keyType = "A9B8E397C091559F";
				String dictType = "977C5BB729F88946";
				Map keyfd = this.X.f(keyType, dictType);
				keyType = "86A8433C4635E2ED";
				Map keykd = this.X.f(keyType, dictType);
				keyType = "0C4BC0DB8EDC474E";
				Map keyjw = this.X.f(keyType, dictType);
				keyType = "52B669C0526DBADC";
				Map keygj = this.X.f(keyType, dictType);
				keyType = "CB7C56987F20CA5F";
				Map keybw = this.X.f(keyType, dictType);
				keyType = "642D3D3B424E2D80";
				Map keysl = this.X.f(keyType, dictType);
				Map unBc = this.aT.getUnBcMap();
				Map unDict = this.ba.getUnDictMap();
				HashMap bjColor = new HashMap();
				List sysdictList = this.p.u("YJBJCYS", "0");
				Iterator rownum = sysdictList.iterator();

				while (rownum.hasNext()) {
					SysDict mapList = (SysDict) rownum.next();
					bjColor.put(mapList.getDictCode(), mapList.getDictName());
				}

				ArrayList arg48 = new ArrayList();
				arg48.add(keyfd);
				arg48.add(keykd);
				arg48.add(keyjw);
				arg48.add(keygj);
				arg48.add(keybw);
				arg48.add(keysl);
				arg48.add(unBc);
				arg48.add(unDict);
				byte arg49 = 101;
				boolean runflag = true;
				String sourceStr = "";
				String analysisStr = "";
				this.bb.f(arg48);
				this.bb.a(bjColor);

				label125 : while (true) {
					while (true) {
						if (!runflag || findcs >= 4500) {
							break label125;
						}

						taskJob = this.aU.findByLink("f_task/ysgrysPacs.shtml");
						if (taskJob == null || taskJob.getStatus().intValue() != 50) {
							runflag = true;
							++findcs;
							List list = this.bj.d(arg49);
							if (list == null || list.size() <= 0) {
								runflag = false;
								break label125;
							}

							runflag = true;
							Date curDate = new Date();
							boolean ysflag = false;

							for (int i = 0; i < list.size(); ++i) {
								St014Pacs pacs = (St014Pacs) list.get(i);
								Date enterDate = pacs.getCheckAt();
								if (enterDate != null && curDate.getTime() >= enterDate.getTime()) {
									String enterDateStr = f.formatDate(enterDate);
									Date enterdt1 = f.l(enterDateStr, "yyyy-MM-dd");
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(enterdt1);
									calendar.add(13, 5);
									Date enterdt = calendar.getTime();
									String dataDateStr = "";
									String sjid = "";
									sourceStr = "检查所见；" + pacs.getCheckDesc() + "<p></p>检查总结；" + pacs.getCheckImpr();
									com.nis.analysis.model.f anrs = this.bb.b("", sourceStr);
									if (anrs.getSuccess()) {
										analysisStr = anrs.getAnalysisResultText();
										analysisStr = analysisStr.replaceAll("检查所见；", "检查所见：");
										analysisStr = analysisStr.replaceAll("检查总结；", "检查总结：");
										pacs.setShowAnalResult(analysisStr);
										pacs.setAnalAt(new Date());
										pacs.setAnalFlag(Long.valueOf(1L));
										List ysList = this.aR.E(pacs.getZyid(), enterDateStr);
										ArrayList gr018YsInsert = new ArrayList();
										ArrayList gr018YsUpdate = new ArrayList();
										if (anrs.getAnalysisflag() && anrs.getSuccess()) {
											Date curdate = new Date();
											Iterator arg43 = anrs.getKeyMap().entrySet().iterator();

											while (arg43.hasNext()) {
												Entry k = (Entry) arg43.next();
												ysflag = false;
												Gr018Ysgrys ys = new Gr018Ysgrys();
												ys.setDataDate(enterdt);
												ys.setDataForm("影像");
												ys.setDataType("文本");
												ys.setElementId(((d) k.getValue()).getElementid());
												ys.setElementName(((d) k.getValue()).getElementname());
												ys.setMonitorAt(curdate);
												ys.setZyid(pacs.getZyid());
												ys.setOriginalContent(((d) k.getValue()).getOriginalContent());
												ys.setState(Integer.valueOf(0));
												ys.setSjId(pacs.getId());
												Iterator arg46 = ysList.iterator();

												while (arg46.hasNext()) {
													Gr018Ysgrys grys = (Gr018Ysgrys) arg46.next();
													dataDateStr = f.formatDate(grys.getDataDate());
													if (((d) k.getValue()).getElementid().equals(grys.getElementId())
															&& enterDateStr.equals(dataDateStr) && ((d) k.getValue())
																	.getElementid().equals(grys.getElementId())) {
														ys = grys;
														sjid = grys.getSjId();
														if (sjid == null) {
															sjid = "";
														}

														if (sjid.indexOf(pacs.getId()) == -1) {
															if ("".equals(sjid)) {
																grys.setSjId(pacs.getId());
															} else {
																grys.setSjId(grys.getSjId() + "," + pacs.getId());
															}
														}

														grys.setState(Integer.valueOf(0));
														grys.setMonitorAt(curDate);
														ysflag = true;
														break;
													}
												}

												if (ysflag) {
													gr018YsUpdate.add(ys);
												} else {
													gr018YsInsert.add(ys);
												}
											}
										}

										this.a(pacs, gr018YsInsert, gr018YsUpdate, sysJudgeLog);
										this.aR.bO(pacs.getZyid());
									} else {
										++this.bx;
										if (errExit) {
											errList.add(anrs.getErrMsg());
											break;
										}

										errList.add(anrs.getErrMsg());
									}

									if (anrs != null) {
										anrs = null;
									}
								}
							}
						} else {
							runflag = false;
							sysJudgeLog.setStatus("3");
						}
					}
				}

				sysJudgeLog.setFailCount(Integer.valueOf(this.bx));
				sysJudgeLog.setEndTime(new Date());
				if (this.bx == 0) {
					sysJudgeLog.setStatus("1");
				}

				this.J.update(sysJudgeLog);
			} catch (Exception arg47) {
				++this.bx;
				c.error("影像分析感染因素异常", arg47);
				this.aV.a(ah.iG, "影像分析感染因素异常AnalysisPacsServiceImpl.analysisPacs", sysJudgeLog.getId(),
						arg47.getMessage());
			}

			rs.setErrMsgList(errList);
			rs.setSucTotals(this.bw);
			rs.setErrTotals(this.bx);
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
	public void a(St014Pacs pacs, List<Gr018Ysgrys> gr018YsInsert, List<Gr018Ysgrys> gr018YsUpdate,
			SysJudgeLog sysJudgeLog) {
		try {
			++this.bw;
			sysJudgeLog.setSuccessCount(Integer.valueOf(this.bw));
			sysJudgeLog.setEndTime(new Date());
			this.J.update(sysJudgeLog);
			this.bj.updateAnalResultTest(pacs);
			if (gr018YsInsert.size() > 0) {
				this.aR.saveList(gr018YsInsert);
			}

			if (gr018YsUpdate.size() > 0) {
				this.aR.K(gr018YsUpdate);
			}
		} catch (Exception arg5) {
			++this.bx;
			sysJudgeLog.setFailCount(Integer.valueOf(this.bx));
			sysJudgeLog.setStatus("2");
			this.J.update(sysJudgeLog);
			arg5.printStackTrace();
			c.error("影像分析感染因素异常AnalysisPacsServiceImpl.analysisPacs", arg5);
		}

	}
}