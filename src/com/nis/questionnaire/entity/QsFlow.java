package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.utils.f;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("qsFlow")
public class QsFlow extends BaseEntity implements Serializable {
	private String fid;
	private String optId;
	private String createUser;
	private Date createTime;
	private String unitId;
	private String fName;
	private Date endDate;
	private String fInfo;
	private String status;
	private String publish;
	private String ext1;
	private String endTime;

	public String getFid() {
		return this.fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getOptId() {
		return this.optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
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

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getfName() {
		return this.fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getfInfo() {
		return this.fInfo;
	}

	public void setfInfo(String fInfo) {
		this.fInfo = fInfo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPublish() {
		return this.publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getEndTime() {
		return f.formatDate(this.getEndDate());
	}

	public void setEndTime(String endTime) {
		this.endTime = f.formatDate(this.getEndDate());
	}
}