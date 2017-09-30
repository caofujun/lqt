package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw103Jcdjg;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw103JcdjgService {
	void save(Hw103Jcdjg arg0);

	void delete(String arg0);

	void update(Hw103Jcdjg arg0);

	void updateSpecified(Hw103Jcdjg arg0, List<String> arg1);

	Hw103Jcdjg get(String arg0);

	MyPage<Hw103Jcdjg> a(Hw103Jcdjg arg0);

	List<Hw103Jcdjg> getAll();

	List<Hw103Jcdjg> findListByHw102Id(String arg0, String arg1);

	void delByHw102Id(String arg0);
}