package com.nis.cdc.service;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk005Syjc;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk005SyjcService {
	void save(CtgBk005Syjc arg0);

	Result<String> a(CtgBk005Syjc arg0, AcAccount arg1);

	void delete(String arg0);

	void update(CtgBk005Syjc arg0);

	CtgBk005Syjc get(String arg0);

	MyPage<CtgBk005Syjc> a(CtgBk005Syjc arg0);

	List<CtgBk005Syjc> getAll();

	List<CtgBk005Syjc> getByMastertid(String arg0);

	List<CtgBk005Syjc> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk005Syjc arg0);

	void retreat(CtgBk005Syjc arg0);

	void cancel(CtgBk005Syjc arg0);

	void remove(CtgBk005Syjc arg0);

	void updateNotes(String arg0, String arg1);

	void batchAudit(CtgBk005Syjc arg0);

	void updatePrintFlag(String arg0);

	HSSFWorkbook a(HttpServletRequest arg0, String arg1);
}