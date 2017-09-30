package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys012Pathology;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys012PathologyService {
	void save(CtgSys012Pathology arg0);

	void delete(String arg0);

	void update(CtgSys012Pathology arg0);

	CtgSys012Pathology get(String arg0);

	MyPage<CtgSys012Pathology> a(CtgSys012Pathology arg0);

	List<CtgSys012Pathology> getAll();

	boolean w(String arg0);
}