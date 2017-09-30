package com.nis.comm.entity;

import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("treeEntity")
public class TreeEntity implements Serializable {
	private static final long serialVersionUID = -3626879161514704333L;
	private String id;
	private String text;
	private String iconCls;
	private String state;
	private Integer cldCount;
	private List<TreeEntity> children;
	private String pid;
	private String _parentId;
	private String isfather;
	private boolean checked;
	private String havegrant;

	public TreeEntity() {
	}

	public TreeEntity(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public String get_parentId() {
		return this._parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getHavegrant() {
		return this.havegrant;
	}

	public void setHavegrant(String havegrant) {
		this.havegrant = havegrant;
	}

	public String getIsfather() {
		return this.isfather;
	}

	public void setIsfather(String isfather) {
		this.isfather = isfather;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getCldCount() {
		return this.cldCount;
	}

	public void setCldCount(Integer cldCount) {
		this.cldCount = cldCount;
	}

	public List<TreeEntity> getChildren() {
		return this.children;
	}

	public void setChildren(List<TreeEntity> children) {
		this.children = children;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}