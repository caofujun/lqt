package com.nis.comm.tags;

import com.nis.access.entity.AcMenu;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class AuthTag extends TagSupport {
	private String menuNo;

	public int doStartTag() throws JspException {
		List menuAssigned = (List) this.pageContext.getSession().getAttribute("user_menus");
		byte bodyIsShow = 0;
		Iterator arg3 = menuAssigned.iterator();

		while (arg3.hasNext()) {
			AcMenu um = (AcMenu) arg3.next();
			if (um.getMenuNo().equals(this.menuNo)) {
				bodyIsShow = 1;
			}
		}

		return bodyIsShow;
	}

	public int doEndTag() throws JspException {
		return 6;
	}

	public void release() {
		super.release();
		this.menuNo = null;
	}

	public String getMenuNo() {
		return this.menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
}