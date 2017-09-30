package com.nis.intervene.dao;

import com.nis.intervene.entity.FxZhibiao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FxZhibiaoDao {
	void save(FxZhibiao arg0);

	void delete(@Param("zbId") String arg0);

	void update(FxZhibiao arg0);

	FxZhibiao get(@Param("zbId") String arg0);

	List<FxZhibiao> findFxZhibiao(FxZhibiao arg0);

	int findFxZhibiaoCount(FxZhibiao arg0);

	List<FxZhibiao> getAll();

	FxZhibiao getByzbClass(@Param("zbClass") String arg0);
}