package com.nis.cdc.service;

import com.nis.cdc.entity.CtgBk001Zzd;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk001ZzdService {
	void save(CtgBk001Zzd arg0);

	void delete(String arg0);

	void update(CtgBk001Zzd arg0);

	CtgBk001Zzd get(String arg0);

	MyPage<CtgBk001Zzd> a(CtgBk001Zzd arg0);

	List<CtgBk001Zzd> getAll();

	boolean v(String arg0);

	CtgBk001Zzd getByMasterid(String arg0);
}