package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.utils.EncryptUtils;
import java.io.Serializable;
import java.util.Date;

public class Zg006Zdmx extends BaseEntity implements Serializable, Cloneable {
	private static final long serialVersionUID = -8815163891296252098L;
	private String mxId;
	private String infectCode;
	private String standardNo;
	private String nodeLevel;
	private String nodeId;
	private String nodeBody;
	private String ifBaseElement;
	private String elementId;
	private String elementName;
	private String ifMust;
	private String listCount;
	private String needCount;
	private String weightValue;
	private String pNodeId;
	private String weightRatio;
	private String nodeType;
	private String nodeType2;
	private Date dataDate;
	private Date lastDate;

	public String getMxId() {
		return this.mxId;
	}

	public void setMxId(String mxId) {
		this.mxId = mxId;
	}

	public String getInfectCode() {
		return this.infectCode;
	}

	public void setInfectCode(String infectCode) {
		this.infectCode = infectCode;
	}

	public String getStandardNo() {
		return this.standardNo;
	}

	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}

	public String getNodeLevel() {
		return this.nodeLevel;
	}

	public void setNodeLevel(String nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeBody() {
		return this.nodeBody;
	}

	public void setNodeBody(String nodeBody) {
		this.nodeBody = nodeBody;
	}

	public String getIfBaseElement() {
		return this.ifBaseElement;
	}

	public void setIfBaseElement(String ifBaseElement) {
		this.ifBaseElement = ifBaseElement;
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

	public String getIfMust() {
		return this.ifMust;
	}

	public void setIfMust(String ifMust) {
		this.ifMust = ifMust;
	}

	public String getListCount() {
		return this.listCount;
	}

	public void setListCount(String listCount) {
		this.listCount = listCount;
	}

	public String getNeedCount() {
		return this.needCount;
	}

	public void setNeedCount(String needCount) {
		this.needCount = needCount;
	}

	public String getWeightValue() {
		return this.weightValue;
	}

	public void setWeightValue(String weightValue) {
		this.weightValue = weightValue;
	}

	public String getPNodeId() {
		return this.pNodeId;
	}

	public void setPNodeId(String pNodeId) {
		this.pNodeId = pNodeId;
	}

	public String getWeightRatio() {
		return this.weightRatio;
	}

	public void setWeightRatio(String weightRatio) {
		this.weightRatio = weightRatio;
	}

	public String getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Date getDataDate() {
		return this.dataDate;
	}

	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}

	public String getNodeType2() {
		return this.nodeType2;
	}

	public void setNodeType2(String nodeType2) {
		this.nodeType2 = nodeType2;
	}

	public Object clone() {
		Zg006Zdmx stu = null;

		try {
			stu = (Zg006Zdmx) super.clone();
		} catch (CloneNotSupportedException arg2) {
			arg2.printStackTrace();
		}

		return stu;
	}

	public void encode() {
		if (this.mxId != null && !"".equals(this.mxId)) {
			this.mxId = this.mxId.toUpperCase();
		}

		if (this.infectCode != null && !"".equals(this.infectCode)) {
			this.infectCode = EncryptUtils.ad(this.infectCode);
		}

		if (this.standardNo != null && !"".equals(this.standardNo)) {
			this.standardNo = EncryptUtils.ad(this.standardNo);
		}

		if (this.nodeLevel != null && !"".equals(this.nodeLevel)) {
			this.nodeLevel = EncryptUtils.ad(this.nodeLevel);
		}

		if (this.nodeId != null && !"".equals(this.nodeId)) {
			this.nodeId = EncryptUtils.ad(this.nodeId);
		}

		if (this.nodeBody != null && !"".equals(this.nodeBody)) {
			this.nodeBody = EncryptUtils.ad(this.nodeBody);
		}

		if (this.ifBaseElement != null && !"".equals(this.ifBaseElement)) {
			this.ifBaseElement = EncryptUtils.ad(this.ifBaseElement);
		}

		if (this.elementId != null && !"".equals(this.elementId)) {
			this.elementId = EncryptUtils.ad(this.elementId);
		}

		if (this.elementName != null && !"".equals(this.elementName)) {
			this.elementName = EncryptUtils.ad(this.elementName);
		}

		if (this.ifMust != null && !"".equals(this.ifMust)) {
			this.ifMust = EncryptUtils.ad(this.ifMust);
		}

		if (this.listCount != null && !"".equals(this.listCount)) {
			this.listCount = EncryptUtils.ad(this.listCount);
		}

		if (this.needCount != null && !"".equals(this.needCount)) {
			this.needCount = EncryptUtils.ad(this.needCount);
		}

		if (this.weightValue != null && !"".equals(this.weightValue)) {
			this.weightValue = EncryptUtils.ad(this.weightValue);
		}

		if (this.pNodeId != null && !"".equals(this.pNodeId)) {
			this.pNodeId = EncryptUtils.ad(this.pNodeId);
		}

		if (this.weightRatio != null && !"".equals(this.weightRatio)) {
			this.weightRatio = EncryptUtils.ad(this.weightRatio);
		}

		if (this.nodeType != null && !"".equals(this.nodeType)) {
			this.nodeType = EncryptUtils.ad(this.nodeType);
		}

		if (this.nodeType2 != null && !"".equals(this.nodeType2)) {
			this.nodeType2 = EncryptUtils.ad(this.nodeType2);
		}

	}

	public void decode() {
		if (this.infectCode != null && !"".equals(this.infectCode)) {
			this.infectCode = EncryptUtils.af(this.infectCode);
		}

		if (this.standardNo != null && !"".equals(this.standardNo)) {
			this.standardNo = EncryptUtils.af(this.standardNo);
		}

		if (this.nodeLevel != null && !"".equals(this.nodeLevel)) {
			this.nodeLevel = EncryptUtils.af(this.nodeLevel);
		}

		if (this.nodeId != null && !"".equals(this.nodeId)) {
			this.nodeId = EncryptUtils.af(this.nodeId);
		}

		if (this.nodeBody != null && !"".equals(this.nodeBody)) {
			this.nodeBody = EncryptUtils.af(this.nodeBody);
		}

		if (this.ifBaseElement != null && !"".equals(this.ifBaseElement)) {
			this.ifBaseElement = EncryptUtils.af(this.ifBaseElement);
		}

		if (this.elementId != null && !"".equals(this.elementId)) {
			this.elementId = EncryptUtils.af(this.elementId);
		}

		if (this.elementName != null && !"".equals(this.elementName)) {
			this.elementName = EncryptUtils.af(this.elementName);
		}

		if (this.ifMust != null && !"".equals(this.ifMust)) {
			this.ifMust = EncryptUtils.af(this.ifMust);
		}

		if (this.listCount != null && !"".equals(this.listCount)) {
			this.listCount = EncryptUtils.af(this.listCount);
		}

		if (this.needCount != null && !"".equals(this.needCount)) {
			this.needCount = EncryptUtils.af(this.needCount);
		}

		if (this.weightValue != null && !"".equals(this.weightValue)) {
			this.weightValue = EncryptUtils.af(this.weightValue);
		}

		if (this.pNodeId != null && !"".equals(this.pNodeId)) {
			this.pNodeId = EncryptUtils.af(this.pNodeId);
		}

		if (this.weightRatio != null && !"".equals(this.weightRatio)) {
			this.weightRatio = EncryptUtils.af(this.weightRatio);
		}

		if (this.nodeType != null && !"".equals(this.nodeType)) {
			this.nodeType = EncryptUtils.af(this.nodeType);
		}

		if (this.nodeType2 != null && !"".equals(this.nodeType2)) {
			this.nodeType2 = EncryptUtils.af(this.nodeType2);
		}

	}
}