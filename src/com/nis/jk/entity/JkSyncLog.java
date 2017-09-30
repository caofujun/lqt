package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.ab;
import com.nis.comm.serializer.a;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("jkSyncLog")
public class JkSyncLog extends BaseEntity implements Serializable {
	private Date syncTime;
	private String bizType;
	private String syncStatus;
	private String table;
	private String tableName;
	private Integer total;
	private Integer completed;
	private String stop;
	private Integer sortNo;

	public JkSyncLog() {
	}

	public JkSyncLog(ab jkTables, Integer total) {
		this.table = jkTables.getCode();
		this.tableName = jkTables.getName();
		this.syncStatus = "0";
		this.stop = "0";
		this.total = total;
		this.sortNo = jkTables.getSortNo();
		this.completed = new Integer(0);
	}

	public String getStop() {
		return this.stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public Integer getSortNo() {
		return this.sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	@JsonSerialize(using = a.class)
	public Date getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	public String getBizType() {
		return this.bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getSyncStatus() {
		return this.syncStatus;
	}

	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}

	public String getTable() {
		return this.table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCompleted() {
		return this.completed;
	}

	public void setCompleted(Integer completed) {
		this.completed = completed;
	}
}