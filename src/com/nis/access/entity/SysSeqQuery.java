package com.nis.access.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("sysSeqQuery")
public class SysSeqQuery extends BaseEntity implements Serializable {
	private String seqName;
	private String pageNum;
	private String pageStart;

	public String getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageStart() {
		return this.pageStart;
	}

	public void setPageStart(String pageStart) {
		this.pageStart = pageStart;
	}

	public String getSeqName() {
		return this.seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
}