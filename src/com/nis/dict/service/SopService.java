package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.Sop;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SopService {
	void save(Sop arg0);

	void delete(String arg0);

	void update(Sop arg0);

	Sop get(String arg0);

	MyPage<Sop> a(Sop arg0);

	List<Sop> getAll();
}