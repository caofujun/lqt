package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.ae;
import java.io.Serializable;
import java.util.Date;

public class SysJudgeLog extends BaseEntity implements Serializable {
	private String id;
	private String judgeCode;
	private String judgeName;
	private Date startTime;
	private Integer successCount;
	private Integer failCount;
	private Integer totalCount;
	private Date endTime;
	private String status;
	private String statusName;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJudgeCode() {
		return this.judgeCode;
	}

	public void setJudgeCode(String judgeCode) {
		this.judgeCode = judgeCode;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getSuccessCount() {
		return this.successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getFailCount() {
		return this.failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public Integer getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getJudgeName() {
		return ae.D(this.judgeCode).getName();
	}

	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}