package com.nis.yj.service;

import com.nis.comm.entity.MyPage;
import com.nis.yj.entity.Yj003Standard;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Yj003StandardService {
	void save(Yj003Standard arg0);

	void delete(String arg0);

	void update(Yj003Standard arg0);

	Yj003Standard get(String arg0);

	MyPage<Yj003Standard> a(Yj003Standard arg0);

	List<Yj003Standard> getAll();
}