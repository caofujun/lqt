package com.nis.pdca.dao;

import com.nis.pdca.entity.ZlPdcaFlowDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZlPdcaFlowDetailDao {
	void save(ZlPdcaFlowDetail arg0);

	void delete(@Param("fdSubid") String arg0);

	void update(ZlPdcaFlowDetail arg0);

	ZlPdcaFlowDetail get(@Param("fdSubid") String arg0);

	List<ZlPdcaFlowDetail> findZlPdcaFlowDetail(ZlPdcaFlowDetail arg0);

	int findZlPdcaFlowDetailCount(ZlPdcaFlowDetail arg0);

	List<ZlPdcaFlowDetail> getAll();

	List<ZlPdcaFlowDetail> getByFuid(@Param("fuid") String arg0);

	List<ZlPdcaFlowDetail> findZlPdcaFlowDetailList(ZlPdcaFlowDetail arg0);
}