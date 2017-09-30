package com.nis.task.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class TaskProject extends BaseEntity implements Serializable {
	private String id;
	private String name;
	private String remark;
	private Integer isrecemail;
	private String recemail;
	private Date addtime;
	private String adduser;
	private String sign;
	private String signstring;
	private String isrecemailname;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSignstring() {
		return this.signstring;
	}

	public void setSignstring(String signstring) {
		this.signstring = signstring;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Integer getIsrecemail() {
		return this.isrecemail;
	}

	public void setIsrecemail(Integer isrecemail) {
		this.isrecemail = isrecemail;
	}

	public String getRecemail() {
		return this.recemail;
	}

	public void setRecemail(String recemail) {
		this.recemail = recemail;
	}

	public String getIsrecemailname() {
		return this.isrecemailname;
	}

	public void setIsrecemailname(String isrecemailname) {
		this.isrecemailname = isrecemailname;
	}
}