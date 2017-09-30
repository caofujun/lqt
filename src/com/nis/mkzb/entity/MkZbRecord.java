package com.nis.mkzb.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.d;
import com.nis.comm.utils.f;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("mkZbRecord")
public class MkZbRecord extends BaseEntity implements Serializable {
	private String id;
	private Long reportStartYear;
	private Long reportStartMonth;
	private Date startDate;
	private Long reportEndYear;
	private Long reportEndMonth;
	private Date endDate;
	private Long reportAmount;
	private Long unReportAmount;
	private String itemCode;
	private String itemName;
	private String userId;
	private String userName;
	private Date reportDate;
	private String reportResult;
	private String reportStatus;
	private String reportProgress;
	private List<String> zyidsW;
	private List<String> zyidsY;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getReportStartYear() {
		return this.reportStartYear;
	}

	public void setReportStartYear(Long reportStartYear) {
		this.reportStartYear = reportStartYear;
	}

	public Long getReportStartMonth() {
		return this.reportStartMonth;
	}

	public void setReportStartMonth(Long reportStartMonth) {
		this.reportStartMonth = reportStartMonth;
	}

	public Long getReportEndYear() {
		return this.reportEndYear;
	}

	public void setReportEndYear(Long reportEndYear) {
		this.reportEndYear = reportEndYear;
	}

	public Long getReportEndMonth() {
		return this.reportEndMonth;
	}

	public void setReportEndMonth(Long reportEndMonth) {
		this.reportEndMonth = reportEndMonth;
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

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonSerialize(using = d.class)
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportResult() {
		return this.reportResult;
	}

	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}

	public String getReportStatus() {
		return this.reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Long getReportAmount() {
		return this.reportAmount;
	}

	public void setReportAmount(Long reportAmount) {
		this.reportAmount = reportAmount;
	}

	public Long getUnReportAmount() {
		return this.unReportAmount;
	}

	public void setUnReportAmount(Long unReportAmount) {
		this.unReportAmount = unReportAmount;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate() throws ParseException {
		String timeTemp = this.reportStartYear.intValue() + "-" + this.reportStartMonth.intValue() + "-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.startDate = sdf.parse(timeTemp);
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate() {
		String timeTemp = this.reportEndYear.intValue() + "-" + this.reportEndMonth.intValue() + "-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			this.endDate = sdf.parse(timeTemp);
		} catch (ParseException arg3) {
			arg3.printStackTrace();
		}

		this.endDate = f.b(this.endDate, true);
	}

	public String toString() {
		return "ZbRecord [id=" + this.id + ", reportStartYear=" + this.reportStartYear + ", reportStartMonth="
				+ this.reportStartMonth + ", reportEndYear=" + this.reportEndYear + ", reportEndMonth="
				+ this.reportEndMonth + ", reportAmount=" + this.reportAmount + ", unReportAmount="
				+ this.unReportAmount + ", itemCode=" + this.itemCode + ", itemName=" + this.itemName + ", userId="
				+ this.userId + ", userName=" + this.userName + ", reportDate=" + this.reportDate + ", reportResult="
				+ this.reportResult + ", reportStatus=" + this.reportStatus + "]";
	}

	public String getReportProgress() {
		return this.reportProgress;
	}

	public void setReportProgress(String reportProgress) {
		this.reportProgress = reportProgress;
	}

	public List<String> getZyidsW() {
		return this.zyidsW;
	}

	public void setZyidsW(List<String> zyidsW) {
		this.zyidsW = zyidsW;
	}

	public List<String> getZyidsY() {
		return this.zyidsY;
	}

	public void setZyidsY(List<String> zyidsY) {
		this.zyidsY = zyidsY;
	}
}