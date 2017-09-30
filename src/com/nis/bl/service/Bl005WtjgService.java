package com.nis.bl.service;

import com.nis.bl.entity.Bl005Wtjg;
import com.nis.comm.entity.MyPage;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public interface Bl005WtjgService {
	void save(Bl005Wtjg arg0);

	void delete(String arg0, Long arg1);

	void update(Bl005Wtjg arg0);

	Bl005Wtjg get(String arg0, Long arg1);

	MyPage<Bl005Wtjg> a(Bl005Wtjg arg0);

	List<Bl005Wtjg> getAll();

	Map a(HttpServletRequest arg0);

	List<Bl005Wtjg> findByBlId(String arg0);
}