package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.bk;
import com.nis.comm.utils.ab;
import com.nis.questionnaire.entity.QsReportOptions;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("qsReportTopic")
public class QsReportTopic extends BaseEntity implements Serializable {
	private String qId;
	private String tid;
	private String title;
	private String ttype;
	private String ttypeName;
	private Long tidCount;
	private List<QsReportOptions> qsReportOptionList;
	private String optId;
	private Long allQsCount;
	private Long trueQsCount;
	private String unitId;
	private String depNo;

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

	public Long getTidCount() {
		return this.tidCount;
	}

	public void setTidCount(Long tidCount) {
		this.tidCount = tidCount;
	}

	public List<QsReportOptions> getQsReportOptionList() {
		return this.qsReportOptionList;
	}

	public void setQsReportOptionList(List<QsReportOptions> qsReportOptionList) {
		this.qsReportOptionList = qsReportOptionList;
	}

	public String getTtypeName() {
		if (ab.isNotEmpty(this.ttypeName)) {
			return this.ttypeName;
		} else if (ab.isNotEmpty(this.ttype)) {
			bk typeEnums = bk.q(Integer.valueOf(Integer.parseInt(this.ttype)));
			return typeEnums == null ? null : typeEnums.getName();
		} else {
			return null;
		}
	}

	public void setTtypeName(String ttypeName) {
		this.ttypeName = ttypeName;
	}

	public String getqId() {
		return this.qId;
	}

	public void setqId(String qId) {
		this.qId = qId;
	}

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getOptId() {
		return this.optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public Long getAllQsCount() {
		return this.allQsCount;
	}

	public void setAllQsCount(Long allQsCount) {
		this.allQsCount = allQsCount;
	}

	public Long getTrueQsCount() {
		return this.trueQsCount;
	}

	public void setTrueQsCount(Long trueQsCount) {
		this.trueQsCount = trueQsCount;
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
}