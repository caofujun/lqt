package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("xl010DicStatkind")
public class Xl010DicStatkind extends BaseEntity implements Serializable {
	private String statid;
	private String statname;
	private String zjc;

	public String getStatid() {
		return this.statid;
	}

	public void setStatid(String statid) {
		this.statid = statid;
	}

	public String getStatname() {
		return this.statname;
	}

	public void setStatname(String statname) {
		this.statname = statname;
	}

	public String getZjc() {
		return this.zjc;
	}

	public void setZjc(String zjc) {
		this.zjc = zjc;
	}
}