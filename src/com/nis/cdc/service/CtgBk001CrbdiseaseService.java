package com.nis.cdc.service;

import com.nis.cdc.entity.CtgBk001Crbdisease;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk001CrbdiseaseService {
	void save(CtgBk001Crbdisease arg0);

	void delete(String arg0);

	void update(CtgBk001Crbdisease arg0);

	List<CtgBk001Crbdisease> get(String arg0);

	MyPage<CtgBk001Crbdisease> a(CtgBk001Crbdisease arg0);

	List<CtgBk001Crbdisease> getAll();

	void audit(CtgBk001Crbdisease arg0);

	void retreat(CtgBk001Crbdisease arg0);

	void cancel(CtgBk001Crbdisease arg0);

	void remove(CtgBk001Crbdisease arg0);

	void updateDiseaseNotes(String arg0, String arg1);

	void batchAudit(CtgBk001Crbdisease arg0);

	void a(CtgBk001Crbmaster arg0);
}