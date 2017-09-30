package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("trocheKind")
public class TrocheKind extends BaseEntity implements Serializable {
	private Long year;
	private String thocheKindId;
	private String thocheKindName;
	private String memoryId;
	private Long flag;
	private Date lastAt;

	public Long getYear() {
		return this.year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getThocheKindId() {
		return this.thocheKindId;
	}

	public void setThocheKindId(String thocheKindId) {
		this.thocheKindId = thocheKindId;
	}

	public String getThocheKindName() {
		return this.thocheKindName;
	}

	public void setThocheKindName(String thocheKindName) {
		this.thocheKindName = thocheKindName;
	}

	public String getMemoryId() {
		return this.memoryId;
	}

	public void setMemoryId(String memoryId) {
		this.memoryId = memoryId;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String toString() {
		return "TrocheKind [year=" + this.year + ", thocheKindId=" + this.thocheKindId + ", thocheKindName="
				+ this.thocheKindName + ", memoryId=" + this.memoryId + ", flag=" + this.flag + ", lastAt="
				+ this.lastAt + "]";
	}
}