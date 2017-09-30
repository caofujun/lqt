package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.TrocheKind;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface TrocheKindService {
	void save(TrocheKind arg0);

	void delete(Long arg0, String arg1);

	void update(TrocheKind arg0);

	List<TrocheKind> a(Long arg0, String arg1);

	TrocheKind get(Long arg0, String arg1);

	MyPage<TrocheKind> a(TrocheKind arg0);

	List<TrocheKind> getAll();
}