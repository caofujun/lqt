package com.nis.monitor.controller;

import com.nis.comm.controller.BaseController;
import com.nis.monitor.service.Gr011BytService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Gr011BytController extends BaseController {
	private static final Logger c = Logger.getLogger(Gr011BytController.class);
	@Autowired
	private Gr011BytService uw;
}