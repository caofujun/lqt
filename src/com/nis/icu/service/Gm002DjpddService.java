package com.nis.icu.service;

import com.nis.comm.entity.MyPage;
import com.nis.icu.entity.Gm002Djpdd;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gm002DjpddService {
	void save(Gm002Djpdd arg0);

	void update(Gm002Djpdd arg0);

	MyPage<Gm002Djpdd> a(Gm002Djpdd arg0);

	List<Gm002Djpdd> getAll();

	List<Gm002Djpdd> getByDateAndDeptId(String arg0, String arg1, Integer arg2, Integer arg3, Integer arg4);

	List<Gm002Djpdd> getBedByDateAndDeptId(String arg0, String arg1, Integer arg2, Integer arg3, Integer arg4);

	void saveList(List<Gm002Djpdd> arg0);
}