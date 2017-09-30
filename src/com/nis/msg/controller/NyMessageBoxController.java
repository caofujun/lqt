package com.nis.msg.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.msg.entity.NyMessageDetail;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.msg.service.NyMessageUserDetailService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NyMessageBoxController extends BaseController {
	private static final Logger c = Logger.getLogger(NyMessageBoxController.class);
	@Autowired
	private NyMessageUserDetailService cT;
	@Autowired
	private NyMessageDetailService cV;

	@RequestMapping({"/nyMessageBox/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "msg/nyMessageBox";
	}

	@RequestMapping({"/nyMessageBox/f_view/intefaceBox"})
	public String L(HttpServletRequest request, ModelMap modelMap) {
		return "msg/nyInterfaceMessageBox";
	}

	@RequestMapping({"/nyMessageBox/f_json/unreadMsgCount"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, NyMessageDetail nyMessageDetail) {
		AcAccount account = (AcAccount) this.b(request);
		if (!"hospital".equals(account.getAcType())) {
			nyMessageDetail.setUserId(account.getDocNo());
		} else {
			nyMessageDetail.setUserId(account.getUsername());
		}

		nyMessageDetail.setIsRead("0");
		int msgCount = this.cV.getMsgsCount(nyMessageDetail);
		this.a(response, Integer.valueOf(msgCount));
	}

	@RequestMapping({"/nyMessageBox/f_json/getUnreadMsgs"})
	public String a(HttpServletRequest request, ModelMap modelMap, NyMessageDetail nyMessageDetail) {
		AcAccount account = (AcAccount) this.b(request);
		if (!"hospital".equals(account.getAcType())) {
			nyMessageDetail.setUserId(account.getDocNo());
		} else {
			nyMessageDetail.setUserId(account.getUsername());
		}

		nyMessageDetail.setIsRead("0");
		MyPage page = this.cV.b(nyMessageDetail);
		modelMap.put("MsgPage", page);
		return "msg/nyMessageList";
	}

	@RequestMapping({"/nyMessageBox/f_json/getAllMsgs"})
	public String b(HttpServletRequest request, ModelMap modelMap, NyMessageDetail nyMessageDetail) {
		AcAccount account = (AcAccount) this.b(request);
		if (!"hospital".equals(account.getAcType())) {
			nyMessageDetail.setUserId(account.getDocNo());
		} else {
			nyMessageDetail.setUserId(account.getUsername());
		}

		MyPage page = this.cV.b(nyMessageDetail);
		modelMap.put("MsgPage", page);
		return "msg/nyMessageList";
	}

	@RequestMapping({"/nyMessageBox/f_json/getInterfaceMsgs"})
	public String c(HttpServletRequest request, ModelMap modelMap, NyMessageDetail nyMessageDetail) {
		AcAccount account = (AcAccount) this.b(request);
		if (!"hospital".equals(account.getAcType())) {
			nyMessageDetail.setUserId(account.getDocNo());
		} else {
			nyMessageDetail.setUserId(account.getUsername());
		}

		nyMessageDetail.setMsgType("7");
		nyMessageDetail.setIsRead("0");
		nyMessageDetail.setOrclEndNum(Integer.valueOf(99999));
		MyPage page = this.cV.b(nyMessageDetail);
		modelMap.put("MsgPage", page);
		return "msg/nyMessageList";
	}

	@RequestMapping({"/nyMessageBox/f_json/markAsRead"})
	@ResponseBody
	public void O(HttpServletRequest request, HttpServletResponse response, String mids, String themeIds) {
		Result r = new Result();
		AcAccount account = (AcAccount) this.b(request);
		String userId = "";
		if (!"hospital".equals(account.getAcType())) {
			userId = account.getDocNo();
		} else {
			userId = account.getUsername();
		}

		if (ab.isNotEmpty(mids) && ab.isNotEmpty(themeIds) && ab.isNotEmpty(userId)) {
			String[] midss = mids.split("\\|");
			String[] themeIdss = themeIds.split("\\|");

			for (int i = 0; i < midss.length; ++i) {
				this.cT.markAsRead(midss[i], themeIdss[i], userId);
			}

			r.setResult("success");
			r.setMsg("成功标记为已读！");
		} else {
			r.setResult("error");
			r.setMsg(ab.isEmpty(mids) ? "没有要更新的数据！" : "登录过期！");
		}

		this.a(response, r);
	}
}