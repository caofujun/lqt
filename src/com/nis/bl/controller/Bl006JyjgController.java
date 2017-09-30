package com.nis.bl.controller;

import com.nis.bl.entity.Bl006Jyjg;
import com.nis.bl.service.Bl006JyjgService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Bl006JyjgController extends BaseController {
	private static final Logger c = Logger.getLogger(Bl006JyjgController.class);
	@Autowired
	private Bl006JyjgService cQ;

	@RequestMapping({"/bl006Jyjg/f_json/save"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, List<Bl006Jyjg> bl006JyjgList) {
		Result result = null;

		try {
			result = new Result();
			if (bl006JyjgList.size() > 0) {
				this.cQ.delete(((Bl006Jyjg) bl006JyjgList.get(0)).getBlId());
				Iterator arg5 = bl006JyjgList.iterator();

				while (arg5.hasNext()) {
					Bl006Jyjg e = (Bl006Jyjg) arg5.next();
					this.cQ.save(e);
				}
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl006Jyjg/f_json/findBl006Jyjg"})
	@ResponseBody
	@SqlLog(p = "职业暴露--检验项目列表")
	public void d(HttpServletRequest request, HttpServletResponse response, String blId, String jyDh, String itemName) {
		if (ab.isNotEmpty(itemName)) {
			itemName = itemName + "检验项目";
		}

		List bl006JyjgList = this.cQ.getBl006JyjgList(blId, jyDh, itemName);
		this.b(response, bl006JyjgList);
	}

	@RequestMapping({"/bl006Jyjg/f_json/findByTime"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Integer day) {
		String startDate = f.formatDate(new Date());
		String endDate = f.formatDate(f.a(new Date(), day.intValue()));
		List JyjgList = this.cQ.findByTime(startDate, endDate);
		this.b(response, JyjgList);
	}

	@RequestMapping({"/bl006Jyjg/f_json/updFlag"})
	@ResponseBody
	@SqlLog(p = "职业暴露--更新职业暴露状态")
	public void a(HttpServletRequest request, HttpServletResponse response, Bl006Jyjg jyjg) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(jyjg.getBlId())) {
				Bl006Jyjg e = this.cQ.get(jyjg.getBlId(), jyjg.getJyDh(), jyjg.getJyHm());
				e.setFlag(Long.valueOf(1L));
				e.setJyTime(jyjg.getJyTime());
				e.setJyJg(jyjg.getJyJg());
				e.setSfMemo(jyjg.getSfMemo());
				this.cQ.update(e);
				result.setResult("success");
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}