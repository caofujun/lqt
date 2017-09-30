package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("hw007Xjlb")
public class Hw007Xjlb extends BaseEntity implements Serializable {
	private String pathoId;
	private String pathoName;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Date lastAt;
	private Long flag;

	public String getPathoId() {
		return this.pathoId;
	}

	public void setPathoId(String pathoId) {
		this.pathoId = pathoId;
	}

	public String getPathoName() {
		return this.pathoName;
	}

	public void setPathoName(String pathoName) {
		this.pathoName = pathoName;
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

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
}