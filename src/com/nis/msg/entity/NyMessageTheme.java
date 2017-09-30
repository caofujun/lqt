package com.nis.msg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class NyMessageTheme extends BaseEntity implements Serializable {
	private String themeId;
	private String theme;
	private String createUser;
	private Date createTime;
	private String zyid;
	private String mzid;
	private String lastMid;
	private String startDate;
	private String endDate;
	private String patientId;
	private String patientName;
	private String deptName;
	private String zbNames;
	private Date lastSendTime;
	private String lastSendUserId;
	private String lastSendUser;
	private String lastContent;
	private Long msgCount;
	private Long msgUserUnReadCount;
	private String userId;
	private String url;
	private String isRead;
	private String msgType;
	private String uid;

	public String getThemeId() {
		return this.themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getZbNames() {
		return this.zbNames;
	}

	public void setZbNames(String zbNames) {
		this.zbNames = zbNames;
	}

	public Date getLastSendTime() {
		return this.lastSendTime;
	}

	public void setLastSendTime(Date lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public String getLastSendUser() {
		return this.lastSendUser;
	}

	public void setLastSendUser(String lastSendUser) {
		this.lastSendUser = lastSendUser;
	}

	public String getLastContent() {
		return this.lastContent;
	}

	public void setLastContent(String lastContent) {
		this.lastContent = lastContent;
	}

	public String getLastMid() {
		return this.lastMid;
	}

	public void setLastMid(String lastMid) {
		this.lastMid = lastMid;
	}

	public Long getMsgCount() {
		return this.msgCount;
	}

	public void setMsgCount(Long msgCount) {
		this.msgCount = msgCount;
	}

	public Long getMsgUserUnReadCount() {
		return this.msgUserUnReadCount;
	}

	public void setMsgUserUnReadCount(Long msgUserUnReadCount) {
		this.msgUserUnReadCount = msgUserUnReadCount;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String toString() {
		return "NyMessageTheme [themeId=" + this.themeId + ", theme=" + this.theme + ", createUser=" + this.createUser
				+ ", createTime=" + this.createTime + ", zyid=" + this.zyid + ", lastMid=" + this.lastMid
				+ ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", patientId=" + this.patientId
				+ ", patientName=" + this.patientName + ", deptName=" + this.deptName + ", zbNames=" + this.zbNames
				+ ", lastSendTime=" + this.lastSendTime + ", lastSendUser=" + this.lastSendUser + ", lastContent="
				+ this.lastContent + ", msgCount=" + this.msgCount + ", msgUserUnReadCount=" + this.msgUserUnReadCount
				+ ", userId=" + this.userId + "]";
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsRead() {
		return this.isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getLastSendUserId() {
		return this.lastSendUserId;
	}

	public void setLastSendUserId(String lastSendUserId) {
		this.lastSendUserId = lastSendUserId;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}
}