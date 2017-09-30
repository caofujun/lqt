package com.nis.monitor.dao;

import com.nis.monitor.entity.Bk002Grzd;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Bk002GrzdDao {
	void save(Bk002Grzd arg0);

	void delete(@Param("relid") String arg0);

	void update(Bk002Grzd arg0);

	void updateSpecified(@Param("bk002Grzd") Bk002Grzd arg0, @Param("updateAttrs") List<String> arg1);

	void updBkType(@Param("refid") String arg0, @Param("lastoperDate") Date arg1, @Param("lastoperName") String arg2,
			@Param("bkType") Integer arg3);

	void updBkType1(@Param("relid") String arg0, @Param("lastoperDate") Date arg1, @Param("lastoperName") String arg2);

	Bk002Grzd get(@Param("relid") String arg0);

	List<Bk002Grzd> findBk002Grzd(Bk002Grzd arg0);

	int findBk002GrzdCount(Bk002Grzd arg0);

	List<Bk002Grzd> getAll();

	List<Bk002Grzd> getReportInfect(@Param("refid") String arg0, @Param("authStatus") String arg1);

	Bk002Grzd getInfectInfo(@Param("relid") String arg0);

	void updStatusByRefid(Bk002Grzd arg0);

	List<Integer> findStatusByRefid(@Param("refid") String arg0);

	int findUnAuditCount(@Param("refid") String arg0);

	List<Bk002Grzd> getbyOperRelid(@Param("operRelid") String arg0);

	List<Map<String, Object>> findqrxczxgxgrlcs(@Param("startDate") String arg0, @Param("endDate") String arg1,
			@Param("ificu") String arg2);

	Bk002Grzd getOneInfectByRefid(@Param("refid") String arg0);

	List<Map<String, Object>> findMainInfectionParts(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findMainIncidence(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findBloodInfections(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Bk002Grzd> queryGrPosition();

	List<Bk002Grzd> getLastReport(@Param("zyid") String arg0);

	void updateOutcome(Bk002Grzd arg0);

	void returnCard(Bk002Grzd arg0);
}