package com.nis.hand.service;

import com.nis.comm.entity.MyPage;
import com.nis.hand.entity.Sw004Ycxsj;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Sw004YcxsjService {
	void save(Sw004Ycxsj arg0);

	void delete(String arg0);

	void update(Sw004Ycxsj arg0);

	Sw004Ycxsj get(String arg0);

	List<Sw004Ycxsj> getByDcid(String arg0);

	MyPage<Sw004Ycxsj> a(Sw004Ycxsj arg0);

	List<Sw004Ycxsj> getAll();
}