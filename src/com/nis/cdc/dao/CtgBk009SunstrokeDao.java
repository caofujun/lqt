package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk009Sunstroke;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk009SunstrokeDao {
	void save(CtgBk009Sunstroke arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk009Sunstroke arg0);

	CtgBk009Sunstroke get(@Param("masterid") String arg0);

	List<CtgBk009Sunstroke> findCtgBk009Sunstroke(CtgBk009Sunstroke arg0);

	int findCtgBk009SunstrokeCount(CtgBk009Sunstroke arg0);

	List<CtgBk009Sunstroke> getAll();

	List<CtgBk009Sunstroke> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk009Sunstroke arg0);

	void retreat(CtgBk009Sunstroke arg0);

	void cancel(CtgBk009Sunstroke arg0);

	void remove(CtgBk009Sunstroke arg0);

	void batchAudit(CtgBk009Sunstroke arg0);

	void updatePrintFlag(@Param("masterid") String arg0);

	void updateNotes(@Param("masterid") String arg0, @Param("notes") String arg1);
}