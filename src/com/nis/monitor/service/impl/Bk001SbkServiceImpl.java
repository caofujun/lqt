package com.nis.monitor.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bc;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.g;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.monitor.dao.Bk001SbkDao;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Gr002YsgrMx;
import com.nis.monitor.service.Bk001SbkService;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.monitor.service.Bk003YgysService;
import com.nis.monitor.service.Bk004SjbbService;
import com.nis.monitor.service.Gr002YsgrMxService;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St005Ssxxb;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.zg.dao.Zg002ByksDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Bk001SbkServiceImpl implements Bk001SbkService {
	@Autowired
	private Bk001SbkDao uC;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private Gr002YsgrMxService bX;
	@Autowired
	private Zg002ByksDao tm;
	@Autowired
	private Bk002GrzdService us;
	@Autowired
	private Bk003YgysService ut;
	@Autowired
	private Bk004SjbbService co;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private SysParamService j;
	@Autowired
	private St005SsxxbService bO;

	public void save(Bk001Sbk bk001Sbk) {
		bk001Sbk.setRelid(z.a(com.nis.comm.enums.bg.mT));
		this.uC.save(bk001Sbk);
	}

	public void delete(String relid) {
		this.uC.delete(relid);
	}

	public void update(Bk001Sbk bk001Sbk) {
		this.uC.update(bk001Sbk);
	}

	public void updateSpecified(Bk001Sbk bk001Sbk, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.uC.updateSpecified(bk001Sbk, updateAttrs);
		}

	}

	public Bk001Sbk get(String relid) {
		return this.uC.get(relid);
	}

	public MyPage<Bk001Sbk> a(Bk001Sbk bk001Sbk) {
		int total = this.uC.findBk001SbkCount(bk001Sbk);
		List data = null;
		if (total > 0) {
			data = this.uC.findBk001Sbk(bk001Sbk);
		}

		return new MyPage(bk001Sbk.getPage().intValue(), bk001Sbk.getSize().intValue(), total, data);
	}

	public List<Bk001Sbk> getAll() {
		return this.uC.getAll();
	}

	@SqlLog(p = "报卡--感染病例列表")
	public MyPage<Bk001Sbk> b(Bk001Sbk bk001Sbk) {
		List data = this.uC.findInfectionsCards(bk001Sbk);
		return new MyPage(1, data.size(), data.size(), data);
	}

	@SqlLog(p = "报卡--患者报卡列表")
	public List<Bk001Sbk> findReportCards(Bk001Sbk bk001Sbk) {
		return this.uC.findReportCards(bk001Sbk);
	}

	public Bk001Sbk findCardInfo(String relid) {
		return this.uC.findCardInfo(relid);
	}

	public void c(Bk001Sbk bk001Sbk) {
		ArrayList updateAttrs = new ArrayList();
		updateAttrs.add("chargeDrId");
		this.updateSpecified(bk001Sbk, updateAttrs);
	}

	public void d(Bk001Sbk bk001Sbk) {
		ArrayList updateAttrs = new ArrayList();
		updateAttrs.add("jbzdCode");
		updateAttrs.add("jbzd");
		this.updateSpecified(bk001Sbk, updateAttrs);
	}

	public void a(Bk001Sbk bk001Sbk, Gr002YsgrMx gr002YsgrMx, LoginUser user, String chargeDrId, String jbzd,
			String jbzdCode) {
		St003Cryxxb st003Cryxxb = this.bg.getSt003Cryxxb(gr002YsgrMx.getZyid());
		if (ab.isNotEmpty(st003Cryxxb.getAge())) {
			bk001Sbk.setAge(st003Cryxxb.getAge());
		}

		bk001Sbk.setAgeUnit(st003Cryxxb.getAgeUnit());
		bk001Sbk.setBedNo(st003Cryxxb.getBedNo());
		bk001Sbk.setBkType(g.fK.getValue());
		bk001Sbk.setCardSource(bc.lV.getValue());
		bk001Sbk.setChargeDrId(chargeDrId);
		bk001Sbk.setInfectDeptId(gr002YsgrMx.getInfectDeptId());
		bk001Sbk.setInHospAt(st003Cryxxb.getInHospAt());
		bk001Sbk.setIsDel(Integer.valueOf(0));
		bk001Sbk.setIsCb(Integer.valueOf(0));
		bk001Sbk.setIsLb(Integer.valueOf(0));
		bk001Sbk.setIsGr(Integer.valueOf(0));
		bk001Sbk.setIsOk(com.nis.comm.enums.z.hz.getValue());
		bk001Sbk.setJbzd(jbzd);
		bk001Sbk.setJbzdCode(jbzdCode);
		bk001Sbk.setLastoperDate(new Date());
		bk001Sbk.setLastoperName(user.getUsername());
		bk001Sbk.setPatientId(st003Cryxxb.getPatientId());
		bk001Sbk.setPatientName(st003Cryxxb.getPatientName());
		bk001Sbk.setReportAt(new Date());
		int reportVisit = this.uC.findReportNum(gr002YsgrMx.getZyid());
		bk001Sbk.setReportVisit(Integer.valueOf(reportVisit + 1));
		bk001Sbk.setRyzd(st003Cryxxb.getRyzd());
		bk001Sbk.setRyzdCode(st003Cryxxb.getRyzdCode());
		bk001Sbk.setSex(st003Cryxxb.getSex());
		bk001Sbk.setVisitId(st003Cryxxb.getVisitId());
		bk001Sbk.setWeight(Double.valueOf(0.0D));
		bk001Sbk.setZyid(st003Cryxxb.getZyid());
		this.save(bk001Sbk);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Bk002Grzd bk002Grzd, LoginUser user, String chargeDrId, String jbzd, String jbzdCode,
			Gr002YsgrMx gr002YsgrMx, Bk001Sbk bk001Sbk) {
		this.a(bk001Sbk, gr002YsgrMx, user, chargeDrId, jbzd, jbzdCode);
		bk002Grzd.setRefid(bk001Sbk.getRelid());
		this.us.a(bk002Grzd, bk001Sbk, user);
		if (ab.isNotEmpty(bk002Grzd.getOpeRelid())) {
			St005Ssxxb st005Ssxxb = this.bO.getByRelid(bk002Grzd.getOpeRelid());
			if (ab.isNotEmpty(bk002Grzd.getMemo())) {
				st005Ssxxb.setIncisionGrade(bk002Grzd.getMemo());
			}

			String attrStr = "incisionGrade";
			List updateAttrs = Arrays.asList(attrStr.split(","));
			this.bO.updateSpecified(st005Ssxxb, updateAttrs);
		}

		if (gr002YsgrMx.getInfectCode() != null && gr002YsgrMx.getInfectCode().equals(bk002Grzd.getInfectDiagnId())) {
			this.us.b(gr002YsgrMx, bk001Sbk, bk002Grzd);
		}

		this.ut.b(bk002Grzd, bk002Grzd.getFactorIds());
		this.co.a(bk002Grzd, bk001Sbk, bk002Grzd.getTestOrderNos(), user.getDepNo());
	}

	public List<Bk001Sbk> findByZyid(String zyid) {
		return this.uC.findByZyid(zyid);
	}

	public List<Bk001Sbk> getByTestNoAndPathoId(String testno, String pathoid) {
		return this.uC.getByTestNoAndPathoId(testno, pathoid);
	}

	public List<Bk001Sbk> D(String testno, String pathoid) {
		return this.uC.getByTestNoAndPathoIdnew(testno, pathoid);
	}

	public List<Bk001Sbk> isReportBefore(String zyid, String idi, String ift) {
		return this.uC.isReportBefore(zyid, idi, ift);
	}

	public List<Bk001Sbk> findByZyidAndCode(String zyid, String idi) {
		return this.uC.findByZyidAndCode(zyid, idi);
	}

	public List<Bk001Sbk> findByZyidState(String zyid) {
		return this.uC.findByZyidState(zyid);
	}

	public void b(String relid, AcAccount account) {
		Bk001Sbk bk1 = new Bk001Sbk();
		bk1.setRelid(relid);
		bk1.setLastoperName(account.getRealname());
		bk1.setLastoperDate(new Date());
		this.uC.returnCard(bk1);
		Bk002Grzd bk2 = new Bk002Grzd();
		bk2.setRefid(relid);
		bk2.setLastoperDate(new Date());
		bk2.setLastoperName(account.getRealname());
		this.us.returnCard(bk2);
	}

	public List<Bk001Sbk> getReportHistoryDetailsByZyid(String zyid) {
		return this.uC.getReportHistoryDetailsByZyid(zyid);
	}
}