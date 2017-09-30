package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.be;
import com.nis.comm.serializer.c;
import com.nis.hygiene.entity.Hw103Jcdjg;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("hw102Jcdmx")
public class Hw102Jcdmx extends BaseEntity implements Serializable {
	private String id;
	private String djId;
	private String reportId;
	private String classId;
	private String className;
	private String placeId;
	private String placeName;
	private String takeModeId;
	private String takeModeName;
	private String sampleId;
	private String sampleName;
	private String posId;
	private String posName;
	private String takeBy;
	private Date takeAt;
	private String testBy;
	private Date testAt;
	private String verifyBy;
	private Date verifyAt;
	private String reportBy;
	private Date reportAt;
	private String recheck;
	private Double resultPathoNum;
	private Integer resultFlag;
	private String result;
	private String criterion;
	private String memo;
	private String sampleGg;
	private String sampleCj;
	private String samplePh;
	private Date sampleXdrq;
	private Date sampleSxrq;
	private String sampleNds;
	private String sampleZsh;
	private String takeType;
	private String takeTypeName;
	private Integer status;
	private String checkBy;
	private String checkName;
	private Date checkAt;
	private String printBy;
	private String printName;
	private Date printAt;
	private Double isPrint;
	private Integer type;
	private Long iftran;
	private Long ifjctran;
	private String deptId;
	private String deptName;
	private String djDeptId;
	private String userId;
	private String takeByName;
	private String testByName;
	private String reportByName;
	private String dateField;
	private String notPass;
	private String resultFlagName;
	private List<Hw103Jcdjg> hw103List;
	private List<String> checkOutBacteria;
	private String checkOutStr;
	private String clinical;
	private String hw103Action;
	private String cyMeno;
	private String resultCriterion;
	private String condition;
	private String[] deptIds;
	private String dataStatus;
	private Integer isprint;
	private String posValue;

	public String getDataStatus() {
		return this.dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Integer isprint) {
		this.isprint = isprint;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}

	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return this.placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getTakeModeId() {
		return this.takeModeId;
	}

	public void setTakeModeId(String takeModeId) {
		this.takeModeId = takeModeId;
	}

	public String getTakeModeName() {
		return this.takeModeName;
	}

	public void setTakeModeName(String takeModeName) {
		this.takeModeName = takeModeName;
	}

