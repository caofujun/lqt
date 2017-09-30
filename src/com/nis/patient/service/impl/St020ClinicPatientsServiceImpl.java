package com.nis.patient.service.impl;

import com.nis.cdc.entity.CtgSys005LisruleMaster;
import com.nis.cdc.entity.CtgSys006LisruleDetail;
import com.nis.cdc.service.CtgSys005LisruleMasterService;
import com.nis.cdc.service.CtgSys006LisruleDetailService;
import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.patient.dao.St003CryxxbDao;
import com.nis.patient.dao.St020ClinicPatientsDao;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St020ClinicPatientsService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class St020ClinicPatientsServiceImpl implements St020ClinicPatientsService {
	private static final Logger c = Logger.getLogger(St020ClinicPatientsServiceImpl.class);
	@Autowired
	private St020ClinicPatientsDao tp;
	@Autowired
	private St003CryxxbDao tr;
	@Autowired
	private CtgSys006LisruleDetailService bK;
	@Autowired
	private CtgSys005LisruleMasterService bJ;
	@Autowired
	private ApplicationContext dO;

	public void save(St020ClinicPatients st020ClinicPatients) {
		this.tp.save(st020ClinicPatients);
	}

	public void delete(String id) {
		this.tp.delete(id);
	}

	public void update(St020ClinicPatients st020ClinicPatients) {
		this.tp.update(st020ClinicPatients);
	}

	public St020ClinicPatients get(String id) {
		return this.tp.get(id);
	}

	public MyPage<St020ClinicPatients> c(St020ClinicPatients st020ClinicPatients) {
		int total = this.tp.findSt020ClinicPatientsCount(st020ClinicPatients);
		List data = null;
		if (total > 0) {
			data = this.tp.findSt020ClinicPatients(st020ClinicPatients);
		}

		return new MyPage(st020ClinicPatients.getPage().intValue(), st020ClinicPatients.getSize().intValue(), total,
				data);
	}

	public List<St020ClinicPatients> getAll() {
		return this.tp.getAll();
	}

	public MyPage<St020ClinicPatients> d(St020ClinicPatients st020ClinicPatients) {
		int total = this.tp.findSt020ClinicPatientsForMainCount(st020ClinicPatients);
		List data = null;
		if (total > 0) {
			data = this.tp.findSt020ClinicPatientsForMain(st020ClinicPatients);
		}

		return new MyPage(st020ClinicPatients.getPage().intValue(), st020ClinicPatients.getSize().intValue(), total,
				data);
	}

	public MyPage<St020ClinicPatients> e(St020ClinicPatients st020ClinicPatients) {
		List data = null;
		String jyzb = st020ClinicPatients.getJYZB();
		if (!ab.isEmpty(jyzb)) {
			String total = " select distinct mzid jymzid from st009_sjbb bb where  bb.test_order_no in (  ";
			String[] ordernos = jyzb.split(",");

			for (int j = 0; j < ordernos.length; ++j) {
				CtgSys005LisruleMaster rulemaster = this.bJ.get(new Long(ordernos[j]));
				List no = this.bK.getByOrderNo(ordernos[j]);
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

		int arg13 = this.tp.getSuspectedCaseMzCount(st020ClinicPatients);
		if (arg13 > 0) {
			data = this.tp.getSuspectedCaseMz(st020ClinicPatients);
		}

		return new MyPage(st020ClinicPatients.getPage().intValue(), st020ClinicPatients.getSize().intValue(), arg13,
				data);
	}

	public St020ClinicPatients getByMzid(String mzid, String uid) {
		return this.tp.getByMzid(mzid, uid);
	}

	public MyPage<St020ClinicPatients> f(St020ClinicPatients st020ClinicPatients) {
		Object data = new ArrayList();
		int total = this.tp.getSCSMzCount(st020ClinicPatients);
		if (total > 0) {
			data = this.tp.getSCSMz(st020ClinicPatients);
		}

		return new MyPage(st020ClinicPatients.getPage().intValue(), st020ClinicPatients.getSize().intValue(), total,
				(List) data);
	}

	public List<St020ClinicPatients> getByPatientId(String patientId) {
		return this.tp.getByPatientId(patientId);
	}

	public List<St020ClinicPatients> findByPatientIdAndVisitId(String patientId, Integer visitId) {
		return this.tp.findByPatientIdAndVisitId(patientId, visitId);
	}

	public List<St020ClinicPatients> findMzZdList(int rownum) {
		return this.tp.findMzZdList(rownum);
	}

	public MyPage<St020ClinicPatients> g(St020ClinicPatients st020ClinicPatients) {
		Object data = new ArrayList();
		int total = this.tp.getNewSCSCount(st020ClinicPatients);
		if (total > 0) {
			data = this.tp.getNewSCS(st020ClinicPatients);
		}

		return new MyPage(st020ClinicPatients.getPage().intValue(), st020ClinicPatients.getSize().intValue(), total,
				(List) data);
	}

	public List<DataWarning> findPatentClinicWarning(Date queryStartDate, Date queryEndDate) {
		return this.tp.findPatentClinicWarning(queryStartDate, queryEndDate);
	}
}