package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("hw009Kssq")
public class Hw009Kssq extends BaseEntity implements Serializable {
	private String userId;
	private String deptId;
	private String deptName;
	private String defValue;

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

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDefValue() {
		return this.defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
}