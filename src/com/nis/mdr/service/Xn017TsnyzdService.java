package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn017Tsnyzd;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn017TsnyzdService {
	void save(Xn017Tsnyzd arg0);

	void save2(Xn017Tsnyzd arg0);

	void delete(Xn017Tsnyzd arg0);

	void update(Xn017Tsnyzd arg0);

	Xn017Tsnyzd get(String arg0);

	MyPage<Xn017Tsnyzd> a(Xn017Tsnyzd arg0);

	List<Xn017Tsnyzd> getAll();

	List<Xn017Tsnyzd> getKvEntity();

	Xn017Tsnyzd getByPathogenIdDrugIdSpecDescribe(Xn017Tsnyzd arg0);

	List<Xn017Tsnyzd> findTsnyToAna();

	void deleteBySpecDescribe(String arg0);
}