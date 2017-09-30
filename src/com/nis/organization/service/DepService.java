package com.nis.organization.service;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.organization.entity.Dep;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface DepService {
	Result<String> a(Dep arg0);

	List<Dep> getDept();

	void delete(String arg0, String arg1);

	MyPage<Dep> b(Dep arg0);

	Integer c(Dep arg0);

	List<Dep> getAll();

	List<Dep> d(Dep arg0);

	List<Dep> getDepByUnitIdAndDepTypeExt(String arg0, String arg1, String arg2, Long arg3);

	List<Dep> getDepList(Dep arg0);

	List<Dep> findLike(String arg0, String arg1);

	Dep F(String arg0, String arg1);

	String G(String arg0, String arg1);

	String H(String arg0, String arg1);

	Dep get(String arg0);

	String getName(String arg0);

	List<Dep> getByDeptIds(List<String> arg0);

	List<Dep> e(Dep arg0);

	Result<String> f(Dep arg0);

	String getDeptType(String arg0);

	Result<String> k(String arg0, String arg1, String arg2, String arg3);

	Result<String> l(String arg0, String arg1, String arg2, String arg3);

	Result<String> a(String arg0, String arg1, String arg2, String arg3, String[] arg4);

	Result<String> m(String arg0, String arg1, String arg2, String arg3);

	Result<String> n(String arg0, String arg1, String arg2, String arg3);

	Result<String> f(String arg0, String arg1, String arg2, String arg3, String arg4);

	Result<String> g(String arg0, String arg1, String arg2, String arg3, String arg4);

	List<Map<String, String>> getNicuListBack(String arg0, String arg1);

	List<Map<String, Object>> getICUList(String arg0, String arg1);

	List<Map<String, Object>> getXsrList(String arg0);

	void updateYzxxb(String arg0, String arg1, String arg2, Date arg3);

	void updateSjbb(String arg0, String arg1, String arg2, Date arg3);

	void updateSsxxb(String arg0, String arg1, String arg2, Date arg3);

	void updateZkjl(String arg0, String arg1, String arg2, Date arg3);
}