package com.nis.comm.entity;

import com.nis.comm.entity.SmsReplayObj;
import java.util.Arrays;
import java.util.List;

public class SmsPullReplyRespJson {
	private String callfunc;
	private String[] args;
	private List<SmsReplayObj> content;
	private String interval;
	private String token;

	public String getCallfunc() {
		return this.callfunc;
	}

	public void setCallfunc(String callfunc) {
		this.callfunc = callfunc;
	}

	public List<SmsReplayObj> getContent() {
		return this.content;
	}

	public void setContent(List<SmsReplayObj> content) {
		this.content = content;
	}

	public String getInterval() {
		return this.interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String[] getArgs() {
		return this.args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public String toString() {
		return "SmsPullReplyRespJson [callfunc=" + this.callfunc + ", args=" + Arrays.toString(this.args) + ", content="
				+ this.content + ", interval=" + this.interval + ", token=" + this.token + "]";
	}
}