package com.nis.access.entity;

import com.nis.access.entity.AcAccount;
import org.apache.ibatis.type.Alias;

@Alias("AcAccountAndUnit")
public class AcAccountAndUnit extends AcAccount {
	private String unitId;
	private String unitName;
	private Long areaId;
	private String areaName;
	private String unitType;
	private String unitLevel;
	private String unitClass;
	private String unitState;
	private String city;
	private String province;
	private String type;

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUnitType() {
		return this.unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getUnitLevel() {
		return this.unitLevel;
	}

	public void setUnitLevel(String unitLevel) {
		this.unitLevel = unitLevel;
	}

	public String getUnitClass() {
		return this.unitClass;
	}

	public void setUnitClass(String unitClass) {
		this.unitClass = unitClass;
	}

	public String getUnitState() {
		return this.unitState;
	}

	public void setUnitState(String unitState) {
		this.unitState = unitState;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}