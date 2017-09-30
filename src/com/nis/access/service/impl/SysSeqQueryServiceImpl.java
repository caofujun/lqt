package com.nis.access.service.impl;

import com.nis.access.entity.SysSeqQuery;
import com.nis.access.service.SysSeqQueryService;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SysSeqQueryServiceImpl implements SysSeqQueryService {
	private static final Logger logger = Logger.getLogger(SysSeqQueryServiceImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> a(SysSeqQuery sysSeqQuery) {
		Integer total = Integer.valueOf(this.c(sysSeqQuery));
		List data = null;

		try {
			if (total.intValue() > 0) {
				Integer e = Integer.valueOf(Integer.parseInt(sysSeqQuery.getPageNum())
						* (Integer.parseInt(sysSeqQuery.getPageStart()) - 1));
				Integer endNum = Integer.valueOf(
						Integer.parseInt(sysSeqQuery.getPageNum()) * Integer.parseInt(sysSeqQuery.getPageStart()));
				data = this.jdbcTemplate.queryForList("select * from (select rownum rn,a.* from ("
						+ sysSeqQuery.getSeqName() + ") a) where rn between " + e + " and " + endNum);
			}
		} catch (Exception arg5) {
			arg5.printStackTrace();
			logger.error("序列生成器，读取新的数据库步进值错误", arg5);
		}

		return data;
	}

	public int b(SysSeqQuery sysSeqQuery) {
		return this.jdbcTemplate.update(sysSeqQuery.getSeqName());
	}

	public int c(SysSeqQuery sysSeqQuery) {
		return this.jdbcTemplate.queryForInt("select count(1) from (" + sysSeqQuery.getSeqName() + ")");
	}
}