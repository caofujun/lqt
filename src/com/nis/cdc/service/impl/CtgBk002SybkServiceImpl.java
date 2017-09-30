package com.nis.cdc.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.dao.CtgBk002SybkDao;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk002Sybk;
import com.nis.cdc.entity.CtgSys013Serial;
import com.nis.cdc.service.CtgBk002SybkService;
import com.nis.cdc.service.CtgSys013SerialService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
public class CtgBk002SybkServiceImpl implements CtgBk002SybkService {
	private static final Logger c = Logger.getLogger(CtgBk002SybkServiceImpl.class);
	@Autowired
	private CtgBk002SybkDao dR;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private SysParamService j;
	@Autowired
	private CtgSys013SerialService dw;

	public void save(CtgBk002Sybk ctgBk002Sybk) {
		this.dR.save(ctgBk002Sybk);
	}

	public void delete(String masterid) {
		this.dR.delete(masterid);
	}

	public void update(CtgBk002Sybk ctgBk002Sybk) {
		this.dR.update(ctgBk002Sybk);
	}

	public CtgBk002Sybk get(String masterid) {
		return this.dR.get(masterid);
	}

	public MyPage<CtgBk002Sybk> a(CtgBk002Sybk ctgBk002Sybk) {
		int total = this.dR.findCtgBk002SybkCount(ctgBk002Sybk);
		List data = null;
		if (total > 0) {
			data = this.dR.findCtgBk002Sybk(ctgBk002Sybk);
		}

		return new MyPage(ctgBk002Sybk.getPage().intValue(), ctgBk002Sybk.getSize().intValue(), total, data);
	}

	public List<CtgBk002Sybk> getAll() {
		return this.dR.getAll();
	}

	public List<CtgBk002Sybk> findCardsForAdmin(CtgBk001Crbmaster ctgBk001Crbmaster) {
		List list = this.dR.findCardsForAdmin(ctgBk001Crbmaster);
		return list;
	}

	public void audit(CtgBk002Sybk ctgBk002Sybk) {
		CtgBk002Sybk sybk = this.get(ctgBk002Sybk.getMasterid());
		ctgBk002Sybk.setSerialnumber(sybk.getSerialnumber());
		if (ctgBk002Sybk != null && "1".equals(this.j.findByParamCode(Param.NIS_CDC_GENERATE_DC_SERIAL_NUMBER))) {
			if (ab.isEmpty(sybk.getSerialnumber())) {
				ctgBk002Sybk.setSerialnumber(this.dw.x(ctgBk002Sybk.getMasterid()));
			} else {
				ctgBk002Sybk.setSerialnumber(sybk.getSerialnumber());
			}
		}

		this.dR.audit(ctgBk002Sybk);
	}

	public void retreat(CtgBk002Sybk ctgBk002Sybk) {
		CtgBk002Sybk sybk = this.dR.get(ctgBk002Sybk.getMasterid());
		if (sybk != null && ab.isNotEmpty(sybk.getSerialnumber())
				&& "1".equals(this.j.findByParamCode(Param.NIS_CDC_GENERATE_DC_SERIAL_NUMBER))) {
			String serialNum = sybk.getSerialnumber();
			ctgBk002Sybk.setSerialnumber("");
			CtgSys013Serial css = new CtgSys013Serial();
			css.setIdnumber(af.getUUID32());
			css.setIsReuse(Long.valueOf(0L));
			css.setMasterid(sybk.getMasterid());
			css.setRecyDt(new Date());
			css.setRecyPersonid(ctgBk002Sybk.getAuditor());
			css.setRecyPersonname(ctgBk002Sybk.getAuditorname());
			css.setSerialnumber(serialNum);
			this.dw.save(css);
		} else if (sybk != null && ab.isNotEmpty(sybk.getSerialnumber())
				&& !"1".equals(this.j.findByParamCode(Param.NIS_CDC_GENERATE_DC_SERIAL_NUMBER))) {
			ctgBk002Sybk.setSerialnumber(sybk.getSerialnumber());
		}

		this.dR.retreat(ctgBk002Sybk);
	}

	public void cancel(CtgBk002Sybk ctgBk002Sybk) {
		this.dR.cancel(ctgBk002Sybk);
	}

