package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn015Trlyjl;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn015TrlyjlService {
	void save(Xn015Trlyjl arg0);

	void update(Xn015Trlyjl arg0);

	Xn015Trlyjl get(String arg0, String arg1, String arg2);

	MyPage<Xn015Trlyjl> a(Xn015Trlyjl arg0);

	List<Xn015Trlyjl> getAll();
}