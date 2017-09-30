package com.nis.outbreak.service;

import com.nis.comm.entity.MyPage;
import com.nis.outbreak.entity.By007Config;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface By007ConfigService {
	void save(By007Config arg0);

	void delete(String arg0);

	void update(By007Config arg0);

	By007Config get(String arg0);

	MyPage<By007Config> a(By007Config arg0);

	List<By007Config> getAll();

	By007Config getByShowId(String arg0);

	void batchUpdAbsolute(List<By007Config> arg0);

	void batchUpdRelative(List<By007Config> arg0);

	void L(List<String> arg0);
}