package com.nis.msg.dao;

import com.nis.msg.entity.NyServerMsgTemplate;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyServerMsgTemplateDao {
	void save(NyServerMsgTemplate arg0);

	void delete(@Param("id") String arg0);

	void update(NyServerMsgTemplate arg0);

	NyServerMsgTemplate get(@Param("id") String arg0);

	List<NyServerMsgTemplate> findNyServerMsgTemplate(NyServerMsgTemplate arg0);

	int findNyServerMsgTemplateCount(NyServerMsgTemplate arg0);

	List<NyServerMsgTemplate> getAll();
}