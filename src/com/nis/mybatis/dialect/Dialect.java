package com.nis.mybatis.dialect;

public interface Dialect {
	String copyright = "Copyright 2011, Carefx Corporation";
	String vk = "$URL: https://192.168.66.26/svn/SourceCodeBase/framework/release/Framework2.1.3-20111025/framework-core/main/java/net/carefx/framework/mybatis/dialect/Dialect.java $";
	String vl = "$Date: 2011-06-21 16:30:37 +0800 (周二, 21 六月 2011) $";
	String vm = "$Author: dnie $";
	String vn = "$Revision: 36468 $";

	String getDatabaseName();
}