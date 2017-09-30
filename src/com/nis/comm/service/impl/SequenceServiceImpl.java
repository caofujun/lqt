package com.nis.comm.service.impl;

import com.nis.comm.entity.CsmTableSeq;
import com.nis.comm.service.CsmTableSeqService;
import com.nis.comm.service.SequenceService;
import com.nis.comm.service.impl.Sequence;
import com.nis.comm.utils.ab;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceServiceImpl implements SequenceService {
	@Autowired
	private CsmTableSeqService oU;
	static final long oV = 100000000L;
	private int oW;
	private final Map<String, Sequence> oX = new HashMap();
	private final Map<String, Lock> oY = new HashMap();
	private final Lock oZ = new ReentrantLock();
	private static Logger logger = Logger.getLogger(SequenceServiceImpl.class.getName());

	public void setIncr(int incr) {
		this.oW = incr;
	}

	public void setCsmTableSeqService(CsmTableSeqService csmTableSeqService) {
		if (logger.isInfoEnabled()) {
			logger.info(csmTableSeqService);
		}

		this.oU = csmTableSeqService;
	}

	public Long L(String tableName) {
		return this.a(tableName, Integer.valueOf(9900), Long.valueOf(100000000L));
	}

	public Long a(String tableName, Integer prefixNum) {
		return this.a(tableName, prefixNum, Long.valueOf(100000000L));
	}

	public Long a(String tableName, Integer prefixNum, Long multiple) {
		if (ab.isEmpty(tableName)) {
			logger.error("序列生成器入参为NULL值或者空值 ");
		}

		tableName = tableName.toUpperCase();
		Lock row_lock = this.N(tableName);
		Long nexSeqId = this.a(tableName, row_lock);
		if (logger.isDebugEnabled()) {
			logger.debug("[" + tableName + "]最终返回的序列值为    nexSeqId ===" + nexSeqId);
		}

		return Long.valueOf((long) prefixNum.intValue() * multiple.longValue() + nexSeqId.longValue());
	}

	private Long a(String tableName, Lock row_lock) {
		try {
			row_lock.lock();
			if (logger.isDebugEnabled()) {
				logger.debug("[" + tableName + "]锁定：" + row_lock);
			}

			Sequence e = this.P(tableName);
			Long nextSeq = e.getNextSeq();
			int connectionCount = 0;

			while (nextSeq == Sequence.oQ) {
				++connectionCount;
				if (logger.isInfoEnabled()) {
					logger.info(
							"TABLE_NAME:[" + tableName + "]内存序列缓存器达到最大值，需要重新获取从数据库中获取下一个步进值，连接次数：" + connectionCount);
				}

				this.O(tableName);
				e = this.P(tableName);
				nextSeq = e.getNextSeq();
				if (connectionCount > 9) {
					logger.error("连接次数超过最大数" + connectionCount);
					throw new Exception("[" + tableName + "]表连接次数超过最大数 " + connectionCount
							+ "   1、请检查数据库连接是否正常；2、请检查是否创建GET_NEXT_SEQ存储过程；3、请检查是否为连接用户赋予owner权限");
				}
			}

			Long arg6 = nextSeq;
			return arg6;
		} catch (Exception arg9) {
			logger.error("序列生成器[" + tableName + "]读取错误 ");
		} finally {
			row_lock.unlock();
			if (logger.isDebugEnabled()) {
				logger.debug("[" + tableName + "]释放锁：" + row_lock);
			}

		}

		return null;
	}

	private Lock N(String tableName) {
		if (logger.isDebugEnabled()) {
			logger.debug("[" + tableName + " ]进入获取行级锁");
		}

		try {
			this.oZ.lock();
			Lock e = (Lock) this.oY.get(tableName);
			if (e == null) {
				e = new ReentrantLock();
				this.oY.put(tableName, e);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("[" + tableName + "] 获得行锁" + e);
			}

			Object arg3 = e;
			return (Lock) arg3;
		} catch (Exception arg6) {
			logger.error("序列生成器[" + tableName + "]行级锁获取错误 ");
		} finally {
			this.oZ.unlock();
			if (logger.isDebugEnabled()) {
				logger.debug(" 表[" + tableName + "] 释放锁");
			}

		}

		return null;
	}

	protected void O(String tableName) {
		this.oX.remove(tableName);
	}

	protected Sequence P(String tableName) {
		Sequence sequence = (Sequence) this.oX.get(tableName);
		if (sequence != null) {
			return sequence;
		} else {
			CsmTableSeq csmTableSeq = this.oU.a(tableName, this.oW);
			if (csmTableSeq == null) {
				logger.error("序列生成器[" + tableName + "]没有配置，请检查主键表[csm_sql_tbl]是否有该" + tableName + "的记录 ");
				throw new RuntimeException("序列生成器[" + tableName + "]没有配置，请检查主键表[csm_sql_tbl]是否有该" + tableName + "的记录 ");
			} else {
				if (logger.isInfoEnabled()) {
					logger.info("从数据库读取序列值    TableName:[" + csmTableSeq.getTableName() + "]    NextSeq:["
							+ csmTableSeq.getNextSeq() + "]    LastSeq:[" + csmTableSeq.getLastSeq() + "]");
				}

				sequence = new Sequence(csmTableSeq.getNextSeq(), csmTableSeq.getLastSeq());
				this.oX.put(tableName, sequence);
				return sequence;
			}
		}
	}
}