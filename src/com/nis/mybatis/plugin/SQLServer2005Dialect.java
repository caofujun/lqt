package com.nis.mybatis.plugin;

import com.nis.mybatis.plugin.Dialect;

public class SQLServer2005Dialect extends Dialect {
	public boolean af() {
		return true;
	}

	public boolean ag() {
		return true;
	}

	public String a(String querySqlString, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		StringBuffer pagingBuilder = new StringBuffer();
		String orderby = ci(querySqlString);
		String distinctStr = "";
		String loweredString = querySqlString.toLowerCase();
		String sqlPartString = querySqlString;
		if (loweredString.trim().startsWith("select")) {
			byte result = 6;
			if (loweredString.startsWith("select distinct")) {
				distinctStr = "DISTINCT ";
				result = 15;
			}

			sqlPartString = querySqlString.substring(result);
		}

		pagingBuilder.append(sqlPartString);
		if (orderby == null || orderby.length() == 0) {
			orderby = "ORDER BY CURRENT_TIMESTAMP";
		}

		StringBuffer result1 = new StringBuffer();
		result1.append("WITH query AS (SELECT ").append(distinctStr).append("TOP 100 PERCENT ")
				.append(" ROW_NUMBER() OVER (").append(orderby).append(") as __row_number__, ").append(pagingBuilder)
				.append(") SELECT * FROM query WHERE __row_number__ BETWEEN ").append(offset).append(" AND ")
				.append(offset + limit).append(" ORDER BY __row_number__");
		return result1.toString();
	}

	static String ci(String sql) {
		String loweredString = sql.toLowerCase();
		int orderByIndex = loweredString.indexOf("order by");
		return orderByIndex != -1 ? sql.substring(orderByIndex) : "";
	}
}