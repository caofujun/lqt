package com.nis.jk.dao;

import com.nis.jk.entity.JkDicOffice;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkDicOfficeDao {
	void save(JkDicOffice arg0);

	void delete(@Param("id") String arg0);

	void update(JkDicOffice arg0);

	JkDicOffice get(@Param("id") String arg0);

	List<JkDicOffice> findJkDicOffice(JkDicOffice arg0);

	int findJkDicOfficeCount(JkDicOffice arg0);

	List<JkDicOffice> getAll();

	void updateFlag(JkDicOffice arg0);
}