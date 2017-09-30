package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.b;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("gr018Ysgrys")
public class Gr018Ysgrys extends BaseEntity implements Serializable {
	private String elementName;
	private Date monitorAt;
	private String dataForm;
	private String originalContent;
	private String dataType;
	private Integer state;
	private String id;
	private String sjId;
	private Long infectTypeid;
	private String zyid;
	private Date dataDate;
	private String elementId;
	private String sex;
	private Date birthDate;
	private Date inHospAt;
	private String tablename;

	public String getElementName() {
		return this.elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public Date getMonitorAt() {
		return this.monitorAt;
	}

	public void setMonitorAt(Date monitorAt) {
		this.monitorAt = monitorAt;
	}

	public String getDataForm() {
		return this.dataForm;
	}

	public void setDataForm(String dataForm) {
		this.dataForm = dataForm;
	}

	public String getOriginalContent() {
		return this.originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSjId() {
		return this.sjId;
	}

	public void setSjId(String sjId) {
		this.sjId = sjId;
	}

	public Long getInfectTypeid() {
		return this.infectTypeid;
	}

	public void setInfectTypeid(Long infectTypeid) {
		this.infectTypeid = infectTypeid;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	@JsonSerialize(using = b.class)
	public Date getDataDate() {
		return this.dataDate;
	}

	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}

	public String getElementId() {
		return this.elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getInHospAt() {
		return this.inHospAt;
	}

	public void setInHospAt(Date inHospAt) {
		this.inHospAt = inHospAt;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}