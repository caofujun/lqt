package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.ba;
import com.nis.comm.enums.h;
import com.nis.comm.serializer.c;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("xl001Brxx")
public class Xl001Brxx extends BaseEntity implements Serializable {
	private Integer sqyykjyw;
	private Integer sqjy;
	private Integer fszl;
	private Integer hxzl;
	private Integer xytx;
	private Integer myyzj;
	private Integer tpzjs;
	private Integer djmcg;
	private Integer mndcg;
	private Integer syhxj;
	private Integer qgcg;
	private Integer qgqk;
	private Integer ywmndcg;
	private Integer ywdjmcg;
	private Integer ywqgqqg;
	private Integer ywsyychxj;
	private Integer state;
	private Integer spyshkjyw;
	private String kjywmc1;
	private String kjywmc2;
	private String kjywmc3;
	private String kjywmc4;
	private Integer aiz;
	private Integer tyb;
	private Integer xyjb;
	private Integer yybl;
	private Integer dlsykjyw;
	private String brid;
	private Integer year;
	private Integer month;
	private String zyid;
	private String patientId;
	private String patientName;
	private Integer visitId;
	private String deptId;
	private String deptName;
	private String bedNo;
	private String regId;
	private String sex;
	private Integer age;
	private String ageUnit;
	private String diagnoseId;
	private String diagnose;
	private Integer isOper;
	private Integer isGrade1;
	private Integer isGrade2;
	private Integer isGrade3;
	private Integer isGrade4;
	private Integer isInfect;
	private Integer isCa;
	private Integer isHa;
	private Integer pop;
	private Integer sykjyw;
	private Integer yymd;
	private Integer lhyy;
	private Integer zlyyspy;
	private String voteid;
	private String votename;
	private Date votedate;
	private Date lastAt;
	private Integer isEdit;
	private String operName1;
	private String operName2;
	private String gradeType2;
	private String incisionType;
	private String drugResults;
	private String stateName;
	private String isOperName;
	private String infectName;
	private Integer syrsnj;
	private String qtqxxcc;
	private Integer wj;
	private Integer cj;
	private Integer xzj;
	private Integer hj;
	private Integer sezcj;

	public Integer getSyrsnj() {
		return this.syrsnj;
	}

	public void setSyrsnj(Integer syrsnj) {
		this.syrsnj = syrsnj;
	}

	public String getQtqxxcc() {
		return this.qtqxxcc;
	}

	public void setQtqxxcc(String qtqxxcc) {
		this.qtqxxcc = qtqxxcc;
	}

	public Integer getWj() {
		return this.wj;
	}

	public void setWj(Integer wj) {
		this.wj = wj;
	}

	public Integer getCj() {
		return this.cj;
	}

	public void setCj(Integer cj) {
		this.cj = cj;
	}

	public Integer getXzj() {
		return this.xzj;
	}

	public void setXzj(Integer xzj) {
		this.xzj = xzj;
	}

	public Integer getHj() {
		return this.hj;
	}

	public void setHj(Integer hj) {
		this.hj = hj;
	}

	public Integer getSezcj() {
		return this.sezcj;
	}

	public void setSezcj(Integer sezcj) {
		this.sezcj = sezcj;
	}

	public Integer getSqyykjyw() {
		return this.sqyykjyw;
	}

	public void setSqyykjyw(Integer sqyykjyw) {
		this.sqyykjyw = sqyykjyw;
	}

	public Integer getSqjy() {
		return this.sqjy;
	}

	public void setSqjy(Integer sqjy) {
		this.sqjy = sqjy;
	}

	public Integer getFszl() {
		return this.fszl;
	}

	public void setFszl(Integer fszl) {
		this.fszl = fszl;
	}

	public Integer getHxzl() {
		return this.hxzl;
	}

	public void setHxzl(Integer hxzl) {
		this.hxzl = hxzl;
	}

	public Integer getXytx() {
		return this.xytx;
	}

