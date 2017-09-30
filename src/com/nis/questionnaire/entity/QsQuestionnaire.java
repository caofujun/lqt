package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.questionnaire.entity.QsTopic;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("qsQuestionnaire")
public class QsQuestionnaire extends BaseEntity implements Serializable {
	private String qid;
	private String title;
	private String note;
	private String catId;
	private String catName;
	private Integer status;
	private String endTime;
	private String startTime;
	private String createTime;
	private String updTime;
	private String updUser;
	private String depNo;
	private String unitId;
	private String qIds;
	private String resultOpenState;
	private String createUser;
	private String publish;
	private String isMod;
	private String isQz;
	private String isEval;
	private Long answerCount;
	private Long yjCount;
	private Long tmCount;
	private String qlevel;
	private Float feedbackValue;
	private Float dcValue;
	private String isAnonymous;
	private String depNoName;
	private String statusName;
	private String createUserName;
	private String ext1;
	private List<String> excludeQids;
	private List<QsTopic> qsTopicList;
	private Integer isExpire;

	public String getQid() {
		return this.qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getDepNo() {
		return this.depNo;
	}

	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getQIds() {
		return this.qIds;
	}

	public void setQIds(String qIds) {
		this.qIds = qIds;
	}

	public String getResultOpenState() {
		return this.resultOpenState;
	}

	public void setResultOpenState(String resultOpenState) {
		this.resultOpenState = resultOpenState;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getPublish() {
		return this.publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getIsMod() {
		return this.isMod;
	}

	public void setIsMod(String isMod) {
		this.isMod = isMod;
	}

	public String getIsEval() {
		return this.isEval;
	}

	public void setIsEval(String isEval) {
		this.isEval = isEval;
	}

	public Long getAnswerCount() {
		return this.answerCount;
	}

	public void setAnswerCount(Long answerCount) {
		this.answerCount = answerCount;
	}

	public Long getYjCount() {
		return this.yjCount;
	}

	public void setYjCount(Long yjCount) {
		this.yjCount = yjCount;
	}

	public Long getTmCount() {
		return this.tmCount;
	}

	public void setTmCount(Long tmCount) {
		this.tmCount = tmCount;
	}

	public String getDepNoName() {
		return this.depNoName;
	}

	public void setDepNoName(String depNoName) {
		this.depNoName = depNoName;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public List<QsTopic> getQsTopicList() {
		return this.qsTopicList;
	}

	public void setQsTopicList(List<QsTopic> qsTopicList) {
		this.qsTopicList = qsTopicList;
	}

	public List<String> getExcludeQids() {
		return this.excludeQids;
	}

	public void setExcludeQids(List<String> excludeQids) {
		this.excludeQids = excludeQids;
	}

	public String getQlevel() {
		return this.qlevel;
	}

	public void setQlevel(String qlevel) {
		this.qlevel = qlevel;
	}

	public Float getFeedbackValue() {
		return this.feedbackValue;
	}

	public void setFeedbackValue(Float feedbackValue) {
		this.feedbackValue = feedbackValue;
	}

	public Float getDcValue() {
		return this.dcValue;
	}

	public void setDcValue(Float dcValue) {
		this.dcValue = dcValue;
	}

	public String getIsAnonymous() {
		return this.isAnonymous;
	}

	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getIsQz() {
		return this.isQz;
	}

	public void setIsQz(String isQz) {
		this.isQz = isQz;
	}

	public Integer getIsExpire() {
		this.isExpire = Integer.valueOf(0);
		if (this.status != null && this.status.intValue() == 1 && ab.isNotEmpty(this.endTime)) {
			try {
				Timestamp et = f.k(this.endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				if (f.e(new Date(), et) > 0) {
					this.isExpire = Integer.valueOf(1);
				}
			} catch (Exception arg1) {
				;
			}
		}

		return this.isExpire;
	}

	public void setIsExpire(Integer isExpire) {
		this.isExpire = isExpire;
	}
}