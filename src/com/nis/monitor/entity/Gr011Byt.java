package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("gr011Byt")
public class Gr011Byt extends BaseEntity implements Serializable {
	private String id;
	private String regId;
	private Integer orderIndex;
	private String infectPartId;
	private String sampleId;
	private String pathogenId;
	private String pathogenName;
	private Date submiAt;
	private Date enterAt;
	private String relid;
	private String pathogenSn;
	private String testOrderNo;
	private String gr11Relid;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getInfectPartId() {
		return this.infectPartId;
	}

	public void setInfectPartId(String infectPartId) {
		this.infectPartId = infectPartId;
	}

	public String getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getPathogenId() {
		return this.pathogenId;
	}

	public void setPathogenId(String pathogenId) {
		this.pathogenId = pathogenId;
	}

	public String getPathogenName() {
		return this.pathogenName;
	}

	public void setPathogenName(String pathogenName) {
		this.pathogenName = pathogenName;
	}

	public Date getSubmiAt() {
		return this.submiAt;
	}

	public void setSubmiAt(Date submiAt) {
		this.submiAt = submiAt;
	}

	public Date getEnterAt() {
		return this.enterAt;
	}

	public void setEnterAt(Date enterAt) {
		this.enterAt = enterAt;
	}

	public String getRelid() {
		return this.relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getPathogenSn() {
		return this.pathogenSn;
	}

	public void setPathogenSn(String pathogenSn) {
		this.pathogenSn = pathogenSn;
	}

	public String getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public String getGr11Relid() {
		return this.gr11Relid;
	}

	public void setGr11Relid(String gr11Relid) {
		this.gr11Relid = gr11Relid;
	}
}