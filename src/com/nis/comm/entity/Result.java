package com.nis.comm.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Result<T> implements Serializable {
	@XStreamAlias("result")
	private String result;
	@XStreamAlias("msg")
	private String msg;
	@XStreamAlias("data")
	private T data;
	@XStreamAlias("expandValue")
	private String expandValue;
	@XStreamAlias("extraValue")
	private String extraValue;
	@XStreamAlias("url")
	private String url;

	public Result() {
		this.result = "success";
	}

	public Result(String result) {
		this.result = result;
	}

	public Result(String result, String msg) {
		this.result = result;
		this.msg = msg;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getExpandValue() {
		return this.expandValue;
	}

	public void setExpandValue(String expandValue) {
		this.expandValue = expandValue;
	}

	public String getExtraValue() {
		return this.extraValue;
	}

	public void setExtraValue(String extraValue) {
		this.extraValue = extraValue;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}