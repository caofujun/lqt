package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.monitor.dao.Gr018YsgrysDao;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St004Yzxxb;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr018YsgrysServiceImpl implements Gr018YsgrysService {
	@Autowired
	private Gr018YsgrysDao uM;
	@Autowired
	private SysParamService j;

	public void save(Gr018Ysgrys gr018Ysgrys) {
		gr018Ysgrys.setTablename(this.j.findByParamCode(Param.NIS_YJ_IS_VERSION));
		this.uM.save(gr018Ysgrys);
	}

	public void batchInsert(List<Gr018Ysgrys> gr018YsgrysList) {
		if (gr018YsgrysList != null && gr018YsgrysList.size() > 0) {
			String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
			this.uM.batchInsert(gr018YsgrysList, yjVersion);
		}

	}

	public void delete(String id) {
		this.uM.delete(id);
	}

	public void update(Gr018Ysgrys gr018Ysgrys) {
		this.uM.update(gr018Ysgrys);
	}

	public void K(List<Gr018Ysgrys> gr018YsgrysList) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.uM.updateList(gr018YsgrysList, yjVersion);
	}

	public Gr018Ysgrys bP(String id) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.uM.get(id, yjVersion);
	}

	public MyPage<Gr018Ysgrys> a(Gr018Ysgrys gr018Ysgrys) {
		int total = this.uM.findGr018YsgrysCount(gr018Ysgrys);
		List data = null;
		if (total > 0) {
			data = this.uM.findGr018Ysgrys(gr018Ysgrys);
		}

		return new MyPage(gr018Ysgrys.getPage().intValue(), gr018Ysgrys.getSize().intValue(), total, data);
	}

	public List<Gr018Ysgrys> getAll() {
		return this.uM.getAll();
	}

	public List<Gr018Ysgrys> findListByZyid(String zyid) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		String isPacs = this.j.findByParamCode(Param.NIS_YJ_IS_PACS);
		return this.uM.findListByZyid(zyid, yjVersion, isPacs);
	}

	public List<Gr018Ysgrys> findByFX(Gr018Ysgrys gr018Ysgrys) {
		return this.uM.findByFX(gr018Ysgrys);
	}

	public void saveList(List<Gr018Ysgrys> gr018YsgrysList) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.uM.saveList(gr018YsgrysList, yjVersion);
	}

	public List<Gr018Ysgrys> E(String zyid, String dataDate) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.uM.findListByDataDate(zyid, dataDate, yjVersion);
	}

	public void bQ(String zyid) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.uM.deleteGrysByZyid(zyid, yjVersion);
	}

	public void bO(String zyid) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.uM.updateByZyid(zyid, yjVersion);
	}

	public List<Gr018Ysgrys> findByState() {
		List gr018List = this.uM.findByState();
		return gr018List;
	}

	public List<Gr018Ysgrys> findByZyid(String zyid, String dataDate) {
		return this.uM.findByZyid(zyid, dataDate);
	}

	public Date getMonitorPatientBcLastAt() {
		return this.uM.getMonitorPatientBcLastAt();
	}

	public void b(String zyid, Integer state) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.uM.updateStateByZyid(zyid, state, yjVersion);
	}

	public int findByStateCount(Integer state) {
		return this.uM.findByStateCount(state);
	}

	public int a(String zyid, Date dataDate, String elementId, String dataForm) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.uM.findcountByKey(zyid, dataDate, elementId, dataForm, yjVersion);
	}

	public St004Yzxxb c(String zyid, Date orderAt) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.uM.getLastUseKjyw(zyid, orderAt, yjVersion);
	}

	public List<String> ac() {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		String isPacs = this.j.findByParamCode(Param.NIS_YJ_IS_PACS);
		return this.uM.findZyidByState(yjVersion, isPacs);
	}

	public int ad() {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		String isPacs = this.j.findByParamCode(Param.NIS_YJ_IS_PACS);
		return this.uM.findZyidByStateCount(yjVersion, isPacs);
	}

	public int d(String zyid, Date dataDate) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.uM.getUnionDrugNum(zyid, dataDate, yjVersion);
	}

	public List<Gr018Ysgrys> findTwList(Double tw) {
		return this.uM.findTwList(tw);
	}

	public int findTwCount(Double tw) {
		return this.uM.findTwCount(tw);
	}
}