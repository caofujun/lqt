package com.nis.access.service;

import com.nis.access.entity.SysAction;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysActionService {
	void save(SysAction arg0);

	void delete(String arg0);

	void update(SysAction arg0);

	SysAction get(String arg0);

	SysAction findSysAction(SysAction arg0);

	List<SysAction> findSysActionList(SysAction arg0);

	SysAction findByParamCode(String arg0, Long arg1, String arg2, String arg3);
}