package com.nis.comm.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("dataWarning")
public class DataWarning extends BaseEntity implements Serializable {
	private String zyId;
	private String patientName;
	private String warning;
	private String sql;
	private Date warningDate;

	public String getZyId() {
		return this.zyId;
	}

	public void setZyId(String zyId) {
		this.zyId = zyId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getWarning() {
		return this.warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public Date getWarningDate() {
		return this.warningDate;
	}

	public void setWarningDate(Date warningDate) {
		this.warningDate = warningDate;
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}