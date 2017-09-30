package com.nis.cdc.service;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk002Sybk;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk002SybkService {
	void save(CtgBk002Sybk arg0);

	void delete(String arg0);

	void update(CtgBk002Sybk arg0);

	CtgBk002Sybk get(String arg0);

	MyPage<CtgBk002Sybk> a(CtgBk002Sybk arg0);

	List<CtgBk002Sybk> getAll();

	List<CtgBk002Sybk> findCardsForAdmin(CtgBk001Crbmaster arg0);

	Result<String> a(CtgBk002Sybk arg0, AcAccount arg1);

	void audit(CtgBk002Sybk arg0);

	void retreat(CtgBk002Sybk arg0);

	void cancel(CtgBk002Sybk arg0);

	void remove(CtgBk002Sybk arg0);

	void updateNotes(String arg0, String arg1);

	void batchAudit(CtgBk002Sybk arg0);

	void updatePrintFlag(String arg0);
}