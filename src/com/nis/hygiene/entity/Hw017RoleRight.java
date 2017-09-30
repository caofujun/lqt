package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("hw017RoleRight")
public class Hw017RoleRight extends BaseEntity implements Serializable {
	private String roleId;
	private String classId;
	private Integer selfView;
	private Integer selfAdd;
	private Integer selfResult;
	private Integer otherView;
	private Integer otherAdd;
	private Integer otherResult;
	private String className;

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Integer getSelfView() {
		return this.selfView;
	}

	public void setSelfView(Integer selfView) {
		this.selfView = selfView;
	}

	public Integer getSelfAdd() {
		return this.selfAdd;
	}

	public void setSelfAdd(Integer selfAdd) {
		this.selfAdd = selfAdd;
	}

	public Integer getSelfResult() {
		return this.selfResult;
	}

	public void setSelfResult(Integer selfResult) {
		this.selfResult = selfResult;
	}

	public Integer getOtherView() {
		return this.otherView;
	}

	public void setOtherView(Integer otherView) {
		this.otherView = otherView;
	}

	public Integer getOtherAdd() {
		return this.otherAdd;
	}

	public void setOtherAdd(Integer otherAdd) {
		this.otherAdd = otherAdd;
	}

	public Integer getOtherResult() {
		return this.otherResult;
	}

	public void setOtherResult(Integer otherResult) {
		this.otherResult = otherResult;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}