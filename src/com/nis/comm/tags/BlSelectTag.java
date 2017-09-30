package com.nis.comm.tags;

import com.nis.bl.entity.Bl004CsDetailinfo;
import com.nis.bl.service.Bl004CsDetailinfoService;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;

public class BlSelectTag extends TagSupport {
	private static final long serialVersionUID = 7293280902947614510L;
	private static final Logger logger = Logger.getLogger(BlSelectTag.class);
	private String id;
	private String name;
	private String value;
	private String pi;
	private String pj;
	private String pk;
	private List<KvEntity> pl;
	private String[] pm;
	private String csmId;
	private String pn;
	private String exp;

	public int doStartTag() throws JspException {
		try {
			JspWriter e = this.pageContext.getOut();
			if (this.pl == null && ab.isEmpty(this.csmId)) {
				logger.error("items is null");
				return 0;
			} else {
				StringBuffer result = new StringBuffer();
				result.append("<select id=\"").append(this.getId()).append("\" name=\"").append(this.getName())
						.append("\" class=\"").append(this.getCssCls()).append("\" ").append(this.getExp()).append(">");
				if (this.pk != null) {
					result.append("<option value=\"").append(this.pj).append("\">").append(this.pk).append("</option>");
				}

				if (ab.isNotEmpty(this.csmId)) {
					String kvEntity = (String) this.pageContext.getSession().getAttribute("user_json");
					Bl004CsDetailinfoService detailinfoService = (Bl004CsDetailinfoService) AppContextUtil.getInstance()
							.getBean(Bl004CsDetailinfoService.class);
					this.pl = new ArrayList();
					String unitId = null;
					if (kvEntity != null) {
						LoginUser detail = (LoginUser) l.toObject(kvEntity, LoginUser.class);
						unitId = detail.getUnitId();
					}

					List arg16 = detailinfoService.findDetailBycsmId(this.csmId);
					ArrayList listCopy = new ArrayList();
					Iterator dict = arg16.iterator();

					Bl004CsDetailinfo bl004CsDetailinfo;
					while (dict.hasNext()) {
						bl004CsDetailinfo = (Bl004CsDetailinfo) dict.next();
						listCopy.add(bl004CsDetailinfo.clone());
					}

					if (this.pm != null && this.pm.length > 0) {
						Iterator arg17 = listCopy.iterator();

						while (arg17.hasNext()) {
							Bl004CsDetailinfo arg18 = (Bl004CsDetailinfo) arg17.next();
							String[] arg12 = this.pm;
							int arg11 = this.pm.length;

							for (int arg10 = 0; arg10 < arg11; ++arg10) {
								String excludeCode = arg12[arg10];
								if (arg18.getCsdId().equals(excludeCode)) {
									arg17.remove();
								}
							}
						}
					}

					dict = listCopy.iterator();

					while (dict.hasNext()) {
						bl004CsDetailinfo = (Bl004CsDetailinfo) dict.next();
						this.pl.add(new KvEntity(bl004CsDetailinfo.getCsdId(), bl004CsDetailinfo.getCsdName()));
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
		this.csmId = null;
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

	public String getCsmId() {
		return this.csmId;
	}

	public void setCsmId(String csmId) {
		this.csmId = csmId;
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