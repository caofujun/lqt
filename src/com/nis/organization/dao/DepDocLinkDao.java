package com.nis.organization.dao;

import com.nis.organization.entity.DepDocLink;
import org.apache.ibatis.annotations.Param;

public interface DepDocLinkDao {
	void save(DepDocLink arg0);

	void delete(@Param("doctorId") String arg0);
}