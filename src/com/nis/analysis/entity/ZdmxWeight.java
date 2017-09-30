package com.nis.analysis.entity;

import com.nis.analysis.entity.Gr019YsgrmxWeight;
import com.nis.analysis.entity.Zg006Zdmx;
import java.util.Date;
import java.util.List;

public class ZdmxWeight {
	private Integer weight;
	private Date grDate;
	private List<Zg006Zdmx> zdmxList;
	private Gr019YsgrmxWeight ysgrmxWeight;

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public List<Zg006Zdmx> getZdmxList() {
		return this.zdmxList;
	}

	public void setZdmxList(List<Zg006Zdmx> zdmxList) {
		this.zdmxList = zdmxList;
	}

	public Date getGrDate() {
		return this.grDate;
	}

	public void setGrDate(Date grDate) {
		this.grDate = grDate;
	}

	public Gr019YsgrmxWeight getYsgrmxWeight() {
		return this.ysgrmxWeight;
	}

	public void setYsgrmxWeight(Gr019YsgrmxWeight ysgrmxWeight) {
		this.ysgrmxWeight = ysgrmxWeight;
	}
}