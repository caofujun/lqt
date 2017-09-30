package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("qsReportOptions")
public class QsReportOptions extends BaseEntity implements Serializable {
	private String optId;
	private String optName;
	private Double opScale;
	private Long opCount;

	public String getOptId() {
		return this.optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return this.optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Double getOpScale() {
		return this.opScale;
	}

	public void setOpScale(Double opScale) {
		this.opScale = opScale;
	}

	public Long getOpCount() {
		return this.opCount;
	}

	public void setOpCount(Long opCount) {
		this.opCount = opCount;
	}
}