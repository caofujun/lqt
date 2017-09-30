package com.nis.intervene.service;

import com.nis.comm.entity.MyPage;
import com.nis.intervene.entity.FxZhibiao;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface FxZhibiaoService {
	void save(FxZhibiao arg0);

	void delete(String arg0);

	void update(FxZhibiao arg0);

	FxZhibiao get(String arg0);

	MyPage<FxZhibiao> a(FxZhibiao arg0);

	List<FxZhibiao> getAll();

	FxZhibiao getByzbClass(String arg0);
}