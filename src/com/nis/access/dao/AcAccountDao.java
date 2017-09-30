package com.nis.access.dao;

import com.nis.access.entity.AcAccount;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcAccountDao {
	void save(AcAccount arg0);

	void delete(@Param("userId") String arg0);

	void update(AcAccount arg0);

	void updateLoginInfo(@Param("userId") String arg0, @Param("errorTimes") Long arg1);

	void updateUserIdByUserId(@Param("userId") String arg0, @Param("newUserId") String arg1);

	AcAccount get(@Param("userId") String arg0);

	List<AcAccount> findAcAccount(AcAccount arg0);

	List<AcAccount> findAcAccountByCondition(AcAccount arg0);

	int findAcAccountCount(AcAccount arg0);

	int findAcAccountCountByCondition(AcAccount arg0);

	List<AcAccount> getUsername(@Param("username") String arg0, @Param("unitId") String arg1);

	AcAccount findAccountByRealnameNameAndEmail(@Param("realname") String arg0, @Param("email") String arg1);

	AcAccount findAccountByMobilenum(@Param("mobilenum") String arg0);

	List<AcAccount> findAccountByEmail(@Param("email") String arg0);

	void updatePwdByUserId(@Param("userId") String arg0, @Param("passwd") String arg1);

	List<AcAccount> findAccExtis(AcAccount arg0);

	List<AcAccount> findRealname(@Param("realname") String arg0);

	List<AcAccount> findByUnitId(@Param("unitId") String arg0);

	List<AcAccount> findAccoutByCondition(AcAccount arg0);

	int checkUniqueExtis(AcAccount arg0);

	List<AcAccount> findByDeptIds(@Param("deptIds") List<String> arg0);
}