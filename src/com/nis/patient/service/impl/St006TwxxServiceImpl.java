package com.nis.patient.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.param.service.SysParamService;
import com.nis.patient.dao.St006TwxxDao;
import com.nis.patient.entity.St006Twxx;
import com.nis.patient.service.St006TwxxService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St006TwxxServiceImpl implements St006TwxxService {
	@Autowired
	private St006TwxxDao tx;
	@Autowired
	private SysParamService j;

	public void save(St006Twxx st006Twxx) {
		this.tx.save(st006Twxx);
	}

	public void delete(String id) {
		this.tx.delete(id);
	}

	public void update(St006Twxx st006Twxx) {
		this.tx.update(st006Twxx);
	}

	public St006Twxx get(String id) {
		return this.tx.get(id);
	}

	public MyPage<St006Twxx> a(St006Twxx st006Twxx) {
		int total = this.tx.findSt006TwxxCount(st006Twxx);
		List data = null;
		if (total > 0) {
			data = this.tx.findSt006Twxx(st006Twxx);
		}

		return new MyPage(st006Twxx.getPage().intValue(), st006Twxx.getSize().intValue(), total, data);
	}

	public List<St006Twxx> findTwxxList(St006Twxx st006Twxx) {
		return this.tx.findTwxxList(st006Twxx);
	}

	public List<St006Twxx> getAll() {
		return this.tx.getAll();
	}

	public St006Twxx getMaxATByZY(String zyid) {
		return this.tx.getMaxATByZY(zyid);
	}

	@SqlLog(p = "院感端首页--发热趋势")
	public List<Map<String, Object>> h(Date startDate, Date endDate) {
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		return this.tx.findMainFever(startDate, endDate, tws);
	}

	public List<St006Twxx> findAbnormalByZyid(String zyid) {
		return this.tx.findAbnormalByZyid(zyid);
	}

	public Date getMonitorPatientTwxxLastAt() {
		return this.tx.getMonitorPatientTwxxLastAt();
	}

	public List<DataWarning> findPatentTwxxWarning(Date queryStartDate, Date queryEndDate) {
		return this.tx.findPatentTwxxWarning(queryStartDate, queryEndDate);
	}

	public int getNumBeforeFever(String zyid, Date recordingAt, Double twValues) {
		return this.tx.getNumBeforeFever(zyid, recordingAt, twValues);
	}

	public void updateAnalFlag(String id, Integer twAnalFlag, Date twAnalDt) {
		this.tx.updateAnalFlag(id, twAnalFlag, twAnalDt);
	}
}