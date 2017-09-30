package com.nis.hygiene.controller;

import com.nis.comm.controller.BaseController;
import com.nis.hygiene.service.Hw018RoleUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Hw018RoleUserController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw018RoleUserController.class);
	@Autowired
	private Hw018RoleUserService rC;
}