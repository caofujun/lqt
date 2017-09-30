package com.nis.access.service.impl;

import com.nis.access.dao.AcMenuDao;
import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcMenuForEasyUiAtrr;
import com.nis.access.entity.AcPrivilege;
import com.nis.access.entity.AcRole;
import com.nis.access.service.AcMenuService;
import com.nis.access.service.AcPrivilegeService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.entity.TreeEntity;
import com.nis.comm.enums.b;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.c;
import com.nis.comm.enums.h;
import com.nis.comm.enums.q;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.param.service.SysParamService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcMenuServiceImpl implements AcMenuService {
	private static final Logger logger = Logger.getLogger(AcMenuServiceImpl.class);
	@Autowired
	private AcMenuDao s;
	@Autowired
	private AcPrivilegeService i;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysParamService j;

	public Result<String> a(AcMenu acMenu) {
		AcMenu obj = this.checkExtis(acMenu);
		Result result = new Result();
		if (obj != null) {
			result.setResult("error_extis");
			result.setMsg("编码已存在！");
		} else {
			if (StringUtils.isBlank(acMenu.getMenuId())) {
				acMenu.setMenuId(z.a(bg.mz));
				this.s.save(acMenu);
			} else {
				this.s.update(acMenu);
			}

			result.setResult("success");
		}

		return result;
	}

	private AcMenu checkExtis(AcMenu acMenu) {
		return this.s.checkExtis(acMenu);
	}

	public void delete(String menuId) {
		AcMenu acMenu = this.get(menuId);
		this.s.delete(menuId, acMenu.getMenuNo());
	}

	public void update(AcMenu acMenu) {
		this.s.update(acMenu);
	}

	public AcMenu get(String menuId) {
		return this.s.get(menuId);
	}

	public MyPage<AcMenu> b(AcMenu acMenu) {
		List listData = this.d(acMenu);
		return new MyPage(1, listData.size(), listData.size(), listData);
	}

	public List<AcMenu> c(AcMenu acMenu) {
		List data = this.getAll();
		return this.b(data);
	}

	public List<AcMenu> b(List<AcMenu> data) {
		ArrayList list = new ArrayList();
		Iterator arg3 = data.iterator();

		label48 : while (true) {
			AcMenu parentMenu;
			do {
				if (!arg3.hasNext()) {
					return list;
				}

				parentMenu = (AcMenu) arg3.next();
			} while (!ab.aM(parentMenu.getParentMenuNo()));

			list.add(parentMenu);
			Iterator arg5 = data.iterator();

			label46 : while (true) {
				AcMenu firstMenu;
				do {
					if (!arg5.hasNext()) {
						continue label48;
					}

					firstMenu = (AcMenu) arg5.next();
				} while (!parentMenu.getMenuNo().equals(firstMenu.getParentMenuNo()));

				firstMenu.setMenuName("    |--" + firstMenu.getMenuName());
				list.add(firstMenu);
				Iterator arg7 = data.iterator();

				while (true) {
					AcMenu secondMenu;
					do {
						if (!arg7.hasNext()) {
							continue label46;
						}

						secondMenu = (AcMenu) arg7.next();
					} while (!firstMenu.getMenuNo().equals(secondMenu.getParentMenuNo()));

					secondMenu.setMenuName("        |----" + secondMenu.getMenuName());
					list.add(secondMenu);
					Iterator arg9 = data.iterator();

					while (arg9.hasNext()) {
						AcMenu thirdMenu = (AcMenu) arg9.next();
						if (secondMenu.getMenuNo().equals(thirdMenu.getParentMenuNo())) {
							thirdMenu.setMenuName("            |------" + thirdMenu.getMenuName());
							list.add(thirdMenu);
						}
					}
				}
			}
		}
	}

	private void a(List<AcMenu> list, List<AcMenu> data, AcMenu parentMenu, int index) {
		String prefix = "--";

		for (int i = 0; i < data.size(); ++i) {
			AcMenu menu = (AcMenu) data.get(i);
			if (parentMenu.getMenuNo().equals(menu.getParentMenuNo())) {
				String prefixCache = "";

				int childIndex;
				for (childIndex = 0; childIndex <= index; ++childIndex) {
					prefixCache = prefixCache + prefix;
				}

				menu.setMenuName("|" + prefixCache + menu.getMenuName());
				list.add(menu);
				childIndex = index + 1;
				this.a(list, data, menu, childIndex);
			}
		}

	}

	public List<AcMenu> getAll() {
		return this.s.getAll();
	}

	public List<AcMenu> findByOwnership(AcMenu acMenu) {
		List list = this.s.findByOwnership(acMenu);
		Iterator arg3 = list.iterator();

		while (arg3.hasNext()) {
			AcMenu m = (AcMenu) arg3.next();
			m.setMenuTypeName(this.p.k(q.gF.getValue(), m.getMenuType(), (String) null));
		}

		return list;
	}

	public List<AcMenu> d(AcMenu acMenu) {
		List list = this.findByOwnership(acMenu);
		return this.b(list);
	}

	public List<AcMenu> getAllPatentMenu(String ownership) {
		return this.s.getAllPatentMenu(ownership);
	}

	public AcMenu e(String menuNo) {
		List list = this.s.getMenuNo(menuNo);
		return list.size() > 0 ? (AcMenu) list.get(0) : null;
	}

	public void a(AcMenu acMenu, AcRole acRole) {
		this.a(acMenu);
		ArrayList acPrivileges = new ArrayList();
		AcPrivilege acPrivilege = new AcPrivilege();
		acPrivilege.setId(z.a(bg.mD));
		acPrivilege.setRoleId(acRole.getRoleId());
		acPrivilege.setAccessType(b.fu.getCode().toString());
		acPrivilege.setAccessId(acMenu.getMenuId());
		acPrivilege.setOperation(c.fx.getCode().toString());
		acPrivilege.setHavegrant(h.fP.getCode().toString());
		acPrivileges.add(acPrivilege);
		this.i.save(acPrivileges);
	}

	public List<AcMenu> getMenuByParentNo(String parentMenuNo, String roleId) {
		List menuList = this.s.getMenuByParentNo(parentMenuNo, roleId);
		Iterator arg4 = menuList.iterator();

		while (true) {
			AcMenu menu;
			do {
				if (!arg4.hasNext()) {
					return menuList;
				}

				menu = (AcMenu) arg4.next();
				List childrenList = this.s.getMenuByParentNo(menu.getMenuNo(), roleId);
				byte count = 5;
				if ("1".equals(menu.getIsreport())) {
					count = 4;
				}

				ArrayList childList = new ArrayList();
				int num = 1;
				if (childrenList.size() / count >= 1) {
					num = childrenList.size() / count + childrenList.size() % count;
				}

				for (int reportList = 0; reportList < childrenList.size(); reportList += num) {
					AcMenu reports = new AcMenu();
					ArrayList i = new ArrayList();

					for (int mu = 0; mu < num; ++mu) {
						if (reportList + mu < childrenList.size()) {
							i.add((AcMenu) childrenList.get(reportList + mu));
						}
					}

					reports.setChildren(i);
					childList.add(reports);
				}

				menu.setChildren(childList);
			} while (!ab.isNotEmpty(menu.getJoinReport()));

			List arg14 = this.s.getMenuNos(menu.getJoinReport().split(","), roleId);
			ArrayList arg15 = new ArrayList();

			for (int arg16 = 0; arg16 < arg14.size(); arg16 += 2) {
				AcMenu arg17 = new AcMenu();
				ArrayList children = new ArrayList();
				children.add((AcMenu) arg14.get(arg16));
				if (arg16 + 1 < arg14.size()) {
					children.add((AcMenu) arg14.get(arg16 + 1));
				}

				arg17.setChildren(children);
				arg15.add(arg17);
			}

			menu.setReportList(arg15);
		}
	}

	public void deleteByMenuNo(String menuNo) {
		this.s.deleteByMenuNo(menuNo);
	}

	public List<AcMenu> getAllMenu(AcMenu acMenu) {
		return this.s.getAllMenu(acMenu);
	}

	public List<AcMenu> getMenuNos(String[] menuNos, String roleId) {
		return this.s.getMenuNos(menuNos, roleId);
	}

	public List<AcMenu> f(String menuNo) {
		List menuList = this.s.getReportMenu();
		AcMenu menu = this.e(menuNo);
		if (ab.isNotEmpty(menu.getJoinReport())) {
			String[] menuNos = menu.getJoinReport().split(",");
			Iterator arg5 = menuList.iterator();

			while (arg5.hasNext()) {
				AcMenu acMenu = (AcMenu) arg5.next();
				List menuNoList = Arrays.asList(menuNos);
				if (menuNoList.contains(acMenu.getMenuNo())) {
					acMenu.setIsHavegrant("1");
				}
			}
		}

		return menuList;
	}

	public List<AcMenu> a() {
		return null;
	}

	public List<AcMenu> g(String mid) {
		AcMenu acMenu = new AcMenu();
		acMenu.setOwnership("hospital");
		ArrayList hospitalMenuList = new ArrayList();
		AcMenu menu = new AcMenu();
		menu.setMenuNo("SY01");
		menu.setId("SY01");
		menu.setMenuName("院感首页");
		menu.setText("院感首页");
		menu.setMenuType("0");
		menu.setOwnership("hospital");
		menu.setDestUrl("/gm004Jcmx/f_view/main_info_new.shtml");
		hospitalMenuList.add(menu);
		List hospitalAcMenuList = this.findByOwnership(acMenu);
		hospitalMenuList.addAll(hospitalAcMenuList);

		AcMenu acMenu2;
		AcMenuForEasyUiAtrr acMenuForEasyUiAtrr;
		for (Iterator arg6 = hospitalMenuList.iterator(); arg6.hasNext(); acMenu2.setAttributes(acMenuForEasyUiAtrr)) {
			acMenu2 = (AcMenu) arg6.next();
			acMenuForEasyUiAtrr = new AcMenuForEasyUiAtrr();
			if (acMenu2.getDestUrl() != null && !acMenu2.getDestUrl().equals("") && acMenu2.getDestUrl() != "") {
				acMenuForEasyUiAtrr.setDestUrl(acMenu2.getDestUrl());
			} else {
				acMenuForEasyUiAtrr.setDestUrl("disselect");
			}
		}

		if (mid != null && !mid.equals("") && mid != "") {
			return this.a(this.c((List) hospitalMenuList), mid);
		} else {
			return this.d(this.c((List) hospitalMenuList));
		}
	}

	public List<AcMenu> c(List<AcMenu> data) {
		ArrayList list = new ArrayList();
		Iterator arg3 = data.iterator();

		label66 : while (true) {
			AcMenu parentMenu;
			do {
				if (!arg3.hasNext()) {
					return list;
				}

				parentMenu = (AcMenu) arg3.next();
			} while (!ab.aM(parentMenu.getParentMenuNo()));

			if (list.size() == 0) {
				parentMenu.setState("open");
			} else {
				parentMenu.setState("closed");
			}

			list.add(parentMenu);
			Iterator arg5 = data.iterator();

			label64 : while (true) {
				AcMenu firstMenu;
				do {
					if (!arg5.hasNext()) {
						continue label66;
					}

					firstMenu = (AcMenu) arg5.next();
				} while (!parentMenu.getMenuNo().equals(firstMenu.getParentMenuNo()));

				if (parentMenu.getChildren() == null) {
					ArrayList secondMenu = new ArrayList();
					parentMenu.setChildren(secondMenu);
					parentMenu.getChildren().add(firstMenu);
				} else {
					parentMenu.getChildren().add(firstMenu);
				}

				Iterator arg7 = data.iterator();

				while (true) {
					AcMenu secondMenu1;
					do {
						if (!arg7.hasNext()) {
							continue label64;
						}

						secondMenu1 = (AcMenu) arg7.next();
					} while (!firstMenu.getMenuNo().equals(secondMenu1.getParentMenuNo()));

					if (firstMenu.getChildren() == null) {
						ArrayList thirdMenu = new ArrayList();
						firstMenu.setChildren(thirdMenu);
						firstMenu.getChildren().add(secondMenu1);
					} else {
						firstMenu.getChildren().add(secondMenu1);
					}

					Iterator arg9 = data.iterator();

					while (arg9.hasNext()) {
						AcMenu thirdMenu1 = (AcMenu) arg9.next();
						if (secondMenu1.getMenuNo().equals(thirdMenu1.getParentMenuNo())) {
							if (secondMenu1.getChildren() == null) {
								ArrayList thirdChildrens = new ArrayList();
								secondMenu1.setChildren(thirdChildrens);
								secondMenu1.getChildren().add(thirdMenu1);
							} else {
								secondMenu1.getChildren().add(thirdMenu1);
							}
						}
					}
				}
			}
		}
	}

	public List<AcMenu> d(List<AcMenu> data) {
		for (int i = 0; i < data.size(); ++i) {
			List firtMenus;
			int j;
			if (i != 0) {
				if (((AcMenu) data.get(i)).getChildren() != null && ((AcMenu) data.get(i)).getChildren().size() > 0) {
					firtMenus = ((AcMenu) data.get(i)).getChildren();

					for (j = 0; j < firtMenus.size(); ++j) {
						if (((AcMenu) firtMenus.get(j)).getChildren() != null
								&& ((AcMenu) firtMenus.get(j)).getChildren().size() > 0) {
							((AcMenu) firtMenus.get(j)).setState("closed");
						}
					}
				}
			} else if (((AcMenu) data.get(i)).getChildren() != null
					&& ((AcMenu) data.get(i)).getChildren().size() > 0) {
				firtMenus = ((AcMenu) data.get(i)).getChildren();

				for (j = 0; j < firtMenus.size(); ++j) {
					if (j == 0) {
						if (((AcMenu) firtMenus.get(j)).getChildren() != null
								&& ((AcMenu) firtMenus.get(j)).getChildren().size() > 0) {
							List secondMenus = ((AcMenu) firtMenus.get(j)).getChildren();

							for (int k = 0; k < secondMenus.size(); ++k) {
								if (i == 0) {
									((AcMenu) secondMenus.get(k)).setState("open");
								} else {
									((AcMenu) secondMenus.get(k)).setState("closed");
								}
							}
						}
					} else if (((AcMenu) firtMenus.get(j)).getChildren() != null
							&& ((AcMenu) firtMenus.get(j)).getChildren().size() > 0) {
						((AcMenu) firtMenus.get(j)).setState("closed");
					}
				}
			}
		}

		return data;
	}

	public List<AcMenu> a(List<AcMenu> data, String mid) {
		Iterator secondMenuStr = data.iterator();

		AcMenu thirdAcMenu;
		while (secondMenuStr.hasNext()) {
			thirdAcMenu = (AcMenu) secondMenuStr.next();
			thirdAcMenu.setState("closed");
		}

		thirdAcMenu = this.e(mid);
		String secondMenuStr1 = thirdAcMenu.getParentMenuNo();
		AcMenu secondAcMenu = this.e(secondMenuStr1);
		String firstMenuStr = secondAcMenu.getParentMenuNo();
		AcMenu acMenu;
		Iterator arg7;
		if (firstMenuStr != null && !firstMenuStr.equals("") && firstMenuStr != "") {
			arg7 = data.iterator();

			while (true) {
				List secondAcMenuList;
				AcMenu acMenu2;
				Iterator arg10;
				do {
					label66 : do {
						while (arg7.hasNext()) {
							acMenu = (AcMenu) arg7.next();
							if (acMenu.getMenuNo().equals(firstMenuStr)) {
								acMenu.setState("open");
								continue label66;
							}

							acMenu.setState("closed");
							if (acMenu.getChildren() != null && acMenu.getChildren().size() > 0) {
								secondAcMenuList = acMenu.getChildren();
								arg10 = secondAcMenuList.iterator();

								while (arg10.hasNext()) {
									acMenu2 = (AcMenu) arg10.next();
									if (acMenu2.getChildren() != null && acMenu2.getChildren().size() > 0) {
										acMenu2.setState("closed");
									}
								}
							}
						}

						return data;
					} while (acMenu.getChildren() == null);
				} while (acMenu.getChildren().size() <= 0);

				secondAcMenuList = acMenu.getChildren();
				arg10 = secondAcMenuList.iterator();

				while (arg10.hasNext()) {
					acMenu2 = (AcMenu) arg10.next();
					if (acMenu2.getMenuNo().equals(secondMenuStr1)) {
						acMenu2.setState("open");
					} else if (acMenu2.getChildren() != null && acMenu2.getChildren().size() > 0) {
						acMenu2.setState("closed");
					}
				}
			}
		} else {
			arg7 = data.iterator();

			while (arg7.hasNext()) {
				acMenu = (AcMenu) arg7.next();
				if (acMenu.getMenuNo().equals(secondMenuStr1)) {
					acMenu.setState("open");
				} else {
					acMenu.setState("closed");
				}
			}

			return data;
		}
	}

	public List<TreeEntity> h(String roleId) {
		List root = this.s.getReportMenuRoot();

		for (int i = 0; i < root.size(); ++i) {
			String name = ((TreeEntity) root.get(i)).getId();
			String isfather = ((TreeEntity) root.get(i)).getIsfather();
			if ("1".equals(isfather) && ab.isNotEmpty(name)) {
				List leaf = this.getReportMenuLeaf(name, roleId);
				((TreeEntity) root.get(i)).setChildren(leaf);
			}
		}

		return root;
	}

	public List<TreeEntity> getReportMenuLeaf(String parentMenuNo, String roleId) {
		return this.s.getReportMenuLeaf(parentMenuNo, roleId);
	}

	public List<AcMenu> a(AcAccount account, String ownership, String roleId) {
		AcMenu acMemu = new AcMenu();
		acMemu.setOwnership(ownership);
		acMemu.setIsvalid(h.fP.getCode().toString());
		acMemu.setUnitId(account.getUnitId());
		acMemu.setRoleId(roleId);
		List menuRoot = this.s.getMenuRoot(acMemu);
		Iterator arg6 = menuRoot.iterator();

		while (arg6.hasNext()) {
			AcMenu am = (AcMenu) arg6.next();
			am.setRoleId(roleId);
			am.setMenuTypeName(this.p.k(q.gF.getValue(), am.getMenuType(), (String) null));
			am.setChildren(this.e(am));
		}

		return menuRoot;
	}

	public List<AcMenu> e(AcMenu am) {
		List leafs = this.s.getMenuLeaf(am);
		if (leafs.size() > 0) {
			Iterator arg3 = leafs.iterator();

			while (arg3.hasNext()) {
				AcMenu leaf = (AcMenu) arg3.next();
				leaf.setRoleId(am.getRoleId());
				leaf.setMenuTypeName(this.p.k(q.gF.getValue(), leaf.getMenuType(), (String) null));
				leaf.setChildren(this.e(leaf));
			}
		}

		return leafs;
	}

	public List<AcMenu> findMenuNos(String[] menuNos) {
		return this.s.findMenuNos(menuNos);
	}
}