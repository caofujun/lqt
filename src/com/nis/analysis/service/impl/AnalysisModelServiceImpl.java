package com.nis.analysis.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.nis.analysis.cache.Zg006ZdmxCache;
import com.nis.analysis.entity.Gr019Ysgrmx;
import com.nis.analysis.entity.Gr019YsgrmxWeight;
import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.entity.ZdmxWeight;
import com.nis.analysis.entity.Zg006Zdmx;
import com.nis.analysis.model.a;
import com.nis.analysis.model.c;
import com.nis.analysis.service.AnalysisModelService;
import com.nis.analysis.service.Gr019YsgrmxService;
import com.nis.analysis.service.Gr019YsgrmxWeightService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.analysis.service.Zg006ZdmxService;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ah;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.i;
import com.nis.comm.utils.l;
import com.nis.dict.service.Zg005YygrzdService;
import com.nis.log.service.SysLogService;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Gr002YsgrMx;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.monitor.service.Bk001SbkService;
import com.nis.monitor.service.Gr002YsgrMxService;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St005Ssxxb;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import com.nis.zg.entity.Zg006ZdmxFa;
import com.nis.zg.service.Zg006ZdmxFaService;
import com.nis.zg.service.Zg007GrysService;



class AnalysisModelServiceImpl$1
  implements Comparator<Gr018Ysgrys>
{
  AnalysisModelServiceImpl$1(AnalysisModelServiceImpl paramAnalysisModelServiceImpl) {}
  
  public int compare(Gr018Ysgrys d1, Gr018Ysgrys d2)
  {
    return d1.getDataDate().compareTo(d2.getDataDate());
  }
  
}


class AnalysisModelServiceImpl$2
implements Comparator<Zg006Zdmx>
{
AnalysisModelServiceImpl$2(AnalysisModelServiceImpl paramAnalysisModelServiceImpl) {}

public int compare(Zg006Zdmx d1, Zg006Zdmx d2)
{
  return d1.getInfectCode().compareTo(d2.getInfectCode());
}
}


class AnalysisModelServiceImpl$3
implements Comparator<Zg006Zdmx>
{
AnalysisModelServiceImpl$3(AnalysisModelServiceImpl paramAnalysisModelServiceImpl) {}

public int compare(Zg006Zdmx d1, Zg006Zdmx d2)
{
  return d1.getNodeLevel().compareTo(d2.getNodeLevel());
}
}


class AnalysisModelServiceImpl$4
implements Comparator<Zg006Zdmx>
{
AnalysisModelServiceImpl$4(AnalysisModelServiceImpl paramAnalysisModelServiceImpl) {}

public int compare(Zg006Zdmx d1, Zg006Zdmx d2)
{
  return d1.getDataDate().compareTo(d2.getDataDate());
}
}




class AnalysisModelServiceImpl$5
  implements Comparator<Zg006Zdmx>
{
  AnalysisModelServiceImpl$5(AnalysisModelServiceImpl paramAnalysisModelServiceImpl) {}
  
  public int compare(Zg006Zdmx d1, Zg006Zdmx d2)
  {
    return d1.getDataDate().compareTo(d2.getDataDate());
  }
}


class AnalysisModelServiceImpl$6
implements Runnable
{
	AnalysisModelServiceImpl cc;
	SysJudgeLog bd;
	String cd;
	AnalysisModelServiceImpl$6(AnalysisModelServiceImpl paramAnalysisModelServiceImpl, String paramString, SysJudgeLog paramSysJudgeLog) {
		this.cc = paramAnalysisModelServiceImpl;
		this.bd = paramSysJudgeLog;
		this.cd = paramString;
		
		
	}
	
	public void run()
	{
		//TODO:
//	  List<Gr018Ysgrys> gr018List = this.cc.getbO().findListByZyid(this.cd);
//	  this.cc.a(gr018List, this.bd);
	}
}




@Component
public class AnalysisModelServiceImpl implements AnalysisModelService {
	private static final Logger c = Logger.getLogger(AnalysisModelServiceImpl.class);
	private int bQ = 48;
	private int bR = 30;
	private Map<String, List<Zg006Zdmx>> bS = new HashMap();
	private Map<String, String> bT = new HashMap();
	@Autowired
	private Zg005YygrzdService E;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private Zg006ZdmxCache bU;
	@Autowired
	private Zg007GrysService F;
	@Autowired
	private SysParamService j;
	@Autowired
	private Gr018YsgrysService aR;
	@Autowired
	private Gr019YsgrmxService bV;
	@Autowired
	private Bk001SbkService bW;
	@Autowired
	private Gr002YsgrMxService bX;
	@Autowired
	private JudgeCode bY;
	@Autowired
	private Zg006ZdmxService bZ;
	@Autowired
	private SysLogService aV;
	@Autowired
	private Gr019YsgrmxWeightService ca;
	@Autowired
	private Zg006ZdmxFaService cb;
	@Autowired
	private TaskJobService aU;
	@Autowired
	private SysJudgeLogService J;
	
	

	public St005SsxxbService getbO() {
		return bO;
	}