	public String getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getSampleName() {
		return this.sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getPosId() {
		return this.posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getPosName() {
		return this.posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getTakeBy() {
		return this.takeBy;
	}

	public void setTakeBy(String takeBy) {
		this.takeBy = takeBy;
	}

	@JsonSerialize(using = c.class)
	public Date getTakeAt() {
		return this.takeAt;
	}

	public void setTakeAt(Date takeAt) {
		this.takeAt = takeAt;
	}

	public String getTestBy() {
		return this.testBy;
	}

	public void setTestBy(String testBy) {
		this.testBy = testBy;
	}

	@JsonSerialize(using = c.class)
	public Date getTestAt() {
		return this.testAt;
	}

	public void setTestAt(Date testAt) {
		this.testAt = testAt;
	}

	public String getVerifyBy() {
		return this.verifyBy;
	}

	public void setVerifyBy(String verifyBy) {
		this.verifyBy = verifyBy;
	}

	public Date getVerifyAt() {
		return this.verifyAt;
	}

	public void setVerifyAt(Date verifyAt) {
		this.verifyAt = verifyAt;
	}

	public String getReportBy() {
		return this.reportBy;
	}

	public void setReportBy(String reportBy) {
		this.reportBy = reportBy;
	}

	@JsonSerialize(using = c.class)
	public Date getReportAt() {
		return this.reportAt;
	}

	public void setReportAt(Date reportAt) {
		this.reportAt = reportAt;
	}

	public String getRecheck() {
		return this.recheck;
	}

	public void setRecheck(String recheck) {
		this.recheck = recheck;
	}

	public Double getResultPathoNum() {
		return this.resultPathoNum;
	}

	public void setResultPathoNum(Double resultPathoNum) {
		this.resultPathoNum = resultPathoNum;
	}

	public Integer getResultFlag() {
		return this.resultFlag;
	}

	public void setResultFlag(Integer resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCriterion() {
		return this.criterion;
	}

	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSampleGg() {
		return this.sampleGg;
	}

	public void setSampleGg(String sampleGg) {
		this.sampleGg = sampleGg;
	}

	public String getSampleCj() {
		return this.sampleCj;
	}

	public void setSampleCj(String sampleCj) {
		this.sampleCj = sampleCj;
	}

	public String getSamplePh() {
		return this.samplePh;
	}

	public void setSamplePh(String samplePh) {
		this.samplePh = samplePh;
	}

	public Date getSampleXdrq() {
		return this.sampleXdrq;
	}

	public void setSampleXdrq(Date sampleXdrq) {
		this.sampleXdrq = sampleXdrq;
	}

	public Date getSampleSxrq() {
		return this.sampleSxrq;
	}

	public void setSampleSxrq(Date sampleSxrq) {
		this.sampleSxrq = sampleSxrq;
	}

	public String getSampleNds() {
		return this.sampleNds;
	}

	public void setSampleNds(String sampleNds) {
		this.sampleNds = sampleNds;
	}

	public String getSampleZsh() {
		return this.sampleZsh;
	}

	public void setSampleZsh(String sampleZsh) {
		this.sampleZsh = sampleZsh;
	}

	public String getTakeType() {
		return this.takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}

	public String getTakeTypeName() {
		return this.takeTypeName;
	}

	public void setTakeTypeName(String takeTypeName) {
		this.takeTypeName = takeTypeName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCheckBy() {
		return this.checkBy;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}

	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public Date getCheckAt() {
		return this.checkAt;
	}

	public void setCheckAt(Date checkAt) {
		this.checkAt = checkAt;
	}

	public String getPrintBy() {
		return this.printBy;
	}

	public void setPrintBy(String printBy) {
		this.printBy = printBy;
	}

	public String getPrintName() {
		return this.printName;
	}

	public void setPrintName(String printName) {
		this.printName = printName;
	}

	public Date getPrintAt() {
		return this.printAt;
	}

	public void setPrintAt(Date printAt) {
		this.printAt = printAt;
	}

	public Double getIsPrint() {
		return this.isPrint;
	}

	public void setIsPrint(Double isPrint) {
		this.isPrint = isPrint;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getIftran() {
		return this.iftran;
	}

	public void setIftran(Long iftran) {
		this.iftran = iftran;
	}

	public Long getIfjctran() {
		return this.ifjctran;
	}

	public void setIfjctran(Long ifjctran) {
		this.ifjctran = ifjctran;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDjDeptId() {
		return this.djDeptId;
	}

	public void setDjDeptId(String djDeptId) {
		this.djDeptId = djDeptId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTakeByName() {
		return this.takeByName;
	}

	public void setTakeByName(String takeByName) {
		this.takeByName = takeByName;
	}

	public String getTestByName() {
		return this.testByName;
	}

	public void setTestByName(String testByName) {
		this.testByName = testByName;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getReportByName() {
		return this.reportByName;
	}

	public void setReportByName(String reportByName) {
		this.reportByName = reportByName;
	}

	public String getDateField() {
		return this.dateField;
	}

	public void setDateField(String dateField) {
		this.dateField = dateField;
	}

	public String getResultFlagName() {
		return be.p(this.resultFlag).getName();
	}

	public void setResultFlagName(String resultFlagName) {
		this.resultFlagName = resultFlagName;
	}

	public List<Hw103Jcdjg> getHw103List() {
		return this.hw103List;
	}

	public void setHw103List(List<Hw103Jcdjg> hw103List) {
		this.hw103List = hw103List;
	}

	public List<String> getCheckOutBacteria() {
		return this.checkOutBacteria;
	}

	public String getCyMeno() {
		return this.cyMeno;
	}

	public void setCyMeno(String cyMeno) {
		this.cyMeno = cyMeno;
	}

	public void setCheckOutBacteria(List<String> checkOutBacteria) {
		this.checkOutBacteria = checkOutBacteria;
	}

	public String getCheckOutStr() {
		return this.checkOutStr;
	}

	public void setCheckOutStr(String checkOutStr) {
		this.checkOutStr = checkOutStr;
	}

	public String getClinical() {
		return this.clinical;
	}

	public void setClinical(String clinical) {
		this.clinical = clinical;
	}

	public String getHw103Action() {
		return this.hw103Action;
	}

	public void setHw103Action(String hw103Action) {
		this.hw103Action = hw103Action;
	}

	public String getResultCriterion() {
		return this.resultCriterion;
	}

	public void setResultCriterion(String resultCriterion) {
		this.resultCriterion = resultCriterion;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String[] getDeptIds() {
		return this.deptIds;
	}

	public void setDeptIds(String[] deptIds) {
		this.deptIds = deptIds;
	}

	public String getPosValue() {
		return this.posValue;
	}

	public void setPosValue(String posValue) {
		this.posValue = posValue;
	}

	public String getNotPass() {
		return this.notPass;
	}

	public void setNotPass(String notPass) {
		this.notPass = notPass;
	}
}