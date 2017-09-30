package com.nis.cdc.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.dao.CtgBk004SyycbkDao;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk004Syycbk;
import com.nis.cdc.service.CtgBk004SyycbkService;
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
public class CtgBk004SyycbkServiceImpl implements CtgBk004SyycbkService {
	private static final Logger c = Logger.getLogger(CtgBk004SyycbkServiceImpl.class);
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private CtgBk004SyycbkDao dT;
	@Autowired
	private SysParamService j;

	public void save(CtgBk004Syycbk ctgBk004Syycbk) {
		this.dT.save(ctgBk004Syycbk);
	}

	public void delete(String masterid) {
		this.dT.delete(masterid);
	}

	public void update(CtgBk004Syycbk ctgBk004Syycbk) {
		this.dT.update(ctgBk004Syycbk);
	}

	public CtgBk004Syycbk get(String masterid) {
		return this.dT.get(masterid);
	}

	public MyPage<CtgBk004Syycbk> a(CtgBk004Syycbk ctgBk004Syycbk) {
		int total = this.dT.findCtgBk004SyycbkCount(ctgBk004Syycbk);
		List data = null;
		if (total > 0) {
			data = this.dT.findCtgBk004Syycbk(ctgBk004Syycbk);
		}

		return new MyPage(ctgBk004Syycbk.getPage().intValue(), ctgBk004Syycbk.getSize().intValue(), total, data);
	}

	public List<CtgBk004Syycbk> getAll() {
		return this.dT.getAll();
	}

	public List<CtgBk004Syycbk> findCardsForAdmin(CtgBk001Crbmaster ctgBk001Crbmaster) {
		return this.dT.findCardsForAdmin(ctgBk001Crbmaster);
	}

	public void audit(CtgBk004Syycbk ctgBk004Syycbk) {
		this.dT.audit(ctgBk004Syycbk);
	}

	public void retreat(CtgBk004Syycbk ctgBk004Syycbk) {
		this.dT.retreat(ctgBk004Syycbk);
	}

	public void cancel(CtgBk004Syycbk ctgBk004Syycbk) {
		this.dT.cancel(ctgBk004Syycbk);
	}

	public void remove(CtgBk004Syycbk ctgBk004Syycbk) {
		this.dT.remove(ctgBk004Syycbk);
	}

	public void updateNotes(String masterid, String notes) {
	}

	public void batchAudit(CtgBk004Syycbk ctgBk004Syycbk) {
		this.dT.batchAudit(ctgBk004Syycbk);
	}

	public Result<String> a(CtgBk004Syycbk ctgBk004Syycbk, AcAccount account) {
		Result r = new Result();
		new SimpleDateFormat("yyyyMMddHHmmssSSS");
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			if (ctgBk004Syycbk.getReportdt() == null) {
				ctgBk004Syycbk.setReportdt(new Date());
			}

			if (ctgBk004Syycbk.getFlag() == null) {
				ctgBk004Syycbk.setFlag(Long.valueOf(0L));
			}

			if (ab.aM(ctgBk004Syycbk.getMasterid())) {
				ctgBk004Syycbk.setMasterid(af.getUUID32());
				this.dT.save(ctgBk004Syycbk);
			} else {
				this.dT.update(ctgBk004Syycbk);
			}

			String e = this.j.findByParamCode(Param.NIS_GK_DEPTID);
			String content = ctgBk004Syycbk.getReportdoctorname() + "（" + ctgBk004Syycbk.getReportdeptname()
					+ "）上报了 食源异常报卡";
			this.cV.a(ctgBk004Syycbk.getZyid(), ctgBk004Syycbk.getMzid(), account.getUsername(), account.getRealname(),
					content, (String[]) null, new String[]{e}, al.jw.getValue(), ctgBk004Syycbk.getMasterid());
			r.setResult("success");
			r.setMsg("保存成功！");
		} catch (Exception arg7) {
			arg7.printStackTrace();
			c.error("食源异常报卡保存操作出错！", arg7);
			r.setResult("error");
			r.setMsg("保存失败！");
			r.setExtraValue(arg7.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return r;
	}

	public void updatePrintFlag(String masterid) {
		this.dT.updatePrintFlag(masterid);
	}
}