	public void setXytx(Integer xytx) {
		this.xytx = xytx;
	}

	public Integer getMyyzj() {
		return this.myyzj;
	}

	public void setMyyzj(Integer myyzj) {
		this.myyzj = myyzj;
	}

	public Integer getTpzjs() {
		return this.tpzjs;
	}

	public void setTpzjs(Integer tpzjs) {
		this.tpzjs = tpzjs;
	}

	public Integer getDjmcg() {
		return this.djmcg;
	}

	public void setDjmcg(Integer djmcg) {
		this.djmcg = djmcg;
	}

	public Integer getMndcg() {
		return this.mndcg;
	}

	public void setMndcg(Integer mndcg) {
		this.mndcg = mndcg;
	}

	public Integer getSyhxj() {
		return this.syhxj;
	}

	public void setSyhxj(Integer syhxj) {
		this.syhxj = syhxj;
	}

	public Integer getQgcg() {
		return this.qgcg;
	}

	public void setQgcg(Integer qgcg) {
		this.qgcg = qgcg;
	}

	public Integer getQgqk() {
		return this.qgqk;
	}

	public void setQgqk(Integer qgqk) {
		this.qgqk = qgqk;
	}

	public Integer getYwmndcg() {
		return this.ywmndcg;
	}

	public void setYwmndcg(Integer ywmndcg) {
		this.ywmndcg = ywmndcg;
	}

	public Integer getYwdjmcg() {
		return this.ywdjmcg;
	}

	public void setYwdjmcg(Integer ywdjmcg) {
		this.ywdjmcg = ywdjmcg;
	}

	public Integer getYwqgqqg() {
		return this.ywqgqqg;
	}

	public void setYwqgqqg(Integer ywqgqqg) {
		this.ywqgqqg = ywqgqqg;
	}

	public Integer getYwsyychxj() {
		return this.ywsyychxj;
	}

