package com.nis.mybatis.dialect;

import com.nis.mybatis.dialect.ADialectManager;
import com.nis.mybatis.dialect.DialectConstant;
import com.nis.mybatis.dialect.MySqlDialect;
import com.nis.mybatis.dialect.OracleDialect;
import com.nis.mybatis.dialect.SQLServerDialect;

public class DefalutDialectManager extends ADialectManager {
	public void ae() {
		this.vj.put(DialectConstant.vo, new OracleDialect());
		this.vj.put(DialectConstant.vp, new SQLServerDialect());
		this.vj.put(DialectConstant.vq, new MySqlDialect());
	}
}