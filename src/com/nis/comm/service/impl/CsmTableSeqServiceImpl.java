package com.nis.comm.service.impl;

import com.nis.comm.entity.CsmTableSeq;
import com.nis.comm.service.CsmTableSeqService;
import com.nis.comm.utils.p;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CsmTableSeqServiceImpl implements CsmTableSeqService {
	private static final Logger logger = Logger.getLogger(CsmTableSeqServiceImpl.class.getName());
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

	public CsmTableSeq a(String tableName, int incr) {
		CsmTableSeq seq = this.M(tableName);
		return seq;
	}

	private CsmTableSeq M(String tableName) {
		CsmTableSeq csmTableSeq = new CsmTableSeq();

		try {
			csmTableSeq.setTableName(tableName);
			Long e = null;
			Long lastSeq = null;
			Integer count = Integer.valueOf(this.jdbcTemplate
					.queryForInt("select count(1) from table_seq where table_name=?", new Object[]{tableName}));
			if (count != null && count.intValue() != 0) {
				List seqs = this.jdbcTemplate.queryForList("select * from table_seq where table_name=?",
						new Object[]{tableName});
				Map seq = (Map) seqs.get(0);
				lastSeq = Long
						.valueOf(p.e(seq, "table_sequence").longValue() + p.e(seq, "table_increment").longValue());
				this.jdbcTemplate.update("update table_seq set table_sequence=? where table_name=?",
						new Object[]{lastSeq, tableName});
				e = p.e(seq, "table_sequence");
			} else {
				this.jdbcTemplate.update(
						"insert into table_seq(table_name,table_sequence,table_increment) values (?,0,50)",
						new Object[]{tableName});
				e = Long.valueOf(0L);
				lastSeq = Long.valueOf(49L);
			}

			csmTableSeq.setNextSeq(e);
			csmTableSeq.setLastSeq(lastSeq);
		} catch (Exception arg7) {
			logger.error("序列生成器，读取新的数据库步进值错误", arg7);
		}

		return csmTableSeq;
	}
}