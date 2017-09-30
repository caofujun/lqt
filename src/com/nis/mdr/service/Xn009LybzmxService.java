package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn009Lybzmx;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn009LybzmxService {
	void save(Xn009Lybzmx arg0);

	void update(Xn009Lybzmx arg0);

	MyPage<Xn009Lybzmx> a(Xn009Lybzmx arg0);

	List<Xn009Lybzmx> getAll();
}