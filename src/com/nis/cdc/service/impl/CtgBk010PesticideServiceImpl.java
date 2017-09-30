package com.nis.cdc.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.dao.CtgBk010PesticideDao;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk010Pesticide;
import com.nis.cdc.service.CtgBk010PesticideService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.param.service.SysParamService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
public class CtgBk010PesticideServiceImpl implements CtgBk010PesticideService {
	private static final Logger c = Logger.getLogger(CtgBk010PesticideServiceImpl.class);
	@Autowired
	private CtgBk010PesticideDao ea;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private SysParamService j;

	public void save(CtgBk010Pesticide ctgBk010Pesticide) {
		this.ea.save(ctgBk010Pesticide);
	}

	public void delete(String masterid) {
		this.ea.delete(masterid);
	}

	public void update(CtgBk010Pesticide ctgBk010Pesticide) {
		this.ea.update(ctgBk010Pesticide);
	}

	public CtgBk010Pesticide get(String masterid) {
		return this.ea.get(masterid);
	}

	public MyPage<CtgBk010Pesticide> a(CtgBk010Pesticide ctgBk010Pesticide) {
		int total = this.ea.findCtgBk010PesticideCount(ctgBk010Pesticide);
		List data = null;
		if (total > 0) {
			data = this.ea.findCtgBk010Pesticide(ctgBk010Pesticide);
		}

		return new MyPage(ctgBk010Pesticide.getPage().intValue(), ctgBk010Pesticide.getSize().intValue(), total, data);
	}

	public List<CtgBk010Pesticide> getAll() {
		return this.ea.getAll();
	}

	public Result<String> a(CtgBk010Pesticide ctgBk010Pesticide, AcAccount ac) {
		Result r = new Result();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			if (ctgBk010Pesticide.getReportdt() == null) {
				ctgBk010Pesticide.setReportdt(new Date());
			}

			if (ab.isEmpty(ctgBk010Pesticide.getCardId())) {
				ctgBk010Pesticide.setCardId("C" + sdf.format(new Date()));
			}

			if (ctgBk010Pesticide.getFlag() == null) {
				ctgBk010Pesticide.setFlag(Long.valueOf(0L));
			}

			if (ab.aM(ctgBk010Pesticide.getMasterid())) {
				ctgBk010Pesticide.setMasterid(af.getUUID32());
				this.ea.save(ctgBk010Pesticide);
			} else {
				this.ea.update(ctgBk010Pesticide);
			}

			String e = this.j.findByParamCode(Param.NIS_GK_DEPTID);
			String content = ctgBk010Pesticide.getReportdoctorname() + "（" + ctgBk010Pesticide.getReportdeptname()
					+ "）上报了 农药中毒报卡";
			this.cV.a(ctgBk010Pesticide.getZyid(), ctgBk010Pesticide.getMzid(), ac.getUsername(), ac.getRealname(),
					content, (String[]) null, new String[]{e}, al.jX.getValue(), ctgBk010Pesticide.getMasterid());
			r.setResult("success");
			r.setMsg("保存成功！");
		} catch (Exception arg7) {
			arg7.printStackTrace();
			c.error("农药中毒报卡保存操作出错！", arg7);
			r.setResult("error");
			r.setMsg("保存失败！");
			r.setExtraValue(arg7.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return r;
	}

	public List<CtgBk010Pesticide> findCardsForAdmin(CtgBk001Crbmaster ctgBk001Crbmaster) {
		return this.ea.findCardsForAdmin(ctgBk001Crbmaster);
	}

	public void audit(CtgBk010Pesticide ctgBk010Pesticide) {
		this.ea.audit(ctgBk010Pesticide);
	}

	public void retreat(CtgBk010Pesticide ctgBk010Pesticide) {
		this.ea.retreat(ctgBk010Pesticide);
	}

	public void cancel(CtgBk010Pesticide ctgBk010Pesticide) {
		this.ea.cancel(ctgBk010Pesticide);
	}

	public void remove(CtgBk010Pesticide ctgBk010Pesticide) {
		this.ea.remove(ctgBk010Pesticide);
	}

	public void updateNotes(String masterid, String notes) {
		this.ea.updateNotes(masterid, notes);
	}

	public void batchAudit(CtgBk010Pesticide ctgBk010Pesticide) {
		this.ea.batchAudit(ctgBk010Pesticide);
	}

	public void updatePrintFlag(String masterid) {
		this.ea.updatePrintFlag(masterid);
	}
}