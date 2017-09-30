package com.nis.access.service;

import com.nis.access.entity.SysSeqQuery;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface SysSeqQueryService {
	List<Map<String, Object>> a(SysSeqQuery arg0);

	int b(SysSeqQuery arg0);

	int c(SysSeqQuery arg0);
}