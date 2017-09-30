package com.nis.outbreak.entity;

import com.nis.comm.serializer.c;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("by007GraphData")
public class By007GraphData implements Serializable {
	private Date moniDate;
	private Double line;
	private Double observeLine;
	private Double cnt;
	private Double cntAll;

	@JsonSerialize(using = c.class)
	public Date getMoniDate() {
		return this.moniDate;
	}

	public void setMoniDate(Date moniDate) {
		this.moniDate = moniDate;
	}

	public Double getLine() {
		return this.line == null ? Double.valueOf(0.0D) : this.line;
	}

	public void setLine(Double line) {
		this.line = line;
	}

	public Double getObserveLine() {
		return this.observeLine;
	}

	public void setObserveLine(Double observeLine) {
		this.observeLine = observeLine;
	}

	public Double getCnt() {
		return this.cnt;
	}

	public void setCnt(Double cnt) {
		this.cnt = cnt;
	}

	public Double getCntAll() {
		return this.cntAll;
	}

	public void setCntAll(Double cntAll) {
		this.cntAll = cntAll;
	}
}