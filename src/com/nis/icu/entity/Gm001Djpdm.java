package com.nis.icu.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.b;
import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class Gm001Djpdm extends BaseEntity implements Serializable {
	private Integer dtYear;
	private Integer dtMonth;
	private String weeknumber;
	private Date startdt;
	private Date enddt;
	private Date evaluatedt;
	private String adjusterid;
	private String adjustername;
	private Double acount;
	private Double bcount;
	private Double ccount;
	private Double dcount;
	private Double ecount;
	private Double avgscore;
	private String deptId;
	private Double sumscore;
	private Integer noScore;

	public Integer getDtYear() {
		return this.dtYear;
	}

	public void setDtYear(Integer dtYear) {
		this.dtYear = dtYear;
	}

	public Integer getDtMonth() {
		return this.dtMonth;
	}

	public void setDtMonth(Integer dtMonth) {
		this.dtMonth = dtMonth;
	}

	public String getWeeknumber() {
		return this.weeknumber;
	}

	public void setWeeknumber(String weeknumber) {
		this.weeknumber = weeknumber;
	}

	@JsonSerialize(using = b.class)
	public Date getStartdt() {
		return this.startdt;
	}

	public void setStartdt(Date startdt) {
		this.startdt = startdt;
	}

	@JsonSerialize(using = b.class)
	public Date getEnddt() {
		return this.enddt;
	}

	public void setEnddt(Date enddt) {
		this.enddt = enddt;
	}

	public Date getEvaluatedt() {
		return this.evaluatedt;
	}

	public void setEvaluatedt(Date evaluatedt) {
		this.evaluatedt = evaluatedt;
	}

	public String getAdjusterid() {
		return this.adjusterid;
	}

	public void setAdjusterid(String adjusterid) {
		this.adjusterid = adjusterid;
	}

	public String getAdjustername() {
		return this.adjustername;
	}

	public void setAdjustername(String adjustername) {
		this.adjustername = adjustername;
	}

	public Double getAcount() {
		return this.acount;
	}

	public void setAcount(Double acount) {
		this.acount = acount;
	}

	public Double getBcount() {
		return this.bcount;
	}

	public void setBcount(Double bcount) {
		this.bcount = bcount;
	}

	public Double getCcount() {
		return this.ccount;
	}

	public void setCcount(Double ccount) {
		this.ccount = ccount;
	}

	public Double getDcount() {
		return this.dcount;
	}

	public void setDcount(Double dcount) {
		this.dcount = dcount;
	}

	public Double getEcount() {
		return this.ecount;
	}

	public void setEcount(Double ecount) {
		this.ecount = ecount;
	}

	public Double getAvgscore() {
		return this.avgscore;
	}

	public void setAvgscore(Double avgscore) {
		this.avgscore = avgscore;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Double getSumscore() {
		return this.sumscore;
	}

	public void setSumscore(Double sumscore) {
		this.sumscore = sumscore;
	}

	public Integer getNoScore() {
		return this.noScore;
	}

	public void setNoScore(Integer noScore) {
		this.noScore = noScore;
	}
}