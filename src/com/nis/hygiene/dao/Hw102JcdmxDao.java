package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw102Jcdmx;
import com.nis.hygiene.entity.Hw201Jcdmb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw102JcdmxDao {
	void save(Hw102Jcdmx arg0);

	void delete(@Param("id") String arg0);

	void update(Hw102Jcdmx arg0);

	void updateSpecified(@Param("hw102Jcdmx") Hw102Jcdmx arg0, @Param("updateAttrs") List<String> arg1);

	Hw102Jcdmx get(@Param("id") String arg0);

	List<Hw102Jcdmx> findHw102Jcdmx(Hw102Jcdmx arg0);

	int findHw102JcdmxCount(Hw102Jcdmx arg0);

	List<Hw102Jcdmx> getAll();

	List<Hw102Jcdmx> findHw102JcdmxByDjId(Hw102Jcdmx arg0);

	List<String> findIdByDjId(@Param("djId") String arg0);

	Hw102Jcdmx getShowSample(@Param("id") String arg0);

	List<Hw201Jcdmb> findHw201ByDjId(@Param("djId") String arg0);

	void updCheckByDjId(Hw102Jcdmx arg0);

	List<Hw102Jcdmx> findJcdmxByDjId(Hw102Jcdmx arg0);
}