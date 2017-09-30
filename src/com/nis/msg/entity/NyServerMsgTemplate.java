package com.nis.msg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("nyServerMsgTemplate")
public class NyServerMsgTemplate extends BaseEntity implements Serializable {
	private String id;
	private String title;
	private String spCode;
	private byte[] content;
	private String contentStr;
	private Date createAt;
	private String createOper;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;

		try {
			this.contentStr = new String(content, "utf-8");
		} catch (UnsupportedEncodingException arg2) {
			arg2.printStackTrace();
		}

	}

	public String getContentStr() {
		return this.contentStr;
	}

	public void setContentStr(String contentStr) {
		try {
			this.content = contentStr.getBytes("utf-8");
		} catch (UnsupportedEncodingException arg2) {
			arg2.printStackTrace();
		}

		this.contentStr = contentStr;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getCreateOper() {
		return this.createOper;
	}

	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}

	public String toString() {
		return "NyServerMsgTemplate [id=" + this.id + ", title=" + this.title + ", spCode=" + this.spCode + ", content="
				+ Arrays.toString(this.content) + ", contentStr=" + this.contentStr + ", createAt=" + this.createAt
				+ ", createOper=" + this.createOper + "]";
	}
}