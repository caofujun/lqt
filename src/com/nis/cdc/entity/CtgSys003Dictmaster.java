package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("ctgSys003Dictmaster")
public class CtgSys003Dictmaster extends BaseEntity implements Serializable {
	private String scopeid;
	private String scopename;
	private String masterid;
	private String description;

	public String getScopeid() {
		return this.scopeid;
	}

	public void setScopeid(String scopeid) {
		this.scopeid = scopeid;
	}

	public String getScopename() {
		return this.scopename;
	}

	public void setScopename(String scopename) {
		this.scopename = scopename;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}