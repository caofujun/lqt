package com.nis.outbreak.controller;

import com.nis.comm.controller.BaseController;
import com.nis.outbreak.service.By007DataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class By007DataController extends BaseController {
	private static final Logger c = Logger.getLogger(By007DataController.class);
	@Autowired
	private By007DataService wh;
}