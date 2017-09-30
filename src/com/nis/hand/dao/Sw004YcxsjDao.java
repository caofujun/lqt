package com.nis.hand.dao;

import com.nis.hand.entity.Sw004Ycxsj;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Sw004YcxsjDao {
	void save(Sw004Ycxsj arg0);

	void delete(@Param("keyId") String arg0);

	void update(Sw004Ycxsj arg0);

	Sw004Ycxsj get(@Param("keyId") String arg0);

	List<Sw004Ycxsj> findSw004Ycxsj(Sw004Ycxsj arg0);

	int findSw004YcxsjCount(Sw004Ycxsj arg0);

	List<Sw004Ycxsj> getAll();

	void deleteByDcid(@Param("dcId") String arg0);

	List<Sw004Ycxsj> getByDcid(@Param("dcId") String arg0);
}