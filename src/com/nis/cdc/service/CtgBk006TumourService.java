package com.nis.cdc.service;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk006Tumour;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk006TumourService {
	void save(CtgBk006Tumour arg0);

	Result<String> a(CtgBk006Tumour arg0, AcAccount arg1);

	void delete(String arg0);

	void update(CtgBk006Tumour arg0);

	CtgBk006Tumour get(String arg0);

	MyPage<CtgBk006Tumour> a(CtgBk006Tumour arg0);

	List<CtgBk006Tumour> getAll();

	List<CtgBk006Tumour> getByMastertid(String arg0);

	List<CtgBk006Tumour> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk006Tumour arg0);

	void retreat(CtgBk006Tumour arg0);

	void cancel(CtgBk006Tumour arg0);

	void remove(CtgBk006Tumour arg0);

	void updateNotes(String arg0, String arg1);

	void batchAudit(CtgBk006Tumour arg0);

	void updatePrintFlag(String arg0);
}