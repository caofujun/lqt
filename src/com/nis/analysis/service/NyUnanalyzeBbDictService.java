package com.nis.analysis.service;

import com.nis.analysis.entity.NyUnanalyzeBbDict;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface NyUnanalyzeBbDictService {
	void save(NyUnanalyzeBbDict arg0);

	void delete(String arg0);

	void update(NyUnanalyzeBbDict arg0);

	NyUnanalyzeBbDict get(String arg0);

	MyPage<NyUnanalyzeBbDict> a(NyUnanalyzeBbDict arg0);

	List<NyUnanalyzeBbDict> getAll();
}