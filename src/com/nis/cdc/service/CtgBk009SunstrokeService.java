package com.nis.cdc.service;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk009Sunstroke;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk009SunstrokeService {
	void save(CtgBk009Sunstroke arg0);

	Result<String> a(CtgBk009Sunstroke arg0, AcAccount arg1);

	void delete(String arg0);

	void update(CtgBk009Sunstroke arg0);

	CtgBk009Sunstroke get(String arg0);

	MyPage<CtgBk009Sunstroke> a(CtgBk009Sunstroke arg0);

	List<CtgBk009Sunstroke> getAll();

	List<CtgBk009Sunstroke> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk009Sunstroke arg0);

	void retreat(CtgBk009Sunstroke arg0);

	void cancel(CtgBk009Sunstroke arg0);

	void remove(CtgBk009Sunstroke arg0);

	void updateNotes(String arg0, String arg1);

	void batchAudit(CtgBk009Sunstroke arg0);

	void updatePrintFlag(String arg0);
}