package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("ctgSys014Todolist")
public class CtgSys014Todolist extends BaseEntity implements Serializable {
	private String keyid;
	private String patientType;
	private String mzzyid;
	private String patientName;

	public String getKeyid() {
		return this.keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getMzzyid() {
		return this.mzzyid;
	}

	public void setMzzyid(String mzzyid) {
		this.mzzyid = mzzyid;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
}