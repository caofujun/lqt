package com.nis.mybatis.dialect;

import com.nis.mybatis.dialect.Dialect;

public class DB2Dialect implements Dialect {
	public String getDatabaseName() {
		return "db2";
	}
}