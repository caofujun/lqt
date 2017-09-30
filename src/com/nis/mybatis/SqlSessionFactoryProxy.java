package com.nis.mybatis;

import com.nis.mybatis.SqlSessionProxy;
import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlSessionFactoryProxy implements SqlSessionFactory {
	private SqlSessionFactory vf;
	private String vg;

	public SqlSessionFactoryProxy(SqlSessionFactory sqlSessionFactory) {
		this.vf = sqlSessionFactory;
	}

	public SqlSessionFactoryProxy(SqlSessionFactory sqlSessionFactory, String dialectName) {
		this.vf = sqlSessionFactory;
		this.vg = dialectName;
	}

	public void setDialectName(String dialectName) {
		this.vg = dialectName;
	}

	public Configuration getConfiguration() {
		return this.vf.getConfiguration();
	}

	public SqlSession openSession() {
		SqlSession sqlSession = this.vf.openSession();
		SqlSessionProxy sqlSessionProxy = new SqlSessionProxy(sqlSession, this.vg);
		return sqlSessionProxy;
	}

	public SqlSession openSession(boolean autoCommit) {
		SqlSession sqlSession = this.vf.openSession(autoCommit);
		SqlSessionProxy sqlSessionProxy = new SqlSessionProxy(sqlSession, this.vg);
		return sqlSessionProxy;
	}

	public SqlSession openSession(Connection connection) {
		SqlSession sqlSession = this.vf.openSession(connection);
		SqlSessionProxy sqlSessionProxy = new SqlSessionProxy(sqlSession, this.vg);
		return sqlSessionProxy;
	}

	public SqlSession openSession(ExecutorType execType) {
		SqlSession sqlSession = this.vf.openSession(execType);
		SqlSessionProxy sqlSessionProxy = new SqlSessionProxy(sqlSession, this.vg);
		return sqlSessionProxy;
	}

	public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
		SqlSession sqlSession = this.vf.openSession(execType, autoCommit);
		SqlSessionProxy sqlSessionProxy = new SqlSessionProxy(sqlSession, this.vg);
		return sqlSessionProxy;
	}

	public SqlSession openSession(ExecutorType execType, Connection connection) {
		SqlSession sqlSession = this.vf.openSession(execType, connection);
		SqlSessionProxy sqlSessionProxy = new SqlSessionProxy(sqlSession, this.vg);
		return sqlSessionProxy;
	}

	public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
		SqlSession sqlSession = this.vf.openSession(execType, level);
		SqlSessionProxy sqlSessionProxy = new SqlSessionProxy(sqlSession, this.vg);
		return sqlSessionProxy;
	}

	public SqlSession openSession(TransactionIsolationLevel level) {
		SqlSession sqlSession = this.vf.openSession(level);
		SqlSessionProxy sqlSessionProxy = new SqlSessionProxy(sqlSession, this.vg);
		return sqlSessionProxy;
	}
}