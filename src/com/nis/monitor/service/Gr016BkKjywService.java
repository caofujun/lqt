package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Gr016BkKjyw;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr016BkKjywService {
	void save(Gr016BkKjyw arg0);

	void delete(String arg0);

	void update(Gr016BkKjyw arg0);

	Gr016BkKjyw get(String arg0);

	MyPage<Gr016BkKjyw> a(Gr016BkKjyw arg0);

	List<Gr016BkKjyw> getAll();

	List<Gr016BkKjyw> query(Gr016BkKjyw arg0);

	void deleteByRefid(String arg0);
}