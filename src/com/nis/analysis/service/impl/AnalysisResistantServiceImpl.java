package com.nis.analysis.service.impl;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.service.AnalysisResistantService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ae;
import com.nis.comm.enums.ah;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.y;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.r;
import com.nis.comm.utils.z;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import com.nis.mdr.entity.Xn008Jlybz;
import com.nis.mdr.entity.Xn009Lybzmx;
import com.nis.mdr.entity.Xn011Dclymx;
import com.nis.mdr.entity.Xn013Dbyw;
import com.nis.mdr.entity.Xn015Trlyjl;
import com.nis.mdr.entity.Xn017Tsnyzd;
import com.nis.mdr.service.Xn004TrnyService;
import com.nis.mdr.service.Xn008JlybzService;
import com.nis.mdr.service.Xn011DclymxService;
import com.nis.mdr.service.Xn015TrlyjlService;
import com.nis.mdr.service.Xn017TsnyzdService;
import com.nis.monitor.service.Bk004SjbbService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.ResistantAnalysis;
import com.nis.patient.entity.St010Jcbyt;
import com.nis.patient.entity.St011Syjgb;
import com.nis.patient.service.St009SjbbService;
import com.nis.patient.service.St010JcbytService;
import com.nis.patient.service.St011SyjgbService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import java.util.ArrayList;
import java.util.Collection;
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
public class AnalysisResistantServiceImpl implements AnalysisResistantService {
	private static final Logger c = Logger.getLogger(AnalysisResistantServiceImpl.class);
	private int bw = 0;
	private int bx = 0;
	private long ce = 0L;
	private String cf = "";
	private String[] cg = new String[0];
	private List<Xn011Dclymx> ch = new ArrayList();
	private int lastSize = 0;
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private SysLogService aV;
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private Xn004TrnyService ci;
	@Autowired
	private Xn008JlybzService ck;
	@Autowired
	private Xn015TrlyjlService cl;
	@Autowired
	private Xn017TsnyzdService cm;
	@Autowired
	private Xn011DclymxService cn;
	@Autowired
	private St010JcbytService bF;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysJudgeLogService J;
	@Autowired
	private TaskJobService aU;
	@Autowired
	private SysDictService p;
	@Autowired
	private Bk004SjbbService co;

