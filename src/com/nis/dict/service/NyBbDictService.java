package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.NyBbDict;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface NyBbDictService {
	void save(NyBbDict arg0);

	void delete(String arg0);

	void update(NyBbDict arg0);

	NyBbDict get(String arg0);

	MyPage<NyBbDict> a(NyBbDict arg0);

	List<NyBbDict> getAll();
}