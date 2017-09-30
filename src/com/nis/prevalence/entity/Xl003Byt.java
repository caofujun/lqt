package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.prevalence.entity.Xl004Kjyw;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("xl003Byt")
public class Xl003Byt extends BaseEntity implements Serializable {
	private String bytid;
	private String brid;
	private String grid;
	private String infectPathoId;
	private String infectPathoName;
	private Date lastAt;
	private String pathoresult;
	private String sample;
	private List<String> bytidNotIn;
	private List<Xl004Kjyw> xl004List;

	public String getSample() {
		return this.sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}

	public String getBytid() {
		return this.bytid;
	}

	public void setBytid(String bytid) {
		this.bytid = bytid;
	}

	public String getBrid() {
		return this.brid;
	}

	public void setBrid(String brid) {
		this.brid = brid;
	}

	public String getGrid() {
		return this.grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getInfectPathoId() {
		return this.infectPathoId;
	}

	public void setInfectPathoId(String infectPathoId) {
		this.infectPathoId = infectPathoId;
	}

	public String getInfectPathoName() {
		return this.infectPathoName;
	}

	public void setInfectPathoName(String infectPathoName) {
		this.infectPathoName = infectPathoName;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getPathoresult() {
		return this.pathoresult;
	}

	public void setPathoresult(String pathoresult) {
		this.pathoresult = pathoresult;
	}

	public List<String> getBytidNotIn() {
		return this.bytidNotIn;
	}

	public void setBytidNotIn(List<String> bytidNotIn) {
		this.bytidNotIn = bytidNotIn;
	}

	public List<Xl004Kjyw> getXl004List() {
		return this.xl004List;
	}

	public void setXl004List(List<Xl004Kjyw> xl004List) {
		this.xl004List = xl004List;
	}
}