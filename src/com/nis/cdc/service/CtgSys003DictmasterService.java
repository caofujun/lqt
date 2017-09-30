package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys003Dictmaster;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys003DictmasterService {
	void save(CtgSys003Dictmaster arg0);

	void delete(String arg0);

	void update(CtgSys003Dictmaster arg0);

	CtgSys003Dictmaster get(String arg0);

	MyPage<CtgSys003Dictmaster> a(CtgSys003Dictmaster arg0);

	List<CtgSys003Dictmaster> getAll();
}