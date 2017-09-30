package com.nis.mkzb.service;

import com.nis.comm.entity.MyPage;
import com.nis.mkzb.entity.MkZbRecord;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface MkZbRecordService {
	MyPage<MkZbRecord> a(MkZbRecord arg0);

	List<MkZbRecord> findZbxhlList(Date arg0, Date arg1);

	String b(MkZbRecord arg0);

	String c(MkZbRecord arg0);

	void bK(String arg0);
}