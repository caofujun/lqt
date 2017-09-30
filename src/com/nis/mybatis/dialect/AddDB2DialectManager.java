package com.nis.mybatis.dialect;

import com.nis.mybatis.dialect.DefalutDialectManager;
import com.nis.mybatis.dialect.DialectConstant;
import com.nis.mybatis.dialect.OracleDialect;

public class AddDB2DialectManager extends DefalutDialectManager {
	public void ae() {
		super.ae();
		this.vj.put(DialectConstant.vr, new OracleDialect());
	}
}