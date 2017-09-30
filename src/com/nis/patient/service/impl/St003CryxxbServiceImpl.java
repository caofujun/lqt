package com.nis.patient.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgSys005LisruleMaster;
import com.nis.cdc.entity.CtgSys006LisruleDetail;
import com.nis.cdc.service.CtgSys005LisruleMasterService;
import com.nis.cdc.service.CtgSys006LisruleDetailService;
import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.param.service.SysParamService;
import com.nis.patient.dao.St003CryxxbDao;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St003CryxxbServiceImpl implements St003CryxxbService {
	@Autowired
	private St003CryxxbDao tr;
	@Autowired
	private CtgSys006LisruleDetailService wy;
	@Autowired
	private CtgSys005LisruleMasterService bJ;
	@Autowired
	private SysParamService j;

	public void save(St003Cryxxb st003Cryxxb) {
		this.tr.save(st003Cryxxb);
	}

	public void delete(String zyid) {
		this.tr.delete(zyid);
	}

	public void update(St003Cryxxb st003Cryxxb) {
		this.tr.update(st003Cryxxb);
	}

	public St003Cryxxb get(String zyid) {
		return this.tr.get(zyid);
	}

	public MyPage<St003Cryxxb> l(St003Cryxxb st003Cryxxb) {
		int total = this.tr.findSt003CryxxbCount(st003Cryxxb);
		List data = null;
		if (total > 0) {
			data = this.tr.findSt003Cryxxb(st003Cryxxb);
		}

		return new MyPage(st003Cryxxb.getPage().intValue(), st003Cryxxb.getSize().intValue(), total, data);
	}

	public List<St003Cryxxb> getAll() {
		return this.tr.getAll();
	}

	public St003Cryxxb getPatientRecords(String zyid) {
		return this.tr.getPatientRecords(zyid);
	}

	public List<St003Cryxxb> findCryxxByPatientId(String patientId) {
		return this.tr.findCryxxByPatientId(patientId);
	}

	public List<St003Cryxxb> findCryxxByFX() {
		return this.tr.findCryxxByFX();
	}

	public int findCountbyDate(St003Cryxxb st003Cryxxb) {
		return this.tr.findCountbyDate(st003Cryxxb);
	}

	public St003Cryxxb getSt003Cryxxb(String zyid) {
		return this.tr.getSt003Cryxxb(zyid);
	}

	public MyPage<St003Cryxxb> a(String unitId, String typeid, String deptid, String neonatebw, Date startdate,
			Date enddate, Integer page, Integer size) {
		List cryxxbList = null;
		MyPage myPage = null;
		Integer orclBegNum = null;
		Integer orclEndNum = null;
		if (page != null && size != null) {
			orclBegNum = Integer.valueOf((page.intValue() - 1) * size.intValue() + 1);
		}

		if (page != null && size != null) {
			orclEndNum = Integer.valueOf(page.intValue() * size.intValue());
		}

		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		int total;
		if ("07".equals(typeid)) {
			total = this.tr.findByDateAndDeptIdCount(unitId, deptid, startdate, enddate, tws);
			if (total > 0) {
				cryxxbList = this.tr.findByDateAndDeptId(unitId, deptid, startdate, enddate, tws, orclBegNum,
						orclEndNum);
			}

			myPage = new MyPage(page.intValue(), size.intValue(), total, cryxxbList);
		} else {
			total = this.tr.findByTypeIdAndDeptIdCount(unitId, typeid, deptid, neonatebw, startdate, enddate);
			if (total > 0) {
				cryxxbList = this.tr.findByTypeIdAndDeptId(unitId, typeid, deptid, neonatebw, startdate, enddate,
						orclBegNum, orclEndNum);
			}

			myPage = new MyPage(page.intValue(), size.intValue(), total, cryxxbList);
		}

		return myPage;
	}

	public MyPage<St003Cryxxb> b(String unitId, String typeid, String deptid, String neonatebw, Date startdate,
			Date enddate, Integer page, Integer size) {
		List cryxxbList = null;
		MyPage myPage = null;
		Integer orclBegNum = null;
		Integer orclEndNum = null;
		if (page != null && size != null) {
			orclBegNum = Integer.valueOf((page.intValue() - 1) * size.intValue() + 1);
		}

		if (page != null && size != null) {
			orclEndNum = Integer.valueOf(page.intValue() * size.intValue());
		}

		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		int total;
		if ("07".equals(typeid)) {
			total = this.tr.findBedByDateAndDeptIdCount(unitId, deptid, startdate, enddate, tws);
			if (total > 0) {
				cryxxbList = this.tr.findBedByDateAndDeptId(unitId, deptid, startdate, enddate, tws, orclBegNum,
						orclEndNum);
			}

			myPage = new MyPage(page.intValue(), size.intValue(), total, cryxxbList);
		} else {
			total = this.tr.findBedByTypeIdAndDeptIdCount(unitId, typeid, deptid, neonatebw, startdate, enddate);
			if (total > 0) {
				cryxxbList = this.tr.findBedByTypeIdAndDeptId(unitId, typeid, deptid, neonatebw, startdate, enddate,
						orclBegNum, orclEndNum);
			}

			myPage = new MyPage(page.intValue(), size.intValue(), total, cryxxbList);
		}

		return myPage;
	}

	public List<St003Cryxxb> b(String unitId, String typeid, String deptid, String neonatebw, Date startdate,
			Date enddate) {
		List cryxxbList = null;
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		int total;
		if ("07".equals(typeid)) {
			total = this.tr.findByDateAndDeptIdCount(unitId, deptid, startdate, enddate, tws);
			if (total > 0) {
				cryxxbList = this.tr.findByDateAndDeptId(unitId, deptid, startdate, enddate, tws, Integer.valueOf(1),
						Integer.valueOf(total));
			}
		} else {
			total = this.tr.findByTypeIdAndDeptIdCount(unitId, typeid, deptid, neonatebw, startdate, enddate);
			if (total > 0) {
				cryxxbList = this.tr.findByTypeIdAndDeptId(unitId, typeid, deptid, neonatebw, startdate, enddate,
						Integer.valueOf(1), Integer.valueOf(total));
			}
		}

		return cryxxbList;
	}

	public List<St003Cryxxb> m(St003Cryxxb st003Cryxxb) {
		return this.tr.findSt003Cryxxb(st003Cryxxb);
	}

	public MyPage<St003Cryxxb> n(St003Cryxxb st003Cryxxb) {
		int total = this.tr.findPatientListCount(st003Cryxxb);
		List data = null;
		if (total > 0) {
			data = this.tr.findPatientList(st003Cryxxb);
		}

		return new MyPage(st003Cryxxb.getPage().intValue(), st003Cryxxb.getSize().intValue(), total, data);
	}

	public List<St003Cryxxb> s(String deptId, String searchString, String showCrb) {
		String qzValue = this.j.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		return this.tr.findInHospPatientListByDeptId(deptId, searchString, showCrb, qzValue);
	}

	public MyPage<St003Cryxxb> o(St003Cryxxb st003Cryxxb) {
		int total = this.tr.findNewbornListCount(st003Cryxxb);
		List data = null;
		if (total > 0) {
			data = this.tr.findNewbornList(st003Cryxxb);
		}

		return new MyPage(st003Cryxxb.getPage().intValue(), st003Cryxxb.getSize().intValue(), total, data);
	}

	public MyPage<St003Cryxxb> a(St020ClinicPatients st020ClinicPatients) {
		List data = null;
		String jyzb = st020ClinicPatients.getJYZB();
		if (!ab.isEmpty(jyzb)) {
			String total = " select distinct zyid jyzyid from st009_sjbb bb where  bb.test_order_no in (  ";
			String[] ordernos = jyzb.split(",");

			for (int j = 0; j < ordernos.length; ++j) {
				CtgSys005LisruleMaster rulemaster = this.bJ.get(new Long(ordernos[j]));
				List no = this.wy.getByOrderNo(ordernos[j]);
				boolean isAnd = false;
				String tablename = rulemaster.getTablename();
				if (j != 0) {
					total = total + " union ";
				}

				total = total + " select test_order_no from " + tablename + " where ";
				if (no != null && !no.isEmpty()) {
					isAnd = ((CtgSys006LisruleDetail) no.get(0)).getWeightvalue().longValue() < rulemaster.getYjWeight()
							.longValue();

					for (int i = 0; i < no.size(); ++i) {
						CtgSys006LisruleDetail cld = (CtgSys006LisruleDetail) no.get(i);
						String singleCdt = "";
						if (isAnd) {
							singleCdt = "  ";
							if (ab.aY(cld.getResultnamevalue())) {
								singleCdt = singleCdt + " ( " + cld.getResultnamefield() + " " + cld.getResultnameoper()
										+ " " + cld.getResultnamevalue() + " ";
							} else if ("like".equals(cld.getResultnameoper().replaceAll(" ", ""))) {
								singleCdt = singleCdt + " ( " + cld.getResultnamefield() + " " + cld.getResultnameoper()
										+ " \'%" + cld.getResultnamevalue() + "%\' ";
							} else {
								singleCdt = singleCdt + " ( " + cld.getResultnamefield() + " " + cld.getResultnameoper()
										+ " \'" + cld.getResultnamevalue() + "\' ";
							}

							if (ab.aY(cld.getMatchvalue())) {
								singleCdt = singleCdt + " and " + cld.getMatchvaluefield() + " "
										+ cld.getMatchvalueoper() + " " + cld.getMatchvalue() + " ) ";
							} else if ("like".equals(cld.getResultnameoper().replaceAll(" ", ""))) {
								singleCdt = singleCdt + " and " + cld.getMatchvaluefield() + " "
										+ cld.getMatchvalueoper() + " \'%" + cld.getMatchvalue() + "%\' ) ";
							} else {
								singleCdt = singleCdt + " and " + cld.getMatchvaluefield() + " "
										+ cld.getMatchvalueoper() + " \'" + cld.getMatchvalue() + "\' ) ";
							}

							if (i == 0) {
								total = total + "  " + singleCdt;
							} else {
								total = total + " and " + singleCdt;
							}
						} else {
							singleCdt = "  ";
							if (ab.aY(cld.getResultnamevalue())) {
								singleCdt = singleCdt + " ( " + cld.getResultnamefield() + " " + cld.getResultnameoper()
										+ " " + cld.getResultnamevalue() + " ";
							} else if ("like".equals(cld.getResultnameoper().replaceAll(" ", ""))) {
								singleCdt = singleCdt + " ( " + cld.getResultnamefield() + " " + cld.getResultnameoper()
										+ " \'%" + cld.getResultnamevalue() + "%\' ";
							} else {
								singleCdt = singleCdt + " ( " + cld.getResultnamefield() + " " + cld.getResultnameoper()
										+ " \'" + cld.getResultnamevalue() + "\' ";
							}

							if (ab.aY(cld.getMatchvalue())) {
								singleCdt = singleCdt + " and " + cld.getMatchvaluefield() + " "
										+ cld.getMatchvalueoper() + " " + cld.getMatchvalue() + " ) ";
							} else if ("like".equals(cld.getResultnameoper().replaceAll(" ", ""))) {
								singleCdt = singleCdt + " and " + cld.getMatchvaluefield() + " "
										+ cld.getMatchvalueoper() + " \'%" + cld.getMatchvalue() + "%\' ) ";
							} else {
								singleCdt = singleCdt + " and " + cld.getMatchvaluefield() + " "
										+ cld.getMatchvalueoper() + " \'" + cld.getMatchvalue() + "\' ) ";
							}

							if (i == 0) {
								total = total + "  " + singleCdt;
							} else {
								total = total + " or " + singleCdt;
							}
						}
					}
				}
			}

			total = total + " ) ";
			st020ClinicPatients.setJYZBConditions(total);
		}

		int arg13 = this.tr.getSuspectedCaseZyCount(st020ClinicPatients);
		if (arg13 > 0) {
			data = this.tr.getSuspectedCaseZy(st020ClinicPatients);
		}

		return new MyPage(st020ClinicPatients.getPage().intValue(), st020ClinicPatients.getSize().intValue(), arg13,
				data);
	}

	public List<St003Cryxxb> h(String deptId, String searchString, String startDate, String endDate, String showCrb) {
		String qzValue = this.j.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		return this.tr.findOutHospPatientListByDeptId(deptId, searchString, startDate, endDate, showCrb, qzValue);
	}

	public List<St003Cryxxb> findYjCalInfo(int rownum) {
		return this.tr.findYjCalInfo(rownum);
	}

	public MyPage<St003Cryxxb> b(St020ClinicPatients st020ClinicPatients) {
		Object data = new ArrayList();
		int total = this.tr.getSCSZyCount(st020ClinicPatients);
		if (total > 0) {
			data = this.tr.getSCSZy(st020ClinicPatients);
		}

		return new MyPage(st020ClinicPatients.getPage().intValue(), st020ClinicPatients.getSize().intValue(), total,
				(List) data);
	}

	public List<St003Cryxxb> findByPatientIdAndVisitId(String patientId, Integer visitId) {
		return this.tr.findByPatientIdAndVisitId(patientId, visitId);
	}

	public Date cr(String deptId) {
		return this.tr.getMonitorPatientCountByInAt(deptId);
	}

	public Date cs(String deptId) {
		return this.tr.getMonitorPatientCountByOutAt(deptId);
	}

	public List<DataWarning> findPatentCryxxbWarning(Date queryStartDate, Date queryEndDate) {
		return this.tr.findPatentCryxxbWarning(queryStartDate, queryEndDate);
	}

	public List<St003Cryxxb> a(String deptId, String searchString, String showCrb, String patientRange,
			AcAccount account, String ygyj, String ygqr, String dnlx, String ydnlx, String dbhz, String key) {
		String qzValue = this.j.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		return this.tr.findInHospPatientListByDeptIdNew(deptId, searchString, showCrb, patientRange, account.getDocNo(),
				ygyj, ygqr, dnlx, ydnlx, dbhz, key, qzValue);
	}

	public List<St003Cryxxb> a(String deptId, String searchString, String startDate, String endDate, String showCrb,
			String patientRange, AcAccount account, String ygyj, String ygqr, String dnlx, String ydnlx, String key) {
		String qzValue = this.j.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		return this.tr.findOutHospPatientListByDeptIdNew(deptId, searchString, startDate, endDate, showCrb,
				patientRange, account.getDocNo(), ygyj, ygqr, dnlx, ydnlx, key, qzValue);
	}

	public List<St003Cryxxb> ewListForInterface(St003Cryxxb st003Cryxxb) {
		String qzValue = this.j.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		st003Cryxxb.setQzValue(qzValue);
		return this.tr.ewListForInterface(st003Cryxxb);
	}
}