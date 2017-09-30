package com.nis.yj.service;

import com.nis.comm.entity.MyPage;
import com.nis.yj.entity.Yj003RegularLis;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Yj003RegularLisService {
	void save(Yj003RegularLis arg0);

	void delete(String arg0);

	void update(Yj003RegularLis arg0);

	Yj003RegularLis get(String arg0);

	MyPage<Yj003RegularLis> a(Yj003RegularLis arg0);

	List<Yj003RegularLis> getAll();
}