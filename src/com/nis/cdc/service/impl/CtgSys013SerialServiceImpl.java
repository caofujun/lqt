package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys013SerialDao;
import com.nis.cdc.entity.CtgSys013Serial;
import com.nis.cdc.service.CtgSys013SerialService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.param.entity.SysParam;
import com.nis.param.service.SysParamService;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys013SerialServiceImpl implements CtgSys013SerialService {
	private static final Logger c = Logger.getLogger(CtgSys013SerialServiceImpl.class);
	@Autowired
	private CtgSys013SerialDao en;
	@Autowired
	private SysParamService j;

	public void save(CtgSys013Serial ctgSys013Serial) {
		this.en.save(ctgSys013Serial);
	}

	public void delete(String idnumber) {
		this.en.delete(idnumber);
	}

	public void update(CtgSys013Serial ctgSys013Serial) {
		this.en.update(ctgSys013Serial);
	}

	public CtgSys013Serial get(String idnumber) {
		return this.en.get(idnumber);
	}

	public MyPage<CtgSys013Serial> a(CtgSys013Serial ctgSys013Serial) {
		int total = this.en.findCtgSys013SerialCount(ctgSys013Serial);
		List data = null;
		if (total > 0) {
			data = this.en.findCtgSys013Serial(ctgSys013Serial);
		}

		return new MyPage(ctgSys013Serial.getPage().intValue(), ctgSys013Serial.getSize().intValue(), total, data);
	}

	public List<CtgSys013Serial> getAll() {
		return this.en.getAll();
	}

	public CtgSys013Serial getAUnusedRecord() {
		return this.en.getAUnusedRecord();
	}

	public synchronized String x(String masterid) {
		DecimalFormat decimalFormat = new DecimalFormat("0000");
		int cY = Calendar.getInstance().get(1);
		int pY = Integer.parseInt(this.j.findByParamCode(Param.NIS_CDC_GDSN_RECORD_YEAR));
		String oC = this.j.findByParamCode(Param.NIS_CDC_GDSN_ORG_CODE);
		SysParam param;
		if (cY != pY) {
			SysParam unusedSerial = this.j.getByParamCode(Param.NIS_CDC_GDSN_RECORD_YEAR.getCode());
			unusedSerial.setParamValue(String.valueOf(cY));
			this.j.update(unusedSerial);
			param = this.j.getByParamCode(Param.NIS_CDC_GDSN_NEXT_NUMBER.getCode());
			String startn = this.isSNUsed(String.valueOf(cY));
			if (ab.isEmpty(startn)) {
				param.setParamValue("1");
				this.j.update(param);
			} else {
				int orgCode = Integer.parseInt(startn.substring(13)) + 1;
				param.setParamValue(String.valueOf(orgCode));
				this.j.update(param);
			}
		}

		CtgSys013Serial unusedSerial1 = this.getAUnusedRecord();
		if (unusedSerial1 != null && ab.isNotEmpty(unusedSerial1.getSerialnumber())) {
			unusedSerial1.setIsReuse(Long.valueOf(1L));
			unusedSerial1.setReMasterid(masterid);
			this.update(unusedSerial1);
			return unusedSerial1.getSerialnumber();
		} else {
			param = this.j.getByParamCode(Param.NIS_CDC_GDSN_NEXT_NUMBER.getCode());
			int startn1 = Integer.parseInt(param.getParamValue());
			String orgCode1 = this.j.findByParamCode(Param.NIS_CDC_GDSN_ORG_CODE);
			int sn = startn1 + 1;
			param.setParamValue(String.valueOf(sn));
			this.j.update(param);
			String serialNumber = orgCode1 + cY + decimalFormat.format((long) startn1);
			return serialNumber;
		}
	}

	public void updateUnusedRecord(String serialNumber, String masterId, String isReuse) {
		this.en.updateUnusedRecord(serialNumber, masterId, isReuse);
	}

	public String isSNUsed(String year) {
		return this.en.isSNUsed(year);
	}
}