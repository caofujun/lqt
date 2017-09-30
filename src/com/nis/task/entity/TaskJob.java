package com.nis.task.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class TaskJob extends BaseEntity implements Serializable {
	private String id;
	private String projectid;
	private String name;
	private String remark;
	private String link;
	private String cron;
	private Integer isfailmail;
	private Date addtime;
	private String adduser;
	private Integer status;
	private String statusmsg;
	private String statusname;
	private String isfailmailname;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectid() {
		return this.projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCron() {
		return this.cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public Date getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAdduser() {
		return this.adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusmsg() {
		return this.statusmsg;
	}

	public void setStatusmsg(String statusmsg) {
		this.statusmsg = statusmsg;
	}

	public String getStatusname() {
		return this.statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public Integer getIsfailmail() {
		return this.isfailmail;
	}

	public void setIsfailmail(Integer isfailmail) {
		this.isfailmail = isfailmail;
	}

	public String getIsfailmailname() {
		return this.isfailmailname;
	}

	public void setIsfailmailname(String isfailmailname) {
		this.isfailmailname = isfailmailname;
	}
}