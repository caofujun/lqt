package com.nis.patient.entity;

import com.nis.comm.constants.b;
import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.d;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("st008Bcjl")
public class St008Bcjl extends BaseEntity implements Serializable {
	private Date analAt;
	private byte[] analResult;
	private Date updDate;
	private String id;
	private String zyid;
	private String mzid;
	private String patientId;
	private Integer visitId;
	private Date enterDate;
	private String caseClass;
	private String bcCode;
	private String bcName;
	private Date createAt;
	private String courseContent;
	private String contentType;
	private Long analFlag;
	private String keyWords;
	private String showAnalResult;
	private Date outAt;
	private String tablename;
	private String orderBy;
	private Integer cdcanalflag;
	private Date cdcanaldt;

	public Integer getCdcanalflag() {
		return this.cdcanalflag;
	}

	public void setCdcanalflag(Integer cdcanalflag) {
		this.cdcanalflag = cdcanalflag;
	}

	public Date getCdcanaldt() {
		return this.cdcanaldt;
	}

	public void setCdcanaldt(Date cdcanaldt) {
		this.cdcanaldt = cdcanaldt;
	}

	public Date getAnalAt() {
		return this.analAt;
	}

	public void setAnalAt(Date analAt) {
		this.analAt = analAt;
	}

	public byte[] getAnalResult() {
		return this.analResult;
	}

	public void setAnalResult(byte[] analResult) {
		this.analResult = analResult;

		try {
			if (analResult != null) {
				this.showAnalResult = new String(analResult, b.eA);
			}
		} catch (UnsupportedEncodingException arg2) {
			arg2.printStackTrace();
		}

	}

	@JsonSerialize(using = d.class)
	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Integer getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	@JsonSerialize(using = d.class)
	public Date getEnterDate() {
		return this.enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public String getCaseClass() {
		return this.caseClass;
	}

	public void setCaseClass(String caseClass) {
		this.caseClass = caseClass;
	}

	public String getBcCode() {
		return this.bcCode;
	}

	public void setBcCode(String bcCode) {
		this.bcCode = bcCode;
	}

	public String getBcName() {
		return this.bcName;
	}

	public void setBcName(String bcName) {
		this.bcName = bcName;
	}

	@JsonSerialize(using = d.class)
	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getCourseContent() {
		return this.courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public Long getAnalFlag() {
		return this.analFlag;
	}

	public void setAnalFlag(Long analFlag) {
		this.analFlag = analFlag;
	}

	public String getKeyWords() {
		return this.keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getShowAnalResult() {
		return this.showAnalResult;
	}

	public void setShowAnalResult(String showAnalResult) {
		this.showAnalResult = showAnalResult;

		try {
			if (StringUtils.isBlank(showAnalResult)) {
				this.analResult = null;
			} else {
				this.analResult = showAnalResult.getBytes(b.eA);
			}
		} catch (UnsupportedEncodingException arg2) {
			arg2.printStackTrace();
		}

	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public Date getOutAt() {
		return this.outAt;
	}

	public void setOutAt(Date outAt) {
		this.outAt = outAt;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}