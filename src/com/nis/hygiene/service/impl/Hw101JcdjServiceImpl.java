package com.nis.hygiene.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.enums.be;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.t;
import com.nis.hygiene.dao.Hw101JcdjDao;
import com.nis.hygiene.entity.Hw003Cycs;
import com.nis.hygiene.entity.Hw004Cybb;
import com.nis.hygiene.entity.Hw006Cyds;
import com.nis.hygiene.entity.Hw101Jcdj;
import com.nis.hygiene.entity.Hw102Jcdmx;
import com.nis.hygiene.entity.Hw103Jcdjg;
import com.nis.hygiene.entity.Hw104JcdjgXj;
import com.nis.hygiene.service.Hw003CycsService;
import com.nis.hygiene.service.Hw004CybbService;
import com.nis.hygiene.service.Hw006CydsService;
import com.nis.hygiene.service.Hw008XmsqService;
import com.nis.hygiene.service.Hw101JcdjService;
import com.nis.hygiene.service.Hw102JcdmxService;
import com.nis.hygiene.service.Hw103JcdjgService;
import com.nis.hygiene.service.Hw104JcdjgXjService;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.param.service.SysParamService;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Hw101JcdjServiceImpl implements Hw101JcdjService {
	@Autowired
	private Hw101JcdjDao rW;
	@Autowired
	private Hw102JcdmxService rE;
	@Autowired
	private Hw103JcdjgService rG;
	@Autowired
	private Hw104JcdjgXjService rF;
	@Autowired
	private Hw008XmsqService rx;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private SysParamService j;
	@Autowired
	private Hw006CydsService rs;
	@Autowired
	private Hw003CycsService rt;
	@Autowired
	private Hw004CybbService ru;

	public void save(Hw101Jcdj hw101Jcdj) {
		if (hw101Jcdj.getCreateAt() == null) {
			hw101Jcdj.setCreateAt(new Date());
		}

		this.rW.save(hw101Jcdj);
	}

	public void delete(String djId) {
		this.rW.delete(djId);
	}

	public void update(Hw101Jcdj hw101Jcdj) {
		this.rW.update(hw101Jcdj);
	}

	public void updateSpecified(Hw101Jcdj hw101Jcdj, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.rW.updateSpecified(hw101Jcdj, updateAttrs);
		}

	}

	public Hw101Jcdj get(String djId) {
		return this.rW.get(djId);
	}

	public MyPage<Hw101Jcdj> a(Hw101Jcdj hw101Jcdj) {
		int total = this.rW.findHw101JcdjCount(hw101Jcdj);
		List data = null;
		if (total > 0) {
			data = this.rW.findHw101Jcdj(hw101Jcdj);
		}

		return new MyPage(hw101Jcdj.getPage().intValue(), hw101Jcdj.getSize().intValue(), total, data);
	}

	public List<Hw101Jcdj> getAll() {
		return this.rW.getAll();
	}

	public MyPage<Hw102Jcdmx> a(Hw102Jcdmx hw102Jcdmx) {
		int total = this.rW.findHw101ListCount(hw102Jcdmx);
		List data = null;
		if (total > 0) {
			data = this.rW.findHw101List(hw102Jcdmx);
		}

		return new MyPage(hw102Jcdmx.getPage().intValue(), hw102Jcdmx.getSize().intValue(), total, data);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Hw101Jcdj hw101Jcdj, Hw102Jcdmx hw102Jcdmx) {
		this.save(hw101Jcdj);
		this.e(hw102Jcdmx);
	}

	private void e(Hw102Jcdmx hw102Jcdmx) {
		if (hw102Jcdmx.getResultFlag() == null) {
			hw102Jcdmx.setResultFlag(this.f(hw102Jcdmx));
		}

		Hw003Cycs cycsDict = this.g(hw102Jcdmx);
		if (cycsDict != null) {
			hw102Jcdmx.setPlaceId(cycsDict.getPlaceId());
			hw102Jcdmx.setPlaceName(cycsDict.getPlaceName());
		}

		Hw004Cybb cybbDict = this.h(hw102Jcdmx);
		if (cybbDict != null) {
			hw102Jcdmx.setSampleId(cybbDict.getSampleId());
			hw102Jcdmx.setSampleName(cybbDict.getSampleName());
		}

		this.rE.save(hw102Jcdmx);
		List hw103List = hw102Jcdmx.getHw103List();
		if (hw103List != null && hw103List.size() > 0) {
			Iterator pathoId = hw103List.iterator();

			while (pathoId.hasNext()) {
				Hw103Jcdjg list = (Hw103Jcdjg) pathoId.next();
				list.setReportId(hw102Jcdmx.getReportId());
				list.setHw102Id(hw102Jcdmx.getId());
				this.rG.save(list);
			}
		}

		List list1 = hw102Jcdmx.getCheckOutBacteria();
		if (list1 != null && list1.size() > 0) {
			Iterator arg6 = list1.iterator();

			while (arg6.hasNext()) {
				String pathoId1 = (String) arg6.next();
				Hw104JcdjgXj hw104JcdjgXj = new Hw104JcdjgXj();
				hw104JcdjgXj.setReportId(hw102Jcdmx.getReportId());
				hw104JcdjgXj.setHw102Id(hw102Jcdmx.getId());
				hw104JcdjgXj.setPathoId(pathoId1);
				this.rF.save(hw104JcdjgXj);
			}
		}

	}

	private Integer f(Hw102Jcdmx hw102Jcdmx) {
		List hw103List = hw102Jcdmx.getHw103List();
		Integer resultFlag = Integer.valueOf(-1);
		Hw006Cyds cyds = this.rs.get(hw102Jcdmx.getPosId());
		if (cyds != null && "1".equals(cyds.getPosValue())) {
			if (hw103List != null && hw103List.size() > 0) {
				int arg8 = 0;
				Iterator arg11 = hw103List.iterator();

				while (arg11.hasNext()) {
					Hw103Jcdjg arg9 = (Hw103Jcdjg) arg11.next();
					if ((new Integer(1)).equals(arg9.getResultFlag())) {
						resultFlag = Integer.valueOf(1);
						break;
					}

					if ((new Integer(0)).equals(arg9.getResultFlag())) {
						++arg8;
					}
				}

				if (arg8 == hw103List.size()) {
					resultFlag = Integer.valueOf(0);
				}
			}
		} else if (hw103List != null && hw103List.size() > 0) {
			Double sumResult = Double.valueOf(0.0D);
			boolean qualified = false;
			Iterator resultCriterion = hw103List.iterator();

			while (resultCriterion.hasNext()) {
				Hw103Jcdjg avg = (Hw103Jcdjg) resultCriterion.next();
				if (ab.isNotEmpty(avg.getResult())) {
					sumResult = Double.valueOf(sumResult.doubleValue() + Double.parseDouble(avg.getResult()));
				}

				if ((new Integer(-1)).equals(avg.getResultFlag())) {
					qualified = true;
				}
			}

			if (!qualified) {
				Double arg10 = Double.valueOf(sumResult.doubleValue() / (double) hw103List.size());
				Double arg12 = Double.valueOf(Double.parseDouble(((Hw103Jcdjg) hw103List.get(0)).getResultCriterion()));
				if ("≤".equals(((Hw103Jcdjg) hw103List.get(0)).getResultCondition())
						&& arg10.doubleValue() <= arg12.doubleValue()) {
					resultFlag = Integer.valueOf(0);
				} else if ("≥".equals(((Hw103Jcdjg) hw103List.get(0)).getResultCondition())
						&& arg10.doubleValue() >= arg12.doubleValue()) {
					resultFlag = Integer.valueOf(0);
				} else if ("<".equals(((Hw103Jcdjg) hw103List.get(0)).getResultCondition())
						&& arg10.doubleValue() < arg12.doubleValue()) {
					resultFlag = Integer.valueOf(0);
				} else if (">".equals(((Hw103Jcdjg) hw103List.get(0)).getResultCondition())
						&& arg10.doubleValue() > arg12.doubleValue()) {
					resultFlag = Integer.valueOf(0);
				} else if ("=".equals(((Hw103Jcdjg) hw103List.get(0)).getResultCondition()) && arg10 == arg12) {
					resultFlag = Integer.valueOf(0);
				} else {
					resultFlag = Integer.valueOf(1);
				}
			}
		}

		return resultFlag;
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Hw101Jcdj hw101Jcdj, Hw102Jcdmx hw102Jcdmx, AcAccount acAccount) {
		String hw101AttrsStr = "deptId,deptName,reportBy,reportAt,takeBy,takeAt,createBy";
		List hw101Attrs = Arrays.asList(hw101AttrsStr.split(","));
		this.updateSpecified(hw101Jcdj, hw101Attrs);
		if (StringUtils.isEmpty(hw102Jcdmx.getId())) {
			this.e(hw102Jcdmx);
		} else {
			Hw102Jcdmx hw102 = this.rE.get(hw102Jcdmx.getId());
			int numReport = this.rx.judgeReportPermissions(acAccount.getUsername(), acAccount.getDepNo(),
					hw101Jcdj.getDeptId(), hw102.getClassId().substring(0, 2));
			int numResults = this.rx.judgeResultsPermissions(acAccount.getUsername(), acAccount.getDepNo(),
					hw101Jcdj.getDeptId(), hw102.getClassId().substring(0, 2));
			String hw102AttrsStr = "";
			if (numReport > 0) {
				hw102AttrsStr = hw102AttrsStr
						+ "placeName,placeId,sampleName,sampleId,takeModeName,takeModeId,posName,posId,takeBy,takeAt,takeTypeName,takeType,cyMeno,recheck";
			}

			if (numResults > 0) {
				hw102AttrsStr = hw102AttrsStr + ",testBy,testAt,resultPathoNum,memo,resultFlag";
			}

			List hw102Attrs = Arrays.asList(hw102AttrsStr.split(","));
			if (hw102Jcdmx.getResultFlag() == null) {
				hw102Jcdmx.setResultFlag(this.f(hw102Jcdmx));
			}

			Hw003Cycs cycsDict = this.g(hw102Jcdmx);
			if (cycsDict != null) {
				hw102Jcdmx.setPlaceId(cycsDict.getPlaceId());
				hw102Jcdmx.setPlaceName(cycsDict.getPlaceName());
			}

			Hw004Cybb cybbDict = this.h(hw102Jcdmx);
			if (cybbDict != null) {
				hw102Jcdmx.setSampleId(cybbDict.getSampleId());
				hw102Jcdmx.setSampleName(cybbDict.getSampleName());
			}

			this.rE.updateSpecified(hw102Jcdmx, hw102Attrs);
			if (numResults > 0) {
				List hw103List = hw102Jcdmx.getHw103List();
				if (hw103List != null && hw103List.size() > 0) {
					this.rG.delByHw102Id(hw102Jcdmx.getId());
					Iterator pathoIdList = hw103List.iterator();

					while (pathoIdList.hasNext()) {
						Hw103Jcdjg list = (Hw103Jcdjg) pathoIdList.next();
						list.setReportId(hw102Jcdmx.getReportId());
						list.setHw102Id(hw102Jcdmx.getId());
						this.rG.save(list);
					}
				}

				List arg23 = hw102Jcdmx.getCheckOutBacteria();
				this.rF.delHw104(arg23, hw102Jcdmx.getId());
				List arg24 = this.rF.findPathoIdByHw102Id(hw102Jcdmx.getId());
				String gkDeptId;
				if (arg23 != null && arg23.size() > 0) {
					Iterator jcdmx = arg23.iterator();

					while (jcdmx.hasNext()) {
						gkDeptId = (String) jcdmx.next();
						if (!arg24.contains(gkDeptId)) {
							Hw104JcdjgXj findHw102List = new Hw104JcdjgXj();
							findHw102List.setReportId(hw102Jcdmx.getReportId());
							findHw102List.setHw102Id(hw102Jcdmx.getId());
							findHw102List.setPathoId(gkDeptId);
							this.rF.save(findHw102List);
						}
					}
				}

				if (hw103List != null && hw103List.size() > 0 && hw102Jcdmx.getResultFlag() == be.mf.getValue()) {
					gkDeptId = this.j.findByParamCode(Param.NIS_GK_DEPTID);
					Hw102Jcdmx arg25 = new Hw102Jcdmx();
					arg25.setDjId(hw102Jcdmx.getDjId());
					List arg26 = this.rE.findJcdmxByDjId(arg25);
					int bhg = 0;
					int wcl = 0;
					int hg = 0;
					Iterator arg22 = arg26.iterator();

					while (arg22.hasNext()) {
						Hw102Jcdmx content = (Hw102Jcdmx) arg22.next();
						switch (content.getResultFlag().intValue()) {
							case -1 :
								++wcl;
								break;
							case 0 :
								++hg;
								break;
							case 1 :
								++bhg;
						}
					}

					String arg27 = "申请单号 " + hw102Jcdmx.getDjId() + "，不合格 " + bhg + "项，未处理 " + wcl + " 项，标本共 "
							+ arg26.size() + " 项，合格 " + hg + " 项";
					this.cV.a((String) null, (String) null, acAccount.getUsername(), acAccount.getRealname(), arg27,
							new String[]{hw101Jcdj.getReportBy()}, new String[]{gkDeptId}, al.jp.getValue(),
							hw102Jcdmx.getId());
				}
			}
		}

	}

	@Transactional(rollbackFor = {Exception.class})
	public void bv(String djId) {
		this.delete(djId);
		List idsList = this.rE.findIdByDjId(djId);
		if (idsList.size() > 0) {
			Iterator arg3 = idsList.iterator();

			while (arg3.hasNext()) {
				String id = (String) arg3.next();
				this.rE.bw(id);
			}
		}

	}

	public void b(Hw102Jcdmx hw102Jcdmx) {
		hw102Jcdmx.setCheckAt(new Date());
		if (hw102Jcdmx.getStatus() != null && hw102Jcdmx.getStatus().intValue() != 0) {
			hw102Jcdmx.setStatus(Integer.valueOf(0));
		} else {
			hw102Jcdmx.setStatus(Integer.valueOf(1));
		}

		this.rE.updCheckByDjId(hw102Jcdmx);
	}

	public void updatePrintFlag(String djId) {
		this.rW.updatePrintFlag(djId);
	}

	private Hw003Cycs g(Hw102Jcdmx hw102Jcdmx) {
		if (ab.isNotEmpty(hw102Jcdmx.getPlaceId())) {
			Hw003Cycs cycs = this.rt.isExist(hw102Jcdmx.getPlaceId());
			if (cycs == null) {
				System.err.println("未找到相关字典信息，新增采样场所字典...");
				String maxPlaceId = this.rt.findMaxPlaceId();
				String plasid = String.valueOf(Integer.parseInt(maxPlaceId) + 1);
				String spm = t.aH(hw102Jcdmx.getPlaceId());
				Hw003Cycs ncycs = new Hw003Cycs(plasid, hw102Jcdmx.getPlaceId(), spm.toUpperCase(), new Date(),
						Integer.valueOf(1));
				this.rt.save(ncycs);
				System.err.println("新增采样场所字典...成功");
				return ncycs;
			} else {
				return cycs;
			}
		} else {
			return null;
		}
	}

	public Hw004Cybb h(Hw102Jcdmx hw102Jcdmx) {
		if (ab.isNotEmpty(hw102Jcdmx.getSampleId())) {
			Hw004Cybb cybb = this.ru.isExist(hw102Jcdmx.getSampleId());
			if (cybb == null) {
				System.err.println("未找到相关字典信息，新增采样标本字典...");
				String maxSampleId = this.ru.findMaxSampleId();
				String smpid = String.valueOf(Integer.parseInt(maxSampleId) + 1);
				String spm = t.aH(hw102Jcdmx.getSampleId());
				Hw004Cybb ncybb = new Hw004Cybb(smpid, hw102Jcdmx.getSampleId(),
						hw102Jcdmx.getClassId().substring(0, 2), spm.toUpperCase(), new Date(), Integer.valueOf(1));
				this.ru.save(ncybb);
				System.err.println("新增采样标本字典...成功");
				return ncybb;
			} else {
				return cybb;
			}
		} else {
			return null;
		}
	}
}