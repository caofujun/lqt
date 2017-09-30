package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.utils.ab;
import com.nis.questionnaire.entity.QsSurveyResult;
import com.nis.questionnaire.entity.QsTopicOption;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("qsTopic")
public class QsTopic extends BaseEntity implements Serializable {
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
	private String status;
	private String updTime;
	private String updUser;
	private String qid;
	private Long sortNo;
	private String depType;
	private Long maxValue;
	private Long feedType;
	private String isCount;
	private String weight;
	private List<QsTopicOption> options;
	private List<QsSurveyResult> answers;
	private String titleDiff;

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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getQid() {
		return this.qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public Long getSortNo() {
		return this.sortNo;
	}

	public void setSortNo(Long sortNo) {
		this.sortNo = sortNo;
	}

	public String getDepType() {
		return this.depType;
	}

	public void setDepType(String depType) {
		this.depType = depType;
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

	public String getIsCount() {
		return this.isCount;
	}

	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}

	public List<QsTopicOption> getOptions() {
		return this.options;
	}

	public void setOptions(List<QsTopicOption> options) {
		this.options = options;
	}

	public List<QsSurveyResult> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<QsSurveyResult> answers) {
		this.answers = answers;
	}

	public String getTitleDiff() {
		return this.titleDiff;
	}

	public void setTitleDiff(String titleDiff) {
		if (ab.isNotEmpty(titleDiff)) {
			this.title = titleDiff;
		}

		this.titleDiff = titleDiff;
	}

	public String getWeight() {
		return this.weight == null ? "100" : this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
}