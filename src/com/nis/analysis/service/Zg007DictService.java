package com.nis.analysis.service;

import com.nis.analysis.entity.Zg007Dict;
import com.nis.comm.entity.MyPage;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface Zg007DictService {
	void save(Zg007Dict arg0);

	void delete(String arg0);

	void update(Zg007Dict arg0);

	Zg007Dict get(String arg0);

	Zg007Dict getByElementId(String arg0);

	MyPage<Zg007Dict> a(Zg007Dict arg0);

	List<Zg007Dict> getAll();

	List<Zg007Dict> getByClass(String arg0, String arg1);

	boolean h();

	Map<String, List<Zg007Dict>> f(String arg0, String arg1);

	List<Zg007Dict> g(List<Zg007Dict> arg0);

	void i();

	Map<String, Zg007Dict> g(String arg0, String arg1);
}