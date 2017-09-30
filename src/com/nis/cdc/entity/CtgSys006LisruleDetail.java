package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class CtgSys006LisruleDetail extends BaseEntity implements Serializable {
	private Long orderno;
	private Long suborderno;
	private String resultnamefield;
	private String resultnameoper;
	private String resultnamevalue;
	private String matchvaluefield;
	private String matchvalueoper;
	private String matchvalue;
	private Long weightvalue;
	private Long isallresult;
	private String yjResult;

	public Long getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}

	public Long getSuborderno() {
		return this.suborderno;
	}

	public void setSuborderno(Long suborderno) {
		this.suborderno = suborderno;
	}

	public String getResultnamefield() {
		return this.resultnamefield;
	}

	public void setResultnamefield(String resultnamefield) {
		this.resultnamefield = resultnamefield;
	}

	public String getResultnameoper() {
		return this.resultnameoper;
	}

	public void setResultnameoper(String resultnameoper) {
		this.resultnameoper = resultnameoper;
	}

	public String getResultnamevalue() {
		return this.resultnamevalue;
	}

	public void setResultnamevalue(String resultnamevalue) {
		this.resultnamevalue = resultnamevalue;
	}

	public String getMatchvaluefield() {
		return this.matchvaluefield;
	}

	public void setMatchvaluefield(String matchvaluefield) {
		this.matchvaluefield = matchvaluefield;
	}

	public String getMatchvalueoper() {
		return this.matchvalueoper;
	}

	public void setMatchvalueoper(String matchvalueoper) {
		this.matchvalueoper = matchvalueoper;
	}

	public String getMatchvalue() {
		return this.matchvalue;
	}

	public void setMatchvalue(String matchvalue) {
		this.matchvalue = matchvalue;
	}

	public Long getWeightvalue() {
		return this.weightvalue;
	}

	public void setWeightvalue(Long weightvalue) {
		this.weightvalue = weightvalue;
	}

	public Long getIsallresult() {
		return this.isallresult;
	}

	public void setIsallresult(Long isallresult) {
		this.isallresult = isallresult;
	}

	public String getYjResult() {
		return this.yjResult;
	}

	public void setYjResult(String yjResult) {
		this.yjResult = yjResult;
	}
}