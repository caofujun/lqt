package com.nis.comm.sign.model;

import com.nis.comm.utils.ab;
import java.util.ArrayList;
import java.util.List;

public class a {
	private String suffix;
	private List<String> pd;

	public a() {
	}

	public a(String suffix, String... params) {
		this.suffix = suffix;
		if (params != null) {
			String[] arg5 = params;
			int arg4 = params.length;

			for (int arg3 = 0; arg3 < arg4; ++arg3) {
				String param = arg5[arg3];
				this.Q(param);
			}
		}

	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public List<String> getSignParams() {
		if (this.pd == null) {
			this.pd = new ArrayList();
		}

		return this.pd;
	}

	public void setSignParams(List<String> signParams) {
		this.pd = signParams;
	}

	public void Q(String param) {
		if (!ab.isEmpty(param)) {
			if (this.pd == null) {
				this.pd = new ArrayList();
			}

			this.pd.add(param);
		}
	}
}