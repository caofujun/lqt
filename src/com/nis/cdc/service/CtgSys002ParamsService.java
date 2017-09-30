package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys002Params;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys002ParamsService {
	void save(CtgSys002Params arg0);

	void delete(String arg0);

	void update(CtgSys002Params arg0);

	CtgSys002Params get(String arg0);

	MyPage<CtgSys002Params> a(CtgSys002Params arg0);

	List<CtgSys002Params> getAll();
}