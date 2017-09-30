package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Xk001Scqy;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public interface Xk001ScqyService {
	void save(Xk001Scqy arg0);

	void delete(String arg0);

	void update(Xk001Scqy arg0);

	Xk001Scqy get(String arg0);

	MyPage<Xk001Scqy> a(Xk001Scqy arg0);

	List<Xk001Scqy> getAll(String arg0);

	void setStatus(String arg0, String arg1);

	List<Xk001Scqy> queryQyList(String arg0);

	void a(HttpServletResponse arg0, String arg1);
}