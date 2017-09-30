package com.nis.hand.dao;

import com.nis.hand.entity.Sw001Swsyp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Sw001SwsypDao {
	void save(Sw001Swsyp arg0);

	void delete(@Param("id") String arg0);

	void update(Sw001Swsyp arg0);

	void updateSpecified(@Param("sw001Swsyp") Sw001Swsyp arg0, @Param("updateAttrs") List<String> arg1);

	Sw001Swsyp get(@Param("id") String arg0);

	List<Sw001Swsyp> findSw001Swsyp(Sw001Swsyp arg0);

	int findSw001SwsypCount(Sw001Swsyp arg0);

	List<Sw001Swsyp> getAll();
}