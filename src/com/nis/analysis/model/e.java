package com.nis.analysis.model;

import com.nis.analysis.model.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class e {
	private String ao;
	private String content;
	private List<b> ap = new ArrayList();

	public String getAnalysisContent() {
		this.ao = "";
		Iterator arg1 = this.ap.iterator();

		while (arg1.hasNext()) {
			b t = (b) arg1.next();
			if ("".equals(this.ao)) {
				this.ao = t.getAnalysisResult();
			} else {
				this.ao = this.ao + "，" + t.getAnalysisResult();
			}
		}

		this.ao = this.ao.replaceAll("~\\^~", " ");
		return this.ao;
	}

	public void setAnalysisContent(String analysisContent) {
		this.ao = analysisContent;
	}

	public List<b> getLnt() {
		return this.ap;
	}

	public void setLeafNodeText(b leafnode) {
		this.ap.add(leafnode);
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}