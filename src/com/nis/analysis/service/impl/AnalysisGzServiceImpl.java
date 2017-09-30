package com.nis.analysis.service.impl;

import com.nis.analysis.model.f;
import com.nis.analysis.service.AnalysisGzService;
import com.nis.analysis.service.AnalysisTextService;
import com.nis.analysis.service.NyUnanalyzeBcService;
import com.nis.analysis.service.NyUnanalyzeDictService;
import com.nis.analysis.service.Zg007DictService;
import com.nis.comm.constants.b;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnalysisGzServiceImpl implements AnalysisGzService {
	@Autowired
	private AnalysisTextService bI;
	@Autowired
	private Zg007DictService X;
	@Autowired
	private NyUnanalyzeBcService aT;
	@Autowired
	private NyUnanalyzeDictService ba;
	@Autowired
	private SysDictService p;

	public void j() {
		String keyType = "A9B8E397C091559F";
		String dictType = "977C5BB729F88946";
		Map keyfd = this.X.f(keyType, dictType);
		keyType = "86A8433C4635E2ED";
		Map keykd = this.X.f(keyType, dictType);
		keyType = "0C4BC0DB8EDC474E";
		Map keyjw = this.X.f(keyType, dictType);
		keyType = "52B669C0526DBADC";
		Map keygj = this.X.f(keyType, dictType);
		keyType = "CB7C56987F20CA5F";
		Map keybw = this.X.f(keyType, dictType);
		keyType = "642D3D3B424E2D80";
		Map keysl = this.X.f(keyType, dictType);
		Map unBc = this.aT.getUnBcMap();
		Map unDict = this.ba.getUnDictMap();
		HashMap bjColor = new HashMap();
		List sysdictList = this.p.u("YJBJCYS", "0");
		Iterator arg13 = sysdictList.iterator();

		while (arg13.hasNext()) {
			SysDict mapList = (SysDict) arg13.next();
			bjColor.put(mapList.getDictCode(), mapList.getDictName());
		}

		ArrayList mapList1 = new ArrayList();
		mapList1.add(keyfd);
		mapList1.add(keykd);
		mapList1.add(keyjw);
		mapList1.add(keygj);
		mapList1.add(keybw);
		mapList1.add(keysl);
		mapList1.add(unBc);
		mapList1.add(unDict);
		this.bI.f(mapList1);
		this.bI.a(bjColor);
	}

	public f l(String cttext) {
		this.j();
		return this.bI.b(b.fi, cttext);
	}
}