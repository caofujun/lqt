package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.bb;
import com.nis.comm.enums.g;
import com.nis.comm.enums.w;
import com.nis.comm.enums.x;
import com.nis.comm.enums.y;
import com.nis.comm.enums.z;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("bk002Grzd")
public class Bk002Grzd extends BaseEntity implements Serializable {
	private String relid;
	private String refid;
	private Integer sn;
	private String diagnoseId;
	private String infectDiagnId;
	private String infectDiagnName;
	private Date infectDate;
	private String infectDeptId;
	private String infectNexus;
	private String infectDeptName;
	private String subId;
	private String incisionGrade;
	private String woundHealing;
	private String operId;
	private Integer isselect;
	private Integer infectType;
	private Integer deleted;
	private Date confirmDt;
	private String opeRelid;
	private String opeName;
	private String customDiagnId;
	private String customDiagnName;
	private String memo;
	private Integer bkType;
	private String lastoperName;
	private Date lastoperDate;
	private String bkTypename;
	private Integer incisionType;
	private String infectDeptNameEdit;
	private String zdyj2;
	private String dgxggrId;
	private String relation;
	private String jbzg;
	private String infectRelation;
	private Integer infectCategory;
	private Date authAt;
	private String authUserid;
	private String authUsername;
	private String infectKind;
	private String returnReason;
	private String delReason;
	private Integer authStatus;
	private Date jbzgDate;
	private String isPrint;
	private String infectTypeName;
	private String confirmDrName;
	private String regId;
	private String infectCategoryName;
	private String bkTypeName;
	private String relationCode;
	private String incisionTypeCode;
	private String zyid;
	private String authStatusName;
	private String[] factorIds;
	private String[] testOrderNos;
	private List<Bk002Grzd> bk002List;
	private String action;
	private String flInfectName;
	private String flInfectCode;

