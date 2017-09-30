package com.nis.mybatis.plugin;

public class Dialect {
	public boolean af() {
		return false;
	}

	public boolean ag() {
		return this.af();
	}

	public String a(String sql, int offset, int limit) {
		return this.a(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
	}

	public String a(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		throw new UnsupportedOperationException("paged queries not supported");
	}
}