	public void a(Date startDate, Date endDate, String testOrderNo, String id) {
		SysJudgeLog sysJudgeLog = this.J.get(id);
		TaskJob taskJob = this.aU.findByLink(ae.ir.getUrl());
		if (taskJob != null && taskJob.getStatus().intValue() == 50) {
			sysJudgeLog.setStatus("3");
			sysJudgeLog.setEndTime(new Date());
			this.J.update(sysJudgeLog);
		} else {
			Date curr = new Date();
			int total = this.bE.findWaitAnaResisCount();
			sysJudgeLog.setTotalCount(Integer.valueOf(total));
			this.J.update(sysJudgeLog);
			Set trnySet = this.ci.findIdSet();
			List jlybzList = this.ck.findJlybzInfo();
			HashMap jlybzMap = new HashMap();
			Xn008Jlybz tsnyList;
			if (jlybzList.size() > 0) {
				Iterator tsnyMap = jlybzList.iterator();

				label135 : while (true) {
					do {
						if (!tsnyMap.hasNext()) {
							break label135;
						}

						tsnyList = (Xn008Jlybz) tsnyMap.next();
					} while (tsnyList.getItemId() == null);

					HashMap hasMore = new HashMap();
					Iterator index = tsnyList.getXn009List().iterator();

					while (index.hasNext()) {
						Xn009Lybzmx size = (Xn009Lybzmx) index.next();
						String[] resisList = size.getTestresult().split(",");
						HashSet xn011Yin = new HashSet();
						String[] arg21 = resisList;
						int arg20 = resisList.length;

						for (int arg19 = 0; arg19 < arg20; ++arg19) {
							String xn011 = arg21[arg19];
							xn011Yin.add(xn011);
						}

						size.setTestresultSet(xn011Yin);
						hasMore.put(size.getDrugTypeId(), size);
					}

					tsnyList.setXn009Map(hasMore);
					HashMap arg27 = new HashMap();
					Iterator arg33 = tsnyList.getXn013List().iterator();

					while (arg33.hasNext()) {
						Xn013Dbyw arg30 = (Xn013Dbyw) arg33.next();
						arg27.put(arg30.getDrugId(), arg30);
					}

					tsnyList.setXn013Map(arg27);
					String arg31 = tsnyList.getGermId() + "-" + tsnyList.getClassType();
					if (jlybzMap.containsKey(arg31)) {
						((List) jlybzMap.get(arg31)).add(tsnyList);
					} else {
						ArrayList arg34 = new ArrayList();
						arg34.add(tsnyList);
						jlybzMap.put(arg31, arg34);
					}
				}
			}

			jlybzList = null;
			List arg23 = this.cm.findTsnyToAna();
			HashMap arg24 = new HashMap();
			if (arg23.size() > 0) {
				Iterator arg28 = arg23.iterator();

				while (arg28.hasNext()) {
					Xn017Tsnyzd arg25 = (Xn017Tsnyzd) arg28.next();
					arg24.put(arg25.getPathogenId() + "-" + arg25.getDrugId() + "-" + arg25.getTestresult(), arg25);
				}
			}

			tsnyList = null;
			boolean arg26 = true;
			Integer arg29 = Integer.valueOf(500);
			int arg32 = r.d(this.j.findByParamCode(Param.NIS_ANA_PREVENT_INFINITE_LOOP));
			this.cg = this.j.findByParamCode(Param.NIS_DN_ALL_YIN_EXCL).split(",");
			this.cf = "";
			this.ce = this.cn.getMaxOrderno();

			while (arg26) {
				taskJob = this.aU.findByLink(ae.in.getUrl());
				if (taskJob != null && taskJob.getStatus().intValue() == 50) {
					sysJudgeLog.setStatus("3");
					sysJudgeLog.setEndTime(new Date());
					this.J.update(sysJudgeLog);
					arg26 = false;
				}

				--arg32;
				List arg35 = this.bE.findWaitAnaResis(arg29, startDate, endDate, testOrderNo, curr);
				if (arg35 == null || arg35.size() < arg29.intValue() || arg32 <= 0) {
					arg26 = false;
				}

				if (arg35.size() > 0) {
					try {
						this.a(arg35, trnySet, jlybzMap, arg24, id);
						this.bw += arg35.size();
						sysJudgeLog.setSuccessCount(Integer.valueOf(this.bw));
						sysJudgeLog.setEndTime(new Date());
						this.J.update(sysJudgeLog);
					} catch (Exception arg22) {
						++this.bx;
						sysJudgeLog.setFailCount(Integer.valueOf(this.bx));
						sysJudgeLog.setStatus("2");
						this.J.update(sysJudgeLog);
						arg22.printStackTrace();
						c.error("多重耐药阶段分析执行异常!", arg22);
						this.aV.a(ah.iG, "多重耐药分析异常{AnalysisResistantServiceImpl.analysisResistant()}", id,
								arg22.getMessage());
					}
				}
			}

			if (StringUtils.isNotBlank(this.cf) && this.lastSize == this.ch.size()) {
				Xn011Dclymx arg36 = null;
				Iterator arg38 = this.ch.iterator();

				while (arg38.hasNext()) {
					Xn011Dclymx arg37 = (Xn011Dclymx) arg38.next();
					if (!this.q(arg37.getPathoName())) {
						arg36 = arg37;
						break;
					}
				}

				if (arg36 != null) {
					this.a(arg36);
				} else if (this.ch.size() > 0) {
					this.a((Xn011Dclymx) this.ch.get(0));
				}
			}

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

	private void a(List<ResistantAnalysis> resisList, Set<String> trnySet, Map<String, List<Xn008Jlybz>> jlybzMap,
			Map<String, Xn017Tsnyzd> tsnyMap, String id) {
		Date curr = new Date();
		Object st011List = null;
		ArrayList xn008List = new ArrayList();
		Object xn009Lybzmx = null;
		String specDescribe = "";
		String esbl = "";
		Object xn017Tsnyzd = null;
		String validationStr = "";
		HashMap resultMap = new HashMap();
		Object xn011Dclymx = null;
		Iterator arg16 = resisList.iterator();

		while (arg16.hasNext()) {
			ResistantAnalysis resistant = (ResistantAnalysis) arg16.next();
			St010Jcbyt st010Jcbyt = new St010Jcbyt();
			st010Jcbyt.setId(resistant.getId());
			st010Jcbyt.setCalculateTag(Integer.valueOf(1));
			st010Jcbyt.setCalculateDt(curr);
			xn008List.clear();
			this.a(resistant, (List) st011List, jlybzMap, xn008List, (Xn009Lybzmx) xn009Lybzmx, specDescribe, esbl,
					validationStr, resultMap, trnySet, (Xn011Dclymx) xn011Dclymx, (Xn017Tsnyzd) xn017Tsnyzd, tsnyMap,
					st010Jcbyt, validationStr);
		}

		st011List = null;
	}

	private boolean q(String pathoName) {
		if (StringUtils.isNotBlank(pathoName)) {
			String[] arg4 = this.cg;
			int arg3 = this.cg.length;

			for (int arg2 = 0; arg2 < arg3; ++arg2) {
				String excl = arg4[arg2];
				if (pathoName.indexOf(excl) >= 0) {
					return true;
				}
			}
		}

		return false;
	}

	@Transactional(rollbackFor = {Exception.class})
	private void a(ResistantAnalysis resistant, List<St011Syjgb> st011List, Map<String, List<Xn008Jlybz>> jlybzMap,
			List<Xn008Jlybz> xn008List, Xn009Lybzmx xn009Lybzmx, String specDescribe, String esbl, String validationStr,
			Map<String, Object> resultMap, Set<String> trnySet, Xn011Dclymx xn011Dclymx, Xn017Tsnyzd xn017Tsnyzd,
			Map<String, Xn017Tsnyzd> tsnyMap, St010Jcbyt st010Jcbyt, String id) {
		Integer resProp = Integer.valueOf(0);
		Integer normItemId = null;
		Integer normOrderno = null;
		if ((new Integer(1)).equals(resistant.getIsym())) {
			st011List = this.bH.findWaitAnalysis(resistant.getPathogenSn(), resistant.getTestOrderNo());
			if (st011List.size() > 0) {
				if (jlybzMap.containsKey(resistant.getPathogenId() + "-1")) {
					xn008List.addAll((Collection) jlybzMap.get(resistant.getPathogenId() + "-1"));
				}

				if (jlybzMap.containsKey(resistant.getBactGenusId() + "-2")) {
					xn008List.addAll((Collection) jlybzMap.get(resistant.getBactGenusId() + "-2"));
				}

				if (xn008List.size() > 0) {
					Iterator xn011 = xn008List.iterator();

					while (xn011.hasNext()) {
						Xn008Jlybz e = (Xn008Jlybz) xn011.next();
						Integer resProp1 = this.a(st011List, xn009Lybzmx, trnySet, resistant, xn017Tsnyzd, tsnyMap, e,
								resultMap);
						if (resProp1.intValue() >= resProp.intValue()) {
							resProp = resProp1;
							normItemId = Integer.valueOf(r.d(resultMap.get("normItemId")));
							normOrderno = Integer.valueOf(r.d(resultMap.get("normOrderno")));
							specDescribe = ab.g(resultMap.get("specDescribe"));
							validationStr = ab.g(resultMap.get("validationStr"));
							esbl = ab.g(resultMap.get("esbl"));
						}
					}
				} else {
					resProp = this.a(st011List, xn009Lybzmx, trnySet, resistant, xn017Tsnyzd, tsnyMap,
							(Xn008Jlybz) null, resultMap);
					normItemId = Integer.valueOf(r.d(resultMap.get("normItemId")));
					normOrderno = Integer.valueOf(r.d(resultMap.get("normOrderno")));
					specDescribe = ab.g(resultMap.get("specDescribe"));
					validationStr = ab.g(resultMap.get("validationStr"));
					esbl = ab.g(resultMap.get("esbl"));
				}

				++this.ce;
				xn011Dclymx = this.a(resistant, resProp, normItemId, normOrderno, specDescribe, esbl, validationStr);
			} else {
				if (!(new Integer(0)).equals(resistant.getIsyang())) {
					this.bF.updAnalDt(st010Jcbyt);
					return;
				}

				++this.ce;
				xn011Dclymx = this.a(resistant, (Integer) null, (Integer) null, (Integer) null, (String) null,
						(String) null, (String) null);
			}
		} else {
			++this.ce;
			xn011Dclymx = this.a(resistant, (Integer) null, (Integer) null, (Integer) null, (String) null,
					(String) null, (String) null);
		}

		try {
			if (!resistant.getTestOrderNo().equals(this.cf)) {
				if (StringUtils.isNotBlank(this.cf) && this.lastSize > 0 && this.lastSize == this.ch.size()) {
					Xn011Dclymx arg22 = null;
					Iterator arg24 = this.ch.iterator();

					while (arg24.hasNext()) {
						Xn011Dclymx arg23 = (Xn011Dclymx) arg24.next();
						if (!this.q(arg23.getPathoName())) {
							arg22 = arg23;
							break;
						}
					}

					if (arg22 != null) {
						this.a(arg22);
					} else if (this.ch.size() > 0) {
						this.a((Xn011Dclymx) this.ch.get(0));
					}
				}

				this.cf = resistant.getTestOrderNo();
				this.lastSize = 0;
				this.ch.clear();
			}

			if ((new Integer(1)).equals(resistant.getIsyang())) {
				this.a(xn011Dclymx);
			} else if (StringUtils.isBlank(this.cf) || resistant.getTestOrderNo().equals(this.cf)) {
				this.ch.add(xn011Dclymx);
			}

			this.bF.updAnalFlag(st010Jcbyt);
			++this.lastSize;
		} catch (Exception arg21) {
			arg21.printStackTrace();
			c.error("多耐分析异常：st011-id=" + st010Jcbyt.getId(), arg21);
			this.aV.a(ah.iG, "多耐分析异常{AnalysisResistantServiceImpl.resisAnalysis()}：st011-id=" + st010Jcbyt.getId(), id,
					arg21.getMessage());
		}

	}

	private void a(Xn011Dclymx xn011Dclymx) {
		Xn011Dclymx xn011 = this.cn.a(xn011Dclymx.getZyid(), xn011Dclymx.getTestOrderNo(), xn011Dclymx.getItemType(),
				xn011Dclymx.getItemCode(), xn011Dclymx.getPathoCode());
		if (xn011 == null) {
			Integer infectTypeId = this.co.getInfectTypeByTestNo(xn011Dclymx.getTestOrderNo());
			String infectTypeName = y.h(infectTypeId).getShortName();
			xn011Dclymx.setInfectTypeId(infectTypeId);
			xn011Dclymx.setInfectTypeName(infectTypeName);
			this.cn.save(xn011Dclymx);
		} else {
			xn011Dclymx.setId(xn011.getId());
			this.cn.updateMdrInfo(xn011Dclymx);
		}

	}

	private Integer a(List<St011Syjgb> st011List, Xn009Lybzmx xn009Lybzmx, Set<String> trnySet,
			ResistantAnalysis resistant, Xn017Tsnyzd xn017Tsnyzd, Map<String, Xn017Tsnyzd> tsnyMap,
			Xn008Jlybz xn008Jlybz, Map<String, Object> resultMap) {
		int nyNum = 0;
		int totalNum = 0;
		Integer resProp = Integer.valueOf(0);
		Integer normItemId = null;
		Integer normOrderno = null;
		String validationStr = "";
		String specDescribe = "";
		String esbl = "";
		HashSet drugTypeSetNy = new HashSet();
		HashSet drugTypeSetTotal = new HashSet();
		Iterator arg19 = st011List.iterator();

		while (arg19.hasNext()) {
			St011Syjgb st011Syjgb = (St011Syjgb) arg19.next();
			if (StringUtils.isNotBlank(st011Syjgb.getDrugId())) {
				validationStr = validationStr + st011Syjgb.getDrugId() + "|" + ab.aP(st011Syjgb.getYaominResult())
						+ ";";
			}

			xn009Lybzmx = null;
			if (trnySet.contains(resistant.getPathogenId() + "-" + st011Syjgb.getDrugId())) {
				this.a(resistant, st011Syjgb);
			} else {
				if (StringUtils.isBlank(specDescribe) && tsnyMap.containsKey(resistant.getPathogenId() + "-"
						+ st011Syjgb.getDrugId() + "-" + st011Syjgb.getYaominResult())) {
					xn017Tsnyzd = (Xn017Tsnyzd) tsnyMap.get(resistant.getPathogenId() + "-" + st011Syjgb.getDrugId()
							+ "-" + st011Syjgb.getYaominResult());
					if (xn017Tsnyzd.getSpecDescribe().indexOf("ESBL") < 0) {
						specDescribe = xn017Tsnyzd.getSpecDescribe();
					}
				}

				if (StringUtils.isBlank(esbl) && tsnyMap.containsKey(resistant.getPathogenId() + "-"
						+ st011Syjgb.getDrugId() + "-" + st011Syjgb.getYaominResult())) {
					xn017Tsnyzd = (Xn017Tsnyzd) tsnyMap.get(resistant.getPathogenId() + "-" + st011Syjgb.getDrugId()
							+ "-" + st011Syjgb.getYaominResult());
					if (xn017Tsnyzd.getSpecDescribe().indexOf("ESBL") >= 0) {
						esbl = "+";
					}
				}

				if (StringUtils.isBlank(esbl) && StringUtils.isNotBlank(st011Syjgb.getAntiName())
						&& st011Syjgb.getAntiName().indexOf("ESBL") >= 0) {
					if ("+".equals(st011Syjgb.getYaominResult())) {
						resProp = Integer.valueOf(1);
					}

					esbl = st011Syjgb.getYaominResult();
				}

				SysDict arg26;
				String arg27;
				String arg28;
				SysDict arg29;
				if (xn008Jlybz != null) {
					if (xn008Jlybz.getXn009Map().size() > 0) {
						if (xn008Jlybz.getXn009Map().containsKey(st011Syjgb.getDrugId())) {
							xn009Lybzmx = (Xn009Lybzmx) xn008Jlybz.getXn009Map().get(st011Syjgb.getDrugId());
						}

						if (xn009Lybzmx == null && xn008Jlybz.getXn013Map().containsKey(st011Syjgb.getDrugId())) {
							xn009Lybzmx = (Xn009Lybzmx) xn008Jlybz.getXn009Map().get(
									((Xn013Dbyw) xn008Jlybz.getXn013Map().get(st011Syjgb.getDrugId())).getDrugTypeId());
						}

						if (xn009Lybzmx == null) {
							continue;
						}

						Iterator nydict = xn009Lybzmx.getTestresultSet().iterator();

						while (nydict.hasNext()) {
							String dict = (String) nydict.next();
							SysDict zj = this.p.j("ymjg", dict, (String) null);
							String dict2 = zj.getExtParam1();
							String zj1 = this.j.findByParamCode(Param.NIS_SYS_DN_ZJ);
							if ("1".equals(zj1)) {
								SysDict dict21 = this.p.j("ymjg", "I", (String) null);
								dict2 = dict2 + "," + dict21.getExtParam1();
							}

							if (!drugTypeSetNy.contains(xn009Lybzmx.getDrugTypeId())
									&& StringUtils.isNotBlank(st011Syjgb.getYaominResult())
									&& dict2.indexOf(st011Syjgb.getYaominResult()) > -1) {
								++nyNum;
								drugTypeSetNy.add(xn009Lybzmx.getDrugTypeId());
							}
						}
					} else {
						arg26 = this.p.j("ymjg", "R", (String) null);
						arg27 = arg26.getExtParam1();
						arg28 = this.j.findByParamCode(Param.NIS_SYS_DN_ZJ);
						if ("1".equals(arg28)) {
							arg29 = this.p.j("ymjg", "I", (String) null);
							arg27 = arg27 + "," + arg29.getExtParam1();
						}

						if ((StringUtils.isBlank(st011Syjgb.getDrugTypeId())
								|| !drugTypeSetNy.contains(st011Syjgb.getDrugTypeId()))
								&& StringUtils.isNotBlank(st011Syjgb.getYaominResult())
								&& arg27.indexOf(st011Syjgb.getYaominResult()) > -1) {
							++nyNum;
							if (StringUtils.isNotBlank(st011Syjgb.getDrugTypeId())) {
								drugTypeSetNy.add(st011Syjgb.getDrugTypeId());
							}
						}
					}
				} else {
					arg26 = this.p.j("ymjg", "R", (String) null);
					arg27 = arg26.getExtParam1();
					arg28 = this.j.findByParamCode(Param.NIS_SYS_DN_ZJ);
					if ("1".equals(arg28)) {
						arg29 = this.p.j("ymjg", "I", (String) null);
						arg27 = arg27 + "," + arg29.getExtParam1();
					}

					if ((StringUtils.isBlank(st011Syjgb.getDrugTypeId())
							|| !drugTypeSetNy.contains(st011Syjgb.getDrugTypeId()))
							&& StringUtils.isNotBlank(st011Syjgb.getYaominResult())
							&& arg27.indexOf(st011Syjgb.getYaominResult()) > -1) {
						++nyNum;
						if (StringUtils.isNotBlank(st011Syjgb.getDrugTypeId())) {
							drugTypeSetNy.add(st011Syjgb.getDrugTypeId());
						}
					}
				}

				if (xn009Lybzmx != null && !drugTypeSetTotal.contains(xn009Lybzmx.getDrugTypeId())) {
					++totalNum;
					drugTypeSetTotal.add(xn009Lybzmx.getDrugTypeId());
				} else if (StringUtils.isBlank(st011Syjgb.getDrugTypeId())
						|| !drugTypeSetTotal.contains(st011Syjgb.getDrugTypeId())) {
					++totalNum;
					if (StringUtils.isNotBlank(st011Syjgb.getDrugTypeId())) {
						drugTypeSetTotal.add(st011Syjgb.getDrugTypeId());
					}
				}
			}
		}

		if (StringUtils.isNotBlank(esbl)) {
			validationStr = "|" + esbl + ";" + validationStr;
		}

		if (xn008Jlybz != null) {
			if (nyNum >= xn008Jlybz.getPdr().intValue() && xn008Jlybz.getPdr().intValue() != 0) {
				resProp = Integer.valueOf(3);
			} else if (nyNum >= xn008Jlybz.getXdr().intValue() && xn008Jlybz.getXdr().intValue() != 0) {
				resProp = Integer.valueOf(2);
			} else if (nyNum >= xn008Jlybz.getMdr().intValue() && xn008Jlybz.getMdr().intValue() != 0) {
				resProp = Integer.valueOf(1);
			}

			normItemId = xn008Jlybz.getItemId();
			normOrderno = xn008Jlybz.getOrderno();
		} else if (nyNum == totalNum && totalNum != 0) {
			resProp = Integer.valueOf(3);
		} else if (nyNum >= 7) {
			resProp = Integer.valueOf(2);
		} else if (nyNum >= 3) {
			resProp = Integer.valueOf(1);
		}

		resultMap.put("normItemId", normItemId);
		resultMap.put("normOrderno", normOrderno);
		resultMap.put("validationStr", validationStr);
		resultMap.put("specDescribe", specDescribe);
		resultMap.put("esbl", esbl);
		return resProp;
	}

	private Xn011Dclymx a(ResistantAnalysis resistant, Integer resProp, Integer normItemId, Integer normOrderno,
			String specDescribes, String esbl, String validationStr) {
		Xn011Dclymx xn011Dclymx = new Xn011Dclymx();
		xn011Dclymx.setId(z.a(bg.ne));
		xn011Dclymx.setJcbytId(resistant.getId());
		xn011Dclymx.setZyid(resistant.getZyid());
		xn011Dclymx.setDt(resistant.getTestDate());
		xn011Dclymx.setSurveyDeptId(resistant.getSurveyDeptId());
		xn011Dclymx.setSurveyDeptName(resistant.getSurveyDeptName());
		xn011Dclymx.setPatientId(resistant.getPatientId());
		xn011Dclymx.setPatientName(resistant.getPatientName());
		xn011Dclymx.setVisitId(resistant.getVisitId());
		xn011Dclymx.setSex(resistant.getSex());
		xn011Dclymx.setAge(resistant.getAge());
		xn011Dclymx.setAgeUnit(resistant.getAgeUnit());
		xn011Dclymx.setTestOrderNo(resistant.getTestOrderNo());
		xn011Dclymx.setItemType(Integer.valueOf(1));
		xn011Dclymx.setItemTypeName(resistant.getItemTypeName());
		xn011Dclymx.setItemCode(resistant.getItemCode());
		xn011Dclymx.setItemName(resistant.getItemName());
		xn011Dclymx.setSubmiAt(resistant.getSubmiAt());
		xn011Dclymx.setDeptId(resistant.getDeptId());
		xn011Dclymx.setDeptName(resistant.getDeptName());
		xn011Dclymx.setPathoCode(resistant.getPathoCode());
		xn011Dclymx.setPathoName(resistant.getPathoName());
		xn011Dclymx.setPathogenSn(resistant.getPathogenSn());
		xn011Dclymx.setIsym(resistant.getIsym());
		if (!"1".equals(resistant.getIsFungus())) {
			xn011Dclymx.setResProp(resProp);
		}

		xn011Dclymx.setNormItemId(normItemId);
		xn011Dclymx.setNormOrderno(normOrderno);
		xn011Dclymx.setSpecDescribes(specDescribes);
		xn011Dclymx.setEsbl(esbl);
		if (StringUtils.isNotBlank(validationStr)) {
			xn011Dclymx.setValidationStr(validationStr.substring(0, validationStr.length() - 1));
		}

		xn011Dclymx.setOrderno(Long.valueOf(this.ce));
		return xn011Dclymx;
	}

	private void a(ResistantAnalysis resistant, St011Syjgb st011Syjgb) {
		Xn015Trlyjl xn015 = this.cl.get(resistant.getTestOrderNo(), resistant.getPathoCode(), st011Syjgb.getDrugId());
		if (xn015 == null) {
			Xn015Trlyjl xn015Trlyjl = new Xn015Trlyjl();
			xn015Trlyjl.setTestOrderNo(resistant.getTestOrderNo());
			xn015Trlyjl.setPathoCode(resistant.getPathoCode());
			xn015Trlyjl.setPathoName(resistant.getPathoName());
			xn015Trlyjl.setPathogenSn(resistant.getPathogenSn());
			xn015Trlyjl.setAntiCode(st011Syjgb.getDrugId());
			xn015Trlyjl.setAntiName(st011Syjgb.getDrugName());
			xn015Trlyjl.setRecordDt(new Date());
			this.cl.save(xn015Trlyjl);
		}

	}
}