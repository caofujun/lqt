package com.nis.hand.dao;

import com.nis.hand.entity.Sw002Ycxdc;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Sw002YcxdcDao {
	void save(Sw002Ycxdc arg0);

	void delete(@Param("dcId") String arg0);

	void update(Sw002Ycxdc arg0);

	Sw002Ycxdc get(@Param("dcId") String arg0);

	List<Sw002Ycxdc> findSw002Ycxdc(Sw002Ycxdc arg0);

	int findSw002YcxdcCount(Sw002Ycxdc arg0);

	List<Sw002Ycxdc> getAll();
}