package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl014DicTrocheKind;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl014DicTrocheKindDao {
	void save(Xl014DicTrocheKind arg0);

	void delete(@Param("year") Integer arg0, @Param("trochekindid") String arg1);

	void update(Xl014DicTrocheKind arg0);

	Xl014DicTrocheKind get(@Param("year") Integer arg0, @Param("trochekindid") String arg1);

	List<Xl014DicTrocheKind> findXl014DicTrocheKind(Xl014DicTrocheKind arg0);

	int findXl014DicTrocheKindCount(Xl014DicTrocheKind arg0);

	List<Xl014DicTrocheKind> getAll();
}