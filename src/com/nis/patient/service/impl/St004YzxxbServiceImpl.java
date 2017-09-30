package com.nis.patient.service.impl;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.MonitorOrder;
import com.nis.dict.service.MonitorOrderService;
import com.nis.monitor.entity.Gr016BkKjyw;
import com.nis.monitor.entity.Gr016SsbwKjyw;
import com.nis.monitor.service.Gr016BkKjywService;
import com.nis.monitor.service.Gr016SsbwKjywService;
import com.nis.patient.dao.St004YzxxbDao;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.entity.St012Kjyw;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St012KjywService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class St004YzxxbServiceImpl implements St004YzxxbService {
	@Autowired
	private St004YzxxbDao tt;
	@Autowired
	private MonitorOrderService qy;
	@Autowired
	private St012KjywService bM;
	@Autowired
	private Gr016SsbwKjywService uy;
	@Autowired
	private Gr016BkKjywService uu;

	public void save(St004Yzxxb st004Yzxxb) {
		this.tt.save(st004Yzxxb);
	}

	public void delete(String id) {
		this.tt.delete(id);
	}

	public void update(St004Yzxxb st004Yzxxb) {
		this.tt.update(st004Yzxxb);
	}

	public void updateSpecified(St004Yzxxb st004Yzxxb, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.tt.updateSpecified(st004Yzxxb, updateAttrs);
		}

	}

	public St004Yzxxb get(String id) {
		return this.tt.get(id);
	}

	public MyPage<St004Yzxxb> a(St004Yzxxb st004Yzxxb) {
		int total = this.tt.findSt004YzxxbCount(st004Yzxxb);
		List data = null;
		if (total > 0) {
			data = this.tt.findSt004Yzxxb(st004Yzxxb);
		}

		return new MyPage(st004Yzxxb.getPage().intValue(), st004Yzxxb.getSize().intValue(), total, data);
	}

	public List<St004Yzxxb> getAll() {
		return this.tt.getAll();
	}

	public MyPage<St004Yzxxb> b(St004Yzxxb st004Yzxxb) {
		int total = this.tt.findDocAdviceCount(st004Yzxxb);
		List data = null;
		if (total > 0) {
			data = this.tt.findDocAdvice(st004Yzxxb);
		}

		return new MyPage(st004Yzxxb.getPage().intValue(), st004Yzxxb.getSize().intValue(), total, data);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(MonitorOrder monitorOrder, String st004Id) {
		this.qy.save(monitorOrder);
		St004Yzxxb st004Yzxxb = new St004Yzxxb();
		st004Yzxxb.setId(st004Id);
		st004Yzxxb.setFlagJr(Integer.valueOf(1));
		ArrayList updateAttrs = new ArrayList();
		updateAttrs.add("flagJr");
		this.updateSpecified(st004Yzxxb, updateAttrs);
	}

	public List<St004Yzxxb> likeOrderName(St004Yzxxb st004Yzxxb) {
		return this.tt.likeOrderName(st004Yzxxb);
	}

	public List<St004Yzxxb> findInOrderName(String classCode, String zyid) {
		return this.tt.findInOrderName(classCode, zyid);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(St012Kjyw st012Kjyw, String st004Id) {
		this.bM.save(st012Kjyw);
		St004Yzxxb st004Yzxxb = new St004Yzxxb();
		st004Yzxxb.setId(st004Id);
		st004Yzxxb.setIsKjyw(Integer.valueOf(1));
		ArrayList updateAttrs = new ArrayList();
		updateAttrs.add("isKjyw");
		this.updateSpecified(st004Yzxxb, updateAttrs);
	}

	public List<St004Yzxxb> findListByzyidName(String[] orderNames) {
		return this.tt.findListByzyidName(orderNames);
	}

	public List<St004Yzxxb> findDrugSituation(St004Yzxxb st004Yzxxb) {
		return this.tt.findDrugSituation(st004Yzxxb);
	}

	public List<St004Yzxxb> findByZyid(St004Yzxxb st004Yzxxb) {
		return this.tt.findByZyid(st004Yzxxb);
	}

	public Date getMonitorPatientYzxxLastAt(String deptId) {
		return this.tt.getMonitorPatientYzxxLastAt(deptId);
	}

	public List<St004Yzxxb> findDrugSituationByPatient(St004Yzxxb st004Yzxxb) {
		return this.tt.findDrugSituationByPatient(st004Yzxxb);
	}

	public void a(St004Yzxxb st004Yzxxb, String[] yzids) {
		Gr016SsbwKjyw gr016SsbwKjyw = null;
		St004Yzxxb st004Yz = null;
		if (yzids != null) {
			String[] arg7 = yzids;
			int arg6 = yzids.length;

			for (int arg5 = 0; arg5 < arg6; ++arg5) {
				String yzid = arg7[arg5];
				gr016SsbwKjyw = new Gr016SsbwKjyw();
				gr016SsbwKjyw.setRefid(st004Yzxxb.getRefid());
				gr016SsbwKjyw.setOperTypeId(Integer.valueOf(st004Yzxxb.getOperTypeId()));
				gr016SsbwKjyw.setIsselect(Integer.valueOf(1));
				st004Yz = this.tt.get(yzid);
				gr016SsbwKjyw.setOrderType(st004Yz.getOrderTypeName());
				gr016SsbwKjyw.setOrderAt(st004Yz.getOrderAt());
				gr016SsbwKjyw.setStopAt(st004Yz.getStopAt());
				gr016SsbwKjyw.setOrderName(st004Yz.getOrderName());
				gr016SsbwKjyw.setDose(st004Yz.getDosage());
				gr016SsbwKjyw.setDosageUnit(st004Yz.getDosageUnit());
				gr016SsbwKjyw.setQty(st004Yz.getQtyDay());
				gr016SsbwKjyw.setFrequency(st004Yz.getFrequency());
				gr016SsbwKjyw.setDailyTimes(st004Yz.getMrcs());
				gr016SsbwKjyw.setAdminRouteName(st004Yz.getAdminRouteName());
				gr016SsbwKjyw.setExecuteTime(st004Yz.getExecuteTime());
				gr016SsbwKjyw.setExecuteName(st004Yz.getExecuteName());
				gr016SsbwKjyw.setMemo(st004Yz.getMemo());
				gr016SsbwKjyw.setYzId(st004Yz.getId());
				gr016SsbwKjyw.setDateSection(st004Yz.getDateSection());
				this.uy.save(gr016SsbwKjyw);
			}

		}
	}

	public void b(St004Yzxxb st004Yzxxb, String[] yzids) {
		Gr016BkKjyw gr016BkKjyw = null;
		St004Yzxxb st004Yz = null;
		String[] arg7 = yzids;
		int arg6 = yzids.length;

		for (int arg5 = 0; arg5 < arg6; ++arg5) {
			String yzid = arg7[arg5];
			gr016BkKjyw = new Gr016BkKjyw();
			gr016BkKjyw.setOperTypeId(st004Yzxxb.getOperTypeId());
			gr016BkKjyw.setIsselect(1);
			gr016BkKjyw.setRelid(st004Yzxxb.getRelid());
			gr016BkKjyw.setRefid(st004Yzxxb.getRefid());
			st004Yz = this.tt.get(yzid);
			gr016BkKjyw.setOrderType(st004Yz.getOrderTypeName());
			gr016BkKjyw.setOrderAt(st004Yz.getOrderAt());
			gr016BkKjyw.setStopAt(st004Yz.getStopAt());
			gr016BkKjyw.setOrderName(st004Yz.getOrderName());
			gr016BkKjyw.setDose(st004Yz.getDosage());
			gr016BkKjyw.setDosageUnit(st004Yz.getDosageUnit());
			gr016BkKjyw.setQty(st004Yz.getQtyDay());
			gr016BkKjyw.setFrequency(st004Yz.getFrequency());
			gr016BkKjyw.setDailyTimes(st004Yz.getMrcs());
			gr016BkKjyw.setAdminRouteName(st004Yz.getAdminRouteName());
			gr016BkKjyw.setExecuteTime(st004Yz.getExecuteTime());
			gr016BkKjyw.setExecuteName(st004Yz.getExecuteName());
			gr016BkKjyw.setMemo(st004Yz.getMemo());
			gr016BkKjyw.setYzId(st004Yz.getId());
			gr016BkKjyw.setDateSection(st004Yz.getDateSection());
			this.uu.save(gr016BkKjyw);
		}

	}

	public List<DataWarning> findPatentYzxxbWarning(Date queryStartDate, Date queryEndDate) {
		return this.tt.findPatentYzxxbWarning(queryStartDate, queryEndDate);
	}

	public List<St004Yzxxb> getYzxx(String zyId) {
		return this.tt.getYzxx(zyId);
	}

	public List<St003Cryxxb> findKjywWaitAnaly(Integer orclEndNum) {
		return this.tt.findKjywWaitAnaly(orclEndNum);
	}

	public int findKjywWaitCount() {
		return this.tt.findKjywWaitCount();
	}

	public List<St004Yzxxb> findKjywByZyid(String zyid) {
		return this.tt.findKjywByZyid(zyid);
	}

	public void batchUpdAnalFlag(List<St004Yzxxb> st004List) {
		if (st004List != null && st004List.size() > 0) {
			this.tt.batchUpdAnalFlag(st004List);
		}

	}

	public List<St004Yzxxb> findDrug(St004Yzxxb st004Yzxxb) {
		return this.tt.findDrug(st004Yzxxb);
	}

	public List<St004Yzxxb> findDrugSituationByPatientTemp(St004Yzxxb st004Yzxxb) {
		return this.tt.findDrugSituationByPatientTemp(st004Yzxxb);
	}

	public List<St004Yzxxb> findDrugbyZyid(St004Yzxxb st004Yzxxb) {
		return this.tt.findDrugbyZyid(st004Yzxxb);
	}

	public void updAnalFlag(St004Yzxxb st004Yzxxb) {
		this.tt.updAnalFlag(st004Yzxxb);
	}

	public void updKeepToWaitState() {
		this.tt.updKeepToWaitState();
	}

	public int getUseDrugNum(String zyid, Date startDate, Date endDate, String orderId, String id) {
		return this.tt.getUseDrugNum(zyid, startDate, endDate, orderId, id);
	}

	public int getUseDrugNumNo(String zyid, Date startDate, Date endDate, String orderId) {
		return this.tt.getUseDrugNumNo(zyid, startDate, endDate, orderId);
	}

	public int getUseUnLimitNum(String zyid, Date startDate, Date endDate) {
		return this.tt.getUseUnLimitNum(zyid, startDate, endDate);
	}

	public int getUseLimitNum(String zyid, Date startDate, Date endDate) {
		return this.tt.getUseLimitNum(zyid, startDate, endDate);
	}

	public Date getOrderAtNearStart(String zyid, Date startDate, Date endDate) {
		return this.tt.getOrderAtNearStart(zyid, startDate, endDate);
	}

	public int getDrugNumTheDay(String zyid, Date startDate, Date endDate) {
		return this.tt.getDrugNumTheDay(zyid, startDate, endDate);
	}

	public List<St004Yzxxb> findDrugAddbyZyid(St004Yzxxb st004Yzxxb) {
		return this.tt.findDrugAddbyZyid(st004Yzxxb);
	}

	public List<St004Yzxxb> findForYzAnalyzis(St004Yzxxb st004Yzxxb) {
		return this.tt.findForYzAnalyzis(st004Yzxxb);
	}
}