package com.nis.analysis.model;

import com.nis.analysis.entity.Gr019Ysgrmx;
import com.nis.analysis.entity.ZdmxWeight;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class c {
	private boolean success = false;
	private String ag = "";
	private List<Map<String, ZdmxWeight>> ah = new ArrayList();
	private List<Gr019Ysgrmx> ai = new ArrayList();

	public boolean b() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrMsg() {
		return this.ag;
	}

	public void setErrMsg(String errMsg) {
		this.ag = errMsg;
	}

	public List<Map<String, ZdmxWeight>> getZdList() {
		return this.ah;
	}

	public void setZdList(List<Map<String, ZdmxWeight>> zdList) {
		this.ah = zdList;
	}

	public List<Gr019Ysgrmx> getGr019List() {
		return this.ai;
	}

	public void setGr019List(List<Gr019Ysgrmx> gr019List) {
		this.ai = gr019List;
	}
}