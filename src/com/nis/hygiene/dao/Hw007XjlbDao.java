package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw007Xjlb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw007XjlbDao {
	void save(Hw007Xjlb arg0);

	void delete(@Param("pathoId") String arg0);

	void update(Hw007Xjlb arg0);

	Hw007Xjlb get(@Param("pathoId") String arg0);

	List<Hw007Xjlb> findHw007Xjlb(Hw007Xjlb arg0);

	int findHw007XjlbCount(Hw007Xjlb arg0);

	List<Hw007Xjlb> getAll();

	List<Hw007Xjlb> query(Hw007Xjlb arg0);
}