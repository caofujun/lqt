package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.service.Xl005KjywzlService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Xl005KjywzlController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl005KjywzlController.class);
	@Autowired
	private Xl005KjywzlService wL;
}