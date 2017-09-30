package com.nis.analysis.dao;

import com.nis.analysis.entity.SysJudgeLog;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysJudgeLogDao {
	void save(SysJudgeLog arg0);

	void delete(@Param("id") String arg0);

	void update(SysJudgeLog arg0);

	SysJudgeLog get(@Param("id") String arg0);

	List<SysJudgeLog> findSysJudgeLog(SysJudgeLog arg0);

	int findSysJudgeLogCount(SysJudgeLog arg0);

	List<SysJudgeLog> getAll();

	List<SysJudgeLog> findList(@Param("ids") String[] arg0);

	SysJudgeLog getByJudeTypeAndStart(@Param("judgeType") String arg0, @Param("startTime") Date arg1);

	List<SysJudgeLog> findInExecRecore();

	void updateStatus(SysJudgeLog arg0);

	void updInExecToFinish(@Param("endTime") Date arg0);
}