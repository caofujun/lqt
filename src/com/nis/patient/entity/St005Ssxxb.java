package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.ao;
import com.nis.comm.serializer.b;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("st005Ssxxb")
public class St005Ssxxb extends BaseEntity implements Serializable {
	private String sqbxbs;
	private String sfsszylg;
	private String cysfylg;
	private String isjt;
	private String isdzcz;
	private String id;
	private String preZqyy;
	private String afterZqyy;
	private String wsqLhyy;
	private String wsqZqyy;
	private String jbzg;
	private Integer heal;
	private String impOpeId;
	private String impOpeName;
	private Integer viewFlag;
	private String circuitNurse;
	private String scrubNurse;
	private Integer xgFlag;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private String deptName;
	private Date updDate;
	private Date outAt;
	private String age;
	private String ageUnit;
	private String sex;
	private String bedNo;
	private Date inHospAt;
	private String syncCode;
	private String syncName;
	private Date paramBegintime;
	private Date paramEndtime;
	private String lastId;
	private String lastStatus;
	private Date lastSyncTime;
	private String lastLog;
	private String deptId;
	private String inDeptId;
	private String inDeptName;
	private String outDeptId;
	private String outDeptName;
	private Integer inDays;
	private String chargeDrId;
	private String chargeDrName;
	private Double cost;
	private String memo;
	private Integer bedNoIndex;
	private String patientName;
	private String lastModUserid;
	private Integer status;
	private Date operAt;
	private Date returnvisitdate3;
	private Date returnvisitdate2;
	private Date returnvisitdate1;
	private String relid;
	private String operRoom;
	private String operName;
	private String operId;
	private String opedocId;
	private String opedocName;
	private String anesDrName;
	private Integer asa;
	private String narcKind;
	private Integer operLengTime;
	private String incisionGrade;
	private String hospId;
	private String tel;
	private String weight;
	private String patientAddress;
	private String lapseTo;
	private Date lapseToAt;
	private Integer inopeDays;
	private String wzbxbjs;
	private Integer bloodSugarLevel;
	private String yzjcjb;
	private Integer bmi;
	private String typeBuild;
	private String nutritionCondition;
	private String opepartkindid;
	private String partkindname;
	private Date operAtEnd;
	private String ssyszc;
	private String anesDrId;
	private String nurseId;
	private String nurseName;
	private Integer nnis;
	private Integer bloodIn;
	private Integer bloodOut;
	private String continuousOpe;
	private String skinPrepare;
	private String skinMethod;
	private String preSkintime;
	private String wsqsykjyw;
	private String wsqsyyfkjyw;
	private String preYymd;
	private String preLhyy;
	private Integer preYyts;
	private String isSqyy;
	private String szyzjyyewzj;
	private String afterYymd;
	private String afterLhyy;
	private Integer afterYyts;
	private String tmExceed1;
	private String cutred1;
	private String cutpus1;
	private String pathoisyang1;
	private String tmExceed2;
	private String cutred2;
	private String cutpus2;
	private String pathoisyang2;
	private String tmExceed3;
	private String cutred3;
	private String cutpus3;
	private String pathoisyang3;
	private Date monitorDate;
	private Date lastModDate;
	private Date createDate;
	private String urgentOpe;
	private String replant;
	private String glassOpe;
	private String returnvisitStatus;
	private String dangerIndex;
	private Date operAtStart;
	private String operBw;
	private Integer riskRateType;
	private Integer percBitValue;
	private Integer isMonitorOpera;
	private String statusName;
	private Integer nnisValue;
	private String date;
	private String isZdjc;
	private String infectTypeId;
	private String infectTypeName;
	private String gx;
	private String gr;
	private String deptType;
	private String dateType;
	private String grType;
	private List<String> deptIdIn;
	private String lengTime;
	private String infectDate;
	private String infectDiagnName;
	private String diagnosisName;
	private Integer afterGreater24;

	public String getSqbxbs() {
		return this.sqbxbs;
	}

	public void setSqbxbs(String sqbxbs) {
		this.sqbxbs = sqbxbs;
	}

	public String getSfsszylg() {
		return this.sfsszylg;
	}

	public void setSfsszylg(String sfsszylg) {
		this.sfsszylg = sfsszylg;
	}

	public String getCysfylg() {
		return this.cysfylg;
	}

