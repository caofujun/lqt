package com.nis.monitor.controller;

import com.nis.comm.controller.BaseController;
import com.nis.monitor.service.Bk004SjbbService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Bk004SjbbController extends BaseController {
	private static final Logger c = Logger.getLogger(Bk004SjbbController.class);
	@Autowired
	private Bk004SjbbService co;
}