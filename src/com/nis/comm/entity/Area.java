package com.nis.comm.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("area")
public class Area extends BaseEntity implements Serializable {
	private Long areaId;
	private Long parentId;
	private String areaName;
	private Long tLevel;
	private String idPath;
	private Long position;
	private Long isUsed;
	private Long cityId;
	private Long logStat;
	private String areaLevel;
	private String posName;
	private String hot;
	private String map;
	private String areaCode;
	private Long isShow;

	public Long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getTLevel() {
		return this.tLevel;
	}

	public void setTLevel(Long tLevel) {
		this.tLevel = tLevel;
	}

	public String getIdPath() {
		return this.idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public Long getPosition() {
		return this.position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public Long getIsUsed() {
		return this.isUsed;
	}

	public void setIsUsed(Long isUsed) {
		this.isUsed = isUsed;
	}

	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getLogStat() {
		return this.logStat;
	}

	public void setLogStat(Long logStat) {
		this.logStat = logStat;
	}

	public String getAreaLevel() {
		return this.areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getPosName() {
		return this.posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getHot() {
		return this.hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public String getMap() {
		return this.map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Long getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Long isShow) {
		this.isShow = isShow;
	}
}