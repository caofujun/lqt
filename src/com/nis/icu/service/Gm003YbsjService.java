package com.nis.icu.service;

import com.nis.comm.entity.MyPage;
import com.nis.icu.entity.Gm003Ybsj;
import com.nis.icu.entity.IcuCount;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface Gm003YbsjService {
	void save(Gm003Ybsj arg0);

	void delete(Date arg0);

	void update(Gm003Ybsj arg0);

	Gm003Ybsj get(Date arg0);

	MyPage<Gm003Ybsj> a(Gm003Ybsj arg0);

	List<Gm003Ybsj> getAll();

	List<IcuCount> c(String arg0, String arg1, String arg2, String arg3);

	List<IcuCount> d(String arg0, String arg1, String arg2, String arg3);

	List<IcuCount> e(String arg0, String arg1, String arg2, String arg3);

	IcuCount f(String arg0, String arg1, String arg2, String arg3);

	HSSFWorkbook g(String arg0, String arg1, String arg2, String arg3);

	HSSFWorkbook h(String arg0, String arg1, String arg2, String arg3);

	List<IcuCount> c(String arg0, String arg1, String arg2, String arg3, String arg4);

	HSSFWorkbook d(String arg0, String arg1, String arg2, String arg3, String arg4);

	MyPage<Gm003Ybsj> b(Gm003Ybsj arg0);

	void kfjcDelete(Gm003Ybsj arg0);

	List<Gm003Ybsj> findkfjcByCreationdate(Gm003Ybsj arg0);

	List<Map<String, Object>> findMainKs(Date arg0, Date arg1);

	List<Map<String, Object>> findMainFx(Date arg0, Date arg1);
}