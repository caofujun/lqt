package com.nis.cdc.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.dao.CtgBk005BlxxDao;
import com.nis.cdc.dao.CtgBk005CyxxDao;
import com.nis.cdc.dao.CtgBk005SyjcDao;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk005Blxx;
import com.nis.cdc.entity.CtgBk005Cyxx;
import com.nis.cdc.entity.CtgBk005Syjc;
import com.nis.cdc.service.CtgBk005SyjcService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.comm.utils.g;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St020ClinicPatientsService;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
public class CtgBk005SyjcServiceImpl implements CtgBk005SyjcService {
	private static final Logger c = Logger.getLogger(CtgBk005SyjcServiceImpl.class);
	@Autowired
	private CtgBk005SyjcDao dW;
	@Autowired
	private CtgBk005BlxxDao dU;
	@Autowired
	private CtgBk005CyxxDao dV;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private SysParamService j;

	public void save(CtgBk005Syjc ctgBk005Syjc) {
		this.dW.save(ctgBk005Syjc);
	}

	public void delete(String masterid) {
		this.dW.delete(masterid);
	}

	public void update(CtgBk005Syjc ctgBk005Syjc) {
		this.dW.update(ctgBk005Syjc);
	}

	public CtgBk005Syjc get(String masterid) {
		return this.dW.get(masterid);
	}

	public MyPage<CtgBk005Syjc> a(CtgBk005Syjc ctgBk005Syjc) {
		int total = this.dW.findCtgBk005SyjcCount(ctgBk005Syjc);
		List data = null;
		if (total > 0) {
			data = this.dW.findCtgBk005Syjc(ctgBk005Syjc);
		}

		return new MyPage(ctgBk005Syjc.getPage().intValue(), ctgBk005Syjc.getSize().intValue(), total, data);
	}

	public List<CtgBk005Syjc> getAll() {
		return this.dW.getAll();
	}

	public List<CtgBk005Syjc> getByMastertid(String masterid) {
		return this.dW.getByMastertid(masterid);
	}

