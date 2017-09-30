package com.nis.msg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class NyMessageDetail extends BaseEntity implements Serializable {
	private String mid;
	private String themeId;
	private String sendUserId;
	private String sendUserName;
	private String sendDeptId;
	private String sendDeptName;
	private Date sendTime;
	private String content;
	private String msgType;
	private String caseId;
	private String msgUserIds;
	private Integer wdCount;
	private Integer qbCount;
	private String pzId;
	private String url;
	private String zyid;
	private String isRead;
	private String mudId;
	private String userId;
	private String mzid;
	private String theme;
	private String acceptUserId;
	private String acceptUserName;
	private String acceptDeptId;
	private String acceptDeptName;

	public String getAcceptDeptId() {
		return this.acceptDeptId;
	}

	public void setAcceptDeptId(String acceptDeptId) {
		this.acceptDeptId = acceptDeptId;
	}

	public String getAcceptUserId() {
		return this.acceptUserId;
	}

	public void setAcceptUserId(String acceptUserId) {
		this.acceptUserId = acceptUserId;
	}

	public String getAcceptUserName() {
		return this.acceptUserName;
	}

	public void setAcceptUserName(String acceptUserName) {
		this.acceptUserName = acceptUserName;
	}

	public String getAcceptDeptName() {
		return this.acceptDeptName;
	}

	public void setAcceptDeptName(String acceptDeptName) {
		this.acceptDeptName = acceptDeptName;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getThemeId() {
		return this.themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}

	public String getSendUserId() {
		return this.sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getSendUserName() {
		return this.sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgUserIds() {
		return this.msgUserIds;
	}

	public void setMsgUserIds(String msgUserIds) {
		this.msgUserIds = msgUserIds;
	}

	public Integer getWdCount() {
		return this.wdCount;
	}

	public void setWdCount(Integer wdCount) {
		this.wdCount = wdCount;
	}

	public Integer getQbCount() {
		return this.qbCount;
	}

	public void setQbCount(Integer qbCount) {
		this.qbCount = qbCount;
	}

	public String getPzId() {
		return this.pzId;
	}

	public void setPzId(String pzId) {
		this.pzId = pzId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getSendDeptId() {
		return this.sendDeptId;
	}

	public void setSendDeptId(String sendDeptId) {
		this.sendDeptId = sendDeptId;
	}

	public String getSendDeptName() {
		return this.sendDeptName;
	}

	public void setSendDeptName(String sendDeptName) {
		this.sendDeptName = sendDeptName;
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getIsRead() {
		return this.isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getMudId() {
		return this.mudId;
	}

	public void setMudId(String mudId) {
		this.mudId = mudId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}