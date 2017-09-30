package com.nis.bl.dao;

import com.nis.bl.entity.Bl002Sjdj;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Bl002SjdjDao {
	void save(Bl002Sjdj arg0);

	void delete(@Param("blId") String arg0);

	void update(Bl002Sjdj arg0);

	Bl002Sjdj get(@Param("blId") String arg0);

	List<Bl002Sjdj> findBl002Sjdj(Bl002Sjdj arg0);

	int findBl002SjdjCount(Bl002Sjdj arg0);

	List<Bl002Sjdj> getAll();

	List<Map<String, Object>> findzyblryksfb(@Param("startDate") String arg0, @Param("endDate") String arg1);

	List<Map<String, Object>> findzyblfsgwtj(@Param("startDate") String arg0, @Param("endDate") String arg1);

	Bl002Sjdj getMaxBlId();

	List<Map<String, Object>> findMainExposure(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Bl002Sjdj> findFcMessage(@Param("days") String arg0);

	List<Bl002Sjdj> findFcMessageCount(@Param("days") String arg0);
}