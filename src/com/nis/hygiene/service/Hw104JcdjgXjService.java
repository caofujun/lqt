package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw104JcdjgXj;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw104JcdjgXjService {
	void save(Hw104JcdjgXj arg0);

	void delete(String arg0);

	void update(Hw104JcdjgXj arg0);

	Hw104JcdjgXj get(String arg0);

	MyPage<Hw104JcdjgXj> a(Hw104JcdjgXj arg0);

	List<Hw104JcdjgXj> getAll();

	void delHw104(List<String> arg0, String arg1);

	void delByHw102Id(String arg0);

	List<String> findPathoIdByHw102Id(String arg0);

	List<Hw104JcdjgXj> findListByHw102Id(String arg0);

	List<String> findPathoNameByHw102Id(String arg0);
}