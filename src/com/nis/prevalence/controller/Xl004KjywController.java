package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.service.Xl004KjywService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Xl004KjywController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl004KjywController.class);
	@Autowired
	private Xl004KjywService wK;
}