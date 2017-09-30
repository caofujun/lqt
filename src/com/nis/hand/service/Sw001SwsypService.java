package com.nis.hand.service;

import com.nis.comm.entity.MyPage;
import com.nis.hand.entity.Sw001Swsyp;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Sw001SwsypService {
	void save(Sw001Swsyp arg0);

	void delete(String arg0);

	void update(Sw001Swsyp arg0);

	void updateSpecified(Sw001Swsyp arg0, List<String> arg1);

	Sw001Swsyp get(String arg0);

	MyPage<Sw001Swsyp> a(Sw001Swsyp arg0);

	List<Sw001Swsyp> getAll();

	void b(Sw001Swsyp arg0);

	void c(Sw001Swsyp arg0);
}