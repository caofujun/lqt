package com.nis.questions.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.questions.entity.QsRepoOptions;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("qsRepoTopic")
public class QsRepoTopic extends BaseEntity implements Serializable {
	private String tid;
	private String title;
	private String ttype;
	private String tlevel;
	private String allowNull;
	private String note;
	private String catId;
	private String catName;
	private String unitId;
	private String depNo;
	private String docNo;
	private Integer attr;
	private Integer state;
	private String updTime;
	private String updUser;
	private Long maxValue;
	private Long feedType;
	private String createUser;
	private String ttypeName;
	private List<QsRepoOptions> options;

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTtype() {
		return this.ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public String getTlevel() {
		return this.tlevel;
	}

	public void setTlevel(String tlevel) {
		this.tlevel = tlevel;
	}

	public String getAllowNull() {
		return this.allowNull;
	}

	public void setAllowNull(String allowNull) {
		this.allowNull = allowNull;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCatId() {
		return this.catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getDepNo() {
		return this.depNo;
	}

	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}

	public String getDocNo() {
		return this.docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Integer getAttr() {
		return this.attr;
	}

	public void setAttr(Integer attr) {
		this.attr = attr;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public Long getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}

	public Long getFeedType() {
		return this.feedType;
	}

	public void setFeedType(Long feedType) {
		this.feedType = feedType;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getTtypeName() {
		return this.ttypeName;
	}

	public void setTtypeName(String ttypeName) {
		this.ttypeName = ttypeName;
	}

	public List<QsRepoOptions> getOptions() {
		return this.options;
	}

	public void setOptions(List<QsRepoOptions> options) {
		this.options = options;
	}
}