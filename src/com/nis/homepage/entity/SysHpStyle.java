package com.nis.homepage.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("sysHpStyle")
public class SysHpStyle extends BaseEntity implements Serializable {
	private String id;
	private String layoutCode;
	private String componentCodes;
	private String businessCode;
	private String scopeLevel;
	private String unitId;
	private String depNo;
	private String userName;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLayoutCode() {
		return this.layoutCode;
	}

	public void setLayoutCode(String layoutCode) {
		this.layoutCode = layoutCode;
	}

	public String getComponentCodes() {
		return this.componentCodes;
	}

	public void setComponentCodes(String componentCodes) {
		this.componentCodes = componentCodes;
	}

	public String getBusinessCode() {
		return this.businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getScopeLevel() {
		return this.scopeLevel;
	}

	public void setScopeLevel(String scopeLevel) {
		this.scopeLevel = scopeLevel;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getDepNo() {
		return this.depNo;
	}

	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}