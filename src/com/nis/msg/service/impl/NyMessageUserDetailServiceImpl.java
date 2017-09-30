package com.nis.msg.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.access.service.AcAccountService;
import com.nis.bl.entity.Bl007Fcsj;
import com.nis.bl.service.Bl007FcsjService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.aa;
import com.nis.comm.enums.al;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.msg.dao.NyMessageUserDetailDao;
import com.nis.msg.entity.NyMessageDetail;
import com.nis.msg.entity.NyMessageUser;
import com.nis.msg.entity.NyMessageUserDetail;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.msg.service.NyMessageUserDetailService;
import com.nis.msg.service.NyMessageUserService;
import com.nis.organization.entity.Doctor;
import com.nis.organization.service.DoctorService;
import com.nis.param.service.SysParamService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NyMessageUserDetailServiceImpl implements NyMessageUserDetailService {
	@Autowired
	private NyMessageUserDetailDao uX;
	@Autowired
	private NyMessageUserService uT;
	@Autowired
	private AcAccountService uJ;
	@Autowired
	private DoctorService f;
	@Autowired
	private SysParamService j;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private Bl007FcsjService cX;

	public void save(NyMessageUserDetail nyMessageUserDetail) {
		nyMessageUserDetail.setMudId(z.a(bg.nn));
		this.uX.save(nyMessageUserDetail);
	}

	public void delete(String mudId) {
		this.uX.delete(mudId);
	}

	public void update(NyMessageUserDetail nyMessageUserDetail) {
		this.uX.update(nyMessageUserDetail);
	}

	public NyMessageUserDetail get(String mudId) {
		return this.uX.get(mudId);
	}

	public MyPage<NyMessageUserDetail> a(NyMessageUserDetail nyMessageUserDetail) {
		int total = this.uX.findNyMessageUserDetailCount(nyMessageUserDetail);
		List data = null;
		if (total > 0) {
			data = this.uX.findNyMessageUserDetail(nyMessageUserDetail);
		}

		return new MyPage(nyMessageUserDetail.getPage().intValue(), nyMessageUserDetail.getSize().intValue(), total,
				data);
	}

	public List<NyMessageUserDetail> getAll() {
		return this.uX.getAll();
	}

	@Transactional(rollbackFor = {Exception.class})
	public List<NyMessageUserDetail> a(String msgUserIds, NyMessageDetail nyMessageDetail) {
		if (!StringUtils.isNotEmpty(msgUserIds)) {
			return null;
		} else {
			String[] msgUserId = msgUserIds.split(",");
			List messageUserList = this.uT.getbyList(msgUserId);
			ArrayList doctorList = new ArrayList();
			ArrayList deptList = new ArrayList();
			Iterator doctor = messageUserList.iterator();

			while (doctor.hasNext()) {
				NyMessageUser userDetailList = (NyMessageUser) doctor.next();
				if (StringUtils.isNotEmpty(userDetailList.getDeptId())) {
					deptList.add(userDetailList.getDeptId());
				} else if (StringUtils.isNotEmpty(userDetailList.getUserId())) {
					Doctor i = new Doctor();
					i.setEmployeeId(userDetailList.getUserId());
					if (userDetailList.getUserName() == null) {
						i.setEmployeeName(userDetailList.getDoctorName());
					} else {
						i.setEmployeeName(userDetailList.getUserName());
					}

					doctorList.add(i);
				}
			}

			if (deptList.size() > 0) {
				List arg10 = this.f.findDoctorListByDept((String) null, deptList, (String) null);
				List arg12 = this.uJ.findByDeptIds(deptList);

				for (int arg14 = 0; arg14 < arg12.size(); ++arg14) {
					Doctor userDetail = new Doctor();
					userDetail.setEmployeeId(((AcAccount) arg12.get(arg14)).getUsername());
					userDetail.setEmployeeName(((AcAccount) arg12.get(arg14)).getRealname());
					doctorList.add(userDetail);
				}

				doctorList.addAll(arg10);
			}

			ArrayList arg11 = new ArrayList();
			Iterator arg15 = doctorList.iterator();

			while (arg15.hasNext()) {
				Doctor arg13 = (Doctor) arg15.next();
				if (!arg13.getEmployeeId().equals(nyMessageDetail.getSendUserId())) {
					NyMessageUserDetail arg16 = new NyMessageUserDetail();
					arg16.setMudId(z.a(bg.nn));
					arg16.setMid(nyMessageDetail.getMid());
					arg16.setThemeId(nyMessageDetail.getThemeId());
					arg16.setUserId(arg13.getEmployeeId());
					arg16.setUserName(arg13.getEmployeeName());
					arg16.setIsRead(aa.hG.getValue());
					arg11.add(arg16);
				}
			}

			if (arg11.size() > 0) {
				this.uX.saveList(arg11);
			}

			return arg11;
		}
	}

	public List<NyMessageDetail> getByUserId(String userId) {
		List msgList = this.cV.getByUserId(userId);
		AcAccount account = this.uJ.b(userId);
		if (account == null) {
			Doctor token = this.f.ck(userId);
			if (token != null) {
				account = new AcAccount();
				account.setUsername(token.getEmployeeId());
				account.setPasswd(token.getAuthCode());
			}
		}

		String token1 = this.j.findByParamCode(Param.NIS_MSG_TOKEN);
		String httpUrl = this.j.findByParamCode(Param.NIS_HTTP_URL);

		NyMessageDetail detail;
		String url;
		for (Iterator arg6 = msgList.iterator(); arg6.hasNext(); detail.setUrl(url)) {
			detail = (NyMessageDetail) arg6.next();
			url = "";
			if (ab.isEmpty(detail.getMsgType())) {
				if (ab.isNotEmpty(detail.getZyid())) {
					url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid() + "&username="
							+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1
							+ "&userId=" + detail.getSendUserId();
				} else {
					url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId() + "&username="
							+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1
							+ "&userId=" + detail.getSendUserId();
				}
			} else if (!al.jl.getValue().equals(detail.getMsgType()) && !al.jm.getValue().equals(detail.getMsgType())) {
				if (!al.jn.getValue().equals(detail.getMsgType()) && !al.jn.getValue().equals(detail.getMsgType())) {
					if (al.jo.getValue().equals(detail.getMsgType())) {
						if (ab.isNotEmpty(detail.getZyid())) {
							url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid()
									+ "&username=" + account.getUsername() + "&password=" + account.getPasswd()
									+ "&token=" + token1 + "&userId=" + detail.getSendUserId();
						} else {
							url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId()
									+ "&username=" + account.getUsername() + "&password=" + account.getPasswd()
									+ "&token=" + token1 + "&userId=" + detail.getSendUserId();
						}
					} else if (al.js.getValue().equals(detail.getMsgType())) {
						Bl007Fcsj fcsj = this.cX.get(detail.getCaseId());
						url = httpUrl + "/bl002Sjdj/f_view/toedit.shtml?id=" + fcsj.getBlId() + "&msgType="
								+ detail.getMsgType() + "&username=" + account.getUsername() + "&password="
								+ account.getPasswd() + "&token=" + token1;
					} else if (ab.isNotEmpty(detail.getZyid())) {
						url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid() + "&username="
								+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1
								+ "&userId=" + detail.getSendUserId();
					} else {
						url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId()
								+ "&username=" + account.getUsername() + "&password=" + account.getPasswd() + "&token="
								+ token1 + "&userId=" + detail.getSendUserId();
					}
				} else if (ab.isNotEmpty(detail.getZyid())) {
					url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid() + "&username="
							+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1
							+ "&userId=" + detail.getSendUserId();
				} else {
					url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId() + "&username="
							+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1
							+ "&userId=" + detail.getSendUserId();
				}
			} else if (ab.isNotEmpty(detail.getZyid())) {
				url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid() + "&username="
						+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1 + "&userId="
						+ detail.getSendUserId();
			} else {
				url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId() + "&username="
						+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1 + "&userId="
						+ detail.getSendUserId();
			}
		}

		return msgList;
	}

	public void updateByThemeIdAndUserId(String themeId, String userId) {
		this.uX.updateByThemeIdAndUserId(themeId, userId);
	}

	public MyPage<NyMessageDetail> a(String userId, Integer page, Integer size, String startDate, String endDate,
			Integer orderType, String orderBy) {
		int total = this.cV.getPageByUserIdCount(userId, startDate, endDate);
		List data = null;
		if (total > 0) {
			if (page == null) {
				page = Integer.valueOf(1);
			}

			if (size == null) {
				size = Integer.valueOf(10);
			}

			Integer orclBegNum = Integer.valueOf((page.intValue() - 1) * size.intValue() + 1);
			Integer orclEndNum = Integer.valueOf(page.intValue() * size.intValue());
			data = this.cV.getPageByUserId(userId, startDate, endDate, orderType, orderBy, orclBegNum, orclEndNum);
			AcAccount account = this.uJ.b(userId);
			if (account == null) {
				Doctor token = this.f.ck(userId);
				account = new AcAccount();
				account.setUsername(token.getEmployeeId());
				account.setPasswd(token.getAuthCode());
			}

			String token1 = this.j.findByParamCode(Param.NIS_MSG_TOKEN);
			String httpUrl = this.j.findByParamCode(Param.NIS_HTTP_URL);

			NyMessageDetail detail;
			String url;
			for (Iterator arg15 = data.iterator(); arg15.hasNext(); detail.setUrl(url)) {
				detail = (NyMessageDetail) arg15.next();
				url = "";
				if (ab.isEmpty(detail.getMsgType())) {
					if (ab.isNotEmpty(detail.getZyid())) {
						url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid() + "&username="
								+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1
								+ "&userId=" + detail.getSendUserId();
					} else {
						url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId()
								+ "&username=" + account.getUsername() + "&password=" + account.getPasswd() + "&token="
								+ token1 + "&userId=" + detail.getSendUserId();
					}
				} else if (!al.jn.getValue().equals(detail.getMsgType())
						&& !al.jn.getValue().equals(detail.getMsgType())) {
					if (!al.jl.getValue().equals(detail.getMsgType())
							&& !al.jm.getValue().equals(detail.getMsgType())) {
						if (al.jo.getValue().equals(detail.getMsgType())) {
							if (ab.isNotEmpty(detail.getZyid())) {
								url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid()
										+ "&username=" + account.getUsername() + "&password=" + account.getPasswd()
										+ "&token=" + token1 + "&userId=" + detail.getSendUserId();
							} else {
								url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId()
										+ "&username=" + account.getUsername() + "&password=" + account.getPasswd()
										+ "&token=" + token1 + "&userId=" + detail.getSendUserId();
							}
						} else if (al.js.getValue().equals(detail.getMsgType())) {
							Bl007Fcsj fcsj = this.cX.get(detail.getCaseId());
							url = httpUrl + "/bl002Sjdj/f_view/toedit.shtml?id=" + fcsj.getBlId() + "&msgType="
									+ detail.getMsgType() + "&username=" + account.getUsername() + "&password="
									+ account.getPasswd() + "&token=" + token1;
						} else if (ab.isNotEmpty(detail.getZyid())) {
							url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid()
									+ "&username=" + account.getUsername() + "&password=" + account.getPasswd()
									+ "&token=" + token1 + "&userId=" + detail.getSendUserId();
						} else {
							url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId()
									+ "&username=" + account.getUsername() + "&password=" + account.getPasswd()
									+ "&token=" + token1 + "&userId=" + detail.getSendUserId();
						}
					} else if (ab.isNotEmpty(detail.getZyid())) {
						url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid() + "&username="
								+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1
								+ "&userId=" + detail.getSendUserId();
					} else {
						url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId()
								+ "&username=" + account.getUsername() + "&password=" + account.getPasswd() + "&token="
								+ token1 + "&userId=" + detail.getSendUserId();
					}
				} else if (ab.isNotEmpty(detail.getZyid())) {
					url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?zyid=" + detail.getZyid() + "&username="
							+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1
							+ "&userId=" + detail.getSendUserId();
				} else {
					url = httpUrl + "/nyMessageTheme/f_view/toedit.shtml?themeId=" + detail.getThemeId() + "&username="
							+ account.getUsername() + "&password=" + account.getPasswd() + "&token=" + token1
							+ "&userId=" + detail.getSendUserId();
				}
			}
		}

		return new MyPage(page.intValue(), size.intValue(), total, data);
	}

	public void updateByCaseIdAndUserId(String caseId, String userId) {
		this.uX.updateByCaseIdAndUserId(caseId, userId);
	}

	public Integer unreadMsgCount(String userId) {
		return this.uX.unreadMsgCount(userId);
	}

	public void markAsRead(String mid, String themeId, String userId) {
		this.uX.markAsRead(mid, themeId, userId);
	}
}