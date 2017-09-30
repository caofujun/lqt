package com.nis.log.interceptor;

import com.nis.comm.enums.ah;
import com.nis.comm.utils.AppContextUtil;
import com.nis.log.cache.SqlLogCache;
import com.nis.log.service.SysLogService;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class})})
public class SqlLogInterceptor implements Interceptor {
	private Properties properties;

	public Object intercept(Invocation invocation) throws Throwable {
		Object returnValue = null;
		String thread = Thread.currentThread().getName();
		SqlLogCache sqlLogCache = (SqlLogCache) AppContextUtil.getInstance().getBean(SqlLogCache.class);
		if (StringUtils.isNotBlank(sqlLogCache.bF(thread))) {
			MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
			Object parameter = null;
			if (invocation.getArgs().length > 1) {
				parameter = invocation.getArgs()[1];
			}

			String sqlId = mappedStatement.getId();
			BoundSql boundSql = mappedStatement.getBoundSql(parameter);
			Configuration configuration = mappedStatement.getConfiguration();
			String sql = a(configuration, boundSql, sqlId);
			SysLogService sysLogService = (SysLogService) AppContextUtil.getInstance().getBean(SysLogService.class);
			sysLogService.b(ah.iI, "", sqlLogCache.bF(thread), sql);
		}

		returnValue = invocation.proceed();
		return returnValue;
	}

	public static String a(Configuration configuration, BoundSql boundSql, String sqlId) {
		String sql = a(configuration, boundSql);
		StringBuilder str = new StringBuilder(100);
		str.append(sql);
		str.append(";");
		return str.toString();
	}

	private static String i(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "\'" + obj.toString() + "\'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(2, 2, Locale.CHINA);
			value = "\'" + formatter.format(new Date()) + "\'";
		} else if (obj != null) {
			value = obj.toString();
		} else {
			value = "";
		}

		return value;
	}

	public static String a(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", i(parameterObject));
			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				Iterator arg7 = parameterMappings.iterator();

				while (arg7.hasNext()) {
					ParameterMapping parameterMapping = (ParameterMapping) arg7.next();
					String propertyName = parameterMapping.getProperty();
					Object obj;
					if (metaObject.hasGetter(propertyName)) {
						obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", i(obj));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", i(obj));
					}
				}
			}
		}

		return sql;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return this.properties;
	}
}