package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("xl012DicTroche")
public class Xl012DicTroche extends BaseEntity implements Serializable {
	private String trocheid;
	private String trochename;
	private String memoryid;
	private Integer xhlUse;

	public String getTrocheid() {
		return this.trocheid;
	}

	public void setTrocheid(String trocheid) {
		this.trocheid = trocheid;
	}

	public String getTrochename() {
		return this.trochename;
	}

	public void setTrochename(String trochename) {
		this.trochename = trochename;
	}

	public String getMemoryid() {
		return this.memoryid;
	}

	public void setMemoryid(String memoryid) {
		this.memoryid = memoryid;
	}

	public Integer getXhlUse() {
		return this.xhlUse;
	}

	public void setXhlUse(Integer xhlUse) {
		this.xhlUse = xhlUse;
	}
}