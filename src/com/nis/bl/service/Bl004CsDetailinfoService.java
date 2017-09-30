package com.nis.bl.service;

import com.nis.bl.entity.Bl004CsDetailinfo;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Bl004CsDetailinfoService {
	void save(Bl004CsDetailinfo arg0);

	void delete(String arg0, String arg1);

	void update(Bl004CsDetailinfo arg0);

	Bl004CsDetailinfo get(String arg0, String arg1);

	MyPage<Bl004CsDetailinfo> a(Bl004CsDetailinfo arg0);

	List<Bl004CsDetailinfo> getAll();

	List<Bl004CsDetailinfo> b(String arg0, String arg1, String arg2);

	List<Bl004CsDetailinfo> findByItemName(String arg0);

	List<Bl004CsDetailinfo> findDetailBycsmId(String arg0);
}