package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.St004BkCgxx;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St004BkCgxxService {
	void save(St004BkCgxx arg0);

	void delete(String arg0);

	void update(St004BkCgxx arg0);

	St004BkCgxx get(String arg0);

	MyPage<St004BkCgxx> a(St004BkCgxx arg0);

	List<St004BkCgxx> getAll();

	List<St004BkCgxx> query(St004BkCgxx arg0);

	void deleteByRefid(String arg0);

	List<St004BkCgxx> findCgsybyZyid(St004BkCgxx arg0);

	List<St004BkCgxx> findcgsyByRefid(St004BkCgxx arg0);

	List<St004BkCgxx> findCgsyInfo(St004BkCgxx arg0);
}