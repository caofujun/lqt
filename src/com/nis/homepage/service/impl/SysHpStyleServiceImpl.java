package com.nis.homepage.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.n;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.b;
import com.nis.comm.utils.u;
import com.nis.comm.utils.z;
import com.nis.homepage.dao.SysHpStyleDao;
import com.nis.homepage.entity.SysHpComponent;
import com.nis.homepage.entity.SysHpLayout;
import com.nis.homepage.entity.SysHpStyle;
import com.nis.homepage.service.SysHpComponentService;
import com.nis.homepage.service.SysHpLayoutService;
import com.nis.homepage.service.SysHpStyleService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


class SysHpStyleServiceImpl$1
implements u<Map<String, Object>>
{
SysHpStyleServiceImpl$1(SysHpStyleServiceImpl paramSysHpStyleServiceImpl) {}

public Map<String, Object> a(Collection<Map<String, Object>> cols, Object obj)
{
  for (Map<String, Object> map : cols) {
    if (ab.g(map.get("menuNo")).equalsIgnoreCase(ab.g(obj))) {
      return map;
    }
  }
  return null;
}

}


@Component
public class SysHpStyleServiceImpl implements SysHpStyleService {
	@Autowired
	private SysHpStyleDao rm;
	@Autowired
	private SysHpLayoutService rj;
	@Autowired
	private SysHpComponentService rh;
	@Autowired
	private FreeMarkerConfig rn;

	public void save(SysHpStyle sysHpStyle) {
		sysHpStyle.setId(z.a(bg.mw));
		if (ab.isNotEmpty(sysHpStyle.getUserName())) {
			sysHpStyle.setScopeLevel(n.gh.getValue().toString());
		} else if (ab.isNotEmpty(sysHpStyle.getDepNo())) {
			sysHpStyle.setScopeLevel(n.gi.getValue().toString());
			sysHpStyle.setUserName((String) null);
		} else if (sysHpStyle.getUnitId() != null && "0".equals(sysHpStyle.getUnitId())) {
			sysHpStyle.setScopeLevel(n.gk.getValue().toString());
			sysHpStyle.setUserName((String) null);
		} else {
			sysHpStyle.setScopeLevel(n.gm.getValue().toString());
			sysHpStyle.setUnitId("0");
			sysHpStyle.setUserName((String) null);
		}

		this.rm.save(sysHpStyle);
	}

	public void delete(String id) {
		this.rm.delete(id);
	}

	public void update(SysHpStyle sysHpStyle) {
		if (ab.isNotEmpty(sysHpStyle.getUserName())) {
			sysHpStyle.setScopeLevel(n.gh.getValue().toString());
		} else if (ab.isNotEmpty(sysHpStyle.getDepNo())) {
			sysHpStyle.setScopeLevel(n.gi.getValue().toString());
			sysHpStyle.setUserName((String) null);
		} else if (sysHpStyle.getUnitId() != null && "0".equals(sysHpStyle.getUnitId())) {
			sysHpStyle.setScopeLevel(n.gk.getValue().toString());
			sysHpStyle.setUserName((String) null);
		} else {
			sysHpStyle.setScopeLevel(n.gm.getValue().toString());
			sysHpStyle.setUnitId("0");
			sysHpStyle.setUserName((String) null);
		}

		this.rm.update(sysHpStyle);
	}

	public SysHpStyle get(String id) {
		return this.rm.get(id);
	}

	public MyPage<SysHpStyle> a(SysHpStyle sysHpStyle) {
		int total = this.rm.findSysHpStyleCount(sysHpStyle);
		List data = null;
		if (total > 0) {
			data = this.rm.findSysHpStyle(sysHpStyle);
		}

		return new MyPage(sysHpStyle.getPage().intValue(), sysHpStyle.getSize().intValue(), total, data);
	}

	public List<SysHpStyle> getAll() {
		return this.rm.getAll();
	}

	public SysHpStyle a(Long scopeLevel, String unitId, String depNo, String userName) {
		List styles = this.rm.findByUnitIdDepNoUserName(scopeLevel, unitId, depNo, userName);
		SysHpStyle sysHpStyle = styles.size() > 0 ? (SysHpStyle) styles.get(0) : null;
		return sysHpStyle;
	}

	public SysHpStyle a(int dataScope, String unitId, String depNo, String username) {
		SysHpStyle sysHpStyle = this.a((Long) null, unitId, depNo, username);
		if (sysHpStyle == null && StringUtils.isNotBlank(depNo)) {
			sysHpStyle = this.a((Long) null, unitId, depNo, (String) null);
		}

		if (sysHpStyle == null && StringUtils.isNotBlank(unitId)) {
			sysHpStyle = this.a((Long) null, unitId, (String) null, (String) null);
		}

		if (sysHpStyle == null) {
			sysHpStyle = this.a(new Long((long) dataScope), (String) null, (String) null, (String) null);
		}

		if (sysHpStyle == null) {
			sysHpStyle = this.a((Long) null, (String) null, (String) null, (String) null);
		}

		return sysHpStyle;
	}

	public String a(String layoutCode, String componentCodes, LoginUser loginUser, String sourceType, List<Map<String, Object>> menus) {
      SysHpLayout sysHpLayout = this.rj.getLayoutCode(layoutCode);
      if(sysHpLayout == null) {
         return null;
      } else {
         try {
            Configuration e = this.rn.getConfiguration();
            HashMap map = new HashMap();
            ArrayList components = new ArrayList();
            if(ab.isNotEmpty(componentCodes)) {
               String[] tpl = componentCodes.split(",");
               String[] arg13 = tpl;
               int arg12 = tpl.length;

               for(int arg11 = 0; arg11 < arg12; ++arg11) {
                  String componentCode = arg13[arg11];
                  SysHpComponent component = this.rh.getComponentCode(componentCode);
                  if(component != null) {
                     if(ab.isNotEmpty(component.getMenuCode())) {
                        Map compMap = (Map)b.a(component.getMenuCode(), menus, new SysHpStyleServiceImpl$1(this));
                        if(compMap == null) {
                           continue;
                        }
                     }

                     HashMap arg22 = new HashMap();
                     arg22.put("component", component);
                     arg22.put("loginUser", loginUser);
                     arg22.put("sourceType", sourceType);
                     arg22.put("webroot", com.nis.comm.constants.b.es);

                     try {
                        Template e1 = e.getTemplate(component.getComponentUrl());
                        String compHtml = FreeMarkerTemplateUtils.processTemplateIntoString(e1, arg22);
                        component.setHtml(compHtml);
                     } catch (Exception arg18) {
                        arg18.printStackTrace();
                        continue;
                     }

                     components.add(component);
                  }
               }
            }

            map.put("webroot", com.nis.comm.constants.b.es);
            map.put("components", components);
            map.put("sourceType", sourceType);
            Template arg21 = e.getTemplate(sysHpLayout.getLayoutUrl());
            return FreeMarkerTemplateUtils.processTemplateIntoString(arg21, map);
         } catch (IOException arg19) {
            arg19.printStackTrace();
         } catch (TemplateException arg20) {
            arg20.printStackTrace();
         }

         return null;
      }
   }
}