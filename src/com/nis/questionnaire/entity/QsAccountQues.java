package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("QsAccountQues")
public class QsAccountQues extends BaseEntity implements Serializable {
	private String id;
	private String qid;
	private String userId;
	private Float dcValue;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQid() {
		return this.qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Float getDcValue() {
		return this.dcValue;
	}

	public void setDcValue(Float dcValue) {
		this.dcValue = dcValue;
	}
}