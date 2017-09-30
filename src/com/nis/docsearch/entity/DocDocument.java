package com.nis.docsearch.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("docDocument")
public class DocDocument extends BaseEntity implements Serializable {
	private Integer docScope;
	private String docFlag;
	private String id;
	private String docName;
	private String docDesc;
	private String docType;
	private String docFormat;
	private String docPic;
	private String docRecommeng;
	private String docAnonyrnous;
	private String docUrl;
	private Long docScore;
	private Long docLook;
	private Long docDownload;
	private String createUserid;
	private String createUsername;
	private Date createTime;
	private String updateUserid;
	private String updateUsername;
	private Date updateTime;
	private Long docSize;
	private String userId;
	private String unitId;
	private String docTypeName;
	private Long docDownloadForPeople;
	private String docPreview;
	private List<String> docTypeList;
	private List<String> docFormatList;
	private Long docComplain;
	private String status;
	private String isRepeat;

	public String getDocPreview() {
		return this.docPreview;
	}

	public void setDocPreview(String docPreview) {
		this.docPreview = docPreview;
	}

	public Integer getDocScope() {
		return this.docScope;
	}

	public void setDocScope(Integer docScope) {
		this.docScope = docScope;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocDesc() {
		return this.docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public String getDocType() {
		return this.docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocFormat() {
		return this.docFormat;
	}

	public void setDocFormat(String docFormat) {
		this.docFormat = docFormat;
	}

	public String getDocPic() {
		return this.docPic;
	}

	public void setDocPic(String docPic) {
		this.docPic = docPic;
	}

	public String getDocRecommeng() {
		return this.docRecommeng;
	}

	public void setDocRecommeng(String docRecommeng) {
		this.docRecommeng = docRecommeng;
	}

	public String getDocAnonyrnous() {
		return this.docAnonyrnous;
	}

	public void setDocAnonyrnous(String docAnonyrnous) {
		this.docAnonyrnous = docAnonyrnous;
	}

	public String getDocUrl() {
		return this.docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public Long getDocScore() {
		return this.docScore;
	}

	public void setDocScore(Long docScore) {
		this.docScore = docScore;
	}

	public Long getDocLook() {
		return this.docLook;
	}

	public void setDocLook(Long docLook) {
		this.docLook = docLook;
	}

	public Long getDocDownload() {
		return this.docDownload;
	}

	public void setDocDownload(Long docDownload) {
		this.docDownload = docDownload;
	}

	public String getCreateUserid() {
		return this.createUserid;
	}

	public void setCreateUserid(String createUserid) {
		this.createUserid = createUserid;
	}

	public String getCreateUsername() {
		return this.createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserid() {
		return this.updateUserid;
	}

	public void setUpdateUserid(String updateUserid) {
		this.updateUserid = updateUserid;
	}

	public String getUpdateUsername() {
		return this.updateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getDocSize() {
		return this.docSize;
	}

	public void setDocSize(Long docSize) {
		this.docSize = docSize;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getDocTypeName() {
		return this.docTypeName;
	}

	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	public Long getDocDownloadForPeople() {
		return this.docDownloadForPeople;
	}

	public void setDocDownloadForPeople(Long docDownloadForPeople) {
		this.docDownloadForPeople = docDownloadForPeople;
	}

	public List<String> getDocTypeList() {
		return this.docTypeList;
	}

	public void setDocTypeList(List<String> docTypeList) {
		this.docTypeList = docTypeList;
	}

	public String getDocFlag() {
		return this.docFlag;
	}

	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}

	public Long getDocComplain() {
		return this.docComplain;
	}

	public void setDocComplain(Long docComplain) {
		this.docComplain = docComplain;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getDocFormatList() {
		return this.docFormatList;
	}

	public void setDocFormatList(List<String> docFormatList) {
		this.docFormatList = docFormatList;
	}

	public String getIsRepeat() {
		return this.isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
}