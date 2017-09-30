package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw005Cyff;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw005CyffService {
	void save(Hw005Cyff arg0);

	void delete(String arg0);

	void update(Hw005Cyff arg0);

	Hw005Cyff get(String arg0);

	MyPage<Hw005Cyff> a(Hw005Cyff arg0);

	List<Hw005Cyff> getAll();

	List<Hw005Cyff> queryList(Hw005Cyff arg0);

	List<Hw005Cyff> findList(Hw005Cyff arg0);

	String findMaxTakeModeId();

	void updFlag(Hw005Cyff arg0);
}