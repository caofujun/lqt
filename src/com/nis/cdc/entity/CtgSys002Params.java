package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("ctgSys002Params")
public class CtgSys002Params extends BaseEntity implements Serializable {
	private String paramvalue;
	private String comm;
	private String paramid;
	private String paramname;

	public String getParamvalue() {
		return this.paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getParamid() {
		return this.paramid;
	}

	public void setParamid(String paramid) {
		this.paramid = paramid;
	}

	public String getParamname() {
		return this.paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
}