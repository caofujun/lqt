package com.nis.mkzb.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import com.nis.mkzb.entity.MkZbRecord;
import com.nis.mkzb.service.MkZbRecordService;
import com.nis.mkzb.service.MkZbXhlService;
import com.nis.param.service.SysParamService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MkZbRecordController extends BaseController {
	private static final Logger c = Logger.getLogger(MkZbRecordController.class);
	@Autowired
	private MkZbRecordService un;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysDictService p;
	@Autowired
	private MkZbXhlService uo;

	@RequestMapping({"/mkZbRecord/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("years", f.getYears());
		modelMap.put("months", f.getMonths());
		modelMap.put("curYear", Integer.valueOf(f.getCurYear()));
		modelMap.put("curMonth", Integer.valueOf(f.getCurMonth()));
		modelMap.put("zbURL", this.j.findByParamCode(Param.NIS_SYS_ZB_URL));
		modelMap.put("channel", this.j.findByParamCode(Param.NIS_SYS_ZB_CHANNEL));
		return "mkzb/mkZbRecordList";
	}

	@RequestMapping({"/mkZbRecord/f_view/xhlList"})
	public String B(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("startDate", f.formatDate(f.getYearFirst(), "yyyy-MM"));
		modelMap.put("endDate", f.formatDate(f.getYearLast(), "yyyy-MM"));
		modelMap.put("xhlZbURL", this.j.findByParamCode(Param.NIS_XHL_ZB_URL));
		modelMap.put("channel", this.j.findByParamCode(Param.NIS_SYS_ZB_CHANNEL));
		return "mkzb/mkZbXhlList";
	}

	@RequestMapping({"/mkZbRecord/f_json/xhlPageQuery"})
	@ResponseBody
	public void H(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate) {
		Date start = f.a(f.k(startDate, "yyyy-MM"), true);
		Date end = f.b(f.k(endDate, "yyyy-MM"), true);
		List zbList = this.un.findZbxhlList(start, end);
		this.a(response, zbList);
	}

	@RequestMapping({"/mkZbRecord/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, MkZbRecord zbRecord) {
		if (zbRecord.getReportStartYear() == null) {
			zbRecord.setReportStartYear(new Long((long) f.getCurYear()));
			zbRecord.setReportStartMonth(new Long(1L));
			zbRecord.setReportEndYear(new Long((long) f.getCurYear()));
			zbRecord.setReportEndMonth(new Long((long) (f.getCurMonth() == 1 ? 1 : f.getCurMonth() - 1)));
		}

		MyPage page = this.un.a(zbRecord);
		this.a(response, page);
	}

	@RequestMapping({"/mkZbRecord/f_json/findXhl"})
	@ResponseBody
	public void I(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate) {
		Date start = f.a(f.k(startDate, "yyyy-MM"), true);
		Date end = f.b(f.k(endDate, "yyyy-MM"), true);
		Result result = this.uo.g(start, end);
		this.a(response, result);
	}

	@RequestMapping({"/mkZbRecord/f_json/findReportDataByItem"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, MkZbRecord zbRecord) {
		Result result = new Result();
		String ids = "";

		try {
			zbRecord.setStartDate();
			zbRecord.setEndDate();
			ids = this.un.c(zbRecord);
			result.setResult("success");
			result.setData(ids);
		} catch (Exception arg6) {
			result.setResult("error");
			c.error("获取信息异常!", arg6);
		}

		this.a(response, result);
	}

	@RequestMapping({"/mkZbRecord/f_json/zbResult"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, MkZbRecord zbRecord) {
		Result result = null;

		try {
			zbRecord.setStartDate();
			zbRecord.setEndDate();
		} catch (ParseException arg13) {
			arg13.printStackTrace();
		}

		try {
			result = new Result();
			this.un.bK(zbRecord.getReportResult());
			result.setResult("success");
			String ex = System.getProperty("catalina.home");
			String sourceFilePath = ex + "\\直报\\患者信息上传";
			String zipFilePath = ex + "\\直报\\患者信息压缩文件";
			File resourcesFile = new File(sourceFilePath);
			File targetFile = new File(zipFilePath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}

			String targetName = resourcesFile.getName() + ".zip";
			FileOutputStream outputStream = new FileOutputStream(zipFilePath + "\\" + targetName);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
			this.a(out, resourcesFile, "");
			out.close();
			result.setData(zipFilePath + "\\" + targetName);
		} catch (Exception arg12) {
			result.setResult("error");
			arg12.printStackTrace();
		}

		this.a(response, result);
	}

	public void a(ZipOutputStream out, File file, String dir) throws Exception {
		int j;
		if (file.isDirectory()) {
			File[] fis = file.listFiles();
			out.putNextEntry(new ZipEntry(dir + "/"));
			dir = dir.length() == 0 ? "" : dir + "/";

			for (j = 0; j < fis.length; ++j) {
				this.a(out, fis[j], dir + fis[j].getName());
			}
		} else {
			FileInputStream arg6 = new FileInputStream(file);
			out.putNextEntry(new ZipEntry(dir));
			boolean arg7 = false;
			byte[] buffer = new byte[1024];

			while ((j = arg6.read(buffer)) > 0) {
				out.write(buffer, 0, j);
			}

			arg6.close();
		}

	}

	@RequestMapping({"/mkZbRecord/s_view/downLoadFile"})
	@ResponseBody
	public void u(HttpServletRequest request, HttpServletResponse response, String fileUrl) {
		File file = null;

		try {
			fileUrl = URLDecoder.decode(fileUrl, "UTF-8");
			String[] e = fileUrl.split("\\\\");
			String filename = "";
			if (e.length > 1) {
				filename = e[e.length - 1];
			}

			file = new File(fileUrl);
			response.setContentType("application/octet-stream; charset=utf-8");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String(filename.getBytes("gbk"), "ISO-8859-1") + "\"");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setIntHeader("Expires", -1);
			FileInputStream is = new FileInputStream(file);
			ServletOutputStream fos = null;
			fos = response.getOutputStream();
			boolean len = false;
			byte[] buffer = new byte[1024];

			int len1;
			while ((len1 = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len1);
			}

			is.close();
			fos.flush();
			fos.close();
		} catch (Exception arg10) {
			arg10.printStackTrace();
		}

	}
}