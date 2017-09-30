package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Xk002Shdj;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public interface Xk002ShdjService {
	void save(Xk002Shdj arg0);

	void delete(String arg0);

	void update(Xk002Shdj arg0);

	Xk002Shdj get(String arg0);

	MyPage<Xk002Shdj> a(Xk002Shdj arg0);

	List<Xk002Shdj> getAll(String arg0);

	void setStatus(String arg0, String arg1);

	void b(HttpServletResponse arg0, String arg1);
}