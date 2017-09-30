package com.nis.bl.service.impl;

import com.nis.bl.dao.Bl004CsDetailinfoDao;
import com.nis.bl.entity.Bl004CsDetailinfo;
import com.nis.bl.entity.Bl007Fcsj;
import com.nis.bl.service.Bl004CsDetailinfoService;
import com.nis.bl.service.Bl006JyjgService;
import com.nis.bl.service.Bl007FcsjService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bl004CsDetailinfoServiceImpl implements Bl004CsDetailinfoService {
	@Autowired
	private Bl004CsDetailinfoDao da;
	@Autowired
	private SysDictService p;
	@Autowired
	private Bl006JyjgService cQ;
	@Autowired
	private Bl007FcsjService cX;

	public void save(Bl004CsDetailinfo bl004CsDetailinfo) {
		if (bl004CsDetailinfo.getLastAt() == null) {
			bl004CsDetailinfo.setLastAt(f.getCurDate());
		}

		this.da.save(bl004CsDetailinfo);
	}

	public void delete(String csmId, String csdId) {
		this.da.delete(csmId, csdId);
	}

	public void update(Bl004CsDetailinfo bl004CsDetailinfo) {
		if (bl004CsDetailinfo.getLastAt() == null) {
			bl004CsDetailinfo.setLastAt(f.getCurDate());
		}

		this.da.update(bl004CsDetailinfo);
	}

	public Bl004CsDetailinfo get(String csmId, String csdId) {
		return this.da.get(csmId, csdId);
	}

	public MyPage<Bl004CsDetailinfo> a(Bl004CsDetailinfo bl004CsDetailinfo) {
		int total = this.da.findBl004CsDetailinfoCount(bl004CsDetailinfo);
		List data = null;
		if (total > 0) {
			data = this.da.findBl004CsDetailinfo(bl004CsDetailinfo);
			data = this.a(data);
		}

		return new MyPage(bl004CsDetailinfo.getPage().intValue(), bl004CsDetailinfo.getSize().intValue(), total, data);
	}

	private List<Bl004CsDetailinfo> a(List<Bl004CsDetailinfo> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			Bl004CsDetailinfo bl004CsDetailinfo = (Bl004CsDetailinfo) arg2.next();
			if (StringUtils.isNotBlank(bl004CsDetailinfo.getCsmId())) {
				bl004CsDetailinfo
						.setCsmName(this.p.k("bl_item_type", bl004CsDetailinfo.getCsmId().trim(), (String) null));
			}
		}

		return data;
	}

	public List<Bl004CsDetailinfo> getAll() {
		return this.da.getAll();
	}

	public List<Bl004CsDetailinfo> b(String blId, String itemNames, String date) {
		String[] itemName = itemNames.split(",");
		ArrayList csList = new ArrayList();
		String[] arg8 = itemName;
		int arg7 = itemName.length;

		for (int arg6 = 0; arg6 < arg7; ++arg6) {
			String itN = arg8[arg6];
			List data = this.da.findByItemName(itN + "推荐检测时间");
			List fcsjList = this.cX.findByBlId(blId);
			Iterator arg12 = data.iterator();

			while (arg12.hasNext()) {
				Bl004CsDetailinfo csDetail = (Bl004CsDetailinfo) arg12.next();
				String isChoose = "1";
				csDetail.setFcDate(f.a(f.k(date, "yyyy-MM-dd HH:mm"), Integer.parseInt(csDetail.getBz())));
				Iterator arg15 = fcsjList.iterator();

				while (arg15.hasNext()) {
					Bl007Fcsj fcsj = (Bl007Fcsj) arg15.next();
					if (csDetail.getCsmId().equals(fcsj.getCsmId()) && csDetail.getCsdId().equals(fcsj.getCsdId())) {
						csDetail.setFcDate(fcsj.getFc());
						isChoose = "0";
					}
				}

				csDetail.setIsChoose(isChoose);
			}

			csList.addAll(data);
		}

		return csList;
	}

	public List<Bl004CsDetailinfo> findByItemName(String itemName) {
		return this.da.findByItemName(itemName);
	}

	public List<Bl004CsDetailinfo> findDetailBycsmId(String csmId) {
		return this.da.findDetailBycsmId(csmId);
	}
}