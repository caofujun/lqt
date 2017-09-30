package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.dict.dao.AntibiosisDrugDao;
import com.nis.dict.entity.AntibiosisDrug;
import com.nis.dict.service.AntibiosisDrugService;
import com.nis.dict.service.SysDictService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AntibiosisDrugServiceImpl implements AntibiosisDrugService {
	@Autowired
	private AntibiosisDrugDao qE;
	@Autowired
	private SysDictService p;

	public void save(AntibiosisDrug antibiosisDrug) {
		if (StringUtils.isBlank(antibiosisDrug.getSpCode())) {
			antibiosisDrug.setSpCode(t.aE(antibiosisDrug.getDrugName()));
		}

		antibiosisDrug.setLastAt(f.getCurDate());
		this.qE.save(antibiosisDrug);
	}

	public void delete(String drugId) {
		this.qE.delete(drugId);
	}

	public void update(AntibiosisDrug antibiosisDrug) {
		antibiosisDrug.setLastAt(f.getCurDate());
		this.qE.update(antibiosisDrug);
	}

	public AntibiosisDrug get(String drugId) {
		return this.qE.get(drugId);
	}

	public MyPage<AntibiosisDrug> a(AntibiosisDrug antibiosisDrug) {
		int total = this.qE.findAntibiosisDrugCount(antibiosisDrug);
		List data = null;
		if (total > 0) {
			data = this.qE.findAntibiosisDrug(antibiosisDrug);
			data = this.a(data);
		}

		return new MyPage(antibiosisDrug.getPage().intValue(), antibiosisDrug.getSize().intValue(), total, data);
	}

	private List<AntibiosisDrug> a(List<AntibiosisDrug> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			AntibiosisDrug antibiosisDrug = (AntibiosisDrug) arg2.next();
			if (StringUtils.isNotBlank(antibiosisDrug.getDrugTypeid())) {
				antibiosisDrug.setShowDrugTypeid(
						this.p.k("antibiotic_type", antibiosisDrug.getDrugTypeid().trim(), (String) null));
			}
		}

		return data;
	}

	public List<AntibiosisDrug> getAll() {
		return this.qE.getAll();
	}

	public List<AntibiosisDrug> t(String drugTypeid, String searchString) {
		AntibiosisDrug searchAntibiosisDrug = new AntibiosisDrug();
		searchAntibiosisDrug.setDrugTypeid(drugTypeid);
		searchAntibiosisDrug.setSearchString(searchString);
		return this.qE.findAntibiosisDrug(searchAntibiosisDrug);
	}

	public List<HashMap<String, String>> findKjywyl(AntibiosisDrug antibiosisDrug) {
		return this.qE.findKjywyl(antibiosisDrug);
	}
}