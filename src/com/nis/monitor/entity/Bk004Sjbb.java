package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.bd;
import com.nis.comm.enums.y;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("bk004Sjbb")
public class Bk004Sjbb extends BaseEntity implements Serializable {
	private String id;
	private String cardRelid;
	private String refid;
	private Integer sn;
	private String patientId;
	private String zyid;
	private Integer visitId;
	private String patientName;
	private String sex;
	private String age;
	private String testNo;
	private Integer testTypeId;
	private String testTypeName;
	private Date submiAt;
	private String sampleId;
	private String sampleName;
	private String pathoId;
	private String pathoName;
	private String memo;
	private Date createDate;
	private Integer isSelect;
	private String submiDeptId;
	private Long operId;
	private String pathogenSn;
	private List<String> testNoNotIn;
	private String infectDeptname;
	private Date infectDate;
	private Integer infectType;
	private Date testDate;
	private String infectTypeName;
	private String st9Id;
	private String submiAtStr;
	private Integer resProp;
	private String resPropName;
	private String infectPartId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardRelid() {
		return this.cardRelid;
	}

	public void setCardRelid(String cardRelid) {
		this.cardRelid = cardRelid;
	}

	public String getRefid() {
		return this.refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
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

	public String getTestNo() {
		return this.testNo;
	}

	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}

	public Integer getTestTypeId() {
		return this.testTypeId;
	}

	public void setTestTypeId(Integer testTypeId) {
		this.testTypeId = testTypeId;
	}

	public String getTestTypeName() {
		return this.testTypeName;
	}

	public void setTestTypeName(String testTypeName) {
		this.testTypeName = testTypeName;
	}

	public Date getSubmiAt() {
		return this.submiAt;
	}

	public void setSubmiAt(Date submiAt) {
		this.submiAt = submiAt;
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

	public String getPathoId() {
		return this.pathoId;
	}

	public void setPathoId(String pathoId) {
		this.pathoId = pathoId;
	}

	public String getPathoName() {
		return this.pathoName;
	}

	public void setPathoName(String pathoName) {
		this.pathoName = pathoName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsSelect() {
		return this.isSelect;
	}

	public void setIsSelect(Integer isSelect) {
		this.isSelect = isSelect;
	}

	public String getSubmiDeptId() {
		return this.submiDeptId;
	}

	public void setSubmiDeptId(String submiDeptId) {
		this.submiDeptId = submiDeptId;
	}

	public Long getOperId() {
		return this.operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	public String getPathogenSn() {
		return this.pathogenSn;
	}

	public void setPathogenSn(String pathogenSn) {
		this.pathogenSn = pathogenSn;
	}

	public List<String> getTestNoNotIn() {
		return this.testNoNotIn;
	}

	public void setTestNoNotIn(List<String> testNoNotIn) {
		this.testNoNotIn = testNoNotIn;
	}

	public String getInfectDeptname() {
		return this.infectDeptname;
	}

	public void setInfectDeptname(String infectDeptname) {
		this.infectDeptname = infectDeptname;
	}

	public Date getInfectDate() {
		return this.infectDate;
	}

	public void setInfectDate(Date infectDate) {
		this.infectDate = infectDate;
	}

	public Integer getInfectType() {
		return this.infectType;
	}

	public void setInfectType(Integer infectType) {
		this.infectType = infectType;
	}

	public Date getTestDate() {
		return this.testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getInfectTypeName() {
		return y.h(this.infectType).getShortName();
	}

	public void setInfectTypeName(String infectTypeName) {
		this.infectTypeName = infectTypeName;
	}

	public String getSt9Id() {
		return this.st9Id;
	}

	public void setSt9Id(String st9Id) {
		this.st9Id = st9Id;
	}

	public String getSubmiAtStr() {
		return this.submiAtStr;
	}

	public void setSubmiAtStr(String submiAtStr) {
		this.submiAtStr = submiAtStr;
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

	public String getInfectPartId() {
		return this.infectPartId;
	}

	public void setInfectPartId(String infectPartId) {
		this.infectPartId = infectPartId;
	}
}