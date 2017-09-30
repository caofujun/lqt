package com.nis.icu.service;

import com.nis.comm.entity.MyPage;
import com.nis.icu.entity.Gm001Djpdm;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface Gm001DjpdmService {
	void save(Gm001Djpdm arg0);

	void update(Gm001Djpdm arg0);

	MyPage<Gm001Djpdm> a(Gm001Djpdm arg0);

	List<Gm001Djpdm> getAll();

	List<Gm001Djpdm> y(String arg0, String arg1) throws Exception;

	void a(Integer arg0, Integer arg1, Integer arg2, String arg3, String arg4, String arg5, String arg6, String arg7);

	HSSFWorkbook z(String arg0, String arg1) throws Exception;
}