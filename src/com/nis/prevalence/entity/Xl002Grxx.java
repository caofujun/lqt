package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.prevalence.entity.Xl003Byt;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("xl002Grxx")
public class Xl002Grxx extends BaseEntity implements Serializable {
	private String grid;
	private String brid;
	private Integer infectType;
	private String infectCode;
	private String infectName;
	private Date lastAt;
	private List<Xl003Byt> xl003List;
	private List<String> gridNotIn;

	public String getGrid() {
		return this.grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getBrid() {
		return this.brid;
	}

	public void setBrid(String brid) {
		this.brid = brid;
	}

	public Integer getInfectType() {
		return this.infectType;
	}

	public void setInfectType(Integer infectType) {
		this.infectType = infectType;
	}

	public String getInfectCode() {
		return this.infectCode;
	}

	public void setInfectCode(String infectCode) {
		this.infectCode = infectCode;
	}

	public String getInfectName() {
		return this.infectName;
	}

	public void setInfectName(String infectName) {
		this.infectName = infectName;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public List<Xl003Byt> getXl003List() {
		return this.xl003List;
	}

	public void setXl003List(List<Xl003Byt> xl003List) {
		this.xl003List = xl003List;
	}

	public List<String> getGridNotIn() {
		return this.gridNotIn;
	}

	public void setGridNotIn(List<String> gridNotIn) {
		this.gridNotIn = gridNotIn;
	}
}