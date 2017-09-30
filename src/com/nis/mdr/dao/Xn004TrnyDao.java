package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn004Trny;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface Xn004TrnyDao {
	void save(Xn004Trny arg0);

	void delete(@Param("pathogenId") String arg0, @Param("drugId") String arg1);

	void update(Xn004Trny arg0);

	Xn004Trny get(@Param("pathogenId") String arg0, @Param("drugId") String arg1);

	List<Xn004Trny> findXn004Trny(Xn004Trny arg0);

	int findXn004TrnyCount(Xn004Trny arg0);

	List<Xn004Trny> getAll();

	Set<String> findIdSet();
}