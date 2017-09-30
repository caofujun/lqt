package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl012DicTroche;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl012DicTrocheService {
	void save(Xl012DicTroche arg0);

	void delete(String arg0);

	void update(Xl012DicTroche arg0);

	Xl012DicTroche get(String arg0);

	MyPage<Xl012DicTroche> a(Xl012DicTroche arg0);

	List<Xl012DicTroche> getAll();
}