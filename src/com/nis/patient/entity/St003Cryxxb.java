package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.am;
import com.nis.comm.serializer.c;
import com.nis.comm.serializer.d;
import com.nis.patient.entity.St004Yzxxb;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("st003Cryxxb")
public class St003Cryxxb extends BaseEntity implements Serializable {
	private String zyid;
	private String id;
	private String patientId;
	private Integer visitId;
	private String patientName;
	private String age;
	private String ageUnit;
	private String sex;
	private String deptCode;
	private String deptName;
	private String inDeptId;
	private String inDeptName;
	private String outDeptId;
	private String outDeptName;
	private Date inHospAt;
	private Date outAt;
	private Integer inDays;
	private String bedNo;
	private String chargeDrId;
	private String chargeDrName;
	private Double cost;
	private String memo;
	private Date updDate;
	private Double bedNoIndex;
	private Integer fxStatus;
	private Date fxDate;
	private String gyStatus;
	private String pdcaStatus;
	private String hospId;
	private String fhyj;
	private String knyj;
	private String bkCount;
	private Date birthDate;
	private String weight;
	private String diagnosisName;
	private String startDate;
	private String endDate;
	private String dgsType;
	private String ryzdCode;
	private String ryzd;
	private String hospName;
	private Integer isInHosp;
	private String neonatebw;
	private String neonatebwName;
	private String regId;
	private String iHD;
	private String oHD;
	private String oD;
	private String unReadMsg;
	private String isMarked;
	private String tel;
	private String nation;
	private String idCard;
	private String marriage;
	private String lxrName;
	private String lxrPhone;
	private String workAddr;
	private String chargeNrId;
	private String chargeNrName;
	private Date submiAt;
	private String submiDeptName;
	private String itemTypeName;
	private Date testDate;
	private String pathoName;
	private Integer crbCount;
	private Integer syCount;
	private Integer jcCount;
	private Integer tmCount;
	private Integer ycCount;
	private Integer xnCount;
	private Integer zsCount;
	private Integer nyCount;
	private Integer cyjCount;
	private String mzzyid;
	private String patientType;
	private String indiagnosis;
	private String outdiagnosis;
	private Date diagnosisDt;
	private String yjdisease;
	private String yjdiseaseName;
	private String yjsource;
	private String yjcontent;
	private String scopetime;
	private String repeatcycle;
	private String diseaseid;
	private Date filldate;
	private String bkjg;
	private String iscb;
	private String islb;
	private String flag;
	private int cardCount;
	private String gm4DeptName;
	private Double tw;
	private Date recording_at;
	private String lisBytname;
	private String propName;
	private String specDescribes;
	private Date moniDate;
	private String infection;
	private String address;
	private String regAddress;
	private String ageFw;
	private String mdroCount;
	private String mdroYCount;
	private String zdCount;
	private String zdYCount;
	private List<St004Yzxxb> st004List;
	private List<String> deptIdIn;
	private String showCrb;
	private Integer infectTypeId;
	private String orderName;
	private String isdb;
	private String qzValue;

	public Integer getNyCount() {
		return this.nyCount;
	}

	public void setNyCount(Integer nyCount) {
		this.nyCount = nyCount;
	}

	public String getIsdb() {
		return this.isdb;
	}

	public void setIsdb(String isdb) {
		this.isdb = isdb;
	}

	public Integer getInfectTypeId() {
		return this.infectTypeId;
	}

	public void setInfectTypeId(Integer infectTypeId) {
		this.infectTypeId = infectTypeId;
	}

	public List<String> getDeptIdIn() {
		return this.deptIdIn;
	}

	public void setDeptIdIn(List<String> deptIdIn) {
		this.deptIdIn = deptIdIn;
	}

	public String getShowCrb() {
		return this.showCrb;
	}

	public void setShowCrb(String showCrb) {
		this.showCrb = showCrb;
	}

	public Integer getCyjCount() {
		return this.cyjCount;
	}

