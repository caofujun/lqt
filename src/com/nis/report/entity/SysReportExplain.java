package com.nis.report.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("sysReportExplain")
public class SysReportExplain extends BaseEntity implements Serializable {
	private String reportName;
	private String reportFormula;
	private String reportRule;
	private String reportDesc;
	private String reportId;
	private String formulaTitle;
	private Integer seq;
	private String[] reportRules;
	private String reportType;
	private String reportShowName;

	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportFormula() {
		return this.reportFormula;
	}

	public void setReportFormula(String reportFormula) {
		this.reportFormula = reportFormula;
	}

	public String getReportRule() {
		return this.reportRule;
	}

	public void setReportRule(String reportRule) {
		this.reportRule = reportRule;
	}

	public String getReportDesc() {
		return this.reportDesc;
	}

	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}

	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getFormulaTitle() {
		return this.formulaTitle;
	}

	public void setFormulaTitle(String formulaTitle) {
		this.formulaTitle = formulaTitle;
	}

	public String[] getReportRules() {
		return this.reportRules;
	}

	public void setReportRules(String[] reportRules) {
		this.reportRules = reportRules;
	}

	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportShowName() {
		return this.reportShowName;
	}

	public void setReportShowName(String reportShowName) {
		this.reportShowName = reportShowName;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}