package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("bk003Ygys")
public class Bk003Ygys extends BaseEntity implements Serializable {
	private String cardRelid;
	private String refid;
	private String factorId;
	private String factorName;
	private Integer isFlag;
	private String id;
	private List<String> factorIdIn;

	public String getCardRelid() {
		return this.cardRelid;
	}

	public void setCardRelid(String cardRelid) {
		this.cardRelid = cardRelid;
	}

	public String getRefid() {
		return this.refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public String getFactorId() {
		return this.factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFactorName() {
		return this.factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public Integer getIsFlag() {
		return this.isFlag;
	}

	public void setIsFlag(Integer isFlag) {
		this.isFlag = isFlag;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getFactorIdIn() {
		return this.factorIdIn;
	}

	public void setFactorIdIn(List<String> factorIdIn) {
		this.factorIdIn = factorIdIn;
	}
}