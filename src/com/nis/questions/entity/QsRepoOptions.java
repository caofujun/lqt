package com.nis.questions.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("qsRepoOptions")
public class QsRepoOptions extends BaseEntity implements Serializable {
	private String optId;
	private String optName;
	private String allowInput;
	private String optValue;
	private String rtid;
	private String unitId;
	private Long sortNo;
	private Integer status;

	public QsRepoOptions() {
	}

	public QsRepoOptions(String optName, String optValue, String allowInput, String unitId, Long sortNo) {
		this.optName = optName;
		this.optValue = optValue;
		this.allowInput = allowInput;
		this.unitId = unitId;
		this.sortNo = sortNo;
	}

	public String getOptName() {
		return this.optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getAllowInput() {
		return this.allowInput;
	}

	public void setAllowInput(String allowInput) {
		this.allowInput = allowInput;
	}

	public String getOptValue() {
		return this.optValue;
	}

	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}

	public String getOptId() {
		return this.optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getRtid() {
		return this.rtid;
	}

	public void setRtid(String rtid) {
		this.rtid = rtid;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public Long getSortNo() {
		return this.sortNo;
	}

	public void setSortNo(Long sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}