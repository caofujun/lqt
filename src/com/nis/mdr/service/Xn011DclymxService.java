package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.ViewMdr;
import com.nis.mdr.entity.Xn011Dclymx;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface Xn011DclymxService {
	void save(Xn011Dclymx arg0);

	void batchInsert(List<Xn011Dclymx> arg0);

	void delete(Date arg0);

	void update(Xn011Dclymx arg0);

	void updateMdrType(Xn011Dclymx arg0);

	void updateMdrInfo(Xn011Dclymx arg0);

	void updateInfectTypeId(Xn011Dclymx arg0);

	void updateByTestOrderNo(Xn011Dclymx arg0);

	Xn011Dclymx get(Date arg0);

	Xn011Dclymx a(String arg0, String arg1, Integer arg2, String arg3, String arg4);

	MyPage<Xn011Dclymx> b(Xn011Dclymx arg0);

	List<Xn011Dclymx> getAll();

	MyPage<ViewMdr> a(ViewMdr arg0);

	HSSFWorkbook b(ViewMdr arg0);

	List<Map<String, Object>> findwswbbfb(String arg0, String arg1, String arg2);

	List<Map<String, Object>> findwswjcqk(String arg0, String arg1, String arg2);

	List<Map<String, Object>> dcnyfbt(String arg0, String arg1, String arg2);

	List<Map<String, Object>> grblfbt(String arg0, String arg1, String arg2);

	Map<String, Object> b(String arg0, String arg1, List<String> arg2);

	List<ViewMdr> c(ViewMdr arg0);

	HSSFWorkbook d(ViewMdr arg0);

	List<Map<String, Object>> findMainSamples(Date arg0, Date arg1);

	List<Map<String, Object>> findMainFocusBacteria(Date arg0, Date arg1);

	List<Map<String, Object>> findMainMoreResistant(Date arg0, Date arg1);

	List<ViewMdr> findCheckOutbacteria(@Param("zyid") String arg0);

	Date getMonitorPatientMdrLastAt();

	void updateIT(Xn011Dclymx arg0);

	Xn011Dclymx getByPrimaryKeys(Xn011Dclymx arg0);

	MyPage<ViewMdr> e(ViewMdr arg0);

	void clearInfectTypeByCardId(String arg0);

	long getMaxOrderno();

	void updateMdrCheck(Xn011Dclymx arg0);
}