package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw201Jcdmb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw201JcdmbDao {
	void save(Hw201Jcdmb arg0);

	void delete(@Param("templetId") String arg0);

	void update(Hw201Jcdmb arg0);

	Hw201Jcdmb get(@Param("templetId") String arg0);

	List<Hw201Jcdmb> findHw201Jcdmb(Hw201Jcdmb arg0);

	int findHw201JcdmbCount(Hw201Jcdmb arg0);

	List<Hw201Jcdmb> getAll();

	List<Hw201Jcdmb> findTempletList(Hw201Jcdmb arg0);

	List<Hw201Jcdmb> findSampleList(Hw201Jcdmb arg0);

	int findByTempletName(Hw201Jcdmb arg0);

	void delByTempletName(Hw201Jcdmb arg0);

	void batchInsert(List<Hw201Jcdmb> arg0);
}