package com.nis.mybatis;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class SqlSessionProxy implements SqlSession {
	private SqlSession vh;
	private String vg;

	public SqlSessionProxy(SqlSession sqlSession, String dialectName) {
		this.vh = sqlSession;
		this.vg = dialectName;
	}

	public String bR(String statement) {
		try {
			DatabaseMetaData dbMD = this.vh.getConnection().getMetaData();
			String databaseName = dbMD.getDatabaseProductName().toLowerCase();
			Configuration configuration = this.vh.getConfiguration();
			Collection statements = configuration.getMappedStatementNames();
			if (!statements.contains(statement)) {
				int lastPointIndex = statement.lastIndexOf(".");
				String curAllClassPath = statement.substring(0, lastPointIndex);
				String curFunctionStatement = statement.substring(lastPointIndex);
				StringBuffer sb = new StringBuffer();
				sb.append(curAllClassPath + "_" + databaseName);
				sb.append(curFunctionStatement + "_" + databaseName);
				statement = sb.toString();
			}
		} catch (SQLException arg9) {
			;
		}

		return statement;
	}

	public void select(String statement, Object parameter, ResultHandler handler) {
		this.vh.select(this.bR(statement), parameter, handler);
	}

	public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		this.vh.select(this.bR(statement), parameter, rowBounds, handler);
	}

	public int insert(String statement) {
		return this.vh.insert(this.bR(statement));
	}

	public int insert(String statement, Object parameter) {
		return this.vh.insert(this.bR(statement), parameter);
	}

	public int update(String statement) {
		return this.vh.update(this.bR(statement));
	}

	public int update(String statement, Object parameter) {
		return this.vh.update(this.bR(statement), parameter);
	}

	public int delete(String statement) {
		return this.vh.delete(this.bR(statement));
	}

	public int delete(String statement, Object parameter) {
		return this.vh.delete(this.bR(statement), parameter);
	}

	public void commit() {
		this.vh.commit();
	}

	public void commit(boolean force) {
		this.vh.commit(force);
	}

	public void rollback() {
		this.vh.rollback();
	}

	public void rollback(boolean force) {
		this.vh.rollback(force);
	}

	public void close() {
		this.vh.close();
	}

	public void clearCache() {
		this.vh.clearCache();
	}

	public Configuration getConfiguration() {
		return this.vh.getConfiguration();
	}

	public <T> T getMapper(Class<T> type) {
		return this.vh.getMapper(type);
	}

	public Connection getConnection() {
		return this.vh.getConnection();
	}

	public void select(String arg0, ResultHandler arg1) {
		this.vh.select(arg0, arg1);
	}

	public <K, V> Map<K, V> selectMap(String arg0, String arg1) {
		return this.vh.selectMap(arg0, arg1);
	}

	public <K, V> Map<K, V> selectMap(String arg0, Object arg1, String arg2) {
		return this.vh.selectMap(arg0, arg1, arg2);
	}

	public <K, V> Map<K, V> selectMap(String arg0, Object arg1, String arg2, RowBounds arg3) {
		return this.vh.selectMap(arg0, arg1, arg2, arg3);
	}

	public List<BatchResult> flushStatements() {
		return this.vh.flushStatements();
	}

	public <E> List<E> selectList(String statement) {
		return this.vh.selectList(this.bR(statement));
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		return this.vh.selectList(this.bR(statement), parameter);
	}

	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
		return this.vh.selectList(this.bR(statement), parameter, rowBounds);
	}

	public <T> T selectOne(String statement) {
		return this.vh.selectOne(this.bR(statement));
	}

	public <T> T selectOne(String statement, Object parameter) {
		return this.vh.selectOne(this.bR(statement), parameter);
	}
}