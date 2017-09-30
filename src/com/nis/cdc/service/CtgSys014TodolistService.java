package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys014Todolist;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys014TodolistService {
	void save(CtgSys014Todolist arg0);

	Result<String> a(CtgSys014Todolist arg0);

	void delete(String arg0);

	Result<String> b(CtgSys014Todolist arg0);

	void update(CtgSys014Todolist arg0);

	CtgSys014Todolist get(String arg0);

	MyPage<CtgSys014Todolist> c(CtgSys014Todolist arg0);

	List<CtgSys014Todolist> getAll();

	List<CtgSys014Todolist> isExist(CtgSys014Todolist arg0);
}