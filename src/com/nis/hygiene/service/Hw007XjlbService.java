package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw007Xjlb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw007XjlbService {
	void save(Hw007Xjlb arg0);

	void delete(String arg0);

	void update(Hw007Xjlb arg0);

	Hw007Xjlb get(String arg0);

	MyPage<Hw007Xjlb> a(Hw007Xjlb arg0);

	List<Hw007Xjlb> getAll();

	List<Hw007Xjlb> query(Hw007Xjlb arg0);
}