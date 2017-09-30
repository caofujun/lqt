package com.nis.icu.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;

public class Gm002Djpdd extends BaseEntity implements Serializable {
	private Integer dtYear;
	private Integer dtMonth;
	private Integer weeknumber;
	private String zyid;
	private Integer gradea;
	private Integer gradeb;
	private Integer gradec;
	private Integer graded;
	private Integer gradee;
	private Double week1;
	private Double week2;
	private Double week3;
	private Double week4;
	private Double week5;
	private Double week6;
	private Double week7;
	private String deptId;
	private String patientId;
	private String patientName;
	private String age;
	private String sex;
	private String outAt;
	private String bedNo;
	private String grade;
	private List<Gm002Djpdd> djpddList;

	public Integer getDtYear() {
		return this.dtYear;
	}

	public void setDtYear(Integer dtYear) {
		this.dtYear = dtYear;
	}

	public Integer getDtMonth() {
		return this.dtMonth;
	}

	public void setDtMonth(Integer dtMonth) {
		this.dtMonth = dtMonth;
	}

	public Integer getWeeknumber() {
		return this.weeknumber;
	}

	public void setWeeknumber(Integer weeknumber) {
		this.weeknumber = weeknumber;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public Integer getGradea() {
		return this.gradea;
	}

	public void setGradea(Integer gradea) {
		this.gradea = gradea;
	}

	public Integer getGradeb() {
		return this.gradeb;
	}

	public void setGradeb(Integer gradeb) {
		this.gradeb = gradeb;
	}

	public Integer getGradec() {
		return this.gradec;
	}

	public void setGradec(Integer gradec) {
		this.gradec = gradec;
	}

	public Integer getGraded() {
		return this.graded;
	}

	public void setGraded(Integer graded) {
		this.graded = graded;
	}

	public Integer getGradee() {
		return this.gradee;
	}

	public void setGradee(Integer gradee) {
		this.gradee = gradee;
	}

	public Double getWeek1() {
		return this.week1;
	}

	public void setWeek1(Double week1) {
		this.week1 = week1;
	}

	public Double getWeek2() {
		return this.week2;
	}

	public void setWeek2(Double week2) {
		this.week2 = week2;
	}

	public Double getWeek3() {
		return this.week3;
	}

	public void setWeek3(Double week3) {
		this.week3 = week3;
	}

	public Double getWeek4() {
		return this.week4;
	}

	public void setWeek4(Double week4) {
		this.week4 = week4;
	}

	public Double getWeek5() {
		return this.week5;
	}

	public void setWeek5(Double week5) {
		this.week5 = week5;
	}

	public Double getWeek6() {
		return this.week6;
	}

	public void setWeek6(Double week6) {
		this.week6 = week6;
	}

	public Double getWeek7() {
		return this.week7;
	}

	public void setWeek7(Double week7) {
		this.week7 = week7;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOutAt() {
		return this.outAt;
	}

	public void setOutAt(String outAt) {
		this.outAt = outAt;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<Gm002Djpdd> getDjpddList() {
		return this.djpddList;
	}

	public void setDjpddList(List<Gm002Djpdd> djpddList) {
		this.djpddList = djpddList;
	}
}