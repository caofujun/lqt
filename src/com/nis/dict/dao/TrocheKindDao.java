package com.nis.dict.dao;

import com.nis.dict.entity.TrocheKind;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrocheKindDao {
	void save(TrocheKind arg0);

	void delete(@Param("year") Long arg0, @Param("trocheKindId") String arg1);

	void update(TrocheKind arg0);

	TrocheKind get(@Param("year") Long arg0, @Param("trocheKindId") String arg1);

	List<TrocheKind> findTrocheKind(TrocheKind arg0);

	int findTrocheKindCount(TrocheKind arg0);

	List<TrocheKind> getAll();
}