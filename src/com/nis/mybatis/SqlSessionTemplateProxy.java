package com.nis.mybatis;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.PersistenceExceptionTranslator;

public class SqlSessionTemplateProxy extends SqlSessionTemplate {
	private SqlSessionTemplate vi;

	public SqlSessionTemplateProxy(SqlSessionFactory sqlSessionFactory, SqlSessionTemplate sqlSessionTemplate) {
		super(sqlSessionFactory, sqlSessionFactory.getConfiguration().getDefaultExecutorType());
		this.vi = sqlSessionTemplate;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return this.vi.getSqlSessionFactory();
	}

	public ExecutorType getExecutorType() {
		return this.vi.getExecutorType();
	}

	public PersistenceExceptionTranslator getPersistenceExceptionTranslator() {
		return this.vi.getPersistenceExceptionTranslator();
	}

	public Object selectOne(String statement) {
		return this.vi.selectOne(statement);
	}

	public Object selectOne(String statement, Object parameter) {
		return this.vi.selectOne(statement, parameter);
	}

	public Map<?, ?> selectMap(String statement, String mapKey) {
		return this.vi.selectMap(statement, mapKey);
	}

	public Map<?, ?> selectMap(String statement, Object parameter, String mapKey) {
		return this.vi.selectMap(statement, parameter, mapKey);
	}

	public Map<?, ?> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		return this.vi.selectMap(statement, parameter, mapKey, rowBounds);
	}

	public List<?> selectList(String statement) {
		return this.vi.selectList(statement);
	}

	public List<?> selectList(String statement, Object parameter) {
		return this.vi.selectList(statement, parameter);
	}

	public List<?> selectList(String statement, Object parameter, RowBounds rowBounds) {
		return this.vi.selectList(statement, parameter, rowBounds);
	}

	public void select(String statement, ResultHandler handler) {
		this.vi.select(statement, handler);
	}

	public void select(String statement, Object parameter, ResultHandler handler) {
		this.vi.select(statement, parameter, handler);
	}

	public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		this.vi.select(statement, parameter, rowBounds, handler);
	}

	public int insert(String statement) {
		return this.vi.insert(statement);
	}

	public int insert(String statement, Object parameter) {
		return this.vi.insert(statement, parameter);
	}

	public int update(String statement) {
		return this.vi.update(statement);
	}

	public int update(String statement, Object parameter) {
		return this.vi.update(statement, parameter);
	}

	public int delete(String statement) {
		return this.vi.delete(statement);
	}

	public int delete(String statement, Object parameter) {
		return this.vi.delete(statement, parameter);
	}

	public <T> T getMapper(Class<T> type) {
		return this.vi.getMapper(type);
	}

	public void commit() {
		this.vi.commit();
	}

	public void commit(boolean force) {
		this.vi.commit(force);
	}

	public void rollback() {
		this.vi.rollback();
	}

	public void rollback(boolean force) {
		this.vi.rollback(force);
	}

	public void close() {
		this.vi.close();
	}

	public void clearCache() {
		this.vi.clearCache();
	}

	public Configuration getConfiguration() {
		return this.vi.getConfiguration();
	}

	public Connection getConnection() {
		return this.vi.getConnection();
	}
}