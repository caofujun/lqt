package com.nis.monitor.controller;

import com.nis.comm.controller.BaseController;
import com.nis.monitor.service.Xn020GadcService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Xn020GadcController extends BaseController {
	private static final Logger c = Logger.getLogger(Xn020GadcController.class);
	@Autowired
	private Xn020GadcService uB;
}