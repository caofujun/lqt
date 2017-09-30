package com.nis.bl.controller;

import com.nis.access.entity.AcAccount;
import com.nis.bl.entity.Bl002Sjdj;
import com.nis.bl.entity.Bl005Wtjg;
import com.nis.bl.entity.Bl006Jyjg;
import com.nis.bl.service.Bl002SjdjService;
import com.nis.bl.service.Bl005WtjgService;
import com.nis.bl.service.Bl006JyjgService;
import com.nis.bl.service.Bl007FcsjService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.utils.ab;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Bl005WtjgController extends BaseController {
	private static final Logger c = Logger.getLogger(BaseController.class);
	@Autowired
	private Bl005WtjgService cS;
	@Autowired
	private Bl002SjdjService cP;
	@Autowired
	private Bl006JyjgService cQ;
	@Autowired
	private Bl007FcsjService cX;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private SysParamService j;
	@Autowired
	private DepService e;

	@RequestMapping({"/bl005Wtjg/f_json/save"})
	@ResponseBody
	@SqlLog(p = "职业暴露--保存列表结果")
	public void a(HttpServletRequest request, HttpServletResponse response, Bl002Sjdj bl002Sjdj, String type,
			String onlySave) {
		Result result = null;
		AcAccount account = (AcAccount) this.b(request);

		try {
			result = new Result();
			Map e = this.cS.a(request);
			Set set = e.entrySet();
			if ("04".equals(type)) {
				this.cS.delete(bl002Sjdj.getBlId(), Long.valueOf(34L));
				this.cS.delete(bl002Sjdj.getBlId(), Long.valueOf(40L));
				this.cS.delete(bl002Sjdj.getBlId(), Long.valueOf(130L));
				this.cS.delete(bl002Sjdj.getBlId(), Long.valueOf(132L));
			} else if ("05".equals(type)) {
				this.cS.delete(bl002Sjdj.getBlId(), Long.valueOf(195L));
				this.cS.delete(bl002Sjdj.getBlId(), Long.valueOf(215L));
				this.cS.delete(bl002Sjdj.getBlId(), Long.valueOf(216L));
			}

			Iterator bl006JyjgList = set.iterator();

			label106 : while (true) {
				String jyjg;
				String value;
				do {
					if (!bl006JyjgList.hasNext()) {
						Bl002Sjdj arg20 = this.cP.get(bl002Sjdj.getBlId());
						if ("01".equals(type)) {
							arg20.setBlStep("1");
							arg20.setSjState("0");
						}

						if ("02".equals(type)) {
							arg20.setBlStep("2");
							arg20.setSjState("0");
						}

						if ("03".equals(type)) {
							arg20.setSjMemo(bl002Sjdj.getSjMemo());
							arg20.setBlStep("3");
							arg20.setSjBlqk(bl002Sjdj.getSjBlqk());
							arg20.setSjState("0");
						}

						if ("04".equals(type)) {
							arg20.setSjBlqk(bl002Sjdj.getSjBlqk());
							arg20.setSjMemo(bl002Sjdj.getSjMemo());
							arg20.setPgMs(bl002Sjdj.getPgMs());
							arg20.setPgJy(bl002Sjdj.getPgJy());
							arg20.setPgKjyj(bl002Sjdj.getPgKjyj());
							if (!"1".equals(onlySave)) {
								arg20.setSjState("1");
								arg20.setBlStep("4");
							}

							arg20.setKjyjQm(bl002Sjdj.getKjyjQm());
							Dep arg21 = null;
							if (ab.isNotEmpty(account.getDepNo())) {
								arg21 = this.e.get(account.getDepNo());
							}

							jyjg = arg20.getDjMen() + "（" + (arg21 != null ? arg21.getDeptName() : "")
									+ "）上报了 职业暴露  报卡";
							this.cV.a((String) null, (String) null, account.getUsername(), account.getRealname(), jyjg,
									(String[]) null, new String[]{this.j.findByParamCode(Param.NIS_GK_DEPTID)},
									al.jo.getValue(), arg20.getBlId());
						}

						List arg22;
						if ("05".equals(type)) {
							arg20.setZjclYj(bl002Sjdj.getZjclYj());
							arg20.setXlzxsYj(bl002Sjdj.getXlzxsYj());
							arg20.setYgkYj(bl002Sjdj.getYgkYj());
							arg20.setBlStep("5");
							arg22 = bl002Sjdj.getBl004CsDetailinfoList();
							this.cX.deleteByBlid(bl002Sjdj.getBlId());
							this.cQ.delete(bl002Sjdj.getBlId());
							if (arg22 != null && arg22.size() > 0) {
								this.cX.b(bl002Sjdj.getBlId(), arg22);
								this.cQ.a(bl002Sjdj.getBlId(), arg20.getEnterTime(), this.d(request).getRealname(),
										arg22);
							}
						}

						if ("06".equals(type)) {
							arg20.setPgYyname1(bl002Sjdj.getPgYyname1());
							arg20.setPgYyjl1(bl002Sjdj.getPgYyjl1());
							arg20.setPgYystime1(bl002Sjdj.getPgYystime1());
							arg20.setPgYyetime1(bl002Sjdj.getPgYyetime1());
							arg20.setPgYyname2(bl002Sjdj.getPgYyname2());
							arg20.setPgYyjl2(bl002Sjdj.getPgYyjl2());
							arg20.setPgYystime2(bl002Sjdj.getPgYystime2());
							arg20.setPgYyetime2(bl002Sjdj.getPgYyetime2());
							arg20.setPgYyname3(bl002Sjdj.getPgYyname3());
							arg20.setPgYyjl3(bl002Sjdj.getPgYyjl3());
							arg20.setPgYystime3(bl002Sjdj.getPgYystime3());
							arg20.setPgYyetime3(bl002Sjdj.getPgYyetime3());
							arg20.setPgYyname4(bl002Sjdj.getPgYyname4());
							arg20.setPgYyjl4(bl002Sjdj.getPgYyjl4());
							arg20.setPgYystime4(bl002Sjdj.getPgYystime4());
							arg20.setPgYyetime4(bl002Sjdj.getPgYyetime4());
							arg20.setPgYyname5(bl002Sjdj.getPgYyname5());
							arg20.setPgYyjl5(bl002Sjdj.getPgYyjl5());
							arg20.setPgYystime5(bl002Sjdj.getPgYystime5());
							arg20.setPgYyetime5(bl002Sjdj.getPgYyetime5());
							arg20.setSjState("5");
							arg20.setBlStep("6");
						}

						if ("07".equals(type)) {
							arg22 = bl002Sjdj.getBl006JyjgList();
							if (arg22 != null && arg22.size() > 0) {
								Iterator arg24 = arg22.iterator();

								while (arg24.hasNext()) {
									Bl006Jyjg arg23 = (Bl006Jyjg) arg24.next();
									arg23.setBlId(arg20.getBlId());
									this.cQ.update(arg23);
								}
							}

							if (!"1".equals(onlySave)) {
								arg20.setSjState("7");
								arg20.setBlStep("7");
							}
						}

						if ("08".equals(type)) {
							arg20.setBlJl(bl002Sjdj.getBlJl());
							arg20.setBlJlMen(bl002Sjdj.getBlJlMen());
							arg20.setSjState("9");
							arg20.setBlStep("8");
						}

						this.cP.update(arg20);
						result.setResult("success");
						break label106;
					}

					Entry sjdj = (Entry) bl006JyjgList.next();
					jyjg = sjdj.getKey().toString();
					value = sjdj.getValue().toString();
				} while (jyjg.indexOf("wtjg") <= -1);

				this.cS.delete(bl002Sjdj.getBlId(), Long.valueOf(Long.parseLong(jyjg.substring(4))));
				String[] values = value.split(",");
				String[] arg17 = values;
				int arg16 = values.length;

				for (int arg15 = 0; arg15 < arg16; ++arg15) {
					String vl = arg17[arg15];
					if (ab.isNotEmpty(vl)) {
						Bl005Wtjg wtjg = new Bl005Wtjg();
						wtjg.setBlId(bl002Sjdj.getBlId());
						wtjg.setSjId("XY_TY_DJ");
						wtjg.setStId(Long.valueOf(Long.parseLong(jyjg.substring(4))));
						wtjg.setShText(vl);
						wtjg.setShType("1");
						wtjg.setShMemo(vl);
						wtjg.setFlag(Long.valueOf(1L));
						this.cS.save(wtjg);
					}
				}
			}
		} catch (Exception arg19) {
			c.error("获取信息异常!", arg19);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}