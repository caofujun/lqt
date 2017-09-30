package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys004Dictaddrarea;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys004DictaddrareaService {
	void save(CtgSys004Dictaddrarea arg0);

	void delete(String arg0);

	void update(CtgSys004Dictaddrarea arg0);

	CtgSys004Dictaddrarea get(String arg0);

	MyPage<CtgSys004Dictaddrarea> a(CtgSys004Dictaddrarea arg0);

	List<CtgSys004Dictaddrarea> getAll();

	List<CtgSys004Dictaddrarea> getSheng();

	List<CtgSys004Dictaddrarea> getOther(String arg0);
}