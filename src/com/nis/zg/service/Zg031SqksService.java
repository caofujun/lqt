package com.nis.zg.service;

import com.nis.zg.entity.Zg031Sqks;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg031SqksService {
	void save(Zg031Sqks arg0);

	void delete(Zg031Sqks arg0);

	List<Zg031Sqks> getAll(String arg0);

	int isTableExist(String arg0);

	String getDepts(String arg0);
}