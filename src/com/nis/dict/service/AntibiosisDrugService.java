package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.AntibiosisDrug;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface AntibiosisDrugService {
	void save(AntibiosisDrug arg0);

	void delete(String arg0);

	void update(AntibiosisDrug arg0);

	AntibiosisDrug get(String arg0);

	MyPage<AntibiosisDrug> a(AntibiosisDrug arg0);

	List<AntibiosisDrug> t(String arg0, String arg1);

	List<AntibiosisDrug> getAll();

	List<HashMap<String, String>> findKjywyl(AntibiosisDrug arg0);
}