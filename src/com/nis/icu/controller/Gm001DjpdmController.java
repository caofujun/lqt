package com.nis.icu.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcRole;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.icu.entity.Gm001Djpdm;
import com.nis.icu.service.Gm001DjpdmService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Gm001DjpdmController extends BaseController {
	private static final Logger c = Logger.getLogger(Gm001DjpdmController.class);
	@Autowired
	private Gm001DjpdmService sb;
	@Autowired
	private DepService e;

	@RequestMapping({"/gm001Djpdm/f_view/index"})
	public String d(HttpServletRequest request, ModelMap modelMap, String deptId, String monthDate, String ifChildIcu) {
		AcAccount acc = (AcAccount) this.b(request);
		AcRole role = acc.getRoleCur();
		Dep dep;
		if (role != null && "clinical".equals(role.getRoleType())) {
			String acAccount1 = (String) this.b(request, "zg_dept");
			dep = this.e.get(acAccount1);
			if (dep != null) {
				if (dep.getIficu() == null || dep.getIficu().longValue() - 1L != 0L) {
					modelMap.put("msg", "不是ICU科室，无权限评定");
					return "icu/tips";
				}

				modelMap.put("deptId", dep.getDeptId());
				modelMap.put("roleType", "clinical");
			}
		} else {
			AcAccount acAccount = (AcAccount) this.b(request);
			dep = new Dep();
			if (ab.isEmpty(ifChildIcu)) {
				dep.setIficu(Long.valueOf(1L));
			} else {
				dep.setIfchildoffice(Long.valueOf(1L));
			}

			dep.setHospId(acAccount.getUnitId());
			dep.setPage(Integer.valueOf(1));
			dep.setSize(Integer.valueOf(200));
			MyPage icuDepPage = this.e.b(dep);
			dep = new Dep();
			dep.setIfbedicu(Long.valueOf(1L));
			dep.setHospId(acAccount.getUnitId());
			dep.setPage(Integer.valueOf(1));
			dep.setSize(Integer.valueOf(200));
			MyPage bedIcuDepPage = this.e.b(dep);
			icuDepPage.getRows().addAll(bedIcuDepPage.getRows());
			if (icuDepPage.getRows().size() > 0) {
				modelMap.put("deptId", ((Dep) icuDepPage.getRows().get(0)).getDeptId());
			}

			modelMap.put("icuList", icuDepPage.getRows());
			modelMap.put("roleType", "hospital");
		}

		if (ab.isNotEmpty(monthDate)) {
			modelMap.put("monthDate", monthDate);
		} else {
			modelMap.put("monthDate", f.c(new Date(), "yyyy-MM"));
		}

		modelMap.put("unitId", this.c(request));
		return "icu/gm001DjpdmList";
	}

	@RequestMapping({"/gm001Djpdm/f_json/pageQuery"})
	@ResponseBody
	public void C(HttpServletRequest request, HttpServletResponse response, String deptId, String monthDate)
			throws Exception {
		List gm001DjpdmList = this.sb.y(monthDate, deptId);
		MyPage page = new MyPage(1, gm001DjpdmList.size(), gm001DjpdmList.size(), gm001DjpdmList);
		Integer aSum = Integer.valueOf(0);
		Integer bSum = Integer.valueOf(0);
		Integer cSum = Integer.valueOf(0);
		Integer dSum = Integer.valueOf(0);
		Integer eSum = Integer.valueOf(0);
		Double avgscoreSum = Double.valueOf(0.0D);
		Integer sumscoreSum = Integer.valueOf(0);
		Integer noScoreSum = Integer.valueOf(0);
		Iterator gm001DjpdmMxFooterList = gm001DjpdmList.iterator();

		Gm001Djpdm gm001Djpdm;
		while (gm001DjpdmMxFooterList.hasNext()) {
			gm001Djpdm = (Gm001Djpdm) gm001DjpdmMxFooterList.next();
			if (gm001Djpdm.getAcount() != null) {
				aSum = Integer.valueOf(aSum.intValue() + gm001Djpdm.getAcount().intValue());
			}

			if (gm001Djpdm.getBcount() != null) {
				bSum = Integer.valueOf(bSum.intValue() + gm001Djpdm.getBcount().intValue());
			}

			if (gm001Djpdm.getCcount() != null) {
				cSum = Integer.valueOf(cSum.intValue() + gm001Djpdm.getCcount().intValue());
			}

			if (gm001Djpdm.getDcount() != null) {
				dSum = Integer.valueOf(dSum.intValue() + gm001Djpdm.getDcount().intValue());
			}

			if (gm001Djpdm.getEcount() != null) {
				eSum = Integer.valueOf(eSum.intValue() + gm001Djpdm.getEcount().intValue());
			}

			if (gm001Djpdm.getAvgscore() != null) {
				avgscoreSum = Double.valueOf(avgscoreSum.doubleValue() + gm001Djpdm.getAvgscore().doubleValue());
			}

			if (gm001Djpdm.getSumscore() != null) {
				sumscoreSum = Integer.valueOf(sumscoreSum.intValue() + gm001Djpdm.getSumscore().intValue());
			}

			if (gm001Djpdm.getNoScore() != null) {
				noScoreSum = Integer.valueOf(noScoreSum.intValue() + gm001Djpdm.getNoScore().intValue());
			}
		}

		gm001Djpdm = new Gm001Djpdm();
		gm001Djpdm.setWeeknumber("合计");
		gm001Djpdm.setAcount(Double.valueOf(aSum.doubleValue()));
		gm001Djpdm.setBcount(Double.valueOf(bSum.doubleValue()));
		gm001Djpdm.setCcount(Double.valueOf(cSum.doubleValue()));
		gm001Djpdm.setDcount(Double.valueOf(dSum.doubleValue()));
		gm001Djpdm.setEcount(Double.valueOf(eSum.doubleValue()));
		gm001Djpdm.setAvgscore(Double.valueOf(a(avgscoreSum.doubleValue())));
		gm001Djpdm.setSumscore(Double.valueOf(sumscoreSum.doubleValue()));
		gm001Djpdm.setNoScore(noScoreSum);
		ArrayList gm001DjpdmMxFooterList1 = new ArrayList();
		gm001DjpdmMxFooterList1.add(gm001Djpdm);
		page.setFooter(gm001DjpdmMxFooterList1);
		this.a(response, page);
	}

	public static double a(double d) {
		BigDecimal bg = (new BigDecimal(d)).setScale(2, RoundingMode.UP);
		return bg.doubleValue();
	}

	@RequestMapping({"/gm001Djpdm/f_json/exportExcelDjpdm"})
	@ResponseBody
	public void D(HttpServletRequest request, HttpServletResponse response, String deptId, String monthDate) {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("ICU日志_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.sb.z(monthDate, deptId);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
		}

	}
}