package com.nis.organization.service;

import com.nis.comm.entity.MyPage;
import com.nis.organization.entity.StandDept;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface StandDeptService {
	void save(StandDept arg0);

	void delete(String arg0);

	void update(StandDept arg0);

	StandDept get(String arg0);

	MyPage<StandDept> a(StandDept arg0);

	List<StandDept> getAll();

	List<StandDept> getStandDept(StandDept arg0);
}