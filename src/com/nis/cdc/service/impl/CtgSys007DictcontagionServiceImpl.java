package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys007DictcontagionDao;
import com.nis.cdc.entity.CtgSys007Dictcontagion;
import com.nis.cdc.service.CtgSys007DictcontagionService;
import com.nis.comm.utils.ab;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys007DictcontagionServiceImpl implements CtgSys007DictcontagionService {
	@Autowired
	private CtgSys007DictcontagionDao ei;

	public void save(CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		if (ctgSys007Dictcontagion.getCaninput() == null) {
			ctgSys007Dictcontagion.setCaninput(Long.valueOf(0L));
		}

		if (ctgSys007Dictcontagion.getSexcard() == null) {
			ctgSys007Dictcontagion.setSexcard(Long.valueOf(0L));
		}

		this.ei.save(ctgSys007Dictcontagion);
	}

	public void delete(String diseaseid) {
		this.ei.delete(diseaseid);
	}

	public void update(CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		if (ctgSys007Dictcontagion.getCaninput() == null) {
			ctgSys007Dictcontagion.setCaninput(Long.valueOf(0L));
		}

		if (ctgSys007Dictcontagion.getSexcard() == null) {
			ctgSys007Dictcontagion.setSexcard(Long.valueOf(0L));
		}

		this.ei.update(ctgSys007Dictcontagion);
	}

	public CtgSys007Dictcontagion get(String diseaseid) {
		return this.ei.get(diseaseid);
	}

	public List<CtgSys007Dictcontagion> a(CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		int total = this.ei.findCtgSys007DictcontagionCount(ctgSys007Dictcontagion);
		List data = null;
		if (total > 0) {
			data = this.ei.findCtgSys007Dictcontagion(ctgSys007Dictcontagion);
		}

		return data;
	}

	public List<CtgSys007Dictcontagion> getAll() {
		return this.ei.getAll();
	}

	public List<CtgSys007Dictcontagion> search(String keyword) {
		if (ab.isNotEmpty(keyword)) {
			try {
				keyword = URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException arg2) {
				arg2.printStackTrace();
				keyword = "";
			}
		}

		return this.ei.search(keyword);
	}

	public String getNeedFKDiseasis() {
		return this.ei.getNeedFKDiseasis();
	}

	public List<CtgSys007Dictcontagion> getByParentId(CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		return this.ei.getByParentId(ctgSys007Dictcontagion);
	}

	public String getNeedFKDiseasisByParentId(String parentId) {
		return this.ei.getNeedFKDiseasisByParentId(parentId);
	}

	public String getMDDiseaseId() {
		return this.ei.getMDDiseaseId();
	}

	public List<CtgSys007Dictcontagion> getByIds(String ids) {
		return this.ei.getByIds(ids);
	}

	public Integer getAvailableDiseaseId() {
		return this.ei.getAvailableDiseaseId();
	}

	public List<CtgSys007Dictcontagion> getDiseaseParent() {
		return this.ei.getDiseaseParent();
	}

	public void updateStatus(CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		this.ei.updateStatus(ctgSys007Dictcontagion);
	}

	public List<CtgSys007Dictcontagion> getClassify() {
		List classify = this.ei.getClassify();
		return classify;
	}

	public List<CtgSys007Dictcontagion> getDiseaseParentLevel(CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		return this.ei.getDiseaseParentLevel(ctgSys007Dictcontagion);
	}

	public List<CtgSys007Dictcontagion> b(CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		List classify = this.ei.getClassify();

		for (int i = 0; i < classify.size(); ++i) {
			List parentLevel = this.ei.getDiseaseParentLevel((CtgSys007Dictcontagion) classify.get(i));
			((CtgSys007Dictcontagion) classify.get(i))
					.setDiseaseid(((CtgSys007Dictcontagion) classify.get(i)).getKindid());
			((CtgSys007Dictcontagion) classify.get(i))
					.setDiseasename(((CtgSys007Dictcontagion) classify.get(i)).getKindname());
			((CtgSys007Dictcontagion) classify.get(i)).setIconCls("tree-folder");
			((CtgSys007Dictcontagion) classify.get(i)).setKindid("");

			for (int j = 0; j < parentLevel.size(); ++j) {
				CtgSys007Dictcontagion o = new CtgSys007Dictcontagion();
				o.setParentid(((CtgSys007Dictcontagion) parentLevel.get(j)).getDiseaseid());
				o.setSearchString(ctgSys007Dictcontagion.getSearchString());
				if (ab.isNotEmpty(ctgSys007Dictcontagion.getSearchString())) {
					Map disease = this.ei.getSelfAndSonsByParentId(o);
					if (((BigDecimal) disease.get("SONS")).intValue() > 0) {
						List disease1 = this.ei.getByParentId(o);
						((CtgSys007Dictcontagion) parentLevel.get(j)).setChildren(disease1);
					} else if (((BigDecimal) disease.get("SELF")).intValue() <= 0) {
						parentLevel.remove(j);
						--j;
					}
				} else {
					List arg8 = this.ei.getByParentId(o);
					((CtgSys007Dictcontagion) parentLevel.get(j)).setChildren(arg8);
				}
			}

			((CtgSys007Dictcontagion) classify.get(i)).setChildren(parentLevel);
			if (ab.isNotEmpty(ctgSys007Dictcontagion.getSearchString())
					&& (parentLevel == null || parentLevel.size() <= 0)) {
				classify.remove(i);
				--i;
			}
		}

		return classify;
	}

	public Map<String, Object> getSelfAndSonsByParentId(CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		return this.ei.getSelfAndSonsByParentId(ctgSys007Dictcontagion);
	}

	public void delByParentId(String parentId) {
		this.ei.delByParentId(parentId);
	}
}