	public void remove(CtgBk002Sybk ctgBk002Sybk) {
		CtgBk002Sybk sybk = this.dR.get(ctgBk002Sybk.getMasterid());
		if (sybk != null && ab.isNotEmpty(sybk.getSerialnumber())
				&& "1".equals(this.j.findByParamCode(Param.NIS_CDC_GENERATE_DC_SERIAL_NUMBER))) {
			String serialNum = sybk.getSerialnumber();
			ctgBk002Sybk.setSerialnumber("");
			CtgSys013Serial css = new CtgSys013Serial();
			css.setIdnumber(af.getUUID32());
			css.setIsReuse(Long.valueOf(0L));
			css.setMasterid(sybk.getMasterid());
			css.setRecyDt(new Date());
			css.setRecyPersonid(ctgBk002Sybk.getAuditor());
			css.setRecyPersonname(ctgBk002Sybk.getAuditorname());
			css.setSerialnumber(serialNum);
			this.dw.save(css);
		} else if (sybk != null && ab.isNotEmpty(sybk.getSerialnumber())
				&& !"1".equals(this.j.findByParamCode(Param.NIS_CDC_GENERATE_DC_SERIAL_NUMBER))) {
			ctgBk002Sybk.setSerialnumber(sybk.getSerialnumber());
		}

		this.dR.remove(ctgBk002Sybk);
	}

	public void updateNotes(String masterid, String notes) {
		this.dR.updateNotes(masterid, notes);
	}

	public void batchAudit(CtgBk002Sybk ctgBk002Sybk) {
		this.dR.batchAudit(ctgBk002Sybk);
	}

	@Transactional
	public Result<String> a(CtgBk002Sybk ctgBk002Sybk, AcAccount account) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Result result = new Result();

		try {
			if (ab.aM(ctgBk002Sybk.getCardid())) {
				ctgBk002Sybk.setCardid(sdf.format(new Date()));
			}

			if (ctgBk002Sybk.getFilldate() == null) {
				ctgBk002Sybk.setFilldate(new Date());
			}

			if (ctgBk002Sybk.getFlag() == null) {
				ctgBk002Sybk.setFlag(Long.valueOf(0L));
			}

			String e = af.getUUID32();
			String isGen = this.j.findByParamCode(Param.NIS_CDC_GENERATE_DC_SERIAL_NUMBER);
			String gkkCode;
			if ("1".equals(isGen) && ab.isEmpty(ctgBk002Sybk.getSerialnumber())) {
				gkkCode = "";
				if (ab.aM(ctgBk002Sybk.getMasterid())) {
					gkkCode = this.dw.x(e);
				} else {
					gkkCode = this.dw.x(ctgBk002Sybk.getMasterid());
				}

				ctgBk002Sybk.setSerialnumber(gkkCode);
			}

			if (ab.aM(ctgBk002Sybk.getMasterid())) {
				ctgBk002Sybk.setMasterid(e);
				result.setExtraValue(ctgBk002Sybk.getMasterid());
				this.dR.save(ctgBk002Sybk);
			} else {
				result.setExtraValue(ctgBk002Sybk.getMasterid());
				this.dR.update(ctgBk002Sybk);
			}

			gkkCode = this.j.findByParamCode(Param.NIS_GK_DEPTID);
			String content = ctgBk002Sybk.getReportdoctorname() + "（" + ctgBk002Sybk.getReportdeptname() + "）上报了 死因报卡";
			this.cV.a(ctgBk002Sybk.getZyid(), ctgBk002Sybk.getMzid(), account.getUsername(), account.getRealname(),
					content, (String[]) null, new String[]{gkkCode}, al.jv.getValue(), ctgBk002Sybk.getMasterid());
			result.setResult("success");
			result.setMsg("保存成功！");
		} catch (Exception arg9) {
			arg9.printStackTrace();
			c.error("保存操作出错！", arg9);
			result.setResult("error");
			result.setMsg("保存失败！");
			result.setExtraValue(arg9.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return result;
	}

	public void updatePrintFlag(String masterid) {
		this.dR.updatePrintFlag(masterid);
	}
}