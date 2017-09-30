package com.nis.task.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.a;
import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class TaskJobLog extends BaseEntity implements Serializable {
	private String id;
	private String jobid;
	private Date addtime;
	private Integer status;
	private String link;
	private String result;
	private String statusname;

	public TaskJobLog() {
	}

	public TaskJobLog(String jobid, Date addtime, Integer status, String link, String result) {
		this.jobid = jobid;
		this.addtime = addtime;
		this.status = status;
		this.link = link;
		this.result = result;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobid() {
		return this.jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	@JsonSerialize(using = a.class)
	public Date getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStatusname() {
		return this.statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
}