package com.nis.intervene.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.u;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.intervene.dao.FxPatientDao;
import com.nis.intervene.entity.FxPatient;
import com.nis.intervene.entity.FxPatientIndex;
import com.nis.intervene.service.FxPatientService;
import com.nis.intervene.service.impl.WxYsUtil;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.service.St003CryxxbService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FxPatientServiceImpl implements FxPatientService {
	@Autowired
	private FxPatientDao sH;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private WxYsUtil sI;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysParamService j;
	private String deptName = "科室名称";
	private String deptCode = "科室编码";
	private String sJ = "存在风险人数";
	private String zdCount = "风险增大人数";
	private String[] sK;

	public FxPatientServiceImpl() {
		this.sK = new String[]{this.deptName, this.sJ, this.zdCount};
	}

	public void save(FxPatient fxPatient) {
		FxPatient fp = this.sH.findByZyid(fxPatient.getZyId());
		if (fp != null) {
			fp.setScore(fxPatient.getScore());
			this.sH.update(fp);
		} else {
			fxPatient.setFxId(z.a(com.nis.comm.enums.bg.no));
			this.sH.save(fxPatient);
		}

	}

	public void delete(String fxId) {
		this.sH.delete(fxId);
	}

	public void update(FxPatient fxPatient) {
		this.sH.update(fxPatient);
	}

	public FxPatient get(String fxId) {
		return this.sH.get(fxId);
	}

	public MyPage<FxPatient> a(FxPatient fxPatient) {
		int total = this.sH.findFxPatientCount(fxPatient);
		List data = null;
		if (total > 0) {
			data = this.sH.findFxPatient(fxPatient);
		}

		return new MyPage(fxPatient.getPage().intValue(), fxPatient.getSize().intValue(), total, data);
	}

	public List<FxPatient> getAll() {
		return this.sH.getAll();
	}

	@Transactional(rollbackFor = {Exception.class})
	public void F() {
		List cryList = this.bg.findCryxxByFX();
		this.sI.G();
		this.sI.H();
		this.sI.I();
		Iterator arg2 = cryList.iterator();

		while (arg2.hasNext()) {
			St003Cryxxb cry = (St003Cryxxb) arg2.next();
			int xinshenger = this.sI.a(cry).intValue();
			int gaoling = this.sI.b(cry).intValue();
			int shoushu = this.sI.c(cry).intValue();
			int jijingmai = this.sI.d(cry).intValue();
			int miniaodao = this.sI.e(cry).intValue();
			int huxiji = this.sI.f(cry).intValue();
			int zhongxinjingmai = this.sI.g(cry).intValue();
			int zhuyuantianshu = this.sI.h(cry).intValue();
			int baixibao = this.sI.i(cry).intValue();
			int yishizhangai = this.sI.j(cry).intValue();
			int baidanbai = this.sI.k(cry).intValue();
			int zbValue = xinshenger + gaoling + shoushu + jijingmai + miniaodao + huxiji + zhongxinjingmai
					+ zhuyuantianshu + baixibao + yishizhangai + baidanbai;
			if (zbValue > Integer.parseInt(this.j.findByParamCode(Param.NIS_FXZB_MAX_SCORE))) {
				FxPatient fxPatient = new FxPatient();
				fxPatient.setPatientId(cry.getPatientId());
				fxPatient.setZyId(cry.getZyid());
				fxPatient.setScore(Integer.valueOf(zbValue));
				fxPatient.setUpdateTime(new Date());
				this.save(fxPatient);
			}

			cry.setFxStatus(Integer.valueOf(1));
			cry.setFxDate(new Date());
			this.bg.update(cry);
		}

	}

	public MyPage<FxPatientIndex> b(FxPatient fxPatient) {
		int total = this.sH.findDepFxCount(fxPatient);
		List data = null;
		if (total > 0) {
			data = this.sH.findDepFx(fxPatient);
		}

		return new MyPage(fxPatient.getPage().intValue(), fxPatient.getSize().intValue(), total, data);
	}

	@SqlLog(p = "风险分析--患者风险详情列表")
	public List<FxPatient> findFxPatientList(FxPatient fxPatient) {
		String gyStatus = fxPatient.getGyStatus();
		if (ab.isNotEmpty(gyStatus)) {
			if (gyStatus.equals("0")) {
				fxPatient.setGyStatus(gyStatus);
			} else {
				fxPatient.setGyStatusList(gyStatus.split(","));
			}
		}

		List fxPatientList = this.sH.findFxPatientList(fxPatient);
		Iterator arg4 = fxPatientList.iterator();

		while (arg4.hasNext()) {
			FxPatient fx = (FxPatient) arg4.next();
			if (ab.isEmpty(fx.getGyStatus())) {
				fx.setGyStatus(u.gY.getValue());
			}

			fx.setGyStatusName(this.p.k("gy_status", fx.getGyStatus(), (String) null));
			if (ab.isNotEmpty(fx.getPdcaStatus())) {
				fx.setPdcaStatusName(this.p.k("pdca_patient_status", fx.getPdcaStatus(), (String) null));
			}
		}

		return fxPatientList;
	}

	@SqlLog(p = "风险分析--风险分析趋势图")
	public List<Map<String, Object>> a(Date startDate, Date endDate, String deptCode, String dgsType, String dateType) {
		int day = 0;
		if ("day".equals(dateType)) {
			day = f.a(endDate, startDate);
		} else if ("month".equals(dateType)) {
			day = f.a(endDate, startDate) / 30;
		}

		ArrayList mapList = new ArrayList();

		for (int i = 0; i < day; ++i) {
			HashMap map = new HashMap();
			FxPatient fxPatient = new FxPatient();
			if ("day".equals(dateType)) {
				fxPatient.setStartDate(f.formatDate(f.a(startDate, i)));
				fxPatient.setEndDate(f.formatDate(f.a(startDate, i + 1)));
			} else if ("month".equals(dateType)) {
				fxPatient.setStartDate(f.formatDate(f.b(startDate, i)));
				fxPatient.setEndDate(f.formatDate(f.b(startDate, i + 1)));
			}

			fxPatient.setDeptCode(deptCode);
			fxPatient.setDgsType(dgsType);
			St003Cryxxb st003Cryxxb = new St003Cryxxb();
			if ("day".equals(dateType)) {
				st003Cryxxb.setStartDate(f.formatDate(f.a(startDate, i)));
				st003Cryxxb.setEndDate(f.formatDate(f.a(startDate, i + 1)));
			} else if ("month".equals(dateType)) {
				st003Cryxxb.setStartDate(f.formatDate(f.b(startDate, i)));
				st003Cryxxb.setEndDate(f.formatDate(f.b(startDate, i + 1)));
			}

			st003Cryxxb.setDeptCode(deptCode);
			st003Cryxxb.setDgsType(dgsType);
			int count = this.sH.findCountbyDate(fxPatient);
			int count2 = this.bg.findCountbyDate(st003Cryxxb);
			if ("day".equals(dateType)) {
				map.put("DATE", f.c(f.a(startDate, i + 1), "MM/dd"));
			} else if ("month".equals(dateType)) {
				map.put("DATE", f.c(f.b(startDate, i + 1), "yyyy/MM"));
			}

			map.put("COUNT", Integer.valueOf(count));
			map.put("COUNT2", Integer.valueOf(count2));
			mapList.add(map);
		}

		return mapList;
	}

	public HSSFWorkbook D(List<FxPatientIndex> dataList) {
		HSSFWorkbook workbook = null;

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = this.c(workbook);
			if (dataList != null && dataList.size() > 0) {
				HSSFRow row = e.createRow(0);

				int k;
				for (k = 0; k < this.sK.length; ++k) {
					this.a(row, k, style, 1, this.sK[k]);
				}

				for (k = 0; k < dataList.size(); ++k) {
					HSSFRow row1 = e.createRow(k + 1);

					for (int j = 0; j < this.sK.length; ++j) {
						this.a(row1, j, style, 1, this.a(this.sK[j], (FxPatientIndex) dataList.get(k)));
					}
				}
			} else {
				this.a(e.createRow(0), 0, style, 1, "查无资料");
			}
		} catch (Exception arg8) {
			arg8.printStackTrace();
		}

		return workbook;
	}

	private HSSFCellStyle c(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short)200);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		return style;
	}

	private void a(HSSFRow row, int column, HSSFCellStyle style, int cellType, Object value) {
		HSSFCell cell = row.createCell(column);
		if (style != null) {
			cell.setCellStyle(style);
		}

		switch (cellType) {
			case 0 :
				cell.setCellType(0);
				cell.setCellValue(Double.parseDouble(value.toString()));
				break;
			case 1 :
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			case 2 :
			case 3 :
		}

	}

	private String a(String head, FxPatientIndex enty) {
		return this.deptName.equals(head)
				? enty.getDeptName()
				: (this.deptCode.equals(head)
						? enty.getDeptCode()
						: (this.sJ.equals(head)
								? "" + enty.getFxCount()
								: (this.zdCount.equals(head) ? "" + enty.getZbCount() : "")));
	}

	public List<FxPatientIndex> c(FxPatient fxPatient) {
		return this.sH.findDepFxList(fxPatient);
	}

	public List<FxPatient> findFxPatientByList(List<St003Cryxxb> cryxxbList) {
		return this.sH.findFxPatientByList(cryxxbList);
	}
}