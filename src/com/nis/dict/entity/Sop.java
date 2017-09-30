package com.nis.dict.entity;

import com.nis.comm.constants.b;
import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

@Alias("sop")
public class Sop extends BaseEntity implements Serializable {
	private String fileId;
	private String pFileId;
	private String fileTitle;
	private String filePath;
	private byte[] fileData;
	private String showFileData;
	private String fileName;
	private String wbCode;
	private String spCode;
	private String bhCode;
	private Date lastAt;
	private String flag;
	private String url;
	private String pFileName;

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getpFileId() {
		return this.pFileId;
	}

	public void setpFileId(String pFileId) {
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

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getpFileName() {
		return this.pFileName;
	}

	public void setpFileName(String pFileName) {
		this.pFileName = pFileName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return this.fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;

		try {
			if (fileData != null) {
				this.showFileData = new String(fileData, b.eA);
			}
		} catch (UnsupportedEncodingException arg2) {
			arg2.printStackTrace();
		}

	}

	public String getShowFileData() {
		return this.showFileData;
	}

	public void setShowFileData(String showFileData) {
		this.showFileData = showFileData;

		try {
			if (StringUtils.isBlank(showFileData)) {
				this.fileData = null;
			} else {
				this.fileData = showFileData.getBytes(b.eA);
			}
		} catch (UnsupportedEncodingException arg2) {
			arg2.printStackTrace();
		}

	}

	public String toString() {
		return "Sop [fileId=" + this.fileId + ", pFileId=" + this.pFileId + ", fileTitle=" + this.fileTitle
				+ ", filePath=" + this.filePath + ", showfileData=" + this.showFileData + ", fileName=" + this.fileName
				+ ", wbCode=" + this.wbCode + ", spCode=" + this.spCode + ", bhCode=" + this.bhCode + ", lastAt="
				+ this.lastAt + ", flag=" + this.flag + ", url=" + this.url + ", pFileName=" + this.pFileName + "]";
	}
}