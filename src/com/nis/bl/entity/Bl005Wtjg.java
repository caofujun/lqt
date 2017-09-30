package com.nis.bl.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Bl005Wtjg extends BaseEntity implements Serializable {
	private Long updFlag;
	private String blId;
	private String sjId;
	private Long stId;
	private String shText;
	private String shType;
	private String shMemo;
	private Long flag;

	public Long getUpdFlag() {
		return this.updFlag;
	}

	public void setUpdFlag(Long updFlag) {
		this.updFlag = updFlag;
	}

	public String getBlId() {
		return this.blId;
	}

	public void setBlId(String blId) {
		this.blId = blId;
	}

	public String getSjId() {
		return this.sjId;
	}

	public void setSjId(String sjId) {
		this.sjId = sjId;
	}

	public Long getStId() {
		return this.stId;
	}

	public void setStId(Long stId) {
		this.stId = stId;
	}

	public String getShText() {
		return this.shText;
	}

	public void setShText(String shText) {
		this.shText = shText;
	}

	public String getShType() {
		return this.shType;
	}

	public void setShType(String shType) {
		this.shType = shType;
	}

	public String getShMemo() {
		return this.shMemo;
	}

	public void setShMemo(String shMemo) {
		this.shMemo = shMemo;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
}