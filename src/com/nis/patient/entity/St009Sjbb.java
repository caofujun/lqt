package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.bd;
import com.nis.comm.serializer.c;
import com.nis.comm.serializer.d;
import com.nis.comm.utils.ab;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("st009Sjbb")
public class St009Sjbb extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String mzid;
	private String patientId;
	private Integer visitId;
	private String patientName;
	private String testOrderNo;
	private String sex;
	private String age;
	private String ageUnit;
	private String hospId;
	private Integer itemType;
	private String itemTypeName;
	private String itemCode;
	private String itemName;
	private Date submiAt;
	private String deptId;
	private String deptName;
	private Date createAt;
	private Date updDate;
	private Double cgjySpeFlag;
	private Integer sjbbAnalFlag;
	private Date sjbbAnalDt;
	private Integer likeFlag;
	private String checkOutAt;
	private Date testDate;
	private String pathoCode;
	private String pathoName;
	private Integer resProp;
	private List<String> testOrderNoList;
	private String resPropName;
	private String specDescribes;
	private String refid;
	private String infectPartId;
	private Integer isyang;
	private Integer isexception;
	private Integer cdcanalflag;
	private Date cdcanaldt;
	private List<String> remark;
	private String mzzyid;
	private String patientType;
	private String orderBy;
	private String pathogenSn;
	private String orderno;
	private String surveyDeptId;
	private String infectTypeId;
	private String infectTypeName;

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

	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getSurveyDeptId() {
		return this.surveyDeptId;
	}

	public void setSurveyDeptId(String surveyDeptId) {
		this.surveyDeptId = surveyDeptId;
	}

	public String getMzzyid() {
		return this.mzzyid;
	}

	public void setMzzyid(String mzzyid) {
		this.mzzyid = mzzyid;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public Integer getCdcanalflag() {
		return this.cdcanalflag;
	}

	public void setCdcanalflag(Integer cdcanalflag) {
		this.cdcanalflag = cdcanalflag;
	}

	public Date getCdcanaldt() {
		return this.cdcanaldt;
	}

	public void setCdcanaldt(Date cdcanaldt) {
		this.cdcanaldt = cdcanaldt;
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

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public Integer getItemType() {
		return this.itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public String getItemTypeName() {
		return this.itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@JsonSerialize(using = c.class)
	public Date getSubmiAt() {
		return this.submiAt;
	}

	public void setSubmiAt(Date submiAt) {
		this.submiAt = submiAt;
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

	public Double getCgjySpeFlag() {
		return this.cgjySpeFlag;
	}

	public void setCgjySpeFlag(Double cgjySpeFlag) {
		this.cgjySpeFlag = cgjySpeFlag;
	}

	public Integer getSjbbAnalFlag() {
		return this.sjbbAnalFlag;
	}

	public void setSjbbAnalFlag(Integer sjbbAnalFlag) {
		this.sjbbAnalFlag = sjbbAnalFlag;
	}

	@JsonSerialize(using = d.class)
	public Date getSjbbAnalDt() {
		return this.sjbbAnalDt;
	}

	public void setSjbbAnalDt(Date sjbbAnalDt) {
		this.sjbbAnalDt = sjbbAnalDt;
	}

	public String getCheckOutAt() {
		if (ab.isNotEmpty(this.checkOutAt) && this.checkOutAt.length() > 9) {
			this.checkOutAt = this.checkOutAt.substring(0, 10);
		}

		return this.checkOutAt;
	}

	public void setCheckOutAt(String checkOutAt) {
		this.checkOutAt = checkOutAt;
	}

	@JsonSerialize(using = c.class)
	public Date getTestDate() {
		return this.testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
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

	public Integer getResProp() {
		return this.resProp;
	}

	public void setResProp(Integer resProp) {
		this.resProp = resProp;
	}

	public String getResPropName() {
		return bd.o(this.resProp).getName();
	}

	public void setResPropName(String resPropName) {
		this.resPropName = resPropName;
	}

	public String getSpecDescribes() {
		return this.specDescribes;
	}

	public void setSpecDescribes(String specDescribes) {
		this.specDescribes = specDescribes;
	}

	public String getRefid() {
		return this.refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public String getInfectPartId() {
		return this.infectPartId;
	}

	public void setInfectPartId(String infectPartId) {
		this.infectPartId = infectPartId;
	}

	public Integer getIsyang() {
		return this.isyang;
	}

	public void setIsyang(Integer isyang) {
		this.isyang = isyang;
	}

	public Integer getIsexception() {
		return this.isexception;
	}

	public void setIsexception(Integer isexception) {
		this.isexception = isexception;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public List<String> getRemark() {
		return this.remark;
	}

	public void setRemark(List<String> remark) {
		this.remark = remark;
	}

	public String getHospId() {
		return this.hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public Integer getLikeFlag() {
		return this.likeFlag;
	}

	public void setLikeFlag(Integer likeFlag) {
		this.likeFlag = likeFlag;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getPathogenSn() {
		return this.pathogenSn;
	}

	public void setPathogenSn(String pathogenSn) {
		this.pathogenSn = pathogenSn;
	}

	public List<String> getTestOrderNoList() {
		return this.testOrderNoList;
	}

	public void setTestOrderNoList(List<String> testOrderNoList) {
		this.testOrderNoList = testOrderNoList;
	}
}