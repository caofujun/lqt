package com.nis.comm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface ExecuteService {
	void J(String arg0);

	void a(StringBuffer arg0);

	List<HashMap<String, Object>> K(String arg0);

	List<HashMap<String, Object>> b(StringBuffer arg0);

	Date getDBCurrentDate();
}