	@Transactional
	public Result<String> a(CtgBk005Syjc ctgBk005Syjc, AcAccount account) {
		Result r = new Result();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean isUpdate = false;
		byte isemptycard = 1;

		try {
			if (ctgBk005Syjc != null) {
				if (ab.isNotEmpty(ctgBk005Syjc.getZyid())) {
					St003Cryxxb e = this.bg.get(ctgBk005Syjc.getZyid());
					if (e != null) {
						isemptycard = 0;
					}
				} else if (ab.isNotEmpty(ctgBk005Syjc.getMzid())) {
					St020ClinicPatients arg14 = this.bh.get(ctgBk005Syjc.getMzid());
					if (arg14 != null) {
						isemptycard = 0;
					}
				}
			}

			if (ab.aM(ctgBk005Syjc.getMasterid())) {
				String arg15 = af.getUUID32();
				ctgBk005Syjc.setMasterid(arg15);
				ctgBk005Syjc.setCardid("C" + sdf.format(new Date()));
				ctgBk005Syjc.setIsemptycard(Integer.valueOf(isemptycard));
				ctgBk005Syjc.setFlag(new Long(0L));
				ctgBk005Syjc.setSavedt(new Date());
				if (ctgBk005Syjc.getReportdt() == null) {
					ctgBk005Syjc.setReportdt(new Date());
				}

				this.dW.save(ctgBk005Syjc);
				isUpdate = false;
			} else {
				ctgBk005Syjc.setIsemptycard(Integer.valueOf(isemptycard));
				this.dW.update(ctgBk005Syjc);
				isUpdate = true;
			}

			List arg16 = ctgBk005Syjc.getCtgBk005Blxx();
			this.dU.deleteByMasterid(ctgBk005Syjc.getMasterid());
			if (arg16 != null && !arg16.isEmpty()) {
				int cyxxList = 1;
				Iterator content = arg16.iterator();

				while (content.hasNext()) {
					CtgBk005Blxx gkkCode = (CtgBk005Blxx) content.next();
					if (gkkCode.getFoodname() != null) {
						String subId = af.getUUID32();
						gkkCode.setMasterid(ctgBk005Syjc.getMasterid());
						gkkCode.setSubid(subId);
						gkkCode.setOrderno(new Long((long) (cyxxList++)));
						this.dU.save(gkkCode);
					}
				}
			}

			List arg17 = ctgBk005Syjc.getCtgBk005Cyxx();
			this.dV.deleteByMasterid(ctgBk005Syjc.getMasterid());
			if (arg17 != null && !arg17.isEmpty()) {
				int arg18 = 1;
				Iterator arg22 = arg17.iterator();

				while (arg22.hasNext()) {
					CtgBk005Cyxx arg20 = (CtgBk005Cyxx) arg22.next();
					if (arg20.getSpecimentype() != null) {
						String subId1 = af.getUUID32();
						arg20.setMasterid(ctgBk005Syjc.getMasterid());
						arg20.setSubid(subId1);
						arg20.setOrderno(new Long((long) (arg18++)));
						this.dV.save(arg20);
					}
				}
			}

			System.err.println("===" + ctgBk005Syjc.getIsemptycard());
			String arg19 = this.j.findByParamCode(Param.NIS_GK_DEPTID);
			String arg21 = ctgBk005Syjc.getReportdoctorname() + "（" + ctgBk005Syjc.getReportdeptname() + "）上报了 食源监测报卡";
			this.cV.a(ctgBk005Syjc.getZyid(), ctgBk005Syjc.getMzid(), account.getUsername(), account.getRealname(),
					arg21, (String[]) null, new String[]{arg19}, al.jx.getValue(), ctgBk005Syjc.getMasterid());
			r.setResult("success");
			r.setMsg("保存成功！");
		} catch (Exception arg13) {
			arg13.printStackTrace();
			c.error("保存操作出错！", arg13);
			r.setResult("error");
			r.setExtraValue(arg13.getMessage());
			r.setMsg("保存失败！");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return r;
	}

	public List<CtgBk005Syjc> findCardsForAdmin(CtgBk001Crbmaster ctgBk001Crbmaster) {
		return this.dW.findCardsForAdmin(ctgBk001Crbmaster);
	}

	public void audit(CtgBk005Syjc ctgBk005Syjc) {
		this.dW.audit(ctgBk005Syjc);
	}

	public void retreat(CtgBk005Syjc ctgBk005Syjc) {
		this.dW.retreat(ctgBk005Syjc);
	}

	public void cancel(CtgBk005Syjc ctgBk005Syjc) {
		this.dW.cancel(ctgBk005Syjc);
	}

	public void remove(CtgBk005Syjc ctgBk005Syjc) {
		this.dW.remove(ctgBk005Syjc);
	}

	public void updateNotes(String masterid, String notes) {
		this.dW.updateNotes(masterid, notes);
	}

	public void batchAudit(CtgBk005Syjc ctgBk005Syjc) {
		this.dW.batchAudit(ctgBk005Syjc);
	}

	public void updatePrintFlag(String masterid) {
		this.dW.updatePrintFlag(masterid);
	}

	public HSSFWorkbook a(HttpServletRequest request, String masterid) {
		HSSFWorkbook workbook = null;
		SimpleDateFormat sv = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat lv = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			String arg27 = request.getSession().getServletContext().getRealPath("/")
					+ "resources\\excelModel\\jlsrmyysyjcbldrmb.xls";
			workbook = new HSSFWorkbook(new FileInputStream(arg27));
			HSSFSheet e1 = workbook.getSheet("病例信息");
			CtgBk005Syjc syjc = this.dW.get(masterid);
			Map hospInfoMap = this.dW.getHospInfo4Excel();
			HSSFRow row = e1.createRow(2);
			g.a(row, 0, (HSSFCellStyle) null, 1, Integer.valueOf(1));
			g.a(row, 1, (HSSFCellStyle) null, 1, hospInfoMap.get("sheng"));
			g.a(row, 2, (HSSFCellStyle) null, 1, hospInfoMap.get("shi"));
			g.a(row, 3, (HSSFCellStyle) null, 1, hospInfoMap.get("xian"));
			g.a(row, 4, (HSSFCellStyle) null, 1, hospInfoMap.get("hospName"));
			g.a(row, 5, (HSSFCellStyle) null, 1, syjc.getReportdoctorname());
			g.a(row, 6, (HSSFCellStyle) null, 1, lv.format(syjc.getReportdt()));
			g.a(row, 7, (HSSFCellStyle) null, 1, lv.format(syjc.getStartDate()));
			g.a(row, 8, (HSSFCellStyle) null, 1, lv.format(syjc.getDiagnoseDate()));
			g.a(row, 9, (HSSFCellStyle) null, 1, syjc.getMzid());
			g.a(row, 10, (HSSFCellStyle) null, 1, syjc.getIsreturnvisit().intValue() == 1 ? "是" : "否");
			g.a(row, 11, (HSSFCellStyle) null, 1, syjc.getIsinhospital());
			g.a(row, 12, (HSSFCellStyle) null, 1, syjc.getZyid());
			g.a(row, 13, (HSSFCellStyle) null, 1, syjc.getDeaddate());
			g.a(row, 14, (HSSFCellStyle) null, 1, syjc.getPatientName());
			g.a(row, 15, (HSSFCellStyle) null, 1, syjc.getParentName());
			g.a(row, 16, (HSSFCellStyle) null, 1, syjc.getSexname());
			g.a(row, 17, (HSSFCellStyle) null, 1, syjc.getProfession());
			g.a(row, 18, (HSSFCellStyle) null, 1, syjc.getId());
			g.a(row, 19, (HSSFCellStyle) null, 1, sv.format(syjc.getBirthday()));
			g.a(row, 20, (HSSFCellStyle) null, 1, syjc.getTelp());
			g.a(row, 21, (HSSFCellStyle) null, 1, syjc.getUnit());
			g.a(row, 22, (HSSFCellStyle) null, 1, syjc.getAreatypeName());
			Map addrInfo = this.dW.getAddrInfo4Excel(syjc.getAddrcode());
			g.a(row, 23, (HSSFCellStyle) null, 1, addrInfo.get("sheng"));
			g.a(row, 24, (HSSFCellStyle) null, 1, addrInfo.get("shi"));
			g.a(row, 25, (HSSFCellStyle) null, 1, addrInfo.get("xian"));
			g.a(row, 26, (HSSFCellStyle) null, 1, syjc.getAddr());
			g.a(row, 27, (HSSFCellStyle) null, 1, syjc.getIsusedantibiotic());
			g.a(row, 28, (HSSFCellStyle) null, 1, syjc.getAntibiotic());
			g.a(row, 29, (HSSFCellStyle) null, 1, "数据验证通过");
			String mzzyid = ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid();
			HSSFSheet sheet2 = workbook.getSheet("症状信息");
			int seqNum = 0;
			String[] sheet3;
			String seqNum3;
			int sheet4;
			int seqNum4;
			String[] sheet5;
			HSSFRow blxxs;
			if (ab.isNotEmpty(syjc.getSymptoms())) {
				sheet3 = syjc.getSymptoms().split("\\|");
				sheet5 = sheet3;
				seqNum4 = sheet3.length;

				for (sheet4 = 0; sheet4 < seqNum4; ++sheet4) {
					seqNum3 = sheet5[sheet4];
					++seqNum;
					blxxs = sheet2.createRow(seqNum);
					g.a(blxxs, 0, (HSSFCellStyle) null, 1, Integer.valueOf(seqNum));
					g.a(blxxs, 1, (HSSFCellStyle) null, 1,
							ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid());
					g.a(blxxs, 2, (HSSFCellStyle) null, 1, "全身症状与体征");
					g.a(blxxs, 3, (HSSFCellStyle) null, 1, seqNum3);
					g.a(blxxs, 4, (HSSFCellStyle) null, 1, "");
					if ("发热".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getSymptomsFr());
					} else if ("其他".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getSymptomsOther());
					}

					g.a(blxxs, 6, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			if (ab.isNotEmpty(syjc.getDigestives())) {
				sheet3 = syjc.getDigestives().split("\\|");
				sheet5 = sheet3;
				seqNum4 = sheet3.length;

				for (sheet4 = 0; sheet4 < seqNum4; ++sheet4) {
					seqNum3 = sheet5[sheet4];
					++seqNum;
					blxxs = sheet2.createRow(seqNum);
					g.a(blxxs, 0, (HSSFCellStyle) null, 1, Integer.valueOf(seqNum));
					g.a(blxxs, 1, (HSSFCellStyle) null, 1,
							ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid());
					g.a(blxxs, 2, (HSSFCellStyle) null, 1, "消化系统");
					g.a(blxxs, 3, (HSSFCellStyle) null, 1, seqNum3);
					if ("腹泻".equals(seqNum3)) {
						g.a(blxxs, 4, (HSSFCellStyle) null, 1, syjc.getDigestiveFxxz());
					}

					if ("呕吐".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getDigestiveOt());
					} else if ("腹泻".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getDigestiveFx());
					} else if ("其他".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getDigestiveOther());
					}

					g.a(blxxs, 6, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			if (ab.isNotEmpty(syjc.getRespiratorys())) {
				sheet3 = syjc.getRespiratorys().split("\\|");
				sheet5 = sheet3;
				seqNum4 = sheet3.length;

				for (sheet4 = 0; sheet4 < seqNum4; ++sheet4) {
					seqNum3 = sheet5[sheet4];
					++seqNum;
					blxxs = sheet2.createRow(seqNum);
					g.a(blxxs, 0, (HSSFCellStyle) null, 1, Integer.valueOf(seqNum));
					g.a(blxxs, 1, (HSSFCellStyle) null, 1,
							ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid());
					g.a(blxxs, 2, (HSSFCellStyle) null, 1, "呼吸系统");
					g.a(blxxs, 3, (HSSFCellStyle) null, 1, seqNum3);
					g.a(blxxs, 4, (HSSFCellStyle) null, 1, "");
					if ("其他".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getRespiratoryOther());
					}

					g.a(blxxs, 6, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			if (ab.isNotEmpty(syjc.getCardiovasculars())) {
				sheet3 = syjc.getCardiovasculars().split("\\|");
				sheet5 = sheet3;
				seqNum4 = sheet3.length;

				for (sheet4 = 0; sheet4 < seqNum4; ++sheet4) {
					seqNum3 = sheet5[sheet4];
					++seqNum;
					blxxs = sheet2.createRow(seqNum);
					g.a(blxxs, 0, (HSSFCellStyle) null, 1, Integer.valueOf(seqNum));
					g.a(blxxs, 1, (HSSFCellStyle) null, 1,
							ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid());
					g.a(blxxs, 2, (HSSFCellStyle) null, 1, "心脏血管系统");
					g.a(blxxs, 3, (HSSFCellStyle) null, 1, seqNum3);
					g.a(blxxs, 4, (HSSFCellStyle) null, 1, "");
					if ("其他".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getCardiovascularOther());
					}

					g.a(blxxs, 6, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			if (ab.isNotEmpty(syjc.getUrinarys())) {
				sheet3 = syjc.getUrinarys().split("\\|");
				sheet5 = sheet3;
				seqNum4 = sheet3.length;

				for (sheet4 = 0; sheet4 < seqNum4; ++sheet4) {
					seqNum3 = sheet5[sheet4];
					++seqNum;
					blxxs = sheet2.createRow(seqNum);
					g.a(blxxs, 0, (HSSFCellStyle) null, 1, Integer.valueOf(seqNum));
					g.a(blxxs, 1, (HSSFCellStyle) null, 1,
							ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid());
					g.a(blxxs, 2, (HSSFCellStyle) null, 1, "泌尿系统");
					g.a(blxxs, 3, (HSSFCellStyle) null, 1, seqNum3);
					g.a(blxxs, 4, (HSSFCellStyle) null, 1, "");
					if ("其他".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getUrinaryOther());
					}

					g.a(blxxs, 6, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			if (ab.isNotEmpty(syjc.getNervous())) {
				sheet3 = syjc.getNervous().split("\\|");
				sheet5 = sheet3;
				seqNum4 = sheet3.length;

				for (sheet4 = 0; sheet4 < seqNum4; ++sheet4) {
					seqNum3 = sheet5[sheet4];
					++seqNum;
					blxxs = sheet2.createRow(seqNum);
					g.a(blxxs, 0, (HSSFCellStyle) null, 1, Integer.valueOf(seqNum));
					g.a(blxxs, 1, (HSSFCellStyle) null, 1,
							ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid());
					g.a(blxxs, 2, (HSSFCellStyle) null, 1, "神经系统");
					g.a(blxxs, 3, (HSSFCellStyle) null, 1, seqNum3);
					g.a(blxxs, 4, (HSSFCellStyle) null, 1, "");
					if ("瞳孔异常".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getNervouYc());
					} else if ("其他".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getNervouOther());
					}

					g.a(blxxs, 6, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			if (ab.isNotEmpty(syjc.getSkins())) {
				sheet3 = syjc.getSkins().split("\\|");
				sheet5 = sheet3;
				seqNum4 = sheet3.length;

				for (sheet4 = 0; sheet4 < seqNum4; ++sheet4) {
					seqNum3 = sheet5[sheet4];
					++seqNum;
					blxxs = sheet2.createRow(seqNum);
					g.a(blxxs, 0, (HSSFCellStyle) null, 1, Integer.valueOf(seqNum));
					g.a(blxxs, 1, (HSSFCellStyle) null, 1,
							ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid());
					g.a(blxxs, 2, (HSSFCellStyle) null, 1, "皮肤和皮下组织");
					g.a(blxxs, 3, (HSSFCellStyle) null, 1, seqNum3);
					g.a(blxxs, 4, (HSSFCellStyle) null, 1, "");
					if ("其他".equals(seqNum3)) {
						g.a(blxxs, 5, (HSSFCellStyle) null, 1, syjc.getSkinOther());
					}

					g.a(blxxs, 6, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			HSSFSheet arg28 = workbook.getSheet("初步诊断");
			int arg29 = 0;
			if (ab.isNotEmpty(syjc.getInitdiagnosis())) {
				String[] arg33 = syjc.getInitdiagnosis().split("\\|");
				String[] sheet6 = arg33;
				int arg35 = arg33.length;

				for (int arg31 = 0; arg31 < arg35; ++arg31) {
					String arg30 = sheet6[arg31];
					++arg29;
					HSSFRow cyxxs = arg28.createRow(arg29);
					g.a(cyxxs, 0, (HSSFCellStyle) null, 1, Integer.valueOf(arg29));
					g.a(cyxxs, 1, (HSSFCellStyle) null, 1,
							ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid());
					g.a(cyxxs, 2, (HSSFCellStyle) null, 1, arg30);
					if ("其他".equals(arg30)) {
						g.a(cyxxs, 3, (HSSFCellStyle) null, 1, syjc.getInitdiagnosisOther());
					}

					g.a(cyxxs, 4, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			HSSFSheet arg34 = workbook.getSheet("既往病史");
			seqNum4 = 0;
			int arg32;
			if (ab.isNotEmpty(syjc.getPrevioushistory())) {
				sheet5 = syjc.getPrevioushistory().split("\\|");
				String[] i = sheet5;
				int arg36 = sheet5.length;

				for (arg32 = 0; arg32 < arg36; ++arg32) {
					String arg39 = i[arg32];
					++seqNum4;
					HSSFRow cyxx = arg34.createRow(seqNum4);
					g.a(cyxx, 0, (HSSFCellStyle) null, 1, Integer.valueOf(seqNum4));
					g.a(cyxx, 1, (HSSFCellStyle) null, 1, ab.isEmpty(syjc.getMzid()) ? syjc.getZyid() : syjc.getMzid());
					g.a(cyxx, 2, (HSSFCellStyle) null, 1, arg39);
					if ("其他".equals(arg39)) {
						g.a(cyxx, 3, (HSSFCellStyle) null, 1, syjc.getPrevioushistoryOther());
					}

					g.a(cyxx, 4, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			HSSFSheet arg37 = workbook.getSheet("暴露信息");
			List arg41 = this.dU.getByMastertid(masterid);
			if (arg41 != null && arg41.size() > 0) {
				for (arg32 = 0; arg32 < arg41.size(); ++arg32) {
					CtgBk005Blxx arg40 = (CtgBk005Blxx) arg41.get(arg32);
					HSSFRow arg43 = arg37.createRow(arg32 + 2);
					g.a(arg43, 0, (HSSFCellStyle) null, 1, arg40.getOrderno());
					g.a(arg43, 1, (HSSFCellStyle) null, 1, mzzyid);
					g.a(arg43, 2, (HSSFCellStyle) null, 1, arg40.getFoodname());
					g.a(arg43, 3, (HSSFCellStyle) null, 1, arg40.getFoodclass());
					g.a(arg43, 4, (HSSFCellStyle) null, 1, arg40.getPackingway());
					g.a(arg43, 5, (HSSFCellStyle) null, 1, arg40.getFoodbrand());
					g.a(arg43, 6, (HSSFCellStyle) null, 1, arg40.getManufacturer());
					g.a(arg43, 7, (HSSFCellStyle) null, 1, arg40.getPlacetype());
					g.a(arg43, 8, (HSSFCellStyle) null, 1, "");
					g.a(arg43, 9, (HSSFCellStyle) null, 1, "境内");
					Map arg45 = this.dW.getAddrInfo4Excel(arg40.getPurcplacecode());
					g.a(arg43, 10, (HSSFCellStyle) null, 1, arg45.get("sheng"));
					g.a(arg43, 11, (HSSFCellStyle) null, 1, arg45.get("shi"));
					g.a(arg43, 12, (HSSFCellStyle) null, 1, arg45.get("xian"));
					g.a(arg43, 13, (HSSFCellStyle) null, 1, arg40.getPurchaseplace());
					g.a(arg43, 14, (HSSFCellStyle) null, 1, "境内");
					Map s6row = this.dW.getAddrInfo4Excel(arg40.getEatplacecode());
					g.a(arg43, 15, (HSSFCellStyle) null, 1, s6row.get("sheng"));
					g.a(arg43, 16, (HSSFCellStyle) null, 1, s6row.get("shi"));
					g.a(arg43, 17, (HSSFCellStyle) null, 1, s6row.get("xian"));
					g.a(arg43, 18, (HSSFCellStyle) null, 1, arg40.getEatingplaces());
					g.a(arg43, 19, (HSSFCellStyle) null, 1, arg40.getNumberofeating());
					g.a(arg43, 20, (HSSFCellStyle) null, 1, lv.format(arg40.getEatingtime()));
					g.a(arg43, 21, (HSSFCellStyle) null, 1, arg40.getOtherpeople());
					g.a(arg43, 22, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}

			HSSFSheet arg38 = workbook.getSheet("标本信息");
			List arg42 = this.dV.getByMastertid(masterid);
			if (arg42 != null && arg42.size() > 0) {
				for (int arg44 = 0; arg44 < arg42.size(); ++arg44) {
					CtgBk005Cyxx arg46 = (CtgBk005Cyxx) arg42.get(arg44);
					HSSFRow arg47 = arg38.createRow(arg44 + 1);
					g.a(arg47, 0, (HSSFCellStyle) null, 1, arg46.getOrderno());
					g.a(arg47, 1, (HSSFCellStyle) null, 1, mzzyid);
					g.a(arg47, 2, (HSSFCellStyle) null, 1, arg46.getSpecimentype());
					g.a(arg47, 3, (HSSFCellStyle) null, 1, arg46.getSpecimencount());
					g.a(arg47, 4, (HSSFCellStyle) null, 1, arg46.getNumberofunits());
					g.a(arg47, 5, (HSSFCellStyle) null, 1, sv.format(arg46.getSamplingdate()));
					g.a(arg47, 6, (HSSFCellStyle) null, 1, arg46.getNote());
					g.a(arg47, 7, (HSSFCellStyle) null, 1, "数据验证通过");
				}
			}
		} catch (Exception arg26) {
			Exception e = arg26;
			arg26.printStackTrace();

			try {
				throw new Exception("导出模版出错，错误信息：" + e.getMessage());
			} catch (Exception arg25) {
				arg25.printStackTrace();
			}
		}

		return workbook;
	}
}