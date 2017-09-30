package com.nis.hand.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.a;
import com.nis.hand.entity.Sw003Ycxsj;
import com.nis.hand.entity.Sw004Ycxsj;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("sw002Ycxdc")
public class Sw002Ycxdc extends BaseEntity implements Serializable {
	private String dcId;
	private Date dcDate;
	private String dcWard;
	private Long gcTime;
	private String gcUsername;
	private Date djDate;
	private String ypss;
	private String gcUserId;
	private String dcWardId;
	private String sjs;
	private String personalType;
	private List<Sw003Ycxsj> watchList;
	private List<Sw004Ycxsj> watchList2;

	public List<Sw004Ycxsj> getWatchList2() {
		return this.watchList2;
	}

	public void setWatchList2(List<Sw004Ycxsj> watchList2) {
		this.watchList2 = watchList2;
	}

	public List<Sw003Ycxsj> getWatchList() {
		return this.watchList;
	}

	public void setWatchList(List<Sw003Ycxsj> watchList) {
		this.watchList = watchList;
	}

	public String getGcUserId() {
		return this.gcUserId;
	}

	public void setGcUserId(String gcUserId) {
		this.gcUserId = gcUserId;
	}

	public String getDcWardId() {
		return this.dcWardId;
	}

	public void setDcWardId(String dcWardId) {
		this.dcWardId = dcWardId;
	}

	public String getSjs() {
		return this.sjs;
	}

	public void setSjs(String sjs) {
		this.sjs = sjs;
	}

	public String getPersonalType() {
		return this.personalType;
	}

	public void setPersonalType(String personalType) {
		this.personalType = personalType;
	}

	public String getDcId() {
		return this.dcId;
	}

	public void setDcId(String dcId) {
		this.dcId = dcId;
	}

	public Date getDcDate() {
		return this.dcDate;
	}

	public void setDcDate(Date dcDate) {
		this.dcDate = dcDate;
	}

	public String getDcWard() {
		return this.dcWard;
	}

	public void setDcWard(String dcWard) {
		this.dcWard = dcWard;
	}

	public Long getGcTime() {
		return this.gcTime;
	}

	public void setGcTime(Long gcTime) {
		this.gcTime = gcTime;
	}

	public String getGcUsername() {
		return this.gcUsername;
	}

	public void setGcUsername(String gcUsername) {
		this.gcUsername = gcUsername;
	}

	@JsonSerialize(using = a.class)
	public Date getDjDate() {
		return this.djDate;
	}

	public void setDjDate(Date djDate) {
		this.djDate = djDate;
	}

	public String getYpss() {
		return this.ypss;
	}

	public void setYpss(String ypss) {
		this.ypss = ypss;
	}
}