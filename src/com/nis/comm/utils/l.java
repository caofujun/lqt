package com.nis.comm.utils;

import com.nis.comm.utils.ab;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class l {
	private static final Logger c = Logger.getLogger(l.class);
	private static ObjectMapper pF;

	private static ObjectMapper getMapperInstance() {
		return i(false);
	}

	private static synchronized ObjectMapper i(boolean createNew) {
		if (createNew) {
			ObjectMapper simpleDateFormat1 = new ObjectMapper();
			simpleDateFormat1.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			return simpleDateFormat1;
		} else {
			if (pF == null) {
				pF = new ObjectMapper();
			}

			pF.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pF.getSerializationConfig().setDateFormat(simpleDateFormat);
			return pF;
		}
	}

	public static String toString(Object obj) {
		try {
			ObjectMapper e = getMapperInstance();
			StringWriter sw = new StringWriter();
			JsonGenerator gen = (new JsonFactory()).createJsonGenerator(sw);
			e.writeValue(gen, obj);
			return sw.toString();
		} catch (IOException arg3) {
			c.error(arg3.getMessage(), arg3);
			return null;
		}
	}

	public static <T> T toObject(String string, Class<?> cls) {
		try {
			if (ab.isEmpty(string)) {
				return null;
			} else {
				ObjectMapper e = getMapperInstance();
				e.configure(org.codehaus.jackson.map.SerializationConfig.Feature.INDENT_OUTPUT,
						Boolean.TRUE.booleanValue());
				return e.readValue(string, cls);
			}
		} catch (IOException arg2) {
			c.error(arg2.getMessage());
			return null;
		}
	}

	public static <T> T ar(String string) {
		try {
			if (ab.isEmpty(string)) {
				return null;
			} else {
				ObjectMapper e = getMapperInstance();
				JavaType type = e.getTypeFactory().constructType(Map.class);
				return e.readValue(string, type);
			}
		} catch (IOException arg2) {
			c.error(arg2.getMessage());
			return null;
		}
	}

	public static <T> T a(String string, Class<?> cls) {
		try {
			ObjectMapper e = getMapperInstance();
			JavaType type = e.getTypeFactory().constructParametricType(List.class, new Class[]{cls});
			return e.readValue(string, type);
		} catch (IOException arg3) {
			c.error(arg3.getMessage());
			return null;
		}
	}

	public static JsonNode as(String string) {
		try {
			ObjectMapper e = getMapperInstance();
			return e.readTree(string);
		} catch (IOException arg1) {
			c.error(arg1.getMessage(), arg1);
			return null;
		}
	}
}