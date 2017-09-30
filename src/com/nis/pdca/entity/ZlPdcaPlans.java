package com.nis.pdca.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.b;
import com.nis.pdca.entity.ZlPdcaPlansDetail;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class ZlPdcaPlans extends BaseEntity implements Serializable {
	private String puid;
	private String planId;
	private String planName;
	private String planContent;
	private String planExplain;
	private String curNode;
	private String deptId;
	private String deptName;
	private String createrId;
	private String createrName;
	private String jcxm;
	private String yqmb;
	private String zlly;
	private String jcjg;
	private String wtxs;
	private String yyfx;
	private String xmfzr;
	private String cjry;
	private String isPdca;
	private Date createDate;
	private Long status;
	private String flowId;
	private String flowName;
	private String fuid;
	private String pzId;
	private String startDate;
	private String endDate;
	private String saveOrAs;
	private List<ZlPdcaPlansDetail> zlPdcaPlansDetailList;
	private String wcd;

	public String getPuid() {
		return this.puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	public String getPlanId() {
		return this.planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanContent() {
		return this.planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}

	public String getPlanExplain() {
		return this.planExplain;
	}

	public void setPlanExplain(String planExplain) {
		this.planExplain = planExplain;
	}

	public String getCurNode() {
		return this.curNode;
	}

	public void setCurNode(String curNode) {
		this.curNode = curNode;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return this.createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	@JsonSerialize(using = b.class)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getFlowId() {
		return this.flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowName() {
		return this.flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFuid() {
		return this.fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
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

	public List<ZlPdcaPlansDetail> getZlPdcaPlansDetailList() {
		return this.zlPdcaPlansDetailList;
	}

	public void setZlPdcaPlansDetailList(List<ZlPdcaPlansDetail> zlPdcaPlansDetailList) {
		this.zlPdcaPlansDetailList = zlPdcaPlansDetailList;
	}

	public String getWcd() {
		return this.wcd;
	}

	public void setWcd(String wcd) {
		this.wcd = wcd;
	}

	public String getSaveOrAs() {
		return this.saveOrAs;
	}

	public void setSaveOrAs(String saveOrAs) {
		this.saveOrAs = saveOrAs;
	}

	public String getPzId() {
		return this.pzId;
	}

	public void setPzId(String pzId) {
		this.pzId = pzId;
	}

	public String getJcxm() {
		return this.jcxm;
	}

	public void setJcxm(String jcxm) {
		this.jcxm = jcxm;
	}

	public String getYqmb() {
		return this.yqmb;
	}

	public void setYqmb(String yqmb) {
		this.yqmb = yqmb;
	}

	public String getZlly() {
		return this.zlly;
	}

	public void setZlly(String zlly) {
		this.zlly = zlly;
	}

	public String getJcjg() {
		return this.jcjg;
	}

	public void setJcjg(String jcjg) {
		this.jcjg = jcjg;
	}

	public String getWtxs() {
		return this.wtxs;
	}

	public void setWtxs(String wtxs) {
		this.wtxs = wtxs;
	}

	public String getYyfx() {
		return this.yyfx;
	}

	public void setYyfx(String yyfx) {
		this.yyfx = yyfx;
	}

	public String getXmfzr() {
		return this.xmfzr;
	}

	public void setXmfzr(String xmfzr) {
		this.xmfzr = xmfzr;
	}

	public String getCjry() {
		return this.cjry;
	}

	public void setCjry(String cjry) {
		this.cjry = cjry;
	}

	public String getIsPdca() {
		return this.isPdca;
	}

	public void setIsPdca(String isPdca) {
		this.isPdca = isPdca;
	}
}