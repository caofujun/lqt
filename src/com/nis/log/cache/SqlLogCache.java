package com.nis.log.cache;

import com.nis.comm.utils.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SqlLogCache {
	private String bD(String thread) {
		String key = "sqlLog_" + thread + "_" + Thread.currentThread().getId();
		return key;
	}

	public String bE(String thread) {
		String key = "sqlLog_flag_" + thread + "_" + Thread.currentThread().getId();
		return key;
	}

	public void B(String thread, String flag) {
		String key = this.bE(thread);
		d.set(key, flag);
	}

	public String bF(String thread) {
		String key = this.bE(thread);
		return (String) d.get(key);
	}

	public void bG(String thread) {
		String key = this.bE(thread);
		d.a(key);
	}

	public void C(String thread, String sql) {
		String key = this.bD(thread);
		Object data = (List) d.get(key);
		if (data == null) {
			data = new ArrayList();
		}

		((List) data).add(sql);
		d.set(key, data);
	}

	public List<String> bH(String thread) {
		String key = this.bD(thread);
		List data = (List) d.get(key);
		if (data != null) {
			d.a(key);
		}

		return data;
	}

	public String bI(String thread) {
		List sqls = this.bH(thread);
		StringBuffer sqlBuffer = new StringBuffer();
		if (sqls != null) {
			Iterator arg4 = sqls.iterator();

			while (arg4.hasNext()) {
				String sql = (String) arg4.next();
				sqlBuffer.append(sql).append("\t");
			}
		}

		return sqlBuffer.toString();
	}

	public void bJ(String thread) {
		String key = this.bD(thread);
		d.a(key);
	}
}