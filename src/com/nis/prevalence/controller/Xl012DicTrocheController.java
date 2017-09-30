package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.service.Xl012DicTrocheService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Xl012DicTrocheController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl012DicTrocheController.class);
	@Autowired
	private Xl012DicTrocheService wP;
}