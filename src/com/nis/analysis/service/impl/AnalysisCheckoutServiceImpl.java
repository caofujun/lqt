package com.nis.analysis.service.impl;

import com.nis.analysis.entity.NyUnanalyzeBbByt;
import com.nis.analysis.entity.NyUnanalyzeDict;
import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.service.AnalysisCheckoutService;
import com.nis.analysis.service.NyUnanalyzeBbBytService;
import com.nis.analysis.service.NyUnanalyzeDictService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.analysis.until.a;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ae;
import com.nis.comm.enums.ah;
import com.nis.comm.enums.ai;
import com.nis.comm.enums.aj;
import com.nis.comm.enums.bh;
import com.nis.comm.enums.bi;
import com.nis.comm.enums.k;
import com.nis.comm.enums.l;
import com.nis.comm.utils.f;
import com.nis.comm.utils.r;
import com.nis.dict.entity.NyBbDict;
import com.nis.dict.entity.Zg032Sjxmpp;
import com.nis.dict.entity.Zg033Jcxxpp;
import com.nis.dict.service.NyBbDictService;
import com.nis.dict.service.Zg032SjxmppService;
import com.nis.log.service.SysLogService;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St009Sjbb;
import com.nis.patient.entity.St010Jcbyt;
import com.nis.patient.entity.St011Syjgb;
import com.nis.patient.service.St009SjbbService;
import com.nis.patient.service.St010JcbytService;
import com.nis.patient.service.St011SyjgbService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import com.nis.zg.entity.Zg027LisbbPp;
import com.nis.zg.service.Zg027LisbbPpService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AnalysisCheckoutServiceImpl implements AnalysisCheckoutService {
	private static final Logger c = Logger.getLogger(AnalysisCheckoutServiceImpl.class);
	private int bw = 0;
	private int bx = 0;
	private int by = 0;
	private int bA = 0;
	@Autowired
	private NyBbDictService bB;
	@Autowired
	private Zg027LisbbPpService bC;
	@Autowired
	private NyUnanalyzeBbBytService V;
	@Autowired
	private NyUnanalyzeDictService bD;
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private St010JcbytService bF;
	@Autowired
	private Gr018YsgrysService aR;
	@Autowired
	private Zg032SjxmppService bG;
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysJudgeLogService J;
	@Autowired
	private TaskJobService aU;

	public void c(String version, String id) {
		SysJudgeLog sysJudgeLog = this.J.get(id);
		TaskJob taskJob = this.aU.findByLink(ae.in.getUrl());
		if (taskJob != null && taskJob.getStatus().intValue() == 50) {
			sysJudgeLog.setStatus("3");
			sysJudgeLog.setEndTime(new Date());
			this.J.update(sysJudgeLog);
		} else {
			boolean total = false;
			Date curr = new Date();
			int arg19;
			if ("6".equals(version)) {
				arg19 = this.bE.x(curr);
			} else {
				arg19 = this.bE.w(curr);
			}

			sysJudgeLog.setTotalCount(Integer.valueOf(arg19));
			this.J.update(sysJudgeLog);
			List bbDictList = this.bB.getAll();
			HashMap bbDictMap = new HashMap();
			NyBbDict lisBbPpList;
			Iterator lisBbPpMap;
			if (bbDictList.size() > 0) {
				lisBbPpMap = bbDictList.iterator();

				while (lisBbPpMap.hasNext()) {
					lisBbPpList = (NyBbDict) lisBbPpMap.next();
					bbDictMap.put(lisBbPpList.getBbid(), lisBbPpList);
				}
			}

			bbDictList = null;
			List arg20 = this.bC.getAll();
			HashMap arg21 = new HashMap();
			Zg027LisbbPp bbBytList;
			Iterator bbBytSet;
			if (arg20.size() > 0) {
				bbBytSet = arg20.iterator();

				while (bbBytSet.hasNext()) {
					bbBytList = (Zg027LisbbPp) bbBytSet.next();
					if (StringUtils.isNotBlank(bbBytList.getItemCode())) {
						arg21.put(bbBytList.getItemCode(), bbBytList);
					}
				}
			}

			lisBbPpList = null;
			List arg22 = this.V.getAll();
			HashSet arg23 = new HashSet();
			NyUnanalyzeBbByt unDictList;
			Iterator unDictSet;
			if (arg22.size() > 0) {
				unDictSet = arg22.iterator();

				while (unDictSet.hasNext()) {
					unDictList = (NyUnanalyzeBbByt) unDictSet.next();
					arg23.add(unDictList.getBbid() + '-' + unDictList.getBytName());
				}
			}

			bbBytList = null;
			List arg24 = this.bD.getAll();
			HashSet arg25 = new HashSet();
			if (arg24.size() > 0) {
				Iterator size = arg24.iterator();

				while (size.hasNext()) {
					NyUnanalyzeDict hasMore = (NyUnanalyzeDict) size.next();
					arg25.add(hasMore.getDcName());
				}
			}

			unDictList = null;
			boolean arg26 = true;
			Integer arg27 = Integer.valueOf(500);
			List sjbbList = null;
			Date submiAt = new Date();
			int index = r.d(this.j.findByParamCode(Param.NIS_ANA_PREVENT_INFINITE_LOOP));

			while (arg26) {
				taskJob = this.aU.findByLink(ae.in.getUrl());
				if (taskJob != null && taskJob.getStatus().intValue() == 50) {
					sysJudgeLog.setStatus("3");
					sysJudgeLog.setEndTime(new Date());
					this.J.update(sysJudgeLog);
					arg26 = false;
				}

				--index;
				if ("6".equals(version)) {
					sjbbList = this.bE.b(curr, arg27);
				} else {
					sjbbList = this.bE.a(curr, arg27);
				}

				if (sjbbList == null || sjbbList.size() < arg27.intValue() || index <= 0) {
					arg26 = false;
				}

				if (sjbbList.size() > 0) {
					this.a(sjbbList, bbDictMap, arg21, arg23, arg25, submiAt, sysJudgeLog);
				}
			}

			sjbbList = null;
			bbDictMap = null;
			lisBbPpMap = null;
			bbBytSet = null;
			unDictSet = null;
			if (this.bw > sysJudgeLog.getTotalCount().intValue()) {
				this.bw = sysJudgeLog.getTotalCount().intValue();
			}

			sysJudgeLog.setSuccessCount(Integer.valueOf(this.bw));
			sysJudgeLog.setFailCount(Integer.valueOf(this.bx));
			sysJudgeLog.setEndTime(new Date());
			if (this.bx == 0 || !sysJudgeLog.getStatus().equals("3")) {
				sysJudgeLog.setStatus("1");
			}

			this.J.update(sysJudgeLog);
		}

	}

	@Transactional(rollbackFor = {Exception.class})
	private void a(List<St009Sjbb> sjbbList, Map<String, NyBbDict> bbDictMap, Map<String, Zg027LisbbPp> lisBbPpMap,
			Set<String> bbBytSet, Set<String> unDictSet, Date submiAt, SysJudgeLog sysJudgeLog) {
		ArrayList st009Upd = new ArrayList();
		ArrayList gr018Ins = new ArrayList();
		Date curr = new Date();
		StringBuffer elementName = new StringBuffer();
		NyBbDict nyBbDict = null;
		HashSet gr018KeySet = new HashSet();
		boolean isWarn = true;
		boolean num = false;
		Iterator arg17 = sjbbList.iterator();

		while (true) {
			label52 : while (arg17.hasNext()) {
				St009Sjbb st009Sjbb = (St009Sjbb) arg17.next();
				List st011List = this.bH.findByZyidTestNo(st009Sjbb.getZyid(), st009Sjbb.getTestOrderNo());
				if (st011List != null && st011List.size() > 0) {
					try {
						elementName.setLength(0);
						st009Sjbb.setSjbbAnalDt(curr);
						st009Sjbb.setSjbbAnalFlag(bh.oc.getValue());
						Zg027LisbbPp e = (Zg027LisbbPp) lisBbPpMap.get(st009Sjbb.getItemCode());
						if (e != null && StringUtils.isNotBlank(e.getBbid())) {
							nyBbDict = (NyBbDict) bbDictMap.get(e.getBbid());
						} else if (StringUtils.isNotBlank(st009Sjbb.getItemName())) {
							nyBbDict = this.a(bbDictMap, st009Sjbb.getItemName());
							if (nyBbDict != null) {
								st009Sjbb.setLikeFlag(bi.of.getValue());
							}
						}

						if (nyBbDict == null) {
							nyBbDict = new NyBbDict();
							nyBbDict.setElementId("JY000270");
						}

						isWarn = true;
						elementName.append("从<" + st009Sjbb.getItemName() + ">培养出：");
						List st010List = this.bF.findByZyidTestNo(st009Sjbb.getZyid(), st009Sjbb.getTestOrderNo());
						Iterator gr018Ysgrys = st010List.iterator();

						while (true) {
							St010Jcbyt gr018Key;
							do {
								do {
									if (!gr018Ysgrys.hasNext()) {
										String gr018Key1 = st009Sjbb.getZyid() + f.formatDate(st009Sjbb.getSubmiAt())
												+ nyBbDict.getElementId();
										if (isWarn && !gr018KeySet.contains(gr018Key1)) {
											int num1 = 0;
											if (!"JY000270".equals(nyBbDict.getElementId())) {
												gr018KeySet.add(gr018Key1);
												num1 = this.aR.a(st009Sjbb.getZyid(), st009Sjbb.getSubmiAt(),
														nyBbDict.getElementId(), "检验");
											}

											if (num1 == 0) {
												submiAt.setTime(submiAt.getTime() + 1000L);
												Gr018Ysgrys gr018Ysgrys1 = new Gr018Ysgrys();
												gr018Ysgrys1.setZyid(st009Sjbb.getZyid());
												gr018Ysgrys1.setDataDate(f.f(st009Sjbb.getSubmiAt(), submiAt));
												gr018Ysgrys1.setElementId(nyBbDict.getElementId());
												gr018Ysgrys1.setElementName(
														elementName.substring(0, elementName.length() - 1));
												gr018Ysgrys1.setMonitorAt(curr);
												gr018Ysgrys1.setDataForm("检验");
												gr018Ysgrys1.setOriginalContent(gr018Ysgrys1.getElementName());
												gr018Ysgrys1.setDataType("文本");
												gr018Ysgrys1.setState(Integer.valueOf(0));
												gr018Ysgrys1.setSjId(st009Sjbb.getTestOrderNo());
												gr018Ins.add(gr018Ysgrys1);
											}
										}

										st009Upd.add(st009Sjbb);
										++this.bw;
										sysJudgeLog.setSuccessCount(Integer.valueOf(this.bw));
										sysJudgeLog.setEndTime(new Date());
										this.J.update(sysJudgeLog);
										continue label52;
									}

									gr018Key = (St010Jcbyt) gr018Ysgrys.next();
									elementName.append(gr018Key.getPathoName() + ",");
								} while ("JY000270".equals(nyBbDict.getElementId()));
							} while ((e == null || !bbBytSet.contains(e.getBbid() + "-" + gr018Key.getPathoName()))
									&& !unDictSet.contains(gr018Key.getPathoName()));

							isWarn = false;
						}
					} catch (Exception arg22) {
						++this.bx;
						sysJudgeLog.setFailCount(Integer.valueOf(this.bx));
						sysJudgeLog.setStatus("2");
						this.J.update(sysJudgeLog);
						arg22.printStackTrace();
						c.error("标本分析（细菌）异常!", arg22);
						this.aV.a(ah.iG,
								"标本分析（细菌）异常{AnalysisCheckoutServiceImpl.phaseAnalysis()},zyid=" + st009Sjbb.getZyid()
										+ "testOrderNo=" + st009Sjbb.getTestOrderNo(),
								sysJudgeLog.getId(), arg22.getMessage());
					}
				} else {
					st009Sjbb.setSjbbAnalDt(f.a(new Date(), 1));
					st009Upd.add(st009Sjbb);
				}
			}

			this.aR.batchInsert(gr018Ins);
			this.bE.batchUpdAnalFlag(st009Upd);
			gr018KeySet = null;
			st009Upd = null;
			gr018Ins = null;
			return;
		}
	}

	private NyBbDict a(Map<String, NyBbDict> bbDictMap, String itemName) {
		Iterator arg3 = bbDictMap.values().iterator();

		NyBbDict dict;
		do {
			if (!arg3.hasNext()) {
				return null;
			}

			dict = (NyBbDict) arg3.next();
		} while (!StringUtils.isNotBlank(dict.getBbmc()) || dict.getBbmc().indexOf(itemName) < 0);

		return dict;
	}

	public void d(String version, String id) {
		SysJudgeLog sysJudgeLog = this.J.get(id);
		TaskJob taskJob = this.aU.findByLink(ae.io.getUrl());
		if (taskJob != null && taskJob.getStatus().intValue() == 50) {
			sysJudgeLog.setStatus("3");
			sysJudgeLog.setEndTime(new Date());
			this.J.update(sysJudgeLog);
		} else {
			boolean total = false;
			Date curr = new Date();
			int arg12;
			if ("6".equals(version)) {
				arg12 = this.bE.z(curr);
			} else {
				arg12 = this.bE.y(curr);
			}

			sysJudgeLog.setTotalCount(Integer.valueOf(arg12));
			this.J.update(sysJudgeLog);
			boolean hasMore = true;
			Integer size = Integer.valueOf(500);
			List sjbbList = null;
			List sjxmList = this.bG.findJoinGrys();
			Date submiAt = new Date();
			int index = r.d(this.j.findByParamCode(Param.NIS_ANA_PREVENT_INFINITE_LOOP));

			while (hasMore) {
				taskJob = this.aU.findByLink(ae.io.getUrl());
				if (taskJob != null && taskJob.getStatus().intValue() == 50) {
					sysJudgeLog.setStatus("3");
					sysJudgeLog.setEndTime(new Date());
					this.J.update(sysJudgeLog);
					hasMore = false;
				}

				--index;
				if ("6".equals(version)) {
					sjbbList = this.bE.d(curr, size);
				} else {
					sjbbList = this.bE.c(curr, size);
				}

				if (sjbbList == null || sjbbList.size() < size.intValue() || index <= 0) {
					hasMore = false;
				}

				if (sjbbList != null && sjbbList.size() > 0) {
					this.a(sjbbList, sjxmList, submiAt, sysJudgeLog);
				}
			}

			sjbbList = null;
			sjxmList = null;
			sysJudgeLog.setSuccessCount(Integer.valueOf(this.by));
			sysJudgeLog.setFailCount(Integer.valueOf(this.bA));
			sysJudgeLog.setEndTime(new Date());
			if (this.bA == 0 || !sysJudgeLog.getStatus().equals("3")) {
				sysJudgeLog.setStatus("1");
			}

			this.J.update(sysJudgeLog);
		}

	}

	@Transactional(rollbackFor = {Exception.class})
	private void a(List<St009Sjbb> sjbbList, List<Zg032Sjxmpp> sjxmList, Date submiAt, SysJudgeLog sysJudgeLog) {
		ArrayList st009Upd = new ArrayList();
		ArrayList gr018Ins = new ArrayList();
		Date curr = new Date();
		StringBuffer elementName = new StringBuffer();
		HashSet gr018KeySet = new HashSet();
		boolean isSendMatch = false;
		boolean isWarn = false;
		HashSet groupSet = new HashSet();
		boolean num = false;
		Iterator arg16 = sjbbList.iterator();

		while (arg16.hasNext()) {
			St009Sjbb st009Sjbb = (St009Sjbb) arg16.next();

			try {
				st009Sjbb.setSjbbAnalDt(curr);
				st009Sjbb.setSjbbAnalFlag(bh.oc.getValue());
				List st011List = this.bH.findByZyidTestNo(st009Sjbb.getZyid(), st009Sjbb.getTestOrderNo());
				groupSet.clear();
				Iterator arg18 = sjxmList.iterator();

				while (arg18.hasNext()) {
					Zg032Sjxmpp e = (Zg032Sjxmpp) arg18.next();
					if (e.getElementName().equals("尿常规白细胞++")) {
						System.out.print("---");
					}

					isSendMatch = false;
					String groupKey = e.getMatchField() + e.getMatch() + e.getMatchValue() + e.getGroupCode();
					if (!groupSet.contains(groupKey)) {
						String fieldValue = "";
						if (ai.iK.getValue().equals(e.getMatchField())) {
							fieldValue = st009Sjbb.getItemTypeName();
						} else if (ai.iL.getValue().equals(e.getMatchField())) {
							fieldValue = st009Sjbb.getItemName();
						}

						if (StringUtils.isNotBlank(fieldValue)) {
							if (aj.iN.getValue().equals(e.getMatch()) && fieldValue.indexOf(e.getMatchValue()) >= 0) {
								isSendMatch = true;
							} else if (fieldValue.equals(e.getMatchValue())) {
								isSendMatch = true;
							}
						}

						if (isSendMatch) {
							String gr018Key = st009Sjbb.getZyid() + f.formatDate(st009Sjbb.getSubmiAt())
									+ e.getInfectFactor();
							isWarn = false;
							elementName.setLength(0);
							if (!gr018KeySet.contains(gr018Key)) {
								elementName.append("从<" + st009Sjbb.getItemName() + ">检出：");
								List jcxxppList = e.getJcxxppList();
								Iterator arg23 = jcxxppList.iterator();

								while (arg23.hasNext()) {
									Zg033Jcxxpp gr018Ysgrys = (Zg033Jcxxpp) arg23.next();
									if (k.fZ.getValue().equals(gr018Ysgrys.getItemField())) {
										if (aj.iN.getValue().equals(gr018Ysgrys.getItemMatch())) {
											isWarn = this.a(st011List, gr018Ysgrys, elementName);
											if (isWarn) {
												break;
											}
										} else {
											isWarn = this.a(st011List, gr018Ysgrys, elementName);
											if (isWarn) {
												break;
											}
										}
									}
								}
							}

							if (isWarn) {
								groupSet.add(groupKey);
								gr018KeySet.add(gr018Key);
								int num1 = this.aR.a(st009Sjbb.getZyid(), st009Sjbb.getSubmiAt(), e.getInfectFactor(),
										"检验");
								if (num1 == 0) {
									submiAt.setTime(submiAt.getTime() + 1000L);
									Gr018Ysgrys gr018Ysgrys1 = new Gr018Ysgrys();
									gr018Ysgrys1.setZyid(st009Sjbb.getZyid());
									gr018Ysgrys1.setDataDate(f.f(st009Sjbb.getSubmiAt(), submiAt));
									gr018Ysgrys1.setElementId(e.getInfectFactor());
									gr018Ysgrys1.setElementName(e.getElementName());
									gr018Ysgrys1.setMonitorAt(curr);
									gr018Ysgrys1.setDataForm("检验");
									gr018Ysgrys1.setOriginalContent(elementName.toString());
									gr018Ysgrys1.setDataType("文本");
									gr018Ysgrys1.setState(Integer.valueOf(0));
									gr018Ysgrys1.setSjId(st009Sjbb.getTestOrderNo());
									gr018Ins.add(gr018Ysgrys1);
								}
							}
						}
					}
				}

				st009Upd.add(st009Sjbb);
				++this.by;
				if (this.by > sysJudgeLog.getTotalCount().intValue()) {
					this.by = sysJudgeLog.getTotalCount().intValue();
				}

				sysJudgeLog.setSuccessCount(Integer.valueOf(this.by));
				sysJudgeLog.setEndTime(new Date());
				this.J.update(sysJudgeLog);
			} catch (Exception arg24) {
				++this.bA;
				sysJudgeLog.setFailCount(Integer.valueOf(this.bA));
				sysJudgeLog.setStatus("2");
				this.J.update(sysJudgeLog);
				arg24.printStackTrace();
				c.error("常规检出分析异常!", arg24);
				this.aV.a(ah.iG, "常规检出分析异常{AnalysisCheckoutServiceImpl.commAnalysis()},zyid=" + st009Sjbb.getZyid()
						+ "testOrderNo=" + st009Sjbb.getTestOrderNo(), sysJudgeLog.getId(), arg24.getMessage());
			}
		}

		this.aR.batchInsert(gr018Ins);
		this.bE.batchUpdAnalFlag(st009Upd);
		gr018KeySet = null;
		st009Upd = null;
		gr018Ins = null;
	}

	private boolean a(List<St011Syjgb> st011List, Zg033Jcxxpp zg033Jcxxpp, StringBuffer elementName) {
		if (st011List != null && st011List.size() > 0) {
			Iterator arg4 = st011List.iterator();

			while (true) {
				while (true) {
					St011Syjgb st011Syjgb;
					do {
						do {
							do {
								do {
									if (!arg4.hasNext()) {
										return false;
									}

									st011Syjgb = (St011Syjgb) arg4.next();
								} while (st011Syjgb == null);
							} while (!StringUtils.isNotBlank(st011Syjgb.getAntiName()));
						} while (!StringUtils.isNotBlank(zg033Jcxxpp.getItemMatchValue()));
					} while (st011Syjgb.getAntiName().indexOf(zg033Jcxxpp.getItemMatchValue()) < 0);

					if (!StringUtils.isNotBlank(zg033Jcxxpp.getValueField())) {
						elementName.append(st011Syjgb.getAntiName() + " " + st011Syjgb.getRemark());
						return true;
					}

					if (l.gb.getValue().equals(zg033Jcxxpp.getValueField())
							&& StringUtils.isNotBlank(st011Syjgb.getTestResult())) {
						if (aj.iN.getValue().equals(zg033Jcxxpp.getValueMatch())) {
							if (st011Syjgb.getTestResult().indexOf(zg033Jcxxpp.getValueMatchValue()) >= 0) {
								elementName.append(st011Syjgb.getAntiName() + " " + st011Syjgb.getTestResult());
								return true;
							}
						} else if (aj.iP.getValue().equals(zg033Jcxxpp.getValueMatch())) {
							if (a.j(st011Syjgb.getTestResult(), st011Syjgb.getUnit()) > a
									.r(zg033Jcxxpp.getValueMatchValue())) {
								elementName.append(st011Syjgb.getAntiName() + " " + st011Syjgb.getTestResult()
										+ st011Syjgb.getUnit());
								return true;
							}
						} else if (aj.iQ.getValue().equals(zg033Jcxxpp.getValueMatch())) {
							if (a.j(st011Syjgb.getTestResult(), st011Syjgb.getUnit()) < a
									.r(zg033Jcxxpp.getValueMatchValue())) {
								elementName.append(st011Syjgb.getAntiName() + " " + st011Syjgb.getTestResult()
										+ st011Syjgb.getUnit());
								return true;
							}
						} else if (st011Syjgb.getTestResult().equals(zg033Jcxxpp.getValueMatchValue())) {
							elementName.append(st011Syjgb.getAntiName() + " " + st011Syjgb.getTestResult());
							return true;
						}
					} else if (l.gc.getValue().equals(zg033Jcxxpp.getValueField())
							&& StringUtils.isNotBlank(st011Syjgb.getRemark())) {
						if (aj.iN.getValue().equals(zg033Jcxxpp.getValueMatch())) {
							if (st011Syjgb.getRemark().indexOf(zg033Jcxxpp.getValueMatchValue()) >= 0) {
								elementName.append(st011Syjgb.getAntiName() + " " + st011Syjgb.getRemark());
								return true;
							}
						} else if (aj.iP.getValue().equals(zg033Jcxxpp.getValueMatch())) {
							if (a.r(st011Syjgb.getRemark()) > a.r(zg033Jcxxpp.getValueMatchValue())) {
								elementName.append(st011Syjgb.getAntiName() + " " + st011Syjgb.getRemark());
								return true;
							}
						} else if (aj.iQ.getValue().equals(zg033Jcxxpp.getValueMatch())) {
							if (a.r(st011Syjgb.getRemark()) < a.r(zg033Jcxxpp.getValueMatchValue())) {
								elementName.append(st011Syjgb.getAntiName() + " " + st011Syjgb.getRemark());
								return true;
							}
						} else if (st011Syjgb.getRemark().equals(zg033Jcxxpp.getValueMatchValue())) {
							elementName.append(st011Syjgb.getAntiName() + " " + st011Syjgb.getRemark());
							return true;
						}
					}
				}
			}
		} else {
			return false;
		}
	}
}