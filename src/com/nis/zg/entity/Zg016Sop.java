package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Zg016Sop extends BaseEntity implements Serializable {
	private String fileId;
	private String pFileId;
	private String fileTitle;
	private String filePath;
	private String fileName;
	private String fileData;
	private String wbCode;
	private String spCode;
	private Date lastAt;
	private Long flag;
	private String bhCode;
	private String url;
	private String pFileName;

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getPFileId() {
		return this.pFileId;
	}

	public void setPFileId(String pFileId) {
		this.pFileId = pFileId;
	}

	public String getFileTitle() {
		return this.fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileData() {
		return this.fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPFileName() {
		return this.pFileName;
	}

	public void setPFileName(String pFileName) {
		this.pFileName = pFileName;
	}
}