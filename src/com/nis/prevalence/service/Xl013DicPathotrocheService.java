package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl013DicPathotroche;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl013DicPathotrocheService {
	void save(Xl013DicPathotroche arg0);

	void delete(String arg0);

	void update(Xl013DicPathotroche arg0);

	Xl013DicPathotroche get(String arg0);

	MyPage<Xl013DicPathotroche> a(Xl013DicPathotroche arg0);

	List<Xl013DicPathotroche> getAll();
}