package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw103Jcdjg;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw103JcdjgDao {
	void save(Hw103Jcdjg arg0);

	void delete(@Param("id") String arg0);

	void delByHw102Id(@Param("hw102Id") String arg0);

	void update(Hw103Jcdjg arg0);

	void updateSpecified(@Param("hw103Jcdjg") Hw103Jcdjg arg0, @Param("updateAttrs") List<String> arg1);

	Hw103Jcdjg get(@Param("id") String arg0);

	List<Hw103Jcdjg> findHw103Jcdjg(Hw103Jcdjg arg0);

	int findHw103JcdjgCount(Hw103Jcdjg arg0);

	List<Hw103Jcdjg> getAll();

	List<Hw103Jcdjg> findListByHw102Id(@Param("hw102Id") String arg0, @Param("result") String arg1);
}