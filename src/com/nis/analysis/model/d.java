package com.nis.analysis.model;

public class d {
	private int id;
	private String aj;
	private String ak;
	private int al;
	private int startPos;
	private int am;
	private String an;
	private String elementType;
	private String bhElement;
	private String originalContent;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getElementid() {
		return this.aj;
	}

	public void setElementid(String elementid) {
		this.aj = elementid;
	}

	public String getElementname() {
		return this.ak;
	}

	public void setElementname(String elementname) {
		this.ak = elementname;
	}

	public int getElementLength() {
		return this.al;
	}

	public void setElementLength(int elementLength) {
		this.al = elementLength;
	}

	public int getStartPos() {
		return this.startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getStopPos() {
		return this.am;
	}

	public void setStopPos(int stopPos) {
		this.am = stopPos;
	}

	public String getElementType() {
		return this.elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getBhElement() {
		return this.bhElement;
	}

	public void setBhElement(String bhElement) {
		this.bhElement = bhElement;
	}

	public String getPreContent() {
		return this.an;
	}

	public void setPreContent(String preContent) {
		this.an = preContent;
	}

	public String getOriginalContent() {
		return this.originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}
}