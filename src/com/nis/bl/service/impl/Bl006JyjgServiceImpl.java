package com.nis.bl.service.impl;

import com.nis.bl.dao.Bl006JyjgDao;
import com.nis.bl.entity.Bl004CsDetailinfo;
import com.nis.bl.entity.Bl006Jyjg;
import com.nis.bl.entity.JyjgFc;
import com.nis.bl.service.Bl004CsDetailinfoService;
import com.nis.bl.service.Bl006JyjgService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bl006JyjgServiceImpl implements Bl006JyjgService {
	@Autowired
	private Bl006JyjgDao dd;
	@Autowired
	private Bl004CsDetailinfoService cW;

	public void save(Bl006Jyjg bl006Jyjg) {
		this.dd.save(bl006Jyjg);
	}

	public void delete(String blId) {
		this.dd.delete(blId);
	}

	public void update(Bl006Jyjg bl006Jyjg) {
		this.dd.update(bl006Jyjg);
	}

	public Bl006Jyjg get(String blId, String jyDh, String jyHm) {
		return this.dd.get(blId, jyDh, jyHm);
	}

	public MyPage<Bl006Jyjg> a(Bl006Jyjg bl006Jyjg) {
		int total = this.dd.findBl006JyjgCount(bl006Jyjg);
		List data = null;
		if (total > 0) {
			data = this.dd.findBl006Jyjg(bl006Jyjg);
		}

		return new MyPage(bl006Jyjg.getPage().intValue(), bl006Jyjg.getSize().intValue(), total, data);
	}

	public List<Bl006Jyjg> getAll() {
		return this.dd.getAll();
	}

	public List<Bl006Jyjg> getBl006JyjgList(String blId, String jyDh, String itemName) {
		List data = this.dd.getBl006JyjgList(blId, jyDh, itemName);
		Iterator arg5 = data.iterator();

		while (arg5.hasNext()) {
			Bl006Jyjg jyjg = (Bl006Jyjg) arg5.next();
			if (jyjg.getJyTime() != null) {
				jyjg.setJyTimeStr(f.formatDate(jyjg.getJyTime()));
			}
		}

		return data;
	}

	public void a(String blId, Date date, String djMen, List<Bl004CsDetailinfo> cList) {
		ArrayList jyjgList = new ArrayList();
		this.dd.delete(blId);
		Iterator arg6 = cList.iterator();

		while (arg6.hasNext()) {
			Bl004CsDetailinfo cs = (Bl004CsDetailinfo) arg6.next();
			String itN = cs.getItemName().replace("推荐检测时间", "");
			List cList2 = this.cW.findByItemName(itN + "检验项目");
			Iterator arg10 = cList2.iterator();

			while (arg10.hasNext()) {
				Bl004CsDetailinfo csItem = (Bl004CsDetailinfo) arg10.next();
				if (ab.isNotEmpty(csItem.getCsdName())) {
					Bl006Jyjg jyjg = new Bl006Jyjg();
					jyjg.setBlId(blId);
					jyjg.setJyHm(csItem.getCsdName());
					jyjg.setDjTime(date);
					jyjg.setJyDh(cs.getCsdName());
					jyjg.setDjMen(djMen);
					jyjg.setSfMemo(csItem.getBz());
					jyjg.setFlag(Long.valueOf(0L));
					jyjgList.add(jyjg);
				}
			}
		}

		this.dd.saveList(jyjgList);
	}

	public List<Bl006Jyjg> findByItemName(String blId, String itemName1, String itemName2) {
		return this.dd.findByItemName(blId, itemName1, itemName2);
	}

	public List<JyjgFc> findByTime(String startDate, String endDate) {
		return this.dd.findByTime(startDate, endDate);
	}
}