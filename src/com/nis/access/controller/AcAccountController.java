package com.nis.access.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcPrivilege;
import com.nis.access.entity.AcRole;
import com.nis.access.service.AcAccountService;
import com.nis.access.service.AcAccountroleService;
import com.nis.access.service.AcPrivilegeService;
import com.nis.access.service.AcRoleService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.constants.b;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.c;
import com.nis.comm.enums.h;
import com.nis.comm.enums.n;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.j;
import com.nis.comm.utils.l;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.organization.service.DoctorService;
import com.nis.param.service.SysParamService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class AcAccountController extends BaseController {
	private static final Logger c = Logger.getLogger(AcAccountController.class);
	@Autowired
	private AcAccountService d;
	@Autowired
	private DepService e;
	@Autowired
	private DoctorService f;
	@Autowired
	private AcRoleService g;
	@Autowired
	private AcAccountroleService h;
	@Autowired
	private AcPrivilegeService i;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/acAccount/json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String name) {
		List list = this.d.findRealname(name);
		ArrayList kvList = new ArrayList();
		Iterator arg6 = list.iterator();

		while (arg6.hasNext()) {
			AcAccount account = (AcAccount) arg6.next();
			kvList.add(new KvEntity(account.getUsername(), account.getRealname()));
		}

		this.b(response, kvList);
	}

	@RequestMapping({"/acAccount/json/get"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String id) {
		AcAccount account = this.d.b(id);
		if (account != null) {
			this.a(response, new KvEntity(account.getUsername(), account.getRealname()));
		}

	}

	@RequestMapping({"/acAccount/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "access/acAccountList";
	}

	@RequestMapping({"/acAccount/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "账号管理--账号列表")
	public void a(HttpServletRequest request, HttpServletResponse response, AcAccount acAccount, String ownership) {
		acAccount.setAcType(ownership);
		if (acAccount.getSearchString() != null && !"".equals(acAccount.getSearchString())) {
			acAccount.setSearchString(ab.aR(acAccount.getSearchString()));
		}

		MyPage page = this.d.b(acAccount);
		this.b(response, page);
	}

	@RequestMapping({"/acAccount/f_view/toedit"})
	@SqlLog(p = "账号管理--账号详情")
	public String a(HttpServletRequest request, ModelMap modelMap, String id, String username, String ownership) {
		AcAccount acc = (AcAccount) this.b(request);
		AcAccount acAccount = new AcAccount();
		List roles = this.g.getRoleByUnitId((String) null, acc.getAcType());
		if (StringUtils.isNotBlank(id) && ab.isNotEmpty(username)) {
			acAccount = this.d.b(username);
			id = acAccount.getUserId();
		}

		if (StringUtils.isNotBlank(id)) {
			acAccount = this.d.get(id);
			List userRoles = this.h.findByUserid(id);
			if (userRoles.size() > 0) {
				modelMap.put("roleIds", ((AcRole) userRoles.get(0)).getRoleId());
			}
		}

		if (acAccount.getUnitId() == null) {
			acAccount.setUnitId(acc.getUnitId());
		}

		if (acAccount.getAcType() == null) {
			acAccount.setAcType(ownership);
		}

		modelMap.put("deflutPwd", b.fa);
		modelMap.put("roles", roles);
		modelMap.put("dataScope", acc.getDataScope());
		modelMap.put("acAccount", acAccount);
		return "access/acAccountEdit";
	}

	@RequestMapping({"/acAccount/f_json/checkExtis"})
	@ResponseBody
	@SqlLog(p = "账号管理--账号查重")
	public void b(HttpServletRequest request, HttpServletResponse response, AcAccount acAccount, String ownership) {
		Result result = null;

		try {
			if (acAccount == null || !StringUtils.isNotBlank(acAccount.getMobilenum())
					&& !StringUtils.isNotBlank(acAccount.getUsername())
					&& !StringUtils.isNotBlank(acAccount.getEmail())) {
				result = new Result("success");
			} else {
				int e = this.d.checkUniqueExtis(acAccount);
				result = new Result();
				if (e > 0) {
					result.setResult("error");
				} else {
					result.setResult("success");
				}
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acAccount/f_view/toauth"})
	@SqlLog(p = "账号管理--账号权限")
	public String a(HttpServletRequest request, ModelMap modelMap, String id, String ownership) {
		if (StringUtils.isNotBlank(id)) {
			AcAccount acAccount = this.d.get(id);
			modelMap.put("acAccount", acAccount);
			if (acAccount != null) {
				List curRolePrivileges = this.i.findByRoleid(acAccount.getUserId());
				Integer isHavegrant = Integer.valueOf(1);
				Iterator arg8 = curRolePrivileges.iterator();

				while (arg8.hasNext()) {
					AcPrivilege ap = (AcPrivilege) arg8.next();
					if (com.nis.comm.enums.h.fP.getCode().toString().equals(ap.getHavegrant())) {
						isHavegrant = Integer.valueOf(1);
						break;
					}
				}

				modelMap.put("isHavegrant", isHavegrant);
			}
		}

		return "access/acAccountAuth";
	}

	@RequestMapping({"/acAccount/f_view/todep"})
	public String b(HttpServletRequest request, ModelMap modelMap, String id, String ownership) {
		if (StringUtils.isNotBlank(id)) {
			AcAccount acAccount = this.d.get(id);
			modelMap.put("acAccount", acAccount);
		}

		return "access/acAccountDep";
	}

	@RequestMapping({"/acAccount/f_json/findMyMenu"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String userId, String ownership) {
		AcAccount account = (AcAccount) this.b(request);
		List list = this.d.a(account, ownership);
		List curRolePrivileges = this.i.findByRoleid(userId);
		Iterator arg8 = list.iterator();

		while (arg8.hasNext()) {
			AcMenu page = (AcMenu) arg8.next();
			Iterator arg10 = curRolePrivileges.iterator();

			while (arg10.hasNext()) {
				AcPrivilege ap = (AcPrivilege) arg10.next();
				if (ap.getAccessId().equals(page.getMenuId())) {
					page.setIsHavegrant(ap.getHavegrant());
					String isFungrant = null;
					if (com.nis.comm.enums.c.fx.getCode().toString().equals(ap.getOperation())) {
						isFungrant = com.nis.comm.enums.h.fP.getCode().toString();
					} else if (com.nis.comm.enums.c.fy.getCode().toString().equals(ap.getOperation())) {
						isFungrant = com.nis.comm.enums.h.fQ.getCode().toString();
					}

					page.setIsFungrant(isFungrant);
				}
			}
		}

		MyPage page1 = new MyPage(1, 10, list.size(), list);
		this.b(response, page1);
	}

	@RequestMapping({"/acAccount/f_json/findMyDep"})
	@ResponseBody
	@SqlLog(p = "账号管理--账号业务科室")
	public void a(HttpServletRequest request, HttpServletResponse response, String userId, Dep dep, String ownership) {
		List list = this.e.d(dep);
		AcAccount account = this.d.get(userId);
		Iterator arg8 = list.iterator();

		while (true) {
			while (arg8.hasNext()) {
				Dep page = (Dep) arg8.next();
				if (StringUtils.isNotBlank(account.getScopeInfo())
						&& account.getScopeInfo().indexOf(page.getDeptId()) > -1) {
					page.setIsHavegrant(com.nis.comm.enums.h.fP.getCode().toString());
				} else {
					page.setIsHavegrant(com.nis.comm.enums.h.fQ.getCode().toString());
				}
			}

			MyPage page1 = new MyPage(1, 10, list.size(), list);
			this.b(response, page1);
			return;
		}
	}

	@RequestMapping({"/acAccount/f_json/saveAuth"})
	@ResponseBody
	@SqlLog(p = "账号管理--保存账号菜单授权信息")
	public void a(HttpServletRequest request, HttpServletResponse response, AcAccount acAccount, String[] fungrants,
			String[] havegrants) {
		Result result = new Result();

		try {
			this.i.a(acAccount.getUserId(), fungrants, havegrants);
			result.setResult("success");
		} catch (Exception arg7) {
			c.error("保存授权信息异常!", arg7);
			result = new Result("error", "保存授权信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acAccount/f_json/saveDep"})
	@ResponseBody
	@SqlLog(p = "账号管理--保存账号科室授权信息")
	public void a(HttpServletRequest request, HttpServletResponse response, AcAccount acAccount, String havegrants,
			String delDepIds) {
		Result result = new Result();

		try {
			this.d.a(acAccount.getUserId(), havegrants, delDepIds);
			result.setResult("success");
		} catch (Exception arg7) {
			c.error("保存授权信息异常!", arg7);
			result = new Result("error", "保存授权信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acAccount/f_json/save"})
	@ResponseBody
	@SqlLog(p = "账号管理--保存账号信息")
	public void b(HttpServletRequest request, HttpServletResponse response, AcAccount acAccount, String roleIds,
			String ownership) {
		Result result = null;

		try {
			if (ab.isEmpty(roleIds)) {
				result = new Result("error", "角色信息为空！请先设置角色信息！");
				this.a(response, result);
				return;
			}

			MultipartHttpServletRequest e = null;
			CommonsMultipartFile file = null;
			String realFileName = null;
			AcAccount acAccount1 = this.d.get(acAccount.getUserId());

			String sourceType;
			String string;
			String photoPath;
			try {
				e = (MultipartHttpServletRequest) request;
				file = (CommonsMultipartFile) e.getFile("image");
				if (file != null && !StringUtils.isEmpty(file.getOriginalFilename())) {
					realFileName = file.getOriginalFilename();
					c.info("接受文件上传=" + realFileName);
					String roleids = realFileName.substring(realFileName.lastIndexOf(".") + 1, realFileName.length());
					roleids = StringUtils.lowerCase(roleids);
					if (!com.nis.comm.utils.j.ap(roleids)) {
						result = new Result("error", "上传头像格式不对！");
						this.a(response, result);
						return;
					}

					String isSave = this.j.findByParamCode(Param.NIS_IMAGE_UPLOAD_SAVE_DIR);
					sourceType = isSave;
					if (StringUtils.isBlank(isSave)) {
						result = new Result("error", "请联系管理员设置文件上传路径！");
						this.a(response, result);
						return;
					}

					isSave = ab.aZ(isSave);
					string = acAccount.getUnitId() + File.separator + com.nis.comm.utils.f.c(new Date(), "yyyy") + File.separator
							+ com.nis.comm.utils.f.c(new Date(), "MM") + File.separator + com.nis.comm.utils.f.c(new Date(), "dd");
					isSave = isSave + File.separator + string;
					File loginUser = new File(isSave);
					if (!loginUser.exists()) {
						loginUser.mkdirs();
					}

					photoPath = System.currentTimeMillis()
							+ realFileName.substring(realFileName.lastIndexOf("."), realFileName.length());
					string = string + File.separator + photoPath;
					if (acAccount1 != null && StringUtils.isNotBlank(acAccount1.getPhotoPath())) {
						String uploadFile = sourceType + File.separator + acAccount1.getPhotoPath();
						File e1 = new File(uploadFile);
						if (e1.exists()) {
							e1.delete();
						}
					}

					File arg34 = new File(isSave + File.separator + photoPath);
					if (!arg34.exists()) {
						try {
							arg34.createNewFile();
							FileCopyUtils.copy(file.getBytes(), arg34);
							acAccount.setPhotoPath(string);
							c.info("头像上传成功=" + arg34.getAbsolutePath());
						} catch (IOException arg19) {
							result = new Result("error", "头像上传失败！");
							this.a(response, result);
							return;
						}
					}
				} else if (acAccount1 != null) {
					acAccount.setPhotoPath(acAccount1.getPhotoPath());
				}
			} catch (Exception arg20) {
				result = new Result("error", "头像上传异常!");
				this.a(response, result);
				return;
			}

			ArrayList arg22 = new ArrayList();
			if (!ab.aM(roleIds)) {
				String[] arg23 = roleIds.split(",");
				String[] arg32 = arg23;
				int arg27 = arg23.length;

				for (int arg26 = 0; arg26 < arg27; ++arg26) {
					sourceType = arg32[arg26];
					arg22.add(sourceType);
				}
			}

			acAccount.setExtRoleId(arg22);
			boolean arg24 = true;
			if (StringUtils.isNotBlank(acAccount.getUnitId()) && !"0".equals(acAccount.getUnitId())) {
				StringBuffer arg25 = new StringBuffer();
				Dep arg28;
				if (n.gi.getValue().intValue() == acAccount.getDataScope().intValue()) {
					arg28 = this.e.F(acAccount.getUnitId(), acAccount.getDepNo());
					arg25.append(arg28.getDeptId());
				} else if (n.gj.getValue().intValue() == acAccount.getDataScope().intValue()
						&& ab.isNotEmpty(acAccount.getScopeInfo())) {
					String[] arg29 = acAccount.getScopeInfo().split(",");
					String[] arg36 = arg29;
					int arg35 = arg29.length;

					for (int arg33 = 0; arg33 < arg35; ++arg33) {
						String arg30 = arg36[arg33];
						if (!ab.isEmpty(arg30)) {
							Dep dep = this.e.F(acAccount.getUnitId(), arg30);
							arg25.append(dep.getDeptId()).append(",");
						}
					}

					arg25.setCharAt(arg25.length() - 1, ' ');
				} else if (n.gh.getValue().intValue() == acAccount.getDataScope().intValue()) {
					arg28 = this.e.F(acAccount.getUnitId(), acAccount.getDepNo());
					arg25.append(arg28.getDeptId());
				}
			}

			if (arg24) {
				sourceType = request.getParameter("sourceType");
				result = this.d.a(acAccount, acAccount1, sourceType);
				string = (String) this.b(request, "user_json");
				LoginUser arg31 = (LoginUser) l.toObject(string, LoginUser.class);
				arg31.setPhotoPath(acAccount.getPhotoPath());
				this.a(request, "user_json", l.toString(arg31));
				if (!StringUtils.isBlank(acAccount.getPhotoPath())) {
					photoPath = acAccount.getPhotoPath();
					photoPath = StringUtils.isEmpty(photoPath) ? null : photoPath.replaceAll("\\\\", "/");
					result.setData(b.es + "/fileSecurity/f_view/fileAnalysis.shtml?path=" + photoPath);
				}
			}
		} catch (Exception arg21) {
			c.error("获取信息异常!", arg21);
			result = new Result("error", "获取信息异常：" + arg21.getMessage());
		}

		this.a(response, result);
	}

	@RequestMapping({"/acAccount/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "账号管理--删除账号信息")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			AcAccount e = this.d.get(id);
			if (e != null && StringUtils.isNotBlank(e.getPhotoPath())) {
				String filePath = this.j.findByParamCode(Param.NIS_IMAGE_UPLOAD_SAVE_DIR);
				String oldFilePath = filePath + File.separator + e.getPhotoPath();
				File oldUploadFile = new File(oldFilePath);
				if (oldUploadFile.exists()) {
					oldUploadFile.delete();
				}
			}

			this.d.delete(id);
			result.setResult("success");
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acAccount/f_json/reSetPwd"})
	@ResponseBody
	@SqlLog(p = "账号管理--重置账号密码")
	public void d(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			String e = DigestUtils.md5Hex(this.j.findByParamCode(Param.NIS_DOC_DEFAULT_PWD));
			this.d.updatePwdByUserId(id, e);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acAccount/f_view/toUpdatePasswd"})
	public String b(HttpServletRequest request, ModelMap modelMap) {
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("userName", acAccount.getUsername());
		return "access/acPasswordUpdate";
	}

	@RequestMapping({"/acAccount/f_json/updatePasswd"})
	@ResponseBody
	@SqlLog(p = "账号管理--账户修改密码")
	public void a(HttpServletRequest request, HttpServletResponse response, String oldPassword, String newPassword,
			String affirmPassword, String sourceType) {
		Result result = null;

		try {
			result = new Result();
			AcAccount e = (AcAccount) this.b(request);
			String oldpwd1 = DigestUtils.md5Hex(oldPassword);
			String oldpwd2 = EncryptUtils.aj(oldPassword);
			if (!oldpwd1.equals(e.getPasswd()) && !oldpwd2.equals(e.getPasswd())) {
				c.error("原密码错误!");
				result = new Result("info_error", "原密码错误");
				this.a(response, result);
				return;
			}

			if (!newPassword.equals(affirmPassword)) {
				c.error("两次输入的密码不一致!");
				result = new Result("info_error", "两次输入的密码不一致");
				this.a(response, result);
				return;
			}

			String newpwd;
			if (n.gh.getValue().equals(e.getDataScope())) {
				newpwd = EncryptUtils.aj(newPassword);
				this.f.updatePwd((String) null, e.getUserId(), newpwd);
			} else {
				newpwd = DigestUtils.md5Hex(newPassword);
				this.d.updatePwdByUserId(e.getUserId(), newpwd);
			}

			if (!"my".equals(sourceType)) {
				this.e(request);
				this.c(request, "user_menus");
				this.c(request, "user_json");
			}

			result.setResult("success");
		} catch (Exception arg11) {
			c.error("获取信息异常!", arg11);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acAccount/f_view/toUpdPasswd"})
	public String c(HttpServletRequest request, ModelMap modelMap) {
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("userName", acAccount.getUsername());
		return "access/acPasswordUpd";
	}
}