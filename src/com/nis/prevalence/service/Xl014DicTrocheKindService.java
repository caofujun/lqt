package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl014DicTrocheKind;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl014DicTrocheKindService {
	void save(Xl014DicTrocheKind arg0);

	void delete(Integer arg0, String arg1);

	void update(Xl014DicTrocheKind arg0);

	Xl014DicTrocheKind get(Integer arg0, String arg1);

	MyPage<Xl014DicTrocheKind> a(Xl014DicTrocheKind arg0);

	List<Xl014DicTrocheKind> getAll();
}