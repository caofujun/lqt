package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("ctgSys007Dictcontagion")
public class CtgSys007Dictcontagion extends BaseEntity implements Serializable {
	private String diseaseid;
	private String diseasename;
	private String zjf;
	private String icd10;
	private Long caninput;
	private Long sexcard;
	private Long sortseq;
	private String parentid;
	private String kindid;
	private String kindname;
	private Long isintestinal;
	private Long isrespiratory;
	private Long isnatural;
	private Long ishemic;
	private Long issexspread;
	private Double scopetime;
	private Long repeatCycle;
	private Long isimportant;
	private List<CtgSys007Dictcontagion> children;
	private String iconCls;

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<CtgSys007Dictcontagion> getChildren() {
		return this.children;
	}

	public void setChildren(List<CtgSys007Dictcontagion> children) {
		this.children = children;
	}

	public Long getRepeatCycle() {
		return this.repeatCycle;
	}

	public void setRepeatCycle(Long repeatCycle) {
		this.repeatCycle = repeatCycle;
	}

	public Long getIsimportant() {
		return this.isimportant;
	}

	public void setIsimportant(Long isimportant) {
		this.isimportant = isimportant;
	}

	public String getDiseaseid() {
		return this.diseaseid;
	}

	public void setDiseaseid(String diseaseid) {
		this.diseaseid = diseaseid;
	}

	public String getDiseasename() {
		return this.diseasename;
	}

	public void setDiseasename(String diseasename) {
		this.diseasename = diseasename;
	}

	public String getZjf() {
		return this.zjf;
	}

	public void setZjf(String zjf) {
		this.zjf = zjf;
	}

	public String getIcd10() {
		return this.icd10;
	}

	public void setIcd10(String icd10) {
		this.icd10 = icd10;
	}

	public Long getCaninput() {
		return this.caninput;
	}

	public void setCaninput(Long caninput) {
		this.caninput = caninput;
	}

	public Long getSexcard() {
		return this.sexcard;
	}

	public void setSexcard(Long sexcard) {
		this.sexcard = sexcard;
	}

	public Long getSortseq() {
		return this.sortseq;
	}

	public void setSortseq(Long sortseq) {
		this.sortseq = sortseq;
	}

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getKindid() {
		return this.kindid;
	}

	public void setKindid(String kindid) {
		this.kindid = kindid;
	}

	public String getKindname() {
		return this.kindname;
	}

	public void setKindname(String kindname) {
		this.kindname = kindname;
	}

	public Long getIsintestinal() {
		return this.isintestinal;
	}

	public void setIsintestinal(Long isintestinal) {
		this.isintestinal = isintestinal;
	}

	public Long getIsrespiratory() {
		return this.isrespiratory;
	}

	public void setIsrespiratory(Long isrespiratory) {
		this.isrespiratory = isrespiratory;
	}

	public Long getIsnatural() {
		return this.isnatural;
	}

	public void setIsnatural(Long isnatural) {
		this.isnatural = isnatural;
	}

	public Long getIshemic() {
		return this.ishemic;
	}

	public void setIshemic(Long ishemic) {
		this.ishemic = ishemic;
	}

	public Long getIssexspread() {
		return this.issexspread;
	}

	public void setIssexspread(Long issexspread) {
		this.issexspread = issexspread;
	}

	public Double getScopetime() {
		return this.scopetime;
	}

	public void setScopetime(Double scopetime) {
		this.scopetime = scopetime;
	}
}