package com.nis.hygiene.controller;

import com.nis.comm.controller.BaseController;
import com.nis.hygiene.service.Hw002JcbzService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Hw002JcbzController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw002JcbzController.class);
	@Autowired
	private Hw002JcbzService rq;
}