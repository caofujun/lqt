package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw104JcdjgXj;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw104JcdjgXjDao {
	void save(Hw104JcdjgXj arg0);

	void delete(@Param("id") String arg0);

	void delByHw102Id(@Param("hw102Id") String arg0);

	void update(Hw104JcdjgXj arg0);

	Hw104JcdjgXj get(@Param("id") String arg0);

	List<Hw104JcdjgXj> findHw104JcdjgXj(Hw104JcdjgXj arg0);

	int findHw104JcdjgXjCount(Hw104JcdjgXj arg0);

	List<Hw104JcdjgXj> getAll();

	void delHw104(@Param("pathoIdNotIn") List<String> arg0, @Param("hw102Id") String arg1);

	List<String> findPathoIdByHw102Id(@Param("hw102Id") String arg0);

	List<Hw104JcdjgXj> findListByHw102Id(@Param("hw102Id") String arg0);

	List<String> findPathoNameByHw102Id(@Param("hw102Id") String arg0);
}