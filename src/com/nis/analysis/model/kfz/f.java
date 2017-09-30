package com.nis.analysis.model.kfz;

import com.nis.analysis.model.kfz.b;
import java.util.List;

public class f {
	private String NAME;
	private String aI;
	private String aJ;
	private List<b> aK;

	public String getNAME() {
		return this.NAME;
	}

	public void setNAME(String nAME) {
		this.NAME = nAME;
	}

	public void setPROPERTY(List<b> pROPERTY) {
		this.aK = pROPERTY;
	}

	public String getCONTEXT() {
		return this.aI;
	}

	public void setCONTEXT(String cONTEXT) {
		this.aI = cONTEXT;
	}

	public List<b> getPROPERTY() {
		return this.aK;
	}

	public String getFORMAL() {
		return this.aJ;
	}

	public void setFORMAL(String fORMAL) {
		this.aJ = fORMAL;
	}
}