package com.nis.intervene.service.impl;

import com.nis.comm.constants.b;
import com.nis.comm.enums.bn;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.intervene.entity.FxPatientZb;
import com.nis.intervene.entity.FxZhibiao;
import com.nis.intervene.service.FxPatientZbService;
import com.nis.intervene.service.FxZhibiaoService;
import com.nis.intervene.service.JkDicAllService;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.patient.entity.St001Jbxxb;
import com.nis.patient.entity.St002Zdxxb;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.entity.St005Ssxxb;
import com.nis.patient.entity.St011Syjgb;
import com.nis.patient.service.St001JbxxbService;
import com.nis.patient.service.St002ZdxxbService;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.patient.service.St011SyjgbService;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WxYsUtil {
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private JkDicAllService sw;
	@Autowired
	private FxZhibiaoService st;
	@Autowired
	private Gr018YsgrysService aR;
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private St001JbxxbService dg;
	@Autowired
	private FxPatientZbService ss;
	@Autowired
	private St002ZdxxbService bv;

	public void G() {
		FxZhibiao zb = this.st.getByzbClass("zhenduan");
		if (zb != null && ab.isNotEmpty(zb.getZbValue())) {
			List zdxxbList = this.bv.findListByZyidAndName(zb.getZbValue().split(","));
			Iterator arg3 = zdxxbList.iterator();

			while (arg3.hasNext()) {
				St002Zdxxb zbxxb = (St002Zdxxb) arg3.next();
				FxPatientZb fxPatientZb = new FxPatientZb();
				fxPatientZb.setPatientId(zbxxb.getPatientId());
				fxPatientZb.setZyId(zbxxb.getZyid());
				fxPatientZb.setZbId(zb.getZbId());
				fxPatientZb.setStartDate(zbxxb.getDiagnosisDate());
				fxPatientZb.setZbName(zbxxb.getDiagnosisName());
				fxPatientZb.setUpdateTime(new Date());
				fxPatientZb.setCaseId(zbxxb.getId());
				fxPatientZb.setPzStatus(zb.getIsGy());
				this.ss.save(fxPatientZb);
			}
		}

	}

	public void H() {
		FxZhibiao zb = this.st.getByzbClass("yizhu");
		List yzxxbList = this.bu.findListByzyidName(zb.getZbValue().split(","));
		Iterator arg3 = yzxxbList.iterator();

		while (arg3.hasNext()) {
			St004Yzxxb Yzxxb = (St004Yzxxb) arg3.next();
			FxPatientZb fxPatientZb = new FxPatientZb();
			fxPatientZb.setPatientId(Yzxxb.getPatientId());
			fxPatientZb.setZyId(Yzxxb.getZyid());
			fxPatientZb.setZbId(zb.getZbId());
			fxPatientZb.setStartDate(Yzxxb.getOrderAt());
			fxPatientZb.setEndDate(Yzxxb.getStopAt());
			fxPatientZb.setZbName(Yzxxb.getOrderName());
			fxPatientZb.setUpdateTime(new Date());
			fxPatientZb.setCaseId(Yzxxb.getId());
			fxPatientZb.setPzStatus(zb.getIsGy());
			this.ss.save(fxPatientZb);
		}

	}

	public void I() {
		FxZhibiao zb = this.st.getByzbClass("operate");
		List ssxxbList = this.bO.findListByName(zb.getZbValue().split(","));
		Iterator arg3 = ssxxbList.iterator();

		while (arg3.hasNext()) {
			St005Ssxxb ssxxb = (St005Ssxxb) arg3.next();
			FxPatientZb fxPatientZb = new FxPatientZb();
			fxPatientZb.setPatientId(ssxxb.getPatientId());
			fxPatientZb.setZyId(ssxxb.getZyid());
			fxPatientZb.setZbId(zb.getZbId());
			fxPatientZb.setStartDate(ssxxb.getOperAt());
			fxPatientZb.setZbName(ssxxb.getOperName());
			fxPatientZb.setUpdateTime(new Date());
			fxPatientZb.setCaseId(ssxxb.getId());
			fxPatientZb.setPzStatus(zb.getIsGy());
			this.ss.save(fxPatientZb);
		}

	}

	public Integer a(St003Cryxxb st003Cryxxb) {
		int result = 0;
		St001Jbxxb st001Jbxxb = this.dg.get(st003Cryxxb.getPatientId());
		Date birth = st001Jbxxb.getBirthDate();
		int day = f.a(new Date(), birth);
		if (day < 30) {
			FxZhibiao zb = this.st.getByzbClass("xinshenger");
			result = zb.getZbScore().intValue();
			FxPatientZb fxPatientZb = new FxPatientZb();
			fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
			fxPatientZb.setZyId(st003Cryxxb.getZyid());
			fxPatientZb.setZbId(zb.getZbId());
			fxPatientZb.setZbScore(Integer.valueOf(result));
			fxPatientZb.setStartDate(st003Cryxxb.getInHospAt());
			fxPatientZb.setZbName(zb.getZbName());
			fxPatientZb.setUpdateTime(new Date());
			fxPatientZb.setPzStatus(zb.getIsGy());
			this.ss.save(fxPatientZb);
		}

		return Integer.valueOf(result);
	}

	public Integer b(St003Cryxxb st003Cryxxb) {
		int result = 0;
		St001Jbxxb st001Jbxxb = this.dg.get(st003Cryxxb.getPatientId());
		int age = f.d(st001Jbxxb.getBirthDate(), st001Jbxxb.getIdCardId());
		if (age > 65) {
			FxZhibiao zb = this.st.getByzbClass("gaoling");
			result = zb.getZbScore().intValue();
			FxPatientZb fxPatientZb = new FxPatientZb();
			fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
			fxPatientZb.setZyId(st003Cryxxb.getZyid());
			fxPatientZb.setZbId(zb.getZbId());
			fxPatientZb.setZbScore(Integer.valueOf(result));
			fxPatientZb.setStartDate(st003Cryxxb.getInHospAt());
			fxPatientZb.setZbName(zb.getZbName());
			fxPatientZb.setUpdateTime(new Date());
			fxPatientZb.setPzStatus(zb.getIsGy());
			this.ss.save(fxPatientZb);
		}

		return Integer.valueOf(result);
	}

	public Integer c(St003Cryxxb st003Cryxxb) {
		int result = 0;
		List sxxbList = this.bO.findListByZyid(st003Cryxxb.getZyid());
		if (sxxbList.size() > 0) {
			FxZhibiao zb = this.st.getByzbClass("operate");
			result = zb.getZbScore().intValue();
			Iterator arg5 = sxxbList.iterator();

			while (arg5.hasNext()) {
				St005Ssxxb ssxxb = (St005Ssxxb) arg5.next();
				if (ssxxb != null) {
					FxPatientZb fxPatientZb = new FxPatientZb();
					fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
					fxPatientZb.setZyId(st003Cryxxb.getZyid());
					fxPatientZb.setZbId(zb.getZbId());
					fxPatientZb.setZbScore(Integer.valueOf(result));
					fxPatientZb.setStartDate(ssxxb.getOperAt());
					fxPatientZb.setZbName(ssxxb.getOperName());
					fxPatientZb.setUpdateTime(new Date());
					fxPatientZb.setCaseId(ssxxb.getId());
					fxPatientZb.setPzStatus(zb.getIsGy());
					this.ss.save(fxPatientZb);
				}
			}
		}

		return Integer.valueOf(result);
	}

	public Integer d(St003Cryxxb st003Cryxxb) {
		int result = 0;
		St004Yzxxb st004Yzxxb = new St004Yzxxb();
		st004Yzxxb.setOrderName("脐静脉");
		st004Yzxxb.setZyid(st003Cryxxb.getZyid());
		List yzxxbList = this.bu.likeOrderName(st004Yzxxb);
		if (yzxxbList.size() > 0) {
			FxZhibiao zb = this.st.getByzbClass("jijingmai");
			result = zb.getZbScore().intValue();
			Iterator arg6 = yzxxbList.iterator();

			while (arg6.hasNext()) {
				St004Yzxxb Yzxxb = (St004Yzxxb) arg6.next();
				FxPatientZb fxPatientZb = new FxPatientZb();
				fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
				fxPatientZb.setZyId(st003Cryxxb.getZyid());
				fxPatientZb.setZbId(zb.getZbId());
				fxPatientZb.setZbScore(Integer.valueOf(result));
				fxPatientZb.setStartDate(Yzxxb.getOrderAt());
				fxPatientZb.setEndDate(Yzxxb.getStopAt());
				fxPatientZb.setZbName(zb.getZbName());
				fxPatientZb.setUpdateTime(new Date());
				fxPatientZb.setCaseId(Yzxxb.getId());
				fxPatientZb.setPzStatus(zb.getIsGy());
				this.ss.save(fxPatientZb);
			}
		}

		return Integer.valueOf(result);
	}

	public Integer e(St003Cryxxb st003Cryxxb) {
		int result = 0;
		List yzxxbList = this.bu.findInOrderName(bn.oE.getValue(), st003Cryxxb.getZyid());
		if (yzxxbList.size() > 0) {
			FxZhibiao zb = this.st.getByzbClass("miniaodao");
			result = zb.getZbScore().intValue();
			Iterator arg5 = yzxxbList.iterator();

			while (arg5.hasNext()) {
				St004Yzxxb Yzxxb = (St004Yzxxb) arg5.next();
				FxPatientZb fxPatientZb = new FxPatientZb();
				fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
				fxPatientZb.setZyId(st003Cryxxb.getZyid());
				fxPatientZb.setZbId(zb.getZbId());
				fxPatientZb.setZbScore(Integer.valueOf(result));
				fxPatientZb.setStartDate(Yzxxb.getOrderAt());
				fxPatientZb.setEndDate(Yzxxb.getStopAt());
				fxPatientZb.setZbName(zb.getZbName());
				fxPatientZb.setUpdateTime(new Date());
				fxPatientZb.setCaseId(Yzxxb.getId());
				fxPatientZb.setPzStatus(zb.getIsGy());
				List fxList = this.ss.a(fxPatientZb);
				if (fxList.size() == 0) {
					this.ss.save(fxPatientZb);
				}
			}
		}

		return Integer.valueOf(result);
	}

	public Integer f(St003Cryxxb st003Cryxxb) {
		int result = 0;
		List yzxxbList = this.bu.findInOrderName(bn.oG.getValue(), st003Cryxxb.getZyid());
		if (yzxxbList.size() > 0) {
			FxZhibiao zb = this.st.getByzbClass("huxiji");
			result = zb.getZbScore().intValue();
			Iterator arg5 = yzxxbList.iterator();

			while (arg5.hasNext()) {
				St004Yzxxb Yzxxb = (St004Yzxxb) arg5.next();
				FxPatientZb fxPatientZb = new FxPatientZb();
				fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
				fxPatientZb.setZyId(st003Cryxxb.getZyid());
				fxPatientZb.setZbId(zb.getZbId());
				fxPatientZb.setZbScore(Integer.valueOf(result));
				fxPatientZb.setStartDate(Yzxxb.getOrderAt());
				fxPatientZb.setEndDate(Yzxxb.getStopAt());
				fxPatientZb.setZbName(zb.getZbName());
				fxPatientZb.setUpdateTime(new Date());
				fxPatientZb.setCaseId(Yzxxb.getId());
				fxPatientZb.setPzStatus(zb.getIsGy());
				List fxList = this.ss.a(fxPatientZb);
				if (fxList.size() == 0) {
					this.ss.save(fxPatientZb);
				}
			}
		}

		return Integer.valueOf(result);
	}

	public Integer g(St003Cryxxb st003Cryxxb) {
		int result = 0;
		List yzxxbList = this.bu.findInOrderName(bn.oF.getValue(), st003Cryxxb.getZyid());
		if (yzxxbList.size() > 0) {
			FxZhibiao zb = this.st.getByzbClass("zhongxinjingmai");
			result = zb.getZbScore().intValue();
			Iterator arg5 = yzxxbList.iterator();

			while (arg5.hasNext()) {
				St004Yzxxb Yzxxb = (St004Yzxxb) arg5.next();
				FxPatientZb fxPatientZb = new FxPatientZb();
				fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
				fxPatientZb.setZyId(st003Cryxxb.getZyid());
				fxPatientZb.setZbId(zb.getZbId());
				fxPatientZb.setZbScore(Integer.valueOf(result));
				fxPatientZb.setStartDate(Yzxxb.getOrderAt());
				fxPatientZb.setEndDate(Yzxxb.getStopAt());
				fxPatientZb.setZbName(zb.getZbName());
				fxPatientZb.setUpdateTime(new Date());
				fxPatientZb.setCaseId(Yzxxb.getId());
				fxPatientZb.setPzStatus(zb.getIsGy());
				List fxList = this.ss.a(fxPatientZb);
				if (fxList.size() == 0) {
					this.ss.save(fxPatientZb);
				}
			}
		}

		return Integer.valueOf(result);
	}

	public Integer h(St003Cryxxb st003Cryxxb) {
		int result = 0;
		FxZhibiao zb = this.st.getByzbClass("zhuyuantianshu");
		if (st003Cryxxb != null && st003Cryxxb.getInDays() != null
				&& st003Cryxxb.getInDays().intValue() > Integer.parseInt(zb.getZbValue())) {
			result = zb.getZbScore().intValue();
		}

		return Integer.valueOf(result);
	}

	public Integer i(St003Cryxxb st003Cryxxb) {
		int result = 0;
		FxZhibiao zb = this.st.getByzbClass("baixibao");
		if (zb != null && ab.isNotEmpty(zb.getZbValue())) {
			List zbValue = Arrays.asList(zb.getZbValue().split(","));
			List sjList = this.bH.findByBXB(st003Cryxxb.getZyid(), b.dybw, zbValue);
			if (sjList.size() > 0) {
				result = zb.getZbScore().intValue();
				Iterator arg6 = sjList.iterator();

				while (arg6.hasNext()) {
					St011Syjgb syjgb = (St011Syjgb) arg6.next();
					FxPatientZb fxPatientZb = new FxPatientZb();
					fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
					fxPatientZb.setZyId(st003Cryxxb.getZyid());
					fxPatientZb.setZbId(zb.getZbId());
					fxPatientZb.setZbScore(Integer.valueOf(result));
					fxPatientZb.setStartDate(f.k(syjgb.getResultDate(), "yyyy-MM-dd HH:mm:ss"));
					fxPatientZb.setZbName(syjgb.getAntiName());
					fxPatientZb.setUpdateTime(new Date());
					fxPatientZb.setCaseId(syjgb.getId());
					fxPatientZb.setPzStatus(zb.getIsGy());
					this.ss.save(fxPatientZb);
				}
			}
		}

		return Integer.valueOf(result);
	}

	public Integer j(St003Cryxxb st003Cryxxb) {
		int result = 0;
		Gr018Ysgrys gr018Ysgrys = new Gr018Ysgrys();
		gr018Ysgrys.setZyid(st003Cryxxb.getZyid());
		gr018Ysgrys.setDataForm(b.dataForm);
		gr018Ysgrys.setElementId(b.fg);
		List ysList = this.aR.findByFX(gr018Ysgrys);
		if (ysList.size() > 0) {
			FxZhibiao zb = this.st.getByzbClass("yishizhangai");
			result = zb.getZbScore().intValue();
			FxPatientZb fxPatientZb = new FxPatientZb();
			fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
			fxPatientZb.setZyId(st003Cryxxb.getZyid());
			fxPatientZb.setZbId(zb.getZbId());
			fxPatientZb.setZbScore(Integer.valueOf(result));
			fxPatientZb.setStartDate(((Gr018Ysgrys) ysList.get(0)).getDataDate());
			fxPatientZb.setZbName(zb.getZbName());
			fxPatientZb.setUpdateTime(new Date());
			fxPatientZb.setCaseId(((Gr018Ysgrys) ysList.get(0)).getId());
			fxPatientZb.setPzStatus(zb.getIsGy());
			this.ss.save(fxPatientZb);
		}

		return Integer.valueOf(result);
	}

	public Integer k(St003Cryxxb st003Cryxxb) {
		int result = 0;
		FxZhibiao zb = this.st.getByzbClass("baidanbai");
		List sjList = this.bH.findByBDB(st003Cryxxb.getZyid(), b.dybw, b.fh);
		if (sjList.size() > 0) {
			result = zb.getZbScore().intValue();
			Iterator arg5 = sjList.iterator();

			while (arg5.hasNext()) {
				St011Syjgb syjgb = (St011Syjgb) arg5.next();
				FxPatientZb fxPatientZb = new FxPatientZb();
				fxPatientZb.setPatientId(st003Cryxxb.getPatientId());
				fxPatientZb.setZyId(st003Cryxxb.getZyid());
				fxPatientZb.setZbId(zb.getZbId());
				fxPatientZb.setZbScore(Integer.valueOf(result));
				fxPatientZb.setStartDate(f.k(syjgb.getResultDate(), "yyyy-MM-dd HH:mm:ss"));
				fxPatientZb.setZbName(syjgb.getAntiName());
				fxPatientZb.setUpdateTime(new Date());
				fxPatientZb.setCaseId(syjgb.getId());
				fxPatientZb.setPzStatus(zb.getIsGy());
				this.ss.save(fxPatientZb);
			}
		}

		return Integer.valueOf(result);
	}
}