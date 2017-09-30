package com.nis.questions.dao;

import com.nis.questions.entity.QsRepoOptions;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QsRepoOptionsDao {
	void save(QsRepoOptions arg0);

	void update(QsRepoOptions arg0);

	QsRepoOptions get(@Param("optId") String arg0);

	List<QsRepoOptions> findQsRepoOptions(QsRepoOptions arg0);

	int findQsRepoOptionsCount(QsRepoOptions arg0);

	List<QsRepoOptions> getAll();

	void deleteRtid(@Param("rtid") String arg0);

	List<QsRepoOptions> findByRtid(@Param("rtid") String arg0);
}