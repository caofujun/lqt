package com.nis.msg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class NyMessageUserDetail extends BaseEntity implements Serializable {
	private String mudId;
	private String mid;
	private String userId;
	private String userName;
	private String isRead;
	private String themeId;
	private String content;

	public String getMudId() {
		return this.mudId;
	}

	public void setMudId(String mudId) {
		this.mudId = mudId;
	}

	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
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

	public String getIsRead() {
		return this.isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getThemeId() {
		return this.themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}