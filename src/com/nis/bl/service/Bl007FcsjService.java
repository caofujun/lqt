package com.nis.bl.service;

import com.nis.bl.entity.Bl004CsDetailinfo;
import com.nis.bl.entity.Bl007Fcsj;
import com.nis.bl.entity.JyjgFc;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Bl007FcsjService {
	void save(Bl007Fcsj arg0);

	void delete(String arg0);

	void update(Bl007Fcsj arg0);

	Bl007Fcsj get(String arg0);

	MyPage<Bl007Fcsj> a(Bl007Fcsj arg0);

	List<Bl007Fcsj> findBl007FcsjList(Bl007Fcsj arg0);

	List<Bl007Fcsj> getAll();

	void b(String arg0, List<Bl004CsDetailinfo> arg1);

	List<Bl007Fcsj> findByBlId(String arg0);

	List<JyjgFc> findByTime(String arg0, String arg1);

	void o();

	List<Bl007Fcsj> getFcList(Integer arg0);

	void deleteByBlid(String arg0);
}