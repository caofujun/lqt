package com.nis.outbreak.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("by007Data")
public class By007Data extends BaseEntity implements Serializable {
	private Date moniDate;
	private String deptId;
	private String identify;
	private String typeId;
	private String extraId;

	public Date getMoniDate() {
		return this.moniDate;
	}

	public void setMoniDate(Date moniDate) {
		this.moniDate = moniDate;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getIdentify() {
		return this.identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getExtraId() {
		return this.extraId;
	}

	public void setExtraId(String extraId) {
		this.extraId = extraId;
	}
}