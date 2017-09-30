package com.nis.mybatis.dialect;

import com.nis.mybatis.dialect.Dialect;

public class MySqlDialect implements Dialect {
	public String getDatabaseName() {
		return "mysql";
	}
}