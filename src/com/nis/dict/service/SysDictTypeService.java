package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.SysDictType;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysDictTypeService {
	void save(SysDictType arg0);

	void delete(String arg0);

	void update(SysDictType arg0);

	SysDictType get(String arg0);

	MyPage<SysDictType> a(SysDictType arg0);

	List<SysDictType> getAll(SysDictType arg0);
}