package com.nis.monitor.controller;

import com.nis.comm.controller.BaseController;
import com.nis.monitor.service.Gr012YmsyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Gr012YmsyController extends BaseController {
	private static final Logger c = Logger.getLogger(Gr012YmsyController.class);
	@Autowired
	private Gr012YmsyService ux;
}