package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn004Trny;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public interface Xn004TrnyService {
	void save(Xn004Trny arg0);

	void delete(String arg0, String arg1);

	void update(Xn004Trny arg0);

	Xn004Trny get(String arg0, String arg1);

	MyPage<Xn004Trny> a(Xn004Trny arg0);

	List<Xn004Trny> getAll();

	Set<String> findIdSet();
}