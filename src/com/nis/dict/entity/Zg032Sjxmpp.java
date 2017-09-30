package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.dict.entity.Zg033Jcxxpp;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("zg032Sjxmpp")
public class Zg032Sjxmpp extends BaseEntity implements Serializable {
	private String id;
	private String matchField;
	private String match;
	private String matchValue;
	private String infectFactor;
	private Integer groupCode;
	private Integer sortNo;
	private String elementName;
	private List<Zg033Jcxxpp> jcxxppList;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatchField() {
		return this.matchField;
	}

	public void setMatchField(String matchField) {
		this.matchField = matchField;
	}

	public String getMatch() {
		return this.match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public String getMatchValue() {
		return this.matchValue;
	}

	public void setMatchValue(String matchValue) {
		this.matchValue = matchValue;
	}

	public String getInfectFactor() {
		return this.infectFactor;
	}

	public void setInfectFactor(String infectFactor) {
		this.infectFactor = infectFactor;
	}

	public String getElementName() {
		return this.elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public List<Zg033Jcxxpp> getJcxxppList() {
		return this.jcxxppList;
	}

	public void setJcxxppList(List<Zg033Jcxxpp> jcxxppList) {
		this.jcxxppList = jcxxppList;
	}

	public Integer getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(Integer groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getSortNo() {
		return this.sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
}