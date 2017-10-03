package com.nis.analysis.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nis.analysis.dao.Zg007DictDao;
import com.nis.analysis.entity.Zg007Dict;
import com.nis.analysis.entity.Zg007DictEn;
import com.nis.analysis.service.Zg007DictEnService;
import com.nis.analysis.service.Zg007DictService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;


class Zg007DictServiceImpl$1
implements Comparator<Zg007Dict>
{
Zg007DictServiceImpl$1(Zg007DictServiceImpl paramZg007DictServiceImpl) {}

public int compare(Zg007Dict o1, Zg007Dict o2)
{
  return o2.getItemName().compareTo(o1.getItemName());
}
}


class Zg007DictServiceImpl$2
implements Comparator<Zg007Dict>
{
Zg007DictServiceImpl$2(Zg007DictServiceImpl paramZg007DictServiceImpl) {}

public int compare(Zg007Dict d1, Zg007Dict d2)
{
  return d1.getItemName().compareTo(d2.getItemName());
}
}


@Component
public class Zg007DictServiceImpl implements Zg007DictService {
	private static final Logger c = Logger.getLogger(Zg007DictServiceImpl.class);
	@Autowired
	private Zg007DictDao cF;
	@Autowired
	private Zg007DictEnService cG;

	public void save(Zg007Dict zg007Dict) {
		if (zg007Dict.getKeyid() == null || "".equals(zg007Dict.getKeyid())) {
			zg007Dict.setKeyid(af.getUUID());
		}

		zg007Dict.encode();
		this.cF.save(zg007Dict);
	}

	public void delete(String keyid) {
		this.cF.delete(keyid);
	}

	public void update(Zg007Dict zg007Dict) {
		zg007Dict.encode();
		this.cF.update(zg007Dict);
	}

	public Zg007Dict get(String keyid) {
		return this.cF.get(keyid);
	}

	public MyPage<Zg007Dict> a(Zg007Dict zg007Dict) {
      String str = null;
      if(zg007Dict.getSearchString() != null && !"".equals(zg007Dict.getSearchString())) {
         str = ab.aR(zg007Dict.getSearchString());
      }

      zg007Dict.encode();
      int total = this.cF.findZg007DictCount(zg007Dict);
      List data = null;
      if(total > 0) {
         data = this.cF.findZg007Dict(zg007Dict);
         Iterator arg5 = data.iterator();

         while(arg5.hasNext()) {
            Zg007Dict dict = (Zg007Dict)arg5.next();
            dict.decode();
         }
      }

      if(data != null) {
         data = this.e(data, str);
         total = data.size();
      }

      Collections.sort(data, new Zg007DictServiceImpl$1(this));
      return new MyPage(1, total, total, data);
   }

	public List<Zg007Dict> e(List<Zg007Dict> data, String searchString) {
		if (searchString == null) {
			return data;
		} else {
			ArrayList data2 = new ArrayList();
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				Zg007Dict zg007Dict = (Zg007Dict) arg4.next();
				if (zg007Dict.getItemName() != null && zg007Dict.getItemName().indexOf(searchString) != -1) {
					data2.add(zg007Dict);
				}
			}

			return data2;
		}
	}

	public List<Zg007Dict> getAll() {
		return this.cF.getAll();
	}

	public boolean h() {
		try {
			List e = this.cG.getAll();
			if (e != null) {
				Zg007Dict zg007Dict = null;
				Iterator arg3 = e.iterator();

				while (arg3.hasNext()) {
					Zg007DictEn dict = (Zg007DictEn) arg3.next();
					zg007Dict = new Zg007Dict();
					this.delete(dict.getKeyid());
					BeanUtils.copyProperties(dict, zg007Dict);
					this.save(zg007Dict);
				}
			}

			return true;
		} catch (Exception arg4) {
			c.error("处理异常!", arg4);
			return false;
		}
	}

	public List<Zg007Dict> getByClass(String itemClass, String dictType) {
		return this.cF.getByClass(itemClass, dictType);
	}

	public Map<String, List<Zg007Dict>> f(String keyType, String dictType) {
		HashMap map = new HashMap();
		List list = this.cF.getByClass(keyType, dictType);
		if (list != null & list.size() > 0) {
			Iterator key = list.iterator();

			while (key.hasNext()) {
				Zg007Dict slist = (Zg007Dict) key.next();
				slist.decode();
			}

			List slist1 = this.g(list);
			String key1 = "";
			String oldKey = "";
			ArrayList mapList = null;

			for (Iterator arg9 = slist1.iterator(); arg9.hasNext(); oldKey = key1) {
				Zg007Dict d = (Zg007Dict) arg9.next();
				key1 = d.getItemName().substring(0, 1);
				if (!key1.equals(oldKey)) {
					mapList = new ArrayList();
					mapList.add(d);
					map.put(key1, mapList);
				} else {
					mapList.add(d);
				}
			}
		}

		return map;
	}

	public List<Zg007Dict> g(List<Zg007Dict> list) {
      if(list != null) {
         Collections.sort(list, new Zg007DictServiceImpl$2(this));
      }

      return list;
   }

	public void i() {
		List list = this.cF.getAll();
		new Zg007DictEn();
		Iterator arg3 = list.iterator();

		while (arg3.hasNext()) {
			Zg007Dict zg007Dict = (Zg007Dict) arg3.next();
			zg007Dict.decode();
			Zg007DictEn zg007DictEn = new Zg007DictEn();
			BeanUtils.copyProperties(zg007Dict, zg007DictEn);
			this.cG.save(zg007DictEn);
		}

	}

	public Zg007Dict getByElementId(String elementId) {
		Zg007Dict zg007Dict = null;
		if (StringUtils.isNotEmpty(elementId)) {
			zg007Dict = this.cF.getByElementId(EncryptUtils.ad(elementId));
			zg007Dict.decode();
		}

		return zg007Dict;
	}

	public Map<String, Zg007Dict> g(String keyType, String dictType) {
		HashMap map = new HashMap();
		List list = this.cF.getByClass(keyType, dictType);
		if (list != null & list.size() > 0) {
			Iterator arg5 = list.iterator();

			while (arg5.hasNext()) {
				Zg007Dict d = (Zg007Dict) arg5.next();
				d.decode();
				map.put(d.getItemName(), d);
			}
		}

		return map;
	}
}