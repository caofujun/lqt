package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("xl009DicInfectdiag")
public class Xl009DicInfectdiag extends BaseEntity implements Serializable {
	private String infectId;
	private String indiagid;
	private String indiagname;
	private String systemkindid;
	private String systemkindname;
	private String remark;
	private String reportorder;
	private Integer torder;
	private String memoryid;
	private List<Xl009DicInfectdiag> children;
	private String id;
	private String text;
	private String state;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Xl009DicInfectdiag> getChildren() {
		return this.children;
	}

	public void setChildren(List<Xl009DicInfectdiag> children) {
		this.children = children;
	}

	public String getSystemkindname() {
		return this.systemkindname;
	}

	public void setSystemkindname(String systemkindname) {
		this.systemkindname = systemkindname;
	}

	public String getInfectId() {
		return this.infectId;
	}

	public void setInfectId(String infectId) {
		this.infectId = infectId;
	}

	public String getIndiagid() {
		return this.indiagid;
	}

	public void setIndiagid(String indiagid) {
		this.indiagid = indiagid;
	}

	public String getIndiagname() {
		return this.indiagname;
	}

	public void setIndiagname(String indiagname) {
		this.indiagname = indiagname;
	}

	public String getSystemkindid() {
		return this.systemkindid;
	}

	public void setSystemkindid(String systemkindid) {
		this.systemkindid = systemkindid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReportorder() {
		return this.reportorder;
	}

	public void setReportorder(String reportorder) {
		this.reportorder = reportorder;
	}

	public Integer getTorder() {
		return this.torder;
	}

	public void setTorder(Integer torder) {
		this.torder = torder;
	}

	public String getMemoryid() {
		return this.memoryid;
	}

	public void setMemoryid(String memoryid) {
		this.memoryid = memoryid;
	}
}