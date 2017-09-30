package com.nis.intervene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.intervene.dao.FxZhibiaoDao;
import com.nis.intervene.entity.FxZhibiao;
import com.nis.intervene.service.FxZhibiaoService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FxZhibiaoServiceImpl implements FxZhibiaoService {
	@Autowired
	private FxZhibiaoDao sN;
	@Autowired
	private SysDictService p;

	public void save(FxZhibiao fxZhibiao) {
		fxZhibiao.setZbId(z.a(bg.nq));
		this.sN.save(fxZhibiao);
	}

	public void delete(String zbId) {
		this.sN.delete(zbId);
	}

	public void update(FxZhibiao fxZhibiao) {
		this.sN.update(fxZhibiao);
	}

	public FxZhibiao get(String zbId) {
		return this.sN.get(zbId);
	}

	public MyPage<FxZhibiao> a(FxZhibiao fxZhibiao) {
		int total = this.sN.findFxZhibiaoCount(fxZhibiao);
		List data = null;
		if (total > 0) {
			data = this.sN.findFxZhibiao(fxZhibiao);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				FxZhibiao zhibiao = (FxZhibiao) arg4.next();
				zhibiao.setIsGyName(this.p.k("boolean", zhibiao.getIsGy(), (String) null));
				zhibiao.setZbTypeName(this.p.k("zb_source", zhibiao.getZbType(), (String) null));
			}
		}

		return new MyPage(fxZhibiao.getPage().intValue(), fxZhibiao.getSize().intValue(), total, data);
	}

	public List<FxZhibiao> getAll() {
		return this.sN.getAll();
	}

	public FxZhibiao getByzbClass(String zbClass) {
		return this.sN.getByzbClass(zbClass);
	}
}