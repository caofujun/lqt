package com.nis.pdca.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class ZlPdcaFlowDetail extends BaseEntity implements Serializable {
	private Long soccr;
	private Long cdr;
	private Long flag;
	private Long xh;
	private String fuid;
	private String fdSubid;
	private String processNo;
	private String processName;
	private String processContent;
	private String flowName;
	private String soccrName;
	private String cdrName;

	public Long getSoccr() {
		return this.soccr;
	}

	public void setSoccr(Long soccr) {
		this.soccr = soccr;
	}

	public Long getCdr() {
		return this.cdr;
	}

	public void setCdr(Long cdr) {
		this.cdr = cdr;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Long getXh() {
		return this.xh;
	}

	public void setXh(Long xh) {
		this.xh = xh;
	}

	public String getFuid() {
		return this.fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	public String getFdSubid() {
		return this.fdSubid;
	}

	public void setFdSubid(String fdSubid) {
		this.fdSubid = fdSubid;
	}

	public String getProcessNo() {
		return this.processNo;
	}

	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getFlowName() {
		return this.flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getSoccrName() {
		return this.soccrName;
	}

	public void setSoccrName(String soccrName) {
		this.soccrName = soccrName;
	}

	public String getCdrName() {
		return this.cdrName;
	}

	public void setCdrName(String cdrName) {
		this.cdrName = cdrName;
	}

	public String getProcessContent() {
		return this.processContent;
	}

	public void setProcessContent(String processContent) {
		this.processContent = processContent;
	}
}