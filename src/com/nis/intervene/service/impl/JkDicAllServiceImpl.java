package com.nis.intervene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.intervene.dao.JkDicAllDao;
import com.nis.intervene.entity.JkDicAll;
import com.nis.intervene.service.JkDicAllService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JkDicAllServiceImpl implements JkDicAllService {
	@Autowired
	private JkDicAllDao sO;
	@Autowired
	private SysDictService p;

	public void save(JkDicAll jkDicAll) {
		jkDicAll.setOrderCode(z.a(bg.mM));
		this.sO.save(jkDicAll);
		this.p.updateLastTimebyCode("data_init", "SGYZ", f.getCurDate());
	}

	public void delete(String orderCode) {
		this.sO.delete(orderCode);
		this.p.updateLastTimebyCode("data_init", "SGYZ", f.getCurDate());
	}

	public void update(JkDicAll jkDicAll) {
		this.sO.update(jkDicAll);
		this.p.updateLastTimebyCode("data_init", "SGYZ", f.getCurDate());
	}

	public JkDicAll get(String orderCode) {
		return this.sO.get(orderCode);
	}

	public MyPage<JkDicAll> a(JkDicAll jkDicAll) {
		int total = this.sO.findJkDicAllCount(jkDicAll);
		List data = null;
		if (total > 0) {
			data = this.sO.findJkDicAll(jkDicAll);
		}

		return new MyPage(jkDicAll.getPage().intValue(), jkDicAll.getSize().intValue(), total, data);
	}

	public List<JkDicAll> getAll() {
		return this.sO.getAll();
	}

	public List<JkDicAll> getByClassCode(String classCode) {
		return this.sO.getByClassCode(classCode);
	}

	public List<JkDicAll> findDictType() {
		return this.sO.findDictType();
	}

	public List<JkDicAll> findDict(JkDicAll jkDicAll) {
		return this.sO.findDict(jkDicAll);
	}
}