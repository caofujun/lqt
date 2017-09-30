package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn018Tsnymx;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn018TsnymxService {
	void save(Xn018Tsnymx arg0);

	void update(Xn018Tsnymx arg0);

	MyPage<Xn018Tsnymx> a(Xn018Tsnymx arg0);

	List<Xn018Tsnymx> getAll();
}