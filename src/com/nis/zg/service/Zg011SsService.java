package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg011Ss;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg011SsService {
	void save(Zg011Ss arg0);

	void delete(String arg0);

	void update(Zg011Ss arg0);

	Zg011Ss get(String arg0);

	Zg011Ss getByOperId(String arg0);

	MyPage<Zg011Ss> a(Zg011Ss arg0);

	List<Zg011Ss> getAll();

	List<Zg011Ss> getOperaInfo(String arg0);

	List<Zg011Ss> query(Zg011Ss arg0);
}