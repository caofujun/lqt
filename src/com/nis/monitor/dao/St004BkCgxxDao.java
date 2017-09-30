package com.nis.monitor.dao;

import com.nis.monitor.entity.St004BkCgxx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St004BkCgxxDao {
	void save(St004BkCgxx arg0);

	void delete(@Param("relid") String arg0);

	void update(St004BkCgxx arg0);

	St004BkCgxx get(@Param("relid") String arg0);

	List<St004BkCgxx> findSt004BkCgxx(St004BkCgxx arg0);

	int findSt004BkCgxxCount(St004BkCgxx arg0);

	List<St004BkCgxx> getAll();

	List<St004BkCgxx> query(St004BkCgxx arg0);

	void deleteByRefid(String arg0);

	List<St004BkCgxx> findCgsybyZyid(St004BkCgxx arg0);

	List<St004BkCgxx> findcgsyByRefid(St004BkCgxx arg0);

	List<St004BkCgxx> findCgsyInfo(St004BkCgxx arg0);
}