	public void setCyjCount(Integer cyjCount) {
		this.cyjCount = cyjCount;
	}

	public Integer getXnCount() {
		return this.xnCount;
	}

	public void setXnCount(Integer xnCount) {
		this.xnCount = xnCount;
	}

	public Integer getZsCount() {
		return this.zsCount;
	}

	public void setZsCount(Integer zsCount) {
		this.zsCount = zsCount;
	}

	public Date getRecording_at() {
		return this.recording_at;
	}

	public void setRecording_at(Date recording_at) {
		this.recording_at = recording_at;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegAddress() {
		return this.regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public Integer getYcCount() {
		return this.ycCount;
	}

	public void setYcCount(Integer ycCount) {
		this.ycCount = ycCount;
	}

	public Integer getTmCount() {
		return this.tmCount;
	}

	public void setTmCount(Integer tmCount) {
		this.tmCount = tmCount;
	}

	public String getIsMarked() {
		return this.isMarked;
	}

	public void setIsMarked(String isMarked) {
		this.isMarked = isMarked;
	}

	public String getYjsource() {
		return this.yjsource;
	}

	public void setYjsource(String yjsource) {
		this.yjsource = yjsource;
	}

	public String getYjcontent() {
		return this.yjcontent;
	}

	public void setYjcontent(String yjcontent) {
		this.yjcontent = yjcontent;
	}

	public String getMzzyid() {
		return this.mzzyid;
	}

	public void setMzzyid(String mzzyid) {
		this.mzzyid = mzzyid;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getIndiagnosis() {
		return this.indiagnosis;
	}

	public void setIndiagnosis(String indiagnosis) {
		this.indiagnosis = indiagnosis;
	}

	public String getOutdiagnosis() {
		return this.outdiagnosis;
	}

	public void setOutdiagnosis(String outdiagnosis) {
		this.outdiagnosis = outdiagnosis;
	}

	public Date getDiagnosisDt() {
		return this.diagnosisDt;
	}

	public void setDiagnosisDt(Date diagnosisDt) {
		this.diagnosisDt = diagnosisDt;
	}

	public String getYjdisease() {
		return this.yjdisease;
	}

	public void setYjdisease(String yjdisease) {
		this.yjdisease = yjdisease;
	}

	public String getYjdiseaseName() {
		return this.yjdiseaseName;
	}

	public void setYjdiseaseName(String yjdiseaseName) {
		this.yjdiseaseName = yjdiseaseName;
	}

	public String getScopetime() {
		return this.scopetime;
	}

	public void setScopetime(String scopetime) {
		this.scopetime = scopetime;
	}

	public String getRepeatcycle() {
		return this.repeatcycle;
	}

	public void setRepeatcycle(String repeatcycle) {
		this.repeatcycle = repeatcycle;
	}

	public String getDiseaseid() {
		return this.diseaseid;
	}

	public void setDiseaseid(String diseaseid) {
		this.diseaseid = diseaseid;
	}

	public Date getFilldate() {
		return this.filldate;
	}

	public void setFilldate(Date filldate) {
		this.filldate = filldate;
	}

	public String getBkjg() {
		return this.bkjg;
	}

	public void setBkjg(String bkjg) {
		this.bkjg = bkjg;
	}

	public String getIscb() {
		return this.iscb;
	}

	public void setIscb(String iscb) {
		this.iscb = iscb;
	}

	public String getIslb() {
		return this.islb;
	}

	public void setIslb(String islb) {
		this.islb = islb;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getCardCount() {
		return this.cardCount;
	}

	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUnReadMsg() {
		return this.unReadMsg;
	}

	public void setUnReadMsg(String unReadMsg) {
		this.unReadMsg = unReadMsg;
	}

	public String getiHD() {
		return this.iHD;
	}

	public void setiHD(String iHD) {
		this.iHD = iHD;
	}

	public String getoHD() {
		return this.oHD;
	}

	public void setoHD(String oHD) {
		this.oHD = oHD;
	}

	public String getoD() {
		return this.oD;
	}

	public void setoD(String oD) {
		this.oD = oD;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Integer getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
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

	public String getAgeUnit() {
		return this.ageUnit;
	}

	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getInDeptId() {
		return this.inDeptId;
	}

	public void setInDeptId(String inDeptId) {
		this.inDeptId = inDeptId;
	}

	public String getInDeptName() {
		return this.inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public String getOutDeptId() {
		return this.outDeptId;
	}

	public void setOutDeptId(String outDeptId) {
		this.outDeptId = outDeptId;
	}

	public String getOutDeptName() {
		return this.outDeptName;
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}

	@JsonSerialize(using = d.class)
	public Date getInHospAt() {
		return this.inHospAt;
	}

	public void setInHospAt(Date inHospAt) {
		this.inHospAt = inHospAt;
	}

	@JsonSerialize(using = d.class)
	public Date getOutAt() {
		return this.outAt;
	}

	public void setOutAt(Date outAt) {
		this.outAt = outAt;
	}

	public Integer getInDays() {
		return this.inDays;
	}

	public void setInDays(Integer inDays) {
		this.inDays = inDays;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getChargeDrId() {
		return this.chargeDrId;
	}

	public void setChargeDrId(String chargeDrId) {
		this.chargeDrId = chargeDrId;
	}

	public String getChargeDrName() {
		return this.chargeDrName;
	}

	public void setChargeDrName(String chargeDrName) {
		this.chargeDrName = chargeDrName;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public Double getBedNoIndex() {
		return this.bedNoIndex;
	}

	public void setBedNoIndex(Double bedNoIndex) {
		this.bedNoIndex = bedNoIndex;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDiagnosisName() {
		return this.diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public Integer getFxStatus() {
		return this.fxStatus;
	}

	public void setFxStatus(Integer fxStatus) {
		this.fxStatus = fxStatus;
	}

	public Date getFxDate() {
		return this.fxDate;
	}

	public void setFxDate(Date fxDate) {
		this.fxDate = fxDate;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDgsType() {
		return this.dgsType;
	}

	public void setDgsType(String dgsType) {
		this.dgsType = dgsType;
	}

	public String getGyStatus() {
		return this.gyStatus;
	}

	public void setGyStatus(String gyStatus) {
		this.gyStatus = gyStatus;
	}

	public String getHospId() {
		return this.hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public String getPdcaStatus() {
		return this.pdcaStatus;
	}

	public void setPdcaStatus(String pdcaStatus) {
		this.pdcaStatus = pdcaStatus;
	}

	public String getRyzdCode() {
		return this.ryzdCode;
	}

	public void setRyzdCode(String ryzdCode) {
		this.ryzdCode = ryzdCode;
	}

	public String getRyzd() {
		return this.ryzd;
	}

	public void setRyzd(String ryzd) {
		this.ryzd = ryzd;
	}

	public String getHospName() {
		return this.hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public Integer getIsInHosp() {
		return this.isInHosp;
	}

	public void setIsInHosp(Integer isInHosp) {
		this.isInHosp = isInHosp;
	}

	public String getFhyj() {
		return this.fhyj;
	}

	public void setFhyj(String fhyj) {
		this.fhyj = fhyj;
	}

	public String getKnyj() {
		return this.knyj;
	}

	public void setKnyj(String knyj) {
		this.knyj = knyj;
	}

	public String getNeonatebw() {
		return this.neonatebw;
	}

	public void setNeonatebw(String neonatebw) {
		this.neonatebw = neonatebw;
	}

	public String getNeonatebwName() {
		return am.F(this.neonatebw).getName();
	}

	public void setNeonatebwName(String neonatebwName) {
		this.neonatebwName = neonatebwName;
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	@JsonSerialize(using = c.class)
	public Date getSubmiAt() {
		return this.submiAt;
	}

	public void setSubmiAt(Date submiAt) {
		this.submiAt = submiAt;
	}

	public String getSubmiDeptName() {
		return this.submiDeptName;
	}

	public void setSubmiDeptName(String submiDeptName) {
		this.submiDeptName = submiDeptName;
	}

	public String getItemTypeName() {
		return this.itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	@JsonSerialize(using = c.class)
	public Date getTestDate() {
		return this.testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getPathoName() {
		return this.pathoName;
	}

	public void setPathoName(String pathoName) {
		this.pathoName = pathoName;
	}

	public String getBkCount() {
		return this.bkCount;
	}

	public void setBkCount(String bkCount) {
		this.bkCount = bkCount;
	}

	public Integer getCrbCount() {
		return this.crbCount;
	}

	public void setCrbCount(Integer crbCount) {
		this.crbCount = crbCount;
	}

	public Integer getSyCount() {
		return this.syCount;
	}

	public void setSyCount(Integer syCount) {
		this.syCount = syCount;
	}

	public Integer getJcCount() {
		return this.jcCount;
	}

	public void setJcCount(Integer jcCount) {
		this.jcCount = jcCount;
	}

	public Double getTw() {
		return this.tw;
	}

	public void setTw(Double tw) {
		this.tw = tw;
	}

	public String getLisBytname() {
		return this.lisBytname;
	}

	public void setLisBytname(String lisBytname) {
		this.lisBytname = lisBytname;
	}

	public String getPropName() {
		return this.propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getSpecDescribes() {
		return this.specDescribes;
	}

	public void setSpecDescribes(String specDescribes) {
		this.specDescribes = specDescribes;
	}

	@JsonSerialize(using = c.class)
	public Date getMoniDate() {
		return this.moniDate;
	}

	public void setMoniDate(Date moniDate) {
		this.moniDate = moniDate;
	}

	public String getInfection() {
		return this.infection;
	}

	public void setInfection(String infection) {
		this.infection = infection;
	}

	public String getAgeFw() {
		return this.ageFw;
	}

	public void setAgeFw(String ageFw) {
		this.ageFw = ageFw;
	}

	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getLxrName() {
		return this.lxrName;
	}

	public void setLxrName(String lxrName) {
		this.lxrName = lxrName;
	}

	public String getLxrPhone() {
		return this.lxrPhone;
	}

	public void setLxrPhone(String lxrPhone) {
		this.lxrPhone = lxrPhone;
	}

	public String getWorkAddr() {
		return this.workAddr;
	}

	public void setWorkAddr(String workAddr) {
		this.workAddr = workAddr;
	}

	public String getChargeNrId() {
		return this.chargeNrId;
	}

	public void setChargeNrId(String chargeNrId) {
		this.chargeNrId = chargeNrId;
	}

	public String getChargeNrName() {
		return this.chargeNrName;
	}

	public void setChargeNrName(String chargeNrName) {
		this.chargeNrName = chargeNrName;
	}

	public String getMdroCount() {
		return this.mdroCount;
	}

	public void setMdroCount(String mdroCount) {
		this.mdroCount = mdroCount;
	}

	public List<St004Yzxxb> getSt004List() {
		return this.st004List;
	}

	public void setSt004List(List<St004Yzxxb> st004List) {
		this.st004List = st004List;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getQzValue() {
		return this.qzValue;
	}

	public void setQzValue(String qzValue) {
		this.qzValue = qzValue;
	}

	public String getGm4DeptName() {
		return this.gm4DeptName;
	}

	public void setGm4DeptName(String gm4DeptName) {
		this.gm4DeptName = gm4DeptName;
	}

	public String getMdroYCount() {
		return this.mdroYCount;
	}

	public void setMdroYCount(String mdroYCount) {
		this.mdroYCount = mdroYCount;
	}

	public String getZdCount() {
		return this.zdCount;
	}

	public void setZdCount(String zdCount) {
		this.zdCount = zdCount;
	}

	public String getZdYCount() {
		return this.zdYCount;
	}

	public void setZdYCount(String zdYCount) {
		this.zdYCount = zdYCount;
	}
}