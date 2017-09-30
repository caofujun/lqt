package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("hw002Jsbz")
public class Hw002Jsbz extends BaseEntity implements Serializable {
	private String itemId;
	private String itemName;
	private String classId;
	private String condition;
	private String criterion;
	private String unit;
	private String memo;
	private Date lastAt;
	private Integer flag;
	private String pclassId;
	private String pclassName;
	private String className;

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCriterion() {
		return this.criterion;
	}

	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getPclassName() {
		return this.pclassName;
	}

	public void setPclassName(String pclassName) {
		this.pclassName = pclassName;
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
}