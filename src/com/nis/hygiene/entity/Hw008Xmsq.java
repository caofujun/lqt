package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("hw008Xmsq")
public class Hw008Xmsq extends BaseEntity implements Serializable {
	private String deptId;
	private String userId;
	private String classId;
	private Integer selfView;
	private Integer selfAdd;
	private Integer selfResult;
	private Integer otherView;
	private Integer otherAdd;
	private Integer otherResult;
	private String className;
	private List<String> roleIdList;
	private List<String> deptIdList;
	private List<Hw008Xmsq> hw008List;

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public List<String> getRoleIdList() {
		return this.roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public List<String> getDeptIdList() {
		return this.deptIdList;
	}

	public void setDeptIdList(List<String> deptIdList) {
		this.deptIdList = deptIdList;
	}

	public List<Hw008Xmsq> getHw008List() {
		return this.hw008List;
	}

	public void setHw008List(List<Hw008Xmsq> hw008List) {
		this.hw008List = hw008List;
	}
}