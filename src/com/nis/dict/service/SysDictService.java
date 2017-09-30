package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.SysDict;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysDictService {
	void save(SysDict arg0);

	void delete(String arg0);

	void deleteByTypeCode(String arg0);

	void update(SysDict arg0);

	void updateLastTimebyCode(String arg0, String arg1, Date arg2);

	void z(List<SysDict> arg0);

	SysDict j(String arg0, String arg1, String arg2);

	SysDict get(String arg0);

	MyPage<SysDict> a(SysDict arg0);

	List<SysDict> b(SysDict arg0);

	List<SysDict> getAll(SysDict arg0);

	List<SysDict> c(SysDict arg0);

	List<SysDict> u(String arg0, String arg1);

	List<SysDict> findByDictTypeCode(String arg0, String arg1, String arg2);

	String k(String arg0, String arg1, String arg2);

	String b(String arg0, String arg1, String arg2, String arg3);

	String l(String arg0, String arg1, String arg2);

	List<SysDict> m(String arg0, String arg1, String arg2);

	SysDict getMaxCodeAndSeq(String arg0, String arg1);

	List<SysDict> findTop(String arg0, String arg1);

	List<SysDict> getAllSpecDescribes(SysDict arg0);

	String v(String arg0, String arg1)
			throws UnsupportedEncodingException, InvocationTargetException, ArrayIndexOutOfBoundsException;
}