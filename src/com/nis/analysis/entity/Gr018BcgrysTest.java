package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Gr018BcgrysTest extends BaseEntity implements Serializable {
	private String zyid;
	private String bcId;
	private String elementId;
	private String elementName;
	private String dataForm;
	private Date enterDate;
	private Date moniDate;

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getBcId() {
		return this.bcId;
	}

	public void setBcId(String bcId) {
		this.bcId = bcId;
	}

	public String getElementId() {
		return this.elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getElementName() {
		return this.elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getDataForm() {
		return this.dataForm;
	}

	public void setDataForm(String dataForm) {
		this.dataForm = dataForm;
	}

	public Date getEnterDate() {
		return this.enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public Date getMoniDate() {
		return this.moniDate;
	}

	public void setMoniDate(Date moniDate) {
		this.moniDate = moniDate;
	}
}