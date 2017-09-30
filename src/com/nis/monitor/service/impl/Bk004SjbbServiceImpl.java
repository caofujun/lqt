package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.mdr.service.Xn011DclymxService;
import com.nis.monitor.dao.Bk004SjbbDao;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Bk004Sjbb;
import com.nis.monitor.service.Bk004SjbbService;
import com.nis.patient.entity.St009Sjbb;
import com.nis.patient.service.St009SjbbService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bk004SjbbServiceImpl implements Bk004SjbbService {
	@Autowired
	private Bk004SjbbDao uF;
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private Xn011DclymxService cn;

	public void save(Bk004Sjbb bk004Sjbb) {
		bk004Sjbb.setId(z.a(bg.mW));
		this.uF.save(bk004Sjbb);
	}

	public void delete(Bk004Sjbb bk004Sjbb) {
		this.uF.delete(bk004Sjbb);
	}

	public void update(Bk004Sjbb bk004Sjbb) {
		this.uF.update(bk004Sjbb);
	}

	public Bk004Sjbb get(Bk004Sjbb bk004Sjbb) {
		return this.uF.get(bk004Sjbb);
	}

	public MyPage<Bk004Sjbb> a(Bk004Sjbb bk004Sjbb) {
		int total = this.uF.findBk004SjbbCount(bk004Sjbb);
		List data = null;
		if (total > 0) {
			data = this.uF.findBk004Sjbb(bk004Sjbb);
		}

		return new MyPage(bk004Sjbb.getPage().intValue(), bk004Sjbb.getSize().intValue(), total, data);
	}

	public List<Bk004Sjbb> getAll() {
		return this.uF.getAll();
	}

	public int findNumBytestNo(Bk004Sjbb bk004Sjbb) {
		return this.uF.findNumBytestNo(bk004Sjbb);
	}

	public void a(Bk002Grzd bk002Grzd, Bk001Sbk bk001Sbk, String[] testOrderNos, String depNo) {
		ArrayList testNoList = null;
		if (testOrderNos != null && testOrderNos.length > 0) {
			List testNoList1 = Arrays.asList(testOrderNos);
			HashSet testNo = new HashSet(testNoList1);
			testNoList = new ArrayList();
			testNoList.addAll(testNo);
		}

		if (testNoList != null) {
			Iterator arg6 = testNoList.iterator();

			while (arg6.hasNext()) {
				String testNo1 = (String) arg6.next();
				if (StringUtils.isNotBlank(testNo1)) {
					Bk004Sjbb bk4 = new Bk004Sjbb();
					bk4.setCardRelid(bk002Grzd.getRefid());
					bk4.setRefid(bk002Grzd.getRelid());
					bk4.setTestNo(testNo1);
					bk4.setCreateDate(new Date());
					bk4.setZyid(bk001Sbk.getZyid());
					this.a(bk001Sbk, bk4, testNo1, depNo);
				}
			}
		}

	}

	private void a(Bk001Sbk bk001Sbk, Bk004Sjbb bk4, String testNo, String depNo) {
		bk4.setPatientId(bk001Sbk.getPatientId());
		bk4.setSn(Integer.valueOf(0));
		bk4.setSex(bk001Sbk.getSex());
		bk4.setVisitId(Integer.valueOf(1));
		List st009List = this.bE.findSjbbListBytestNo(testNo);
		Iterator arg6 = st009List.iterator();

		while (arg6.hasNext()) {
			St009Sjbb st009 = (St009Sjbb) arg6.next();
			bk4.setTestTypeId(st009.getItemType());
			bk4.setTestTypeName(st009.getItemTypeName());
			bk4.setSampleId(st009.getItemCode());
			bk4.setSampleName(st009.getItemName());
			bk4.setSubmiAt(st009.getSubmiAt());
			bk4.setPathoId(st009.getPathoCode());
			bk4.setPathoName(st009.getPathoName());
			bk4.setMemo(st009.getItemTypeName());
			bk4.setIsSelect(Integer.valueOf(1));
			bk4.setOperId(Long.valueOf(0L));
			bk4.setSubmiDeptId(depNo);
			this.save(bk4);
		}

	}

	public List<Bk004Sjbb> findBk004ByRefid(String refid) {
		return this.uF.findBk004ByRefid(refid);
	}

	public List<Bk004Sjbb> findMultiDrugResis(String relid) {
		return this.uF.findMultiDrugResis(relid);
	}

	public List<Bk004Sjbb> findPathogenDetection(Bk004Sjbb bk004Sjbb) {
		return this.uF.findPathogenDetection(bk004Sjbb);
	}

	public void b(Bk002Grzd bk002Grzd, Bk001Sbk bk001Sbk, String[] testOrderNos, String depNo) {
		ArrayList testNoList = null;
		if (testOrderNos != null && testOrderNos.length > 0) {
			List testNoList1 = Arrays.asList(testOrderNos);
			HashSet bk004 = new HashSet(testNoList1);
			testNoList = new ArrayList();
			testNoList.addAll(bk004);
		}

		Bk004Sjbb bk0041 = new Bk004Sjbb();
		bk0041.setTestNoNotIn(testNoList);
		bk0041.setCardRelid(bk002Grzd.getRefid());
		bk0041.setRefid(bk002Grzd.getRelid());
		this.delete(bk0041);
		if (testNoList != null) {
			Iterator arg7 = testNoList.iterator();

			while (arg7.hasNext()) {
				String testNo = (String) arg7.next();
				Bk004Sjbb bk4 = new Bk004Sjbb();
				bk4.setCardRelid(bk002Grzd.getRefid());
				bk4.setRefid(bk002Grzd.getRelid());
				bk4.setTestNo(testNo);
				bk4.setZyid(bk001Sbk.getZyid());
				int num = this.findNumBytestNo(bk4);
				if (num <= 0) {
					this.a(bk001Sbk, bk4, testNo, depNo);
				}
			}
		}

	}

	public void delByRefid(String refid) {
		this.uF.delByRefid(refid);
	}

	public int findPathogenDetectionCount(Bk004Sjbb bk004Sjbb) {
		return this.uF.findPathogenDetectionCount(bk004Sjbb);
	}

	public Integer getInfectTypeByTestNo(String testNo) {
		return this.uF.getInfectTypeByTestNo(testNo);
	}
}