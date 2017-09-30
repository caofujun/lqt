package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.dict.service.SysDictService;
import com.nis.mdr.dao.Xn017TsnyzdDao;
import com.nis.mdr.entity.Xn017Tsnyzd;
import com.nis.mdr.service.Xn017TsnyzdService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn017TsnyzdServiceImpl implements Xn017TsnyzdService {
	@Autowired
	private Xn017TsnyzdDao uk;
	@Autowired
	private SysDictService p;

	public void save(Xn017Tsnyzd xn017Tsnyzd) {
		this.uk.save(xn017Tsnyzd);
	}

	public void delete(Xn017Tsnyzd xn017Tsnyzd) {
		this.uk.delete(xn017Tsnyzd);
	}

	public void update(Xn017Tsnyzd xn017Tsnyzd) {
		if (xn017Tsnyzd.getPathogenId() == null) {
			xn017Tsnyzd.setPathogenId("无");
			xn017Tsnyzd.setPathogenName("无");
		}

		if (xn017Tsnyzd.getDrugId() == null) {
			xn017Tsnyzd.setDrugId("无");
			xn017Tsnyzd.setDrugName("无");
		}

		if (xn017Tsnyzd.getTestresult() == null) {
			xn017Tsnyzd.setTestresult("无");
		}

		this.uk.update(xn017Tsnyzd);
	}

	public Xn017Tsnyzd get(String pathogenId) {
		return this.uk.get(pathogenId);
	}

	public MyPage<Xn017Tsnyzd> a(Xn017Tsnyzd xn017Tsnyzd) {
		int total = this.uk.findXn017TsnyzdCount(xn017Tsnyzd);
		List data = null;
		if (total > 0) {
			data = this.uk.findXn017Tsnyzd(xn017Tsnyzd);
			this.a(data);
		}

		return new MyPage(xn017Tsnyzd.getPage().intValue(), xn017Tsnyzd.getSize().intValue(), total, data);
	}

	public List<Xn017Tsnyzd> a(List<Xn017Tsnyzd> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			Xn017Tsnyzd xn017Tsnyzd = (Xn017Tsnyzd) arg2.next();
			xn017Tsnyzd.setShowTestresult(this.p.k("ymjg", xn017Tsnyzd.getTestresult(), (String) null));
		}

		return data;
	}

	public List<Xn017Tsnyzd> getAll() {
		return this.uk.getAll();
	}

	public List<Xn017Tsnyzd> getKvEntity() {
		return this.uk.getKvEntity();
	}

	public void save2(Xn017Tsnyzd xn017Tsnyzd) {
		this.uk.save2(xn017Tsnyzd);
	}

	public Xn017Tsnyzd getByPathogenIdDrugIdSpecDescribe(Xn017Tsnyzd xn017Tsnyzd) {
		return this.uk.getByPathogenIdDrugIdSpecDescribe(xn017Tsnyzd);
	}

	public List<Xn017Tsnyzd> findTsnyToAna() {
		return this.uk.findTsnyToAna();
	}

	public void deleteBySpecDescribe(String specDescribe) {
		this.uk.deleteBySpecDescribe(specDescribe);
	}
}