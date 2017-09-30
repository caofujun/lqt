package com.nis.mybatis;

import com.nis.mybatis.dialect.ADialectManager;
import com.nis.mybatis.dialect.Dialect;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.util.ReflectionUtils;

public class SqlSessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean {
	public static final String vd = "FRAMEWORK-ERR-00000014";
	private ADialectManager ve = null;

	public ADialectManager getDialectManager() {
		return this.ve;
	}

	public void setDialectManager(ADialectManager dialectManager) {
		this.ve = dialectManager;
		this.ve.ae();
	}

	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		SqlSessionFactory sqlSessionFactory = super.buildSqlSessionFactory();
		Dialect dialect = this.a(sqlSessionFactory);
		Configuration configuration = sqlSessionFactory.getConfiguration();
		Object[] statements = configuration.getMappedStatements().toArray(new Object[0]);
		Object[] arg7 = statements;
		int arg6 = statements.length;

		for (int arg5 = 0; arg5 < arg6; ++arg5) {
			Object statementTmp = arg7[arg5];
			if (statementTmp instanceof MappedStatement) {
				MappedStatement statement = (MappedStatement) statementTmp;
				String id = statement.getId();
				int index = id.lastIndexOf("_" + dialect.getDatabaseName());
				if (index > 0) {
					String newId = null;
					if (id.endsWith("!selectKey")) {
						newId = id.substring(0, index) + "!selectKey";
					} else {
						newId = id.substring(0, index);
					}

					KeyGenerator keyGenerator = statement.getKeyGenerator();
					Field idField = ReflectionUtils.findField(MappedStatement.class, "id");
					idField.setAccessible(true);
					ReflectionUtils.setField(idField, statement, newId);
					if (configuration.hasStatement(newId)) {
						Field tmpStatementId = ReflectionUtils.findField(Configuration.class, "mappedStatements");
						tmpStatementId.setAccessible(true);
						Map cache = (Map) ReflectionUtils.getField(tmpStatementId, configuration);
						cache.remove(newId);
					}

					if (keyGenerator != null && keyGenerator instanceof SelectKeyGenerator) {
						SelectKeyGenerator arg22 = (SelectKeyGenerator) keyGenerator;
						Field arg24 = ReflectionUtils.findField(SelectKeyGenerator.class, "keyStatement");
						arg24.setAccessible(true);
						MappedStatement mappedStatementsFlushCacheRequiredField = (MappedStatement) ReflectionUtils
								.getField(arg24, arg22);
						String mappedStatementsUseCacheField = mappedStatementsFlushCacheRequiredField.getId();
						index = mappedStatementsUseCacheField
								.lastIndexOf("_" + dialect.getDatabaseName() + "!selectKey");
						if (index > 0) {
							mappedStatementsUseCacheField = mappedStatementsUseCacheField.substring(0, index)
									+ "!selectKey";
							ReflectionUtils.setField(idField, mappedStatementsFlushCacheRequiredField,
									mappedStatementsUseCacheField);
						}

						String mappedStatementsCacheField = id + "!selectKey";
						if (configuration.hasStatement(mappedStatementsCacheField)) {
							Field mappedStatementsField = ReflectionUtils.findField(Configuration.class,
									"mappedStatements");
							mappedStatementsField.setAccessible(true);
							Map mappedStatements = (Map) ReflectionUtils.getField(mappedStatementsField, configuration);
							mappedStatements.remove(mappedStatementsCacheField);
							if (mappedStatements.containsKey(mappedStatementsUseCacheField)) {
								mappedStatements.remove(mappedStatementsUseCacheField);
							}

							mappedStatements.put(mappedStatementsUseCacheField,
									mappedStatementsFlushCacheRequiredField);
						}
					}

					String arg23 = id.substring(0, id.lastIndexOf("."));
					if (configuration.getCacheNames().contains(arg23)) {
						Cache arg25 = configuration.getCache(arg23);
						if (arg25 != null) {
							Field arg26 = ReflectionUtils.findField(MappedStatement.class, "flushCacheRequired");
							arg26.setAccessible(true);
							ReflectionUtils.setField(arg26, statement,
									Boolean.valueOf(statement.isFlushCacheRequired()));
							Field arg27 = ReflectionUtils.findField(MappedStatement.class, "useCache");
							arg27.setAccessible(true);
							ReflectionUtils.setField(arg27, statement, Boolean.valueOf(statement.isUseCache()));
							Field arg28 = ReflectionUtils.findField(MappedStatement.class, "cache");
							arg28.setAccessible(true);
							ReflectionUtils.setField(arg28, statement, arg25);
						}
					}

					configuration.addMappedStatement(statement);
				}
			}
		}

		return sqlSessionFactory;
	}

	private Dialect a(SqlSessionFactory sqlSessionFactory) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String databaseName = null;

		try {
			databaseName = sqlSession.getConnection().getMetaData().getDatabaseProductName().toLowerCase();
		} catch (Exception arg7) {
			arg7.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}

		}

		this.ve.bT(databaseName);
		return this.ve.bS(databaseName);
	}
}