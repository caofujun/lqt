package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl001Brxx;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Xl001BrxxDao {
	void save(Xl001Brxx arg0);

	void delete(@Param("brid") String arg0);

	void update(Xl001Brxx arg0);

	void updateSpecified(@Param("xl001Brxx") Xl001Brxx arg0, @Param("updateAttrs") List<String> arg1);

	Xl001Brxx get(@Param("brid") String arg0);

	List<Xl001Brxx> findXl001Brxx(Xl001Brxx arg0);

	int findXl001BrxxCount(Xl001Brxx arg0);

	List<Xl001Brxx> getAll();

	List<Xl001Brxx> findVoteDateList();

	List<Xl001Brxx> pageFindList(Xl001Brxx arg0);

	int pageFindListCount(Xl001Brxx arg0);

	Map<String, Object> callPNis6TaskXhl(Map<String, Object> arg0);

	List<Xl001Brxx> findWaitRegister(Xl001Brxx arg0);

	int findWaitRegisterCount(Xl001Brxx arg0);
}