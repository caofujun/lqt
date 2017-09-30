package com.nis.zb.service;

import com.nis.comm.entity.MyPage;
import com.nis.log.entity.SysLog;
import com.nis.zb.entity.ZbRecord;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface ZbRecordService {
	MyPage<ZbRecord> a(ZbRecord arg0);

	List<ZbRecord> findZbxhlList(Date arg0, Date arg1);

	List<SysLog> b(ZbRecord arg0);

	String c(ZbRecord arg0);

	String[] d(ZbRecord arg0);

	void cL(String arg0);

	void cM(String arg0);

	void cN(String arg0);

	void cO(String arg0);

	void cP(String arg0);

	void cQ(String arg0);

	void cR(String arg0);

	void cS(String arg0);

	void cT(String arg0);

	void cU(String arg0);

	void cV(String arg0);

	void cW(String arg0);

	void cX(String arg0);

	void cY(String arg0);

	void cZ(String arg0);

	void da(String arg0);

	void a(String arg0, Date arg1, Date arg2);

	void b(String arg0, Date arg1, Date arg2);

	void db(String arg0);

	void dc(String arg0);

	void dd(String arg0);

	void c(String arg0, Date arg1, Date arg2);

	void de(String arg0);

	void df(String arg0);

	void dg(String arg0);

	void dh(String arg0);

	ZbRecord e(ZbRecord arg0);
}