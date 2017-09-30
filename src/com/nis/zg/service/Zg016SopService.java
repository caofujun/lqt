package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg016Sop;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg016SopService {
	void save(Zg016Sop arg0);

	void delete(String arg0);

	void update(Zg016Sop arg0);

	Zg016Sop get(String arg0);

	MyPage<Zg016Sop> a(Zg016Sop arg0);

	List<Zg016Sop> getAll();
}