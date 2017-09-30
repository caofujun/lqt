package com.nis.hand.dao;

import com.nis.hand.entity.Sw003Ycxsj;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Sw003YcxsjDao {
	void save(Sw003Ycxsj arg0);

	void delete(@Param("sjId") String arg0);

	void deleteByDcid(@Param("dcId") String arg0);

	void update(Sw003Ycxsj arg0);

	Sw003Ycxsj get(@Param("sjId") String arg0);

	List<Sw003Ycxsj> getByDcid(@Param("dcId") String arg0);

	List<Sw003Ycxsj> findSw003Ycxsj(Sw003Ycxsj arg0);

	int findSw003YcxsjCount(Sw003Ycxsj arg0);

	List<Sw003Ycxsj> getAll();
}