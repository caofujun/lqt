package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.hygiene.dao.Hw008XmsqDao;
import com.nis.hygiene.dao.Hw009KssqDao;
import com.nis.hygiene.dao.Hw018RoleUserDao;
import com.nis.hygiene.entity.Hw008Xmsq;
import com.nis.hygiene.entity.Hw009Kssq;
import com.nis.hygiene.entity.Hw018RoleUser;
import com.nis.hygiene.service.Hw008XmsqService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Hw008XmsqServiceImpl implements Hw008XmsqService {
	@Autowired
	private Hw008XmsqDao rQ;
	@Autowired
	private Hw018RoleUserDao rR;
	@Autowired
	private Hw009KssqDao rS;
	@Autowired
	private SysDictService p;

	public void save(Hw008Xmsq hw008Xmsq) {
		if (hw008Xmsq.getSelfAdd() == null) {
			hw008Xmsq.setSelfAdd(Integer.valueOf(0));
		}

		if (hw008Xmsq.getSelfView() == null) {
			hw008Xmsq.setSelfView(Integer.valueOf(0));
		}

		if (hw008Xmsq.getSelfResult() == null) {
			hw008Xmsq.setSelfResult(Integer.valueOf(0));
		}

		if (hw008Xmsq.getOtherView() == null) {
			hw008Xmsq.setOtherView(Integer.valueOf(0));
		}

		if (hw008Xmsq.getOtherAdd() == null) {
			hw008Xmsq.setOtherAdd(Integer.valueOf(0));
		}

		if (hw008Xmsq.getOtherResult() == null) {
			hw008Xmsq.setOtherResult(Integer.valueOf(0));
		}

		this.rQ.save(hw008Xmsq);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void delete(String userId, String classId) {
		this.rQ.delete(userId, classId);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void update(Hw008Xmsq hw008Xmsq) {
		if (hw008Xmsq.getSelfAdd() == null) {
			hw008Xmsq.setSelfAdd(Integer.valueOf(0));
		}

		if (hw008Xmsq.getSelfView() == null) {
			hw008Xmsq.setSelfView(Integer.valueOf(0));
		}

		if (hw008Xmsq.getSelfResult() == null) {
			hw008Xmsq.setSelfResult(Integer.valueOf(0));
		}

		if (hw008Xmsq.getOtherView() == null) {
			hw008Xmsq.setOtherView(Integer.valueOf(0));
		}

		if (hw008Xmsq.getOtherAdd() == null) {
			hw008Xmsq.setOtherAdd(Integer.valueOf(0));
		}

		if (hw008Xmsq.getOtherResult() == null) {
			hw008Xmsq.setOtherResult(Integer.valueOf(0));
		}

		this.rQ.update(hw008Xmsq);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public Hw008Xmsq get(String userId, String classId) {
		return this.rQ.get(userId, classId);
	}

	public MyPage<Hw008Xmsq> a(Hw008Xmsq hw008Xmsq) {
		int total = this.rQ.findHw008XmsqCount(hw008Xmsq);
		List data = null;
		if (total > 0) {
			data = this.rQ.findHw008Xmsq(hw008Xmsq);
		}

		return new MyPage(hw008Xmsq.getPage().intValue(), hw008Xmsq.getSize().intValue(), total, data);
	}

	public List<Hw008Xmsq> getAll() {
		return this.rQ.getAll();
	}

	public List<Hw008Xmsq> findXmsqList(Hw008Xmsq hw008Xmsq) {
		return this.rQ.findXmsqList(hw008Xmsq);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void b(Hw008Xmsq hw008Xmsq) {
		this.rR.delHw018RoleUserNotIn(hw008Xmsq);
		List roleIdList = hw008Xmsq.getRoleIdList();
		if (roleIdList != null && roleIdList.size() > 0) {
			Iterator hw008List = roleIdList.iterator();

			while (hw008List.hasNext()) {
				String deptIdList = (String) hw008List.next();
				if (this.rR.get(hw008Xmsq.getUserId(), deptIdList) == null) {
					Hw018RoleUser hw008 = new Hw018RoleUser();
					hw008.setUserId(hw008Xmsq.getUserId());
					hw008.setRoleId(deptIdList);
					this.rR.save(hw008);
				}
			}
		}

		this.rS.delHw009KssqNotIn(hw008Xmsq);
		List deptIdList1 = hw008Xmsq.getDeptIdList();
		if (deptIdList1 != null && deptIdList1.size() > 0) {
			Iterator hw0081 = deptIdList1.iterator();

			while (hw0081.hasNext()) {
				String hw008List1 = (String) hw0081.next();
				if (this.rS.get(hw008Xmsq.getUserId(), hw008List1) == null) {
					Hw009Kssq hw009Kssq = new Hw009Kssq();
					hw009Kssq.setDeptId(hw008List1);
					hw009Kssq.setUserId(hw008Xmsq.getUserId());
					this.rS.save(hw009Kssq);
				}
			}
		}

		List hw008List2 = hw008Xmsq.getHw008List();
		if (hw008List2 != null && hw008List2.size() > 0) {
			Iterator hw009Kssq1 = hw008List2.iterator();

			while (hw009Kssq1.hasNext()) {
				Hw008Xmsq hw0082 = (Hw008Xmsq) hw009Kssq1.next();
				hw0082.setUserId(hw008Xmsq.getUserId());
				if (this.get(hw0082.getUserId(), hw0082.getClassId()) == null) {
					this.save(hw0082);
				} else {
					this.update(hw0082);
				}
			}
		}

		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public int judgeResultsPermissions(String userId, String deptId, String djDeptId, String classId) {
		return this.rQ.judgeResultsPermissions(userId, deptId, djDeptId, classId);
	}

	public int judgeReportPermissions(String userId, String deptId, String djDeptId, String classId) {
		return this.rQ.judgeReportPermissions(userId, deptId, djDeptId, classId);
	}
}