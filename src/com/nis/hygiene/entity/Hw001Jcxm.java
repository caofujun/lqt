package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("hw001Jcxm")
public class Hw001Jcxm extends BaseEntity implements Serializable {
	private String classId;
	private String className;
	private String pclassId;
	private String material;
	private String takeArea;
	private String takeMode;
	private String testMode;
	private String defaultModeId;
	private String defaultSampleId;
	private String defaultPosId;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private String memo;
	private Date lastAt;
	private Integer type;
	private Integer flag;
	private List<Hw001Jcxm> children;
	private String id;
	private String text;
	private String state;
	private String djDeptId;
	private String userId;
	private String deptId;
	private Integer isAdd;
	private Integer isSee;

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPclassId() {
		return this.pclassId;
	}

	public void setPclassId(String pclassId) {
		this.pclassId = pclassId;
	}

	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getTakeArea() {
		return this.takeArea;
	}

	public void setTakeArea(String takeArea) {
		this.takeArea = takeArea;
	}

	public String getTakeMode() {
		return this.takeMode;
	}

	public void setTakeMode(String takeMode) {
		this.takeMode = takeMode;
	}

	public String getTestMode() {
		return this.testMode;
	}

	public void setTestMode(String testMode) {
		this.testMode = testMode;
	}

	public String getDefaultModeId() {
		return this.defaultModeId;
	}

	public void setDefaultModeId(String defaultModeId) {
		this.defaultModeId = defaultModeId;
	}

	public String getDefaultSampleId() {
		return this.defaultSampleId;
	}

	public void setDefaultSampleId(String defaultSampleId) {
		this.defaultSampleId = defaultSampleId;
	}

	public String getDefaultPosId() {
		return this.defaultPosId;
	}

	public void setDefaultPosId(String defaultPosId) {
		this.defaultPosId = defaultPosId;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public List<Hw001Jcxm> getChildren() {
		return this.children;
	}

	public void setChildren(List<Hw001Jcxm> children) {
		this.children = children;
	}

	public String getDjDeptId() {
		return this.djDeptId;
	}

	public void setDjDeptId(String djDeptId) {
		this.djDeptId = djDeptId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Integer getIsAdd() {
		return this.isAdd;
	}

	public void setIsAdd(Integer isAdd) {
		this.isAdd = isAdd;
	}

	public Integer getIsSee() {
		return this.isSee;
	}

	public void setIsSee(Integer isSee) {
		this.isSee = isSee;
	}
}