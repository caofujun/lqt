package com.nis.access.entity;

import com.nis.access.entity.AcRole;
import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.ibatis.type.Alias;

@Alias("acAccount")
public class AcAccount extends BaseEntity implements Serializable {
	private Long errorTimes;
	private String userId;
	private String username;
	private String passwd;
	private String realname;
	private String nickname;
	private Long isvalid;
	private Long userType;
	private String mobilenum;
	private String depNo;
	private Date lastLogintime;
	private String lastIp;
	private Date updTime;
	private Date validDate;
	private String updUser;
	private String unitId;
	private Integer dataScope;
	private String scopeInfo;
	private String email;
	private String doctorId;
	private String docNo;
	private String stringDate;
	private String ext1;
	private String ext2;
	private String acType;
	private List<String> extRoleId;
	private AcRole roleCur;
	private List<AcRole> roles;
	private String showDepName;
	private String showRoleName;
	private String depName;
	private String roleName;
	private String unitName;
	private String roleScope;
	private String source;
	private String employeeId;
	private String id;
	private String ptUserId;
	private String photoPath;
	private String isNeedUpdPwd;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getErrorTimes() {
		return this.errorTimes;
	}

	public void setErrorTimes(Long errorTimes) {
		this.errorTimes = errorTimes;
	}

	public String getScopeInfo() {
		return this.scopeInfo;
	}

	public void setScopeInfo(String scopeInfo) {
		this.scopeInfo = scopeInfo;
	}

	public List<String> getExtRoleId() {
		return this.extRoleId;
	}

	public void setExtRoleId(List<String> extRoleId) {
		this.extRoleId = extRoleId;
	}

	public String getShowDepName() {
		return this.showDepName;
	}

	public void setShowDepName(String showDepName) {
		this.showDepName = showDepName;
	}

	public String getShowRoleName() {
		return this.showRoleName;
	}

	public void setShowRoleName(String showRoleName) {
		this.showRoleName = showRoleName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Long isvalid) {
		this.isvalid = isvalid;
	}

	public Long getUserType() {
		return this.userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public String getMobilenum() {
		return this.mobilenum;
	}

	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}

	public String getDepNo() {
		return this.depNo;
	}

	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}

	public Date getLastLogintime() {
		return this.lastLogintime;
	}

	public void setLastLogintime(Date lastLogintime) {
		this.lastLogintime = lastLogintime;
	}

	public String getLastIp() {
		return this.lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Date getValidDate() {
		return this.validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public Integer getDataScope() {
		return this.dataScope;
	}

	public void setDataScope(Integer dataScope) {
		this.dataScope = dataScope;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDocNo() {
		return this.docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return this.ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public AcRole getRoleCur() {
		return this.roleCur;
	}

	public void setRoleCur(AcRole roleCur) {
		this.roleCur = roleCur;
	}

	public List<AcRole> getRoles() {
		return this.roles;
	}

	public void setRoles(List<AcRole> roles) {
		this.roles = roles;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPtUserId() {
		return this.ptUserId;
	}

	public void setPtUserId(String ptUserId) {
		this.ptUserId = ptUserId;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getIsNeedUpdPwd() {
		return this.isNeedUpdPwd;
	}

	public void setIsNeedUpdPwd(String isNeedUpdPwd) {
		this.isNeedUpdPwd = isNeedUpdPwd;
	}

	public String getStringDate() {
		if (this.getValidDate() == null) {
			return "";
		} else {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
			return DATE_FORMAT.format(this.getValidDate());
		}
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	public String getAcType() {
		return this.acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public String getRoleScope() {
		return this.roleScope;
	}

	public void setRoleScope(String roleScope) {
		this.roleScope = roleScope;
	}
}