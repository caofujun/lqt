package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.aq;
import com.nis.comm.enums.r;
import com.nis.comm.serializer.d;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("st004Yzxxb")
public class St004Yzxxb extends BaseEntity implements Serializable {
	private String orderDetail;
	private String frequency;
	private Double qtyDay;
	private Double totalQty;
	private String execofficeCode;
	private String execofficeName;
	private String bdocId;
	private String bdocName;
	private String edocId;
	private String edocName;
	private Integer isKjyw;
	private String memo;
	private String isMemo;
	private String firstdayDosage;
	private String lastdayDosage;
	private Integer flagJr;
	private Date updDate;
	private String executeName;
	private Date executeTime;
	private Integer mrcs;
	private Double xhsl;
	private Integer drugLine;
	private String id;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private String patientName;
	private Integer orderType;
	private Date orderAt;
	private Date stopAt;
	private Double usedrugDays;
	private String orderClass;
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
	private Double ypdj;
	private String bnrsId;
	private String bnrsName;
	private String isKjywAna;
	private Date analysisAt;
	private String orderTypeName;
	private List<String> orderTypeIn;
	private String classCode;
	private Date operAt;
	private String operTypeId;
	private String refid;
	private Integer isselect;
	private String relid;
	private String dateSection;
	private String preYymd;
	private String szyzjyyewzj;
	private String drugLineName;
	private String days;
	private String isCgException;
	private String[] yzId;
	private String yymd;
	private String yzIdStr;
	private String panelId;
	private String orderBy;

	public String getOrderDetail() {
		return this.orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
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

	public Integer getIsKjyw() {
		return this.isKjyw;
	}

	public void setIsKjyw(Integer isKjyw) {
		this.isKjyw = isKjyw;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFirstdayDosage() {
		return this.firstdayDosage;
	}

	public void setFirstdayDosage(String firstdayDosage) {
		this.firstdayDosage = firstdayDosage;
	}

	public String getLastdayDosage() {
		return this.lastdayDosage;
	}

	public void setLastdayDosage(String lastdayDosage) {
		this.lastdayDosage = lastdayDosage;
	}

	public Integer getFlagJr() {
		return this.flagJr;
	}

	public void setFlagJr(Integer flagJr) {
		this.flagJr = flagJr;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getExecuteName() {
		return this.executeName;
	}

	public void setExecuteName(String executeName) {
		this.executeName = executeName;
	}

	@JsonSerialize(using = d.class)
	public Date getExecuteTime() {
		return this.executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public Integer getMrcs() {
		return this.mrcs;
	}

	public void setMrcs(Integer mrcs) {
		this.mrcs = mrcs;
	}

	public Double getXhsl() {
		return this.xhsl;
	}

	public void setXhsl(Double xhsl) {
		this.xhsl = xhsl;
	}

	public Integer getDrugLine() {
		return this.drugLine;
	}

	public void setDrugLine(Integer drugLine) {
		this.drugLine = drugLine;
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

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	@JsonSerialize(using = d.class)
	public Date getOrderAt() {
		return this.orderAt;
	}

	public void setOrderAt(Date orderAt) {
		this.orderAt = orderAt;
	}

	@JsonSerialize(using = d.class)
	public Date getStopAt() {
		return this.stopAt;
	}

	public void setStopAt(Date stopAt) {
		this.stopAt = stopAt;
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

	public Double getYpdj() {
		return this.ypdj;
	}

	public void setYpdj(Double ypdj) {
		this.ypdj = ypdj;
	}

	public String getOrderTypeName() {
		return aq.k(this.orderType).getName();
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public List<String> getOrderTypeIn() {
		return this.orderTypeIn;
	}

	public void setOrderTypeIn(List<String> orderTypeIn) {
		this.orderTypeIn = orderTypeIn;
	}

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Date getOperAt() {
		return this.operAt;
	}

	public void setOperAt(Date operAt) {
		this.operAt = operAt;
	}

	public String getOperTypeId() {
		return this.operTypeId;
	}

	public void setOperTypeId(String operTypeId) {
		this.operTypeId = operTypeId;
	}

	public String getRefid() {
		return this.refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public Integer getIsselect() {
		return this.isselect;
	}

	public void setIsselect(Integer isselect) {
		this.isselect = isselect;
	}

	public String getRelid() {
		return this.relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getDateSection() {
		return this.dateSection;
	}

	public void setDateSection(String dateSection) {
		this.dateSection = dateSection;
	}

	public String getDrugLineName() {
		return r.d(this.drugLine).getName();
	}

	public void setDrugLineName(String drugLineName) {
		this.drugLineName = drugLineName;
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

	public String getPreYymd() {
		return this.preYymd;
	}

	public void setPreYymd(String preYymd) {
		this.preYymd = preYymd;
	}

	public String getSzyzjyyewzj() {
		return this.szyzjyyewzj;
	}

	public void setSzyzjyyewzj(String szyzjyyewzj) {
		this.szyzjyyewzj = szyzjyyewzj;
	}

	public String getDays() {
		return this.days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getIsCgException() {
		return this.isCgException;
	}

	public void setIsCgException(String isCgException) {
		this.isCgException = isCgException;
	}

	public String getIsKjywAna() {
		return this.isKjywAna;
	}

	public void setIsKjywAna(String isKjywAna) {
		this.isKjywAna = isKjywAna;
	}

	public Date getAnalysisAt() {
		return this.analysisAt;
	}

	public void setAnalysisAt(Date analysisAt) {
		this.analysisAt = analysisAt;
	}

	public String[] getYzId() {
		return this.yzId;
	}

	public void setYzId(String[] yzId) {
		this.yzId = yzId;
	}

	public String getYymd() {
		return this.yymd;
	}

	public void setYymd(String yymd) {
		this.yymd = yymd;
	}

	public String getYzIdStr() {
		return this.yzIdStr;
	}

	public void setYzIdStr(String yzIdStr) {
		this.yzIdStr = yzIdStr;
	}

	public String getPanelId() {
		return this.panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getIsMemo() {
		return this.isMemo;
	}

	public void setIsMemo(String isMemo) {
		this.isMemo = isMemo;
	}
}