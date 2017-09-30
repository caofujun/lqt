package com.nis.msg.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.access.service.AcAccountService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.aa;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.msg.dao.NyMessageDetailDao;
import com.nis.msg.dao.NyMessageUserDetailDao;
import com.nis.msg.entity.NyMessageDetail;
import com.nis.msg.entity.NyMessageTheme;
import com.nis.msg.entity.NyMessageUser;
import com.nis.msg.entity.NyMessageUserDetail;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.msg.service.NyMessageThemeService;
import com.nis.msg.service.NyMessageUserDetailService;
import com.nis.msg.service.NyMessageUserService;
import com.nis.msg.service.impl.SendDD;
import com.nis.msg.service.impl.SendWebCat;
import com.nis.organization.entity.Dep;
import com.nis.organization.entity.Doctor;
import com.nis.organization.service.DepService;
import com.nis.organization.service.DoctorService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St020ClinicPatientsService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NyMessageDetailServiceImpl implements NyMessageDetailService {
	@Autowired
	private NyMessageDetailDao uW;
	@Autowired
	private NyMessageThemeService uS;
	@Autowired
	private NyMessageUserDetailService cT;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private DoctorService f;
	@Autowired
	private DepService e;
	@Autowired
	private AcAccountService uJ;
	@Autowired
	private NyMessageUserDetailDao uX;
	@Autowired
	private NyMessageUserService uT;
	@Autowired
	private SysParamService j;
	@Autowired
	private SendWebCat uY;
	@Autowired
	private SendDD uZ;

	public void save(NyMessageDetail nyMessageDetail) {
		nyMessageDetail.setMid(z.a(bg.nl));
		this.uW.save(nyMessageDetail);
	}

	public void delete(String mid) {
		this.uW.delete(mid);
	}

	public void update(NyMessageDetail nyMessageDetail) {
		this.uW.update(nyMessageDetail);
	}

	public NyMessageDetail get(String mid) {
		return this.uW.get(mid);
	}

	public MyPage<NyMessageDetail> a(NyMessageDetail nyMessageDetail) {
		int total = this.uW.findNyMessageDetailCount(nyMessageDetail);
		List data = null;
		if (total > 0) {
			data = this.uW.findNyMessageDetail(nyMessageDetail);
		}

		return new MyPage(nyMessageDetail.getPage().intValue(), nyMessageDetail.getSize().intValue(), total, data);
	}

	public List<NyMessageDetail> getAll() {
		return this.uW.getAll();
	}

	public List<NyMessageDetail> getbyThemeId(String themeId) {
		return this.uW.getbyThemeId(themeId);
	}

	public void j(String appid, String url, String userid, String content) {
		String title = content;
		if (content.length() > 10) {
			title = content.substring(0, 9);
		}

		this.uY.e(url, appid, userid, title, content);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(String zyid, String mzid, String sendUserId, String sendUserName, String content, String[] userIds,
			String[] deptIds, String msgType, String caseId) {
		new NyMessageTheme();
		String appid = this.j.findByParamCode(Param.NIS_MSG_WEBCATE_APPID);
		String url = this.j.findByParamCode(Param.NIS_MSG_WEBCATE_URL);
		String ddAppid = this.j.findByParamCode(Param.NIS_MSG_DD_APPID);
		String ddUrl = this.j.findByParamCode(Param.NIS_MSG_DD_URL);
		String qname = this.j.findByParamCode(Param.NIS_MSG_DD_QNAME);
		String color = this.j.findByParamCode(Param.NIS_MSG_DD_COLOR);
		NyMessageTheme nyMessageTheme;
		if (ab.isNotEmpty(zyid)) {
			nyMessageTheme = this.uS.getByZyid(zyid);
			if (nyMessageTheme == null) {
				nyMessageTheme = new NyMessageTheme();
				nyMessageTheme.setCreateTime(new Date());
				nyMessageTheme.setCreateUser(sendUserId);
				nyMessageTheme.setZyid(zyid);
				St003Cryxxb detailList = this.bg.get(zyid);
				nyMessageTheme.setTheme("患者" + (detailList != null ? detailList.getPatientName() : "") + "的消息");
				this.uS.save(nyMessageTheme);
			}
		} else if (ab.isNotEmpty(mzid)) {
			nyMessageTheme = this.uS.getByMzid(mzid);
			if (nyMessageTheme == null) {
				nyMessageTheme = new NyMessageTheme();
				nyMessageTheme.setCreateTime(new Date());
				nyMessageTheme.setCreateUser(sendUserId);
				nyMessageTheme.setMzid(mzid);
				St020ClinicPatients arg33 = this.bh.getByMzid(mzid, (String) null);
				nyMessageTheme.setTheme("患者" + (arg33 != null ? arg33.getPatientName() : "") + "的消息");
				this.uS.save(nyMessageTheme);
			}
		} else {
			nyMessageTheme = this.uS.getByCreateUser(sendUserId);
			if (nyMessageTheme == null) {
				nyMessageTheme = new NyMessageTheme();
				nyMessageTheme.setCreateTime(new Date());
				nyMessageTheme.setCreateUser(sendUserId);
				nyMessageTheme.setZyid(zyid);
				nyMessageTheme.setTheme(sendUserName + "的消息内容");
				this.uS.save(nyMessageTheme);
			}
		}

		List arg34 = this.uW.findByCaseId(caseId);
		int MT = 0;
		if (arg34 != null && arg34.size() > 0) {
			try {
				MT = Integer.parseInt(((NyMessageDetail) arg34.get(0)).getMsgType());
			} catch (Exception arg32) {
				MT = 0;
			}
		}

		if (arg34 == null || arg34.size() == 0 || MT >= 8 && MT <= 28 || MT >= 100 && MT < 210 || MT == 2) {
			NyMessageDetail nyMessageDetail = new NyMessageDetail();
			nyMessageDetail.setThemeId(nyMessageTheme.getThemeId());
			nyMessageDetail.setSendUserId(sendUserId);
			nyMessageDetail.setSendUserName(sendUserName);
			nyMessageDetail.setContent(content);
			nyMessageDetail.setSendTime(new Date());
			nyMessageDetail.setMsgType(msgType);
			nyMessageDetail.setCaseId(caseId);
			AcAccount account = this.uJ.b(sendUserId);
			String deptCode = null;
			if (account != null) {
				deptCode = account.getDepNo();
			} else {
				Doctor userDetailList = this.f.ck(sendUserId);
				deptCode = userDetailList.getDeptId();
			}

			if (deptCode != null) {
				if (deptCode.indexOf(",") == -1) {
					nyMessageDetail.setSendDeptId(deptCode);
					Dep arg35 = this.e.get(deptCode);
					if (arg35 != null) {
						nyMessageDetail.setSendDeptName(arg35.getDeptName());
					}
				} else if (deptCode.indexOf(",") > -1) {
					String arg36 = deptCode.split(",")[0];
					nyMessageDetail.setSendDeptId(arg36);
					Dep nyMessageUser = this.e.get(arg36);
					if (nyMessageUser != null) {
						nyMessageDetail.setSendDeptName(nyMessageUser.getDeptName());
					}
				}
			}

			this.save(nyMessageDetail);
			nyMessageTheme.setLastMid(nyMessageDetail.getMid());
			this.uS.update(nyMessageTheme);
			ArrayList arg37 = new ArrayList();
			int userDetail;
			int arg24;
			String[] arg25;
			String arg38;
			if (userIds != null) {
				arg25 = userIds;
				arg24 = userIds.length;

				for (userDetail = 0; userDetail < arg24; ++userDetail) {
					arg38 = arg25[userDetail];
					NyMessageUserDetail doctors = new NyMessageUserDetail();
					doctors.setMudId(z.a(bg.nn));
					doctors.setMid(nyMessageDetail.getMid());
					doctors.setUserId(arg38);
					doctors.setIsRead(aa.hG.getValue());
					doctors.setThemeId(nyMessageTheme.getThemeId());
					arg37.add(doctors);
					NyMessageUser deptList = new NyMessageUser();
					deptList.setUserId(arg38);
					deptList.setThemeId(nyMessageDetail.getThemeId());
					deptList.setCreateTime(new Date());
					this.uT.a(deptList);
				}
			}

			if (deptIds != null) {
				arg25 = deptIds;
				arg24 = deptIds.length;

				for (userDetail = 0; userDetail < arg24; ++userDetail) {
					arg38 = arg25[userDetail];
					List arg42 = this.f.findDoctorListByDept((String) null, (List) null, arg38);
					ArrayList arg43 = new ArrayList();
					arg43.add(arg38);
					List accountList = this.uJ.findByDeptIds(arg43);

					for (int nyMessageUser1 = 0; nyMessageUser1 < accountList.size(); ++nyMessageUser1) {
						Doctor doc = new Doctor();
						doc.setEmployeeId(((AcAccount) accountList.get(nyMessageUser1)).getUsername());
						doc.setEmployeeName(((AcAccount) accountList.get(nyMessageUser1)).getRealname());
						arg42.add(doc);
					}

					Iterator arg46 = arg42.iterator();

					while (arg46.hasNext()) {
						Doctor arg44 = (Doctor) arg46.next();
						NyMessageUserDetail userDetail1 = new NyMessageUserDetail();
						userDetail1.setMudId(z.a(bg.nn));
						userDetail1.setMid(nyMessageDetail.getMid());
						userDetail1.setUserId(arg44.getEmployeeId());
						userDetail1.setIsRead(aa.hG.getValue());
						userDetail1.setThemeId(nyMessageTheme.getThemeId());
						arg37.add(userDetail1);
					}

					NyMessageUser arg45 = new NyMessageUser();
					arg45.setDeptId(arg38);
					arg45.setThemeId(nyMessageDetail.getThemeId());
					arg45.setCreateTime(new Date());
					this.uT.a(arg45);
				}
			}

			if (arg37.size() > 0) {
				arg38 = "";
				NyMessageUserDetail arg40;
				if (!"0".equals(appid)) {
					for (Iterator arg41 = arg37.iterator(); arg41.hasNext(); arg38 = arg38 + arg40.getUserId() + "|") {
						arg40 = (NyMessageUserDetail) arg41.next();
						this.j(appid, url, arg40.getUserId(), content);
					}
				}

				if (!"0".equals(ddAppid)) {
					this.a(ddAppid, qname, ddUrl, color, arg38, "", nyMessageDetail.getContent());
				}

				this.uX.saveList(arg37);
			}

			NyMessageUser arg39 = new NyMessageUser();
			arg39.setUserId(sendUserId);
			arg39.setThemeId(nyMessageDetail.getThemeId());
			arg39.setCreateTime(new Date());
			this.uT.a(arg39);
		}

	}

	public List<NyMessageDetail> getByUserId(String userId) {
		return this.uW.getByUserId(userId);
	}

	public int getPageByUserIdCount(String userId, String startDate, String endDate) {
		return this.uW.getPageByUserIdCount(userId, startDate, endDate);
	}

	public List<NyMessageDetail> getPageByUserId(String userId, String startDate, String endDate, Integer orderType,
			String orderBy, Integer orclBegNum, Integer orclEndNum) {
		return this.uW.getPageByUserId(userId, startDate, endDate, orderType, orderBy, orclBegNum, orclEndNum);
	}

	public List<NyMessageDetail> findListByCaseId(String caseId) {
		return this.uW.findListByCaseId(caseId);
	}

	public int getMsgsCount(NyMessageDetail nyMessageDetail) {
		return this.uW.getMsgsCount(nyMessageDetail);
	}

	public MyPage<NyMessageDetail> b(NyMessageDetail nyMessageDetail) {
		if (nyMessageDetail.getPage() == null) {
			nyMessageDetail.setPage(Integer.valueOf(1));
		}

		if (nyMessageDetail.getSize() == null) {
			nyMessageDetail.setSize(Integer.valueOf(8));
		}

		System.err.println("page:" + nyMessageDetail.getPage() + " size:" + nyMessageDetail.getSize());
		int total = this.uW.getMsgsCount(nyMessageDetail);
		List data = null;
		if (total > 0) {
			data = this.uW.getMsgs(nyMessageDetail);
		}

		return new MyPage(nyMessageDetail.getPage().intValue(), nyMessageDetail.getSize().intValue(), total, data);
	}

	public void a(String appid, String qname, String url, String color, String userid, String deptId, String content) {
		String head_title = content;
		if (content.length() > 10) {
			head_title = content.substring(0, 9);
		}

		this.uZ.a(url, qname, appid, userid, deptId, "12345", color, head_title, content, new ArrayList(),
				new ArrayList());
	}

	public int getUnreadMsgsCountForInterFace(NyMessageDetail nyMessageDetail) {
		return this.uW.getUnreadMsgsCountForInterFace(nyMessageDetail);
	}

	public MyPage<NyMessageDetail> c(NyMessageDetail nyMessageDetail) {
		int total = this.uW.getUnreadMsgsCountForInterFace(nyMessageDetail);
		List data = null;
		if (total > 0) {
			data = this.uW.getUnreadMsgsForInterFace(nyMessageDetail);
		}

		return new MyPage(nyMessageDetail.getPage().intValue(), nyMessageDetail.getSize().intValue(), total, data);
	}

	public List<NyMessageDetail> getUnreadMsgsForInterFace(NyMessageDetail nyMessageDetail) {
		return this.uW.getUnreadMsgsForInterFace(nyMessageDetail);
	}
}