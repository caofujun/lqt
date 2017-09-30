package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn008Jlybz;
import java.util.List;

public interface Xn008JlybzDao {
	void save(Xn008Jlybz arg0);

	void update(Xn008Jlybz arg0);

	List<Xn008Jlybz> findXn008Jlybz(Xn008Jlybz arg0);

	int findXn008JlybzCount(Xn008Jlybz arg0);

	List<Xn008Jlybz> getAll();

	List<Xn008Jlybz> findJlybzInfo();
}