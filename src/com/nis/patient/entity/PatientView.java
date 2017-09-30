package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class PatientView extends BaseEntity implements Serializable {
	private Integer day;
	private Date dateTime;
	private String twValues;
	private Integer twValuesCount;
	private Date operAt;
	private String operName;
	private Integer operAtCount;
	private Date mndcgOrderAt;
	private String mndcgOrderName;
	private Integer mndcgOrderAtCount;
	private Date jmcgOrderAt;
	private String jmcgOrderName;
	private Integer jmcgOrderAtCount;
	private Date hxjOrderAt;
	private String hxjOrderName;
	private Integer hxjOrderAtCount;
	private String xcgSubmiAt;
	private String xcgItemName;
	private String xcgTestOrderNo;
	private Integer xcgSubmiAtCount;
	private String ncgSubmiAt;
	private String ncgItemName;
	private String ncgTestOrderNo;
	private Integer ncgSubmiAtCount;
	private String qtcgSubmiAt;
	private String qtcgItemName;
	private String qtcgTestOrderNo;
	private Integer qtcgSubmiAtCount;
	private String jgsySubmiAt;
	private String jgsyItemName;
	private String jgsyTestOrderNo;
	private Integer jgsySubmiAtCount;
	private String cmcfydbSubmiAt;
	private String cmcfydbItemName;
	private String cmcfydbTestOrderNo;
	private Integer cmcfydbSubmiAtCount;
	private String cfydbSubmiAt;
	private String cfydbItemName;
	private String cfydbTestOrderNo;
	private Integer cfydbSubmiAtCount;
	private String yjSubmiAt;
	private String yjItemName;
	private String yjTestOrderNo;
	private Integer yjSubmiAtCount;
	private Date glbsOrderAt;
	private String glbsOrderName;
	private Integer glbsOrderAtCount;

	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getTwValues() {
		return this.twValues;
	}

	public void setTwValues(String twValues) {
		this.twValues = twValues;
	}

	public Integer getTwValuesCount() {
		return this.twValuesCount;
	}

	public void setTwValuesCount(Integer twValuesCount) {
		this.twValuesCount = twValuesCount;
	}

	public Date getOperAt() {
		return this.operAt;
	}

	public void setOperAt(Date operAt) {
		this.operAt = operAt;
	}

	public Integer getOperAtCount() {
		return this.operAtCount;
	}

	public void setOperAtCount(Integer operAtCount) {
		this.operAtCount = operAtCount;
	}

	public Date getMndcgOrderAt() {
		return this.mndcgOrderAt;
	}

	public void setMndcgOrderAt(Date mndcgOrderAt) {
		this.mndcgOrderAt = mndcgOrderAt;
	}

	public Integer getMndcgOrderAtCount() {
		return this.mndcgOrderAtCount;
	}

	public void setMndcgOrderAtCount(Integer mndcgOrderAtCount) {
		this.mndcgOrderAtCount = mndcgOrderAtCount;
	}

	public Date getJmcgOrderAt() {
		return this.jmcgOrderAt;
	}

	public void setJmcgOrderAt(Date jmcgOrderAt) {
		this.jmcgOrderAt = jmcgOrderAt;
	}

	public Integer getJmcgOrderAtCount() {
		return this.jmcgOrderAtCount;
	}

	public void setJmcgOrderAtCount(Integer jmcgOrderAtCount) {
		this.jmcgOrderAtCount = jmcgOrderAtCount;
	}

	public Date getHxjOrderAt() {
		return this.hxjOrderAt;
	}

	public void setHxjOrderAt(Date hxjOrderAt) {
		this.hxjOrderAt = hxjOrderAt;
	}

	public Integer getHxjOrderAtCount() {
		return this.hxjOrderAtCount;
	}

	public void setHxjOrderAtCount(Integer hxjOrderAtCount) {
		this.hxjOrderAtCount = hxjOrderAtCount;
	}

	public String getXcgSubmiAt() {
		return this.xcgSubmiAt;
	}

	public void setXcgSubmiAt(String xcgSubmiAt) {
		this.xcgSubmiAt = xcgSubmiAt;
	}

	public Integer getXcgSubmiAtCount() {
		return this.xcgSubmiAtCount;
	}

	public void setXcgSubmiAtCount(Integer xcgSubmiAtCount) {
		this.xcgSubmiAtCount = xcgSubmiAtCount;
	}

	public String getNcgSubmiAt() {
		return this.ncgSubmiAt;
	}

	public void setNcgSubmiAt(String ncgSubmiAt) {
		this.ncgSubmiAt = ncgSubmiAt;
	}

	public Integer getNcgSubmiAtCount() {
		return this.ncgSubmiAtCount;
	}

	public void setNcgSubmiAtCount(Integer ncgSubmiAtCount) {
		this.ncgSubmiAtCount = ncgSubmiAtCount;
	}

	public String getQtcgSubmiAt() {
		return this.qtcgSubmiAt;
	}

	public void setQtcgSubmiAt(String qtcgSubmiAt) {
		this.qtcgSubmiAt = qtcgSubmiAt;
	}

	public Integer getQtcgSubmiAtCount() {
		return this.qtcgSubmiAtCount;
	}

	public void setQtcgSubmiAtCount(Integer qtcgSubmiAtCount) {
		this.qtcgSubmiAtCount = qtcgSubmiAtCount;
	}

	public String getJgsySubmiAt() {
		return this.jgsySubmiAt;
	}

	public void setJgsySubmiAt(String jgsySubmiAt) {
		this.jgsySubmiAt = jgsySubmiAt;
	}

	public Integer getJgsySubmiAtCount() {
		return this.jgsySubmiAtCount;
	}

	public void setJgsySubmiAtCount(Integer jgsySubmiAtCount) {
		this.jgsySubmiAtCount = jgsySubmiAtCount;
	}

	public String getCmcfydbSubmiAt() {
		return this.cmcfydbSubmiAt;
	}

	public void setCmcfydbSubmiAt(String cmcfydbSubmiAt) {
		this.cmcfydbSubmiAt = cmcfydbSubmiAt;
	}

	public Integer getCmcfydbSubmiAtCount() {
		return this.cmcfydbSubmiAtCount;
	}

	public void setCmcfydbSubmiAtCount(Integer cmcfydbSubmiAtCount) {
		this.cmcfydbSubmiAtCount = cmcfydbSubmiAtCount;
	}

	public String getCfydbSubmiAt() {
		return this.cfydbSubmiAt;
	}

	public void setCfydbSubmiAt(String cfydbSubmiAt) {
		this.cfydbSubmiAt = cfydbSubmiAt;
	}

	public Integer getCfydbSubmiAtCount() {
		return this.cfydbSubmiAtCount;
	}

	public void setCfydbSubmiAtCount(Integer cfydbSubmiAtCount) {
		this.cfydbSubmiAtCount = cfydbSubmiAtCount;
	}

	public String getYjSubmiAt() {
		return this.yjSubmiAt;
	}

	public void setYjSubmiAt(String yjSubmiAt) {
		this.yjSubmiAt = yjSubmiAt;
	}

	public Integer getYjSubmiAtCount() {
		return this.yjSubmiAtCount;
	}

	public void setYjSubmiAtCount(Integer yjSubmiAtCount) {
		this.yjSubmiAtCount = yjSubmiAtCount;
	}

	public String getOperName() {
		return this.operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getMndcgOrderName() {
		return this.mndcgOrderName;
	}

	public void setMndcgOrderName(String mndcgOrderName) {
		this.mndcgOrderName = mndcgOrderName;
	}

	public String getJmcgOrderName() {
		return this.jmcgOrderName;
	}

	public void setJmcgOrderName(String jmcgOrderName) {
		this.jmcgOrderName = jmcgOrderName;
	}

	public String getHxjOrderName() {
		return this.hxjOrderName;
	}

	public void setHxjOrderName(String hxjOrderName) {
		this.hxjOrderName = hxjOrderName;
	}

	public String getXcgItemName() {
		return this.xcgItemName;
	}

	public void setXcgItemName(String xcgItemName) {
		this.xcgItemName = xcgItemName;
	}

	public String getNcgItemName() {
		return this.ncgItemName;
	}

	public void setNcgItemName(String ncgItemName) {
		this.ncgItemName = ncgItemName;
	}

	public String getQtcgItemName() {
		return this.qtcgItemName;
	}

	public void setQtcgItemName(String qtcgItemName) {
		this.qtcgItemName = qtcgItemName;
	}

	public String getJgsyItemName() {
		return this.jgsyItemName;
	}

	public void setJgsyItemName(String jgsyItemName) {
		this.jgsyItemName = jgsyItemName;
	}

	public String getCmcfydbItemName() {
		return this.cmcfydbItemName;
	}

	public void setCmcfydbItemName(String cmcfydbItemName) {
		this.cmcfydbItemName = cmcfydbItemName;
	}

	public String getCfydbItemName() {
		return this.cfydbItemName;
	}

	public void setCfydbItemName(String cfydbItemName) {
		this.cfydbItemName = cfydbItemName;
	}

	public String getYjItemName() {
		return this.yjItemName;
	}

	public void setYjItemName(String yjItemName) {
		this.yjItemName = yjItemName;
	}

	public String getXcgTestOrderNo() {
		return this.xcgTestOrderNo;
	}

	public void setXcgTestOrderNo(String xcgTestOrderNo) {
		this.xcgTestOrderNo = xcgTestOrderNo;
	}

	public String getNcgTestOrderNo() {
		return this.ncgTestOrderNo;
	}

	public void setNcgTestOrderNo(String ncgTestOrderNo) {
		this.ncgTestOrderNo = ncgTestOrderNo;
	}

	public String getQtcgTestOrderNo() {
		return this.qtcgTestOrderNo;
	}

	public void setQtcgTestOrderNo(String qtcgTestOrderNo) {
		this.qtcgTestOrderNo = qtcgTestOrderNo;
	}

	public String getJgsyTestOrderNo() {
		return this.jgsyTestOrderNo;
	}

	public void setJgsyTestOrderNo(String jgsyTestOrderNo) {
		this.jgsyTestOrderNo = jgsyTestOrderNo;
	}

	public String getCmcfydbTestOrderNo() {
		return this.cmcfydbTestOrderNo;
	}

	public void setCmcfydbTestOrderNo(String cmcfydbTestOrderNo) {
		this.cmcfydbTestOrderNo = cmcfydbTestOrderNo;
	}

	public String getCfydbTestOrderNo() {
		return this.cfydbTestOrderNo;
	}

	public void setCfydbTestOrderNo(String cfydbTestOrderNo) {
		this.cfydbTestOrderNo = cfydbTestOrderNo;
	}

	public String getYjTestOrderNo() {
		return this.yjTestOrderNo;
	}

	public void setYjTestOrderNo(String yjTestOrderNo) {
		this.yjTestOrderNo = yjTestOrderNo;
	}

	public Date getGlbsOrderAt() {
		return this.glbsOrderAt;
	}

	public void setGlbsOrderAt(Date glbsOrderAt) {
		this.glbsOrderAt = glbsOrderAt;
	}

	public String getGlbsOrderName() {
		return this.glbsOrderName;
	}

	public void setGlbsOrderName(String glbsOrderName) {
		this.glbsOrderName = glbsOrderName;
	}

	public Integer getGlbsOrderAtCount() {
		return this.glbsOrderAtCount;
	}

	public void setGlbsOrderAtCount(Integer glbsOrderAtCount) {
		this.glbsOrderAtCount = glbsOrderAtCount;
	}

	public String toString() {
		return "PatientView [day=" + this.day + ", dateTime=" + this.dateTime + ", twValues=" + this.twValues
				+ ", twValuesCount=" + this.twValuesCount + ", operAt=" + this.operAt + ", operName=" + this.operName
				+ ", operAtCount=" + this.operAtCount + ", mndcgOrderAt=" + this.mndcgOrderAt + ", mndcgOrderName="
				+ this.mndcgOrderName + ", mndcgOrderAtCount=" + this.mndcgOrderAtCount + ", jmcgOrderAt="
				+ this.jmcgOrderAt + ", jmcgOrderName=" + this.jmcgOrderName + ", jmcgOrderAtCount="
				+ this.jmcgOrderAtCount + ", hxjOrderAt=" + this.hxjOrderAt + ", hxjOrderName=" + this.hxjOrderName
				+ ", hxjOrderAtCount=" + this.hxjOrderAtCount + ", xcgSubmiAt=" + this.xcgSubmiAt + ", xcgItemName="
				+ this.xcgItemName + ", xcgTestOrderNo=" + this.xcgTestOrderNo + ", xcgSubmiAtCount="
				+ this.xcgSubmiAtCount + ", ncgSubmiAt=" + this.ncgSubmiAt + ", ncgItemName=" + this.ncgItemName
				+ ", ncgTestOrderNo=" + this.ncgTestOrderNo + ", ncgSubmiAtCount=" + this.ncgSubmiAtCount
				+ ", qtcgSubmiAt=" + this.qtcgSubmiAt + ", qtcgItemName=" + this.qtcgItemName + ", qtcgTestOrderNo="
				+ this.qtcgTestOrderNo + ", qtcgSubmiAtCount=" + this.qtcgSubmiAtCount + ", jgsySubmiAt="
				+ this.jgsySubmiAt + ", jgsyItemName=" + this.jgsyItemName + ", jgsyTestOrderNo=" + this.jgsyTestOrderNo
				+ ", jgsySubmiAtCount=" + this.jgsySubmiAtCount + ", cmcfydbSubmiAt=" + this.cmcfydbSubmiAt
				+ ", cmcfydbItemName=" + this.cmcfydbItemName + ", cmcfydbTestOrderNo=" + this.cmcfydbTestOrderNo
				+ ", cmcfydbSubmiAtCount=" + this.cmcfydbSubmiAtCount + ", cfydbSubmiAt=" + this.cfydbSubmiAt
				+ ", cfydbItemName=" + this.cfydbItemName + ", cfydbTestOrderNo=" + this.cfydbTestOrderNo
				+ ", cfydbSubmiAtCount=" + this.cfydbSubmiAtCount + ", yjSubmiAt=" + this.yjSubmiAt + ", yjItemName="
				+ this.yjItemName + ", yjTestOrderNo=" + this.yjTestOrderNo + ", yjSubmiAtCount=" + this.yjSubmiAtCount
				+ "]";
	}
}