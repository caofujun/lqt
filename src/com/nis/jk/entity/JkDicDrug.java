package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkDicDrug")
public class JkDicDrug extends BaseEntity implements Serializable {
	private String id;
	private String drugCode;
	private String drugName;
	private String spec;
	private Long drugLine;
	private Double price;
	private Long isAntibiotic;
	private String units;
	private String drugForm;
	private String toxiProperty;
	private String dosePerUnit;
	private String doseUnits;
	private String drugIndicator;
	private Date updTime;
	private Long updFlag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDrugCode() {
		return this.drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Long getDrugLine() {
		return this.drugLine;
	}

	public void setDrugLine(Long drugLine) {
		this.drugLine = drugLine;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getIsAntibiotic() {
		return this.isAntibiotic;
	}

	public void setIsAntibiotic(Long isAntibiotic) {
		this.isAntibiotic = isAntibiotic;
	}

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getDrugForm() {
		return this.drugForm;
	}

	public void setDrugForm(String drugForm) {
		this.drugForm = drugForm;
	}

	public String getToxiProperty() {
		return this.toxiProperty;
	}

	public void setToxiProperty(String toxiProperty) {
		this.toxiProperty = toxiProperty;
	}

	public String getDosePerUnit() {
		return this.dosePerUnit;
	}

	public void setDosePerUnit(String dosePerUnit) {
		this.dosePerUnit = dosePerUnit;
	}

	public String getDoseUnits() {
		return this.doseUnits;
	}

	public void setDoseUnits(String doseUnits) {
		this.doseUnits = doseUnits;
	}

	public String getDrugIndicator() {
		return this.drugIndicator;
	}

	public void setDrugIndicator(String drugIndicator) {
		this.drugIndicator = drugIndicator;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Long getUpdFlag() {
		return this.updFlag;
	}

	public void setUpdFlag(Long updFlag) {
		this.updFlag = updFlag;
	}
}