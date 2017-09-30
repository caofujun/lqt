package com.nis.analysis.model;

import com.nis.analysis.model.d;
import com.nis.analysis.model.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class f {
	private List<e> ap = new ArrayList();
	private boolean aq = false;
	private boolean success = false;
	private String ag = "";
	private String ar = "";
	private Map<String, d> as = new HashMap();

	public String getErrMsg() {
		return this.ag;
	}

	public void setErrMsg(String errMsg) {
		this.ag = errMsg;
	}

	public List<e> getLnt() {
		return this.ap;
	}

	public void setLnt(List<e> lnt) {
		this.ap = lnt;
	}

	public boolean getAnalysisflag() {
		return this.aq;
	}

	public void setAnalysisflag(boolean analysisflag) {
		this.aq = analysisflag;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, d> getKeyMap() {
		return this.as;
	}

	public void setKeyMap(Map<String, d> keyMap) {
		this.as = keyMap;
	}

	public String getAnalysisResultText() {
		return this.ar;
	}

	public void setAnalysisResultText(String analysisResultText) {
		this.ar = analysisResultText;
	}
}