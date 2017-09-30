package com.nis.outbreak.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.az;
import com.nis.comm.enums.i;
import com.nis.comm.serializer.c;
import com.nis.comm.serializer.d;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("by007Bfjl")
public class By007Bfjl extends BaseEntity implements Serializable {
	private String id;
	private String showId;
	private Integer auditFlag;
	private Date breakStartDate;
	private Date breakEndDate;
	private Integer breakCount;
	private Date createDate;
	private Date moniDate;
	private String deptId;
	private Integer breakType;
	private Integer readFlag;
	private String auditId;
	private Date auditDate;
	private Double line;
	private Double observeLine;
	private Double mulriple;
	private String observeDay;
	private Integer isWarn;
	private String auditName;
	private String deptName;
	private String typeName;
	private String memo;
	private String readFlagName;
	private String breakTypeName;
	private String typeId;
	private String absoluteDetailName;
	private String grade;
	private Integer typeCount;
	private Integer deptCount;
	private String auditFlagStr;
	private List<String> idList;
	private List<String> auditFlagList;
	private String name;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShowId() {
		return this.showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public Integer getAuditFlag() {
		return this.auditFlag;
	}

	public void setAuditFlag(Integer auditFlag) {
		this.auditFlag = auditFlag;
	}

	@JsonSerialize(using = c.class)
	public Date getBreakStartDate() {
		return this.breakStartDate;
	}

	public void setBreakStartDate(Date breakStartDate) {
		this.breakStartDate = breakStartDate;
	}

	@JsonSerialize(using = c.class)
	public Date getBreakEndDate() {
		return this.breakEndDate;
	}

	public void setBreakEndDate(Date breakEndDate) {
		this.breakEndDate = breakEndDate;
	}

	public Integer getBreakCount() {
		return this.breakCount;
	}

	public void setBreakCount(Integer breakCount) {
		this.breakCount = breakCount;
	}

	@JsonSerialize(using = d.class)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonSerialize(using = c.class)
	public Date getMoniDate() {
		return this.moniDate;
	}

	public void setMoniDate(Date moniDate) {
		this.moniDate = moniDate;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Integer getBreakType() {
		return this.breakType;
	}

	public void setBreakType(Integer breakType) {
		this.breakType = breakType;
	}

	public Integer getReadFlag() {
		return this.readFlag;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}

	public String getAuditId() {
		return this.auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	@JsonSerialize(using = d.class)
	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Double getLine() {
		return this.line;
	}

	public void setLine(Double line) {
		this.line = line;
	}

	public Double getObserveLine() {
		return this.observeLine;
	}

	public void setObserveLine(Double observeLine) {
		this.observeLine = observeLine;
	}

	public Double getMulriple() {
		return this.mulriple;
	}

	public void setMulriple(Double mulriple) {
		this.mulriple = mulriple;
	}

	public String getObserveDay() {
		return this.observeDay;
	}

	public void setObserveDay(String observeDay) {
		this.observeDay = observeDay;
	}

	public Integer getIsWarn() {
		return this.isWarn;
	}

	public void setIsWarn(Integer isWarn) {
		this.isWarn = isWarn;
	}

	public String getAuditName() {
		return this.auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getReadFlagName() {
		return az.l(this.readFlag).getName();
	}

	public void setReadFlagName(String readFlagName) {
		this.readFlagName = readFlagName;
	}

	public String getBreakTypeName() {
		return i.c(this.breakType).getName();
	}

	public void setBreakTypeName(String breakTypeName) {
		this.breakTypeName = breakTypeName;
	}

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getTypeCount() {
		return this.typeCount;
	}

	public void setTypeCount(Integer typeCount) {
		this.typeCount = typeCount;
	}

	public Integer getDeptCount() {
		return this.deptCount;
	}

	public void setDeptCount(Integer deptCount) {
		this.deptCount = deptCount;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<String> getIdList() {
		return this.idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

	public List<String> getAuditFlagList() {
		return this.auditFlagList;
	}

	public void setAuditFlagList(List<String> auditFlagList) {
		this.auditFlagList = auditFlagList;
	}

	public String getAuditFlagStr() {
		return this.auditFlagStr;
	}

	public void setAuditFlagStr(String auditFlagStr) {
		this.auditFlagStr = auditFlagStr;
	}

	public String getAbsoluteDetailName() {
		return this.absoluteDetailName;
	}

	public void setAbsoluteDetailName(String absoluteDetailName) {
		this.absoluteDetailName = absoluteDetailName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}