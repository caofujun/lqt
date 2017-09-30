package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.d;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("st011Syjgb")
public class St011Syjgb extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String mzid;
	private String patientId;
	private Integer visitId;
	private String testOrderNo;
	private String resultDate;
	private Integer isym;
	private String antiCode;
	private String antiName;
	private String yaominResult;
	private String testResult;
	private String unit;
	private String referRange;
	private String remark;
	private String pathogenSn;
	private Date createAt;
	private Date updDate;
	private String infectDiagnId;
	private String sampleId;
	private String pathoId;
	private Date submiAt;
	private String drugId;
	private String drugName;
	private String drugTypeId;
	private String drugTypeName;
	private String db;
	private String subclassId;
	private String subclassName;
	private String classCode;
	private String gn;

	public String getGn() {
		return this.gn;
	}

	public void setGn(String gn) {
		this.gn = gn;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public String getResultDate() {
		return this.resultDate;
	}

	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}

	public Integer getIsym() {
		return this.isym;
	}

	public void setIsym(Integer isym) {
		this.isym = isym;
	}

	public String getAntiCode() {
		return this.antiCode;
	}

	public void setAntiCode(String antiCode) {
		this.antiCode = antiCode;
	}

	public String getAntiName() {
		return this.antiName;
	}

	public void setAntiName(String antiName) {
		this.antiName = antiName;
	}

	public String getYaominResult() {
		return this.yaominResult;
	}

	public void setYaominResult(String yaominResult) {
		this.yaominResult = yaominResult;
	}

	public String getTestResult() {
		return this.testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getReferRange() {
		return this.referRange;
	}

	public void setReferRange(String referRange) {
		this.referRange = referRange;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPathogenSn() {
		return this.pathogenSn;
	}

	public void setPathogenSn(String pathogenSn) {
		this.pathogenSn = pathogenSn;
	}

	@JsonSerialize(using = d.class)
	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@JsonSerialize(using = d.class)
	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getInfectDiagnId() {
		return this.infectDiagnId;
	}

	public void setInfectDiagnId(String infectDiagnId) {
		this.infectDiagnId = infectDiagnId;
	}

	public String getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getPathoId() {
		return this.pathoId;
	}

	public void setPathoId(String pathoId) {
		this.pathoId = pathoId;
	}

	@JsonSerialize(using = d.class)
	public Date getSubmiAt() {
		return this.submiAt;
	}

	public void setSubmiAt(Date submiAt) {
		this.submiAt = submiAt;
	}

	public String getDrugId() {
		return this.drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugTypeName() {
		return this.drugTypeName;
	}

	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}

	public String getDb() {
		return this.db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public String getDrugTypeId() {
		return this.drugTypeId;
	}

	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	public String getSubclassId() {
		return this.subclassId;
	}

	public void setSubclassId(String subclassId) {
		this.subclassId = subclassId;
	}

	public String getSubclassName() {
		return this.subclassName;
	}

	public void setSubclassName(String subclassName) {
		this.subclassName = subclassName;
	}

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
}