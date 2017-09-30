package com.nis.analysis.service;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysJudgeLogService {
	void save(SysJudgeLog arg0);

	void a(SysJudgeLog arg0);

	void delete(String arg0);

	void update(SysJudgeLog arg0);

	SysJudgeLog get(String arg0);

	MyPage<SysJudgeLog> b(SysJudgeLog arg0);

	List<SysJudgeLog> getAll();

	List<SysJudgeLog> o(String arg0);

	void e();

	void f();
}