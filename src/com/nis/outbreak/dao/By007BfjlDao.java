package com.nis.outbreak.dao;

import com.nis.outbreak.entity.By007Bfjl;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface By007BfjlDao {
	void save(By007Bfjl arg0);

	void delete(@Param("id") String arg0);

	void update(By007Bfjl arg0);

	void updateSpecified(@Param("by007Bfjl") By007Bfjl arg0, @Param("updateAttrs") List<String> arg1);

	By007Bfjl get(@Param("id") String arg0);

	List<By007Bfjl> findBy007Bfjl(By007Bfjl arg0);

	int findBy007BfjlCount(By007Bfjl arg0);

	List<By007Bfjl> getAll();

	List<By007Bfjl> findList(By007Bfjl arg0);

	int findListCount(By007Bfjl arg0);

	List<By007Bfjl> findListByDept(By007Bfjl arg0);

	void updAuditFlag(By007Bfjl arg0);

	void updMemo(By007Bfjl arg0);
}