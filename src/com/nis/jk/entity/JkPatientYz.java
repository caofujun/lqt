package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkPatientYz")
public class JkPatientYz extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private String patientName;
	private Integer orderType;
	private Date orderAt;
	private Date stopAt;
	private Date executeTime;
	private String orderId;
	private String orderName;
	private String drugSpeci;
	private Double dosage;
	private String dosageUnit;
	private Integer useCount;
	private String sypc;
	private String adminRouteId;
	private String adminRouteName;
	private String usePurpose;
	private Long frequency;
	private String execofficeCode;
	private String execofficeName;
	private String bdocId;
	private String bdocName;
	private String bnrsId;
	private String bnrsName;
	private String edocId;
	private String edocName;
	private String yzStatus;
	private String memo;
	private Double usedrugDays;
	private String orderClass;
	private Double ypdj;
	private Double qtyDay;
	private Double totalQty;
	private Integer isKjyw;
	private Date updTime;
	private Long updFlag;

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

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getOrderAt() {
		return this.orderAt;
	}

	public void setOrderAt(Date orderAt) {
		this.orderAt = orderAt;
	}

	public Date getStopAt() {
		return this.stopAt;
	}

	public void setStopAt(Date stopAt) {
		this.stopAt = stopAt;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getDrugSpeci() {
		return this.drugSpeci;
	}

	public void setDrugSpeci(String drugSpeci) {
		this.drugSpeci = drugSpeci;
	}

	public Double getDosage() {
		return this.dosage;
	}

	public void setDosage(Double dosage) {
		this.dosage = dosage;
	}

	public String getDosageUnit() {
		return this.dosageUnit;
	}

	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}

	public Integer getUseCount() {
		return this.useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public String getSypc() {
		return this.sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public String getAdminRouteId() {
		return this.adminRouteId;
	}

	public void setAdminRouteId(String adminRouteId) {
		this.adminRouteId = adminRouteId;
	}

	public String getAdminRouteName() {
		return this.adminRouteName;
	}

	public void setAdminRouteName(String adminRouteName) {
		this.adminRouteName = adminRouteName;
	}

	public String getUsePurpose() {
		return this.usePurpose;
	}

	public void setUsePurpose(String usePurpose) {
		this.usePurpose = usePurpose;
	}

	public Long getFrequency() {
		return this.frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	public String getExecofficeCode() {
		return this.execofficeCode;
	}

	public void setExecofficeCode(String execofficeCode) {
		this.execofficeCode = execofficeCode;
	}

	public String getExecofficeName() {
		return this.execofficeName;
	}

	public void setExecofficeName(String execofficeName) {
		this.execofficeName = execofficeName;
	}

	public String getBdocId() {
		return this.bdocId;
	}

	public void setBdocId(String bdocId) {
		this.bdocId = bdocId;
	}

	public String getBdocName() {
		return this.bdocName;
	}

	public void setBdocName(String bdocName) {
		this.bdocName = bdocName;
	}

	public String getBnrsId() {
		return this.bnrsId;
	}

	public void setBnrsId(String bnrsId) {
		this.bnrsId = bnrsId;
	}

	public String getBnrsName() {
		return this.bnrsName;
	}

	public void setBnrsName(String bnrsName) {
		this.bnrsName = bnrsName;
	}

	public String getEdocId() {
		return this.edocId;
	}

	public void setEdocId(String edocId) {
		this.edocId = edocId;
	}

	public String getEdocName() {
		return this.edocName;
	}

	public void setEdocName(String edocName) {
		this.edocName = edocName;
	}

	public String getYzStatus() {
		return this.yzStatus;
	}

	public void setYzStatus(String yzStatus) {
		this.yzStatus = yzStatus;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Double getUsedrugDays() {
		return this.usedrugDays;
	}

	public void setUsedrugDays(Double usedrugDays) {
		this.usedrugDays = usedrugDays;
	}

	public String getOrderClass() {
		return this.orderClass;
	}

	public void setOrderClass(String orderClass) {
		this.orderClass = orderClass;
	}

	public Double getYpdj() {
		return this.ypdj;
	}

	public void setYpdj(Double ypdj) {
		this.ypdj = ypdj;
	}

	public Double getQtyDay() {
		return this.qtyDay;
	}

	public void setQtyDay(Double qtyDay) {
		this.qtyDay = qtyDay;
	}

	public Double getTotalQty() {
		return this.totalQty;
	}

	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}

	public Integer getIsKjyw() {
		return this.isKjyw;
	}

	public void setIsKjyw(Integer isKjyw) {
		this.isKjyw = isKjyw;
	}

	public Date getExecuteTime() {
		return this.executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Long getUpdFlag() {
		return this.updFlag;
	}

	public void setUpdFlag(Long updFlag) {
		this.updFlag = updFlag;
	}
}