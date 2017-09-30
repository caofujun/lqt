package com.nis.analysis.model.kfz;

import com.nis.analysis.model.kfz.d;
import java.util.List;

public class a {
	private String at;
	private String av;
	private String aw;
	private String ax;
	private String ay;
	private List<d> az;

	public String getDoc_Type() {
		return this.at;
	}

	public void setDoc_Type(String doc_Type) {
		this.at = doc_Type;
	}

	public String getUpdateTime() {
		return this.av;
	}

	public void setUpdateTime(String updateTime) {
		this.av = updateTime;
	}

	public String getVersion() {
		return this.aw;
	}

	public void setVersion(String version) {
		this.aw = version;
	}

	public String getDoc_Title() {
		return this.ax;
	}

	public void setDoc_Title(String doc_Title) {
		this.ax = doc_Title;
	}

	public String getDoc_Str() {
		return this.ay;
	}

	public void setDoc_Str(String doc_Str) {
		this.ay = doc_Str;
	}

	public List<d> getDoc_Tree() {
		return this.az;
	}

	public void setDoc_Tree(List<d> doc_Tree) {
		this.az = doc_Tree;
	}
}