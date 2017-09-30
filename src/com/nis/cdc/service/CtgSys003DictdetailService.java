package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys003Dictdetail;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys003DictdetailService {
	void save(CtgSys003Dictdetail arg0);

	void delete(String arg0);

	void update(CtgSys003Dictdetail arg0);

	CtgSys003Dictdetail get(String arg0);

	MyPage<CtgSys003Dictdetail> a(CtgSys003Dictdetail arg0);

	List<CtgSys003Dictdetail> getAll();
}