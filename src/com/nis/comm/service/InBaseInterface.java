package com.nis.comm.service;

import com.nis.comm.entity.InterfaceParams;
import com.nis.comm.entity.Result;

public interface InBaseInterface {
	String er = "success";
	String ERROR = "error";

	Result<String> a(InterfaceParams arg0);
}