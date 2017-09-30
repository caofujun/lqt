package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Gr011Byt;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr011BytService {
	void save(Gr011Byt arg0);

	void delete(Long arg0);

	void update(Gr011Byt arg0);

	Gr011Byt get(Long arg0);

	MyPage<Gr011Byt> a(Gr011Byt arg0);

	List<Gr011Byt> getAll();
}