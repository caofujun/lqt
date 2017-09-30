package com.nis.access.entity;

import com.nis.access.entity.AcMenuForEasyUiAtrr;
import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("acMenu")
public class AcMenu extends BaseEntity implements Serializable {
	private String menuId;
	private String unitId;
	private String menuNo;
	private String menuName;
	private String parentMenuNo;
	private Long showOrder;
	private String destUrl;
	private Long target;
	private String isvalid;
	private String moduleCode;
	private String ownership;
	private String image;
	private String remark;
	private String ext1;
	private String ext2;
	private String menuType;
	private String code91160;
	private String isreport;
	private String joinReport;
	private String isHavegrant;
	private String isFungrant;
	private String menuTypeName;
	private List<AcMenu> children;
	private List<AcMenu> reportList;
	private AcMenuForEasyUiAtrr attributes;
	private String id;
	private String text;
	private String state;
	private String checked;
	private String roleId;

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<AcMenu> getChildren() {
		return this.children;
	}

	public void setChildren(List<AcMenu> children) {
		this.children = children;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getMenuNo() {
		return this.menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentMenuNo() {
		return this.parentMenuNo;
	}

	public void setParentMenuNo(String parentMenuNo) {
		this.parentMenuNo = parentMenuNo;
	}

	public Long getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}

	public String getDestUrl() {
		return this.destUrl;
	}

	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}

	public Long getTarget() {
		return this.target;
	}

	public void setTarget(Long target) {
		this.target = target;
	}

	public String getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getModuleCode() {
		return this.moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getOwnership() {
		return this.ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return this.ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getIsHavegrant() {
		return this.isHavegrant;
	}

	public void setIsHavegrant(String isHavegrant) {
		this.isHavegrant = isHavegrant;
	}

	public String getIsFungrant() {
		return this.isFungrant;
	}

	public void setIsFungrant(String isFungrant) {
		this.isFungrant = isFungrant;
	}

	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuTypeName() {
		return this.menuTypeName;
	}

	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}

	public String getCode91160() {
		return this.code91160;
	}

	public void setCode91160(String code91160) {
		this.code91160 = code91160;
	}

	public String getIsreport() {
		return this.isreport;
	}

	public void setIsreport(String isreport) {
		this.isreport = isreport;
	}

	public String getJoinReport() {
		return this.joinReport;
	}

	public void setJoinReport(String joinReport) {
		this.joinReport = joinReport;
	}

	public List<AcMenu> getReportList() {
		return this.reportList;
	}

	public void setReportList(List<AcMenu> reportList) {
		this.reportList = reportList;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChecked() {
		return this.checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public AcMenuForEasyUiAtrr getAttributes() {
		return this.attributes;
	}

	public void setAttributes(AcMenuForEasyUiAtrr attributes) {
		this.attributes = attributes;
	}
}