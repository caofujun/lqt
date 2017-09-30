package com.nis.cdc.service;

import com.nis.cdc.entity.CtgBk003Frbk;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk003FrbkService {
	void save(CtgBk003Frbk arg0);

	void delete(String arg0);

	void update(CtgBk003Frbk arg0);

	CtgBk003Frbk get(String arg0);

	MyPage<CtgBk003Frbk> a(CtgBk003Frbk arg0);

	List<CtgBk003Frbk> getAll();
}