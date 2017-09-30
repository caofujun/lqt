package com.nis.yj.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Yj003Synonym extends BaseEntity implements Serializable {
	private String id;
	private String standardId;
	private String synonyms;
	private Long compareType;
	private String compareUserId;
	private Date compareTime;
	private String standardName;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStandardId() {
		return this.standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public String getSynonyms() {
		return this.synonyms;
	}

	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}

	public Long getCompareType() {
		return this.compareType;
	}

	public void setCompareType(Long compareType) {
		this.compareType = compareType;
	}

	public String getCompareUserId() {
		return this.compareUserId;
	}

	public void setCompareUserId(String compareUserId) {
		this.compareUserId = compareUserId;
	}

	public Date getCompareTime() {
		return this.compareTime;
	}

	public void setCompareTime(Date compareTime) {
		this.compareTime = compareTime;
	}

	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
}