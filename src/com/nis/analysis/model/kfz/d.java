package com.nis.analysis.model.kfz;

import com.nis.analysis.model.kfz.e;
import com.nis.analysis.model.kfz.f;
import java.util.List;
import java.util.Map;

public class d {
	private String aE;
	private String aF;
	private List<e> aG;
	private Map<String, List<f>> aH;

	public String getSection_Content() {
		return this.aE;
	}

	public void setSection_Content(String section_Content) {
		this.aE = section_Content;
	}

	public String getSection_Type() {
		return this.aF;
	}

	public void setSection_Type(String section_Type) {
		this.aF = section_Type;
	}

	public List<e> getSub_Sections() {
		return this.aG;
	}

	public void setSub_Sections(List<e> sub_Sections) {
		this.aG = sub_Sections;
	}

	public Map<String, List<f>> getSection_Result() {
		return this.aH;
	}

	public void setSection_Result(Map<String, List<f>> section_Result) {
		this.aH = section_Result;
	}
}