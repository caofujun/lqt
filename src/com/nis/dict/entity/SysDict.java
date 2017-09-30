package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.d;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("sysDict")
public class SysDict extends BaseEntity implements Serializable, Cloneable {
	private String id;
	private String parentCode;
	private String dictTypeCode;
	private String dictCode;
	private String dictName;
	private String pinyin;
	private String dictStatus;
	private String dictStatusName;
	private Long sequenceNumber;
	private String extParam1;
	private String extParam2;
	private String remark;
	private String scopeLevel;
	private String scopelevelName;
	private String unitId;
	private String depNo;
	private String text;
	private List<SysDict> children;
	private String unitName;
	private boolean checked;
	private Date updateTime;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDictTypeCode() {
		return this.dictTypeCode;
	}

	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
	}

	public String getDictCode() {
		return this.dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getDictStatus() {
		return this.dictStatus;
	}

	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}

	public Long getSequenceNumber() {
		return this.sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getExtParam1() {
		return this.extParam1;
	}

	public void setExtParam1(String extParam1) {
		this.extParam1 = extParam1;
	}

	public String getExtParam2() {
		return this.extParam2;
	}

	public void setExtParam2(String extParam2) {
		this.extParam2 = extParam2;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<SysDict> getChildren() {
		return this.children;
	}

	public void setChildren(List<SysDict> children) {
		this.children = children;
	}

	public String getDictStatusName() {
		return this.dictStatusName;
	}

	public void setDictStatusName(String dictStatusName) {
		this.dictStatusName = dictStatusName;
	}

	public String getScopelevelName() {
		return this.scopelevelName;
	}

	public void setScopelevelName(String scopelevelName) {
		this.scopelevelName = scopelevelName;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public SysDict clone() {
		SysDict sysDict;
		try {
			sysDict = (SysDict) super.clone();
		} catch (CloneNotSupportedException arg2) {
			sysDict = new SysDict();
			arg2.printStackTrace();
		}

		return sysDict;
	}

	@JsonSerialize(using = d.class)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}