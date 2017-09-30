package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw004Cybb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw004CybbService {
	void save(Hw004Cybb arg0);

	void delete(String arg0);

	void update(Hw004Cybb arg0);

	Hw004Cybb get(String arg0);

	MyPage<Hw004Cybb> a(Hw004Cybb arg0);

	List<Hw004Cybb> getAll();

	List<Hw004Cybb> queryList(Hw004Cybb arg0);

	List<Hw004Cybb> findList(Hw004Cybb arg0);

	String findMaxSampleId();

	void updFlag(Hw004Cybb arg0);

	Hw004Cybb getHw004Cybb(String arg0);

	Hw004Cybb isExist(String arg0);
}