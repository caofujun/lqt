package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("ctgSys005LisruleMaster")
public class CtgSys005LisruleMaster extends BaseEntity implements Serializable {
	private Long orderno;
	private String itemnamefield;
	private String itemnameoper;
	private String itemnamevalue;
	private Long yjWeight;
	private Long isallitems;
	private Long calculateorder;
	private String tablename;
	private String yjresult;

	public String getYjresult() {
		return this.yjresult;
	}

	public void setYjresult(String yjresult) {
		this.yjresult = yjresult;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public Long getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}

	public String getItemnamefield() {
		return this.itemnamefield;
	}

	public void setItemnamefield(String itemnamefield) {
		this.itemnamefield = itemnamefield;
	}

	public String getItemnameoper() {
		return this.itemnameoper;
	}

	public void setItemnameoper(String itemnameoper) {
		this.itemnameoper = itemnameoper;
	}

	public String getItemnamevalue() {
		return this.itemnamevalue;
	}

	public void setItemnamevalue(String itemnamevalue) {
		this.itemnamevalue = itemnamevalue;
	}

	public Long getYjWeight() {
		return this.yjWeight;
	}

	public void setYjWeight(Long yjWeight) {
		this.yjWeight = yjWeight;
	}

	public Long getIsallitems() {
		return this.isallitems;
	}

	public void setIsallitems(Long isallitems) {
		this.isallitems = isallitems;
	}

	public Long getCalculateorder() {
		return this.calculateorder;
	}

	public void setCalculateorder(Long calculateorder) {
		this.calculateorder = calculateorder;
	}
}