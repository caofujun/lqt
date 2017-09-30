package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.y;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.p;
import com.nis.comm.utils.z;
import com.nis.patient.service.St002ZdxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.prevalence.dao.Xl001BrxxDao;
import com.nis.prevalence.entity.Xl001Brxx;
import com.nis.prevalence.entity.Xl002Grxx;
import com.nis.prevalence.entity.Xl003Byt;
import com.nis.prevalence.entity.Xl004Kjyw;
import com.nis.prevalence.service.Xl001BrxxService;
import com.nis.prevalence.service.Xl002GrxxService;
import com.nis.prevalence.service.Xl003BytService;
import com.nis.prevalence.service.Xl004KjywService;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Xl001BrxxServiceImpl implements Xl001BrxxService {
	@Autowired
	private Xl001BrxxDao wU;
	@Autowired
	private Xl002GrxxService wI;
	@Autowired
	private Xl003BytService wJ;
	@Autowired
	private Xl004KjywService wK;
	@Autowired
	private St002ZdxxbService bv;
	@Autowired
	private St005SsxxbService bO;

	public void save(Xl001Brxx xl001Brxx) {
		xl001Brxx.setBrid(z.a(bg.ng));
		if (xl001Brxx.getVisitId() == null) {
			xl001Brxx.setVisitId(Integer.valueOf(1));
		}

		this.wU.save(xl001Brxx);
	}

	public void delete(String brid) {
		this.wU.delete(brid);
	}

	public void update(Xl001Brxx xl001Brxx) {
		this.wU.update(xl001Brxx);
	}

	public void updateSpecified(Xl001Brxx xl001Brxx, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.wU.updateSpecified(xl001Brxx, updateAttrs);
		}

	}

	public Xl001Brxx get(String brid) {
		return this.wU.get(brid);
	}

	public MyPage<Xl001Brxx> a(Xl001Brxx xl001Brxx) {
		int total = this.wU.findXl001BrxxCount(xl001Brxx);
		List data = null;
		if (total > 0) {
			data = this.wU.findXl001Brxx(xl001Brxx);
		}

		return new MyPage(xl001Brxx.getPage().intValue(), xl001Brxx.getSize().intValue(), total, data);
	}

	public List<Xl001Brxx> getAll() {
		return this.wU.getAll();
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Xl001Brxx xl001Brxx, List<Xl002Grxx> xl2ListU, List<Xl002Grxx> xl2ListC, List<String> gridNotIn,
			List<String> bytidNotIn, List<String> yjywidNotIn) {
		String attrStr = "patientId,deptName,deptId,bedNo,zyid,patientName,sex,age,ageUnit,infectDeptName,diagnose,diagnoseId,isOper,isGrade1,isGrade2,isGrade3,isGrade4,isInfect,isCa,isHa,pop,sykjyw,kjywmc1,kjywmc2,kjywmc3,kjywmc4,yymd,lhyy,zlyyspy,spyshkjyw,votename,voteid,votedate,lastAt,isEdit,state,fszl,hxzl,djmcg,mndcg,syhxj,qgcg,qgqk,sqyykjyw,sqjy,syrsnj,qtqxxcc,wj,cj,xzj,hj,sezcj,ywmndcg,ywdjmcg,ywqgqqg,ywsyychxj,xytx,operName1,operName2,gradeType2";
		List updateAttrs = Arrays.asList(attrStr.split(","));
		xl001Brxx.setIsEdit(Integer.valueOf(1));
		xl001Brxx.setState(Integer.valueOf(1));
		xl001Brxx.setLastAt(new Date());
		this.updateSpecified(xl001Brxx, updateAttrs);
		this.wI.delXl002Grxx(gridNotIn, xl001Brxx.getBrid());
		this.wJ.delXl003Byt(bytidNotIn, xl001Brxx.getBrid());
		this.wK.delXl004Kjyw(yjywidNotIn, xl001Brxx.getBrid());
		if (xl2ListU != null && xl2ListU.size() > 0) {
			this.a(xl2ListU, y.hu.getValue(), xl001Brxx.getBrid());
		}

		if (xl2ListC != null && xl2ListC.size() > 0) {
			this.a(xl2ListC, y.hv.getValue(), xl001Brxx.getBrid());
		}

	}

	private void a(List<Xl002Grxx> xl2List, Integer infectType, String brid) {
		Iterator arg4 = xl2List.iterator();

		label62 : while (true) {
			Xl002Grxx xl002;
			List xl3List;
			do {
				do {
					if (!arg4.hasNext()) {
						return;
					}

					xl002 = (Xl002Grxx) arg4.next();
					xl002.setInfectType(infectType);
					xl002.setBrid(brid);
					xl002.setLastAt(new Date());
					if (ab.isEmpty(xl002.getGrid())) {
						this.wI.save(xl002);
					} else {
						this.wI.update(xl002);
					}

					xl3List = xl002.getXl003List();
				} while (xl3List == null);
			} while (xl3List.size() <= 0);

			Iterator arg7 = xl3List.iterator();

			while (true) {
				Xl003Byt xl003;
				List xl4List;
				do {
					do {
						do {
							if (!arg7.hasNext()) {
								continue label62;
							}

							xl003 = (Xl003Byt) arg7.next();
						} while (!StringUtils.isNotBlank(xl003.getInfectPathoId()));

						xl003.setGrid(xl002.getGrid());
						xl003.setBrid(brid);
						xl003.setLastAt(new Date());
						if (ab.isEmpty(xl003.getBytid())) {
							this.wJ.save(xl003);
						} else {
							this.wJ.update(xl003);
						}

						xl4List = xl003.getXl004List();
					} while (xl4List == null);
				} while (xl4List.size() <= 0);

				Iterator arg10 = xl4List.iterator();

				while (arg10.hasNext()) {
					Xl004Kjyw xl004 = (Xl004Kjyw) arg10.next();
					xl004.setBrid(brid);
					xl004.setGrid(xl002.getGrid());
					xl004.setBytid(xl003.getBytid());
					xl004.setLastAt(new Date());
					if (ab.isEmpty(xl004.getYjywid())) {
						this.wK.save(xl004);
					} else {
						this.wK.update(xl004);
					}
				}
			}
		}
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Xl001Brxx xl001Brxx, List<Xl002Grxx> xl2ListU, List<Xl002Grxx> xl2ListC) {
		Calendar cal = Calendar.getInstance();
		xl001Brxx.setIsEdit(Integer.valueOf(1));
		xl001Brxx.setMonth(Integer.valueOf(cal.get(2) + 1));
		xl001Brxx.setYear(Integer.valueOf(cal.get(1)));
		xl001Brxx.setState(Integer.valueOf(1));
		xl001Brxx.setLastAt(new Date());
		this.save(xl001Brxx);
		if (xl2ListU != null && xl2ListU.size() > 0) {
			this.a(xl2ListU, y.hu.getValue(), xl001Brxx.getBrid());
		}

		if (xl2ListC != null && xl2ListC.size() > 0) {
			this.a(xl2ListC, y.hv.getValue(), xl001Brxx.getBrid());
		}

	}

	public Map<String, Object> cw(String zyid) {
		HashMap map = new HashMap();
		String diagnosisName = this.bv.findDiagnosisName(zyid);
		map.put("diagnosisName", diagnosisName);
		Integer gradeType = this.bO.findGradeType(zyid);
		map.put("gradeType", gradeType);
		return map;
	}

	public MyPage<Xl001Brxx> b(Xl001Brxx xl001Brxx) {
		int total = this.wU.pageFindListCount(xl001Brxx);
		List data = null;
		if (total > 0) {
			data = this.wU.pageFindList(xl001Brxx);
		}

		return new MyPage(xl001Brxx.getPage().intValue(), xl001Brxx.getSize().intValue(), total, data);
	}

	public Xl001Brxx c(Xl001Brxx xl001Brxx) {
		List list = this.wU.pageFindList(xl001Brxx);
		return list.size() > 0 ? (Xl001Brxx) list.get(0) : null;
	}

	@Transactional(rollbackFor = {Exception.class})
	public void N(List<String> brids) {
		Iterator arg2 = brids.iterator();

		while (arg2.hasNext()) {
			String brid = (String) arg2.next();
			this.delete(brid);
			this.wI.deleteByBrid(brid);
			this.wJ.deleteByBrid(brid);
			this.wK.deleteByBrid(brid);
		}

	}

	public String A(Date surveyDate) {
		HashMap paramMap = new HashMap();
		paramMap.put("startDate", surveyDate);
		paramMap.put("endDate", surveyDate);
		paramMap.put("typeId", "");
		this.wU.callPNis6TaskXhl(paramMap);
		return p.b(paramMap, "status");
	}

	public MyPage<Xl001Brxx> d(Xl001Brxx xl001Brxx) {
		int total = this.wU.findWaitRegisterCount(xl001Brxx);
		List data = null;
		if (total > 0) {
			data = this.wU.findWaitRegister(xl001Brxx);
		}

		return new MyPage(xl001Brxx.getPage().intValue(), xl001Brxx.getSize().intValue(), total, data);
	}

	public List<Xl001Brxx> findVoteDateList() {
		return this.wU.findVoteDateList();
	}
}