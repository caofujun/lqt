package com.nis.comm.service;

import com.nis.comm.entity.Result;
import org.springframework.stereotype.Component;

@Component
public interface NisTaskService {
	void execute(Result<?> arg0);
}