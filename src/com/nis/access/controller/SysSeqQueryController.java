package com.nis.access.controller;

import com.nis.access.entity.SysSeqQuery;
import com.nis.access.service.SysSeqQueryService;
import com.nis.comm.controller.BaseController;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysSeqQueryController extends BaseController {
	private static final Logger c = Logger.getLogger(SysSeqQueryController.class);
	@Autowired
	private SysSeqQueryService m;

	@RequestMapping({"/sysSeqQuery/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "sysSeqQuery/sysSeqQuery";
	}

	@RequestMapping({"/sysSeqQuery/f_json/pageQuery"})
	public String a(HttpServletRequest request, ModelMap modelMap, SysSeqQuery sysSeqQuery, String seqName,
			String pageNum, String pageStart, String States) {
		sysSeqQuery.setSeqName(seqName);
		if ("".equals(pageNum) || "0".equals(pageNum) || pageNum == null) {
			pageNum = "20";
		}

		if ("".equals(pageStart) || "0".equals(pageStart) || pageStart == null) {
			pageStart = "1";
		}

		sysSeqQuery.setPageNum(pageNum);
		sysSeqQuery.setPageStart(pageStart);

		try {
			int e = this.m.c(sysSeqQuery);
			if (e > 0) {
				modelMap.put("totalNum", Integer.valueOf(e));
				boolean totalPage = false;
				int arg16;
				if (e % Integer.parseInt(pageNum) == 0) {
					arg16 = e / Integer.parseInt(pageNum);
				} else {
					arg16 = e / Integer.parseInt(pageNum) + 1;
				}

				modelMap.put("totalPage", Integer.valueOf(arg16));
				if ("nextPage".equals(States)) {
					if (arg16 == Integer.valueOf(pageStart).intValue()) {
						modelMap.put("tsmsg", "已经是最后一页了！");
						modelMap.put("pageStart", pageStart);
					} else {
						Integer list = Integer.valueOf(Integer.valueOf(pageStart).intValue() + 1);
						sysSeqQuery.setPageStart(String.valueOf(list));
						modelMap.put("pageStart", list);
					}
				} else if ("finallyPage".equals(States)) {
					if (arg16 == Integer.valueOf(pageStart).intValue()) {
						modelMap.put("tsmsg", "已经是最后一页了！");
						modelMap.put("pageStart", pageStart);
					} else {
						modelMap.put("pageStart", Integer.valueOf(arg16));
						sysSeqQuery.setPageStart(String.valueOf(arg16));
						modelMap.put("pageStart", Integer.valueOf(arg16));
					}
				} else {
					modelMap.put("pageStart", pageStart);
				}

				List arg17 = this.m.a(sysSeqQuery);
				modelMap.put("list", arg17);
				String str = "";

				for (int i = 0; i < arg17.size(); ++i) {
					Map m = (Map) arg17.get(i);

					String key;
					for (Iterator arg14 = m.keySet().iterator(); arg14.hasNext(); str = str + m.get(key) + "+--+") {
						key = (String) arg14.next();
						if (i == 0) {
							modelMap.put("keyMap", m);
						}
					}

					str = str + ";";
				}

				modelMap.put("str", str);
			} else {
				modelMap.put("msg", "无查询数据!");
				modelMap.put("pageStart", Integer.valueOf(1));
			}
		} catch (Exception arg15) {
			c.error("获取信息异常!", arg15);
			modelMap.put("msg", "请确定sql语句是否正确！如无问题，请联系开发人员！");
		}

		return "sysSeqQuery/sysSeqQueryPage";
	}

	@RequestMapping({"/sysSeqQuery/f_json/updateState"})
	public String a(HttpServletRequest request, ModelMap modelMap, SysSeqQuery sysSeqQuery, String seqName) {
		sysSeqQuery.setSeqName(seqName);

		try {
			int e = this.m.b(sysSeqQuery);
			if (e > 0) {
				modelMap.put("msg", "操作成功！");
			} else {
				modelMap.put("msg", "操作失败！");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			modelMap.put("msg", "请确定sql语句是否正确！");
		}

		return "sysSeqQuery/sysSeqQueryPage";
	}
}