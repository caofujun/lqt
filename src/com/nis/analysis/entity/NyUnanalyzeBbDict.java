package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("nyUnanalyzeBbDict")
public class NyUnanalyzeBbDict extends BaseEntity implements Serializable {
	private String noDictName;
	private String noDictName2;

	public String getNoDictName() {
		return this.noDictName;
	}

	public void setNoDictName(String noDictName) {
		this.noDictName = noDictName;
	}

	public String getNoDictName2() {
		return this.noDictName2;
	}

	public void setNoDictName2(String noDictName2) {
		this.noDictName2 = noDictName2;
	}
}