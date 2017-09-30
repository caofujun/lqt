package com.nis.intervene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class FxZhibiao extends BaseEntity implements Serializable {
	private String zbId;
	private String zbName;
	private String zbClass;
	private String zbValue;
	private Integer zbScore;
	private String isGy;
	private String zbType;
	private String qid;
	private String isGyName;
	private String zbTypeName;

	public String getZbId() {
		return this.zbId;
	}

	public void setZbId(String zbId) {
		this.zbId = zbId;
	}

	public String getZbName() {
		return this.zbName;
	}

	public void setZbName(String zbName) {
		this.zbName = zbName;
	}

	public String getZbClass() {
		return this.zbClass;
	}

	public void setZbClass(String zbClass) {
		this.zbClass = zbClass;
	}

	public String getZbValue() {
		return this.zbValue;
	}

	public void setZbValue(String zbValue) {
		this.zbValue = zbValue;
	}

	public Integer getZbScore() {
		return this.zbScore;
	}

	public void setZbScore(Integer zbScore) {
		this.zbScore = zbScore;
	}

	public String getIsGy() {
		return this.isGy;
	}

	public void setIsGy(String isGy) {
		this.isGy = isGy;
	}

	public String getZbType() {
		return this.zbType;
	}

	public void setZbType(String zbType) {
		this.zbType = zbType;
	}

	public String getQid() {
		return this.qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getIsGyName() {
		return this.isGyName;
	}

	public void setIsGyName(String isGyName) {
		this.isGyName = isGyName;
	}

	public String getZbTypeName() {
		return this.zbTypeName;
	}

	public void setZbTypeName(String zbTypeName) {
		this.zbTypeName = zbTypeName;
	}
}