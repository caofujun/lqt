package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.service.Xl002GrxxService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Xl002GrxxController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl002GrxxController.class);
	@Autowired
	private Xl002GrxxService wI;
}