package com.nis.pdca.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.b;
import com.nis.pdca.entity.ZlPdcaFlowDetail;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class ZlPdcaFlow extends BaseEntity implements Serializable {
	private String fuid;
	private String flowNo;
	private String flowName;
	private String flowSteps;
	private Date flowCreatetime;
	private String createName;
	private String createId;
	private Long status;
	private String startDate;
	private String endDate;
	private String statusName;
	private List<ZlPdcaFlowDetail> zlPdcaFlowDetailList;

	public String getFuid() {
		return this.fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	public String getFlowNo() {
		return this.flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getFlowName() {
		return this.flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowSteps() {
		return this.flowSteps;
	}

	public void setFlowSteps(String flowSteps) {
		this.flowSteps = flowSteps;
	}

	@JsonSerialize(using = b.class)
	public Date getFlowCreatetime() {
		return this.flowCreatetime;
	}

	public void setFlowCreatetime(Date flowCreatetime) {
		this.flowCreatetime = flowCreatetime;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateId() {
		return this.createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public List<ZlPdcaFlowDetail> getZlPdcaFlowDetailList() {
		return this.zlPdcaFlowDetailList;
	}

	public void setZlPdcaFlowDetailList(List<ZlPdcaFlowDetail> zlPdcaFlowDetailList) {
		this.zlPdcaFlowDetailList = zlPdcaFlowDetailList;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}