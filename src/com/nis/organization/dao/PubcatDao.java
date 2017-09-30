package com.nis.organization.dao;

import com.nis.organization.entity.Pubcat;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubcatDao {
	void save(Pubcat arg0);

	void delete(@Param("catid") Long arg0);

	void update(Pubcat arg0);

	Pubcat get(@Param("catid") Long arg0);

	List<Pubcat> findPubcat(Pubcat arg0);

	int findPubcatCount(Pubcat arg0);

	List<Pubcat> getAll();
}