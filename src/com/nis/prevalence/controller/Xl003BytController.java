package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.service.Xl003BytService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Xl003BytController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl003BytController.class);
	@Autowired
	private Xl003BytService wJ;
}