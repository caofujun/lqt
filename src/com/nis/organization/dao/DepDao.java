package com.nis.organization.dao;

import com.nis.organization.entity.Dep;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface DepDao {
	void save(Dep arg0);

	void delete(@Param("deptId") String arg0);

	void update(Dep arg0);

	Dep get(@Param("deptId") String arg0);

	List<Dep> findDep(Dep arg0);

	int findDepCount(Dep arg0);

	List<Dep> getAll();

	List<Dep> getDept();

	List<Dep> getDepByUnitIdAndDepTypeExt(@Param("hospId") String arg0, @Param("deptTypeId") String arg1,
			@Param("dgsType") String arg2, @Param("isClosed") Long arg3);

	List<Dep> getDepList(Dep arg0);

	List<Dep> findLike(@Param("hospId") String arg0, @Param("name") String arg1);

	List<Dep> getUnitIdDepId(@Param("hospId") String arg0, @Param("deptId") String arg1);

	List<Dep> findDepExtis(Dep arg0);

	List<Dep> getByDeptIds(@Param("deptIds") List<String> arg0);

	List<Dep> pageQueryForAuthDept(Dep arg0);

	void updateBaseInfect(Dep arg0);

	String getDeptType(@Param("deptId") String arg0);

	void excuteNicu(@Param("yzName") String arg0, @Param("nicuId") String arg1, @Param("nicuName") String arg2,
			@Param("day") String arg3);

	void excuteXsr(@Param("yzName") String arg0, @Param("xsrId") String arg1, @Param("xsrName") String arg2,
			@Param("day") String arg3);

	void excuteIcu(@Param("yzName") String arg0, @Param("icuId") String arg1, @Param("icuName") String arg2,
			@Param("day") String arg3, @Param("deptIds") String[] arg4);

	void excuteCcu(@Param("yzName") String arg0, @Param("ccuId") String arg1, @Param("ccuName") String arg2,
			@Param("day") String arg3);

	void excuteXCcu(@Param("yzName") String arg0, @Param("xccuId") String arg1, @Param("xccuName") String arg2,
			@Param("day") String arg3);

	void excuteCcuBack(@Param("orderAt") String arg0, @Param("zyid") String arg1, @Param("ccuId") String arg2,
			@Param("day") String arg3, @Param("yzName") String arg4);

	void excuteNicuBack(@Param("orderAt") String arg0, @Param("zyid") String arg1, @Param("nicuId") String arg2,
			@Param("day") String arg3, @Param("yzName") String arg4);

	List<Map<String, String>> getNicuListBack(@Param("yzName") String arg0, @Param("day") String arg1);

	List<Map<String, Object>> getICUList(@Param("yzName") String arg0, @Param("day") String arg1);

	List<Map<String, Object>> getXsrList(@Param("day") String arg0);

	void updateYzxxb(@Param("deptId") String arg0, @Param("deptName") String arg1, @Param("zyid") String arg2,
			@Param("orderAt") Date arg3);

	void updateSjbb(@Param("deptId") String arg0, @Param("deptName") String arg1, @Param("zyid") String arg2,
			@Param("orderAt") Date arg3);

	void updateSsxxb(@Param("deptId") String arg0, @Param("deptName") String arg1, @Param("zyid") String arg2,
			@Param("orderAt") Date arg3);

	void updateZkjl(@Param("deptId") String arg0, @Param("deptName") String arg1, @Param("zyid") String arg2,
			@Param("orderAt") Date arg3);
}