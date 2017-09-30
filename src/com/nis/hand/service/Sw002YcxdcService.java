package com.nis.hand.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.hand.entity.Sw002Ycxdc;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Sw002YcxdcService {
	void save(Sw002Ycxdc arg0);

	Result<String> i(String arg0);

	void update(Sw002Ycxdc arg0);

	Sw002Ycxdc get(String arg0);

	MyPage<Sw002Ycxdc> a(Sw002Ycxdc arg0);

	List<Sw002Ycxdc> getAll();

	Result<Sw002Ycxdc> a(Sw002Ycxdc arg0, AcAccount arg1);
}