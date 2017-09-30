package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.utils.EncryptUtils;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("zg007Dict")
public class Zg007Dict extends BaseEntity implements Serializable {
	private String bhElement;
	private String keyid;
	private String elementId;
	private String itemName;
	private String itemClass;
	private String elementType;
	private String qyElement;
	private String appElement;
	private String dictType;
	private String flag;
	private String isMain;

	public String getBhElement() {
		return this.bhElement;
	}

	public void setBhElement(String bhElement) {
		this.bhElement = bhElement;
	}

	public String getKeyid() {
		return this.keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getElementId() {
		return this.elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemClass() {
		return this.itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getElementType() {
		return this.elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getQyElement() {
		return this.qyElement;
	}

	public void setQyElement(String qyElement) {
		this.qyElement = qyElement;
	}

	public String getAppElement() {
		return this.appElement;
	}

	public void setAppElement(String appElement) {
		this.appElement = appElement;
	}

	public void encode() {
		if (this.keyid != null && !"".equals(this.keyid)) {
			this.keyid = this.keyid.toUpperCase();
		}

		if (this.bhElement != null && !"".equals(this.bhElement)) {
			this.bhElement = EncryptUtils.ad(this.bhElement);
		}

		if (this.elementId != null && !"".equals(this.elementId)) {
			this.elementId = EncryptUtils.ad(this.elementId);
		}

		if (this.itemName != null && !"".equals(this.itemName)) {
			this.itemName = EncryptUtils.ad(this.itemName);
		}

		if (this.itemClass != null && !"".equals(this.itemClass)) {
			this.itemClass = EncryptUtils.ad(this.itemClass);
		}

		if (this.elementType != null && !"".equals(this.elementType)) {
			this.elementType = EncryptUtils.ad(this.elementType);
		}

		if (this.qyElement != null && !"".equals(this.qyElement)) {
			this.qyElement = EncryptUtils.ad(this.qyElement);
		}

		if (this.appElement != null && !"".equals(this.appElement)) {
			this.appElement = EncryptUtils.ad(this.appElement);
		}

		if (this.dictType != null && !"".equals(this.dictType)) {
			this.dictType = EncryptUtils.ad(this.dictType);
		}

		if (this.flag != null && !"".equals(this.flag)) {
			this.flag = EncryptUtils.ad(this.flag);
		}

		if (this.isMain != null && !"".equals(this.isMain)) {
			this.isMain = EncryptUtils.ad(this.isMain);
		}

	}

	public void decode() {
		if (this.bhElement != null && !"".equals(this.bhElement)) {
			this.bhElement = EncryptUtils.af(this.bhElement);
		}

		if (this.elementId != null && !"".equals(this.elementId)) {
			this.elementId = EncryptUtils.af(this.elementId);
		}

		if (this.itemName != null && !"".equals(this.itemName)) {
			this.itemName = EncryptUtils.af(this.itemName);
		}

		if (this.itemClass != null && !"".equals(this.itemClass)) {
			this.itemClass = EncryptUtils.af(this.itemClass);
		}

		if (this.elementType != null && !"".equals(this.elementType)) {
			this.elementType = EncryptUtils.af(this.elementType);
		}

		if (this.qyElement != null && !"".equals(this.qyElement)) {
			this.qyElement = EncryptUtils.af(this.qyElement);
		}

		if (this.appElement != null && !"".equals(this.appElement)) {
			this.appElement = EncryptUtils.af(this.appElement);
		}

		if (this.dictType != null && !"".equals(this.dictType)) {
			this.dictType = EncryptUtils.af(this.dictType);
		}

		if (this.flag != null && !"".equals(this.flag)) {
			this.flag = EncryptUtils.af(this.flag);
		}

		if (this.isMain != null && !"".equals(this.isMain)) {
			this.isMain = EncryptUtils.af(this.isMain);
		}

	}

	public String getDictType() {
		return this.dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIsMain() {
		return this.isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
}