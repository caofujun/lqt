package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl001Brxx;
import com.nis.prevalence.entity.Xl002Grxx;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface Xl001BrxxService {
	void save(Xl001Brxx arg0);

	void delete(String arg0);

	void update(Xl001Brxx arg0);

	void updateSpecified(Xl001Brxx arg0, List<String> arg1);

	Xl001Brxx get(String arg0);

	MyPage<Xl001Brxx> a(Xl001Brxx arg0);

	List<Xl001Brxx> getAll();

	void a(Xl001Brxx arg0, List<Xl002Grxx> arg1, List<Xl002Grxx> arg2, List<String> arg3, List<String> arg4,
			List<String> arg5);

	void a(Xl001Brxx arg0, List<Xl002Grxx> arg1, List<Xl002Grxx> arg2);

	Map<String, Object> cw(String arg0);

	MyPage<Xl001Brxx> b(Xl001Brxx arg0);

	Xl001Brxx c(Xl001Brxx arg0);

	void N(List<String> arg0);

	String A(Date arg0);

	List<Xl001Brxx> findVoteDateList();

	MyPage<Xl001Brxx> d(Xl001Brxx arg0);
}