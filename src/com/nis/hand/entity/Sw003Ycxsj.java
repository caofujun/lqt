package com.nis.hand.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("sw003Ycxsj")
public class Sw003Ycxsj extends BaseEntity implements Serializable {
	private String sjId;
	private String ryType;
	private String zzBrq;
	private String zzCzq;
	private String dcId;
	private String isRight;
	private String zzTyh;
	private String zzBrh;
	private String zzHjh;
	private String xwSx;
	private String xwFzs;
	private String xwDst;
	private String xwW;

	public String getSjId() {
		return this.sjId;
	}

	public void setSjId(String sjId) {
		this.sjId = sjId;
	}

	public String getRyType() {
		return this.ryType;
	}

	public void setRyType(String ryType) {
		this.ryType = ryType;
	}

	public String getZzBrq() {
		return this.zzBrq;
	}

	public void setZzBrq(String zzBrq) {
		this.zzBrq = zzBrq;
	}

	public String getZzCzq() {
		return this.zzCzq;
	}

	public void setZzCzq(String zzCzq) {
		this.zzCzq = zzCzq;
	}

	public String getDcId() {
		return this.dcId;
	}

	public void setDcId(String dcId) {
		this.dcId = dcId;
	}

	public String getIsRight() {
		return this.isRight;
	}

	public void setIsRight(String isRight) {
		this.isRight = isRight;
	}

	public String getZzTyh() {
		return this.zzTyh;
	}

	public void setZzTyh(String zzTyh) {
		this.zzTyh = zzTyh;
	}

	public String getZzBrh() {
		return this.zzBrh;
	}

	public void setZzBrh(String zzBrh) {
		this.zzBrh = zzBrh;
	}

	public String getZzHjh() {
		return this.zzHjh;
	}

	public void setZzHjh(String zzHjh) {
		this.zzHjh = zzHjh;
	}

	public String getXwSx() {
		return this.xwSx;
	}

	public void setXwSx(String xwSx) {
		this.xwSx = xwSx;
	}

	public String getXwFzs() {
		return this.xwFzs;
	}

	public void setXwFzs(String xwFzs) {
		this.xwFzs = xwFzs;
	}

	public String getXwDst() {
		return this.xwDst;
	}

	public void setXwDst(String xwDst) {
		this.xwDst = xwDst;
	}

	public String getXwW() {
		return this.xwW;
	}

	public void setXwW(String xwW) {
		this.xwW = xwW;
	}
}