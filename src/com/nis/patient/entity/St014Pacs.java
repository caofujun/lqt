package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.b;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("st014Pacs")
public class St014Pacs extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String mzid;
	private String checkNo;
	private String device;
	private String itemId;
	private String itemName;
	private Date checkAt;
	private Date reportAt;
	private byte[] cliDiagnose;
	private String checkDesc;
	private String checkImpr;
	private String checkDr;
	private String reportDr;
	private String deptId;
	private String deptName;
	private Date createAt;
	private Long analFlag;
	private Date analAt;
	private byte[] analResult;
	private String patientId;
	private Date updDate;
	private String elementName;
	private String showAnalResult;
	private Integer cdcanalflag;
	private Date cdcanaldt;
	private String tablename;

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

	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@JsonSerialize(using = b.class)
	public Date getCheckAt() {
		return this.checkAt;
	}

	public void setCheckAt(Date checkAt) {
		this.checkAt = checkAt;
	}

	@JsonSerialize(using = b.class)
	public Date getReportAt() {
		return this.reportAt;
	}

	public void setReportAt(Date reportAt) {
		this.reportAt = reportAt;
	}

	public byte[] getCliDiagnose() {
		return this.cliDiagnose;
	}

	public void setCliDiagnose(byte[] cliDiagnose) {
		this.cliDiagnose = cliDiagnose;
	}

	public String getCheckDesc() {
		if (this.checkDesc == null) {
			this.checkDesc = "";
		}

		return this.checkDesc;
	}

	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}

	public String getCheckImpr() {
		if (this.checkImpr == null) {
			this.checkImpr = "";
		}

		return this.checkImpr;
	}

	public void setCheckImpr(String checkImpr) {
		this.checkImpr = checkImpr;
	}

	public String getCheckDr() {
		return this.checkDr;
	}

	public void setCheckDr(String checkDr) {
		this.checkDr = checkDr;
	}

	public String getReportDr() {
		return this.reportDr;
	}

	public void setReportDr(String reportDr) {
		this.reportDr = reportDr;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getAnalFlag() {
		return this.analFlag;
	}

	public void setAnalFlag(Long analFlag) {
		this.analFlag = analFlag;
	}

	public Date getAnalAt() {
		return this.analAt;
	}

	public void setAnalAt(Date analAt) {
		this.analAt = analAt;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getElementName() {
		return this.elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public byte[] getAnalResult() {
		return this.analResult;
	}

	public void setAnalResult(byte[] analResult) {
		this.analResult = analResult;

		try {
			if (analResult != null) {
				this.showAnalResult = new String(analResult, com.nis.comm.constants.b.eA);
			}
		} catch (UnsupportedEncodingException arg2) {
			arg2.printStackTrace();
		}

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
				this.analResult = showAnalResult.getBytes(com.nis.comm.constants.b.eA);
			}
		} catch (UnsupportedEncodingException arg2) {
			arg2.printStackTrace();
		}

	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}