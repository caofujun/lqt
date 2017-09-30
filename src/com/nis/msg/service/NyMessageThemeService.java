package com.nis.msg.service;

import com.nis.comm.entity.MyPage;
import com.nis.msg.entity.NyMessageTheme;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface NyMessageThemeService {
	void save(NyMessageTheme arg0);

	void delete(String arg0);

	void update(NyMessageTheme arg0);

	NyMessageTheme get(String arg0);

	MyPage<NyMessageTheme> a(NyMessageTheme arg0);

	List<NyMessageTheme> getAll();

	NyMessageTheme getByZyid(String arg0);

	NyMessageTheme getByMzid(String arg0);

	NyMessageTheme getByCreateUser(String arg0);
}