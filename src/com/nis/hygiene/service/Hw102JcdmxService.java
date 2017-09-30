package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw102Jcdmx;
import com.nis.hygiene.entity.Hw201Jcdmb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw102JcdmxService {
	void save(Hw102Jcdmx arg0);

	void delete(String arg0);

	void update(Hw102Jcdmx arg0);

	void updateSpecified(Hw102Jcdmx arg0, List<String> arg1);

	Hw102Jcdmx get(String arg0);

	MyPage<Hw102Jcdmx> c(Hw102Jcdmx arg0);

	List<Hw102Jcdmx> getAll();

	List<Hw102Jcdmx> findHw102JcdmxByDjId(Hw102Jcdmx arg0);

	void bw(String arg0);

	void d(Hw102Jcdmx arg0);

	List<String> findIdByDjId(String arg0);

	Hw102Jcdmx getShowSample(String arg0);

	void updCheckByDjId(Hw102Jcdmx arg0);

	List<Hw201Jcdmb> findHw201ByDjId(String arg0);

	List<Hw102Jcdmx> findJcdmxByDjId(Hw102Jcdmx arg0);
}