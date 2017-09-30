package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("ctgSys003Dictdetail")
public class CtgSys003Dictdetail extends BaseEntity implements Serializable {
	private String scopeid;
	private String masterid;
	private String dictid;
	private String dictvalue;

	public String getScopeid() {
		return this.scopeid;
	}

	public void setScopeid(String scopeid) {
		this.scopeid = scopeid;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getDictid() {
		return this.dictid;
	}

	public void setDictid(String dictid) {
		this.dictid = dictid;
	}

	public String getDictvalue() {
		return this.dictvalue;
	}

	public void setDictvalue(String dictvalue) {
		this.dictvalue = dictvalue;
	}
}