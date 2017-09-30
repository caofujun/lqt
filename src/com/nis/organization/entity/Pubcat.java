package com.nis.organization.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("pubcat")
public class Pubcat extends BaseEntity implements Serializable {
	private Long catid;
	private Long parentid;
	private Long typeid;
	private String catname;
	private Long position;
	private Long yuyueMax;
	private Long yuyueNum;
	private Long leftNum;
	private Long catdesc;
	private Long cityId;
	private String catNo;
	private String content;
	private String img;

	public Long getCatid() {
		return this.catid;
	}

	public void setCatid(Long catid) {
		this.catid = catid;
	}

	public Long getParentid() {
		return this.parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public Long getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public String getCatname() {
		return this.catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public Long getPosition() {
		return this.position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public Long getYuyueMax() {
		return this.yuyueMax;
	}

	public void setYuyueMax(Long yuyueMax) {
		this.yuyueMax = yuyueMax;
	}

	public Long getYuyueNum() {
		return this.yuyueNum;
	}

	public void setYuyueNum(Long yuyueNum) {
		this.yuyueNum = yuyueNum;
	}

	public Long getLeftNum() {
		return this.leftNum;
	}

	public void setLeftNum(Long leftNum) {
		this.leftNum = leftNum;
	}

	public Long getCatdesc() {
		return this.catdesc;
	}

	public void setCatdesc(Long catdesc) {
		this.catdesc = catdesc;
	}

	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCatNo() {
		return this.catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}