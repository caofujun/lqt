package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("xl011DicPatho")
public class Xl011DicPatho extends BaseEntity implements Serializable {
	private String pathoid;
	private String pathoname;
	private Integer pathoorder;
	private Integer ifdisp;
	private String remark;
	private String memoryid;

	public String getPathoid() {
		return this.pathoid;
	}

	public void setPathoid(String pathoid) {
		this.pathoid = pathoid;
	}

	public String getPathoname() {
		return this.pathoname;
	}

	public void setPathoname(String pathoname) {
		this.pathoname = pathoname;
	}

	public Integer getPathoorder() {
		return this.pathoorder;
	}

	public void setPathoorder(Integer pathoorder) {
		this.pathoorder = pathoorder;
	}

	public Integer getIfdisp() {
		return this.ifdisp;
	}

	public void setIfdisp(Integer ifdisp) {
		this.ifdisp = ifdisp;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMemoryid() {
		return this.memoryid;
	}

	public void setMemoryid(String memoryid) {
		this.memoryid = memoryid;
	}
}