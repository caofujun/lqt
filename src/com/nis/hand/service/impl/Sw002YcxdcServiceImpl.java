package com.nis.hand.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.comm.utils.z;
import com.nis.hand.dao.Sw002YcxdcDao;
import com.nis.hand.dao.Sw003YcxsjDao;
import com.nis.hand.dao.Sw004YcxsjDao;
import com.nis.hand.entity.Sw002Ycxdc;
import com.nis.hand.entity.Sw004Ycxsj;
import com.nis.hand.service.Sw002YcxdcService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
public class Sw002YcxdcServiceImpl implements Sw002YcxdcService {
	private static final Logger c = Logger.getLogger(Sw002YcxdcServiceImpl.class);
	@Autowired
	private Sw002YcxdcDao re;
	@Autowired
	private Sw003YcxsjDao rf;
	@Autowired
	private Sw004YcxsjDao rg;

	public void save(Sw002Ycxdc sw002Ycxdc) {
		this.re.save(sw002Ycxdc);
	}

	public Result<String> i(String dcId) {
		Result r = new Result();

		try {
			if (ab.isNotEmpty(dcId)) {
				this.re.delete(dcId);
				this.rg.deleteByDcid(dcId);
				r.setResult("success");
				r.setMsg("删除成功！");
			} else {
				r.setResult("error");
				r.setMsg("删除失败！");
				r.setExtraValue("未获取到必要参数！");
			}
		} catch (Exception arg5) {
			arg5.printStackTrace();
			c.error("删除数据失败！", arg5);
			r.setResult("error");
			r.setExtraValue(arg5.getMessage());
			r.setMsg("删除失败！");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			;
		}

		return r;
	}

	public void update(Sw002Ycxdc sw002Ycxdc) {
		this.re.update(sw002Ycxdc);
	}

	public Sw002Ycxdc get(String dcId) {
		return this.re.get(dcId);
	}

	public MyPage<Sw002Ycxdc> a(Sw002Ycxdc sw002Ycxdc) {
		int total = this.re.findSw002YcxdcCount(sw002Ycxdc);
		List data = null;
		if (total > 0) {
			data = this.re.findSw002Ycxdc(sw002Ycxdc);
		}

		return new MyPage(sw002Ycxdc.getPage().intValue(), sw002Ycxdc.getSize().intValue(), total, data);
	}

	public List<Sw002Ycxdc> getAll() {
		return this.re.getAll();
	}

	@Transactional
	public Result<Sw002Ycxdc> a(Sw002Ycxdc sw002Ycxdc, AcAccount acount) {
		Result r = new Result();

		try {
			if (ab.isEmpty(sw002Ycxdc.getDcId())) {
				sw002Ycxdc.setDcId(z.a(bg.nO));
				sw002Ycxdc.setGcUserId(acount.getUserId());
				sw002Ycxdc.setGcUsername(acount.getRealname());
				sw002Ycxdc.setDjDate(new Date());
				this.re.save(sw002Ycxdc);
			} else {
				this.re.update(sw002Ycxdc);
			}

			List e = sw002Ycxdc.getWatchList2();
			this.rg.deleteByDcid(sw002Ycxdc.getDcId());
			if (e != null && !e.isEmpty()) {
				Iterator arg5 = e.iterator();

				label101 : while (true) {
					Sw004Ycxsj sj;
					String sjId;
					do {
						do {
							if (!arg5.hasNext()) {
								break label101;
							}

							sj = (Sw004Ycxsj) arg5.next();
							sjId = z.a(bg.nP);
						} while (sj == null);
					} while (!ab.isNotEmpty(sj.getRyType()));

					String[] zzs = sj.getSjList().split(",");
					String[] arg11 = zzs;
					int arg10 = zzs.length;

					for (int arg9 = 0; arg9 < arg10; ++arg9) {
						String zz = arg11[arg9];
						sj.setSjList(zz);
						sj.setKeyId(af.getUUID32());
						sj.setDcId(sw002Ycxdc.getDcId());
						sj.setSjId(sjId);
						this.rg.save(sj);
					}
				}
			}

			r.setResult("success");
			r.setData(sw002Ycxdc);
			r.setMsg("保存成功！");
		} catch (Exception arg14) {
			arg14.printStackTrace();
			c.error("保存数据失败！", arg14);
			r.setResult("error");
			r.setExtraValue(arg14.getMessage());
			r.setMsg("保存失败！");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			;
		}

		return r;
	}
}