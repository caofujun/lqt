package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("xl019Xjnyqk")
public class Xl019Xjnyqk extends BaseEntity implements Serializable {
	private String id;
	private String brid;
	private String byt;
	private String kjyw;
	private String jg;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrid() {
		return this.brid;
	}

	public void setBrid(String brid) {
		this.brid = brid;
	}

	public String getByt() {
		return this.byt;
	}

	public void setByt(String byt) {
		this.byt = byt;
	}

	public String getKjyw() {
		return this.kjyw;
	}

	public void setKjyw(String kjyw) {
		this.kjyw = kjyw;
	}

	public String getJg() {
		return this.jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}
}