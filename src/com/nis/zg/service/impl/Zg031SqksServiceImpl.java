package com.nis.zg.service.impl;

import com.nis.zg.dao.Zg031SqksDao;
import com.nis.zg.entity.Zg031Sqks;
import com.nis.zg.service.Zg031SqksService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg031SqksServiceImpl implements Zg031SqksService {
	@Autowired
	private Zg031SqksDao yD;

	public void save(Zg031Sqks zg031Sqks) {
		this.yD.save(zg031Sqks);
	}

	public void delete(Zg031Sqks zg031Sqks) {
		this.yD.delete(zg031Sqks);
	}

	public List<Zg031Sqks> getAll(String userId) {
		return this.yD.getAll(userId);
	}

	public int isTableExist(String tableName) {
		return this.yD.isTableExist(tableName);
	}

	public String getDepts(String userId) {
		return this.yD.getDepts(userId);
	}
}