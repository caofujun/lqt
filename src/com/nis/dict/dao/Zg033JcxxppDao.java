package com.nis.dict.dao;

import com.nis.dict.entity.Zg033Jcxxpp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg033JcxxppDao {
	void save(Zg033Jcxxpp arg0);

	void delete(@Param("id") String arg0);

	void update(Zg033Jcxxpp arg0);

	Zg033Jcxxpp get(@Param("id") String arg0);

	List<Zg033Jcxxpp> findZg033Jcxxpp(Zg033Jcxxpp arg0);

	int findZg033JcxxppCount(Zg033Jcxxpp arg0);

	List<Zg033Jcxxpp> getAll();

	List<Zg033Jcxxpp> findBySjId(@Param("sjId") String arg0);
}