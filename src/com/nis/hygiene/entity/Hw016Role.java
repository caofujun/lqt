package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.hygiene.entity.Hw017RoleRight;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("hw016Role")
public class Hw016Role extends BaseEntity implements Serializable {
	private String roleId;
	private String roleName;
	private String memo;
	private String spCode;
	private String wbCode;
	private Date lastAt;
	private Integer orderIndex;
	private Integer flag;
	private List<Hw017RoleRight> hw017List;
	private String userId;

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<Hw017RoleRight> getHw017List() {
		return this.hw017List;
	}

	public void setHw017List(List<Hw017RoleRight> hw017List) {
		this.hw017List = hw017List;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}