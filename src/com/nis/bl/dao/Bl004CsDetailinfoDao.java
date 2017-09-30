package com.nis.bl.dao;

import com.nis.bl.entity.Bl004CsDetailinfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Bl004CsDetailinfoDao {
	void save(Bl004CsDetailinfo arg0);

	void delete(@Param("csmId") String arg0, @Param("csdId") String arg1);

	void update(Bl004CsDetailinfo arg0);

	Bl004CsDetailinfo get(@Param("csmId") String arg0, @Param("csdId") String arg1);

	List<Bl004CsDetailinfo> findBl004CsDetailinfo(Bl004CsDetailinfo arg0);

	int findBl004CsDetailinfoCount(Bl004CsDetailinfo arg0);

	List<Bl004CsDetailinfo> getAll();

	List<Bl004CsDetailinfo> findBycsmId(@Param("csmId") String[] arg0);

	List<Bl004CsDetailinfo> findByItemName(@Param("itemName") String arg0);

	List<Bl004CsDetailinfo> findDetailBycsmId(@Param("csmId") String arg0);
}