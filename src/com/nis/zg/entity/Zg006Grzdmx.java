package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Zg006Grzdmx extends BaseEntity implements Serializable {
	private Double mxId;
	private String infectCode;
	private Double standardNo;
	private Double nodeLevel;
	private String nodeId;
	private String nodeBody;
	private Double ifBaseElement;
	private String elementId;
	private Double ifMust;
	private Double listCount;
	private Double needCount;
	private Double weightValue;
	private String pNodeId;
	private Double weightRatio;

	public Double getMxId() {
		return this.mxId;
	}

	public void setMxId(Double mxId) {
		this.mxId = mxId;
	}

	public String getInfectCode() {
		return this.infectCode;
	}

	public void setInfectCode(String infectCode) {
		this.infectCode = infectCode;
	}

	public Double getStandardNo() {
		return this.standardNo;
	}

	public void setStandardNo(Double standardNo) {
		this.standardNo = standardNo;
	}

	public Double getNodeLevel() {
		return this.nodeLevel;
	}

	public void setNodeLevel(Double nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeBody() {
		return this.nodeBody;
	}

	public void setNodeBody(String nodeBody) {
		this.nodeBody = nodeBody;
	}

	public Double getIfBaseElement() {
		return this.ifBaseElement;
	}

	public void setIfBaseElement(Double ifBaseElement) {
		this.ifBaseElement = ifBaseElement;
	}

	public String getElementId() {
		return this.elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public Double getIfMust() {
		return this.ifMust;
	}

	public void setIfMust(Double ifMust) {
		this.ifMust = ifMust;
	}

	public Double getListCount() {
		return this.listCount;
	}

	public void setListCount(Double listCount) {
		this.listCount = listCount;
	}

	public Double getNeedCount() {
		return this.needCount;
	}

	public void setNeedCount(Double needCount) {
		this.needCount = needCount;
	}

	public Double getWeightValue() {
		return this.weightValue;
	}

	public void setWeightValue(Double weightValue) {
		this.weightValue = weightValue;
	}

	public String getPNodeId() {
		return this.pNodeId;
	}

	public void setPNodeId(String pNodeId) {
		this.pNodeId = pNodeId;
	}

	public Double getWeightRatio() {
		return this.weightRatio;
	}

	public void setWeightRatio(Double weightRatio) {
		this.weightRatio = weightRatio;
	}
}