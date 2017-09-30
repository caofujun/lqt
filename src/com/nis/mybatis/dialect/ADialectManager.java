package com.nis.mybatis.dialect;

import com.nis.mybatis.dialect.Dialect;
import java.util.HashMap;
import java.util.Map;

public abstract class ADialectManager {
	protected Map<String, Dialect> vj = new HashMap();

	public Dialect bS(String dataBaseName) {
		return (Dialect) this.vj.get(dataBaseName);
	}

	public boolean bT(String dataBaseName) {
		return this.vj.containsKey(dataBaseName);
	}

	public abstract void ae();
}