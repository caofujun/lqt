package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.be;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("hw103Jcdjg")
public class Hw103Jcdjg extends BaseEntity implements Serializable {
	private String id;
	private String reportId;
	private String resultId;
	private String classId;
	private String itemId;
	private String result;
	private String resultCondition;
	private String resultCriterion;
	private String resultUnit;
	private Integer resultFlag;
	private String memo;
	private String hw102Id;
	private String resultFlagName;
	private String criterion;
	private String unit;
	private String condition;
	private String itemName;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getResultId() {
		return this.resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultCondition() {
		return this.resultCondition;
	}

	public void setResultCondition(String resultCondition) {
		this.resultCondition = resultCondition;
	}

	public String getResultCriterion() {
		return this.resultCriterion;
	}

	public void setResultCriterion(String resultCriterion) {
		this.resultCriterion = resultCriterion;
	}

	public String getResultUnit() {
		return this.resultUnit;
	}

	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}

	public Integer getResultFlag() {
		return this.resultFlag;
	}

	public void setResultFlag(Integer resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getHw102Id() {
		return this.hw102Id;
	}

	public void setHw102Id(String hw102Id) {
		this.hw102Id = hw102Id;
	}

	public String getResultFlagName() {
		return be.p(this.resultFlag).getName();
	}

	public void setResultFlagName(String resultFlagName) {
		this.resultFlagName = resultFlagName;
	}

	public String getCriterion() {
		return this.criterion;
	}

	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}