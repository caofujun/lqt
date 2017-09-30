package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Xn020Gadc;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn020GadcService {
	void save(Xn020Gadc arg0);

	void delete(String arg0);

	void update(Xn020Gadc arg0);

	Xn020Gadc get(String arg0);

	MyPage<Xn020Gadc> a(Xn020Gadc arg0);

	List<Xn020Gadc> getAll();
}