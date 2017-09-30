package com.nis.mybatis.plugin;

import com.nis.mybatis.plugin.Dialect;

public class MySQLDialect extends Dialect {
	public boolean ag() {
		return true;
	}

	public boolean af() {
		return true;
	}

	public String a(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		return offset > 0
				? sql + " limit " + offsetPlaceholder + "," + limitPlaceholder
				: sql + " limit " + limitPlaceholder;
	}
}