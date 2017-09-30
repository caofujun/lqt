package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("hw003Cycs")
public class Hw003Cycs extends BaseEntity implements Serializable {
	private String placeId;
	private String placeName;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Date lastAt;
	private Integer flag;

	public Hw003Cycs(String placeId, String placeName, String wbCode, Date lastAt, Integer flag) {
		this.placeId = placeId;
		this.placeName = placeName;
		this.wbCode = wbCode;
		this.lastAt = lastAt;
		this.flag = flag;
	}

	public Hw003Cycs() {
	}

	public String getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return this.placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}