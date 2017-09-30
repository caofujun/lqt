package com.nis.outbreak.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("by007Config")
public class By007Config extends BaseEntity implements Serializable {
	private Integer absoluteContrast;
	private Double absoluteP;
	private Double relativeP;
	private String showTab;
	private String typeId;
	private String typeName;
	private Integer relativeDays;
	private Integer grade;
	private Integer relativeAppear;
	private Integer relativeContrast;
	private Double relativeThreshold;
	private Integer absoluteDays;
	private Integer absoluteAppear;
	private String relativePeople;
	private String relativeProbability;
	private String relativeAll;
	private String absolutePeople;
	private String relativeMasterName;
	private String relativeDetailName;
	private String absoluteMasterName;
	private String absoluteDetailName;
	private String extraSql;
	private Integer orderBy;
	private String collectProc;
	private boolean checked;
	private Integer showType;
	private List<By007Config> configList;

	public Integer getAbsoluteContrast() {
		return this.absoluteContrast;
	}

	public void setAbsoluteContrast(Integer absoluteContrast) {
		this.absoluteContrast = absoluteContrast;
	}

	public Double getAbsoluteP() {
		return this.absoluteP;
	}

	public void setAbsoluteP(Double absoluteP) {
		this.absoluteP = absoluteP;
	}

	public Double getRelativeP() {
		return this.relativeP;
	}

	public void setRelativeP(Double relativeP) {
		this.relativeP = relativeP;
	}

	public String getShowTab() {
		return this.showTab;
	}

	public void setShowTab(String showTab) {
		this.showTab = showTab;
	}

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getRelativeDays() {
		return this.relativeDays;
	}

	public void setRelativeDays(Integer relativeDays) {
		this.relativeDays = relativeDays;
	}

	public Integer getRelativeAppear() {
		return this.relativeAppear;
	}

	public void setRelativeAppear(Integer relativeAppear) {
		this.relativeAppear = relativeAppear;
	}

	public Integer getRelativeContrast() {
		return this.relativeContrast;
	}

	public void setRelativeContrast(Integer relativeContrast) {
		this.relativeContrast = relativeContrast;
	}

	public Double getRelativeThreshold() {
		return this.relativeThreshold;
	}

	public void setRelativeThreshold(Double relativeThreshold) {
		this.relativeThreshold = relativeThreshold;
	}

	public Integer getAbsoluteDays() {
		return this.absoluteDays;
	}

	public void setAbsoluteDays(Integer absoluteDays) {
		this.absoluteDays = absoluteDays;
	}

	public Integer getAbsoluteAppear() {
		return this.absoluteAppear;
	}

	public void setAbsoluteAppear(Integer absoluteAppear) {
		this.absoluteAppear = absoluteAppear;
	}

	public String getRelativePeople() {
		return this.relativePeople;
	}

	public void setRelativePeople(String relativePeople) {
		this.relativePeople = relativePeople;
	}

	public String getRelativeProbability() {
		return this.relativeProbability;
	}

	public void setRelativeProbability(String relativeProbability) {
		this.relativeProbability = relativeProbability;
	}

	public String getRelativeAll() {
		return this.relativeAll;
	}

	public void setRelativeAll(String relativeAll) {
		this.relativeAll = relativeAll;
	}

	public String getAbsolutePeople() {
		return this.absolutePeople;
	}

	public void setAbsolutePeople(String absolutePeople) {
		this.absolutePeople = absolutePeople;
	}

	public String getRelativeMasterName() {
		return this.relativeMasterName;
	}

	public void setRelativeMasterName(String relativeMasterName) {
		this.relativeMasterName = relativeMasterName;
	}

	public String getRelativeDetailName() {
		return this.relativeDetailName;
	}

	public void setRelativeDetailName(String relativeDetailName) {
		this.relativeDetailName = relativeDetailName;
	}

	public String getAbsoluteMasterName() {
		return this.absoluteMasterName;
	}

	public void setAbsoluteMasterName(String absoluteMasterName) {
		this.absoluteMasterName = absoluteMasterName;
	}

	public String getAbsoluteDetailName() {
		return this.absoluteDetailName;
	}

	public void setAbsoluteDetailName(String absoluteDetailName) {
		this.absoluteDetailName = absoluteDetailName;
	}

	public String getExtraSql() {
		return this.extraSql;
	}

	public void setExtraSql(String extraSql) {
		this.extraSql = extraSql;
	}

	public Integer getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getCollectProc() {
		return this.collectProc;
	}

	public void setCollectProc(String collectProc) {
		this.collectProc = collectProc;
	}

	public Integer getShowType() {
		return this.showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public List<By007Config> getConfigList() {
		return this.configList;
	}

	public void setConfigList(List<By007Config> configList) {
		this.configList = configList;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
		if (grade != null && grade.intValue() != 1) {
			this.setChecked(true);
		} else {
			this.setChecked(false);
		}

	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}