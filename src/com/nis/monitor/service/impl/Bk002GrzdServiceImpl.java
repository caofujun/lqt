package com.nis.monitor.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.al;
import com.nis.comm.enums.bb;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.r;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.mdr.entity.Xn011Dclymx;
import com.nis.mdr.service.Xn011DclymxService;
import com.nis.monitor.dao.Bk002GrzdDao;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Bk004Sjbb;
import com.nis.monitor.entity.Gr002YsgrMx;
import com.nis.monitor.entity.Gr011Byt;
import com.nis.monitor.entity.Gr012Ymsy;
import com.nis.monitor.entity.Xn020Gadc;
import com.nis.monitor.service.Bk001SbkService;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.monitor.service.Bk003YgysService;
import com.nis.monitor.service.Bk004SjbbService;
import com.nis.monitor.service.Gr002YsgrMxService;
import com.nis.monitor.service.Gr011BytService;
import com.nis.monitor.service.Gr012YmsyService;
import com.nis.monitor.service.Xn020GadcService;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.patient.entity.St011Syjgb;
import com.nis.patient.service.St009SjbbService;
import com.nis.patient.service.St011SyjgbService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Bk002GrzdServiceImpl implements Bk002GrzdService {
	@Autowired
	private Bk002GrzdDao uD;
	@Autowired
	private Bk001SbkService bW;
	@Autowired
	private Bk003YgysService ut;
	@Autowired
	private Bk004SjbbService co;
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private Gr011BytService uw;
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private Gr012YmsyService ux;
	@Autowired
	private Xn020GadcService uB;
	@Autowired
	private Gr002YsgrMxService bX;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private Xn011DclymxService cn;
	@Autowired
	private SysDictService p;

	public void save(Bk002Grzd bk002Grzd) {
		bk002Grzd.setRelid(z.a(bg.mU));
		this.uD.save(bk002Grzd);
	}

	public void delete(String relid) {
		this.uD.delete(relid);
	}

	public void update(Bk002Grzd bk002Grzd) {
		this.uD.update(bk002Grzd);
	}

	public void updateSpecified(Bk002Grzd bk002Grzd, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.uD.updateSpecified(bk002Grzd, updateAttrs);
		}

	}

	public Bk002Grzd get(String relid) {
		return this.uD.get(relid);
	}

	public MyPage<Bk002Grzd> a(Bk002Grzd bk002Grzd) {
		int total = this.uD.findBk002GrzdCount(bk002Grzd);
		List data = null;
		if (total > 0) {
			data = this.uD.findBk002Grzd(bk002Grzd);
		}

		return new MyPage(bk002Grzd.getPage().intValue(), bk002Grzd.getSize().intValue(), total, data);
	}

	public List<Bk002Grzd> getAll() {
		return this.uD.getAll();
	}

	public List<Bk002Grzd> getReportInfect(String refid, String authStatus) {
		return this.uD.getReportInfect(refid, authStatus);
	}

	public Bk002Grzd getInfectInfo(String relid) {
		return this.uD.getInfectInfo(relid);
	}

	@Transactional(rollbackFor = {Exception.class})
	@Deprecated
	public void a(Bk002Grzd bk002Grzd, Bk001Sbk bk001Sbk, String[] factorIds, String[] testOrderNos, String depNo) {
		String attrBk001 = "chargeDrId,jbzd,jbzdCode";
		List updateAttrs1 = Arrays.asList(attrBk001.split(","));
		this.bW.updateSpecified(bk001Sbk, updateAttrs1);
		String attrStr = "infectType,infectDate,infectDiagnName,infectDeptName,infectDeptId,confirmDt,relation,operId,opeRelid,opeName,memo,bkType,authUserid,authUsername";
		List updateAttrs = Arrays.asList(attrStr.split(","));
		this.updateSpecified(bk002Grzd, updateAttrs);
		Gr002YsgrMx gr002YsgrMx = new Gr002YsgrMx();
		gr002YsgrMx.setGr2Relid(bk002Grzd.getRelid());
		gr002YsgrMx.setInfectTypeId(bk002Grzd.getInfectType());
		gr002YsgrMx.setStartAt(bk002Grzd.getInfectDate());
		gr002YsgrMx.setInfectName(bk002Grzd.getInfectDiagnName());
		gr002YsgrMx.setInfectDeptId(bk002Grzd.getInfectDeptId());
		gr002YsgrMx.setConfDate(bk002Grzd.getConfirmDt());
		gr002YsgrMx.setOperator(bk002Grzd.getAuthUserid());
		this.bX.updateByGr2Relid(gr002YsgrMx);
		this.ut.a(bk002Grzd, factorIds);
		this.co.b(bk002Grzd, bk001Sbk, testOrderNos, depNo);
	}

	@Transactional(rollbackFor = {Exception.class})
	@Deprecated
	public void a(Bk002Grzd bk002Grzd, Bk001Sbk bk001Sbk, String[] factorIds, String[] testOrderNos, LoginUser user,
			Gr002YsgrMx gr002YsgrMx) {
		String attrBk001 = "chargeDrId,jbzd,jbzdCode";
		List updateAttrs1 = Arrays.asList(attrBk001.split(","));
		this.bW.updateSpecified(bk001Sbk, updateAttrs1);
		this.a(bk002Grzd, bk001Sbk, user);
		if (gr002YsgrMx != null && gr002YsgrMx.getInfectCode().equals(bk002Grzd.getInfectDiagnId())) {
			this.b(gr002YsgrMx, bk001Sbk, bk002Grzd);
		}

		this.ut.b(bk002Grzd, factorIds);
		this.co.a(bk002Grzd, bk001Sbk, testOrderNos, user.getDepNo());
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(List<Bk002Grzd> bk002List, Integer authStatus, Bk001Sbk bk001Sbk, LoginUser user) {
		String attrStr = "authAt,authUserid,authUsername,returnReason,authStatus";
		List updateAttrs = Arrays.asList(attrStr.split(","));
		String gr2AttrStr = "state,confDate,lastoperDate";
		List gr2UpdAttrs = Arrays.asList(gr2AttrStr.split(","));
		ArrayList infectCodeList = new ArrayList();
		Iterator updStr = bk002List.iterator();

		while (true) {
			while (updStr.hasNext()) {
				Bk002Grzd bk001 = (Bk002Grzd) updStr.next();
				Bk002Grzd updAttrs = this.get(bk001.getRelid());
				infectCodeList.add(updAttrs.getInfectDiagnId());
				this.updateSpecified(bk001, updateAttrs);
				if (authStatus.intValue() == 1) {
					List gr0022 = this.co.findBk004ByRefid(bk001.getRelid());
					if (gr0022 != null) {
						Iterator bk4List = gr0022.iterator();

						while (bk4List.hasNext()) {
							Bk004Sjbb content3 = (Bk004Sjbb) bk4List.next();
							Gr011Byt gr0021 = new Gr011Byt();
							gr0021.setEnterAt(new Date());
							gr0021.setRelid(bk001Sbk.getRelid());
							gr0021.setSampleId(content3.getSampleId());
							gr0021.setPathogenId(content3.getPathoId());
							gr0021.setPathogenName(content3.getPathoName());
							gr0021.setSubmiAt(content3.getSubmiAt());
							gr0021.setPathogenSn(content3.getPathogenSn());
							gr0021.setTestOrderNo(content3.getTestNo());
							gr0021.setGr11Relid(content3.getRefid());
							this.uw.save(gr0021);
							Xn011Dclymx content1 = new Xn011Dclymx();
							if (updAttrs.getInfectType() != null) {
								content1.setInfectTypeId(updAttrs.getInfectType());
								content1.setInfectTypeName(
										this.p.k("gr_type", content1.getInfectTypeId().toString(), (String) null));
							}

							content1.setTestOrderNo(content3.getTestNo());
							content1.setPathoCode(content3.getPathoId());
							content1.setChangeUserid(user.getRealname());
							this.cn.updateInfectTypeId(content1);
						}
					}

					List content4 = this.bH.findDrugAllergytList(bk001.getRelid());
					if (content4 != null) {
						Iterator gr0023 = content4.iterator();

						while (gr0023.hasNext()) {
							St011Syjgb bk4List1 = (St011Syjgb) gr0023.next();
							Gr012Ymsy content5 = new Gr012Ymsy();
							content5.setAntiDrugId(bk4List1.getAntiCode());
							content5.setAntiDrugName(bk4List1.getAntiName());
							content5.setResult(bk4List1.getYaominResult());
							content5.setSampleId(bk4List1.getSampleId());
							content5.setPathogenId(bk4List1.getPathoId());
							content5.setInfectPartId(bk4List1.getInfectDiagnId());
							content5.setSubmiAt(bk4List1.getSubmiAt());
							content5.setEnterAt(new Date());
							content5.setRelid(bk001Sbk.getRelid());
							content5.setGr12Relid(bk001.getRelid());
							content5.setGr12TestNo(bk4List1.getTestOrderNo());
							this.ux.save(content5);
						}
					}

					List bk4List2 = this.co.findMultiDrugResis(bk001.getRelid());
					if (bk4List2 != null) {
						Iterator content6 = bk4List2.iterator();

						while (content6.hasNext()) {
							Bk004Sjbb gr0024 = (Bk004Sjbb) content6.next();
							Xn020Gadc xn020 = new Xn020Gadc();
							xn020.setZyid(gr0024.getZyid());
							xn020.setItemCode(gr0024.getSampleId());
							xn020.setPathoCode(gr0024.getPathoId());
							xn020.setInfectTypeName(gr0024.getInfectTypeName());
							xn020.setInfectDept(gr0024.getInfectDeptname());
							xn020.setInfectDt(gr0024.getInfectDate());
							xn020.setTestOrderNo(gr0024.getTestNo());
							xn020.setSubmiAt(gr0024.getSubmiAt());
							xn020.setDt(gr0024.getTestDate());
							this.uB.save(xn020);
						}
					}

					Gr002YsgrMx gr0025 = this.bX.getByGr2Relid(bk001.getRelid());
					Gr002YsgrMx content7;
					if (gr0025 == null) {
						content7 = new Gr002YsgrMx();
						content7.setState(Integer.valueOf(2));
						this.a(content7, bk001Sbk, updAttrs);
					} else {
						this.bX.updateStateByGr2Relid(bk001.getRelid(), Integer.valueOf(2));
						if (gr0025.getReportType() != null) {
							gr0025.setState(Integer.valueOf(1));
							content7 = this.bX.findGr002(gr0025);
							if (content7 != null) {
								content7.setState(Integer.valueOf(2));
								content7.setConfDate(content7.getConfDate());
								content7.setLastoperDate(new Date());
								this.bX.updateSpecified(content7, gr2UpdAttrs);
							}
						}
					}

					if (bk001Sbk.getReportDrId() != null && !bk001Sbk.getReportDrId().equals(bk001.getAuthUserid())) {
						String content8 = "您上报的 " + updAttrs.getInfectDiagnName() + " 报卡被审核通过";
						this.cV.a(bk001Sbk.getZyid(), (String) null, user.getUsername(), user.getRealname(), content8,
								new String[]{bk001Sbk.getReportDrId()}, (String[]) null, al.jn.getValue(),
								bk001Sbk.getRelid());
					}
				} else {
					Gr002YsgrMx gr002 = this.bX.getByGr2Relid(bk001.getRelid());
					if (gr002 != null) {
						this.bX.updateStateByGr2Relid(bk001.getRelid(), Integer.valueOf(3));
						if (gr002.getReportType() != null) {
							gr002.setState(Integer.valueOf(1));
							Gr002YsgrMx content = this.bX.findGr002(gr002);
							if (content != null) {
								content.setState(Integer.valueOf(3));
								content.setConfDate(content.getConfDate());
								content.setLastoperDate(new Date());
								this.bX.updateSpecified(content, gr2UpdAttrs);
							}
						}
					}

					if (ab.isNotEmpty(updAttrs.getRefid())) {
						this.cn.clearInfectTypeByCardId(updAttrs.getRefid());
					}

					if (bk001Sbk.getReportDrId() != null
							&& !bk001Sbk.getReportDrId().equals(updAttrs.getAuthUserid())) {
						String content2 = "您上报的 " + updAttrs.getInfectDiagnName() + " 报卡被退卡";
						this.cV.a(bk001Sbk.getZyid(), (String) null, user.getUsername(), user.getRealname(), content2,
								new String[]{bk001Sbk.getReportDrId()}, (String[]) null, al.jn.getValue(),
								bk001Sbk.getRelid());
					}
				}
			}

			if (authStatus.intValue() == 1) {
				this.bX.updSameInfectCode(bk001Sbk.getRelid(), bk001Sbk.getZyid(), infectCodeList);
			}

			if (bk001Sbk.getCardSource().intValue() != 1) {
				this.uD.updBkType1(bk001Sbk.getRelid(), new Date(), user.getRealname());
			} else {
				this.uD.updBkType(bk001Sbk.getRelid(), new Date(), user.getRealname(), Integer.valueOf(2));
			}

			Bk001Sbk bk0011 = new Bk001Sbk();
			bk0011.setRelid(bk001Sbk.getRelid());
			String updStr1 = "bkType,lastoperDate,lastoperName";
			List updAttrs1;
			if (bk001Sbk.getIsOk().intValue() != 1 && bk001Sbk.getIsOk().intValue() != 2) {
				if (authStatus.intValue() == 1) {
					bk0011.setIsOk(authStatus);
					updStr1 = updStr1 + ",isOk";
					bk0011.setIsGr(Integer.valueOf(1));
					updStr1 = updStr1 + ",isGr";
				} else {
					updAttrs1 = this.uD.findStatusByRefid(bk001Sbk.getRelid());
					if (updAttrs1 != null && !updAttrs1.contains(Integer.valueOf(0))
							&& !updAttrs1.contains(Integer.valueOf(1))) {
						bk0011.setIsOk(authStatus);
						updStr1 = updStr1 + ",isOk";
					}
				}
			}

			if (bk001Sbk.getCardSource().intValue() != 1) {
				bk0011.setBkType(Integer.valueOf(0));
			} else {
				bk0011.setBkType(Integer.valueOf(2));
			}

			bk0011.setLastoperDate(new Date());
			bk0011.setLastoperName(user.getRealname());
			updAttrs1 = Arrays.asList(updStr1.split(","));
			this.bW.updateSpecified(bk0011, updAttrs1);
			return;
		}
	}

	public int findUnAuditCount(String refid) {
		return this.uD.findUnAuditCount(refid);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void bN(String relid) {
		this.delete(relid);
		Gr002YsgrMx gr002YsgrMx = this.bX.getByGr2Relid(relid);
		if (gr002YsgrMx != null) {
			if (gr002YsgrMx.getReportType() != null) {
				this.bX.delByGr2Relid(relid);
			} else {
				this.bX.updGr2RelidNull(relid);
			}
		}

		this.ut.delByRefid(relid);
		this.co.delByRefid(relid);
	}

	public void a(Bk002Grzd bk002Grzd, Bk001Sbk bk001Sbk, LoginUser user) {
		bk002Grzd.setRefid(bk001Sbk.getRelid());
		bk002Grzd.setSn(Integer.valueOf(0));
		bk002Grzd.setIsselect(Integer.valueOf(1));
		bk002Grzd.setDeleted(Integer.valueOf(0));
		bk002Grzd.setInfectCategory(Integer.valueOf(1));
		bk002Grzd.setAuthStatus(Integer.valueOf(0));
		bk002Grzd.setRelation(bb.H(bk002Grzd.getRelation()).getName());
		this.save(bk002Grzd);
	}

	public void a(Gr002YsgrMx gr002YsgrMx, Bk001Sbk bk001Sbk, Bk002Grzd bk002Grzd) {
		gr002YsgrMx.setZyid(bk001Sbk.getZyid());
		gr002YsgrMx.setReportType(Integer.valueOf(1));
		gr002YsgrMx.setInfectCode(bk002Grzd.getInfectDiagnId());
		gr002YsgrMx.setInfectName(bk002Grzd.getInfectDiagnName());
		gr002YsgrMx.setInfectTypeId(bk002Grzd.getInfectType());
		gr002YsgrMx.setRelid(bk001Sbk.getRelid());
		gr002YsgrMx.setGr2Relid(bk002Grzd.getRelid());
		gr002YsgrMx.setStartAt(bk002Grzd.getInfectDate());
		gr002YsgrMx.setInfectDeptId(bk002Grzd.getInfectDeptId());
		gr002YsgrMx.setConfDate(bk002Grzd.getConfirmDt());
		gr002YsgrMx.setOperator(bk002Grzd.getAuthUserid());
		gr002YsgrMx.setLastoperDate(new Date());
		this.bX.save(gr002YsgrMx);
	}

	public void b(Gr002YsgrMx gr002YsgrMx, Bk001Sbk bk001Sbk, Bk002Grzd bk002Grzd) {
		String attrStr = "gr2Relid,relid,infectTypeId,startAt,infectName,infectDeptId,confDate,operator";
		List updateAttrs = Arrays.asList(attrStr.split(","));
		gr002YsgrMx.setRelid(bk001Sbk.getRelid());
		gr002YsgrMx.setGr2Relid(bk002Grzd.getRelid());
		gr002YsgrMx.setInfectTypeId(bk002Grzd.getInfectType());
		gr002YsgrMx.setStartAt(bk002Grzd.getInfectDate());
		gr002YsgrMx.setInfectName(bk002Grzd.getInfectDiagnName());
		gr002YsgrMx.setInfectDeptId(bk002Grzd.getInfectDeptId());
		gr002YsgrMx.setConfDate(bk002Grzd.getConfirmDt());
		gr002YsgrMx.setOperator(bk002Grzd.getAuthUserid());
		this.bX.updateSpecified(gr002YsgrMx, updateAttrs);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Bk001Sbk bk001Sbk, Bk002Grzd bk002Grzd, LoginUser user, String chargeDrId, String jbzd,
			String jbzdCode, String reportDeptId, String reportDeptName) {
		String attrBk001 = "chargeDrId,jbzd,jbzdCode,reportDeptId,reportDeptName";
		List updateAttrs1 = Arrays.asList(attrBk001.split(","));
		this.bW.updateSpecified(bk001Sbk, updateAttrs1);
		String attrStr = "infectType,infectDate,infectDiagnId,infectDiagnName,infectDeptName,infectDeptId,confirmDt,relation,operId,opeRelid,opeName,memo,bkType,authUserid,authUsername,jbzg,jbzgDate";
		bk002Grzd.setRelation(bb.H(bk002Grzd.getRelation()).getName());
		bk002Grzd.setAuthUserid(user.getUsername());
		bk002Grzd.setAuthUsername(user.getRealname());
		List updateAttrs = Arrays.asList(attrStr.split(","));
		this.updateSpecified(bk002Grzd, updateAttrs);
		Gr002YsgrMx gr002YsgrMx = new Gr002YsgrMx();
		gr002YsgrMx.setGr2Relid(bk002Grzd.getRelid());
		gr002YsgrMx.setInfectTypeId(bk002Grzd.getInfectType());
		gr002YsgrMx.setStartAt(bk002Grzd.getInfectDate());
		gr002YsgrMx.setInfectName(bk002Grzd.getInfectDiagnName());
		gr002YsgrMx.setInfectDeptId(bk002Grzd.getInfectDeptId());
		gr002YsgrMx.setConfDate(bk002Grzd.getConfirmDt());
		gr002YsgrMx.setOperator(bk002Grzd.getAuthUserid());
		this.bX.updateByGr2Relid(gr002YsgrMx);
		bk002Grzd.setRefid(bk001Sbk.getRelid());
		this.ut.a(bk002Grzd, bk002Grzd.getFactorIds());
		this.co.b(bk002Grzd, bk001Sbk, bk002Grzd.getTestOrderNos(), user.getDepNo());
	}

	public List<Map<String, Object>> findqrxczxgxgrlcs(String startDate, String endDate, String ificu) {
		return this.uD.findqrxczxgxgrlcs(startDate, endDate, ificu);
	}

	public List<Bk002Grzd> getbyOperRelid(String operRelid) {
		return this.uD.getbyOperRelid(operRelid);
	}

	public Bk002Grzd getOneInfectByRefid(String refid) {
		return this.uD.getOneInfectByRefid(refid);
	}

	@SqlLog(p = "院感端首页--感染部位构成")
	public List<Map<String, Object>> findMainInfectionParts(Date startDate, Date endDate) {
		List list = this.uD.findMainInfectionParts(startDate, endDate);
		boolean others = false;
		Map otherMap = null;
		Map othersMap = null;
		int temp = 0;
		if (list.size() == 1) {
			list.clear();
		}

		Iterator arg8 = list.iterator();

		while (arg8.hasNext()) {
			Map map = (Map) arg8.next();
			if ("其他".equals(map.get("name"))) {
				otherMap = map;
			} else if (!"总计".equals(map.get("name"))) {
				temp += r.d(map.get("value"));
			} else {
				int others1 = r.d(map.get("value")) - temp;
				if (others1 != 0) {
					map.put("name", "其他");
					map.put("value", Integer.valueOf(others1));
				} else {
					othersMap = map;
				}
			}
		}

		if (otherMap != null) {
			list.remove(otherMap);
		}

		if (othersMap != null) {
			list.remove(othersMap);
		}

		return list;
	}

	@SqlLog(p = "院感端首页--院感发病率趋势")
	public List<Map<String, Object>> findMainIncidence(Date startDate, Date endDate) {
		return this.uD.findMainIncidence(startDate, endDate);
	}

	@SqlLog(p = "院感端首页--MRSA血流感染")
	public List<Map<String, Object>> findBloodInfections(Date startDate, Date endDate) {
		return this.uD.findBloodInfections(startDate, endDate);
	}

	public List<Bk002Grzd> queryGrPosition() {
		return this.uD.queryGrPosition();
	}

	public List<Bk002Grzd> getLastReport(String zyid) {
		return this.uD.getLastReport(zyid);
	}

	public void updateOutcome(Bk002Grzd bk002Grzd) {
		this.uD.updateOutcome(bk002Grzd);
	}

	public void returnCard(Bk002Grzd bk002Grzd) {
		this.uD.returnCard(bk002Grzd);
	}
}