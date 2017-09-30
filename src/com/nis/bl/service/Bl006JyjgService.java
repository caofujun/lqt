package com.nis.bl.service;

import com.nis.bl.entity.Bl004CsDetailinfo;
import com.nis.bl.entity.Bl006Jyjg;
import com.nis.bl.entity.JyjgFc;
import com.nis.comm.entity.MyPage;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Bl006JyjgService {
	void save(Bl006Jyjg arg0);

	void delete(String arg0);

	void update(Bl006Jyjg arg0);

	Bl006Jyjg get(String arg0, String arg1, String arg2);

	MyPage<Bl006Jyjg> a(Bl006Jyjg arg0);

	List<Bl006Jyjg> getAll();

	List<Bl006Jyjg> getBl006JyjgList(String arg0, String arg1, String arg2);

	void a(String arg0, Date arg1, String arg2, List<Bl004CsDetailinfo> arg3);

	List<Bl006Jyjg> findByItemName(String arg0, String arg1, String arg2);

	List<JyjgFc> findByTime(String arg0, String arg1);
}