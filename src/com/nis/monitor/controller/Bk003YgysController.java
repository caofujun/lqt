package com.nis.monitor.controller;

import com.nis.comm.controller.BaseController;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.monitor.service.Bk003YgysService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Bk003YgysController extends BaseController {
	private static final Logger c = Logger.getLogger(Bk003YgysController.class);
	@Autowired
	private Bk003YgysService ut;
	@Autowired
	private Bk002GrzdService us;
}