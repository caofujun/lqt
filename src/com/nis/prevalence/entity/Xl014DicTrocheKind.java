package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("xl014DicTrocheKind")
public class Xl014DicTrocheKind extends BaseEntity implements Serializable {
	private Integer year;
	private String trochekindid;
	private String trochekindname;
	private String memoryid;
	private Double flag;
	private Date lastAt;

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTrochekindid() {
		return this.trochekindid;
	}

	public void setTrochekindid(String trochekindid) {
		this.trochekindid = trochekindid;
	}

	public String getTrochekindname() {
		return this.trochekindname;
	}

	public void setTrochekindname(String trochekindname) {
		this.trochekindname = trochekindname;
	}

	public String getMemoryid() {
		return this.memoryid;
	}

	public void setMemoryid(String memoryid) {
		this.memoryid = memoryid;
	}

	public Double getFlag() {
		return this.flag;
	}

	public void setFlag(Double flag) {
		this.flag = flag;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}
}