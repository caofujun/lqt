package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St004YzxxbDao {
	void save(St004Yzxxb arg0);

	void delete(@Param("id") String arg0);

	void update(St004Yzxxb arg0);

	void updateSpecified(@Param("st004Yzxxb") St004Yzxxb arg0, @Param("updateAttrs") List<String> arg1);

	St004Yzxxb get(@Param("id") String arg0);

	List<St004Yzxxb> findSt004Yzxxb(St004Yzxxb arg0);

	int findSt004YzxxbCount(St004Yzxxb arg0);

	List<St004Yzxxb> getAll();

	int findDocAdviceCount(St004Yzxxb arg0);

	int findFlagJrCount(@Param("id") String arg0);

	Integer findFlagKjywCount(@Param("id") String arg0);

	List<St004Yzxxb> findDocAdvice(St004Yzxxb arg0);

	List<St004Yzxxb> likeOrderName(St004Yzxxb arg0);

	List<St004Yzxxb> findInOrderName(@Param("classCode") String arg0, @Param("zyid") String arg1);

	List<St004Yzxxb> findListByzyidName(@Param("orderNames") String[] arg0);

	List<St004Yzxxb> findDrugSituation(St004Yzxxb arg0);

	List<St004Yzxxb> findDrugSituationByPatient(St004Yzxxb arg0);

	List<St004Yzxxb> findByZyid(St004Yzxxb arg0);

	Date getMonitorPatientYzxxLastAt(@Param("deptId") String arg0);

	List<DataWarning> findPatentYzxxbWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	List<St004Yzxxb> getYzxx(@Param("zyId") String arg0);

	List<St003Cryxxb> findKjywWaitAnaly(@Param("orclEndNum") Integer arg0);

	int findKjywWaitCount();

	List<St004Yzxxb> findKjywByZyid(@Param("zyid") String arg0);

	void batchUpdAnalFlag(@Param("st004List") List<St004Yzxxb> arg0);

	List<St004Yzxxb> findDrug(St004Yzxxb arg0);

	List<St004Yzxxb> findDrugSituationByPatientTemp(St004Yzxxb arg0);

	void updAnalFlag(St004Yzxxb arg0);

	void updKeepToWaitState();

	int getUseDrugNum(@Param("zyid") String arg0, @Param("startDate") Date arg1, @Param("endDate") Date arg2,
			@Param("orderId") String arg3, @Param("id") String arg4);

	int getUseDrugNumNo(@Param("zyid") String arg0, @Param("startDate") Date arg1, @Param("endDate") Date arg2,
			@Param("orderId") String arg3);

	int getUseUnLimitNum(@Param("zyid") String arg0, @Param("startDate") Date arg1, @Param("endDate") Date arg2);

	int getUseLimitNum(@Param("zyid") String arg0, @Param("startDate") Date arg1, @Param("endDate") Date arg2);

	Date getOrderAtNearStart(@Param("zyid") String arg0, @Param("startDate") Date arg1, @Param("endDate") Date arg2);

	int getDrugNumTheDay(@Param("zyid") String arg0, @Param("startDate") Date arg1, @Param("endDate") Date arg2);

	List<St004Yzxxb> findDrugbyZyid(St004Yzxxb arg0);

	List<St004Yzxxb> findDrugAddbyZyid(St004Yzxxb arg0);

	List<St004Yzxxb> findForYzAnalyzis(St004Yzxxb arg0);
}