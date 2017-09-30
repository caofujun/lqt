package com.nis.icu.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.c;
import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class Gm004Jcmx extends BaseEntity implements Serializable {
	private Date inDeptDt;
	private Date creationdate;
	private String typeid;
	private String zyid;
	private Integer isnewborn;
	private String neonatebw;
	private String deptid;
	private Long isadd;
	private Double interventionmarks;
	private String operatorName;
	private Date operatorDt;
	private Date startAt;
	private Date stopAt;
	private String outbreakTypeName;
	private Integer days;
	private String qid;
	private Integer qcount;
	private String isTest;
	private Date dngLastDt;
	private Date zxjmLastDt;
	private Date hxjLastDt;

	public Date getDngLastDt() {
		return this.dngLastDt;
	}

	public void setDngLastDt(Date dngLastDt) {
		this.dngLastDt = dngLastDt;
	}

	public Date getZxjmLastDt() {
		return this.zxjmLastDt;
	}

	public void setZxjmLastDt(Date zxjmLastDt) {
		this.zxjmLastDt = zxjmLastDt;
	}

	public Date getHxjLastDt() {
		return this.hxjLastDt;
	}

	public void setHxjLastDt(Date hxjLastDt) {
		this.hxjLastDt = hxjLastDt;
	}

	public Date getInDeptDt() {
		return this.inDeptDt;
	}

	public void setInDeptDt(Date inDeptDt) {
		this.inDeptDt = inDeptDt;
	}

	@JsonSerialize(using = c.class)
	public Date getCreationdate() {
		return this.creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public String getTypeid() {
		return this.typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public Integer getIsnewborn() {
		return this.isnewborn;
	}

	public void setIsnewborn(Integer isnewborn) {
		this.isnewborn = isnewborn;
	}

	public String getNeonatebw() {
		return this.neonatebw;
	}

	public void setNeonatebw(String neonatebw) {
		this.neonatebw = neonatebw;
	}

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Long getIsadd() {
		return this.isadd;
	}

	public void setIsadd(Long isadd) {
		this.isadd = isadd;
	}

	public Double getInterventionmarks() {
		return this.interventionmarks;
	}

	public void setInterventionmarks(Double interventionmarks) {
		this.interventionmarks = interventionmarks;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getOperatorDt() {
		return this.operatorDt;
	}

	public void setOperatorDt(Date operatorDt) {
		this.operatorDt = operatorDt;
	}

	@JsonSerialize(using = c.class)
	public Date getStartAt() {
		return this.startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	@JsonSerialize(using = c.class)
	public Date getStopAt() {
		return this.stopAt;
	}

	public void setStopAt(Date stopAt) {
		this.stopAt = stopAt;
	}

	public String getOutbreakTypeName() {
		return this.outbreakTypeName;
	}

	public void setOutbreakTypeName(String outbreakTypeName) {
		this.outbreakTypeName = outbreakTypeName;
	}

	public Integer getDays() {
		return this.days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getQid() {
		return this.qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public Integer getQcount() {
		return this.qcount;
	}

	public void setQcount(Integer qcount) {
		this.qcount = qcount;
	}

	public String getIsTest() {
		return this.isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}
}