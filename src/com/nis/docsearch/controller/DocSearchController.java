package com.nis.docsearch.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.service.AcAccountService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.docsearch.entity.DocDocument;
import com.nis.docsearch.service.DocDocumentService;
import com.nis.param.service.SysParamService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class DocSearchController extends BaseController {
	private static final Logger c = Logger.getLogger(DocSearchController.class);
	@Autowired
	private DocDocumentService qQ;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysParamService j;
	@Autowired
	private AcAccountService d;

	@RequestMapping({"/docsearch/view/docsearchIndex"})
	@SqlLog(p = "资料分享--资料列表")
	public String a(HttpServletRequest request, DocDocument docDocument) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException arg13) {
			arg13.printStackTrace();
		}

		String data = docDocument.getSearchString();
		if (data != null && !"".equals(data) && "" != data) {
			try {
				data = new String(data.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException arg12) {
				arg12.printStackTrace();
			}

			docDocument.setSearchString(data);
		}

		if (docDocument.getPage() == null || docDocument.getPage().intValue() == 0) {
			docDocument.setPage(Integer.valueOf(1));
		}

		if (docDocument.getSize() == null) {
			docDocument.setSize(Integer.valueOf(10));
		}

		AcAccount acAccount = (AcAccount) this.b(request);
		if (acAccount != null && acAccount.getUnitId() != null && !acAccount.getUnitId().equals("")) {
			docDocument.setUnitId(acAccount.getUnitId());
		} else {
			docDocument.setUnitId("");
		}

		if (acAccount != null) {
			docDocument.setUserId(acAccount.getUserId());
		}

		List labelDict = this.p.u("docType", (String) null);
		ArrayList docTypeList = new ArrayList();
		Iterator resultList = labelDict.iterator();

		while (resultList.hasNext()) {
			SysDict doctype = (SysDict) resultList.next();
			doctype.setChecked(false);
		}

		String arg14 = docDocument.getDocType();
		if (arg14 != null && "" != arg14 && !arg14.equals("")) {
			String[] arg16 = arg14.split(",");
			boolean arg18 = false;

			int i;
			for (i = 0; i < arg16.length; ++i) {
				if (arg16[i].equalsIgnoreCase("1")) {
					arg18 = true;
				}
			}

			if (arg18) {
				Iterator sysDict = labelDict.iterator();

				while (sysDict.hasNext()) {
					SysDict arg20 = (SysDict) sysDict.next();
					if (arg20.getDictCode().equalsIgnoreCase("1")) {
						arg20.setChecked(true);
						break;
					}
				}
			} else {
				for (i = 0; i < arg16.length; ++i) {
					Iterator arg11 = labelDict.iterator();

					while (arg11.hasNext()) {
						SysDict arg21 = (SysDict) arg11.next();
						if (arg21.getDictCode().equalsIgnoreCase(arg16[i])) {
							arg21.setChecked(true);
							docTypeList.add(arg16[i]);
							break;
						}
					}
				}
			}
		} else {
			Iterator totalCount = labelDict.iterator();

			while (totalCount.hasNext()) {
				SysDict arg15 = (SysDict) totalCount.next();
				if (arg15.getDictCode().equalsIgnoreCase("1")) {
					arg15.setChecked(true);
					break;
				}
			}
		}

		docDocument.setDocTypeList(docTypeList);
		request.setAttribute("docDocument", docDocument);
		List arg17 = this.qQ.findDocDocument(docDocument);
		int arg19 = this.qQ.findDocDocumentCount(docDocument);
		request.setAttribute("totalCount", Integer.valueOf(arg19));
		request.setAttribute("totalPage", Integer.valueOf(arg19 % 10 > 0 ? arg19 / 10 + 1 : arg19 / 10));
		request.setAttribute("resultList", arg17);
		request.setAttribute("labelDict", labelDict);
		return "docsearch/s_docsearchResult";
	}

	@RequestMapping({"/docsearch/s_view/downLoadFile"})
	@ResponseBody
	@SqlLog(p = "资料分享--资料下载")
	public void u(HttpServletRequest request, HttpServletResponse response, String fileId) {
		File file = null;
		DocDocument docDocument = this.qQ.get(fileId);

		try {
			if (docDocument != null) {
				AcAccount e = (AcAccount) this.b(request);

				try {
					this.qQ.a(fileId, e);
					file = new File(docDocument.getDocUrl());
					response.setContentType("application/octet-stream; charset=utf-8");
					String e1 = docDocument.getDocName() + "." + docDocument.getDocFormat();
					response.addHeader("Content-Disposition",
							"attachment;filename=\"" + new String(e1.getBytes("gbk"), "ISO-8859-1") + "\"");
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
				} catch (Exception arg11) {
					arg11.printStackTrace();
				}
			}
		} catch (Exception arg12) {
			arg12.printStackTrace();
		}

	}

	@RequestMapping({"/docsearch/f_view/upLoadFileStep1"})
	public String t(HttpServletRequest request, HttpServletResponse response) {
		AcAccount acAccount = (AcAccount) this.b(request);
		request.setAttribute("acAccount", acAccount);
		return "docsearch/s_upfile2";
	}

	@RequestMapping({"/docsearch/json/saveUpLoadFile"})
	@ResponseBody
	@SqlLog(p = "资料分享--保存资料信息")
	public void a(@RequestParam("pic") CommonsMultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		AcAccount acAccount = this.d.get(request.getParameter("userId"));
		Result reuslt = new Result();
		ArrayList list = new ArrayList();
		String fileName = file.getOriginalFilename();
		String format = this.j.findByParamCode(Param.NIS_DOC_FORMAT);
		String[] formatArrary = format.split(";");
		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
		int num = prefix.length();
		String fileNameNoPrefix = fileName.substring(0, fileName.length() - num - 1);
		boolean isRightFormat = false;

		for (int docDocument = 0; docDocument < formatArrary.length; ++docDocument) {
			if (prefix.equalsIgnoreCase(formatArrary[docDocument])) {
				isRightFormat = true;
			}
		}

		if (!isRightFormat) {
			reuslt.setResult("0");
			this.a(response, reuslt);
		} else {
			DocDocument arg18 = new DocDocument();
			String noRepeatName = fileNameNoPrefix;
			if (this.qQ.getDocCountByDocName(fileNameNoPrefix) > 0) {
				arg18.setIsRepeat("1");
				int path = 1;

				do {
					noRepeatName = fileNameNoPrefix + "(" + path + ")";
					++path;
				} while (this.qQ.getDocCountByDocName(noRepeatName) != 0);
			}

			String arg19 = "";

			try {
				arg19 = this.qQ.a(file, acAccount, noRepeatName + "." + prefix);
			} catch (Exception arg17) {
				arg17.printStackTrace();
				c.error(arg17);
				reuslt.setResult("error");
				this.a(response, reuslt);
				return;
			}

			arg18.setDocName(noRepeatName);
			arg18.setDocFormat(prefix.toLowerCase());
			arg18.setDocUrl(arg19);
			reuslt.setResult("success");
			list.add(arg18);
			reuslt.setData(list);
			this.a(response, reuslt);
		}
	}

	@RequestMapping({"/docsearch/s_view/submitFileInfo"})
	@ResponseBody
	@SqlLog(p = "资料分享--保存资料信息")
	public void a(HttpServletRequest request, HttpServletResponse response, DocDocument docDocument) {
		Result result = new Result();
		AcAccount acAccount = (AcAccount) this.b(request);

		try {
			int e = this.qQ.getDocCountByDocName(docDocument.getDocName());
			if (e == 0) {
				this.qQ.a(docDocument, acAccount);
				result.setResult("success");
			} else {
				result = new Result("repeatname", "存在相同文件名，请重新命名！");
			}
		} catch (Exception arg6) {
			c.error("上传文档失败!", arg6);
			result = new Result("error", "上传文档失败！请重试！");
		}

		this.a(response, result);
	}

	@RequestMapping({"/docsearch/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap, DocDocument docDocument) {
		AcAccount account = (AcAccount) this.b(request);
		if ("hospital".equals(account.getAcType())) {
			modelMap.put("acType", "hospital");
		} else {
			modelMap.put("acType", "clinical");
		}

		List labelDict = this.p.u("docType", (String) null);
		Iterator formatList = labelDict.iterator();

		while (formatList.hasNext()) {
			SysDict format = (SysDict) formatList.next();
			if (format.getDictCode().equalsIgnoreCase("1")) {
				format.setChecked(true);
				break;
			}
		}

		modelMap.put("labelDict", labelDict);
		String format1 = Param.NIS_DOC_FORMAT.getValue();
		String[] formatList1 = format1.split(";");
		modelMap.put("formatList", formatList1);
		return "docsearch/docsearchList";
	}

	@RequestMapping({"/docsearch/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "资料分享--资料列表")
	public void b(HttpServletRequest request, HttpServletResponse response, DocDocument docDocument) {
		List labelDict = this.p.u("docType", (String) null);
		ArrayList docTypeList = new ArrayList();
		Iterator docFormatList = labelDict.iterator();

		while (docFormatList.hasNext()) {
			SysDict doctype = (SysDict) docFormatList.next();
			doctype.setChecked(false);
		}

		String arg11 = docDocument.getDocType();
		if (arg11 != null && "" != arg11 && !arg11.equals("")) {
			String[] arg13 = arg11.split(",");
			boolean arg15 = false;

			int page;
			for (page = 0; page < arg13.length; ++page) {
				if (arg13[page].equalsIgnoreCase("1")) {
					arg15 = true;
				}
			}

			if (arg15) {
				Iterator i = labelDict.iterator();

				while (i.hasNext()) {
					SysDict arg17 = (SysDict) i.next();
					if (arg17.getDictCode().equalsIgnoreCase("1")) {
						arg17.setChecked(true);
						break;
					}
				}
			} else {
				for (page = 0; page < arg13.length; ++page) {
					Iterator arg10 = labelDict.iterator();

					while (arg10.hasNext()) {
						SysDict arg18 = (SysDict) arg10.next();
						if (arg18.getDictCode().equalsIgnoreCase(arg13[page])) {
							arg18.setChecked(true);
							docTypeList.add(arg13[page]);
							break;
						}
					}
				}
			}
		} else {
			Iterator docFormat = labelDict.iterator();

			while (docFormat.hasNext()) {
				SysDict arg12 = (SysDict) docFormat.next();
				if (arg12.getDictCode().equalsIgnoreCase("1")) {
					arg12.setChecked(true);
					break;
				}
			}
		}

		ArrayList arg14 = new ArrayList();
		String arg16 = docDocument.getDocFormat();
		if (ab.isNotEmpty(arg16)) {
			String[] arg19 = arg16.split(",");

			for (int arg20 = 0; arg20 < arg19.length && !arg19[arg20].equalsIgnoreCase("1"); ++arg20) {
				arg14.add(arg19[arg20]);
			}
		}

		docDocument.setDocTypeList(docTypeList);
		docDocument.setDocFormatList(arg14);
		if (docDocument.getSearchString() != null && !"".equals(docDocument.getSearchString())) {
			docDocument.setSearchString(ab.aR(docDocument.getSearchString()));
		}

		MyPage arg21 = this.qQ.b(docDocument);
		this.b(response, arg21);
	}

	@RequestMapping({"/docsearch/f_json/delDoc"})
	@ResponseBody
	@SqlLog(p = "资料分享--删除资料信息")
	public void v(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = new Result();
		DocDocument docDocumentSel = this.qQ.get(id);

		try {
			if (docDocumentSel != null) {
				String e = docDocumentSel.getDocUrl();
				e.replace("\\", "\\\\");
				File file = new File(e);
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
						this.qQ.delete(id);
						result.setResult("success");
					}
				} else {
					result = new Result("error", "文件不存在！");
				}
			}
		} catch (Exception arg7) {
			c.error("删除文档失败!", arg7);
			result = new Result("error", "删除文档失败！");
		}

		this.a(response, result);
	}

	@RequestMapping({"/docsearch/f_json/getTotalDocAndCurrentYearTDC"})
	@ResponseBody
	public void u(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		DocDocument docDocument = new DocDocument();
		int totalCount = this.qQ.findDocDocumentCountNOPrivilege(docDocument);
		docDocument.setQueryStartDate(f.f(true));
		docDocument.setQueryEndDate(new Date());
		int currentMouthTotalCount = this.qQ.findDocDocumentCountNOPrivilege(docDocument);
		result.setResult("success");
		result.setExtraValue(String.valueOf(totalCount));
		result.setExpandValue(String.valueOf(currentMouthTotalCount));
		this.b(response, result);
	}
}