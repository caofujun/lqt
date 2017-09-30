package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Xn013Lisbyt extends BaseEntity implements Serializable {
	private String lisBytid;
	private String lisBytname;
	private String pycode;
	private String wbcode;
	private String bhcode;
	private String counterBytid;
	private String pathogenName;

	public String getLisBytid() {
		return this.lisBytid;
	}

	public void setLisBytid(String lisBytid) {
		this.lisBytid = lisBytid;
	}

	public String getLisBytname() {
		return this.lisBytname;
	}

	public void setLisBytname(String lisBytname) {
		this.lisBytname = lisBytname;
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

	public String getCounterBytid() {
		return this.counterBytid;
	}

	public void setCounterBytid(String counterBytid) {
		this.counterBytid = counterBytid;
	}

	public String getPathogenName() {
		return this.pathogenName;
	}

	public void setPathogenName(String pathogenName) {
		this.pathogenName = pathogenName;
	}
}