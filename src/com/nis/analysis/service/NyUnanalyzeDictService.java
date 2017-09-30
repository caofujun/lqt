package com.nis.analysis.service;

import com.nis.analysis.entity.NyUnanalyzeDict;
import com.nis.comm.entity.MyPage;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface NyUnanalyzeDictService {
	void save(NyUnanalyzeDict arg0);

	void delete(String arg0);

	void update(NyUnanalyzeDict arg0);

	NyUnanalyzeDict get(String arg0);

	Map<String, NyUnanalyzeDict> getUnDictMap();

	MyPage<NyUnanalyzeDict> a(NyUnanalyzeDict arg0);

	List<NyUnanalyzeDict> getAll();
}