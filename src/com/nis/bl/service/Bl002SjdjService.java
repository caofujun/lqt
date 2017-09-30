package com.nis.bl.service;

import com.nis.bl.entity.Bl002Sjdj;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface Bl002SjdjService {
	void save(Bl002Sjdj arg0);

	void delete(String arg0);

	void update(Bl002Sjdj arg0);

	Bl002Sjdj get(String arg0);

	MyPage<Bl002Sjdj> a(Bl002Sjdj arg0);

	List<Bl002Sjdj> getAll();

	List<Map<String, Object>> findzyblryksfb(String arg0, String arg1);

	List<Map<String, Object>> findzyblfsgwtj(String arg0, String arg1);

	List<Map<String, Object>> findMainExposure(Date arg0, Date arg1);

	Result<String> a(HttpServletRequest arg0, String arg1, String arg2);

	HSSFWorkbook b(Bl002Sjdj arg0);

	List<Bl002Sjdj> findFcMessage(String arg0);

	List<Bl002Sjdj> findFcMessageCount(String arg0);
}