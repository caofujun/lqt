package com.nis.analysis.service.impl;

import com.google.gson.Gson;
import com.nis.analysis.entity.Gr018Bcgrys;
import com.nis.analysis.entity.NyUnanalyzeBc;
import com.nis.analysis.entity.Zg007Dict;
import com.nis.analysis.model.a;
import com.nis.analysis.model.d;
import com.nis.analysis.model.kfz.b;
import com.nis.analysis.model.kfz.c;
import com.nis.analysis.service.AnalysisBcKfzService;
import com.nis.analysis.service.Gr018BcgrysService;
import com.nis.analysis.service.NyUnanalyzeBcService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.analysis.service.Zg007DictService;
import com.nis.analysis.service.impl.AnalysisBcKfzServiceImpl.1;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ah;
import com.nis.comm.utils.f;
import com.nis.comm.utils.i;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AnalysisBcKfzServiceImpl implements AnalysisBcKfzService {
	private static final Logger c = Logger.getLogger(AnalysisBcKfzServiceImpl.class);
	private Map<String, NyUnanalyzeBc> aL = new HashMap();
	private Map<String, Zg007Dict> aM = new HashMap();
	private Map<String, String> aN = new HashMap();
	private String aO = "http://demo.kangfuzi.com:9899/";
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
		this.J.get(id);
		a rs = new a();
		ArrayList errList = new ArrayList();
		byte findcs = 0;

		try {
			String ex = "A9B8E397C091559F";
			String dictType = "977C5BB729F88946";
			ex = "52B669C0526DBADC";
			this.aM = this.X.g(ex, dictType);
			this.aL = this.aT.getUnBcMap();
			List sysdictList = this.p.u("YJBJCYS", "0");
			Iterator curr = sysdictList.iterator();

			while (curr.hasNext()) {
				SysDict rownum = (SysDict) curr.next();
				this.aN.put(rownum.getDictCode(), rownum.getDictName());
			}

			short rownum1 = 5001;
			Date curr1 = f.d(new Date(), 2);
			this.a(rownum1, curr1, findcs);
		} catch (Exception arg11) {
			errList.add(arg11.getMessage());
			c.error("病程分析感染因素异常AnalysisBcKfzServiceImpl.analysisBc", arg11);
			this.aV.a(ah.iG, "病程分析感染因素异常AnalysisBcKfzServiceImpl.analysisBc ", id, arg11.getMessage());
		}

		rs.setErrMsgList(errList);
		return rs;
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(St008Bcjl bcjl, List<Gr018Bcgrys> gr018BcInsert, List<Gr018Ysgrys> gr018YsInsert,
			List<Gr018Ysgrys> gr018YsUpdate) {
		try {
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
		} catch (Exception arg5) {
			arg5.printStackTrace();
			c.error("AnalysisBcServiceImpl.analysisBc", arg5);
		}

	}

	public void a(int rownum, Date curr, int findcs) {
      String version = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
      List zyids = this.aP.getBcZyidList(curr, rownum, version);
      if(findcs < 1500 && zyids.size() > 0) {
         if(findcs > 2) {
            return;
         }

         TaskJob taskJob = this.aU.findByLink("f_task/ysgrys.shtml");
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
            fixedThreadPool.execute(new 1(this, zyidList, curr, version));
         }

         fixedThreadPool.shutdown();

         while(!fixedThreadPool.isTerminated()) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException arg11) {
               arg11.printStackTrace();
            }
         }

         this.a(rownum, curr, findcs);
         fixedThreadPool = null;
      }

   }

	@Transactional(rollbackFor = {Exception.class})
	public void h(String zyid, String bcid) {
		this.aQ.delete(zyid, bcid);
		this.aR.bQ(zyid);
		this.aP.updateAnalFlag(zyid, "0");
		this.aS.updateAnalFlag(zyid, "0");
	}

	public void a(List<String> zyids, Date curr, String version) {
		List list = this.aP.getBcListByZyid(zyids, curr, version);
		St008Bcjl bcjl = null;
		Date curDate = new Date();
		boolean ysflag = false;
		DefaultHttpClient httpclient = i.getHttpClient();

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
				com.nis.analysis.model.f anrs = this.a(httpclient, bcjl);
				if (anrs.getSuccess()) {
					this.aQ.delete(bcjl.getZyid(), bcjl.getId());
					bcjl.setShowAnalResult(anrs.getAnalysisResultText());
					bcjl.setAnalAt(new Date());
					bcjl.setAnalFlag(Long.valueOf(1L));
					List ysList = this.aR.E(bcjl.getZyid(), enterDateStr);
					ArrayList gr018BcInsert = new ArrayList();
					ArrayList gr018YsInsert = new ArrayList();
					ArrayList gr018YsUpdate = new ArrayList();
					if (anrs.getAnalysisflag() && anrs.getSuccess()) {
						Date num = new Date();
						Iterator arg23 = anrs.getKeyMap().entrySet().iterator();

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

					this.a(bcjl, gr018BcInsert, gr018YsInsert, gr018YsUpdate);
					int arg28 = this.aQ.findGrysCount(bcjl.getZyid(), bcjl.getId());
					if (arg28 > 0) {
						this.h(bcjl.getZyid(), bcjl.getId());
					}

					this.aR.bO(bcjl.getZyid());
				}

				if (anrs != null) {
					anrs = null;
				}
			}
		}

		httpclient = null;
		list = null;
	}

	public com.nis.analysis.model.f a(DefaultHttpClient httpClient, St008Bcjl bc) {
		boolean analysisflag = false;
		String bcname = "";
		boolean success = false;
		String bcText = null;
		com.nis.analysis.model.f rs = new com.nis.analysis.model.f();
		HashMap keyMap = new HashMap();
		rs.setErrMsg("");
		rs.setSuccess(false);
		rs.setAnalysisResultText("");
		keyMap.clear();
		String resStr = null;
		String reqStr = null;

		try {
			bcname = bc.getBcName();
			analysisflag = true;
			Entry ex;
			Iterator gson;
			if (bcname != null && !"".equals(bcname)) {
				gson = this.aL.entrySet().iterator();

				while (gson.hasNext()) {
					ex = (Entry) gson.next();
					if (bcname.indexOf(((NyUnanalyzeBc) ex.getValue()).getBcName()) > -1) {
						analysisflag = false;
						success = true;
						break;
					}
				}
			}

			bcText = bc.getCourseContent();
			if (bcText == null) {
				bcText = "";
			}

			bcText = bcText.trim();
			if (bcText != null && !"".equals(bcText)) {
				c ex1 = new c();
				ex1.setPatient_Id(bc.getZyid());
				ex1.setDoc_Title(bc.getBcName());
				ex1.setDoc_Content(bc.getCourseContent());
				ex1.setHospital_Id("000000");
				Gson gson1 = new Gson();
				reqStr = gson1.toJson(ex1);
				resStr = i.a(httpClient, reqStr, this.aO, "UTF-8");
				com.nis.analysis.model.kfz.a bct = (com.nis.analysis.model.kfz.a) gson1.fromJson(resStr,
						com.nis.analysis.model.kfz.a.class);
				if (bct != null) {
					Iterator arg14 = bct.getDoc_Tree().iterator();

					label104 : while (true) {
						Map sr;
						do {
							if (!arg14.hasNext()) {
								break label104;
							}

							com.nis.analysis.model.kfz.d s = (com.nis.analysis.model.kfz.d) arg14.next();
							sr = s.getSection_Result();
						} while (sr == null);

						Iterator it = sr.entrySet().iterator();

						label102 : while (true) {
							Entry entry;
							do {
								if (!it.hasNext()) {
									continue label104;
								}

								entry = (Entry) it.next();
							} while (!"<SYMPTOM(+)>".equals(entry.getKey()) && !"<DISEASE(+)>".equals(entry.getKey()));

							List listys = (List) entry.getValue();
							Iterator arg20 = listys.iterator();

							while (true) {
								while (true) {
									if (!arg20.hasNext()) {
										continue label102;
									}

									com.nis.analysis.model.kfz.f ys = (com.nis.analysis.model.kfz.f) arg20.next();
									b ndrs;
									Iterator ndrs1;
									Zg007Dict ndrs2;
									d ndrs4;
									if (this.aM.containsKey(ys.getNAME())) {
										System.out.println("name:" + ys.getNAME() + " zyid:" + bc.getZyid() + " bcid:"
												+ bc.getId() + " context:" + ys.getCONTEXT());
										if (ys.getPROPERTY() != null && ys.getPROPERTY().size() > 0) {
											ndrs1 = ys.getPROPERTY().iterator();

											while (ndrs1.hasNext()) {
												ndrs = (b) ndrs1.next();
												System.out.println("type:" + ndrs.getTYPE() + " name:" + ndrs.getNAME()
														+ " value:" + ndrs.getVALUE() + " unit:" + ndrs.getUNIT());
											}
										}

										ndrs2 = (Zg007Dict) this.aM.get(ys.getNAME());
										ndrs4 = new d();
										ndrs4.setElementid(ndrs2.getElementId());
										ndrs4.setElementname(ys.getNAME());
										ndrs4.setBhElement(ndrs2.getBhElement());
										ndrs4.setOriginalContent(ys.getCONTEXT());
										if (!keyMap.containsKey(ndrs4.getElementid())) {
											keyMap.put(ndrs4.getElementid(), ndrs4);
										}
									} else if (!this.aM.containsKey(ys.getFORMAL())) {
										d ndrs3 = new d();
										ndrs3.setElementid(ys.getNAME());
										ndrs3.setElementname(ys.getNAME());
										ndrs3.setBhElement("0");
										ndrs3.setOriginalContent(ys.getCONTEXT());
										if (!keyMap.containsKey(ndrs3.getElementid())) {
											keyMap.put(ndrs3.getElementid(), ndrs3);
										}
									} else {
										System.out.println("name:" + ys.getNAME() + " zyid:" + bc.getZyid() + " bcid:"
												+ bc.getId() + " context:" + ys.getCONTEXT());
										if (ys.getPROPERTY() != null && ys.getPROPERTY().size() > 0) {
											ndrs1 = ys.getPROPERTY().iterator();

											while (ndrs1.hasNext()) {
												ndrs = (b) ndrs1.next();
												System.out.println("type:" + ndrs.getTYPE() + " name:" + ndrs.getNAME()
														+ " value:" + ndrs.getVALUE() + " unit:" + ndrs.getUNIT());
											}
										}

										ndrs2 = (Zg007Dict) this.aM.get(ys.getFORMAL());
										ndrs4 = new d();
										ndrs4.setElementid(ndrs2.getElementId());
										ndrs4.setElementname(ys.getFORMAL());
										ndrs4.setBhElement(ndrs2.getBhElement());
										ndrs4.setOriginalContent(ys.getCONTEXT());
										if (!keyMap.containsKey(ndrs4.getElementid())) {
											keyMap.put(ndrs4.getElementid(), ndrs4);
										}
									}
								}
							}
						}
					}
				}

				rs.setAnalysisResultText(bct.getDoc_Str());
				ex = null;
				bct = null;
				reqStr = null;
				resStr = null;
				gson = null;
				success = true;
			} else {
				success = true;
				analysisflag = false;
			}
		} catch (Exception arg23) {
			success = false;
			rs.setErrMsg(arg23.getMessage());
			System.out.println("zyid:" + bc.getZyid() + " bcid:" + bc.getId() + " name:" + bc.getBcName());
			System.out.println(resStr);
			c.error("exeAnalysis", arg23);
		}

		rs.setSuccess(success);
		rs.setAnalysisflag(analysisflag);
		rs.setKeyMap(keyMap);
		return rs;
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