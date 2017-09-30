package com.nis.organization.service;

import com.nis.comm.entity.MyPage;
import com.nis.organization.entity.DeptType;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface DeptTypeService {
	void save(DeptType arg0);

	void delete(String arg0);

	void update(DeptType arg0);

	DeptType get(String arg0);

	MyPage<DeptType> a(DeptType arg0);

	List<DeptType> getAll();
}