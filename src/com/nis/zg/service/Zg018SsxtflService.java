package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg018Ssxtfl;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg018SsxtflService {
	void save(Zg018Ssxtfl arg0);

	void delete(String arg0);

	void update(Zg018Ssxtfl arg0);

	Zg018Ssxtfl get(String arg0);

	MyPage<Zg018Ssxtfl> a(Zg018Ssxtfl arg0);

	List<Zg018Ssxtfl> getAll();
}