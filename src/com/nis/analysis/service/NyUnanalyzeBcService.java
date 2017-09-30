package com.nis.analysis.service;

import com.nis.analysis.entity.NyUnanalyzeBc;
import com.nis.comm.entity.MyPage;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface NyUnanalyzeBcService {
	void save(NyUnanalyzeBc arg0);

	void delete(String arg0);

	void update(NyUnanalyzeBc arg0);

	NyUnanalyzeBc get(String arg0);

	Map<String, NyUnanalyzeBc> getUnBcMap();

	MyPage<NyUnanalyzeBc> a(NyUnanalyzeBc arg0);

	List<NyUnanalyzeBc> getAll();
}