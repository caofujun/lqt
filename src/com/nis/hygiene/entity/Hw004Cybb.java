package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("hw004Cybb")
public class Hw004Cybb extends BaseEntity implements Serializable {
	private String sampleId;
	private String sampleName;
	private String classId;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private String memo;
	private Date lastAt;
	private Integer flag;
	private String className;
	private String id;
	private String text;
	private String state;
	private List<Hw004Cybb> children;

	public Hw004Cybb() {
	}

	public Hw004Cybb(String sampleId, String sampleName, String classId, String spCode, Date lastAt, Integer flag) {
		this.sampleId = sampleId;
		this.sampleName = sampleName;
		this.classId = classId;
		this.spCode = spCode;
		this.lastAt = lastAt;
		this.flag = flag;
	}

	public String getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getSampleName() {
		return this.sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Hw004Cybb> getChildren() {
		return this.children;
	}

	public void setChildren(List<Hw004Cybb> children) {
		this.children = children;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
}