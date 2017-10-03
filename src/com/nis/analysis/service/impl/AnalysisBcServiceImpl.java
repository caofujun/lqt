package com.nis.analysis.service.impl;

import com.nis.analysis.entity.Gr018Bcgrys;
import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.model.a;
import com.nis.analysis.model.d;
import com.nis.analysis.service.AnalysisBcService;
import com.nis.analysis.service.AnalysisTextService;
import com.nis.analysis.service.Gr018BcgrysService;
import com.nis.analysis.service.NyUnanalyzeBcService;
import com.nis.analysis.service.NyUnanalyzeDictService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.analysis.service.Zg007DictService;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ah;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.v;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St008Bcjl;
import com.nis.patient.service.St008BcjlService;
import com.nis.patient.service.St014PacsService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


class AnalysisBcServiceImpl$1
implements Runnable
{
	AnalysisBcServiceImpl bc;
	List aX;
	Date aY;
	String aZ;
	SysJudgeLog bd;
	
	AnalysisBcServiceImpl$1(AnalysisBcServiceImpl paramAnalysisBcServiceImpl, List paramList, Date paramDate, String paramString, SysJudgeLog paramSysJudgeLog) {
		this.bc = paramAnalysisBcServiceImpl;
		this.aX = paramList;
		this.aY = paramDate;
		this.aZ = paramString;
		this.bd = paramSysJudgeLog;
	}
	
	public void run()
	{
	  this.bc.a(this.aX, this.aY, this.aZ, this.bd);
	}
}



@Component
public class AnalysisBcServiceImpl implements AnalysisBcService {
	private static final Logger c = Logger.getLogger(AnalysisBcServiceImpl.class);
	@Autowired
	private Zg007DictService X;
	@Autowired
	private St008BcjlService aP;
	@Autowired
	private Gr018BcgrysService aQ;
	@Autowired
	private Gr018YsgrysService aR;
	@Autowired
	private St014PacsService aS;
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
	private SysParamService j;
	@Autowired
	private SysJudgeLogService J;

