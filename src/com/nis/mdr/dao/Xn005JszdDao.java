package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn005Jszd;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xn005JszdDao {
	void save(Xn005Jszd arg0);

	void delete(@Param("bactGenusId") String arg0);

	void update(Xn005Jszd arg0);

	Xn005Jszd get(@Param("bactGenusId") String arg0);

	List<Xn005Jszd> findXn005Jszd(Xn005Jszd arg0);

	int findXn005JszdCount(Xn005Jszd arg0);

	List<Xn005Jszd> getAll();
}