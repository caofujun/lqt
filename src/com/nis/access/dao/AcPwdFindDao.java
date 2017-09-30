package com.nis.access.dao;

import com.nis.access.entity.AcPwdFind;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcPwdFindDao {
	void save(AcPwdFind arg0);

	void delete(@Param("id") String arg0);

	void update(AcPwdFind arg0);

	AcPwdFind get(@Param("id") String arg0);

	List<AcPwdFind> findAcPwdFind(AcPwdFind arg0);

	int findAcPwdFindCount(AcPwdFind arg0);

	List<AcPwdFind> getAll();

	void updateState(@Param("id") String arg0, @Param("status") Long arg1);

	AcPwdFind findAcPwdFindByUserNameEmailVDate(@Param("username") String arg0, @Param("email") String arg1,
			@Param("validDate") Date arg2);
}