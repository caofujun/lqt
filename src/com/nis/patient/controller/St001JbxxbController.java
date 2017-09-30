package com.nis.patient.controller;

import com.nis.comm.controller.BaseController;
import com.nis.patient.service.St001JbxxbService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class St001JbxxbController extends BaseController {
	private static final Logger c = Logger.getLogger(St001JbxxbController.class);
	@Autowired
	private St001JbxxbService dg;
}