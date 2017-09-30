package com.nis.comm.service;

import com.nis.comm.entity.Result;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface SyncService {
	Result<String> b(Map<String, Object> arg0);
}