package com.nis.homepage.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("sysHpLayout")
public class SysHpLayout extends BaseEntity implements Serializable {
	private String id;
	private String layoutCode;
	private String layoutName;
	private String layoutUrl;
	private String layoutStatus;
	private String imgUrl;
	private String remark;

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

	public String getLayoutName() {
		return this.layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public String getLayoutUrl() {
		return this.layoutUrl;
	}

	public void setLayoutUrl(String layoutUrl) {
		this.layoutUrl = layoutUrl;
	}

	public String getLayoutStatus() {
		return this.layoutStatus;
	}

	public void setLayoutStatus(String layoutStatus) {
		this.layoutStatus = layoutStatus;
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
}