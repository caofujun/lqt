package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.service.Xl014DicTrocheKindService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Xl014DicTrocheKindController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl014DicTrocheKindController.class);
	@Autowired
	private Xl014DicTrocheKindService wR;
}