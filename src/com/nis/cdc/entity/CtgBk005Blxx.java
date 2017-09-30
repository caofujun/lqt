package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgBk005Blxx extends BaseEntity implements Serializable {
	private String subid;
	private String masterid;
	private Long orderno;
	private String foodname;
	private String foodclass;
	private String packingway;
	private String foodbrand;
	private String manufacturer;
	private String purchaseplace;
	private String eatingplaces;
	private String placetype;
	private Date eatingtime;
	private Long numberofeating;
	private String otherpeople;
	private String issampling;
	private String purcplacecode;
	private String eatplacecode;

	public String getPurcplacecode() {
		return this.purcplacecode;
	}

	public void setPurcplacecode(String purcplacecode) {
		this.purcplacecode = purcplacecode;
	}

	public String getEatplacecode() {
		return this.eatplacecode;
	}

	public void setEatplacecode(String eatplacecode) {
		this.eatplacecode = eatplacecode;
	}

	public String getSubid() {
		return this.subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public Long getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}

	public String getFoodname() {
		return this.foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getFoodclass() {
		return this.foodclass;
	}

	public void setFoodclass(String foodclass) {
		this.foodclass = foodclass;
	}

	public String getPackingway() {
		return this.packingway;
	}

	public void setPackingway(String packingway) {
		this.packingway = packingway;
	}

	public String getFoodbrand() {
		return this.foodbrand;
	}

	public void setFoodbrand(String foodbrand) {
		this.foodbrand = foodbrand;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getPurchaseplace() {
		return this.purchaseplace;
	}

	public void setPurchaseplace(String purchaseplace) {
		this.purchaseplace = purchaseplace;
	}

	public String getEatingplaces() {
		return this.eatingplaces;
	}

	public void setEatingplaces(String eatingplaces) {
		this.eatingplaces = eatingplaces;
	}

	public String getPlacetype() {
		return this.placetype;
	}

	public void setPlacetype(String placetype) {
		this.placetype = placetype;
	}

	public Date getEatingtime() {
		return this.eatingtime;
	}

	public void setEatingtime(Date eatingtime) {
		this.eatingtime = eatingtime;
	}

	public Long getNumberofeating() {
		return this.numberofeating;
	}

	public void setNumberofeating(Long numberofeating) {
		this.numberofeating = numberofeating;
	}

	public String getOtherpeople() {
		return this.otherpeople;
	}

	public void setOtherpeople(String otherpeople) {
		this.otherpeople = otherpeople;
	}

	public String getIssampling() {
		return this.issampling;
	}

	public void setIssampling(String issampling) {
		this.issampling = issampling;
	}
}