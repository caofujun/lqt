package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("qsTopicOption")
public class QsTopicOption extends BaseEntity implements Serializable {
	private String optId;
	private String optName;
	private String allowInput;
	private String optValue;
	private String tid;
	private String unitId;
	private Long sortNo;
	private String status;
	private String isSelect;

	public QsTopicOption() {
	}

	public QsTopicOption(String optId, String optName, String optValue, String allowInput, String isSelect,
			String unitId, Long sortNo) {
		this.isSelect = isSelect;
		this.optId = optId;
		this.optName = optName;
		this.optValue = optValue;
		this.allowInput = allowInput;
		this.unitId = unitId;
		this.sortNo = sortNo;
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

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsSelect() {
		return this.isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
}