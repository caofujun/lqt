package com.nis.outbreak.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.outbreak.dao.By007ConfigDao;
import com.nis.outbreak.entity.By007Config;
import com.nis.outbreak.service.By007ConfigService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class By007ConfigServiceImpl implements By007ConfigService {
	@Autowired
	private By007ConfigDao wk;

	public void save(By007Config by007Config) {
		this.wk.save(by007Config);
	}

	public void delete(String typeId) {
		this.wk.delete(typeId);
	}

	public void update(By007Config by007Config) {
		this.wk.update(by007Config);
	}

	public By007Config get(String typeId) {
		return this.wk.get(typeId);
	}

	public MyPage<By007Config> a(By007Config by007Config) {
		int total = this.wk.findBy007ConfigCount(by007Config);
		List data = null;
		if (total > 0) {
			data = this.wk.findBy007Config(by007Config);
		}

		return new MyPage(by007Config.getPage().intValue(), by007Config.getSize().intValue(), total, data);
	}

	@SqlLog(p = "暴发记录--暴发事件类型列表")
	public List<By007Config> getAll() {
		return this.wk.getAll();
	}

	public By007Config getByShowId(String id) {
		return this.wk.getByShowId(id);
	}

	public void batchUpdAbsolute(List<By007Config> configList) {
		if (configList != null && configList.size() > 0) {
			this.wk.batchUpdAbsolute(configList);
		}

	}

	public void batchUpdRelative(List<By007Config> configList) {
		if (configList != null && configList.size() > 0) {
			this.wk.batchUpdRelative(configList);
		}

	}

	@SqlLog(p = "暴发记录--保存暴发事件类型关注级别")
	public void L(List<String> typeIdList) {
		this.wk.saveUnGraded();
		this.wk.saveGraded(typeIdList);
	}
}