package com.nis.cdc.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.dao.CtgBk009SunstrokeDao;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk009Sunstroke;
import com.nis.cdc.service.CtgBk009SunstrokeService;
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
public class CtgBk009SunstrokeServiceImpl implements CtgBk009SunstrokeService {
	private static final Logger c = Logger.getLogger(CtgBk009SunstrokeServiceImpl.class);
	@Autowired
	private CtgBk009SunstrokeDao dZ;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private SysParamService j;

	public void save(CtgBk009Sunstroke ctgBk009Sunstroke) {
		this.dZ.save(ctgBk009Sunstroke);
	}

	public void delete(String masterid) {
		this.dZ.delete(masterid);
	}

	public void update(CtgBk009Sunstroke ctgBk009Sunstroke) {
		this.dZ.update(ctgBk009Sunstroke);
	}

	public CtgBk009Sunstroke get(String masterid) {
		return this.dZ.get(masterid);
	}

	public MyPage<CtgBk009Sunstroke> a(CtgBk009Sunstroke ctgBk009Sunstroke) {
		int total = this.dZ.findCtgBk009SunstrokeCount(ctgBk009Sunstroke);
		List data = null;
		if (total > 0) {
			data = this.dZ.findCtgBk009Sunstroke(ctgBk009Sunstroke);
		}

		return new MyPage(ctgBk009Sunstroke.getPage().intValue(), ctgBk009Sunstroke.getSize().intValue(), total, data);
	}

	public List<CtgBk009Sunstroke> getAll() {
		return this.dZ.getAll();
	}

	public Result<String> a(CtgBk009Sunstroke ctgBk009Sunstroke, AcAccount ac) {
		Result r = new Result();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			if (ctgBk009Sunstroke.getReportdt() == null) {
				ctgBk009Sunstroke.setReportdt(new Date());
			}

			if (ab.isEmpty(ctgBk009Sunstroke.getCardId())) {
				ctgBk009Sunstroke.setCardId("C" + sdf.format(new Date()));
			}

			if (ctgBk009Sunstroke.getFlag() == null) {
				ctgBk009Sunstroke.setFlag(Long.valueOf(0L));
			}

			if (ab.aM(ctgBk009Sunstroke.getMasterid())) {
				ctgBk009Sunstroke.setMasterid(af.getUUID32());
				this.dZ.save(ctgBk009Sunstroke);
			} else {
				this.dZ.update(ctgBk009Sunstroke);
			}

			String e = this.j.findByParamCode(Param.NIS_GK_DEPTID);
			String content = ctgBk009Sunstroke.getReportdoctorname() + "（" + ctgBk009Sunstroke.getReportdeptname()
					+ "）上报了 高温中暑报卡";
			this.cV.a(ctgBk009Sunstroke.getZyid(), ctgBk009Sunstroke.getMzid(), ac.getUsername(), ac.getRealname(),
					content, (String[]) null, new String[]{e}, al.jA.getValue(), ctgBk009Sunstroke.getMasterid());
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

	public List<CtgBk009Sunstroke> findCardsForAdmin(CtgBk001Crbmaster ctgBk001Crbmaster) {
		return this.dZ.findCardsForAdmin(ctgBk001Crbmaster);
	}

	public void audit(CtgBk009Sunstroke ctgBk009Sunstroke) {
		this.dZ.audit(ctgBk009Sunstroke);
	}

	public void retreat(CtgBk009Sunstroke ctgBk009Sunstroke) {
		this.dZ.retreat(ctgBk009Sunstroke);
	}

	public void cancel(CtgBk009Sunstroke ctgBk009Sunstroke) {
		this.dZ.cancel(ctgBk009Sunstroke);
	}

	public void remove(CtgBk009Sunstroke ctgBk009Sunstroke) {
		this.dZ.remove(ctgBk009Sunstroke);
	}

	public void updateNotes(String masterid, String notes) {
		this.dZ.updateNotes(masterid, notes);
	}

	public void batchAudit(CtgBk009Sunstroke ctgBk009Sunstroke) {
		this.dZ.batchAudit(ctgBk009Sunstroke);
	}

	public void updatePrintFlag(String masterid) {
		this.dZ.updatePrintFlag(masterid);
	}
}