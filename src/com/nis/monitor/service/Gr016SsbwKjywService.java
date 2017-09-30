package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Gr016SsbwKjyw;
import com.nis.patient.entity.St005Ssxxb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr016SsbwKjywService {
	void save(Gr016SsbwKjyw arg0);

	void delete(String arg0);

	void update(Gr016SsbwKjyw arg0);

	Gr016SsbwKjyw get(String arg0);

	MyPage<Gr016SsbwKjyw> a(Gr016SsbwKjyw arg0);

	List<Gr016SsbwKjyw> getAll();

	void a(St005Ssxxb arg0, Gr016SsbwKjyw arg1);

	List<String> findYzIdByRefid(String arg0, Integer arg1);

	void updateByRefids(List<Gr016SsbwKjyw> arg0);

	void updSs001Zdb(String arg0, String arg1, String arg2);
}