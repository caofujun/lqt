package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("ctgBk001Crbafpcard")
public class CtgBk001Crbafpcard extends BaseEntity implements Serializable {
	private String masterid;
	private String subid;
	private String afpAreatype;
	private Date palsydate;
	private String palsysymptom;
	private String doctorareatypeid;
	private String doctorareatypename;
	private Date doctordate;
	private String doctoraddrcode;
	private String doctoraddrvalue;
	private String doctoraddr;
	private String clinicaldiagnosis1;
	private String clinicaldiagnosis2;
	private Date sampledate;
	private String sampleplace;
	private String contactflag;

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getSubid() {
		return this.subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getAfpAreatype() {
		return this.afpAreatype;
	}

	public void setAfpAreatype(String afpAreatype) {
		this.afpAreatype = afpAreatype;
	}

	public Date getPalsydate() {
		return this.palsydate;
	}

	public void setPalsydate(Date palsydate) {
		this.palsydate = palsydate;
	}

	public String getPalsysymptom() {
		return this.palsysymptom;
	}

	public void setPalsysymptom(String palsysymptom) {
		this.palsysymptom = palsysymptom;
	}

	public String getDoctorareatypeid() {
		return this.doctorareatypeid;
	}

	public void setDoctorareatypeid(String doctorareatypeid) {
		this.doctorareatypeid = doctorareatypeid;
	}

	public String getDoctorareatypename() {
		return this.doctorareatypename;
	}

	public void setDoctorareatypename(String doctorareatypename) {
		this.doctorareatypename = doctorareatypename;
	}

	public Date getDoctordate() {
		return this.doctordate;
	}

	public void setDoctordate(Date doctordate) {
		this.doctordate = doctordate;
	}

	public String getDoctoraddrcode() {
		return this.doctoraddrcode;
	}

	public void setDoctoraddrcode(String doctoraddrcode) {
		this.doctoraddrcode = doctoraddrcode;
	}

	public String getDoctoraddrvalue() {
		return this.doctoraddrvalue;
	}

	public void setDoctoraddrvalue(String doctoraddrvalue) {
		this.doctoraddrvalue = doctoraddrvalue;
	}

	public String getDoctoraddr() {
		return this.doctoraddr;
	}

	public void setDoctoraddr(String doctoraddr) {
		this.doctoraddr = doctoraddr;
	}

	public String getClinicaldiagnosis1() {
		return this.clinicaldiagnosis1;
	}

	public void setClinicaldiagnosis1(String clinicaldiagnosis1) {
		this.clinicaldiagnosis1 = clinicaldiagnosis1;
	}

	public String getClinicaldiagnosis2() {
		return this.clinicaldiagnosis2;
	}

	public void setClinicaldiagnosis2(String clinicaldiagnosis2) {
		this.clinicaldiagnosis2 = clinicaldiagnosis2;
	}

	public Date getSampledate() {
		return this.sampledate;
	}

	public void setSampledate(Date sampledate) {
		this.sampledate = sampledate;
	}

	public String getSampleplace() {
		return this.sampleplace;
	}

	public void setSampleplace(String sampleplace) {
		this.sampleplace = sampleplace;
	}

	public String getContactflag() {
		return this.contactflag;
	}

	public void setContactflag(String contactflag) {
		this.contactflag = contactflag;
	}
}