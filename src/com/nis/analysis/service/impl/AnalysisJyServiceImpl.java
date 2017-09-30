package com.nis.analysis.service.impl;

import com.nis.analysis.model.a;
import com.nis.analysis.service.AnalysisJyService;
import com.nis.cdc.entity.CtgSys005LisruleMaster;
import com.nis.cdc.entity.CtgSys006LisruleDetail;
import com.nis.cdc.entity.CtgSys007Dictcontagion;
import com.nis.cdc.entity.CtgSys009Yj;
import com.nis.cdc.service.CtgSys005LisruleMasterService;
import com.nis.cdc.service.CtgSys006LisruleDetailService;
import com.nis.cdc.service.CtgSys007DictcontagionService;
import com.nis.cdc.service.CtgSys009YjService;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St009Sjbb;
import com.nis.patient.entity.St010Jcbyt;
import com.nis.patient.entity.St011Syjgb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St009SjbbService;
import com.nis.patient.service.St010JcbytService;
import com.nis.patient.service.St011SyjgbService;
import com.nis.patient.service.St020ClinicPatientsService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AnalysisJyServiceImpl implements AnalysisJyService {
	private static final Logger c = Logger.getLogger(AnalysisJyServiceImpl.class);
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private CtgSys005LisruleMasterService bJ;
	@Autowired
	private CtgSys006LisruleDetailService bK;
	@Autowired
	private St010JcbytService bF;
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private CtgSys009YjService bi;
	@Autowired
	private CtgSys007DictcontagionService bL;
	@Autowired
	private TaskJobService aU;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private St003CryxxbService bg;

	@Transactional(rollbackFor = {Exception.class})
	public void i(String zyid, String bcid) {
	}

	public a e(boolean errExit) {
		a ar = new a();
		ArrayList errList = new ArrayList();
		ArrayList yjCountList = new ArrayList();
		new ArrayList();
		int findcs = 0;
		int sucnum = 0;
		byte errnum = 0;
		int yjCount = 0;
		byte rows = 5;

		try {
			boolean e = true;
			TaskJob taskJob = this.aU.findByLink("analysis/jy");
			if (taskJob == null || taskJob.getStatus().intValue() != 0) {
				e = false;
				ar.setSuccess(true);
				return ar;
			}

			e = true;
			List lisrulemaster = this.bJ.getAll();

			label214 : while (e) {
				Thread.sleep(1000L);
				++findcs;
				taskJob = this.aU.findByLink("analysis/jy");
				if (taskJob != null && taskJob.getStatus().intValue() == 0) {
					e = true;
					List unAnalysisRecord = this.bE.getUnAnalysisRecord(rows);
					if (unAnalysisRecord != null && unAnalysisRecord.size() > 0) {
						Iterator arg15 = unAnalysisRecord.iterator();

						while (true) {
							if (!arg15.hasNext()) {
								continue label214;
							}

							St009Sjbb ss = (St009Sjbb) arg15.next();
							boolean isSuccess = true;
							String zymzid = ss.getMzzyid();
							String patientType = ss.getPatientType();
							String testorderno = "";
							System.err.println("正在遍历st009表数据，当前病人ZYID/MZID：" + zymzid + "； " + patientType + "病人");
							c.info("正在遍历st009表数据，当前病人ZYID/MZID：" + zymzid + "； " + patientType + "病人");
							System.err.println("获取ListruleMaster表数据");
							Iterator arg21 = lisrulemaster.iterator();

							label202 : while (true) {
								label200 : while (true) {
									if (!arg21.hasNext()) {
										break label202;
									}

									CtgSys005LisruleMaster cslm = (CtgSys005LisruleMaster) arg21.next();
									System.err.println("正在遍历lisrulemaster，当前：" + cslm.getOrderno() + "，isallitems："
											+ cslm.getIsallitems() + "，tablename：" + cslm.getTablename() + "，权重："
											+ cslm.getYjWeight() + "，yj_result：" + cslm.getYjresult());
									c.info("正在遍历lisrulemaster，当前：" + cslm.getOrderno() + "，isallitems："
											+ cslm.getIsallitems() + "，tablename：" + cslm.getTablename() + "，权重："
											+ cslm.getYjWeight() + "，yj_result：" + cslm.getYjresult());
									Long orderno = cslm.getOrderno();
									String tablename = cslm.getTablename();
									long isallitems = cslm.getIsallitems().longValue();
									String yjresult = cslm.getYjresult();
									String filterSql = "";
									if (isallitems != 1L) {
										if (ab.aM(cslm.getItemnamefield())) {
											System.err.println("ListruleMaster表配置错误，当前orderno：" + orderno
													+ "；当isallitems字段为空时，Itemnamefield不能为空");
											c.error("ListruleMaster表配置错误，当前orderno：" + orderno
													+ "；当isallitems字段为空时，Itemnamefield不能为空");
											continue;
										}

										if (ab.aY(cslm.getItemnamevalue())) {
											filterSql = filterSql + " ( " + cslm.getItemnamefield() + " "
													+ cslm.getItemnameoper() + " " + cslm.getItemnamevalue() + " ) ";
										} else if ("like".equals(cslm.getItemnameoper().replaceAll(" ", ""))) {
											filterSql = filterSql + " ( " + cslm.getItemnamefield() + " "
													+ cslm.getItemnameoper() + "  \'%" + cslm.getItemnamevalue()
													+ "%\' ) ";
										} else {
											filterSql = filterSql + " ( " + cslm.getItemnamefield() + " "
													+ cslm.getItemnameoper() + " \'" + cslm.getItemnamevalue()
													+ "\'  ) ";
										}

										System.err.println("正在用lisrulemaster中的条件进行过滤，filterSql：" + filterSql);
										c.info("正在用lisrulemaster中的条件进行过滤，filterSql：" + filterSql);

										try {
											int lisruleDetail = this.bE.masterCdtsNew(ss.getPatientType(),
													ss.getTestOrderNo(), filterSql);
											if (lisruleDetail <= 0) {
												System.err.println("St009不符合条件，遍历下一个病人，当前：mzzyid" + zymzid + "（"
														+ patientType + "）");
												continue;
											}
										} catch (Exception arg38) {
											if (errExit) {
												throw arg38;
											}

											System.err.println("用lisrulemaster条件过滤时出错！msg：" + arg38.getMessage()
													+ "，错误项：mzzyid=" + zymzid + "，lisrulemaster：" + cslm.getOrderno()
													+ "，过滤sql：" + filterSql);
											isSuccess = false;
											continue;
										}
									}

									if (!isSuccess) {
										break label202;
									}

									System.err.println("根据lisrulemaster表获取lisruleDetail，当前 orderno：" + orderno);
									c.info("根据lisrulemaster表获取lisruleDetail，当前 orderno：" + orderno);
									List arg42 = this.bK.get(orderno);
									long weight = 0L;
									Iterator arg32 = arg42.iterator();

									while (true) {
										String yjcontent;
										do {
											while (true) {
												if (!arg32.hasNext()) {
													continue label200;
												}

												CtgSys006LisruleDetail csld = (CtgSys006LisruleDetail) arg32.next();
												yjcontent = "";
												CtgSys006LisruleDetail ldSql;
												if (1L == csld.getIsallresult().longValue()) {
													for (Iterator e3 = arg42.iterator(); e3
															.hasNext(); yjcontent = yjcontent
																	+ ldSql.getResultnamevalue() + ldSql.getMatchvalue()
																	+ ";") {
														ldSql = (CtgSys006LisruleDetail) e3.next();
													}
												} else {
													yjcontent = csld.getResultnamevalue() + csld.getMatchvalue();
												}

												String arg43 = this.a(cslm, csld);
												System.err.println("遍历lisruleDetail并组装条件，当前suborderno："
														+ csld.getSuborderno() + "，lisruleDetail条件：" + arg43);
												c.info("遍历lisruleDetail并组装条件，当前suborderno：" + csld.getSuborderno()
														+ "，lisruleDetail条件：" + arg43);

												try {
													Iterator arg37;
													List arg44;
													if ("st011_syjgb".equals(tablename)) {
														System.err.println("查询st011表......");
														new ArrayList();
														if (1L == csld.getIsallresult().longValue()) {
															arg44 = this.bH.t(zymzid, patientType, arg43);
														} else {
															arg44 = this.bH.queryForYJ(ss.getTestOrderNo(), arg43);
														}

														if (arg44 != null && !arg44.isEmpty()) {
															if (arg44.size() <= 0) {
																break;
															}

															St011Syjgb arg45;
															if (1L == csld.getIsallresult().longValue()) {
																for (arg37 = arg44.iterator(); arg37
																		.hasNext(); testorderno = testorderno
																				+ arg45.getTestOrderNo() + ";") {
																	arg45 = (St011Syjgb) arg37.next();
																}
															} else {
																testorderno = ((St011Syjgb) arg44.get(0))
																		.getTestOrderNo();
															}

															System.err.println("有数据符合当前lisruleDetail条件，" + patientType
																	+ "病人，mzzyid：" + zymzid + " ，当前权重："
																	+ csld.getWeightvalue());
															c.info("有数据符合当前lisruleDetail条件，" + patientType
																	+ "病人，mzzyid：" + zymzid + " ，当前权重："
																	+ csld.getWeightvalue());
															weight += csld.getWeightvalue().longValue();
															break;
														}

														System.err.println("未找到数据符合当前lisruleDetail条件");
														break;
													}

													if ("st010_jcbyt".equals(tablename)) {
														System.err.println("查询st010表......");
														new ArrayList();
														if (1L == csld.getIsallresult().longValue()) {
															arg44 = this.bF.t(zymzid, patientType, arg43);
														} else {
															arg44 = this.bF.queryForYJ(ss.getTestOrderNo(), arg43);
														}

														if (arg44 == null || arg44.isEmpty() || arg44.size() <= 0) {
															break;
														}

														St010Jcbyt sy;
														if (1L == csld.getIsallresult().longValue()) {
															for (arg37 = arg44.iterator(); arg37
																	.hasNext(); testorderno = testorderno
																			+ sy.getTestOrderNo() + ";") {
																sy = (St010Jcbyt) arg37.next();
															}
														} else {
															testorderno = ((St010Jcbyt) arg44.get(0)).getTestOrderNo();
														}

														System.err.println(
																"有数据符合当前lisruleDetail条件，数据testorderno：" + testorderno
																		+ "，lisruleDetail权重：" + csld.getWeightvalue());
														weight += csld.getWeightvalue().longValue();
														break;
													}

													System.err.println("tablename不符合要求，lisrulemaster："
															+ cslm.getOrderno() + "，跳出（lisrulemaster）循环");
													c.info("tablename不符合要求，lisrulemaster：" + cslm.getOrderno()
															+ "，跳出（lisrulemaster）循环");
												} catch (Exception arg39) {
													if (errExit) {
														throw arg39;
													}

													System.err.println("lisruleDetail拼接条件出错！msg" + arg39.getMessage()
															+ "，错误项：mzzyid=" + zymzid + "，lisrulemaster："
															+ cslm.getOrderno() + "，lisruleDetail："
															+ csld.getSuborderno());
													c.error("lisruleDetail拼接条件出错！msg" + arg39.getMessage()
															+ "，错误项：mzzyid=" + zymzid + "，lisrulemaster："
															+ cslm.getOrderno() + "，lisruleDetail："
															+ csld.getSuborderno(), arg39);
												}
											}

											if (!isSuccess) {
												continue label200;
											}
										} while (weight < cslm.getYjWeight().longValue());

										++yjCount;
										System.err.println(
												"yj数据插入......     mzzyid：" + zymzid + "的" + patientType + "病人，Orderno："
														+ cslm.getOrderno() + "，Yjresult：" + cslm.getYjresult());
										yjCountList.add(
												"yj数据插入......     mzzyid：" + zymzid + "的" + patientType + "病人，Orderno："
														+ cslm.getOrderno() + "，Yjresult：" + cslm.getYjresult());
										weight = 0L;

										try {
											this.a(ss, testorderno, yjresult, yjcontent);
										} catch (Exception arg40) {
											if (errExit) {
												throw arg40;
											}

											System.err.println("已找到符合条件的数据，但存入yj表时出错！ st009表 mzzyid：" + zymzid + "（"
													+ patientType + "），testorderno：" + testorderno
													+ "，满足条件【lisrulemaster】orderno：" + cslm.getOrderno());
											c.error("analysisJy - 已找到符合条件的数据，但存入yj表时出错！ st009表 mzzyid：" + zymzid + "（"
													+ patientType + "），testorderno：" + testorderno
													+ "，满足条件【lisrulemaster】orderno：" + cslm.getOrderno() + "。错误信息："
													+ arg40.getMessage(), arg40);
										}
									}
								}
							}

							if (isSuccess) {
								++sucnum;
								System.err.println("更新st009 预警标识，通过testOrderNo更新相关的记录");
								ss.setCdcanaldt(new Date());
								ss.setCdcanalflag(Integer.valueOf(1));
								this.bE.updateFlagByTestOrderNo(ss);
							}
						}
					}

					e = false;
					break;
				}

				e = false;
				break;
			}

			ar.setSuccess(true);
		} catch (Exception arg41) {
			c.error("analysisJy", arg41);
			ar.setSuccess(false);
		}

		ar.setErrMsgList(errList);
		ar.setSucTotals(sucnum);
		ar.setErrTotals(errnum);
		ar.setInfo("执行次数：" + findcs + "，成功次数：" + sucnum + "，错误次数：" + errnum + "，预警数：" + yjCount);
		ar.setTotals(errnum + sucnum);
		System.err.println("分析结束：成功数有：" + yjCountList.size());
		return ar;
	}

	public String a(CtgSys005LisruleMaster cslm, CtgSys006LisruleDetail csld) {
		String cdts = "";
		if (csld != null && csld.getOrderno() != null) {
			String singleCdt = "";
			singleCdt = "  ";
			if (ab.aY(csld.getResultnamevalue())) {
				singleCdt = singleCdt + " ( " + csld.getResultnamefield() + " " + csld.getResultnameoper() + " "
						+ csld.getResultnamevalue() + " ";
			} else if ("like".equals(csld.getResultnameoper().replaceAll(" ", ""))) {
				singleCdt = singleCdt + " ( " + csld.getResultnamefield() + " " + csld.getResultnameoper() + " \'%"
						+ csld.getResultnamevalue() + "%\' ";
			} else {
				singleCdt = singleCdt + " ( " + csld.getResultnamefield() + " " + csld.getResultnameoper() + " \'"
						+ csld.getResultnamevalue() + "\' ";
			}

			if (ab.aY(csld.getMatchvalue())) {
				singleCdt = singleCdt + " and " + csld.getMatchvaluefield() + " " + csld.getMatchvalueoper() + " "
						+ csld.getMatchvalue() + " ) ";
			} else if ("like".equals(csld.getMatchvalueoper().replaceAll(" ", ""))) {
				singleCdt = singleCdt + " and " + csld.getMatchvaluefield() + " " + csld.getMatchvalueoper() + " \'%"
						+ csld.getMatchvalue() + "%\' ) ";
			} else {
				singleCdt = singleCdt + " and " + csld.getMatchvaluefield() + " " + csld.getMatchvalueoper() + " \'"
						+ csld.getMatchvalue() + "\' ) ";
			}

			cdts = singleCdt;
		}

		return cdts;
	}

	@Transactional
	private void a(St009Sjbb ss, String testorderno, String yjCode, String yjContent) {
		CtgSys009Yj yj = new CtgSys009Yj();
		if ("MZ".equals(ss.getPatientType())) {
			St020ClinicPatients exit = this.bh.get(ss.getMzzyid());
			if (exit != null) {
				yj.setPatientId(exit.getPatientId());
				yj.setPatientName(exit.getPatientName());
			}
		} else {
			St003Cryxxb exit1 = this.bg.get(ss.getMzzyid());
			if (exit1 != null) {
				yj.setPatientId(exit1.getPatientId());
				yj.setPatientName(exit1.getPatientName());
			}
		}

		yj.setMasterid(af.getUUID32());
		yj.setPatientType(ss.getPatientType());
		yj.setMzzyid(ss.getMzzyid());
		yj.setYjcode(yjCode);
		yj.setYjsource("检验");
		yj.setYjdt(new Date());
		yj.setYjcontent(yjContent);
		yj.setSourceId(testorderno);
		List exit2 = this.bi.isExit(yj);
		if (exit2 != null && !exit2.isEmpty()) {
			CtgSys009Yj ctgSys007Dictcontagion1 = (CtgSys009Yj) exit2.get(0);
			ctgSys007Dictcontagion1.setYjdt(new Date());
			ctgSys007Dictcontagion1.setYjcontent(yjContent);
			this.bi.update(ctgSys007Dictcontagion1);
		} else {
			CtgSys007Dictcontagion ctgSys007Dictcontagion = this.bL.get(yjCode);
			if (ctgSys007Dictcontagion != null) {
				yj.setYjname(ctgSys007Dictcontagion.getDiseasename());
			}

			this.bi.save(yj);
		}

	}
}