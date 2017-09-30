package com.nis.monitor.dao;

import com.nis.monitor.entity.Gr016SsbwKjyw;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr016SsbwKjywDao {
	void save(Gr016SsbwKjyw arg0);

	void delete(@Param("relid") String arg0);

	void update(Gr016SsbwKjyw arg0);

	Gr016SsbwKjyw get(@Param("relid") String arg0);

	List<Gr016SsbwKjyw> findGr016SsbwKjyw(Gr016SsbwKjyw arg0);

	int findGr016SsbwKjywCount(Gr016SsbwKjyw arg0);

	List<Gr016SsbwKjyw> getAll();

	List<String> findYzIdByRefid(@Param("refid") String arg0, @Param("operTypeId") Integer arg1);

	void updateByRefids(@Param("gr016List") List<Gr016SsbwKjyw> arg0);

	void updSs001Zdb(@Param("operName") String arg0, @Param("opepartkindid") String arg1,
			@Param("partkindname") String arg2);

	void updZg011Ss(@Param("zyid") String arg0, @Param("relid") String arg1);
}