package com.nis.analysis.service;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.entity.ZdmxWeight;
import com.nis.analysis.entity.Zg006Zdmx;
import com.nis.analysis.model.a;
import com.nis.analysis.model.c;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.patient.entity.St003Cryxxb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface AnalysisModelService {
	a a(List<Gr018Ysgrys> arg0, SysJudgeLog arg1);

	c a(List<Gr018Ysgrys> arg0, St003Cryxxb arg1);

	void n(String arg0);

	String d();

	ZdmxWeight b(List<Zg006Zdmx> arg0, St003Cryxxb arg1);
}