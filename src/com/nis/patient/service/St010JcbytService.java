package com.nis.patient.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St010Jcbyt;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St010JcbytService {
	void save(St010Jcbyt arg0);

	void delete(String arg0);

	void update(St010Jcbyt arg0);

	void batchUpdAnalFlag(List<St010Jcbyt> arg0);

	void updAnalFlag(St010Jcbyt arg0);

	void updAnalDt(St010Jcbyt arg0);

	St010Jcbyt get(String arg0);

	MyPage<St010Jcbyt> a(St010Jcbyt arg0);

	List<St010Jcbyt> getAll();

	List<St010Jcbyt> findSt010JcbytList(St010Jcbyt arg0);

	List<St010Jcbyt> queryForYJ(String arg0, String arg1);

	List<St010Jcbyt> t(String arg0, String arg1, String arg2);

	List<DataWarning> findPatentJcbytWarning(Date arg0, Date arg1);

	List<St010Jcbyt> findByZyidTestNo(String arg0, String arg1);
}