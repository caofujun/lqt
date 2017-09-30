package com.nis.hygiene.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.hygiene.dao.Hw002JsbzDao;
import com.nis.hygiene.dao.Hw201JcdmbDao;
import com.nis.hygiene.entity.Hw002Jsbz;
import com.nis.hygiene.entity.Hw006Cyds;
import com.nis.hygiene.entity.Hw101Jcdj;
import com.nis.hygiene.entity.Hw102Jcdmx;
import com.nis.hygiene.entity.Hw103Jcdjg;
import com.nis.hygiene.entity.Hw201Jcdmb;
import com.nis.hygiene.service.Hw006CydsService;
import com.nis.hygiene.service.Hw008XmsqService;
import com.nis.hygiene.service.Hw101JcdjService;
import com.nis.hygiene.service.Hw102JcdmxService;
import com.nis.hygiene.service.Hw103JcdjgService;
import com.nis.hygiene.service.Hw201JcdmbService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Hw201JcdmbServiceImpl implements Hw201JcdmbService {
	@Autowired
	private Hw201JcdmbDao sa;
	@Autowired
	private Hw102JcdmxService rE;
	@Autowired
	private Hw101JcdjService rD;
	@Autowired
	private Hw002JsbzDao rK;
	@Autowired
	private Hw103JcdjgService rG;
	@Autowired
	private Hw008XmsqService rx;
	@Autowired
	private Hw006CydsService rs;

	public void save(Hw201Jcdmb hw201Jcdmb) {
		hw201Jcdmb.setTempletId(z.a(bg.nK));
		if (hw201Jcdmb.getFlag() == null) {
			hw201Jcdmb.setFlag(Integer.valueOf(1));
		}

		this.sa.save(hw201Jcdmb);
	}

	public void delete(String templetId) {
		this.sa.delete(templetId);
	}

	public void update(Hw201Jcdmb hw201Jcdmb) {
		this.sa.update(hw201Jcdmb);
	}

	public Hw201Jcdmb get(String templetId) {
		return this.sa.get(templetId);
	}

	public MyPage<Hw201Jcdmb> a(Hw201Jcdmb hw201Jcdmb) {
		int total = this.sa.findHw201JcdmbCount(hw201Jcdmb);
		List data = null;
		if (total > 0) {
			data = this.sa.findHw201Jcdmb(hw201Jcdmb);
		}

		return new MyPage(hw201Jcdmb.getPage().intValue(), hw201Jcdmb.getSize().intValue(), total, data);
	}

	public List<Hw201Jcdmb> getAll() {
		return this.sa.getAll();
	}

	public List<Hw201Jcdmb> findTempletList(Hw201Jcdmb hw201Jcdmb) {
		return this.sa.findTempletList(hw201Jcdmb);
	}

	public List<Hw201Jcdmb> findSampleList(Hw201Jcdmb hw201Jcdmb) {
		return this.sa.findSampleList(hw201Jcdmb);
	}

	public void i(String[] templetIds) {
		String[] arg4 = templetIds;
		int arg3 = templetIds.length;

		for (int arg2 = 0; arg2 < arg3; ++arg2) {
			String templetId = arg4[arg2];
			if (StringUtils.isNotBlank(templetId)) {
				this.sa.delete(templetId);
			}
		}

	}

	public int findByTempletName(Hw201Jcdmb hw201Jcdmb) {
		return this.sa.findByTempletName(hw201Jcdmb);
	}

	public void batchInsert(List<Hw201Jcdmb> list) {
		this.sa.batchInsert(list);
	}

	public void b(Hw201Jcdmb hw201Jcdmb) {
		int num = this.findByTempletName(hw201Jcdmb);
		if (num > 0) {
			this.delByTempletName(hw201Jcdmb);
		}

		List list = this.rE.findHw201ByDjId(hw201Jcdmb.getDjId());
		if (list.size() > 0) {
			Iterator arg4 = list.iterator();

			while (arg4.hasNext()) {
				Hw201Jcdmb hw201 = (Hw201Jcdmb) arg4.next();
				hw201.setTempletId(z.a(bg.nK));
				hw201.setTempletName(hw201Jcdmb.getTempletName());
				hw201.setDeptId(hw201Jcdmb.getDeptId());
				hw201.setFlag(Integer.valueOf(1));
			}

			this.sa.batchInsert(list);
		}

	}

	public void delByTempletName(Hw201Jcdmb hw201Jcdmb) {
		this.sa.delByTempletName(hw201Jcdmb);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Hw101Jcdj hw101Jcdj, Hw201Jcdmb hw201Jcdmb, AcAccount account) {
		hw101Jcdj.setCreateAt(hw101Jcdj.getReportAt());
		hw101Jcdj.setCreateBy(hw101Jcdj.getReportBy());
		hw101Jcdj.setTakeAt(hw101Jcdj.getReportAt());
		hw101Jcdj.setTakeBy(hw101Jcdj.getReportBy());
		this.rD.save(hw101Jcdj);
		List hw102List = this.sa.findSampleList(hw201Jcdmb);
		Date date = new Date();
		if (hw102List.size() > 0) {
			Iterator arg6 = hw102List.iterator();

			while (arg6.hasNext()) {
				Hw201Jcdmb hw201 = (Hw201Jcdmb) arg6.next();
				int num = this.rx.judgeResultsPermissions(account.getUsername(), account.getDepNo(),
						hw101Jcdj.getDeptId(), hw201.getClassId().substring(0, 2));
				Hw102Jcdmx hw102Jcdmx = new Hw102Jcdmx();
				hw102Jcdmx.setDjId(hw101Jcdj.getDjId());
				hw102Jcdmx.setClassId(hw201.getClassId());
				hw102Jcdmx.setClassName(hw201.getClassName());
				hw102Jcdmx.setPlaceId(hw201.getPlaceId());
				hw102Jcdmx.setPlaceName(hw201.getPlaceName());
				hw102Jcdmx.setTakeModeId(hw201.getTakeModeId());
				hw102Jcdmx.setTakeModeName(hw201.getTakeModeName());
				hw102Jcdmx.setSampleId(hw201.getSampleId());
				hw102Jcdmx.setSampleName(hw201.getSampleName());
				hw102Jcdmx.setPosId(hw201.getPosId());
				hw102Jcdmx.setPosName(hw201.getPosName());
				hw102Jcdmx.setTakeBy(hw201.getTakeBy());
				hw102Jcdmx.setTakeAt(date);
				hw102Jcdmx.setReportBy(hw101Jcdj.getReportBy());
				hw102Jcdmx.setReportAt(date);
				if (num > 0) {
					hw102Jcdmx.setRecheck("0");
					hw102Jcdmx.setResultPathoNum(Double.valueOf(0.0D));
				}

				hw102Jcdmx.setResultFlag(Integer.valueOf(-1));
				hw102Jcdmx.setTakeType(hw201.getTakeType());
				hw102Jcdmx.setTakeTypeName(hw201.getTakeTypeName());
				hw102Jcdmx.setType(hw101Jcdj.getType());
				this.rE.save(hw102Jcdmx);
				List hw2List = this.rK.findListByClassId(hw102Jcdmx.getClassId());
				Hw006Cyds cyds = this.rs.get(hw102Jcdmx.getPosId());
				ArrayList allList = new ArrayList();
				if (cyds != null && ab.isNotEmpty(cyds.getPosValue())) {
					Integer i = Integer.valueOf(Integer.parseInt(cyds.getPosValue()));

					for (int hw002Jsbz = 0; hw002Jsbz < i.intValue(); ++hw002Jsbz) {
						allList.addAll(hw2List);
					}
				}

				for (int arg15 = 0; arg15 < allList.size(); ++arg15) {
					Hw002Jsbz arg16 = (Hw002Jsbz) allList.get(arg15);
					Hw103Jcdjg hw103Jcdjg = new Hw103Jcdjg();
					hw103Jcdjg.setClassId(hw102Jcdmx.getClassId());
					hw103Jcdjg.setReportId(hw102Jcdmx.getReportId());
					hw103Jcdjg.setResultId(String.valueOf(arg15 + 1));
					hw103Jcdjg.setItemId(arg16.getItemId());
					hw103Jcdjg.setItemName(arg16.getItemName());
					hw103Jcdjg.setResultCondition(arg16.getCondition());
					if (arg16.getCriterion().split("\\|").length > 0) {
						hw103Jcdjg.setResultCriterion(arg16.getCriterion().split("\\|")[0]);
					} else {
						hw103Jcdjg.setResultCriterion(arg16.getCriterion());
					}

					if (ab.isNotEmpty(arg16.getUnit())) {
						if (arg16.getUnit().split("\\|").length > 0) {
							hw103Jcdjg.setResultUnit(arg16.getUnit().split("\\|")[0]);
						} else {
							hw103Jcdjg.setResultUnit(arg16.getUnit());
						}
					}

					hw103Jcdjg.setHw102Id(hw102Jcdmx.getId());
					this.rG.save(hw103Jcdjg);
				}
			}
		}

	}
}