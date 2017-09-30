package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("hw201Jcdmb")
public class Hw201Jcdmb extends BaseEntity implements Serializable {
	private String templetId;
	private String templetName;
	private String deptId;
	private String classId;
	private String placeId;
	private String takeModeId;
	private String sampleId;
	private String posId;
	private Date lastAt;
	private Integer flag;
	private String takeType;
	private String takeBy;
	private String deptName;
	private String className;
	private String placeName;
	private String sampleName;
	private String takeModeName;
	private String posName;
	private String takeTypeName;
	private String takeByName;
	private String djId;
	private Integer type;
	private String userId;
	private Integer isCurrDept;
	private String djDeptId;

	public String getTempletId() {
		return this.templetId;
	}

	public void setTempletId(String templetId) {
		this.templetId = templetId;
	}

	public String getTempletName() {
		return this.templetName;
	}

	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getTakeModeId() {
		return this.takeModeId;
	}

	public void setTakeModeId(String takeModeId) {
		this.takeModeId = takeModeId;
	}

	public String getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getPosId() {
		return this.posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getTakeType() {
		return this.takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}

	public String getTakeBy() {
		return this.takeBy;
	}

	public void setTakeBy(String takeBy) {
		this.takeBy = takeBy;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPlaceName() {
		return this.placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getSampleName() {
		return this.sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getTakeModeName() {
		return this.takeModeName;
	}

	public void setTakeModeName(String takeModeName) {
		this.takeModeName = takeModeName;
	}

	public String getPosName() {
		return this.posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getTakeTypeName() {
		return this.takeTypeName;
	}

	public void setTakeTypeName(String takeTypeName) {
		this.takeTypeName = takeTypeName;
	}

	public String getTakeByName() {
		return this.takeByName;
	}

	public void setTakeByName(String takeByName) {
		this.takeByName = takeByName;
	}

	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIsCurrDept() {
		return this.isCurrDept;
	}

	public void setIsCurrDept(Integer isCurrDept) {
		this.isCurrDept = isCurrDept;
	}

	public String getDjDeptId() {
		return this.djDeptId;
	}

	public void setDjDeptId(String djDeptId) {
		this.djDeptId = djDeptId;
	}
}