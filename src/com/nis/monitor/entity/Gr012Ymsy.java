package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("gr012Ymsy")
public class Gr012Ymsy extends BaseEntity implements Serializable {
	private String id;
	private String regId;
	private Integer orderIndex;
	private String infectPartId;
	private String sampleId;
	private String pathogenId;
	private String antiDrugId;
	private String antiDrugName;
	private Double mic;
	private String result;
	private Date submiAt;
	private Date enterAt;
	private String relid;
	private String gr12Relid;
	private String gr12TestNo;

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

	public String getAntiDrugId() {
		return this.antiDrugId;
	}

	public void setAntiDrugId(String antiDrugId) {
		this.antiDrugId = antiDrugId;
	}

	public String getAntiDrugName() {
		return this.antiDrugName;
	}

	public void setAntiDrugName(String antiDrugName) {
		this.antiDrugName = antiDrugName;
	}

	public Double getMic() {
		return this.mic;
	}

	public void setMic(Double mic) {
		this.mic = mic;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getGr12Relid() {
		return this.gr12Relid;
	}

	public void setGr12Relid(String gr12Relid) {
		this.gr12Relid = gr12Relid;
	}

	public String getGr12TestNo() {
		return this.gr12TestNo;
	}

	public void setGr12TestNo(String gr12TestNo) {
		this.gr12TestNo = gr12TestNo;
	}
}