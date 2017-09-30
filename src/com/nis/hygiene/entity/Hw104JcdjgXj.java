package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("hw104JcdjgXj")
public class Hw104JcdjgXj extends BaseEntity implements Serializable {
	private String id;
	private String reportId;
	private String pathoId;
	private Long pathoNum;
	private String memo;
	private String hw102Id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getPathoId() {
		return this.pathoId;
	}

	public void setPathoId(String pathoId) {
		this.pathoId = pathoId;
	}

	public Long getPathoNum() {
		return this.pathoNum;
	}

	public void setPathoNum(Long pathoNum) {
		this.pathoNum = pathoNum;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getHw102Id() {
		return this.hw102Id;
	}

	public void setHw102Id(String hw102Id) {
		this.hw102Id = hw102Id;
	}
}