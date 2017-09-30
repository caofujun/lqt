package com.nis.yj.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Yj003RegularLis extends BaseEntity implements Serializable {
	private String id;
	private String itemTypeName;
	private String antiName;
	private Long compareRegular;
	private String value;
	private String unit;
	private Double maleUp;
	private Double maleDown;
	private Double femaleUp;
	private Double femaleDown;
	private String element;
	private Long lisType;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemTypeName() {
		return this.itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getAntiName() {
		return this.antiName;
	}

	public void setAntiName(String antiName) {
		this.antiName = antiName;
	}

	public Long getCompareRegular() {
		return this.compareRegular;
	}

	public void setCompareRegular(Long compareRegular) {
		this.compareRegular = compareRegular;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getMaleUp() {
		return this.maleUp;
	}

	public void setMaleUp(Double maleUp) {
		this.maleUp = maleUp;
	}

	public Double getMaleDown() {
		return this.maleDown;
	}

	public void setMaleDown(Double maleDown) {
		this.maleDown = maleDown;
	}

	public Double getFemaleUp() {
		return this.femaleUp;
	}

	public void setFemaleUp(Double femaleUp) {
		this.femaleUp = femaleUp;
	}

	public Double getFemaleDown() {
		return this.femaleDown;
	}

	public void setFemaleDown(Double femaleDown) {
		this.femaleDown = femaleDown;
	}

	public String getElement() {
		return this.element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public Long getLisType() {
		return this.lisType;
	}

	public void setLisType(Long lisType) {
		this.lisType = lisType;
	}
}