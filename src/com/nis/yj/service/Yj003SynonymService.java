package com.nis.yj.service;

import com.nis.comm.entity.MyPage;
import com.nis.yj.entity.Yj003Synonym;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface Yj003SynonymService {
	void save(Yj003Synonym arg0);

	void delete(String arg0);

	void update(Yj003Synonym arg0);

	void match();

	Map<String, String> queryMatched();

	Yj003Synonym get(String arg0);

	MyPage<Yj003Synonym> a(Yj003Synonym arg0);

	List<Yj003Synonym> getAll();

	MyPage<Yj003Synonym> b(Yj003Synonym arg0);
}