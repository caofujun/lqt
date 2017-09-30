package com.nis.comm.utils;

import com.nis.comm.utils.EncryptUtils;
import java.io.IOException;
import org.apache.commons.dbcp.BasicDataSource;

public class DesBasicDataSource extends BasicDataSource {
	public synchronized void setPassword(String password) {
		try {
			password = EncryptUtils.ak(password);
		} catch (IOException arg2) {
			arg2.printStackTrace();
		}

		super.setPassword(password);
	}
}