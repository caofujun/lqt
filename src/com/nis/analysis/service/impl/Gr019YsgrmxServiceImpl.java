package com.nis.analysis.service.impl;

import com.nis.analysis.dao.Gr019YsgrmxDao;
import com.nis.analysis.entity.Gr019Ysgrmx;
import com.nis.analysis.service.Gr019YsgrmxService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.param.service.SysParamService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr019YsgrmxServiceImpl implements Gr019YsgrmxService {
	@Autowired
	private Gr019YsgrmxDao cu;
	@Autowired
	private SysParamService cs;

	public void save(Gr019Ysgrmx gr019Ysgrmx) {
		this.cu.save(gr019Ysgrmx);
	}

	public void delete(String zyid) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.cu.delete(zyid, yjVersion);
	}

	public void update(Gr019Ysgrmx gr019Ysgrmx) {
		this.cu.update(gr019Ysgrmx);
	}

	public Gr019Ysgrmx get(String zyid) {
		return this.cu.get(zyid);
	}

	public MyPage<Gr019Ysgrmx> a(Gr019Ysgrmx gr019Ysgrmx) {
		int total = this.cu.findGr019YsgrmxCount(gr019Ysgrmx);
		List data = null;
		if (total > 0) {
			data = this.cu.findGr019Ysgrmx(gr019Ysgrmx);
		}

		return new MyPage(gr019Ysgrmx.getPage().intValue(), gr019Ysgrmx.getSize().intValue(), total, data);
	}

	public List<Gr019Ysgrmx> getAll() {
		return this.cu.getAll();
	}

	public Date getMonitorPatientGrLastAt() {
		return this.cu.getMonitorPatientGrLastAt();
	}

	public void saveList(List<Gr019Ysgrmx> gr019List) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		if (gr019List.size() > 100) {
			List ysgrmxListList = a(gr019List, 100);
			Iterator arg4 = ysgrmxListList.iterator();

			while (arg4.hasNext()) {
				List ysgrmxList = (List) arg4.next();
				this.cu.saveList(ysgrmxList, yjVersion);
			}
		} else {
			this.cu.saveList(gr019List, yjVersion);
		}

	}

	public static List<List<Gr019Ysgrmx>> a(List<Gr019Ysgrmx> targe, int size) {
		ArrayList listArr = new ArrayList();
		int arrSize = targe.size() % size == 0 ? targe.size() / size : targe.size() / size + 1;

		for (int i = 0; i < arrSize; ++i) {
			ArrayList sub = new ArrayList();

			for (int j = i * size; j <= size * (i + 1) - 1; ++j) {
				if (j <= targe.size() - 1) {
					sub.add((Gr019Ysgrmx) targe.get(j));
				}
			}

			listArr.add(sub);
		}

		return listArr;
	}

	public List<Gr019Ysgrmx> getbyZyid(String zyid) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.cu.getbyZyid(zyid, yjVersion);
	}
}