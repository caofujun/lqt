package com.nis.cdc.service;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk010Pesticide;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk010PesticideService {
	void save(CtgBk010Pesticide arg0);

	void delete(String arg0);

	void update(CtgBk010Pesticide arg0);

	CtgBk010Pesticide get(String arg0);

	MyPage<CtgBk010Pesticide> a(CtgBk010Pesticide arg0);

	List<CtgBk010Pesticide> getAll();

	Result<String> a(CtgBk010Pesticide arg0, AcAccount arg1);

	List<CtgBk010Pesticide> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk010Pesticide arg0);

	void retreat(CtgBk010Pesticide arg0);

	void cancel(CtgBk010Pesticide arg0);

	void remove(CtgBk010Pesticide arg0);

	void updateNotes(String arg0, String arg1);

	void batchAudit(CtgBk010Pesticide arg0);

	void updatePrintFlag(String arg0);
}