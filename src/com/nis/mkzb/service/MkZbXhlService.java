package com.nis.mkzb.service;

import com.nis.comm.entity.Result;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface MkZbXhlService {
	List<Map<String, Object>> findDicOffice();

	List<Map<String, Object>> findPatientMain(Date arg0, Date arg1);

	List<Map<String, Object>> findInfectInfo(Date arg0, Date arg1);

	List<Map<String, Object>> findPathoInfo(Date arg0, Date arg1);

	List<Map<String, Object>> findAntibInfo(Date arg0, Date arg1);

	Result<String[]> g(Date arg0, Date arg1);
}