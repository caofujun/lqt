package com.nis.comm.enums;

import com.nis.comm.entity.KvEntity;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public enum ad {
	ie(Integer.valueOf(0), "正常"),iff(Integer.valueOf(50),"停止"), ig(Integer.valueOf(100), "待添加"), ih(Integer.valueOf(150),
			"添加异常"), ii(Integer.valueOf(500), "异常终止");

	public static final String KEY = "job_status";
	private Integer fv;
	private String name;
	private static List<KvEntity> list = new ArrayList();
	private static Map<Integer, String> map = new HashMap();

	static {
      EnumSet set = EnumSet.allOf(ad.class);
      Iterator arg1 = set.iterator();

      while(true) {
         ad e = null;
         do {
            if(!arg1.hasNext()) {
            	break;
            }

            e = (ad)arg1.next();
            map.put(e.getCode(), e.getName());
         } while(e.getCode().intValue() != ie.getCode().intValue() && e.getCode().intValue() != iff.getCode().intValue());

         list.add(new KvEntity(e.getCode().toString(), e.getName()));
         break;
      }
   }

	private ad(Integer code, String name) {
		this.fv = code;
		this.name = name;
	}

	public static String b(Integer code) {
		return (String) map.get(code);
	}

	public static List<KvEntity> getList() {
		return list;
	}

	public Integer getCode() {
		return this.fv;
	}

	public String getName() {
		return this.name;
	}
}