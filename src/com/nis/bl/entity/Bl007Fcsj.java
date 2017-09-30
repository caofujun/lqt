package com.nis.bl.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.c;
import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class Bl007Fcsj extends BaseEntity implements Serializable {
	private String relid;
	private String blId;
	private String sjId;
	private String csmId;
	private String csdId;
	private String djName;
	private String djCardid;
	private Long flag;
	private Date fc;
	private Date beforFc;
	private Date afterFc;
	private Long fcZt;
	private Integer days;
	private Date enterTime;
	private String itemName;
	private String djDept;

	public String getDjDept() {
		return this.djDept;
	}

	public void setDjDept(String djDept) {
		this.djDept = djDept;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getEnterTime() {
		return this.enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public String getRelid() {
		return this.relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getBlId() {
		return this.blId;
	}

	public void setBlId(String blId) {
		this.blId = blId;
	}

	public String getSjId() {
		return this.sjId;
	}

	public void setSjId(String sjId) {
		this.sjId = sjId;
	}

	public String getCsmId() {
		return this.csmId;
	}

	public void setCsmId(String csmId) {
		this.csmId = csmId;
	}

	public String getCsdId() {
		return this.csdId;
	}

	public void setCsdId(String csdId) {
		this.csdId = csdId;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	@JsonSerialize(using = c.class)
	public Date getFc() {
		return this.fc;
	}

	public void setFc(Date fc) {
		this.fc = fc;
	}

	public String getDjName() {
		return this.djName;
	}

	public void setDjName(String djName) {
		this.djName = djName;
	}

	public Long getFcZt() {
		return this.fcZt;
	}

	public void setFcZt(Long fcZt) {
		this.fcZt = fcZt;
	}

	public Integer getDays() {
		return this.days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	@JsonSerialize(using = c.class)
	public Date getBeforFc() {
		return this.beforFc;
	}

	public void setBeforFc(Date beforFc) {
		this.beforFc = beforFc;
	}

	public String getDjCardid() {
		return this.djCardid;
	}

	public void setDjCardid(String djCardid) {
		this.djCardid = djCardid;
	}

	public Date getAfterFc() {
		return this.afterFc;
	}

	public void setAfterFc(Date afterFc) {
		this.afterFc = afterFc;
	}
}