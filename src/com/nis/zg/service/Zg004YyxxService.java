package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg004Yyxx;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg004YyxxService {
	void save(Zg004Yyxx arg0);

	void delete(String arg0);

	void update(Zg004Yyxx arg0);

	Zg004Yyxx get(String arg0);

	MyPage<Zg004Yyxx> a(Zg004Yyxx arg0);

	List<Zg004Yyxx> getAll();
}