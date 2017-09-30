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

public class SelectTag extends TagSupport {
	private static final long serialVersionUID = 7293280902947614510L;
	private static final Logger logger = Logger.getLogger(SelectTag.class);
	private String id;
	private String name;
	private String value;
	private String pi;
	private String pj;
	private String pk;
	private List<KvEntity> pl;
	private String[] pm;
	private String pp;
	private String pn;
	private String exp;

	public int doStartTag() throws JspException {
		try {
			JspWriter e = this.pageContext.getOut();
			if (this.pl == null && ab.isEmpty(this.pp)) {
				logger.error("items is null");
				return 0;
			} else {
				StringBuffer result = new StringBuffer();
				result.append("<select id=\"").append(this.getId()).append("\" name=\"").append(this.getName())
						.append("\" class=\"").append(this.getCssCls()).append("\" ").append(this.getExp()).append(">");
				if (this.pk != null) {
					result.append("<option value=\"").append(this.pj).append("\">").append(this.pk).append("</option>");
				}

				if (ab.isNotEmpty(this.pp)) {
					String kvEntity = (String) this.pageContext.getSession().getAttribute("user_json");
					SysDictService sysDcitService = (SysDictService) AppContextUtil.getInstance()
							.getBean(SysDictService.class);
					this.pl = new ArrayList();
					String unitId = null;
					if (kvEntity != null) {
						LoginUser list = (LoginUser) l.toObject(kvEntity, LoginUser.class);
						unitId = list.getUnitId();
					}

					List arg16 = sysDcitService.u(this.pp, unitId);
					ArrayList listCopy = new ArrayList();
					Iterator dict = arg16.iterator();

					SysDict sysDcit;
					while (dict.hasNext()) {
						sysDcit = (SysDict) dict.next();
						listCopy.add(sysDcit.clone());
					}

					if (this.pm != null && this.pm.length > 0) {
						Iterator arg17 = listCopy.iterator();

						while (arg17.hasNext()) {
							SysDict arg18 = (SysDict) arg17.next();
							String[] arg12 = this.pm;
							int arg11 = this.pm.length;

							for (int arg10 = 0; arg10 < arg11; ++arg10) {
								String excludeCode = arg12[arg10];
								if (arg18.getDictCode().equals(excludeCode)) {
									arg17.remove();
								}
							}
						}
					}

					dict = listCopy.iterator();

					label82 : while (true) {
						while (true) {
							while (true) {
								if (!dict.hasNext()) {
									break label82;
								}

								sysDcit = (SysDict) dict.next();
								if (ab.isNotEmpty(this.pn)) {
									if ("0".equals(this.pn) && ab.isEmpty(sysDcit.getParentCode())) {
										this.pl.add(new KvEntity(sysDcit.getDictCode(), sysDcit.getDictName()));
									} else if (this.pn.equals(sysDcit.getParentCode())) {
										this.pl.add(new KvEntity(sysDcit.getDictCode(), sysDcit.getDictName()));
									}
								} else {
									this.pl.add(new KvEntity(sysDcit.getDictCode(), sysDcit.getDictName()));
								}
							}
						}
					}
				}

				KvEntity arg14;
				for (Iterator arg15 = this.pl.iterator(); arg15.hasNext(); result.append(">").append(arg14.getValue())
						.append("</option>")) {
					arg14 = (KvEntity) arg15.next();
					result.append("<option value=\"").append(arg14.getKey()).append("\"");
					if (ab.isNotEmpty(this.value) && this.value.equals(arg14.getKey())) {
						result.append(" selected=\"selected\"");
					} else if (ab.isNotEmpty(this.value) && this.value.equals(arg14.getValue())) {
						result.append(" selected=\"selected\"");
					}
				}

				result.append("</select>");
				e.print(result.toString());
				return 0;
			}
		} catch (Exception arg13) {
			throw new JspException(arg13.getMessage());
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
		this.pj = null;
		this.pk = null;
		this.pl = null;
		this.pp = null;
		this.pn = null;
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

	public String getHeaderKey() {
		return this.pj;
	}

	public void setHeaderKey(String headerKey) {
		this.pj = headerKey;
	}

	public String getHeaderValue() {
		return this.pk;
	}

	public void setHeaderValue(String headerValue) {
		this.pk = headerValue;
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

	public String getDictParentCode() {
		return this.pn;
	}

	public void setDictParentCode(String dictParentCode) {
		this.pn = dictParentCode;
	}

	public String[] getExcludeItems() {
		return this.pm;
	}

	public void setExcludeItems(String[] excludeItems) {
		this.pm = excludeItems;
	}
}