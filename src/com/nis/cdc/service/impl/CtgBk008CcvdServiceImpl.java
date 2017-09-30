package com.nis.cdc.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.dao.CtgBk008CcvdDao;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk008Ccvd;
import com.nis.cdc.service.CtgBk008CcvdService;
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
public class CtgBk008CcvdServiceImpl implements CtgBk008CcvdService {
	private static final Logger c = Logger.getLogger(CtgBk008CcvdServiceImpl.class);
	@Autowired
	private CtgBk008CcvdDao dY;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private SysParamService j;

	public void save(CtgBk008Ccvd ctgBk008Ccvd) {
		this.dY.save(ctgBk008Ccvd);
	}

	public void delete(String masterid) {
		this.dY.delete(masterid);
	}

	public void update(CtgBk008Ccvd ctgBk008Ccvd) {
		this.dY.update(ctgBk008Ccvd);
	}

	public CtgBk008Ccvd get(String masterid) {
		return this.dY.get(masterid);
	}

	public MyPage<CtgBk008Ccvd> a(CtgBk008Ccvd ctgBk008Ccvd) {
		int total = this.dY.findCtgBk008CcvdCount(ctgBk008Ccvd);
		List data = null;
		if (total > 0) {
			data = this.dY.findCtgBk008Ccvd(ctgBk008Ccvd);
		}

		return new MyPage(ctgBk008Ccvd.getPage().intValue(), ctgBk008Ccvd.getSize().intValue(), total, data);
	}

	public List<CtgBk008Ccvd> getAll() {
		return this.dY.getAll();
	}

	public Result<String> a(CtgBk008Ccvd ctgBk008Ccvd, AcAccount account) {
		new SimpleDateFormat("yyyyMMddHHmmssSSS");
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Result result = new Result();

		try {
			if (ctgBk008Ccvd.getReportdt() == null) {
				ctgBk008Ccvd.setReportdt(new Date());
			}

			if (ctgBk008Ccvd.getFlag() == null) {
				ctgBk008Ccvd.setFlag(Long.valueOf(0L));
			}

			if (!"死亡".equals(ctgBk008Ccvd.getTheresult())) {
				ctgBk008Ccvd.setDeadReason("");
				ctgBk008Ccvd.setDeadReportName("");
				ctgBk008Ccvd.setDeadzone("");
				ctgBk008Ccvd.setDeathDt((Date) null);
			}

			if (ab.aM(ctgBk008Ccvd.getMasterid())) {
				ctgBk008Ccvd.setMasterid(af.getUUID32());
				this.dY.save(ctgBk008Ccvd);
			} else {
				this.dY.update(ctgBk008Ccvd);
			}

			String e = this.j.findByParamCode(Param.NIS_GK_DEPTID);
			String content = ctgBk008Ccvd.getReportdoctorname() + "（" + ctgBk008Ccvd.getReportdeptname()
					+ "）上报了 居民心脑血管事件报卡";
			this.cV.a(ctgBk008Ccvd.getZyid(), ctgBk008Ccvd.getMzid(), account.getUsername(), account.getRealname(),
					content, (String[]) null, new String[]{e}, al.jz.getValue(), ctgBk008Ccvd.getMasterid());
			result.setResult("success");
			result.setMsg("保存成功！");
		} catch (Exception arg7) {
			arg7.printStackTrace();
			c.error("保存操作出错！", arg7);
			result.setResult("error");
			result.setMsg("保存失败！");
			result.setExtraValue(arg7.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return result;
	}

	public List<CtgBk008Ccvd> findCardsForAdmin(CtgBk001Crbmaster ctgBk001Crbmaster) {
		return this.dY.findCardsForAdmin(ctgBk001Crbmaster);
	}

	public void audit(CtgBk008Ccvd ctgBk008Ccvd) {
		this.dY.audit(ctgBk008Ccvd);
	}

	public void retreat(CtgBk008Ccvd ctgBk008Ccvd) {
		this.dY.retreat(ctgBk008Ccvd);
	}

	public void cancel(CtgBk008Ccvd ctgBk008Ccvd) {
		this.dY.cancel(ctgBk008Ccvd);
	}

	public void remove(CtgBk008Ccvd ctgBk008Ccvd) {
		this.dY.remove(ctgBk008Ccvd);
	}

	public void updateNotes(String masterid, String notes) {
	}

	public void batchAudit(CtgBk008Ccvd ctgBk008Ccvd) {
		this.dY.batchAudit(ctgBk008Ccvd);
	}

	public void updatePrintFlag(String masterid) {
		this.dY.updatePrintFlag(masterid);
	}
}