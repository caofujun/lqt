package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn017Tsnyzd;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xn017TsnyzdDao {
	void save(Xn017Tsnyzd arg0);

	void delete(Xn017Tsnyzd arg0);

	void update(Xn017Tsnyzd arg0);

	Xn017Tsnyzd get(@Param("pathogenId") String arg0);

	List<Xn017Tsnyzd> findXn017Tsnyzd(Xn017Tsnyzd arg0);

	int findXn017TsnyzdCount(Xn017Tsnyzd arg0);

	List<Xn017Tsnyzd> getAll();

	List<Xn017Tsnyzd> getKvEntity();

	void save2(Xn017Tsnyzd arg0);

	Xn017Tsnyzd getByPathogenIdDrugIdSpecDescribe(Xn017Tsnyzd arg0);

	List<Xn017Tsnyzd> findTsnyToAna();

	void deleteBySpecDescribe(@Param("specDescribe") String arg0);
}