	public String getRelid() {
		return this.relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getRefid() {
		return this.refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getDiagnoseId() {
		return this.diagnoseId;
	}

	public void setDiagnoseId(String diagnoseId) {
		this.diagnoseId = diagnoseId;
	}

	public String getInfectDiagnId() {
		return this.infectDiagnId;
	}

	public void setInfectDiagnId(String infectDiagnId) {
		this.infectDiagnId = infectDiagnId;
	}

	public String getInfectDiagnName() {
		return this.infectDiagnName;
	}

	public void setInfectDiagnName(String infectDiagnName) {
		this.infectDiagnName = infectDiagnName;
	}

	public Date getInfectDate() {
		return this.infectDate;
	}

	public void setInfectDate(Date infectDate) {
		this.infectDate = infectDate;
	}

	public String getInfectDeptId() {
		return this.infectDeptId;
	}

	public void setInfectDeptId(String infectDeptId) {
		this.infectDeptId = infectDeptId;
	}

	public String getInfectNexus() {
		return this.infectNexus;
	}

	public void setInfectNexus(String infectNexus) {
		this.infectNexus = infectNexus;
	}

	public String getInfectDeptName() {
		return this.infectDeptName;
	}

	public void setInfectDeptName(String infectDeptName) {
		this.infectDeptName = infectDeptName;
	}

	public String getSubId() {
		return this.subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getIncisionGrade() {
		return this.incisionGrade;
	}

	public void setIncisionGrade(String incisionGrade) {
		this.incisionGrade = incisionGrade;
	}

	public String getWoundHealing() {
		return this.woundHealing;
	}

	public void setWoundHealing(String woundHealing) {
		this.woundHealing = woundHealing;
	}

	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Integer getIsselect() {
		return this.isselect;
	}

	public void setIsselect(Integer isselect) {
		this.isselect = isselect;
	}

	public Integer getInfectType() {
		return this.infectType;
	}

	public void setInfectType(Integer infectType) {
		this.infectType = infectType;
	}

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getConfirmDt() {
		return this.confirmDt;
	}

	public void setConfirmDt(Date confirmDt) {
		this.confirmDt = confirmDt;
	}

	public String getOpeRelid() {
		return this.opeRelid;
	}

	public void setOpeRelid(String opeRelid) {
		this.opeRelid = opeRelid;
	}

	public String getOpeName() {
		return this.opeName;
	}

	public void setOpeName(String opeName) {
		this.opeName = opeName;
	}

	public String getCustomDiagnId() {
		return this.customDiagnId;
	}

	public void setCustomDiagnId(String customDiagnId) {
		this.customDiagnId = customDiagnId;
	}

	public String getCustomDiagnName() {
		return this.customDiagnName;
	}

	public void setCustomDiagnName(String customDiagnName) {
		this.customDiagnName = customDiagnName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getBkType() {
		return this.bkType;
	}

	public void setBkType(Integer bkType) {
		this.bkType = bkType;
	}

	public String getLastoperName() {
		return this.lastoperName;
	}

	public void setLastoperName(String lastoperName) {
		this.lastoperName = lastoperName;
	}

	public Date getLastoperDate() {
		return this.lastoperDate;
	}

	public void setLastoperDate(Date lastoperDate) {
		this.lastoperDate = lastoperDate;
	}

	public String getBkTypename() {
		return this.bkTypename;
	}

	public void setBkTypename(String bkTypename) {
		this.bkTypename = bkTypename;
	}

	public Integer getIncisionType() {
		return this.incisionType;
	}

	public void setIncisionType(Integer incisionType) {
		this.incisionType = incisionType;
	}

	public String getInfectDeptNameEdit() {
		return this.infectDeptNameEdit;
	}

	public void setInfectDeptNameEdit(String infectDeptNameEdit) {
		this.infectDeptNameEdit = infectDeptNameEdit;
	}

	public String getZdyj2() {
		return this.zdyj2;
	}

	public void setZdyj2(String zdyj2) {
		this.zdyj2 = zdyj2;
	}

	public String getDgxggrId() {
		return this.dgxggrId;
	}

	public void setDgxggrId(String dgxggrId) {
		this.dgxggrId = dgxggrId;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getJbzg() {
		return this.jbzg;
	}

	public void setJbzg(String jbzg) {
		this.jbzg = jbzg;
	}

	public String getInfectRelation() {
		return this.infectRelation;
	}

	public void setInfectRelation(String infectRelation) {
		this.infectRelation = infectRelation;
	}

	public String getInfectTypeName() {
		return y.h(this.infectType).getName();
	}

	public void setInfectTypeName(String infectTypeName) {
		this.infectTypeName = infectTypeName;
	}

	public String getConfirmDrName() {
		return this.confirmDrName;
	}

	public void setConfirmDrName(String confirmDrName) {
		this.confirmDrName = confirmDrName;
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Integer getInfectCategory() {
		return this.infectCategory;
	}

	public void setInfectCategory(Integer infectCategory) {
		this.infectCategory = infectCategory;
	}

	public String getInfectCategoryName() {
		return x.g(this.infectCategory).getName();
	}

	public void setInfectCategoryName(String infectCategoryName) {
		this.infectCategoryName = infectCategoryName;
	}

	public String getBkTypeName() {
		return g.a(this.bkType).getName();
	}

	public void setBkTypeName(String bkTypeName) {
		this.bkTypeName = bkTypeName;
	}

	public String getRelationCode() {
		return bb.G(this.relation).getValue();
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public String getIncisionTypeCode() {
		return w.A(this.memo).getValue();
	}

	public void setIncisionTypeCode(String incisionTypeCode) {
		this.incisionTypeCode = incisionTypeCode;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public Date getAuthAt() {
		return this.authAt;
	}

	public void setAuthAt(Date authAt) {
		this.authAt = authAt;
	}

	public String getAuthUserid() {
		return this.authUserid;
	}

	public void setAuthUserid(String authUserid) {
		this.authUserid = authUserid;
	}

	public String getAuthUsername() {
		return this.authUsername;
	}

	public void setAuthUsername(String authUsername) {
		this.authUsername = authUsername;
	}

	public String getInfectKind() {
		return this.infectKind;
	}

	public void setInfectKind(String infectKind) {
		this.infectKind = infectKind;
	}

	public String getReturnReason() {
		return this.returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getDelReason() {
		return this.delReason;
	}

	public void setDelReason(String delReason) {
		this.delReason = delReason;
	}

	public Integer getAuthStatus() {
		return this.authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getAuthStatusName() {
		return z.i(this.authStatus).getName();
	}

	public void setAuthStatusName(String authStatusName) {
		this.authStatusName = authStatusName;
	}

	public String[] getFactorIds() {
		return this.factorIds;
	}

	public void setFactorIds(String[] factorIds) {
		this.factorIds = factorIds;
	}

	public String[] getTestOrderNos() {
		return this.testOrderNos;
	}

	public void setTestOrderNos(String[] testOrderNos) {
		this.testOrderNos = testOrderNos;
	}

	public List<Bk002Grzd> getBk002List() {
		return this.bk002List;
	}

	public void setBk002List(List<Bk002Grzd> bk002List) {
		this.bk002List = bk002List;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getJbzgDate() {
		return this.jbzgDate;
	}

	public void setJbzgDate(Date jbzgDate) {
		this.jbzgDate = jbzgDate;
	}

	public String getIsPrint() {
		return this.isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	public String getFlInfectName() {
		return this.flInfectName;
	}

	public void setFlInfectName(String flInfectName) {
		this.flInfectName = flInfectName;
	}

	public String getFlInfectCode() {
		return this.flInfectCode;
	}

	public void setFlInfectCode(String flInfectCode) {
		this.flInfectCode = flInfectCode;
	}
}