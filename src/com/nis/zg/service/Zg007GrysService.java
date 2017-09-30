package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg007Grys;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg007GrysService {
	void save(Zg007Grys arg0);

	void delete(String arg0);

	void update(Zg007Grys arg0);

	Zg007Grys get(String arg0);

	MyPage<Zg007Grys> a(Zg007Grys arg0);

	List<Zg007Grys> getAll();

	List<Zg007Grys> findByElementIdLike(String arg0);
}