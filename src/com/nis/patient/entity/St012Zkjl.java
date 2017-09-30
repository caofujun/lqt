package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.d;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("st012Zkjl")
public class St012Zkjl extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private Date inDate;
	private String inDeptId;
	private Date outDate;
	private String outDeptId;
	private String bedNo;
	private Integer inDeptDays;
	private Date updTime;
	private String inDeptName;
	private String outDeptName;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	@JsonSerialize(using = d.class)
	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getInDeptId() {
		return this.inDeptId;
	}

	public void setInDeptId(String inDeptId) {
		this.inDeptId = inDeptId;
	}

	@JsonSerialize(using = d.class)
	public Date getOutDate() {
		return this.outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOutDeptId() {
		return this.outDeptId;
	}

	public void setOutDeptId(String outDeptId) {
		this.outDeptId = outDeptId;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public Integer getInDeptDays() {
		return this.inDeptDays;
	}

	public void setInDeptDays(Integer inDeptDays) {
		this.inDeptDays = inDeptDays;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getInDeptName() {
		return this.inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public String getOutDeptName() {
		return this.outDeptName;
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}
}