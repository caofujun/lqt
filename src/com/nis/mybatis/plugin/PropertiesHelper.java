package com.nis.mybatis.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class PropertiesHelper {
	public static final int SYSTEM_PROPERTIES_MODE_NEVER = 0;
	public static final int SYSTEM_PROPERTIES_MODE_FALLBACK = 1;
	public static final int SYSTEM_PROPERTIES_MODE_OVERRIDE = 2;
	private int vO = 0;
	private Properties vP;

	public PropertiesHelper(Properties p) {
		this.setProperties(p);
	}

	public PropertiesHelper(Properties p, int systemPropertiesMode) {
		this.setProperties(p);
		if (systemPropertiesMode != 0 && systemPropertiesMode != 1 && systemPropertiesMode != 2) {
			throw new IllegalArgumentException("error systemPropertiesMode mode:" + systemPropertiesMode);
		} else {
			this.vO = systemPropertiesMode;
		}
	}

	public Properties getProperties() {
		return this.vP;
	}

	public void setProperties(Properties props) {
		if (props == null) {
			throw new IllegalArgumentException("properties must be not null");
		} else {
			this.vP = props;
		}
	}

	public String bU(String key) {
		String value = this.getProperty(key);
		if (ch(value)) {
			throw new IllegalStateException("required property is blank by key=" + key);
		} else {
			return value;
		}
	}

	public String bV(String key) {
		String value = this.getProperty(key);
		return ch(value) ? null : value;
	}

	public String bW(String key) {
		String value = this.getProperty(key);
		return value != null && !"".equals(value) ? value : null;
	}

	public String bX(String key) {
		String value = this.getProperty(key);
		if (ch(value)) {
			value = this.getSystemProperty(key);
		}

		return value;
	}

	private String getSystemProperty(String key) {
		String value = System.getProperty(key);
		if (ch(value)) {
			value = System.getenv(key);
		}

		return value;
	}

	public Integer getInteger(String key) {
		String v = this.getProperty(key);
		return v == null ? null : Integer.valueOf(Integer.parseInt(v));
	}

	public int getInt(String key, int defaultValue) {
		return this.getProperty(key) == null ? defaultValue : Integer.parseInt(this.bU(key));
	}

	public int bY(String key) {
		return Integer.parseInt(this.bU(key));
	}

	public Long getLong(String key) {
		return this.getProperty(key) == null ? null : Long.valueOf(Long.parseLong(this.bU(key)));
	}

	public long getLong(String key, long defaultValue) {
		return this.getProperty(key) == null ? defaultValue : Long.parseLong(this.bU(key));
	}

	public Long bZ(String key) {
		return Long.valueOf(Long.parseLong(this.bU(key)));
	}

	public Boolean getBoolean(String key) {
		return this.getProperty(key) == null ? null : Boolean.valueOf(Boolean.parseBoolean(this.bU(key)));
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return this.getProperty(key) == null ? defaultValue : Boolean.parseBoolean(this.bU(key));
	}

	public boolean ca(String key) {
		return Boolean.parseBoolean(this.bU(key));
	}

	public Float cb(String key) {
		return this.getProperty(key) == null ? null : Float.valueOf(Float.parseFloat(this.bU(key)));
	}

	public float getFloat(String key, float defaultValue) {
		return this.getProperty(key) == null ? defaultValue : Float.parseFloat(this.bU(key));
	}

	public Float cc(String key) {
		return Float.valueOf(Float.parseFloat(this.bU(key)));
	}

	public Double cd(String key) {
		return this.getProperty(key) == null ? null : Double.valueOf(Double.parseDouble(this.bU(key)));
	}

	public double getDouble(String key, double defaultValue) {
		return this.getProperty(key) == null ? defaultValue : Double.parseDouble(this.bU(key));
	}

	public Double ce(String key) {
		return Double.valueOf(Double.parseDouble(this.bU(key)));
	}

	public Object d(String key, int value) {
		return this.setProperty(key, String.valueOf(value));
	}

	public Object a(String key, long value) {
		return this.setProperty(key, String.valueOf(value));
	}

	public Object a(String key, float value) {
		return this.setProperty(key, String.valueOf(value));
	}

	public Object a(String key, double value) {
		return this.setProperty(key, String.valueOf(value));
	}

	public Object b(String key, boolean value) {
		return this.setProperty(key, String.valueOf(value));
	}

	public String[] getStringArray(String key) {
		String v = this.getProperty(key);
		return v == null ? new String[0] : tokenizeToStringArray(v, ", \t\n\r\f");
	}

	public int[] cf(String key) {
		return j(this.getStringArray(key));
	}

	public Properties cg(String prefix) {
		if (prefix == null) {
			throw new IllegalArgumentException("\'prefix\' must be not null");
		} else {
			Properties props = this.getProperties();
			Properties result = new Properties();
			Iterator arg4 = props.entrySet().iterator();

			while (arg4.hasNext()) {
				Entry entry = (Entry) arg4.next();
				String key = (String) entry.getKey();
				if (key != null && key.startsWith(prefix)) {
					result.put(key.substring(prefix.length()), entry.getValue());
				}
			}

			return result;
		}
	}

	public String getProperty(String key, String defaultValue) {
		String value = this.getProperty(key);
		return ch(value) ? defaultValue : value;
	}

	public String getProperty(String key) {
		String propVal = null;
		if (this.vO == 2) {
			propVal = this.getSystemProperty(key);
		}

		if (propVal == null) {
			propVal = this.vP.getProperty(key);
		}

		if (propVal == null && this.vO == 1) {
			propVal = this.getSystemProperty(key);
		}

		return propVal;
	}

	public Object setProperty(String key, String value) {
		return this.vP.setProperty(key, value);
	}

	public void clear() {
		this.vP.clear();
	}

	public Set<Entry<Object, Object>> entrySet() {
		return this.vP.entrySet();
	}

	public Enumeration<?> propertyNames() {
		return this.vP.propertyNames();
	}

	public boolean contains(Object value) {
		return this.vP.contains(value);
	}

	public boolean containsKey(Object key) {
		return this.vP.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return this.vP.containsValue(value);
	}

	public Enumeration<Object> elements() {
		return this.vP.elements();
	}

	public Object get(Object key) {
		return this.vP.get(key);
	}

	public boolean isEmpty() {
		return this.vP.isEmpty();
	}

	public Enumeration<Object> keys() {
		return this.vP.keys();
	}

	public Set<Object> keySet() {
		return this.vP.keySet();
	}

	public void list(PrintStream out) {
		this.vP.list(out);
	}

	public void list(PrintWriter out) {
		this.vP.list(out);
	}

	public void load(InputStream inStream) throws IOException {
		this.vP.load(inStream);
	}

	public void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
		this.vP.loadFromXML(in);
	}

	public Object put(Object key, Object value) {
		return this.vP.put(key, value);
	}

	public void putAll(Map<? extends Object, ? extends Object> t) {
		this.vP.putAll(t);
	}

	public Object remove(Object key) {
		return this.vP.remove(key);
	}

	public void save(OutputStream out, String comments) {
		this.vP.save(out, comments);
	}

	public int size() {
		return this.vP.size();
	}

	public void store(OutputStream out, String comments) throws IOException {
		this.vP.store(out, comments);
	}

	public void storeToXML(OutputStream os, String comment, String encoding) throws IOException {
		this.vP.storeToXML(os, comment, encoding);
	}

	public void storeToXML(OutputStream os, String comment) throws IOException {
		this.vP.storeToXML(os, comment);
	}

	public Collection<Object> values() {
		return this.vP.values();
	}

	public String toString() {
		return this.vP.toString();
	}

	private static boolean ch(String value) {
		return value == null || "".equals(value.trim());
	}

	private static String[] tokenizeToStringArray(String str, String seperators) {
		StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
		ArrayList result = new ArrayList();

		while (tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}

		return (String[]) result.toArray(new String[result.size()]);
	}

	private static int[] j(String[] array) {
		int[] result = new int[array.length];

		for (int i = 0; i < array.length; ++i) {
			result[i] = Integer.parseInt(array[i]);
		}

		return result;
	}
}