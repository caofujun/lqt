package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn013Lisbyt;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Xn013LisbytDao {
	void save(Xn013Lisbyt arg0);

	void delete(@Param("lisBytid") String arg0);

	void update(Xn013Lisbyt arg0);

	void match();

	Map<String, String> queryMatched();

	Xn013Lisbyt get(@Param("lisBytid") String arg0);

	List<Xn013Lisbyt> findXn013Lisbyt(Xn013Lisbyt arg0);

	int findXn013LisbytCount(Xn013Lisbyt arg0);

	List<Xn013Lisbyt> getAll();

	List<Xn013Lisbyt> findXn013LisbytList(Xn013Lisbyt arg0);

	int findXn013LisbytListCount(Xn013Lisbyt arg0);

	List<Xn013Lisbyt> autoSaveList();

	List<Xn013Lisbyt> matchBefore();
}