package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.monitor.dao.Bk003YgysDao;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Bk003Ygys;
import com.nis.monitor.service.Bk003YgysService;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bk003YgysServiceImpl implements Bk003YgysService {
	@Autowired
	private Bk003YgysDao uE;
	@Autowired
	private SysDictService p;

	public void save(Bk003Ygys bk003Ygys) {
		bk003Ygys.setId(z.a(bg.mV));
		if (bk003Ygys.getIsFlag() == null) {
			bk003Ygys.setIsFlag(Integer.valueOf(1));
		}

		if (ab.isEmpty(bk003Ygys.getFactorName())) {
			bk003Ygys.setFactorName(this.p.k("susceptible_factor", bk003Ygys.getFactorId(), (String) null));
		}

		this.uE.save(bk003Ygys);
	}

	public void delete(String id) {
		this.uE.delete(id);
	}

	public void update(Bk003Ygys bk003Ygys) {
		this.uE.update(bk003Ygys);
	}

	public Bk003Ygys get(String id) {
		return this.uE.get(id);
	}

	public MyPage<Bk003Ygys> a(Bk003Ygys bk003Ygys) {
		int total = this.uE.findBk003YgysCount(bk003Ygys);
		List data = null;
		if (total > 0) {
			data = this.uE.findBk003Ygys(bk003Ygys);
		}

		return new MyPage(bk003Ygys.getPage().intValue(), bk003Ygys.getSize().intValue(), total, data);
	}

	public List<Bk003Ygys> findBk003Ygys(Bk003Ygys bk003Ygys) {
		return this.uE.findBk003Ygys(bk003Ygys);
	}

	public List<Bk003Ygys> findBk003YgysList(Bk003Ygys bk003Ygys) {
		return this.uE.findBk003YgysList(bk003Ygys);
	}

	public int findBk003YgysCount(Bk003Ygys bk003Ygys) {
		return this.uE.findBk003YgysCount(bk003Ygys);
	}

	public List<Bk003Ygys> getAll() {
		return this.uE.getAll();
	}

	public List<Bk003Ygys> findSusceptFactors(Bk003Ygys bk003Ygys) {
		return this.uE.findSusceptFactors(bk003Ygys);
	}

	public void b(Bk003Ygys bk003Ygys) {
		if (ab.isNotEmpty(bk003Ygys.getFactorId()) && this.uE.findByRefidAndFactorId(bk003Ygys) == null) {
			this.save(bk003Ygys);
		}

	}

	public void delSusceptFactors(Bk003Ygys bk003Ygys) {
		this.uE.delSusceptFactors(bk003Ygys);
	}

	public void a(Bk002Grzd bk002Grzd, String[] factorIds) {
		List factorIdList = null;
		if (factorIds != null && factorIds.length > 0) {
			factorIdList = Arrays.asList(factorIds);
		}

		Bk003Ygys bk003Ygys = new Bk003Ygys();
		bk003Ygys.setRefid(bk002Grzd.getRelid());
		bk003Ygys.setFactorIdIn(factorIdList);
		bk003Ygys.setCardRelid(bk002Grzd.getRefid());
		this.delSusceptFactors(bk003Ygys);
		if (factorIdList != null) {
			Iterator arg5 = factorIdList.iterator();

			while (arg5.hasNext()) {
				String factorId = (String) arg5.next();
				bk003Ygys.setFactorId(factorId);
				bk003Ygys.setFactorName((String) null);
				this.b(bk003Ygys);
			}
		}

	}

	public void delByRefid(String refid) {
		this.uE.delByRefid(refid);
	}

	public void b(Bk002Grzd bk002Grzd, String[] factorIds) {
		if (factorIds != null && factorIds.length > 0) {
			Bk003Ygys bk003Ygys = new Bk003Ygys();
			bk003Ygys.setRefid(bk002Grzd.getRelid());
			bk003Ygys.setCardRelid(bk002Grzd.getRefid());
			String[] arg6 = factorIds;
			int arg5 = factorIds.length;

			for (int arg4 = 0; arg4 < arg5; ++arg4) {
				String factorId = arg6[arg4];
				if (StringUtils.isNotBlank(factorId)) {
					bk003Ygys.setFactorId(factorId);
					bk003Ygys.setFactorName((String) null);
					this.save(bk003Ygys);
				}
			}
		}

	}
}