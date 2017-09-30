package com.nis.cdc.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.dao.CtgBk006TumourDao;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk006Tumour;
import com.nis.cdc.service.CtgBk006TumourService;
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
public class CtgBk006TumourServiceImpl implements CtgBk006TumourService {
	private static final Logger c = Logger.getLogger(CtgBk006TumourServiceImpl.class);
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private CtgBk006TumourDao dX;
	@Autowired
	private SysParamService j;

	public void save(CtgBk006Tumour ctgBk006Tumour) {
	}

	public Result<String> a(CtgBk006Tumour ctgBk006Tumour, AcAccount account) {
		Result r = new Result();
		new SimpleDateFormat("yyyyMMddHHmmssSSS");
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			if (ctgBk006Tumour.getReportdt() == null) {
				ctgBk006Tumour.setReportdt(new Date());
			}

			if (ctgBk006Tumour.getFlag() == null) {
				ctgBk006Tumour.setFlag(Long.valueOf(0L));
			}

			if (ab.aM(ctgBk006Tumour.getMasterid())) {
				ctgBk006Tumour.setMasterid(af.getUUID32());
				this.dX.save(ctgBk006Tumour);
			} else {
				this.dX.update(ctgBk006Tumour);
			}

			String e = this.j.findByParamCode(Param.NIS_GK_DEPTID);
			String content = ctgBk006Tumour.getReportdoctorname() + "（" + ctgBk006Tumour.getReportdeptname()
					+ "）上报了 肿瘤报卡";
			this.cV.a(ctgBk006Tumour.getZyid(), ctgBk006Tumour.getMzid(), account.getUsername(), account.getRealname(),
					content, (String[]) null, new String[]{e}, al.jy.getValue(), ctgBk006Tumour.getMasterid());
			r.setResult("success");
			r.setMsg("保存成功！");
		} catch (Exception arg7) {
			arg7.printStackTrace();
			c.error("肿瘤报卡保存操作出错！", arg7);
			r.setResult("error");
			r.setMsg("保存失败！");
			r.setExtraValue(arg7.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return r;
	}

	public void delete(String masterid) {
		this.dX.delete(masterid);
	}

	public void update(CtgBk006Tumour ctgBk006Tumour) {
		this.dX.update(ctgBk006Tumour);
	}

	public CtgBk006Tumour get(String masterid) {
		return this.dX.get(masterid);
	}

	public MyPage<CtgBk006Tumour> a(CtgBk006Tumour ctgBk006Tumour) {
		return null;
	}

	public List<CtgBk006Tumour> getAll() {
		return this.dX.getAll();
	}

	public List<CtgBk006Tumour> getByMastertid(String masterid) {
		return null;
	}

	public List<CtgBk006Tumour> findCardsForAdmin(CtgBk001Crbmaster ctgBk001Crbmaster) {
		return this.dX.findCardsForAdmin(ctgBk001Crbmaster);
	}

	public void audit(CtgBk006Tumour ctgBk006Tumour) {
		this.dX.audit(ctgBk006Tumour);
	}

	public void retreat(CtgBk006Tumour ctgBk006Tumour) {
		this.dX.retreat(ctgBk006Tumour);
	}

	public void cancel(CtgBk006Tumour ctgBk006Tumour) {
		this.dX.cancel(ctgBk006Tumour);
	}

	public void remove(CtgBk006Tumour ctgBk006Tumour) {
		this.dX.remove(ctgBk006Tumour);
	}

	public void updateNotes(String masterid, String notes) {
	}

	public void batchAudit(CtgBk006Tumour ctgBk006Tumour) {
		this.dX.batchAudit(ctgBk006Tumour);
	}

	public void updatePrintFlag(String masterid) {
		this.dX.updatePrintFlag(masterid);
	}
}