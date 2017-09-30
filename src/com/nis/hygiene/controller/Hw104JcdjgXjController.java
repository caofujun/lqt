package com.nis.hygiene.controller;

import com.nis.comm.controller.BaseController;
import com.nis.hygiene.service.Hw104JcdjgXjService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Hw104JcdjgXjController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw104JcdjgXjController.class);
	@Autowired
	private Hw104JcdjgXjService rF;
}