package com.nis.mybatis.plugin;

import com.nis.mybatis.plugin.Dialect;

public class SQLServerDialect extends Dialect {
	public boolean ag() {
		return false;
	}

	public boolean af() {
		return true;
	}

	static int cj(String sql) {
		int selectIndex = sql.toLowerCase().indexOf("select");
		int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
	}

	public String a(String sql, int offset, int limit) {
		return this.a(sql, offset, (String) null, limit, (String) null);
	}

	public String a(String querySelect, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			throw new UnsupportedOperationException("sql server has no offset");
		} else {
			return (new StringBuffer(querySelect.length() + 8)).append(querySelect)
					.insert(cj(querySelect), " top " + limit).toString();
		}
	}
}