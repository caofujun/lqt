package com.nis.hand.service;

import com.nis.comm.entity.MyPage;
import com.nis.hand.entity.Sw003Ycxsj;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Sw003YcxsjService {
	void save(Sw003Ycxsj arg0);

	void delete(String arg0);

	void deleteByDcid(String arg0);

	void update(Sw003Ycxsj arg0);

	Sw003Ycxsj get(String arg0);

	List<Sw003Ycxsj> getByDcid(String arg0);

	MyPage<Sw003Ycxsj> a(Sw003Ycxsj arg0);

	List<Sw003Ycxsj> getAll();
}