package com.nis.param.service;

import com.nis.comm.enums.Param;
import com.nis.param.entity.SysParam;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysParamService {
	void save(SysParam arg0);

	void delete(String arg0);

	void update(SysParam arg0);

	SysParam get(String arg0);

	SysParam getByParamCode(String arg0);

	List<SysParam> pageQuery(SysParam arg0);

	List<SysParam> getAll();

	List<SysParam> findSysParamList(SysParam arg0);

	String findByParamCode(Param arg0, String arg1, String arg2, String arg3);

	String findByParamCode(Param arg0);

	String findByParamCode(Param arg0, String arg1);
}