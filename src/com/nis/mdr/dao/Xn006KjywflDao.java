package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn006Kjywfl;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xn006KjywflDao {
	void save(Xn006Kjywfl arg0);

	void delete(@Param("drugTypeId") String arg0);

	void update(Xn006Kjywfl arg0);

	Xn006Kjywfl get(@Param("drugTypeId") String arg0);

	List<Xn006Kjywfl> findXn006Kjywfl(Xn006Kjywfl arg0);

	int findXn006KjywflCount(Xn006Kjywfl arg0);

	List<Xn006Kjywfl> getAll();
}