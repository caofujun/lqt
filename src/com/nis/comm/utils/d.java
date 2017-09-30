package com.nis.comm.utils;

import com.nis.comm.enums.bg;
import com.nis.comm.enums.j;
import com.nis.comm.utils.AppContextUtil;
import java.io.Serializable;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;

public class d {
	private static Logger logger = Logger.getLogger(d.class);

	private static Cache a(j cacheKey) {
		return ((CacheManager) AppContextUtil.getInstance().getBean(CacheManager.class)).getCache(cacheKey.getValue());
	}

	public static String a(bg seqTable, String expand) {
		StringBuffer result = new StringBuffer(seqTable.getSimp());
		result.append(expand.toString());
		return result.toString();
	}

	public static <T> T get(String key) {
		return a(j.fW, key);
	}

	public static <T> T a(j cacheKey, String key) {
		try {
			Element e = a(cacheKey).get(key);
			if (e == null) {
				return null;
			}

			Serializable object = e.getValue();
			if (object == null) {
				return null;
			}

			return (T)object;
		} catch (IllegalStateException arg3) {
			logger.error(arg3.getMessage(), arg3);
		} catch (CacheException arg4) {
			logger.error(arg4.getMessage(), arg4);
		}

		return null;
	}

	public static synchronized void set(String key, Object value) {
		a(j.fW, key, value, 0);
	}

	public static synchronized void a(j cacheKey, String key, Object value) {
		a(cacheKey, key, value, 0);
	}

	public static synchronized void a(String key, Object value, int endTime) {
		a(j.fW, key, value, endTime);
	}

	public static synchronized void a(j cacheKey, String key, Object value, int endTime) {
		try {
			Element e = new Element(key, value);
			if (endTime > 0) {
				e.setEternal(false);
				e.setTimeToLive(endTime);
			}

			a(cacheKey).put(e);
		} catch (IllegalArgumentException arg4) {
			logger.error(arg4.getMessage(), arg4);
		} catch (IllegalStateException arg5) {
			logger.error(arg5.getMessage(), arg5);
		} catch (CacheException arg6) {
			logger.error(arg6.getMessage(), arg6);
		}

	}

	public static void a(String key) {
		b(j.fW, key);
	}

	public static void b(j cacheKey, String key) {
		try {
			a(cacheKey).remove(key);
		} catch (IllegalStateException arg2) {
			logger.error(arg2.getMessage(), arg2);
		}

	}

	public static void clear() {
		b(j.fW);
	}

	public static void b(j cacheKey) {
		try {
			a(cacheKey).removeAll();
		} catch (IllegalStateException arg1) {
			logger.error(arg1.getMessage(), arg1);
		}

	}
}