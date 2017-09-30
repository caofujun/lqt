package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Set;
import org.apache.ibatis.type.Alias;

@Alias("xn009Lybzmx")
public class Xn009Lybzmx extends BaseEntity implements Serializable {
	private Integer itemId;
	private Integer orderno;
	private String drugTypeId;
	private String drugTypeName;
	private Integer classtype;
	private String memo;
	private String testresult;
	private Set<String> testresultSet;

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getDrugTypeId() {
		return this.drugTypeId;
	}

	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	public String getDrugTypeName() {
		return this.drugTypeName;
	}

	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}

	public Integer getClasstype() {
		return this.classtype;
	}

	public void setClasstype(Integer classtype) {
		this.classtype = classtype;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTestresult() {
		return this.testresult;
	}

	public void setTestresult(String testresult) {
		this.testresult = testresult;
	}

	public Set<String> getTestresultSet() {
		return this.testresultSet;
	}

	public void setTestresultSet(Set<String> testresultSet) {
		this.testresultSet = testresultSet;
	}
}