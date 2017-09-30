package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("zg006ZdmxFa")
public class Zg006ZdmxFa extends BaseEntity implements Serializable {
	private String id;
	private String faName;
	private String faDescribe;
	private Double jyxxWeight;
	private Integer jyxxDay;
	private Double bcyxWeight;
	private Integer bcyxDay;
	private Double xjppWeight;
	private Integer xjppDay;
	private Double kjyyWeight;
	private Integer kjyyDay;
	private String state;
	private String createUser;
	private Date createTime;
	private Double tyxzbWeight;
	private Integer tyxzbDay;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFaName() {
		return this.faName;
	}

	public void setFaName(String faName) {
		this.faName = faName;
	}

	public Double getJyxxWeight() {
		return this.jyxxWeight;
	}

	public void setJyxxWeight(Double jyxxWeight) {
		this.jyxxWeight = jyxxWeight;
	}

	public Double getBcyxWeight() {
		return this.bcyxWeight;
	}

	public void setBcyxWeight(Double bcyxWeight) {
		this.bcyxWeight = bcyxWeight;
	}

	public Double getXjppWeight() {
		return this.xjppWeight;
	}

	public void setXjppWeight(Double xjppWeight) {
		this.xjppWeight = xjppWeight;
	}

	public Double getKjyyWeight() {
		return this.kjyyWeight;
	}

	public void setKjyyWeight(Double kjyyWeight) {
		this.kjyyWeight = kjyyWeight;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFaDescribe() {
		return this.faDescribe;
	}

	public void setFaDescribe(String faDescribe) {
		this.faDescribe = faDescribe;
	}

	public Double getTyxzbWeight() {
		return this.tyxzbWeight;
	}

	public void setTyxzbWeight(Double tyxzbWeight) {
		this.tyxzbWeight = tyxzbWeight;
	}

	public Integer getJyxxDay() {
		return this.jyxxDay;
	}

	public void setJyxxDay(Integer jyxxDay) {
		this.jyxxDay = jyxxDay;
	}

	public Integer getBcyxDay() {
		return this.bcyxDay;
	}

	public void setBcyxDay(Integer bcyxDay) {
		this.bcyxDay = bcyxDay;
	}

	public Integer getXjppDay() {
		return this.xjppDay;
	}

	public void setXjppDay(Integer xjppDay) {
		this.xjppDay = xjppDay;
	}

	public Integer getKjyyDay() {
		return this.kjyyDay;
	}

	public void setKjyyDay(Integer kjyyDay) {
		this.kjyyDay = kjyyDay;
	}

	public Integer getTyxzbDay() {
		return this.tyxzbDay;
	}

	public void setTyxzbDay(Integer tyxzbDay) {
		this.tyxzbDay = tyxzbDay;
	}
}