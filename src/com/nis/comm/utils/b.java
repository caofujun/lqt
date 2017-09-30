package com.nis.comm.utils;

import com.nis.comm.utils.u;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class b {
	public static <T> T a(Object obj, Collection<T> cols, u<T> propertyExtractor) {
		return propertyExtractor.a(cols, obj);
	}

	public static void a(Map<String, Object> map, Object obj) {
		try {
			BeanInfo e = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = e.getPropertyDescriptors();
			PropertyDescriptor[] arg6 = propertyDescriptors;
			int arg5 = propertyDescriptors.length;

			for (int arg4 = 0; arg4 < arg5; ++arg4) {
				PropertyDescriptor property = arg6[arg4];
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					Method setter = property.getWriteMethod();
					setter.invoke(obj, new Object[]{value});
				}
			}
		} catch (Exception arg10) {
			arg10.printStackTrace();
			System.out.println("transMap2Bean Error " + arg10);
		}

	}

	public static Map<String, Object> a(Object obj) {
		if (obj == null) {
			return null;
		} else {
			HashMap map = new HashMap();

			try {
				BeanInfo e = Introspector.getBeanInfo(obj.getClass());
				PropertyDescriptor[] propertyDescriptors = e.getPropertyDescriptors();
				PropertyDescriptor[] arg6 = propertyDescriptors;
				int arg5 = propertyDescriptors.length;

				for (int arg4 = 0; arg4 < arg5; ++arg4) {
					PropertyDescriptor property = arg6[arg4];
					String key = property.getName();
					if (!key.equals("class")) {
						Method getter = property.getReadMethod();
						Object value = getter.invoke(obj, new Object[0]);
						map.put(key, value);
					}
				}
			} catch (Exception arg10) {
				arg10.printStackTrace();
				System.out.println("transBean2Map Error " + arg10);
			}

			return map;
		}
	}
}