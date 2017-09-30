package com.nis.pdca.dao;

import com.nis.pdca.entity.ZlPdcaPlansDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZlPdcaPlansDetailDao {
	void save(ZlPdcaPlansDetail arg0);

	void delete(@Param("pdSubid") String arg0);

	void update(ZlPdcaPlansDetail arg0);

	ZlPdcaPlansDetail get(@Param("pdSubid") String arg0);

	List<ZlPdcaPlansDetail> findZlPdcaPlansDetail(ZlPdcaPlansDetail arg0);

	int findZlPdcaPlansDetailCount(ZlPdcaPlansDetail arg0);

	List<ZlPdcaPlansDetail> getAll();

	List<ZlPdcaPlansDetail> getByPuid(@Param("puid") String arg0);

	void deleteByPuid(@Param("puid") String arg0);
}