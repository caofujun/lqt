package com.nis.analysis.service;

import com.nis.analysis.entity.NyUnanalyzeBbByt;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface NyUnanalyzeBbBytService {
	void save(NyUnanalyzeBbByt arg0);

	void delete(String arg0);

	void update(NyUnanalyzeBbByt arg0);

	NyUnanalyzeBbByt get(String arg0);

	MyPage<NyUnanalyzeBbByt> a(NyUnanalyzeBbByt arg0);

	List<NyUnanalyzeBbByt> getAll();
}