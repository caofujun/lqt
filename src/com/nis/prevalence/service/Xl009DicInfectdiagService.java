package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.TreeEntity;
import com.nis.prevalence.entity.Xl009DicInfectdiag;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl009DicInfectdiagService {
	void save(Xl009DicInfectdiag arg0);

	void delete(String arg0);

	void update(Xl009DicInfectdiag arg0);

	Xl009DicInfectdiag get(String arg0);

	MyPage<Xl009DicInfectdiag> a(Xl009DicInfectdiag arg0);

	List<Xl009DicInfectdiag> getAll();

	List<Xl009DicInfectdiag> b(Xl009DicInfectdiag arg0);

	List<TreeEntity> queryTree();
}