	public a a(List<Gr018Ysgrys> gr018List, SysJudgeLog sysJudgeLog) {
		int sucnum = sysJudgeLog.getSuccessCount().intValue();
		++sucnum;
		if (sucnum > sysJudgeLog.getTotalCount().intValue()) {
			sucnum = sysJudgeLog.getTotalCount().intValue();
		}

		sysJudgeLog.setSuccessCount(Integer.valueOf(sucnum));
		sysJudgeLog.setEndTime(new Date());
		this.J.update(sysJudgeLog);
		a reuslt = new a();
		new St003Cryxxb();
		reuslt.setTotals(gr018List.size());
		if (gr018List != null && gr018List.size() > 0) {
			St003Cryxxb cryxxb = this.bg.get(((Gr018Ysgrys) gr018List.get(0)).getZyid());
			if (cryxxb == null) {
				reuslt.setSuccess(false);
				if (gr018List != null && gr018List.size() > 0) {
					this.aR.b(((Gr018Ysgrys) gr018List.get(0)).getZyid(), Integer.valueOf(1));
				}

				return reuslt;
			}

			try {
				c e = this.a(gr018List, cryxxb);
				if (e.b()) {
					List infectCodeWeightList = e.getZdList();
					this.a(e.getGr019List(), cryxxb, gr018List, infectCodeWeightList);
					reuslt.setSuccess(true);
					reuslt.setSucTotals(gr018List.size());
				}
			} catch (Exception arg7) {
				c.error("分析诊断模型异常", arg7);
				arg7.printStackTrace();
				this.aV.a(ah.iG, "分析诊断模型异常 ，zyid=" + ((Gr018Ysgrys) gr018List.get(0)).getZyid(), sysJudgeLog.getId(),
						arg7.getMessage());
				this.aR.b(((Gr018Ysgrys) gr018List.get(0)).getZyid(), Integer.valueOf(9));
			}
		}

		return reuslt;
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(List<Gr019Ysgrmx> gr019List, St003Cryxxb cryxxb, List<Gr018Ysgrys> gr018List,
			List<Map<String, ZdmxWeight>> infectCodeWeightList) {
		Date createDate = new Date();
		if (gr019List.size() > 0) {
			this.bV.delete(cryxxb.getZyid());
			this.bV.saveList(gr019List);
		}

		this.bX.deleteByZyidState(((Gr018Ysgrys) gr018List.get(0)).getZyid());

		label146 : for (int i = 0; i < infectCodeWeightList.size(); ++i) {
			Map map = (Map) infectCodeWeightList.get(i);
			Iterator arg8 = map.entrySet().iterator();

			label143 : while (true) {
				ZdmxWeight zdmxWeight;
				Gr002YsgrMx arg21;
				do {
					do {
						while (true) {
							Entry entry;
							List zg006List;
							do {
								if (!arg8.hasNext()) {
									continue label146;
								}

								entry = (Entry) arg8.next();
								zdmxWeight = (ZdmxWeight) entry.getValue();
								zg006List = zdmxWeight.getZdmxList();
							} while (zdmxWeight.getWeight().intValue() <= this.bR);

							this.ca.a(zdmxWeight.getYsgrmxWeight());
							Gr002YsgrMx gr002 = new Gr002YsgrMx();
							gr002.setZyid(cryxxb.getZyid());
							gr002.setInfectCode((String) entry.getKey());
							gr002.setStartAt(zdmxWeight.getGrDate());
							gr002.setMoniAt(createDate);
							gr002.setState(Integer.valueOf(1));
							if (zdmxWeight.getWeight().intValue() < 100) {
								gr002.setSuspectedDegree(Double.valueOf(zdmxWeight.getWeight().doubleValue()));
							} else {
								gr002.setSuspectedDegree(Double.valueOf(100.0D));
							}

							Integer InfectTypeId = this.a(cryxxb, zdmxWeight.getGrDate(), zdmxWeight.getYsgrmxWeight());
							List bk001List;
							if (InfectTypeId.intValue() == 2) {
								boolean grmxList2 = false;
								bk001List = zdmxWeight.getZdmxList();
								Iterator bk001Sbk = bk001List.iterator();

								label101 : while (true) {
									Zg006Zdmx grmxList;
									do {
										if (!bk001Sbk.hasNext()) {
											if (grmxList2) {
												InfectTypeId = Integer.valueOf(1);
											}
											break label101;
										}

										grmxList = (Zg006Zdmx) bk001Sbk.next();
									} while (!"JY".equals(grmxList.getNodeType2())
											&& !"KJ".equals(grmxList.getNodeType2()));

									if (f.d(grmxList.getDataDate(), -72).getTime() > cryxxb.getInHospAt().getTime()) {
										gr002.setStartAt(grmxList.getDataDate());
										grmxList2 = true;
									}
								}
							}

							gr002.setInfectTypeId(InfectTypeId);
							gr002.setYjInfectCode((String) entry.getKey());
							if (ab.isNotEmpty(cryxxb.getDeptCode())) {
								gr002.setDeptId(cryxxb.getDeptCode());
							}

							gr002.setOperator("NIS7");
							if (zg006List != null && zg006List.size() > 0) {
								gr002.setStandardNo(
										Long.valueOf(Long.parseLong(((Zg006Zdmx) zg006List.get(0)).getStandardNo())));
							}

							List arg20 = this.bX.p(cryxxb.getZyid(), (String) entry.getKey(), (String) null);
							if (arg20 != null && arg20.size() > 0) {
								arg21 = (Gr002YsgrMx) arg20.get(0);
								break;
							}

							bk001List = this.bW.findByZyidAndCode(cryxxb.getZyid(), (String) entry.getKey());
							List arg22 = this.bX.p(cryxxb.getZyid(), (String) entry.getKey(), "2");
							if (arg22 != null && arg22.size() > 0) {
								Gr002YsgrMx arg25 = (Gr002YsgrMx) arg22.get(0);
								if (arg25.getState() != null && arg25.getState().intValue() == 2) {
									gr002.setState(Integer.valueOf(2));
									gr002.setStartAt(arg25.getStartAt());
									gr002.setInfectTypeId(arg25.getInfectTypeId());
								}

								this.bX.save(gr002);
							} else if (bk001List != null && bk001List.size() > 0) {
								Bk001Sbk arg24 = (Bk001Sbk) bk001List.get(0);
								gr002.setState(Integer.valueOf(2));
								gr002.setStartAt(arg24.getInfectDate());
								gr002.setInfectTypeId(arg24.getInfectTypeId());
								this.bX.save(gr002);
							} else {
								this.bX.save(gr002);
							}
						}
					} while (arg21.getState() == null);
				} while (arg21.getState().intValue() != 1 && arg21.getState().intValue() != 2);

				boolean arg23 = false;
				Date arg26 = arg21.getLastoperDate();
				List zdmxList = zdmxWeight.getZdmxList();
				Iterator arg19 = zdmxList.iterator();

				while (true) {
					Zg006Zdmx zdmx;
					do {
						if (!arg19.hasNext()) {
							if (arg23) {
								arg21.setInfectTypeId(Integer.valueOf(1));
								arg21.setState(Integer.valueOf(1));
								this.bX.update(arg21);
							}
							continue label143;
						}

						zdmx = (Zg006Zdmx) arg19.next();
					} while (!"JY".equals(zdmx.getNodeType2()) && !"KJ".equals(zdmx.getNodeType2()));

					if (zdmx.getDataDate() != null && cryxxb.getInHospAt() != null
							&& f.d(zdmx.getDataDate(), -48).getTime() > cryxxb.getInHospAt().getTime()) {
						if (arg26 != null) {
							if (zdmx.getDataDate().getTime() > arg26.getTime()) {
								arg23 = true;
								arg21.setInfectendDt(zdmx.getDataDate());
							}
						} else {
							arg23 = true;
							arg21.setInfectendDt(zdmx.getDataDate());
						}
					}
				}
			}
		}

		if (gr018List != null && gr018List.size() > 0) {
			this.aR.b(((Gr018Ysgrys) gr018List.get(0)).getZyid(), Integer.valueOf(1));
		}

	}

	public List<String> i(List<Zg006Zdmx> zg006ZdmxList) {
		ArrayList infectCodeList = new ArrayList();
		Iterator arg3 = zg006ZdmxList.iterator();

		while (arg3.hasNext()) {
			Zg006Zdmx zdmx = (Zg006Zdmx) arg3.next();
			boolean i = true;
			Iterator arg6 = infectCodeList.iterator();

			while (arg6.hasNext()) {
				String infectCode = (String) arg6.next();
				if (infectCode.equals(zdmx.getInfectCode())) {
					i = false;
				}
			}

			if (i) {
				infectCodeList.add(zdmx.getInfectCode());
			}
		}

		return infectCodeList;
	}

	public List<Map<String, ZdmxWeight>> a(List<Zg006Zdmx> zg006ZdmxList, List<String> infectCodeList,
			St003Cryxxb cryxxb, List<St005Ssxxb> st005List) {
		ArrayList infectCodeWeightList = new ArrayList();
		Iterator arg6 = infectCodeList.iterator();

		label90 : while (arg6.hasNext()) {
			String infectCode = (String) arg6.next();
			HashMap map = new HashMap();
			ArrayList zdmxList = new ArrayList();
			ArrayList zdmxJyList = new ArrayList();
			ArrayList zdmxKjywList = new ArrayList();
			ArrayList zdmxXjList = new ArrayList();
			ArrayList zdmxCgList = new ArrayList();
			ArrayList zdmxBcList = new ArrayList();
			ArrayList zg006List = new ArrayList();
			Iterator isOperate = zg006ZdmxList.iterator();

			while (true) {
				Zg006Zdmx grDate;
				do {
					if (!isOperate.hasNext()) {
						Date grDate1 = this.l(zg006List);
						boolean isOperate1 = this.bY.a(st005List, grDate1);
						this.bY.o(zdmxList);
						ArrayList weightList1 = new ArrayList();
						ArrayList jyWeightList = new ArrayList();
						Gr019YsgrmxWeight ysgrmxWeight = new Gr019YsgrmxWeight();
						ysgrmxWeight.setJyjcWeight(Integer.valueOf(0));
						ysgrmxWeight.setXjpyWeight(Integer.valueOf(0));
						String weight;
						List jyWeight;
						if (zdmxList.size() > 0) {
							this.k(zdmxList);
							weight = ((Zg006Zdmx) zdmxList.get(zdmxList.size() - 1)).getNodeLevel();
							jyWeight = this.b(zdmxList, (String) weight);
							this.a(zdmxList, jyWeight, weightList1, Integer.valueOf(Integer.parseInt(weight) - 1),
									ysgrmxWeight);
						}

						this.bY.a(zdmxJyList, isOperate1);
						this.bY.a(zg006List, zdmxJyList, isOperate1);
						this.bY.g(weightList1, zdmxJyList);
						this.bY.h(weightList1, zdmxJyList);
						this.bY.b(zg006List, zdmxJyList, isOperate1);
						if (zdmxJyList.size() > 0) {
							this.k(zdmxJyList);
							weight = ((Zg006Zdmx) zdmxJyList.get(zdmxJyList.size() - 1)).getNodeLevel();
							jyWeight = this.b(zdmxJyList, (String) weight);
							this.a(zdmxJyList, jyWeight, jyWeightList, Integer.valueOf(Integer.parseInt(weight) - 1),
									ysgrmxWeight);
						}

						int weight1 = 0;
						boolean jyWeight1 = false;
						int tyxWeight = 0;
						int kjWeight = 0;
						Iterator bcyxDay = weightList1.iterator();

						Zg006Zdmx zdmxWeight;
						while (bcyxDay.hasNext()) {
							zdmxWeight = (Zg006Zdmx) bcyxDay.next();
							if ("1".equals(zdmxWeight.getNodeId())) {
								weight1 = Integer.parseInt(zdmxWeight.getWeightValue());
							} else if ("99".equals(zdmxWeight.getNodeId())) {
								tyxWeight = Integer.parseInt(zdmxWeight.getWeightValue());
							} else if ("19".equals(zdmxWeight.getNodeId())) {
								kjWeight = Integer.parseInt(zdmxWeight.getWeightValue());
							}
						}

						bcyxDay = jyWeightList.iterator();

						while (bcyxDay.hasNext()) {
							zdmxWeight = (Zg006Zdmx) bcyxDay.next();
							if ("10".equals(zdmxWeight.getNodeId())) {
								int jyWeight2 = Integer.parseInt(zdmxWeight.getWeightValue());
							}
						}

						ZdmxWeight zdmxWeight1 = new ZdmxWeight();
						Date bcyxDay1 = this.c(zdmxBcList, (String) "min");
						Date kjywDay = this.c(zdmxKjywList, (String) "max");
						Date jyjcDay = this.c(zdmxCgList, (String) "max");
						Date xjpyDay = this.c(zdmxXjList, (String) "max");
						ysgrmxWeight.setZyid(cryxxb.getZyid());
						ysgrmxWeight.setInfectCode(infectCode);
						ysgrmxWeight.setBcyxWeight(Integer.valueOf(weight1));
						ysgrmxWeight.setBcyxDay(bcyxDay1);
						ysgrmxWeight.setJyjcDay(jyjcDay);
						ysgrmxWeight.setTyxWeight(Integer.valueOf(tyxWeight));
						ysgrmxWeight.setTyxDay(bcyxDay1);
						ysgrmxWeight.setXjpyDay(xjpyDay);
						ysgrmxWeight.setKjywWeight(Integer.valueOf(kjWeight));
						ysgrmxWeight.setKjywDay(kjywDay);
						ysgrmxWeight.setGrDate(grDate1);
						ysgrmxWeight.setCreateTime(new Date());
						ysgrmxWeight.setMonitorAt(new Date());
						zdmxWeight1.setYsgrmxWeight(ysgrmxWeight);
						zdmxWeight1.setWeight(this.a(ysgrmxWeight, cryxxb.getInHospAt()));
						zdmxWeight1.setGrDate(grDate1);
						zdmxWeight1.setZdmxList(zg006List);
						map.put(infectCode, zdmxWeight1);
						infectCodeWeightList.add(map);
						continue label90;
					}

					grDate = (Zg006Zdmx) isOperate.next();
				} while (!infectCode.equals(grDate.getInfectCode()));

				if ("KJ".equals(grDate.getNodeType2()) && ab.isNotEmpty(grDate.getElementId())) {
					zdmxKjywList.add(grDate);
				}

				if (("JB".equals(grDate.getNodeType2()) || "ALL".equals(grDate.getNodeType2())
						|| "TYX".equals(grDate.getNodeType2())) && ab.isNotEmpty(grDate.getElementId())) {
					zdmxBcList.add(grDate);
				}

				if ("JY".equals(grDate.getNodeType2())) {
					zdmxJyList.add(grDate);
					if (ab.isNotEmpty(grDate.getElementId())) {
						String weightList = grDate.getNodeId().substring(0, 4);
						if (weightList.equals("10.1")) {
							zdmxXjList.add(grDate);
						} else if (weightList.equals("10.2")) {
							zdmxCgList.add(grDate);
						}
					}
				} else {
					zdmxList.add(grDate);
				}

				zg006List.add(grDate);
			}
		}

		return infectCodeWeightList;
	}

	public c a(List<Gr018Ysgrys> gr018List, St003Cryxxb cryxxb) {
      c modelResult = new c();
      ArrayList zg006ZdmxList = new ArrayList();
      List gr019List = this.bV.getbyZyid(cryxxb.getZyid());
      List bk001SbkList = this.bW.findByZyidState(cryxxb.getZyid());
      List gr002List = this.bX.findByZyid(cryxxb.getZyid());
      List st005List = this.bO.findListByZyid(cryxxb.getZyid());
      List gr019ZdmxList = this.m(gr019List);
      zg006ZdmxList.addAll(gr019ZdmxList);
      Iterator infectCodeList = gr018List.iterator();

      List infectCodeWeightList;
      while(infectCodeList.hasNext()) {
         Gr018Ysgrys insertGr019List = (Gr018Ysgrys)infectCodeList.next();
         infectCodeWeightList = this.bU.k(insertGr019List.getElementId());
         Iterator arg13 = infectCodeWeightList.iterator();

         while(arg13.hasNext()) {
            Zg006Zdmx ssgr = (Zg006Zdmx)arg13.next();
            Zg006Zdmx zg006 = (Zg006Zdmx)ssgr.clone();
            List ysgrysList = this.d(gr018List, zg006.getElementId());
            if(ysgrysList.size() > 0) {
               Collections.sort(ysgrysList, new AnalysisModelServiceImpl$1(this));
               zg006.setDataDate(((Gr018Ysgrys)ysgrysList.get(0)).getDataDate());
               zg006.setLastDate(((Gr018Ysgrys)ysgrysList.get(ysgrysList.size() - 1)).getDataDate());
            }

            if(EncryptUtils.ad(insertGr019List.getElementId()).equals(zg006.getElementId())) {
               zg006.setElementName(insertGr019List.getElementName());
            }

            ArrayList zg006List = new ArrayList();
            zg006List.add(zg006);
            this.a((List)zg006List, (Zg006Zdmx)zg006);
            this.b(zg006ZdmxList, (List)zg006List);
         }
      }

      List zg006ZdmxList1 = this.j(zg006ZdmxList);
      List insertGr019List1 = this.a(zg006ZdmxList1, cryxxb, gr018List);
      modelResult.setGr019List(insertGr019List1);
      List infectCodeList1 = this.i(zg006ZdmxList1);
      this.bY.d(gr018List, zg006ZdmxList1);
      this.bY.c(zg006ZdmxList1, cryxxb);
      this.bY.d(zg006ZdmxList1, cryxxb);
      this.bY.c(zg006ZdmxList1, infectCodeList1);
      this.bY.p(zg006ZdmxList1);
      infectCodeWeightList = this.a(zg006ZdmxList1, infectCodeList1, cryxxb, st005List);
      this.bY.r(infectCodeWeightList);
      this.bY.s(infectCodeWeightList);
      this.bY.q(infectCodeWeightList);
      this.bY.a(infectCodeWeightList, Integer.valueOf(this.bR));
      this.bY.e(infectCodeWeightList, gr002List);
      this.bY.f(st005List, infectCodeWeightList);
      String ssgr1 = this.j.findByParamCode(Param.NIS_YJ_SSGR);
      if("0".equals(ssgr1)) {
         this.bY.i(infectCodeWeightList, st005List);
      }

      this.bY.t(infectCodeWeightList);
      this.bY.j(infectCodeWeightList, bk001SbkList);
      this.bY.e(infectCodeWeightList, cryxxb);
      this.bY.b(infectCodeWeightList, Integer.valueOf(this.bR));
      modelResult.setSuccess(true);
      modelResult.setZdList(infectCodeWeightList);
      return modelResult;
   }

	public ZdmxWeight b(List<Zg006Zdmx> zg006ZdmxList, St003Cryxxb cryxxb) {
		ArrayList zdmxList = new ArrayList();
		ArrayList zdmxJyList = new ArrayList();
		ArrayList zdmxKjywList = new ArrayList();
		ArrayList zdmxXjList = new ArrayList();
		ArrayList zdmxCgList = new ArrayList();
		ArrayList zdmxBcList = new ArrayList();
		ArrayList zg006List = new ArrayList();

		Zg006Zdmx grDate;
		for (Iterator weightList = zg006ZdmxList.iterator(); weightList.hasNext(); zg006List.add(grDate)) {
			grDate = (Zg006Zdmx) weightList.next();
			if ("KJ".equals(grDate.getNodeType2()) && ab.isNotEmpty(grDate.getElementId())) {
				zdmxKjywList.add(grDate);
			}

			if (("JB".equals(grDate.getNodeType2()) || "ALL".equals(grDate.getNodeType2())
					|| "TYX".equals(grDate.getNodeType2())) && ab.isNotEmpty(grDate.getElementId())) {
				zdmxBcList.add(grDate);
			}

			if ("JY".equals(grDate.getNodeType2())) {
				zdmxJyList.add(grDate);
				if (ab.isNotEmpty(grDate.getElementId())) {
					String jyWeightList = grDate.getNodeId().substring(0, 4);
					if (jyWeightList.equals("10.1")) {
						zdmxXjList.add(grDate);
					} else if (jyWeightList.equals("10.2")) {
						zdmxCgList.add(grDate);
					}
				}
			} else {
				zdmxList.add(grDate);
			}
		}

		Date grDate1 = this.l(zg006List);
		ArrayList weightList1 = new ArrayList();
		ArrayList jyWeightList1 = new ArrayList();
		Gr019YsgrmxWeight ysgrmxWeight = new Gr019YsgrmxWeight();
		ysgrmxWeight.setJyjcWeight(Integer.valueOf(0));
		ysgrmxWeight.setXjpyWeight(Integer.valueOf(0));
		String weight;
		List jyWeight;
		if (zdmxList.size() > 0) {
			this.k(zdmxList);
			weight = ((Zg006Zdmx) zdmxList.get(zdmxList.size() - 1)).getNodeLevel();
			jyWeight = this.b(zdmxList, (String) weight);
			this.a(zdmxList, jyWeight, weightList1, Integer.valueOf(Integer.parseInt(weight) - 1), ysgrmxWeight);
		}

		if (zdmxJyList.size() > 0) {
			this.k(zdmxJyList);
			weight = ((Zg006Zdmx) zdmxJyList.get(zdmxJyList.size() - 1)).getNodeLevel();
			jyWeight = this.b(zdmxJyList, (String) weight);
			this.a(zdmxJyList, jyWeight, jyWeightList1, Integer.valueOf(Integer.parseInt(weight) - 1), ysgrmxWeight);
		}

		int weight1 = 0;
		boolean jyWeight1 = false;
		int tyxWeight = 0;
		int kjWeight = 0;
		Iterator bcyxDay = weightList1.iterator();

		Zg006Zdmx zdmxWeight;
		while (bcyxDay.hasNext()) {
			zdmxWeight = (Zg006Zdmx) bcyxDay.next();
			if ("1".equals(zdmxWeight.getNodeId())) {
				weight1 = Integer.parseInt(zdmxWeight.getWeightValue());
			} else if ("99".equals(zdmxWeight.getNodeId())) {
				tyxWeight = Integer.parseInt(zdmxWeight.getWeightValue());
			} else if ("19".equals(zdmxWeight.getNodeId())) {
				kjWeight = Integer.parseInt(zdmxWeight.getWeightValue());
			}
		}

		bcyxDay = jyWeightList1.iterator();

		while (bcyxDay.hasNext()) {
			zdmxWeight = (Zg006Zdmx) bcyxDay.next();
			if ("10".equals(zdmxWeight.getNodeId())) {
				int jyWeight2 = Integer.parseInt(zdmxWeight.getWeightValue());
			}
		}

		ZdmxWeight zdmxWeight1 = new ZdmxWeight();
		if (zg006List.size() > 0) {
			Date bcyxDay1 = this.c(zdmxBcList, (String) "min");
			Date kjywDay = this.c(zdmxKjywList, (String) "max");
			Date jyjcDay = this.c(zdmxCgList, (String) "max");
			Date xjpyDay = this.c(zdmxXjList, (String) "max");
			ysgrmxWeight.setBcyxDay(bcyxDay1);
			ysgrmxWeight.setJyjcDay(jyjcDay);
			ysgrmxWeight.setTyxDay(bcyxDay1);
			ysgrmxWeight.setXjpyDay(xjpyDay);
			ysgrmxWeight.setKjywDay(kjywDay);
			ysgrmxWeight.setZyid(cryxxb.getZyid());
			ysgrmxWeight.setInfectCode(((Zg006Zdmx) zg006List.get(0)).getInfectCode());
			ysgrmxWeight.setBcyxWeight(Integer.valueOf(weight1));
			ysgrmxWeight.setTyxWeight(Integer.valueOf(tyxWeight));
			ysgrmxWeight.setKjywWeight(Integer.valueOf(kjWeight));
			ysgrmxWeight.setGrDate(grDate1);
			ysgrmxWeight.setCreateTime(new Date());
			ysgrmxWeight.setMonitorAt(new Date());
			zdmxWeight1.setYsgrmxWeight(ysgrmxWeight);
			zdmxWeight1.setWeight(this.a(ysgrmxWeight, cryxxb.getInHospAt()));
			zdmxWeight1.setGrDate(grDate1);
			zdmxWeight1.setZdmxList(zg006List);
		}

		return zdmxWeight1;
	}

	public List<Zg006Zdmx> b(List<Zg006Zdmx> zdmxList, String nodeLevel) {
		ArrayList leafList = new ArrayList();

		for (int i = 0; i < zdmxList.size(); ++i) {
			Zg006Zdmx zdmx = (Zg006Zdmx) zdmxList.get(i);
			if (nodeLevel.equals(zdmx.getNodeLevel())) {
				leafList.add(zdmx);
				zdmxList.remove(zdmx);
				--i;
			}
		}

		return leafList;
	}

	public Integer a(Gr019YsgrmxWeight weight, Date inDate) {
		Zg006ZdmxFa zg006Fa = this.cb.getState();
		int[] array = new int[]{
				(int) ((double) weight.getBcyxWeight().intValue() * zg006Fa.getBcyxWeight().doubleValue() / 100.0D),
				(int) ((double) weight.getJyjcWeight().intValue() * zg006Fa.getJyxxWeight().doubleValue() / 100.0D),
				(int) ((double) weight.getXjpyWeight().intValue() * zg006Fa.getXjppWeight().doubleValue() / 100.0D),
				(int) ((double) weight.getKjywWeight().intValue() * zg006Fa.getKjyyWeight().doubleValue() / 100.0D),
				(int) ((double) weight.getTyxWeight().intValue() * zg006Fa.getTyxzbWeight().doubleValue() / 100.0D)};
		sort(array);
		return Integer.valueOf(array[0]);
	}

	public static void sort(int[] array) {
		if (array != null && array.length != 0) {
			boolean temp = false;

			for (int i = 0; i < array.length; ++i) {
				for (int j = i; j < array.length; ++j) {
					if (array[i] < array[j]) {
						int arg3 = array[i];
						array[i] = array[j];
						array[j] = arg3;
					}
				}
			}

		}
	}

	public void a(List<Zg006Zdmx> zdmxList, List<Zg006Zdmx> leafList, List<Zg006Zdmx> weightList, Integer nodeLevel,
			Gr019YsgrmxWeight ysgrmxWeight) {
		if (leafList != null && leafList.size() > 0) {
			Map map = this.a(leafList, zdmxList, nodeLevel);
			ArrayList childrenList = new ArrayList();
			Iterator arg8 = map.entrySet().iterator();

			while (arg8.hasNext()) {
				Entry zg006 = (Entry) arg8.next();

				for (int i = 0; i < zdmxList.size(); ++i) {
					Zg006Zdmx zdmx = (Zg006Zdmx) zdmxList.get(i);
					boolean isMust = this.b(zdmxList, zdmx);
					boolean isNeedCount = this.c(zdmxList, zdmx);
					if (isMust || isNeedCount) {
						zdmx.setWeightValue("0");
					}

					if (((String) zg006.getKey()).equals(zdmx.getNodeId())) {
						ZdmxWeight zdmxWeight = (ZdmxWeight) zg006.getValue();
						if (Integer.parseInt(zdmx.getWeightValue()) > zdmxWeight.getWeight().intValue()) {
							zdmx.setWeightValue(zdmxWeight.getWeight().toString());
						}

						if (ab.isNotEmpty(zdmx.getPNodeId())) {
							childrenList.add(zdmx);
						} else {
							weightList.add(zdmx);
						}

						zdmxList.remove(zdmx);
						--i;
					}
				}
			}

			if (nodeLevel.intValue() - 2 == 0) {
				arg8 = childrenList.iterator();

				while (arg8.hasNext()) {
					Zg006Zdmx arg14 = (Zg006Zdmx) arg8.next();
					if ("10.2".equals(arg14.getNodeId())) {
						ysgrmxWeight.setJyjcWeight(Integer.valueOf(Integer.parseInt(arg14.getWeightValue())));
					} else if ("10.1".equals(arg14.getNodeId())) {
						ysgrmxWeight.setXjpyWeight(Integer.valueOf(Integer.parseInt(arg14.getWeightValue())));
					}
				}
			}

			if (zdmxList.size() > 0) {
				nodeLevel = Integer.valueOf(nodeLevel.intValue() - 1);
				this.a(zdmxList, childrenList, weightList, nodeLevel, ysgrmxWeight);
			}
		}

	}

	public Map<String, ZdmxWeight> a(List<Zg006Zdmx> leafList, List<Zg006Zdmx> zdmxList, Integer nodeLevel) {
		ArrayList pNodeIdList = new ArrayList();
		HashMap map = new HashMap();
		Iterator arg6 = zdmxList.iterator();

		while (arg6.hasNext()) {
			Zg006Zdmx pNodeId = (Zg006Zdmx) arg6.next();
			boolean mapList = true;
			Iterator isleaf = pNodeIdList.iterator();

			while (isleaf.hasNext()) {
				String leafWeight = (String) isleaf.next();
				if (leafWeight.equals(pNodeId.getNodeId())) {
					mapList = false;
				}
			}

			if (mapList && nodeLevel.toString().equals(pNodeId.getNodeLevel())) {
				pNodeIdList.add(pNodeId.getNodeId());
			}
		}

		arg6 = pNodeIdList.iterator();

		while (arg6.hasNext()) {
			String pNodeId1 = (String) arg6.next();
			ArrayList mapList1 = new ArrayList();
			Integer leafWeight1 = Integer.valueOf(0);
			boolean isleaf1 = false;
			Iterator arg11 = leafList.iterator();

			Zg006Zdmx zdmxWeight;
			while (arg11.hasNext()) {
				zdmxWeight = (Zg006Zdmx) arg11.next();
				if (zdmxWeight.getPNodeId().equals(pNodeId1)) {
					mapList1.add(zdmxWeight);
					leafWeight1 = Integer
							.valueOf(leafWeight1.intValue() + Integer.parseInt(zdmxWeight.getWeightValue()));
					isleaf1 = true;
				}
			}

			arg11 = zdmxList.iterator();

			while (arg11.hasNext()) {
				zdmxWeight = (Zg006Zdmx) arg11.next();
				if (zdmxWeight.getNodeId().equals(pNodeId1)) {
					mapList1.add(zdmxWeight);
					if (isleaf1) {
						if (leafWeight1.intValue() > Integer.parseInt(zdmxWeight.getWeightValue())) {
							leafWeight1 = Integer.valueOf(Integer.parseInt(zdmxWeight.getWeightValue()));
						}
					} else {
						leafWeight1 = Integer.valueOf(Integer.parseInt(zdmxWeight.getWeightValue()));
					}
				}
			}

			ZdmxWeight zdmxWeight1 = new ZdmxWeight();
			zdmxWeight1.setWeight(leafWeight1);
			zdmxWeight1.setZdmxList(mapList1);
			map.put(pNodeId1, zdmxWeight1);
		}

		return map;
	}

	public void b(List<Zg006Zdmx> zg006ZdmxList, List<Zg006Zdmx> zg006List) {
		Iterator arg3 = zg006List.iterator();

		while (true) {
			Zg006Zdmx zg006;
			boolean i;
			do {
				if (!arg3.hasNext()) {
					return;
				}

				zg006 = (Zg006Zdmx) arg3.next();
				i = true;
			} while (zg006 == null);

			Iterator arg6 = zg006ZdmxList.iterator();

			while (arg6.hasNext()) {
				Zg006Zdmx zdmx = (Zg006Zdmx) arg6.next();
				if (zdmx.getInfectCode().equals(zg006.getInfectCode()) && zdmx.getNodeId().equals(zg006.getNodeId())) {
					i = false;
				}
			}

			if (i) {
				zg006ZdmxList.add(zg006);
			}
		}
	}

	public List<Gr019Ysgrmx> a(List<Zg006Zdmx> zg006List, St003Cryxxb cryxxb, List<Gr018Ysgrys> gr018List) {
		ArrayList gr019List = new ArrayList();
		Iterator arg5 = zg006List.iterator();

		while (arg5.hasNext()) {
			Zg006Zdmx zg006 = (Zg006Zdmx) arg5.next();
			Gr019Ysgrmx gr019 = new Gr019Ysgrmx();
			if (cryxxb != null) {
				gr019.setZyid(cryxxb.getZyid());
				gr019.setInfectCode(zg006.getInfectCode());
				gr019.setNodeLevel(Integer.valueOf(Integer.parseInt(zg006.getNodeLevel())));
				gr019.setNodeId(zg006.getNodeId());
				gr019.setElementId(zg006.getElementId());
				gr019.setWeightValue(Integer.valueOf(Integer.parseInt(zg006.getWeightValue())));
				gr019.setPNodeId(zg006.getPNodeId());
				gr019.setWeightRatio(Double.valueOf(Double.parseDouble(zg006.getWeightRatio())));
				List ysgrysList = this.d(gr018List, zg006.getElementId());
				if (ysgrysList.size() > 0) {
					gr019.setYsId(((Gr018Ysgrys) ysgrysList.get(0)).getId());
				}

				gr019.setCreateAt(zg006.getDataDate());
				gr019.setLastAt(zg006.getLastDate());
				gr019.setStandardNo(Integer.valueOf(Integer.parseInt(zg006.getStandardNo())));
				gr019.setNodeType(zg006.getNodeType());
				gr019.setIfBaseElement(zg006.getIfBaseElement());
				gr019.setNodeType2(zg006.getNodeType2());
				gr019List.add(gr019);
			}
		}

		return gr019List;
	}

	public void a(List<Zg006Zdmx> zg006List, Zg006Zdmx zdmx) {
		if (ab.isNotEmpty(zdmx.getPNodeId())) {
			Zg006Zdmx zg006zdmx = this.bU.j(zdmx.getInfectCode() + "_" + zdmx.getPNodeId());
			Zg006Zdmx zg006 = (Zg006Zdmx) zg006zdmx.clone();
			if (zg006 != null) {
				zg006.setDataDate(zdmx.getDataDate());
				zg006.setLastDate(zdmx.getLastDate());
				zg006List.add(zg006);
				if (!"1".equals(zg006.getNodeLevel())) {
					this.a(zg006List, zg006);
				}
			}
		}

	}

	public List<Zg006Zdmx> j(List<Zg006Zdmx> list) {
      if(list != null) {
         Collections.sort(list, new AnalysisModelServiceImpl$2(this));
      }

      return list;
   }

	public List<Zg006Zdmx> k(List<Zg006Zdmx> list) {
      if(list != null) {
         Collections.sort(list, new AnalysisModelServiceImpl$3(this));
      }

      return list;
   }

	public void k() {
		List zg006List = this.bZ.getAll();
		List zg007List = this.F.getAll();
		Iterator arg3 = zg006List.iterator();

		while (arg3.hasNext()) {
			Zg006Zdmx zdmx = (Zg006Zdmx) arg3.next();
			zdmx.decode();
		}

		this.bU.e(zg006List);
		this.bU.a(zg006List, zg007List);
	}

	public void l() {
		this.bQ = Integer.parseInt(this.j.findByParamCode(Param.NIS_SQ_HOURS));
		this.bR = Integer.parseInt(this.j.findByParamCode(Param.NIS_MIN_WEIGHT));
	}

	public Integer a(St003Cryxxb cryxxb, Date grDate, Gr019YsgrmxWeight weight) {
		Integer infectTypeId = Integer.valueOf(2);
		Zg006ZdmxFa zg006Fa = this.cb.getState();
		Date bcyxDate = f.a(cryxxb.getInHospAt(), zg006Fa.getBcyxDay().intValue());
		Date jyxxDate = f.a(cryxxb.getInHospAt(), zg006Fa.getJyxxDay().intValue());
		Date xjppDate = f.a(cryxxb.getInHospAt(), zg006Fa.getXjppDay().intValue());
		Date kjywDate = f.a(cryxxb.getInHospAt(), zg006Fa.getKjyyDay().intValue());
		Date tyxzbDate = f.a(cryxxb.getInHospAt(), zg006Fa.getTyxzbDay().intValue());
		if (cryxxb != null && grDate != null) {
			boolean xsr = false;
			Date date = f.a(cryxxb.getBirthDate(), 30);
			if (cryxxb.getInHospAt() != null && date.getTime() > cryxxb.getInHospAt().getTime()) {
				xsr = true;
			}

			String xsrGr = this.j.findByParamCode(Param.NIS_ZDMX_XSRGR);
			if (xsr && "0".equals(xsrGr)) {
				infectTypeId = Integer.valueOf(1);
			}

			if (f.d(cryxxb.getInHospAt(), this.bQ).getTime() < grDate.getTime()) {
				infectTypeId = Integer.valueOf(1);
			}
		}

		return infectTypeId;
	}

	public Date l(List<Zg006Zdmx> zg006List) {
      Date dataAt = null;
      ArrayList tyxJbList = new ArrayList();
      Iterator arg4 = zg006List.iterator();

      while(true) {
         while(true) {
            Zg006Zdmx zg006;
            do {
               if(!arg4.hasNext()) {
                  if(tyxJbList != null && tyxJbList.size() > 0) {
                     Collections.sort(tyxJbList, new AnalysisModelServiceImpl$4(this));
                     dataAt = ((Zg006Zdmx)tyxJbList.get(0)).getDataDate();
                  }

                  return dataAt;
               }

               zg006 = (Zg006Zdmx)arg4.next();
            } while(zg006.getDataDate() == null);

            if(!"Organ".equals(zg006.getInfectCode()) && !"DIP".equals(zg006.getInfectCode()) && !"SIP".equals(zg006.getInfectCode())) {
               if("TYX".equals(zg006.getNodeType2())) {
                  tyxJbList.add(zg006);
               } else if("JB".equals(zg006.getNodeType2())) {
                  tyxJbList.add(zg006);
               } else if("JY".equals(zg006.getNodeType2())) {
                  tyxJbList.add(zg006);
               } else if("KJ".equals(zg006.getNodeType2())) {
                  tyxJbList.add(zg006);
               }
            } else if("TYX".equals(zg006.getNodeType2())) {
               tyxJbList.add(zg006);
            } else if("JY".equals(zg006.getNodeType2())) {
               tyxJbList.add(zg006);
            } else if("KJ".equals(zg006.getNodeType2())) {
               tyxJbList.add(zg006);
            }
         }
      }
   }

	public Date c(List<Zg006Zdmx> zg006ZdmxList, String minOrMax) {
      Date dataAt = null;
      if(zg006ZdmxList != null && zg006ZdmxList.size() > 0) {
         Collections.sort(zg006ZdmxList, new AnalysisModelServiceImpl$5(this));
         if("min".equals(minOrMax)) {
            dataAt = ((Zg006Zdmx)zg006ZdmxList.get(0)).getDataDate();
         } else if("max".equals(minOrMax)) {
            dataAt = ((Zg006Zdmx)zg006ZdmxList.get(zg006ZdmxList.size() - 1)).getDataDate();
         }
      }

      return dataAt;
   }

	public List<Zg006Zdmx> m(List<Gr019Ysgrmx> gr019List) {
		ArrayList zdmxList = new ArrayList();

		Zg006Zdmx zdmx;
		for (Iterator arg3 = gr019List.iterator(); arg3.hasNext(); zdmxList.add(zdmx)) {
			Gr019Ysgrmx gr019 = (Gr019Ysgrmx) arg3.next();
			zdmx = new Zg006Zdmx();
			zdmx.setInfectCode(gr019.getInfectCode());
			zdmx.setStandardNo("" + gr019.getStandardNo());
			zdmx.setNodeLevel(gr019.getNodeLevel().toString());
			zdmx.setNodeId(gr019.getNodeId());
			zdmx.setIfMust("" + gr019.getIfMust());
			zdmx.setElementId(gr019.getElementId());
			zdmx.setIfMust("" + gr019.getIfMust());
			zdmx.setListCount("" + gr019.getListCount());
			zdmx.setNeedCount("" + gr019.getNeedCount());
			zdmx.setWeightValue("" + gr019.getWeightValue());
			zdmx.setPNodeId(gr019.getPNodeId());
			zdmx.setWeightRatio("" + gr019.getWeightRatio());
			zdmx.setNodeType(gr019.getNodeType());
			zdmx.setNodeType2(gr019.getNodeType2());
			if (gr019.getCreateAt() != null) {
				zdmx.setDataDate(gr019.getCreateAt());
				zdmx.setLastDate(gr019.getLastAt());
			} else {
				Gr018Ysgrys ysgrs = this.aR.bP(gr019.getYsId());
				if (ysgrs != null) {
					zdmx.setDataDate(ysgrs.getDataDate());
					zdmx.setLastDate(ysgrs.getDataDate());
				}
			}
		}

		return zdmxList;
	}

	public boolean b(List<Zg006Zdmx> zdmxList, Zg006Zdmx zdmx) {
		boolean isMust = false;
		String key = zdmx.getInfectCode() + "_" + zdmx.getNodeId();
		List zg006List = (List) this.bS.get(key);
		ArrayList zg006ZdmxList = new ArrayList();
		if (this.bS.get(key) != null) {
			Iterator arg7 = zdmxList.iterator();

			while (arg7.hasNext()) {
				Zg006Zdmx zd = (Zg006Zdmx) arg7.next();
				if (zdmx.getNodeId().equals(zd.getPNodeId()) && "1".equals(zdmx.getIfMust())) {
					zg006ZdmxList.add(zd);
				}
			}

			if (zg006ZdmxList.size() != zg006List.size()) {
				isMust = true;
			}
		}

		return isMust;
	}

	public boolean c(List<Zg006Zdmx> zdmxList, Zg006Zdmx zdmx) {
		boolean isNeedCount = false;
		String key = zdmx.getInfectCode() + "_" + zdmx.getNodeId();
		if (this.bT.get(key) != null) {
			int needCount = Integer.parseInt((String) this.bT.get(key));
			int num = 0;
			Iterator arg7 = zdmxList.iterator();

			while (arg7.hasNext()) {
				Zg006Zdmx zd = (Zg006Zdmx) arg7.next();
				if (zdmx.getNodeId().equals(zd.getPNodeId())) {
					++num;
				}
			}

			if (num < needCount) {
				isNeedCount = true;
			}
		}

		return isNeedCount;
	}

	public void m() {
		List zg006List = this.bZ.findNeedCountList();
		Iterator arg2 = zg006List.iterator();

		while (arg2.hasNext()) {
			Zg006Zdmx zdmx = (Zg006Zdmx) arg2.next();
			String key = zdmx.getInfectCode() + "_" + zdmx.getPNodeId();
			this.bT.put(key, zdmx.getNeedCount());
		}

	}

	public void n() {
		List zg006List = this.bZ.findMustList();
		HashMap pMustInfectCodeNodeIdMap = new HashMap();
		Iterator arg3 = zg006List.iterator();

		while (arg3.hasNext()) {
			Zg006Zdmx entry = (Zg006Zdmx) arg3.next();
			pMustInfectCodeNodeIdMap.put(entry.getInfectCode() + "_" + entry.getPNodeId(),
					entry.getInfectCode() + "_" + entry.getPNodeId());
		}

		arg3 = pMustInfectCodeNodeIdMap.entrySet().iterator();

		while (arg3.hasNext()) {
			Entry entry1 = (Entry) arg3.next();
			ArrayList zdmxList = new ArrayList();
			Iterator arg6 = zg006List.iterator();

			while (arg6.hasNext()) {
				Zg006Zdmx zdmx = (Zg006Zdmx) arg6.next();
				String key = zdmx.getInfectCode() + "_" + zdmx.getPNodeId();
				if (key.equals(entry1.getKey())) {
					zdmxList.add(zdmx);
				}
			}

			this.bS.put((String) entry1.getKey(), zdmxList);
		}

	}

	public List<Gr018Ysgrys> d(List<Gr018Ysgrys> gr018List, String elementId) {
		ArrayList ysgrysList = new ArrayList();
		if (ab.isNotEmpty(elementId)) {
			Iterator arg4 = gr018List.iterator();

			while (arg4.hasNext()) {
				Gr018Ysgrys gr018 = (Gr018Ysgrys) arg4.next();
				if (elementId.equals(gr018.getElementId())) {
					ysgrysList.add(gr018);
				}
			}
		}

		return ysgrysList;
	}

	public String d() {
		String res = null;
		DefaultHttpClient httpclient = i.getHttpClient();
		HashMap param = new HashMap();
		HashMap params = new HashMap();
		ArrayList features = new ArrayList();
		HashMap map = new HashMap();
		map.put("position", "腹腔内组织");
		map.put("exception", "膈下脓肿,腹水");
		features.add(map);
		String url = "http://localhost:8000/nis7.0.0/analysis/i_adapter/infectionDiagnosis.shtml";
		param.put("sex", "男");
		param.put("age", "0-6");
		param.put("desc",
				" 一.病例特点:    1.患者李维珍，女，52岁    2.主诉：突发右侧肢体偏瘫、头晕7+小时。    3.临床表现：患者诉今晨10时左右在家中无明显诱因突起右侧肢体偏瘫、麻木感，行走不能，伴头晕不适，无呕吐、头痛、视物旋转、抽搐、肢体偏瘫、胸闷、心悸、咳嗽、呼吸困难、发热等，其周边邻居发现后，通知其家属，并送至当地卫生院就诊，经询问病史、体格检查后，考虑病情危重，遂由救护车转送至我院就诊，急诊行头部CT检查后诊断为“脑出血”，并收住我科。起病来，精神差，未进食，无大小便失禁。    4.既往史：既往有“高血压”病史3+年，最高血压＞180/100mmHg，未系统服药治疗。    5.体格检查：体温:36.5℃  脉搏:81次/分  呼吸:22次/分  血压:151/89mmHg 意识清醒，被动卧位，体查合作，双侧额纹对称，眼睑闭合可，双侧瞳孔等大等圆约3.0mm，对光反射灵敏，眼球各项活动正常，双侧鼻唇沟对称，口角无歪斜，伸舌居中，颈软，双肺呼吸音清晰，未闻及干湿啰音。心率78次/分，律齐，无杂音。腹部平软，无压痛及反跳痛，肝脾肋下未扪及，未扪及肿块，腹部移动性浊音征阴性，肠鸣音4次/分，双肾区无叩痛，双下肢无水肿。左侧肢体肌力4-级，肌张力正常，右侧肢体肌力5级，肌张力正常，病理征未引出。    6.辅助检查：2017-4-8我院本部急诊头部CT示：右侧颞叶深部脑出血。    二.拟诊讨论：    （一）诊断依据：    1.右侧基底节区脑出血。依据：1）中老年女性，52岁；2）因“突发右侧肢体偏瘫、头晕7+小时”入院；3）体查：血压:151/89mmHg 双侧瞳孔等大等圆约3.0mm，对光反射灵敏，眼球各项活动正常，左侧肢体肌力4-级，肌张力正常，右侧肢体肌力5级，肌张力正常，病理征未引出。    2.原发性高血压病 Ⅲ级 极高危组。依据：1）入院血压:151/89mmHg；2）既往有“高血压”病史3+年，最高血压＞180/100mmHg。    （二）鉴别诊断：    1.脑梗塞：一般为亚急性起病，有神经系统表现，脑干及小脑梗死有头晕呕吐等表现，头部CT表现为低密度及MRI有相应信号改变可鉴别。    2.高血压脑病：一般有高血压等病史，临床表现为血压极高，头痛、呕吐、抽搐及意识障碍等，降压治疗血压下降后患者症状缓解，一般头部CT正常，个别患者可伴有颅内血肿，应排除。    （三）入院诊断：    1.右侧基底节区脑出血；    2.原发性高血压病 Ⅲ级 极高危组。    三.病历分型：C型    四.诊疗计划:    1.神经内科护理常规、重症监护、吸氧、告病危、监测生命体征、控制血压、止血、护胃、营养脑细胞、补液及对症支持治疗。    2.三大常规、生化、凝血、感染四项、血型鉴定、糖化血红蛋白、心肌酶、心电图、胸片、CT等。    3.密观病情变化，动态复查颅脑CT，及时调整治疗方案。    4.告知患者家属病情、治疗方案及可能存在的风险，征得同意、理解。                                                  医师签名：杨生伟");
		param.put("features", features);
		params.put("data", l.toString(param));

		try {
			res = i.a(httpclient, params, url, "utf-8");
		} catch (IOException arg8) {
			arg8.printStackTrace();
		}

		return res;
	}

	public void n(String id) {
		SysJudgeLog sysJudgeLog = this.J.get(id);
		this.l();
		this.k();
		this.n();
		this.m();
		int total = this.aR.ad();
		sysJudgeLog.setTotalCount(Integer.valueOf(total));
		sysJudgeLog.setSuccessCount(Integer.valueOf(0));
		this.J.update(sysJudgeLog);
		this.c(sysJudgeLog);
		sysJudgeLog.setEndTime(new Date());
		sysJudgeLog.setStatus("1");
		this.J.update(sysJudgeLog);
	}

	public void c(SysJudgeLog sysJudgeLog) {
      TaskJob taskJob = this.aU.findByLink("f_task/ysgrMx.shtml");
      if(taskJob != null && taskJob.getStatus().intValue() == 50) {
         sysJudgeLog.setStatus("3");
         this.J.update(sysJudgeLog);
      } else {
         List zyids = this.aR.ac();
         c.info("总计" + zyids.size() + "个患者");
         if(zyids.size() > 0) {
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

            for(int e = 0; e < zyids.size(); ++e) {
               String zyid = (String)zyids.get(e);
               fixedThreadPool.execute(new AnalysisModelServiceImpl$6(this, zyid, sysJudgeLog));
            }

            fixedThreadPool.shutdown();

            while(!fixedThreadPool.isTerminated()) {
               try {
                  Thread.sleep(1000L);
               } catch (InterruptedException arg6) {
                  arg6.printStackTrace();
               }
            }

            this.c(sysJudgeLog);
         }

      }
   }
}