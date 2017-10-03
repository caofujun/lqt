package com.nis.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


class BoundSqlSqlSource
implements SqlSource
{
BoundSql boundSql;

public BoundSqlSqlSource(BoundSql boundSql)
{
  this.boundSql = boundSql;
}

public BoundSql getBoundSql(Object parameterObject)
{
  return this.boundSql;
}
}



@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class})})
public class OffsetLimitInterceptor implements Interceptor {
	static int vJ = 0;
	static int vK = 1;
	static int vL = 2;
	static int vM = 3;
	Dialect vN;

	public Object intercept(Invocation invocation) throws Throwable {
		this.b(invocation.getArgs());
		return invocation.proceed();
	}

	void b(Object[] queryArgs) {
		MappedStatement ms = (MappedStatement) queryArgs[vJ];
		Object parameter = queryArgs[vK];
		RowBounds rowBounds = (RowBounds) queryArgs[vL];
		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();
		if (this.vN.af() && (offset != 0 || limit != Integer.MAX_VALUE)) {
			BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql().trim();
			if (this.vN.ag()) {
				sql = this.vN.a(sql, offset, limit);
				offset = 0;
			} else {
				sql = this.vN.a(sql, 0, limit);
			}

			limit = Integer.MAX_VALUE;
			queryArgs[vL] = new RowBounds(offset, limit);
			BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			MappedStatement newMs = this.a(ms, new BoundSqlSqlSource(newBoundSql));
			queryArgs[vJ] = newMs;
		}

	}

	private MappedStatement a(MappedStatement ms, SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null) {
			builder.keyProperty(ms.getKeyProperties().toString());
		}

		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		String dialectClass = (new PropertiesHelper(properties)).bU("dialectClass");

		try {
			this.vN = (Dialect) Class.forName(dialectClass).newInstance();
		} catch (Exception arg3) {
			throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, arg3);
		}
	}
}