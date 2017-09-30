package com.nis.cdc.service;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk004Syycbk;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk004SyycbkService {
	void save(CtgBk004Syycbk arg0);

	void delete(String arg0);

	void update(CtgBk004Syycbk arg0);

	CtgBk004Syycbk get(String arg0);

	MyPage<CtgBk004Syycbk> a(CtgBk004Syycbk arg0);

	List<CtgBk004Syycbk> getAll();

	List<CtgBk004Syycbk> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void audit(CtgBk004Syycbk arg0);

	void retreat(CtgBk004Syycbk arg0);

	void cancel(CtgBk004Syycbk arg0);

	void remove(CtgBk004Syycbk arg0);

	void updateNotes(String arg0, String arg1);

	void batchAudit(CtgBk004Syycbk arg0);

	Result<String> a(CtgBk004Syycbk arg0, AcAccount arg1);

	void updatePrintFlag(String arg0);
}