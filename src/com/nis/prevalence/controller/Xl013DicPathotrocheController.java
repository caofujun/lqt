package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.service.Xl013DicPathotrocheService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Xl013DicPathotrocheController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl013DicPathotrocheController.class);
	@Autowired
	private Xl013DicPathotrocheService wQ;
}