package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("qsSurveyResult")
public class QsSurveyResult extends BaseEntity implements Serializable {
	private String id;
	private String rid;
	private String qid;
	private String tid;
	private String optId;
	private String optName;
	private String inputValue;
	private String remark;
	private String ext1;
	private String ext2;
	private String fbStatus;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRid() {
		return this.rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getQid() {
		return this.qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getOptId() {
		return this.optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return this.optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getInputValue() {
		return this.inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return this.ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getFbStatus() {
		return this.fbStatus;
	}

	public void setFbStatus(String fbStatus) {
		this.fbStatus = fbStatus;
	}
}