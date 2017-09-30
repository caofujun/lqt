package com.nis.bl.service.impl;

import com.nis.bl.dao.Bl005WtjgDao;
import com.nis.bl.entity.Bl005Wtjg;
import com.nis.bl.service.Bl005WtjgService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bl005WtjgServiceImpl implements Bl005WtjgService {
	@Autowired
	private Bl005WtjgDao dc;

	public void save(Bl005Wtjg bl005Wtjg) {
		this.dc.save(bl005Wtjg);
	}

	public void delete(String blId, Long stId) {
		this.dc.delete(blId, stId);
	}

	public void update(Bl005Wtjg bl005Wtjg) {
		this.dc.update(bl005Wtjg);
	}

	public Bl005Wtjg get(String blId, Long stId) {
		return this.dc.get(blId, stId);
	}

	public MyPage<Bl005Wtjg> a(Bl005Wtjg bl005Wtjg) {
		int total = this.dc.findBl005WtjgCount(bl005Wtjg);
		List data = null;
		if (total > 0) {
			data = this.dc.findBl005Wtjg(bl005Wtjg);
		}

		return new MyPage(bl005Wtjg.getPage().intValue(), bl005Wtjg.getSize().intValue(), total, data);
	}

	public List<Bl005Wtjg> getAll() {
		return this.dc.getAll();
	}

	public Map a(HttpServletRequest request) {
		HashMap map = new HashMap();
		Enumeration paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			String paramValue;
			if (paramValues.length == 1) {
				paramValue = paramValues[0];
				map.put(paramName, paramValue);
			} else if (paramValues.length > 1) {
				paramValue = ab.g(paramValues);
				map.put(paramName, paramValue);
			}
		}

		return map;
	}

	public List<Bl005Wtjg> findByBlId(String blId) {
		return this.dc.findByBlId(blId);
	}
}