	public void setYwsyychxj(Integer ywsyychxj) {
		this.ywsyychxj = ywsyychxj;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSpyshkjyw() {
		return this.spyshkjyw;
	}

	public void setSpyshkjyw(Integer spyshkjyw) {
		this.spyshkjyw = spyshkjyw;
	}

	public String getKjywmc1() {
		return this.kjywmc1;
	}

	public void setKjywmc1(String kjywmc1) {
		this.kjywmc1 = kjywmc1;
	}

	public String getKjywmc2() {
		return this.kjywmc2;
	}

	public void setKjywmc2(String kjywmc2) {
		this.kjywmc2 = kjywmc2;
	}

	public String getKjywmc3() {
		return this.kjywmc3;
	}

	public void setKjywmc3(String kjywmc3) {
		this.kjywmc3 = kjywmc3;
	}

	public String getKjywmc4() {
		return this.kjywmc4;
	}

	public void setKjywmc4(String kjywmc4) {
		this.kjywmc4 = kjywmc4;
	}

	public Integer getAiz() {
		return this.aiz;
	}

	public void setAiz(Integer aiz) {
		this.aiz = aiz;
	}

	public Integer getTyb() {
		return this.tyb;
	}

	public void setTyb(Integer tyb) {
		this.tyb = tyb;
	}

	public Integer getXyjb() {
		return this.xyjb;
	}

	public void setXyjb(Integer xyjb) {
		this.xyjb = xyjb;
	}

	public Integer getYybl() {
		return this.yybl;
	}

	public void setYybl(Integer yybl) {
		this.yybl = yybl;
	}

	public Integer getDlsykjyw() {
		return this.dlsykjyw;
	}

	public void setDlsykjyw(Integer dlsykjyw) {
		this.dlsykjyw = dlsykjyw;
	}

	public String getBrid() {
		return this.brid;
	}

	public void setBrid(String brid) {
		this.brid = brid;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAgeUnit() {
		return this.ageUnit;
	}

	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}

	public String getDiagnoseId() {
		return this.diagnoseId;
	}

	public void setDiagnoseId(String diagnoseId) {
		this.diagnoseId = diagnoseId;
	}

	public String getDiagnose() {
		return this.diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public Integer getIsOper() {
		return this.isOper;
	}

	public void setIsOper(Integer isOper) {
		this.isOper = isOper;
	}

	public Integer getIsGrade1() {
		return this.isGrade1;
	}

	public void setIsGrade1(Integer isGrade1) {
		this.isGrade1 = isGrade1;
	}

	public Integer getIsGrade2() {
		return this.isGrade2;
	}

	public void setIsGrade2(Integer isGrade2) {
		this.isGrade2 = isGrade2;
	}

	public Integer getIsGrade3() {
		return this.isGrade3;
	}

	public void setIsGrade3(Integer isGrade3) {
		this.isGrade3 = isGrade3;
	}

	public Integer getIsGrade4() {
		return this.isGrade4;
	}

	public void setIsGrade4(Integer isGrade4) {
		this.isGrade4 = isGrade4;
	}

	public Integer getIsInfect() {
		return this.isInfect;
	}

	public void setIsInfect(Integer isInfect) {
		this.isInfect = isInfect;
	}

	public Integer getIsCa() {
		return this.isCa;
	}

	public void setIsCa(Integer isCa) {
		this.isCa = isCa;
	}

	public Integer getIsHa() {
		return this.isHa;
	}

	public void setIsHa(Integer isHa) {
		this.isHa = isHa;
	}

	public Integer getPop() {
		return this.pop;
	}

	public void setPop(Integer pop) {
		this.pop = pop;
	}

	public Integer getSykjyw() {
		return this.sykjyw;
	}

	public void setSykjyw(Integer sykjyw) {
		this.sykjyw = sykjyw;
	}

	public Integer getYymd() {
		return this.yymd;
	}

	public void setYymd(Integer yymd) {
		this.yymd = yymd;
	}

	public Integer getLhyy() {
		return this.lhyy;
	}

	public void setLhyy(Integer lhyy) {
		this.lhyy = lhyy;
	}

	public Integer getZlyyspy() {
		return this.zlyyspy;
	}

	public void setZlyyspy(Integer zlyyspy) {
		this.zlyyspy = zlyyspy;
	}

	public String getVoteid() {
		return this.voteid;
	}

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}

	public String getVotename() {
		return this.votename;
	}

	public void setVotename(String votename) {
		this.votename = votename;
	}

	@JsonSerialize(using = c.class)
	public Date getVotedate() {
		return this.votedate;
	}

	public void setVotedate(Date votedate) {
		this.votedate = votedate;
	}

	@JsonSerialize(using = c.class)
	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Integer getIsEdit() {
		return this.isEdit;
	}

	public void setIsEdit(Integer isEdit) {
		this.isEdit = isEdit;
	}

	public String getIncisionType() {
		return this.incisionType;
	}

	public void setIncisionType(String incisionType) {
		this.incisionType = incisionType;
	}

	public String getDrugResults() {
		return this.drugResults;
	}

	public void setDrugResults(String drugResults) {
		this.drugResults = drugResults;
	}

	public String getStateName() {
		return ba.m(this.state).getName();
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getIsOperName() {
		return h.b(this.isOper);
	}

	public void setIsOperName(String isOperName) {
		this.isOperName = isOperName;
	}

	public String getInfectName() {
		return this.infectName;
	}

	public void setInfectName(String infectName) {
		this.infectName = infectName;
	}

	public String getOperName1() {
		return this.operName1;
	}

	public void setOperName1(String operName1) {
		this.operName1 = operName1;
	}

	public String getOperName2() {
		return this.operName2;
	}

	public void setOperName2(String operName2) {
		this.operName2 = operName2;
	}

	public String getGradeType2() {
		return this.gradeType2;
	}

	public void setGradeType2(String gradeType2) {
		this.gradeType2 = gradeType2;
	}
}