	public a a(boolean errExit, String id) {
		SysJudgeLog sysJudgeLog = this.J.get(id);
		a rs = new a();
		ArrayList errList = new ArrayList();
		boolean sucnum = false;
		byte errnum = 0;
		byte findcs = 0;

		try {
			String ex = "A9B8E397C091559F";
			String dictType = "977C5BB729F88946";
			Map keyfd = this.X.f(ex, dictType);
			ex = "86A8433C4635E2ED";
			Map keykd = this.X.f(ex, dictType);
			ex = "0C4BC0DB8EDC474E";
			Map keyjw = this.X.f(ex, dictType);
			ex = "52B669C0526DBADC";
			Map keygj = this.X.f(ex, dictType);
			ex = "CB7C56987F20CA5F";
			Map keybw = this.X.f(ex, dictType);
			ex = "642D3D3B424E2D80";
			Map keysl = this.X.f(ex, dictType);
			Map unBc = this.aT.getUnBcMap();
			Map unDict = this.ba.getUnDictMap();
			HashMap bjColor = new HashMap();
			List sysdictList = this.p.u("YJBJCYS", "0");
			Iterator rownum = sysdictList.iterator();

			while (rownum.hasNext()) {
				SysDict mapList = (SysDict) rownum.next();
				bjColor.put(mapList.getDictCode(), mapList.getDictName());
			}

			ArrayList mapList1 = new ArrayList();
			mapList1.add(keyfd);
			mapList1.add(keykd);
			mapList1.add(keyjw);
			mapList1.add(keygj);
			mapList1.add(keybw);
			mapList1.add(keysl);
			mapList1.add(unBc);
			mapList1.add(unDict);
			short rownum1 = 5001;
			this.bb.f(mapList1);
			this.bb.a(bjColor);
			Date curr = f.d(new Date(), 2);
			String version = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
			int total = this.aP.getBcZyidCount(curr, version);
			sysJudgeLog.setTotalCount(Integer.valueOf(total));
			sysJudgeLog.setSuccessCount(Integer.valueOf(0));
			this.J.update(sysJudgeLog);
			this.a(rownum1, curr, findcs, id, sysJudgeLog);
			sysJudgeLog.setEndTime(new Date());
			if (errnum == 0) {
				sysJudgeLog.setStatus("1");
			}

			this.J.update(sysJudgeLog);
		} catch (Exception arg25) {
			int errnum1 = errnum + 1;
			sysJudgeLog.setFailCount(Integer.valueOf(errnum1));
			sysJudgeLog.setStatus("2");
			this.J.update(sysJudgeLog);
			errList.add(arg25.getMessage());
			c.error("病程分析感染因素异常AnalysisBcServiceImpl.analysisBc", arg25);
			this.aV.a(ah.iG, "病程分析感染因素异常AnalysisBcServiceImpl.analysisBc ", id, arg25.getMessage());
		}

		rs.setErrMsgList(errList);
		return rs;
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(St008Bcjl bcjl, List<Gr018Bcgrys> gr018BcInsert, List<Gr018Ysgrys> gr018YsInsert,
			List<Gr018Ysgrys> gr018YsUpdate, SysJudgeLog sysJudgeLog) {
		try {
			int ex = sysJudgeLog.getSuccessCount().intValue();
			++ex;
			if (ex > sysJudgeLog.getTotalCount().intValue()) {
				ex = sysJudgeLog.getTotalCount().intValue();
			}

			sysJudgeLog.setSuccessCount(Integer.valueOf(ex));
			sysJudgeLog.setEndTime(new Date());
			this.J.update(sysJudgeLog);
			this.aP.updateAnalResultTest(bcjl);
			if (bcjl.getEnterDate().getTime() < bcjl.getOutAt().getTime()) {
				if (gr018YsInsert.size() > 0) {
					this.aR.saveList(gr018YsInsert);
				}

				if (gr018YsUpdate.size() > 0) {
					this.aR.K(gr018YsUpdate);
				}

				if (gr018BcInsert.size() > 0) {
					this.aQ.saveList(gr018BcInsert);
				}
			}
		} catch (Exception arg6) {
			arg6.printStackTrace();
			c.error("AnalysisBcServiceImpl.analysisBc", arg6);
		}

	}

	public void a(int rownum, Date curr, int findcs, String id, SysJudgeLog sysJudgeLog) {
      TaskJob taskJob = this.aU.findByLink("f_task/ysgrys.shtml");
      if(taskJob != null && taskJob.getStatus().intValue() == 50) {
         sysJudgeLog.setStatus("3");
         this.J.update(sysJudgeLog);
      } else {
         String version = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
         List zyids = this.aP.getBcZyidList(curr, rownum, version);
         if(findcs < 1500 && zyids.size() > 0) {
            taskJob = this.aU.findByLink("f_task/ysgrys.shtml");
            if(taskJob != null && taskJob.getStatus().intValue() == 50) {
               return;
            }

            ++findcs;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
            System.out.println("开始第 " + findcs + " 次分析：" + zyids.size() + " " + formatter.format(new Date()));
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
            List result = a(zyids, 10);

            for(int e = 0; e < result.size(); ++e) {
               List zyidList = (List)result.get(e);
               fixedThreadPool.execute(new AnalysisBcServiceImpl$1(this, zyidList, curr, version, sysJudgeLog));
            }

            fixedThreadPool.shutdown();

            while(!fixedThreadPool.isTerminated()) {
               try {
                  Thread.sleep(1000L);
               } catch (InterruptedException arg13) {
                  arg13.printStackTrace();
               }
            }

            this.a(rownum, curr, findcs, id, sysJudgeLog);
         }

      }
   }

	@Transactional(rollbackFor = {Exception.class})
	public void h(String zyid, String bcid) {
		this.aQ.delete(zyid, bcid);
		this.aR.bQ(zyid);
		this.aP.updateAnalFlag(zyid, "0");
		this.aS.updateAnalFlag(zyid, "0");
	}

	public void a(List<String> zyids, Date curr, String version, SysJudgeLog sysJudgeLog) {
		List list = this.aP.getBcListByZyid(zyids, curr, version);
		St008Bcjl bcjl = null;
		Date curDate = curr;
		boolean ysflag = false;

		for (int i = 0; i < list.size(); ++i) {
			bcjl = (St008Bcjl) list.get(i);
			Date enterDate = bcjl.getEnterDate();
			if (enterDate != null && curDate.getTime() >= enterDate.getTime()) {
				String enterDateStr = f.formatDate(enterDate);
				Date enterdt1 = f.l(enterDateStr, "yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(enterdt1);
				calendar.add(13, 1);
				Date enterdt = calendar.getTime();
				String dataDateStr = "";
				String sjid = "";
				String anrs;
				if (bcjl != null && "1".equals(bcjl.getContentType())) {
					anrs = bcjl.getCourseContent();
					if (ab.isNotEmpty(anrs)) {
						try {
							anrs = v.aI(anrs);
						} catch (Exception arg28) {
							c.debug("解析RTF原始病程失败", arg28);
							arg28.printStackTrace();
						}
					}

					bcjl.setCourseContent(anrs);
				}

				com.nis.analysis.model.f arg29 = this.bb.b(bcjl.getBcName(), bcjl.getCourseContent());
				if (arg29.getSuccess()) {
					this.aQ.delete(bcjl.getZyid(), bcjl.getId());
					bcjl.setShowAnalResult(arg29.getAnalysisResultText());
					bcjl.setAnalAt(new Date());
					bcjl.setAnalFlag(Long.valueOf(1L));
					List ysList = this.aR.E(bcjl.getZyid(), enterDateStr);
					ArrayList gr018BcInsert = new ArrayList();
					ArrayList gr018YsInsert = new ArrayList();
					ArrayList gr018YsUpdate = new ArrayList();
					if (arg29.getAnalysisflag() && arg29.getSuccess()) {
						Date num = new Date();
						Iterator arg23 = arg29.getKeyMap().entrySet().iterator();

						while (arg23.hasNext()) {
							Entry k = (Entry) arg23.next();
							ysflag = false;
							Gr018Bcgrys bc = new Gr018Bcgrys();
							bc.setBcId(bcjl.getId());
							bc.setZyid(bcjl.getZyid());
							bc.setDataForm("病程");
							bc.setElementId(((d) k.getValue()).getElementid());
							bc.setElementName(((d) k.getValue()).getElementname());
							bc.setEnterDate(enterDate);
							bc.setMoniDate(num);
							Gr018Ysgrys ys = new Gr018Ysgrys();
							ys.setDataDate(enterdt);
							ys.setDataForm("病程");
							ys.setDataType("文本");
							ys.setElementId(((d) k.getValue()).getElementid());
							ys.setElementName(((d) k.getValue()).getElementname());
							ys.setMonitorAt(num);
							ys.setZyid(bcjl.getZyid());
							ys.setOriginalContent(((d) k.getValue()).getOriginalContent().replaceAll("\\$", ""));
							ys.setState(Integer.valueOf(0));
							ys.setSjId(bcjl.getId());
							Iterator arg27 = ysList.iterator();

							while (arg27.hasNext()) {
								Gr018Ysgrys grys = (Gr018Ysgrys) arg27.next();
								dataDateStr = f.formatDate(grys.getDataDate());
								if (((d) k.getValue()).getElementid().equals(grys.getElementId())
										&& enterDateStr.equals(dataDateStr)
										&& ((d) k.getValue()).getElementid().equals(grys.getElementId())) {
									ys = grys;
									sjid = grys.getSjId();
									if (sjid == null) {
										sjid = "";
									}

									if (sjid.indexOf(bcjl.getId()) == -1) {
										if ("".equals(sjid)) {
											grys.setSjId(bcjl.getId());
										} else {
											grys.setSjId(grys.getSjId() + "," + bcjl.getId());
										}
									}

									grys.setState(Integer.valueOf(0));
									grys.setMonitorAt(curDate);
									ysflag = true;
									break;
								}
							}

							gr018BcInsert.add(bc);
							if (ysflag) {
								gr018YsUpdate.add(ys);
							} else {
								gr018YsInsert.add(ys);
							}
						}
					}

					this.a(bcjl, gr018BcInsert, gr018YsInsert, gr018YsUpdate, sysJudgeLog);
					int arg30 = this.aQ.findGrysCount(bcjl.getZyid(), bcjl.getId());
					if (arg30 > 0) {
						this.h(bcjl.getZyid(), bcjl.getId());
					}

					this.aR.bO(bcjl.getZyid());
				}

				if (arg29 != null) {
					anrs = null;
				}
			}
		}

		list = null;
	}

	public static List<List<String>> a(List<String> targe, int size) {
		ArrayList listArr = new ArrayList();
		int arrSize = targe.size() % size == 0 ? targe.size() / size : targe.size() / size + 1;

		for (int i = 0; i < arrSize; ++i) {
			ArrayList sub = new ArrayList();

			for (int j = i * size; j <= size * (i + 1) - 1; ++j) {
				if (j <= targe.size() - 1) {
					sub.add((String) targe.get(j));
				}
			}

			listArr.add(sub);
		}

		return listArr;
	}
}