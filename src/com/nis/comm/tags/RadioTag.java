package com.nis.comm.tags;

import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.l;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;

public class RadioTag extends TagSupport {
	private static final long serialVersionUID = 7293280902947614510L;
	private static final Logger logger = Logger.getLogger(RadioTag.class);
	private String id;
	private String name;
	private String value;
	private String po;
	private String pi;
	private List<KvEntity> pl;
	private String pp;
	private String exp;

	public int doStartTag() throws JspException {
		try {
			JspWriter e = this.pageContext.getOut();
			if (this.pl == null && ab.isEmpty(this.pp)) {
				logger.error("items is null");
				return 0;
			} else {
				StringBuffer result = new StringBuffer();
				if (ab.isNotEmpty(this.pp)) {
					String i = (String) this.pageContext.getSession().getAttribute("user_json");
					LoginUser kvEntity = (LoginUser) l.toObject(i, LoginUser.class);
					SysDictService sysDcitService = (SysDictService) AppContextUtil.getInstance()
							.getBean(SysDictService.class);
					this.pl = new ArrayList();
					String unitId = null;
					if (kvEntity != null) {
						unitId = kvEntity.getUnitId();
					}

					List list = sysDcitService.u(this.pp, unitId);
					Iterator arg8 = list.iterator();

					while (arg8.hasNext()) {
						SysDict sysDcit = (SysDict) arg8.next();
						this.pl.add(new KvEntity(sysDcit.getDictCode(), sysDcit.getDictName()));
					}
				}

				if (ab.isEmpty(this.value)) {
					this.value = this.po;
				}

				for (int arg10 = 0; arg10 < this.pl.size(); ++arg10) {
					KvEntity arg11 = (KvEntity) this.pl.get(arg10);
					result.append("<label><input type=\"radio\" id=\"").append(this.getId()).append("\" name=\"")
							.append(this.getName()).append("\" class=\"").append(this.getCssCls()).append("\" ")
							.append(this.getExp());
					result.append(" value=\"").append(arg11.getKey()).append("\"");
					if (ab.isNotEmpty(this.value) && this.value.equals(arg11.getKey())) {
						result.append(" checked=\"checked\"");
					}

					result.append(" />").append("&nbsp;" + arg11.getValue()).append("</label>");
					if (arg10 != this.pl.size()) {
						result.append("&nbsp;&nbsp;&nbsp;");
					}
				}

				e.print(result.toString());
				return 0;
			}
		} catch (Exception arg9) {
			throw new JspException(arg9.getMessage());
		}
	}

	public int doEndTag() throws JspException {
		return 6;
	}

	public void release() {
		super.release();
		this.id = null;
		this.name = null;
		this.pi = null;
		this.value = null;
		this.po = null;
		this.pl = null;
		this.pp = null;
		this.exp = null;
	}

	public String getId() {
		return this.id == null ? "" : this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name == null ? "" : this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCssCls() {
		return this.pi == null ? "" : this.pi;
	}

	public void setCssCls(String cssCls) {
		this.pi = cssCls;
	}

	public List<KvEntity> getItems() {
		return this.pl;
	}

	public void setItems(List<KvEntity> items) {
		this.pl = items;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDictcode() {
		return this.pp;
	}

	public void setDictcode(String dictcode) {
		this.pp = dictcode;
	}

	public String getExp() {
		return this.exp == null ? "" : this.exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getDefvalue() {
		return this.po;
	}

	public void setDefvalue(String defvalue) {
		this.po = defvalue;
	}
}