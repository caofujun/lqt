package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.hygiene.dao.Hw102JcdmxDao;
import com.nis.hygiene.entity.Hw102Jcdmx;
import com.nis.hygiene.entity.Hw103Jcdjg;
import com.nis.hygiene.entity.Hw104JcdjgXj;
import com.nis.hygiene.entity.Hw201Jcdmb;
import com.nis.hygiene.service.Hw006CydsService;
import com.nis.hygiene.service.Hw102JcdmxService;
import com.nis.hygiene.service.Hw103JcdjgService;
import com.nis.hygiene.service.Hw104JcdjgXjService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Hw102JcdmxServiceImpl implements Hw102JcdmxService {
	@Autowired
	private Hw102JcdmxDao rX;
	@Autowired
	private Hw103JcdjgService rG;
	@Autowired
	private Hw104JcdjgXjService rF;
	@Autowired
	private Hw006CydsService rs;

	public void save(Hw102Jcdmx hw102Jcdmx) {
		hw102Jcdmx.setId(z.a(bg.nJ));
		hw102Jcdmx.setReportId(f.c(new Date(), "yyyyMMddHHmmss"));
		if (hw102Jcdmx.getResultFlag() == null) {
			hw102Jcdmx.setResultFlag(Integer.valueOf(-1));
		}

		if (hw102Jcdmx.getType() == null) {
			hw102Jcdmx.setType(Integer.valueOf(1));
		}

		this.rX.save(hw102Jcdmx);
	}

	public void delete(String id) {
		this.rX.delete(id);
	}

	public void update(Hw102Jcdmx hw102Jcdmx) {
		this.rX.update(hw102Jcdmx);
	}

	public void updateSpecified(Hw102Jcdmx hw102Jcdmx, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.rX.updateSpecified(hw102Jcdmx, updateAttrs);
		}

	}

	public Hw102Jcdmx get(String id) {
		return this.rX.get(id);
	}

	public MyPage<Hw102Jcdmx> c(Hw102Jcdmx hw102Jcdmx) {
		int total = this.rX.findHw102JcdmxCount(hw102Jcdmx);
		List data = null;
		if (total > 0) {
			data = this.rX.findHw102Jcdmx(hw102Jcdmx);
		}

		return new MyPage(hw102Jcdmx.getPage().intValue(), hw102Jcdmx.getSize().intValue(), total, data);
	}

	public List<Hw102Jcdmx> getAll() {
		return this.rX.getAll();
	}

	public List<Hw102Jcdmx> findHw102JcdmxByDjId(Hw102Jcdmx hw102Jcdmx) {
		List jcdmxList = this.rX.findHw102JcdmxByDjId(hw102Jcdmx);
		Iterator arg3 = jcdmxList.iterator();

		while (arg3.hasNext()) {
			Hw102Jcdmx jcdmx = (Hw102Jcdmx) arg3.next();
			this.i(jcdmx);
		}

		return jcdmxList;
	}

	public void i(Hw102Jcdmx jcdmx) {
		List hw103List = this.rG.findListByHw102Id(jcdmx.getId(), jcdmx.getResult());
		String resultCriterion = "";
		String condition = "";
		Hw103Jcdjg sumResult;
		if ("1".equals(jcdmx.getPosValue())) {
			for (Iterator avg = hw103List.iterator(); avg
					.hasNext(); condition = condition + ab.aP(sumResult.getResultCondition())
							+ ab.aP(sumResult.getResultCriterion()) + ab.aP(sumResult.getResultUnit()) + ",") {
				sumResult = (Hw103Jcdjg) avg.next();
				if (sumResult.getResult() != null) {
					resultCriterion = resultCriterion + sumResult.getResult() + ",";
				}
			}
		} else {
			Double sumResult1 = Double.valueOf(0.0D);

			Hw103Jcdjg avg1;
			for (Iterator arg6 = hw103List.iterator(); arg6.hasNext(); condition = ab.aP(avg1.getResultCondition())
					+ ab.aP(avg1.getResultCriterion()) + ab.aP(avg1.getResultUnit())) {
				avg1 = (Hw103Jcdjg) arg6.next();
				if (ab.isNotEmpty(avg1.getResult())) {
					sumResult1 = Double.valueOf(sumResult1.doubleValue() + Double.parseDouble(avg1.getResult()));
				}
			}

			Double avg2 = Double.valueOf(sumResult1.doubleValue() / (double) hw103List.size());
			if (Double.isNaN(avg2.doubleValue())) {
				avg2 = Double.valueOf(0.0D);
			}

			resultCriterion = avg2.toString();
		}

		jcdmx.setResultCriterion(resultCriterion);
		jcdmx.setCondition(condition);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void bw(String id) {
		this.delete(id);
		this.rG.delByHw102Id(id);
		this.rF.delByHw102Id(id);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void d(Hw102Jcdmx hw102Jcdmx) {
		if (hw102Jcdmx != null) {
			String hw102Id = hw102Jcdmx.getId();
			this.save(hw102Jcdmx);
			List hw103List = this.rG.findListByHw102Id(hw102Id, (String) null);
			if (hw103List.size() > 0) {
				Iterator hw104JcdjgXj = hw103List.iterator();

				while (hw104JcdjgXj.hasNext()) {
					Hw103Jcdjg hw104List = (Hw103Jcdjg) hw104JcdjgXj.next();
					hw104List.setHw102Id(hw102Jcdmx.getId());
					hw104List.setReportId(hw102Jcdmx.getReportId());
					this.rG.save(hw104List);
				}
			}

			List hw104List1 = this.rF.findListByHw102Id(hw102Id);
			if (hw104List1.size() > 0) {
				Iterator arg5 = hw104List1.iterator();

				while (arg5.hasNext()) {
					Hw104JcdjgXj hw104JcdjgXj1 = (Hw104JcdjgXj) arg5.next();
					hw104JcdjgXj1.setReportId(hw102Jcdmx.getReportId());
					hw104JcdjgXj1.setHw102Id(hw102Jcdmx.getId());
					this.rF.save(hw104JcdjgXj1);
				}
			}
		}

	}

	public List<String> findIdByDjId(String djId) {
		return this.rX.findIdByDjId(djId);
	}

	public Hw102Jcdmx getShowSample(String id) {
		return this.rX.getShowSample(id);
	}

	public void updCheckByDjId(Hw102Jcdmx hw102Jcdmx) {
		this.rX.updCheckByDjId(hw102Jcdmx);
	}

	public List<Hw201Jcdmb> findHw201ByDjId(String djId) {
		return this.rX.findHw201ByDjId(djId);
	}

	public List<Hw102Jcdmx> findJcdmxByDjId(Hw102Jcdmx hw102Jcdmx) {
		return this.rX.findJcdmxByDjId(hw102Jcdmx);
	}
}