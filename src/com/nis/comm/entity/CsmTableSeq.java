package com.nis.comm.entity;

import java.io.Serializable;

public class CsmTableSeq implements Serializable {
	private static final long serialVersionUID = 1L;
	private String m_tableName;
	private Long m_nextSeq;
	private Long m_lastSeq;

	public Long getLastSeq() {
		return this.m_lastSeq;
	}

	public void setLastSeq(Long lastSeq) {
		this.m_lastSeq = lastSeq;
	}

	public String getTableName() {
		return this.m_tableName;
	}

	public void setTableName(String tableName) {
		this.m_tableName = tableName;
	}

	public Long getNextSeq() {
		return this.m_nextSeq;
	}

	public void setNextSeq(Long nextSeq) {
		this.m_nextSeq = nextSeq;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("=================== start  ====================\n");
		sb.append("tableName:" + this.m_tableName + " seq:" + this.m_nextSeq + "\n");
		sb.append("=================== end  ====================\n");
		return sb.toString();
	}
}