package com.nis.cdc.controller;

import com.nis.cdc.entity.CtgSys014Todolist;
import com.nis.cdc.service.CtgSys014TodolistService;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CtgSys014ToDoListController extends BaseController {
	private static final Logger c = Logger.getLogger(CtgSys014ToDoListController.class);
	@Autowired
	private CtgSys014TodolistService dD;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping({"/cdc/f_json/saveTodoList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, CtgSys014Todolist ctgSys014Todolist) {
		Result result = this.dD.a(ctgSys014Todolist);
		this.a(response, result);
	}

	@RequestMapping({"/cdc/f_json/removeTodoList"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, CtgSys014Todolist ctgSys014Todolist) {
		Result result = this.dD.b(ctgSys014Todolist);
		this.a(response, result);
	}
}