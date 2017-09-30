package com.nis.dict.dao;

import com.nis.dict.entity.Zg032Sjxmpp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg032SjxmppDao {
	void save(Zg032Sjxmpp arg0);

	void delete(@Param("id") String arg0);

	void update(Zg032Sjxmpp arg0);

	Zg032Sjxmpp get(@Param("id") String arg0);

	List<Zg032Sjxmpp> findZg032Sjxmpp(Zg032Sjxmpp arg0);

	int findZg032SjxmppCount(Zg032Sjxmpp arg0);

	List<Zg032Sjxmpp> getAll();

	List<Zg032Sjxmpp> findJoinGrys();
}