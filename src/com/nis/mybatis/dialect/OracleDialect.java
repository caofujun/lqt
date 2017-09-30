package com.nis.mybatis.dialect;

import com.nis.mybatis.dialect.Dialect;

public class OracleDialect implements Dialect {
	public String getDatabaseName() {
		return "oracle";
	}
}