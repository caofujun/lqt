package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.service.Xl019XjnyqkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Xl019XjnyqkController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl019XjnyqkController.class);
	@Autowired
	private Xl019XjnyqkService wS;
}