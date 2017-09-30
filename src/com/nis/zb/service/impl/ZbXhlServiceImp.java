package com.nis.zb.service.impl;

import com.nis.comm.entity.Result;
import com.nis.comm.utils.ah;
import com.nis.zb.dao.ZbXhlDao;
import com.nis.zb.service.ZbXhlService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZbXhlServiceImp implements ZbXhlService {
	private static final Logger c = Logger.getLogger(ZbXhlServiceImp.class);
	@Autowired
	private ZbXhlDao ym;

	public List<Map<String, Object>> findDicOffice() {
		return this.ym.findDicOffice();
	}

	public List<Map<String, Object>> findPatientMain(Date startDate, Date endDate) {
		return this.ym.findPatientMain(startDate, endDate);
	}

	public List<Map<String, Object>> findInfectInfo(Date startDate, Date endDate) {
		return this.ym.findInfectInfo(startDate, endDate);
	}

	public List<Map<String, Object>> findPathoInfo(Date startDate, Date endDate) {
		return this.ym.findPathoInfo(startDate, endDate);
	}

	public List<Map<String, Object>> findAntibInfo(Date startDate, Date endDate) {
		return this.ym.findAntibInfo(startDate, endDate);
	}

	public Result<String[]> g(Date startDate, Date endDate) {
		Result result = new Result();
		String[] datas = new String[5];
		String unMatch = this.ym.findStandDeptUnMatch(startDate, endDate);
		if (StringUtils.isNotBlank(unMatch)) {
			result.setResult("error");
			result.setMsg("xl001_brxx病人信息的科室：" + unMatch + "没有匹配标准科室");
			return result;
		} else {
			unMatch = this.ym.findXl2BridUnMatch(startDate, endDate);
			if (StringUtils.isNotBlank(unMatch)) {
				result.setResult("error");
				result.setMsg("xl002_grxx感染信息关联的brid：" + unMatch + "在xl001里面不存在");
				return result;
			} else {
				unMatch = this.ym.findXl3BridUnMatch(startDate, endDate);
				if (StringUtils.isNotBlank(unMatch)) {
					result.setResult("error");
					result.setMsg("xl003_byt病原体关联的brid：" + unMatch + "在xl001里面不存在");
					return result;
				} else {
					unMatch = this.ym.findXl3GridUnMatch(startDate, endDate);
					if (StringUtils.isNotBlank(unMatch)) {
						result.setResult("error");
						result.setMsg("xl003_byt病原体关联的grid：" + unMatch + "在xl002里面不存在");
						return result;
					} else {
						unMatch = this.ym.findXl4BridUnMatch(startDate, endDate);
						if (StringUtils.isNotBlank(unMatch)) {
							result.setResult("error");
							result.setMsg("xl004_kjyw抗菌药物关联的brid：" + unMatch + "在xl001里面不存在");
							return result;
						} else {
							unMatch = this.ym.findXl4GridUnMatch(startDate, endDate);
							if (StringUtils.isNotBlank(unMatch)) {
								result.setResult("error");
								result.setMsg("xl004_kjyw抗菌药物关联的grid：" + unMatch + "在xl002里面不存在");
								return result;
							} else {
								unMatch = this.ym.findXl4BytidUnMatch(startDate, endDate);
								if (StringUtils.isNotBlank(unMatch)) {
									result.setResult("error");
									result.setMsg("xl004_kjyw抗菌药物关联的bytid：" + unMatch + "在xl003里面不存在");
									return result;
								} else {
									List dicOffice = this.ym.findDicOffice();
									Iterator infectInfo = dicOffice.iterator();

									while (infectInfo.hasNext()) {
										Map patientMain = (Map) infectInfo.next();
										if (patientMain.get("OFFICE") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,zg002_byks的科室名称不能为空");
											return result;
										}

										if (patientMain.get("STANDOFFICEID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,zg002_byks未匹配标准科室");
											return result;
										}
									}

									List patientMain1 = this.ym.findPatientMain(startDate, endDate);
									Iterator pathoInfo = patientMain1.iterator();

									while (pathoInfo.hasNext()) {
										Map infectInfo1 = (Map) pathoInfo.next();
										if (infectInfo1.get("PATIENTID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的患者ID不能为空");
											return result;
										}

										if (infectInfo1.get("PATIENTNAME") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的患者姓名不能为空");
											return result;
										}

										if (infectInfo1.get("OFFICE") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的科室不能为空");
											return result;
										}

										if (infectInfo1.get("STANDOFFICEID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的未匹配标准科室");
											return result;
										}

										if (infectInfo1.get("BEDNO") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的床号不能为空");
											return result;
										}

										if (infectInfo1.get("CASEID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的患者ID不能为空");
											return result;
										}

										if (infectInfo1.get("PATSEX") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的患者性别不能为空");
											return result;
										}

										if (infectInfo1.get("IFINFECT") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的is_infect不能为空");
											return result;
										}

										if (infectInfo1.get("INPUTDATE") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的最后更新时间不能为空");
											return result;
										}

										if (infectInfo1.get("VOTENAME") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的登记人不能为空");
											return result;
										}

										if (infectInfo1.get("VOTEDATE") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl001_brxx的登记时间不能为空");
											return result;
										}
									}

									List infectInfo2 = this.ym.findInfectInfo(startDate, endDate);
									Iterator antibInfo = infectInfo2.iterator();

									while (antibInfo.hasNext()) {
										Map pathoInfo1 = (Map) antibInfo.next();
										if (pathoInfo1.get("BRID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl002_grxx的BRID不能为空");
											return result;
										}

										if (pathoInfo1.get("INPUTDATE") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl002_grxx的最后更新时间不能为空");
											return result;
										}
									}

									List pathoInfo2 = this.ym.findPathoInfo(startDate, endDate);
									Iterator e = pathoInfo2.iterator();

									while (e.hasNext()) {
										Map antibInfo1 = (Map) e.next();
										if (antibInfo1.get("BRID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl003_byt的BRID不能为空");
											return result;
										}

										if (antibInfo1.get("GRID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl003_byt的感染ID不能为空");
											return result;
										}

										if (antibInfo1.get("INPUTDATE") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl003_byt的最后更新时间不能为空");
											return result;
										}
									}

									List antibInfo2 = this.ym.findAntibInfo(startDate, endDate);
									Iterator arg11 = antibInfo2.iterator();

									while (arg11.hasNext()) {
										Map e1 = (Map) arg11.next();
										if (e1.get("BRID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl004_kjyw的BRID不能为空");
											return result;
										}

										if (e1.get("GRID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl004_kjyw的感染ID不能为空");
											return result;
										}

										if (e1.get("BYTID") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl004_kjyw的病原体ID不能为空");
											return result;
										}

										if (e1.get("INPUTDATE") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl004_kjyw的最后更新时间不能为空");
											return result;
										}

										if (e1.get("ANTIBNAME") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl004_kjyw的抗菌药物名称不能为空");
											return result;
										}

										if (e1.get("ANTIBRESULT") == null) {
											result.setResult("error");
											result.setMsg("现患率直报异常,xl004_kjyw的药敏结果不能为空");
											return result;
										}
									}

									try {
										datas[0] = ah.a(dicOffice, "BODY", "DATA");
										datas[1] = ah.a(patientMain1, "BODY", "DATA");
										datas[2] = ah.a(infectInfo2, "BODY", "DATA");
										datas[3] = ah.a(pathoInfo2, "BODY", "DATA");
										datas[4] = ah.a(antibInfo2, "BODY", "DATA");
										result.setResult("success");
										result.setData(datas);
									} catch (Exception arg12) {
										c.error("现患率直报异常", arg12);
										arg12.printStackTrace();
										result.setResult("error");
										result.setMsg("现患率直报异常");
									}

									return result;
								}
							}
						}
					}
				}
			}
		}
	}
}