package com.nis.homepage.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("sysHpComponent")
public class SysHpComponent extends BaseEntity implements Serializable {
	private String id;
	private String componentCode;
	private String componentName;
	private String componentUrl;
	private String codeBusiness;
	private String imgUrl;
	private String remark;
	private String layoutStatus;
	private String scopeLevel;
	private String unitId;
	private String depNo;
	private String menuCode;
	private String html;
	private String componentCodes;
	private String isSelected;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComponentCode() {
		return this.componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	public String getComponentName() {
		return this.componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentUrl() {
		return this.componentUrl;
	}

	public void setComponentUrl(String componentUrl) {
		this.componentUrl = componentUrl;
	}

	public String getCodeBusiness() {
		return this.codeBusiness;
	}

	public void setCodeBusiness(String codeBusiness) {
		this.codeBusiness = codeBusiness;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLayoutStatus() {
		return this.layoutStatus;
	}

	public void setLayoutStatus(String layoutStatus) {
		this.layoutStatus = layoutStatus;
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

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getComponentCodes() {
		return this.componentCodes;
	}

	public void setComponentCodes(String componentCodes) {
		this.componentCodes = componentCodes;
	}

	public String getIsSelected() {
		return this.isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
}