package com.nis.jk.dao;

import com.nis.jk.entity.JkMessage;
import com.nis.jk.entity.JkSyncLog;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkMessageDao {
	void save(JkMessage arg0);

	void delete(@Param("id") String arg0);

	void update(JkMessage arg0);

	JkMessage get(@Param("id") String arg0);

	List<JkMessage> findJkMessage(JkMessage arg0);

	int findJkMessageCount(JkMessage arg0);

	List<JkMessage> getAll();

	int findJkSyncLogCount(JkSyncLog arg0);

	int getSyncTotalByTable(@Param("tableName") String arg0, @Param("updFlag") Long arg1, @Param("updTime") Date arg2);

	List<JkSyncLog> findJkSyncLog(JkSyncLog arg0);
}