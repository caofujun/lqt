package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.bd;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("st010Jcbyt")
public class St010Jcbyt extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String mzid;
	private String patientId;
	private Double visitId;
	private String testOrderNo;
	private Date testDate;
	private Integer isyang;
	private String pathoCode;
	private String pathoName;
	private Integer isym;
	private String yaominNo;
	private String pathogenSn;
	private String memo;
	private Date createAt;
	private Date updDate;
	private Integer calculateTag;
	private Date calculateDt;
	private String bacteriaAmount;
	private Integer resProp;
	private String resPropName;
	private String specDescribes;
	private String esbl;

	public String getEsbl() {
		return this.esbl;
	}

	public void setEsbl(String esbl) {
		this.esbl = esbl;
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

	public Double getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Double visitId) {
		this.visitId = visitId;
	}

	public String getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public Date getTestDate() {
		return this.testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Integer getIsyang() {
		return this.isyang;
	}

	public void setIsyang(Integer isyang) {
		this.isyang = isyang;
	}

	public String getPathoCode() {
		return this.pathoCode;
	}

	public void setPathoCode(String pathoCode) {
		this.pathoCode = pathoCode;
	}

	public String getPathoName() {
		return this.pathoName;
	}

	public void setPathoName(String pathoName) {
		this.pathoName = pathoName;
	}

	public Integer getIsym() {
		return this.isym;
	}

	public void setIsym(Integer isym) {
		this.isym = isym;
	}

	public String getYaominNo() {
		return this.yaominNo;
	}

	public void setYaominNo(String yaominNo) {
		this.yaominNo = yaominNo;
	}

	public String getPathogenSn() {
		return this.pathogenSn;
	}

	public void setPathogenSn(String pathogenSn) {
		this.pathogenSn = pathogenSn;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public Integer getCalculateTag() {
		return this.calculateTag;
	}

	public void setCalculateTag(Integer calculateTag) {
		this.calculateTag = calculateTag;
	}

	public Date getCalculateDt() {
		return this.calculateDt;
	}

	public void setCalculateDt(Date calculateDt) {
		this.calculateDt = calculateDt;
	}

	public String getBacteriaAmount() {
		return this.bacteriaAmount;
	}

	public void setBacteriaAmount(String bacteriaAmount) {
		this.bacteriaAmount = bacteriaAmount;
	}

	public Integer getResProp() {
		return this.resProp;
	}

	public void setResProp(Integer resProp) {
		this.resProp = resProp;
	}

	public String getSpecDescribes() {
		return this.specDescribes;
	}

	public void setSpecDescribes(String specDescribes) {
		this.specDescribes = specDescribes;
	}

	public String getResPropName() {
		return bd.o(this.resProp).getName();
	}

	public void setResPropName(String resPropName) {
		this.resPropName = resPropName;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}
}