	public void setCysfylg(String cysfylg) {
		this.cysfylg = cysfylg;
	}

	public String getIsjt() {
		return this.isjt;
	}

	public void setIsjt(String isjt) {
		this.isjt = isjt;
	}

	public String getIsdzcz() {
		return this.isdzcz;
	}

	public void setIsdzcz(String isdzcz) {
		this.isdzcz = isdzcz;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPreZqyy() {
		return this.preZqyy;
	}

	public void setPreZqyy(String preZqyy) {
		this.preZqyy = preZqyy;
	}

	public String getAfterZqyy() {
		return this.afterZqyy;
	}

	public void setAfterZqyy(String afterZqyy) {
		this.afterZqyy = afterZqyy;
	}

	public String getWsqLhyy() {
		return this.wsqLhyy;
	}

	public void setWsqLhyy(String wsqLhyy) {
		this.wsqLhyy = wsqLhyy;
	}

	public String getWsqZqyy() {
		return this.wsqZqyy;
	}

	public void setWsqZqyy(String wsqZqyy) {
		this.wsqZqyy = wsqZqyy;
	}

	public String getJbzg() {
		return this.jbzg;
	}

	public void setJbzg(String jbzg) {
		this.jbzg = jbzg;
	}

	public Integer getHeal() {
		return this.heal;
	}

	public void setHeal(Integer heal) {
		this.heal = heal;
	}

	public String getImpOpeId() {
		return this.impOpeId;
	}

	public void setImpOpeId(String impOpeId) {
		this.impOpeId = impOpeId;
	}

	public String getImpOpeName() {
		return this.impOpeName;
	}

	public void setImpOpeName(String impOpeName) {
		this.impOpeName = impOpeName;
	}

	public Integer getViewFlag() {
		return this.viewFlag;
	}

	public void setViewFlag(Integer viewFlag) {
		this.viewFlag = viewFlag;
	}

	public String getCircuitNurse() {
		return this.circuitNurse;
	}

	public void setCircuitNurse(String circuitNurse) {
		this.circuitNurse = circuitNurse;
	}

	public String getScrubNurse() {
		return this.scrubNurse;
	}

	public void setScrubNurse(String scrubNurse) {
		this.scrubNurse = scrubNurse;
	}

	public Integer getXgFlag() {
		return this.xgFlag;
	}

	public void setXgFlag(Integer xgFlag) {
		this.xgFlag = xgFlag;
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

	public Integer getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	@JsonSerialize(using = b.class)
	public Date getOutAt() {
		return this.outAt;
	}

	public void setOutAt(Date outAt) {
		this.outAt = outAt;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAgeUnit() {
		return this.ageUnit;
	}

	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public Date getInHospAt() {
		return this.inHospAt;
	}

	public void setInHospAt(Date inHospAt) {
		this.inHospAt = inHospAt;
	}

	public String getSyncCode() {
		return this.syncCode;
	}

	public void setSyncCode(String syncCode) {
		this.syncCode = syncCode;
	}

	public String getSyncName() {
		return this.syncName;
	}

	public void setSyncName(String syncName) {
		this.syncName = syncName;
	}

	public Date getParamBegintime() {
		return this.paramBegintime;
	}

	public void setParamBegintime(Date paramBegintime) {
		this.paramBegintime = paramBegintime;
	}

	public Date getParamEndtime() {
		return this.paramEndtime;
	}

	public void setParamEndtime(Date paramEndtime) {
		this.paramEndtime = paramEndtime;
	}

	public String getLastId() {
		return this.lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}

	public String getLastStatus() {
		return this.lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public Date getLastSyncTime() {
		return this.lastSyncTime;
	}

	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public String getLastLog() {
		return this.lastLog;
	}

	public void setLastLog(String lastLog) {
		this.lastLog = lastLog;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getInDeptId() {
		return this.inDeptId;
	}

	public void setInDeptId(String inDeptId) {
		this.inDeptId = inDeptId;
	}

	public String getInDeptName() {
		return this.inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public String getOutDeptId() {
		return this.outDeptId;
	}

	public void setOutDeptId(String outDeptId) {
		this.outDeptId = outDeptId;
	}

	public String getOutDeptName() {
		return this.outDeptName;
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}

	public Integer getInDays() {
		return this.inDays;
	}

	public void setInDays(Integer inDays) {
		this.inDays = inDays;
	}

	public String getChargeDrId() {
		return this.chargeDrId;
	}

	public void setChargeDrId(String chargeDrId) {
		this.chargeDrId = chargeDrId;
	}

	public String getChargeDrName() {
		return this.chargeDrName;
	}

	public void setChargeDrName(String chargeDrName) {
		this.chargeDrName = chargeDrName;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getBedNoIndex() {
		return this.bedNoIndex;
	}

	public void setBedNoIndex(Integer bedNoIndex) {
		this.bedNoIndex = bedNoIndex;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getLastModUserid() {
		return this.lastModUserid;
	}

	public void setLastModUserid(String lastModUserid) {
		this.lastModUserid = lastModUserid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonSerialize(using = b.class)
	public Date getOperAt() {
		return this.operAt;
	}

	public void setOperAt(Date operAt) {
		this.operAt = operAt;
	}

	public Date getReturnvisitdate3() {
		return this.returnvisitdate3;
	}

	public void setReturnvisitdate3(Date returnvisitdate3) {
		this.returnvisitdate3 = returnvisitdate3;
	}

	public Date getReturnvisitdate2() {
		return this.returnvisitdate2;
	}

	public void setReturnvisitdate2(Date returnvisitdate2) {
		this.returnvisitdate2 = returnvisitdate2;
	}

	public Date getReturnvisitdate1() {
		return this.returnvisitdate1;
	}

	public void setReturnvisitdate1(Date returnvisitdate1) {
		this.returnvisitdate1 = returnvisitdate1;
	}

	public String getRelid() {
		return this.relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getOperRoom() {
		return this.operRoom;
	}

	public void setOperRoom(String operRoom) {
		this.operRoom = operRoom;
	}

	public String getOperName() {
		return this.operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getOpedocId() {
		return this.opedocId;
	}

	public void setOpedocId(String opedocId) {
		this.opedocId = opedocId;
	}

	public String getOpedocName() {
		return this.opedocName;
	}

	public void setOpedocName(String opedocName) {
		this.opedocName = opedocName;
	}

	public String getAnesDrName() {
		return this.anesDrName;
	}

	public void setAnesDrName(String anesDrName) {
		this.anesDrName = anesDrName;
	}

	public Integer getAsa() {
		return this.asa;
	}

	public void setAsa(Integer asa) {
		this.asa = asa;
	}

	public String getNarcKind() {
		return this.narcKind;
	}

	public void setNarcKind(String narcKind) {
		this.narcKind = narcKind;
	}

	public Integer getOperLengTime() {
		return this.operLengTime;
	}

	public void setOperLengTime(Integer operLengTime) {
		this.operLengTime = operLengTime;
	}

	public String getIncisionGrade() {
		return this.incisionGrade;
	}

	public void setIncisionGrade(String incisionGrade) {
		this.incisionGrade = incisionGrade;
	}

	public String getHospId() {
		return this.hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPatientAddress() {
		return this.patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getLapseTo() {
		return this.lapseTo;
	}

	public void setLapseTo(String lapseTo) {
		this.lapseTo = lapseTo;
	}

	public Date getLapseToAt() {
		return this.lapseToAt;
	}

	public void setLapseToAt(Date lapseToAt) {
		this.lapseToAt = lapseToAt;
	}

	public Integer getInopeDays() {
		return this.inopeDays;
	}

	public void setInopeDays(Integer inopeDays) {
		this.inopeDays = inopeDays;
	}

	public String getWzbxbjs() {
		return this.wzbxbjs;
	}

	public void setWzbxbjs(String wzbxbjs) {
		this.wzbxbjs = wzbxbjs;
	}

	public Integer getBloodSugarLevel() {
		return this.bloodSugarLevel;
	}

	public void setBloodSugarLevel(Integer bloodSugarLevel) {
		this.bloodSugarLevel = bloodSugarLevel;
	}

	public String getYzjcjb() {
		return this.yzjcjb;
	}

	public void setYzjcjb(String yzjcjb) {
		this.yzjcjb = yzjcjb;
	}

	public Integer getBmi() {
		return this.bmi;
	}

	public void setBmi(Integer bmi) {
		this.bmi = bmi;
	}

	public String getTypeBuild() {
		return this.typeBuild;
	}

	public void setTypeBuild(String typeBuild) {
		this.typeBuild = typeBuild;
	}

	public String getNutritionCondition() {
		return this.nutritionCondition;
	}

	public void setNutritionCondition(String nutritionCondition) {
		this.nutritionCondition = nutritionCondition;
	}

	public String getOpepartkindid() {
		return this.opepartkindid;
	}

	public void setOpepartkindid(String opepartkindid) {
		this.opepartkindid = opepartkindid;
	}

	public String getPartkindname() {
		return this.partkindname;
	}

	public void setPartkindname(String partkindname) {
		this.partkindname = partkindname;
	}

	public Date getOperAtEnd() {
		return this.operAtEnd;
	}

	public void setOperAtEnd(Date operAtEnd) {
		this.operAtEnd = operAtEnd;
	}

	public String getSsyszc() {
		return this.ssyszc;
	}

	public void setSsyszc(String ssyszc) {
		this.ssyszc = ssyszc;
	}

	public String getAnesDrId() {
		return this.anesDrId;
	}

	public void setAnesDrId(String anesDrId) {
		this.anesDrId = anesDrId;
	}

	public String getNurseId() {
		return this.nurseId;
	}

	public void setNurseId(String nurseId) {
		this.nurseId = nurseId;
	}

	public String getNurseName() {
		return this.nurseName;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}

	public Integer getNnis() {
		return this.nnis;
	}

	public void setNnis(Integer nnis) {
		this.nnis = nnis;
	}

	public Integer getBloodIn() {
		return this.bloodIn;
	}

	public void setBloodIn(Integer bloodIn) {
		this.bloodIn = bloodIn;
	}

	public Integer getBloodOut() {
		return this.bloodOut;
	}

	public void setBloodOut(Integer bloodOut) {
		this.bloodOut = bloodOut;
	}

	public String getContinuousOpe() {
		return this.continuousOpe;
	}

	public void setContinuousOpe(String continuousOpe) {
		this.continuousOpe = continuousOpe;
	}

	public String getSkinPrepare() {
		return this.skinPrepare;
	}

	public void setSkinPrepare(String skinPrepare) {
		this.skinPrepare = skinPrepare;
	}

	public String getSkinMethod() {
		return this.skinMethod;
	}

	public void setSkinMethod(String skinMethod) {
		this.skinMethod = skinMethod;
	}

	public String getPreSkintime() {
		return this.preSkintime;
	}

	public void setPreSkintime(String preSkintime) {
		this.preSkintime = preSkintime;
	}

	public String getWsqsykjyw() {
		return this.wsqsykjyw;
	}

	public void setWsqsykjyw(String wsqsykjyw) {
		this.wsqsykjyw = wsqsykjyw;
	}

	public String getWsqsyyfkjyw() {
		return this.wsqsyyfkjyw;
	}

	public void setWsqsyyfkjyw(String wsqsyyfkjyw) {
		this.wsqsyyfkjyw = wsqsyyfkjyw;
	}

	public String getPreYymd() {
		return this.preYymd;
	}

	public void setPreYymd(String preYymd) {
		this.preYymd = preYymd;
	}

	public String getPreLhyy() {
		return this.preLhyy;
	}

	public void setPreLhyy(String preLhyy) {
		this.preLhyy = preLhyy;
	}

	public Integer getPreYyts() {
		return this.preYyts;
	}

	public void setPreYyts(Integer preYyts) {
		this.preYyts = preYyts;
	}

	public String getIsSqyy() {
		return this.isSqyy;
	}

	public void setIsSqyy(String isSqyy) {
		this.isSqyy = isSqyy;
	}

	public String getSzyzjyyewzj() {
		return this.szyzjyyewzj;
	}

	public void setSzyzjyyewzj(String szyzjyyewzj) {
		this.szyzjyyewzj = szyzjyyewzj;
	}

	public String getAfterYymd() {
		return this.afterYymd;
	}

	public void setAfterYymd(String afterYymd) {
		this.afterYymd = afterYymd;
	}

	public String getAfterLhyy() {
		return this.afterLhyy;
	}

	public void setAfterLhyy(String afterLhyy) {
		this.afterLhyy = afterLhyy;
	}

	public Integer getAfterYyts() {
		return this.afterYyts;
	}

	public void setAfterYyts(Integer afterYyts) {
		this.afterYyts = afterYyts;
	}

	public String getTmExceed1() {
		return this.tmExceed1;
	}

	public void setTmExceed1(String tmExceed1) {
		this.tmExceed1 = tmExceed1;
	}

	public String getCutred1() {
		return this.cutred1;
	}

	public void setCutred1(String cutred1) {
		this.cutred1 = cutred1;
	}

	public String getCutpus1() {
		return this.cutpus1;
	}

	public void setCutpus1(String cutpus1) {
		this.cutpus1 = cutpus1;
	}

	public String getPathoisyang1() {
		return this.pathoisyang1;
	}

	public void setPathoisyang1(String pathoisyang1) {
		this.pathoisyang1 = pathoisyang1;
	}

	public String getTmExceed2() {
		return this.tmExceed2;
	}

	public void setTmExceed2(String tmExceed2) {
		this.tmExceed2 = tmExceed2;
	}

	public String getCutred2() {
		return this.cutred2;
	}

	public void setCutred2(String cutred2) {
		this.cutred2 = cutred2;
	}

	public String getCutpus2() {
		return this.cutpus2;
	}

	public void setCutpus2(String cutpus2) {
		this.cutpus2 = cutpus2;
	}

	public String getPathoisyang2() {
		return this.pathoisyang2;
	}

	public void setPathoisyang2(String pathoisyang2) {
		this.pathoisyang2 = pathoisyang2;
	}

	public String getTmExceed3() {
		return this.tmExceed3;
	}

	public void setTmExceed3(String tmExceed3) {
		this.tmExceed3 = tmExceed3;
	}

	public String getCutred3() {
		return this.cutred3;
	}

	public void setCutred3(String cutred3) {
		this.cutred3 = cutred3;
	}

	public String getCutpus3() {
		return this.cutpus3;
	}

	public void setCutpus3(String cutpus3) {
		this.cutpus3 = cutpus3;
	}

	public String getPathoisyang3() {
		return this.pathoisyang3;
	}

	public void setPathoisyang3(String pathoisyang3) {
		this.pathoisyang3 = pathoisyang3;
	}

	public Date getMonitorDate() {
		return this.monitorDate;
	}

	public void setMonitorDate(Date monitorDate) {
		this.monitorDate = monitorDate;
	}

	public Date getLastModDate() {
		return this.lastModDate;
	}

	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUrgentOpe() {
		return this.urgentOpe;
	}

	public void setUrgentOpe(String urgentOpe) {
		this.urgentOpe = urgentOpe;
	}

	public String getReplant() {
		return this.replant;
	}

	public void setReplant(String replant) {
		this.replant = replant;
	}

	public String getGlassOpe() {
		return this.glassOpe;
	}

	public void setGlassOpe(String glassOpe) {
		this.glassOpe = glassOpe;
	}

	public String getReturnvisitStatus() {
		return this.returnvisitStatus;
	}

	public void setReturnvisitStatus(String returnvisitStatus) {
		this.returnvisitStatus = returnvisitStatus;
	}

	public String getDangerIndex() {
		return this.dangerIndex;
	}

	public void setDangerIndex(String dangerIndex) {
		this.dangerIndex = dangerIndex;
	}

	public Integer getRiskRateType() {
		return this.riskRateType;
	}

	public void setRiskRateType(Integer riskRateType) {
		this.riskRateType = riskRateType;
	}

	public Integer getPercBitValue() {
		return this.percBitValue;
	}

	public void setPercBitValue(Integer percBitValue) {
		this.percBitValue = percBitValue;
	}

	public Integer getIsMonitorOpera() {
		return this.isMonitorOpera;
	}

	public void setIsMonitorOpera(Integer isMonitorOpera) {
		this.isMonitorOpera = isMonitorOpera;
	}

	public String getStatusName() {
		return ao.j(this.status).getName();
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getNnisValue() {
		return this.nnisValue;
	}

	public void setNnisValue(Integer nnisValue) {
		this.nnisValue = nnisValue;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIsZdjc() {
		return this.isZdjc;
	}

	public void setIsZdjc(String isZdjc) {
		this.isZdjc = isZdjc;
	}

	public String getInfectTypeId() {
		return this.infectTypeId;
	}

	public void setInfectTypeId(String infectTypeId) {
		this.infectTypeId = infectTypeId;
	}

	public String getInfectTypeName() {
		return this.infectTypeName;
	}

	public void setInfectTypeName(String infectTypeName) {
		this.infectTypeName = infectTypeName;
	}

	public String getGx() {
		return this.gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public List<String> getDeptIdIn() {
		return this.deptIdIn;
	}

	public void setDeptIdIn(List<String> deptIdIn) {
		this.deptIdIn = deptIdIn;
	}

	public String getDeptType() {
		return this.deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public Date getOperAtStart() {
		return this.operAtStart;
	}

	public void setOperAtStart(Date operAtStart) {
		this.operAtStart = operAtStart;
	}

	public String getOperBw() {
		return this.operBw;
	}

	public void setOperBw(String operBw) {
		this.operBw = operBw;
	}

	public String getDateType() {
		return this.dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getLengTime() {
		return this.lengTime;
	}

	public void setLengTime(String lengTime) {
		this.lengTime = lengTime;
	}

	public String getInfectDate() {
		return this.infectDate;
	}

	public void setInfectDate(String infectDate) {
		this.infectDate = infectDate;
	}

	public String getInfectDiagnName() {
		return this.infectDiagnName;
	}

	public void setInfectDiagnName(String infectDiagnName) {
		this.infectDiagnName = infectDiagnName;
	}

	public String getDiagnosisName() {
		return this.diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public String getGr() {
		return this.gr;
	}

	public void setGr(String gr) {
		this.gr = gr;
	}

	public String getGrType() {
		return this.grType;
	}

	public void setGrType(String grType) {
		this.grType = grType;
	}

	public Integer getAfterGreater24() {
		return this.afterGreater24;
	}

	public void setAfterGreater24(Integer afterGreater24) {
		this.afterGreater24 = afterGreater24;
	}

	public String toString() {
		return "St005Ssxxb [sqbxbs=" + this.sqbxbs + ", sfsszylg=" + this.sfsszylg + ", cysfylg=" + this.cysfylg
				+ ", isjt=" + this.isjt + ", isdzcz=" + this.isdzcz + ", id=" + this.id + ", preZqyy=" + this.preZqyy
				+ ", afterZqyy=" + this.afterZqyy + ", wsqLhyy=" + this.wsqLhyy + ", wsqZqyy=" + this.wsqZqyy
				+ ", jbzg=" + this.jbzg + ", heal=" + this.heal + ", impOpeId=" + this.impOpeId + ", impOpeName="
				+ this.impOpeName + ", viewFlag=" + this.viewFlag + ", circuitNurse=" + this.circuitNurse
				+ ", scrubNurse=" + this.scrubNurse + ", xgFlag=" + this.xgFlag + ", zyid=" + this.zyid + ", patientId="
				+ this.patientId + ", visitId=" + this.visitId + ", deptName=" + this.deptName + ", updDate="
				+ this.updDate + ", outAt=" + this.outAt + ", age=" + this.age + ", ageUnit=" + this.ageUnit + ", sex="
				+ this.sex + ", bedNo=" + this.bedNo + ", inHospAt=" + this.inHospAt + ", syncCode=" + this.syncCode
				+ ", syncName=" + this.syncName + ", paramBegintime=" + this.paramBegintime + ", paramEndtime="
				+ this.paramEndtime + ", lastId=" + this.lastId + ", lastStatus=" + this.lastStatus + ", lastSyncTime="
				+ this.lastSyncTime + ", lastLog=" + this.lastLog + ", deptId=" + this.deptId + ", inDeptId="
				+ this.inDeptId + ", inDeptName=" + this.inDeptName + ", outDeptId=" + this.outDeptId + ", outDeptName="
				+ this.outDeptName + ", inDays=" + this.inDays + ", chargeDrId=" + this.chargeDrId + ", chargeDrName="
				+ this.chargeDrName + ", cost=" + this.cost + ", memo=" + this.memo + ", bedNoIndex=" + this.bedNoIndex
				+ ", patientName=" + this.patientName + ", lastModUserid=" + this.lastModUserid + ", status="
				+ this.status + ", operAt=" + this.operAt + ", returnvisitdate3=" + this.returnvisitdate3
				+ ", returnvisitdate2=" + this.returnvisitdate2 + ", returnvisitdate1=" + this.returnvisitdate1
				+ ", relid=" + this.relid + ", operRoom=" + this.operRoom + ", operName=" + this.operName + ", operId="
				+ this.operId + ", opedocId=" + this.opedocId + ", opedocName=" + this.opedocName + ", anesDrName="
				+ this.anesDrName + ", asa=" + this.asa + ", narcKind=" + this.narcKind + ", operLengTime="
				+ this.operLengTime + ", incisionGrade=" + this.incisionGrade + ", hospId=" + this.hospId + ", tel="
				+ this.tel + ", weight=" + this.weight + ", patientAddress=" + this.patientAddress + ", lapseTo="
				+ this.lapseTo + ", lapseToAt=" + this.lapseToAt + ", inopeDays=" + this.inopeDays + ", wzbxbjs="
				+ this.wzbxbjs + ", bloodSugarLevel=" + this.bloodSugarLevel + ", yzjcjb=" + this.yzjcjb + ", bmi="
				+ this.bmi + ", typeBuild=" + this.typeBuild + ", nutritionCondition=" + this.nutritionCondition
				+ ", opepartkindid=" + this.opepartkindid + ", partkindname=" + this.partkindname + ", operAtEnd="
				+ this.operAtEnd + ", ssyszc=" + this.ssyszc + ", anesDrId=" + this.anesDrId + ", nurseId="
				+ this.nurseId + ", nurseName=" + this.nurseName + ", nnis=" + this.nnis + ", bloodIn=" + this.bloodIn
				+ ", bloodOut=" + this.bloodOut + ", continuousOpe=" + this.continuousOpe + ", skinPrepare="
				+ this.skinPrepare + ", skinMethod=" + this.skinMethod + ", preSkintime=" + this.preSkintime
				+ ", wsqsykjyw=" + this.wsqsykjyw + ", wsqsyyfkjyw=" + this.wsqsyyfkjyw + ", preYymd=" + this.preYymd
				+ ", preLhyy=" + this.preLhyy + ", preYyts=" + this.preYyts + ", isSqyy=" + this.isSqyy
				+ ", szyzjyyewzj=" + this.szyzjyyewzj + ", afterYymd=" + this.afterYymd + ", afterLhyy="
				+ this.afterLhyy + ", afterYyts=" + this.afterYyts + ", tmExceed1=" + this.tmExceed1 + ", cutred1="
				+ this.cutred1 + ", cutpus1=" + this.cutpus1 + ", pathoisyang1=" + this.pathoisyang1 + ", tmExceed2="
				+ this.tmExceed2 + ", cutred2=" + this.cutred2 + ", cutpus2=" + this.cutpus2 + ", pathoisyang2="
				+ this.pathoisyang2 + ", tmExceed3=" + this.tmExceed3 + ", cutred3=" + this.cutred3 + ", cutpus3="
				+ this.cutpus3 + ", pathoisyang3=" + this.pathoisyang3 + ", monitorDate=" + this.monitorDate
				+ ", lastModDate=" + this.lastModDate + ", createDate=" + this.createDate + ", urgentOpe="
				+ this.urgentOpe + ", replant=" + this.replant + ", glassOpe=" + this.glassOpe + ", returnvisitStatus="
				+ this.returnvisitStatus + ", dangerIndex=" + this.dangerIndex + ", operAtStart=" + this.operAtStart
				+ ", operBw=" + this.operBw + ", riskRateType=" + this.riskRateType + ", percBitValue="
				+ this.percBitValue + ", isMonitorOpera=" + this.isMonitorOpera + ", statusName=" + this.statusName
				+ ", nnisValue=" + this.nnisValue + ", date=" + this.date + ", isZdjc=" + this.isZdjc
				+ ", infectTypeId=" + this.infectTypeId + ", infectTypeName=" + this.infectTypeName + ", gx=" + this.gx
				+ ", gr=" + this.gr + ", deptType=" + this.deptType + ", dateType=" + this.dateType + ", grType="
				+ this.grType + ", deptIdIn=" + this.deptIdIn + ", lengTime=" + this.lengTime + ", infectDate="
				+ this.infectDate + ", infectDiagnName=" + this.infectDiagnName + ", diagnosisName="
				+ this.diagnosisName + ", afterGreater24=" + this.afterGreater24 + "]";
	}
}