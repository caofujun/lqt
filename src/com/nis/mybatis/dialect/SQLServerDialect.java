package com.nis.mybatis.dialect;

import com.nis.mybatis.dialect.Dialect;

public class SQLServerDialect implements Dialect {
	public String getDatabaseName() {
		return "sqlserver";
	}
}