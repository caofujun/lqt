package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.mdr.entity.Xn009Lybzmx;
import com.nis.mdr.entity.Xn013Dbyw;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.type.Alias;

@Alias("xn008Jlybz")
public class Xn008Jlybz extends BaseEntity implements Serializable {
	private Integer itemId;
	private String germId;
	private String germName;
	private Integer orderno;
	private Integer mdr;
	private Integer xdr;
	private Integer pdr;
	private Integer classType;
	private String matchName;
	private List<Xn009Lybzmx> xn009List;
	private List<Xn013Dbyw> xn013List;
	private Map<String, Xn009Lybzmx> xn009Map;
	private Map<String, Xn013Dbyw> xn013Map;

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getGermId() {
		return this.germId;
	}

	public void setGermId(String germId) {
		this.germId = germId;
	}

	public String getGermName() {
		return this.germName;
	}

	public void setGermName(String germName) {
		this.germName = germName;
	}

	public Integer getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public Integer getMdr() {
		return this.mdr;
	}

	public void setMdr(Integer mdr) {
		this.mdr = mdr;
	}

	public Integer getXdr() {
		return this.xdr;
	}

	public void setXdr(Integer xdr) {
		this.xdr = xdr;
	}

	public Integer getPdr() {
		return this.pdr;
	}

	public void setPdr(Integer pdr) {
		this.pdr = pdr;
	}

	public Integer getClassType() {
		return this.classType;
	}

	public void setClassType(Integer classType) {
		this.classType = classType;
	}

	public String getMatchName() {
		return this.matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public List<Xn009Lybzmx> getXn009List() {
		return this.xn009List;
	}

	public void setXn009List(List<Xn009Lybzmx> xn009List) {
		this.xn009List = xn009List;
	}

	public List<Xn013Dbyw> getXn013List() {
		return this.xn013List;
	}

	public void setXn013List(List<Xn013Dbyw> xn013List) {
		this.xn013List = xn013List;
	}

	public Map<String, Xn009Lybzmx> getXn009Map() {
		return this.xn009Map;
	}

	public void setXn009Map(Map<String, Xn009Lybzmx> xn009Map) {
		this.xn009Map = xn009Map;
	}

	public Map<String, Xn013Dbyw> getXn013Map() {
		return this.xn013Map;
	}

	public void setXn013Map(Map<String, Xn013Dbyw> xn013Map) {
		this.xn013Map = xn013Map;
	}
}