package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.monitor.dao.Gr016SsbwKjywDao;
import com.nis.monitor.entity.Gr016SsbwKjyw;
import com.nis.monitor.service.Gr016SsbwKjywService;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.entity.St005Ssxxb;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St005SsxxbService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr016SsbwKjywServiceImpl implements Gr016SsbwKjywService {
	@Autowired
	private Gr016SsbwKjywDao uL;
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private St004YzxxbService bu;

	public void save(Gr016SsbwKjyw gr016SsbwKjyw) {
		gr016SsbwKjyw.setRelid(z.a(bg.nb));
		this.uL.save(gr016SsbwKjyw);
	}

	public void delete(String relid) {
		this.uL.delete(relid);
	}

	public void update(Gr016SsbwKjyw gr016SsbwKjyw) {
		this.uL.update(gr016SsbwKjyw);
	}

	public Gr016SsbwKjyw get(String relid) {
		return this.uL.get(relid);
	}

	public MyPage<Gr016SsbwKjyw> a(Gr016SsbwKjyw gr016SsbwKjyw) {
		int total = this.uL.findGr016SsbwKjywCount(gr016SsbwKjyw);
		List data = null;
		if (total > 0) {
			data = this.uL.findGr016SsbwKjyw(gr016SsbwKjyw);
		}

		return new MyPage(gr016SsbwKjyw.getPage().intValue(), gr016SsbwKjyw.getSize().intValue(), total, data);
	}

	public List<Gr016SsbwKjyw> getAll() {
		return this.uL.getAll();
	}

	public void a(St005Ssxxb st005Ssxxb, Gr016SsbwKjyw gr016SsbwKjyw) {
		String attrStr = "patientName,sex,age,ageUnit,bedNo,deptName,deptId,inHospAt,outAt,status,inopeDays,bloodSugarLevel,yzjcjb,nutritionCondition,wzbxbjs,bmi,typeBuild,lapseTo,patientAddress,tel,monitorDate,operName,operId,partkindname,opepartkindid,operAt,operLengTime,operAtEnd,opedocName,opedocId,impOpeName,impOpeId,incisionGrade,asa,sqbxbs,ssyszc,skinPrepare,skinMethod,preSkintime,bloodOut,bloodIn,anesDrName,anesDrId,narcKind,operRoom,memo,urgentOpe,continuousOpe,isjt,circuitNurse,scrubNurse,glassOpe,sfsszylg,cysfylg,replant,heal,isdzcz,preYymd,preLhyy,preZqyy,preYyts,isSqyy,szyzjyyewzj,wsqLhyy,wsqZqyy,afterYymd,afterLhyy,afterZqyy,afterYyts,xgFlag,operAtStart,operBw,nnis";
		if ("4".equals("" + st005Ssxxb.getStatus())) {
			attrStr = attrStr + ",lastModDate,lastModUserid,lastLog";
		}

		List updateAttrs = Arrays.asList(attrStr.split(","));
		st005Ssxxb.setXgFlag(Integer.valueOf(1));
		this.bO.a(st005Ssxxb, true);
		this.bO.updateSpecified(st005Ssxxb, updateAttrs);
		ArrayList toUpd = new ArrayList();
		ArrayList toAdd = new ArrayList();
		this.a(toAdd, toUpd, Integer.valueOf(1), gr016SsbwKjyw.getGr16List1(), st005Ssxxb.getRelid());
		this.a(toAdd, toUpd, Integer.valueOf(2), gr016SsbwKjyw.getGr16List2(), st005Ssxxb.getRelid());
		this.a(toAdd, toUpd, Integer.valueOf(3), gr016SsbwKjyw.getGr16List3(), st005Ssxxb.getRelid());
		if (toUpd.size() > 0) {
			this.updateByRefids(toUpd);
		}

		this.updSs001Zdb(st005Ssxxb.getOperName(), st005Ssxxb.getOpepartkindid(), st005Ssxxb.getPartkindname());
		this.updZg011Ss(st005Ssxxb.getZyid(), st005Ssxxb.getRelid());

		for (int i = 0; i < toAdd.size(); ++i) {
			Gr016SsbwKjyw gr016 = (Gr016SsbwKjyw) toAdd.get(i);
			St004Yzxxb st004Yzxxb = null;
			if (StringUtils.isNotBlank(gr016.getYzId())) {
				st004Yzxxb = this.bu.get(gr016.getYzId());
			}

			if (st004Yzxxb != null) {
				gr016.setOrderType(st004Yzxxb.getOrderTypeName());
				gr016.setOrderAt(st004Yzxxb.getOrderAt());
				gr016.setStopAt(st004Yzxxb.getStopAt());
				gr016.setOrderName(st004Yzxxb.getOrderName());
				gr016.setDose(st004Yzxxb.getDosage());
				gr016.setDosageUnit(st004Yzxxb.getDosageUnit());
				gr016.setQty(st004Yzxxb.getQtyDay());
				gr016.setFrequency(st004Yzxxb.getFrequency());
				gr016.setDailyTimes(st004Yzxxb.getMrcs());
				gr016.setAdminRouteName(st004Yzxxb.getAdminRouteName());
				gr016.setExecuteTime(st004Yzxxb.getExecuteTime());
				gr016.setExecuteName(st004Yzxxb.getExecuteName());
				gr016.setMemo(st004Yzxxb.getMemo());
				this.save(gr016);
			}
		}

	}

	private void updZg011Ss(String zyid, String relid) {
		this.uL.updZg011Ss(zyid, relid);
	}

	private void a(List<Gr016SsbwKjyw> toAdd, List<Gr016SsbwKjyw> toUpd, Integer operTypeId,
			List<Gr016SsbwKjyw> gr16List, String refid) {
		List yzIds = this.findYzIdByRefid(refid, operTypeId);
		if (gr16List != null) {
			Iterator arg7 = gr16List.iterator();

			while (arg7.hasNext()) {
				Gr016SsbwKjyw gr016 = (Gr016SsbwKjyw) arg7.next();
				if (yzIds.contains(gr016.getYzId())) {
					toUpd.add(gr016);
				} else {
					gr016.setOperTypeId(operTypeId);
					gr016.setRefid(refid);
					toAdd.add(gr016);
				}
			}
		}

	}

	public List<String> findYzIdByRefid(String refid, Integer operTypeId) {
		return this.uL.findYzIdByRefid(refid, operTypeId);
	}

	public void updateByRefids(List<Gr016SsbwKjyw> gr016List) {
		this.uL.updateByRefids(gr016List);
	}

	public void updSs001Zdb(String operName, String opepartkindid, String partkindname) {
		this.uL.updSs001Zdb(operName, opepartkindid, partkindname);
	}
}