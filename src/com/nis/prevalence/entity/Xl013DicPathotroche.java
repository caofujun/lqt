package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("xl013DicPathotroche")
public class Xl013DicPathotroche extends BaseEntity implements Serializable {
	private String pathotrocheid;
	private Double year;
	private String pathoname;
	private String trochename;
	private String pathoid;
	private String trocheid;

	public String getPathotrocheid() {
		return this.pathotrocheid;
	}

	public void setPathotrocheid(String pathotrocheid) {
		this.pathotrocheid = pathotrocheid;
	}

	public Double getYear() {
		return this.year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

	public String getPathoname() {
		return this.pathoname;
	}

	public void setPathoname(String pathoname) {
		this.pathoname = pathoname;
	}

	public String getTrochename() {
		return this.trochename;
	}

	public void setTrochename(String trochename) {
		this.trochename = trochename;
	}

	public String getPathoid() {
		return this.pathoid;
	}

	public void setPathoid(String pathoid) {
		this.pathoid = pathoid;
	}

	public String getTrocheid() {
		return this.trocheid;
	}

	public void setTrocheid(String trocheid) {
		this.trocheid = trocheid;
	}
}