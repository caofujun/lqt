package com.nis.analysis.service;

import com.nis.analysis.entity.Gr019YsgrmxWeight;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr019YsgrmxWeightService {
	void save(Gr019YsgrmxWeight arg0);

	void a(Gr019YsgrmxWeight arg0);

	void delete(String arg0);

	void update(Gr019YsgrmxWeight arg0);

	MyPage<Gr019YsgrmxWeight> b(Gr019YsgrmxWeight arg0);

	List<Gr019YsgrmxWeight> getAll();
}