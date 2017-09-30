package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw101Jcdj;
import com.nis.hygiene.entity.Hw102Jcdmx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw101JcdjDao {
	void save(Hw101Jcdj arg0);

	void delete(@Param("djId") String arg0);

	void update(Hw101Jcdj arg0);

	void updateSpecified(@Param("hw101Jcdj") Hw101Jcdj arg0, @Param("updateAttrs") List<String> arg1);

	Hw101Jcdj get(@Param("djId") String arg0);

	List<Hw101Jcdj> findHw101Jcdj(Hw101Jcdj arg0);

	int findHw101JcdjCount(Hw101Jcdj arg0);

	List<Hw101Jcdj> getAll();

	List<Hw102Jcdmx> findHw101List(Hw102Jcdmx arg0);

	int findHw101ListCount(Hw102Jcdmx arg0);

	void updatePrintFlag(@Param("djId") String arg0);
}