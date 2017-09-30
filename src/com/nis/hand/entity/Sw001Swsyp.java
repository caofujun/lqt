package com.nis.hand.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.c;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("sw001Swsyp")
public class Sw001Swsyp extends BaseEntity implements Serializable {
	private String id;
	private Date reportDate;
	private String reportUserId;
	private String reportUserName;
	private String reportDeptId;
	private String reportDeptName;
	private Integer type;
	private Integer specification;
	private Integer thisMonthUsed;
	private Integer thisMonthInventory;
	private Date addDate;
	private Integer lastMonthRemain;
	private Integer thisMonthGet;
	private String typeName;
	private String specificaUnit;
	private String usedUnit;
	private String inventoryUnit;
	private List<Sw001Swsyp> sw001List;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonSerialize(using = c.class)
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportUserId() {
		return this.reportUserId;
	}

	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}

	public String getReportUserName() {
		return this.reportUserName;
	}

	public void setReportUserName(String reportUserName) {
		this.reportUserName = reportUserName;
	}

	public String getReportDeptId() {
		return this.reportDeptId;
	}

	public void setReportDeptId(String reportDeptId) {
		this.reportDeptId = reportDeptId;
	}

	public String getReportDeptName() {
		return this.reportDeptName;
	}

	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSpecification() {
		return this.specification;
	}

	public void setSpecification(Integer specification) {
		this.specification = specification;
	}

	public Integer getThisMonthUsed() {
		return this.thisMonthUsed;
	}

	public void setThisMonthUsed(Integer thisMonthUsed) {
		this.thisMonthUsed = thisMonthUsed;
	}

	public Integer getThisMonthInventory() {
		return this.thisMonthInventory;
	}

	public void setThisMonthInventory(Integer thisMonthInventory) {
		this.thisMonthInventory = thisMonthInventory;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSpecificaUnit() {
		return this.specificaUnit;
	}

	public void setSpecificaUnit(String specificaUnit) {
		this.specificaUnit = specificaUnit;
	}

	public String getUsedUnit() {
		return this.usedUnit;
	}

	public void setUsedUnit(String usedUnit) {
		this.usedUnit = usedUnit;
	}

	public String getInventoryUnit() {
		return this.inventoryUnit;
	}

	public void setInventoryUnit(String inventoryUnit) {
		this.inventoryUnit = inventoryUnit;
	}

	public List<Sw001Swsyp> getSw001List() {
		return this.sw001List;
	}

	public void setSw001List(List<Sw001Swsyp> sw001List) {
		this.sw001List = sw001List;
	}

	public Integer getLastMonthRemain() {
		return this.lastMonthRemain;
	}

	public void setLastMonthRemain(Integer lastMonthRemain) {
		this.lastMonthRemain = lastMonthRemain;
	}

	public Integer getThisMonthGet() {
		return this.thisMonthGet;
	}

	public void setThisMonthGet(Integer thisMonthGet) {
		this.thisMonthGet = thisMonthGet;
	}
}