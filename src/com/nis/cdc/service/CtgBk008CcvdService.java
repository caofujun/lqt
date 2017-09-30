package com.nis.cdc.service;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk008Ccvd;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk008CcvdService {
	void save(CtgBk008Ccvd arg0);

	void delete(String arg0);

	void update(CtgBk008Ccvd arg0);

	CtgBk008Ccvd get(String arg0);

	MyPage<CtgBk008Ccvd> a(CtgBk008Ccvd arg0);

	List<CtgBk008Ccvd> getAll();

	Result<String> a(CtgBk008Ccvd arg0, AcAccount arg1);

	List<CtgBk008Ccvd> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk008Ccvd arg0);

	void retreat(CtgBk008Ccvd arg0);

	void cancel(CtgBk008Ccvd arg0);

	void remove(CtgBk008Ccvd arg0);

	void updateNotes(String arg0, String arg1);

	void batchAudit(CtgBk008Ccvd arg0);

	void updatePrintFlag(String arg0);
}