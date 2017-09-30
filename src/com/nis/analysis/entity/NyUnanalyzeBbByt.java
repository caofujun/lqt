package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("nyUnanalyzeBbByt")
public class NyUnanalyzeBbByt extends BaseEntity implements Serializable {
	private String bytid;
	private String bbid;
	private String bbmc;
	private String bytName;

	public String getBytid() {
		return this.bytid;
	}

	public void setBytid(String bytid) {
		this.bytid = bytid;
	}

	public String getBbid() {
		return this.bbid;
	}

	public void setBbid(String bbid) {
		this.bbid = bbid;
	}

	public String getBbmc() {
		return this.bbmc;
	}

	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	public String getBytName() {
		return this.bytName;
	}

	public void setBytName(String bytName) {
		this.bytName = bytName;
	}
}