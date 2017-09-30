package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Gr012Ymsy;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr012YmsyService {
	void save(Gr012Ymsy arg0);

	void delete(Long arg0);

	void update(Gr012Ymsy arg0);

	Gr012Ymsy get(Long arg0);

	MyPage<Gr012Ymsy> a(Gr012Ymsy arg0);

	List<Gr012Ymsy> getAll();
}