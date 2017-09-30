package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class CtgSys012Pathology extends BaseEntity implements Serializable {
	private String pathologyno;
	private String pathologyname;
	private String pycode;
	private String wbcode;
	private String bhcode;

	public String getPathologyno() {
		return this.pathologyno;
	}

	public void setPathologyno(String pathologyno) {
		this.pathologyno = pathologyno;
	}

	public String getPathologyname() {
		return this.pathologyname;
	}

	public void setPathologyname(String pathologyname) {
		this.pathologyname = pathologyname;
	}

	public String getPycode() {
		return this.pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}

	public String getWbcode() {
		return this.wbcode;
	}

	public void setWbcode(String wbcode) {
		this.wbcode = wbcode;
	}

	public String getBhcode() {
		return this.bhcode;
	}

	public void setBhcode(String bhcode) {
		this.bhcode = bhcode;
	}
}