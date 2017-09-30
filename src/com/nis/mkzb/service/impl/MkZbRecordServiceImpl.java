package com.nis.mkzb.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import com.nis.mkzb.dao.MkZbRecordDao;
import com.nis.mkzb.entity.MkZbRecord;
import com.nis.mkzb.service.MkZbRecordService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MkZbRecordServiceImpl implements MkZbRecordService {
	@Autowired
	private MkZbRecordDao up;
	@Autowired
	private ApplicationContext dO;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysDictService p;
	private boolean uq = false;

	public MyPage<MkZbRecord> a(MkZbRecord zbRecord) {
		try {
			zbRecord.setStartDate();
			zbRecord.setEndDate();
		} catch (ParseException arg3) {
			arg3.printStackTrace();
		}

		HashMap paramMap = new HashMap();
		paramMap.put("startDate", zbRecord.getStartDate());
		paramMap.put("endDate", zbRecord.getEndDate());
		List data = this.up.findZbRecordList(zbRecord);
		return new MyPage(1, 100, 50, data);
	}

	public boolean bL(String sPath) {
		this.uq = false;
		File file = new File(sPath);
		if (file.isFile() && file.exists()) {
			file.delete();
			this.uq = true;
		}

		return this.uq;
	}

	public boolean bM(String sPath) {
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}

		File dirFile = new File(sPath);
		if (dirFile.exists() && dirFile.isDirectory()) {
			this.uq = true;
			File[] files = dirFile.listFiles();

			for (int i = 0; i < files.length; ++i) {
				if (files[i].isFile()) {
					this.uq = this.bL(files[i].getAbsolutePath());
					if (!this.uq) {
						break;
					}
				} else {
					this.uq = this.bM(files[i].getAbsolutePath());
					if (!this.uq) {
						break;
					}
				}
			}

			return !this.uq ? false : dirFile.delete();
		} else {
			return false;
		}
	}

	public String c(MkZbRecord zbRecord) {
		List zBMapList0 = null;
		List brjbxxList = null;
		List jcjbList = null;
		List ssqkList = null;
		List ygysList = null;
		List qhxczList = null;
		List grbwxxqkList = null;
		List grbwxgqhxczList = null;
		List jcbbxxqkList = null;
		List bbjcbytxxqkList = null;
		List ymsyjgList = null;
		List kjywsyqkList = null;
		List icubrjbqkList = null;
		List icujbzdList = null;
		List zxjmcgqkList = null;
		List hxjsyqkList = null;
		List dngsyqkList = null;
		String ids = "";
		zBMapList0 = this.up.caseRegist(zbRecord);
		Properties props = System.getProperties();
		String os = props.getProperty("os.name");
		String courseFileUrl = System.getProperty("catalina.home");
		String delFile = courseFileUrl + "\\直报\\患者信息上传";
		File dfile = new File(delFile);
		if (dfile.exists()) {
			this.bM(delFile);
		}

		System.out.println("操作系统的名称：" + props.getProperty("os.name"));
		Iterator arg25 = zBMapList0.iterator();

		while (true) {
			while (arg25.hasNext()) {
				HashMap map = (HashMap) arg25.next();
				ids = ids + map.get("KEY_ID").toString() + ",";
				brjbxxList = this.up.findBrjbxx(map.get("KEY_ID").toString());
				jcjbList = this.up.findjcjb(map.get("KEY_ID").toString());
				ssqkList = this.up.findSsqk(map.get("KEY_ID").toString());
				ygysList = this.up.findYgys(map.get("KEY_ID").toString());
				qhxczList = this.up.findQhxcz(map.get("KEY_ID").toString());
				grbwxxqkList = this.up.findGrbwxxqk(map.get("KEY_ID").toString());
				grbwxgqhxczList = this.up.findGrbwxgqhxcz(map.get("KEY_ID").toString());
				jcbbxxqkList = this.up.findJcbbxxqk(map.get("KEY_ID").toString());
				bbjcbytxxqkList = this.up.findBbjcbytxxqk(map.get("KEY_ID").toString());
				ymsyjgList = this.up.findYmsyjg(map.get("KEY_ID").toString());
				kjywsyqkList = this.up.findKjywsyqk(map.get("KEY_ID").toString());
				icubrjbqkList = this.up.findIcubrjbqk(map.get("KEY_ID").toString());
				icujbzdList = this.up.findIcujbzd(map.get("KEY_ID").toString());
				zxjmcgqkList = this.up.findZxjmcgqk(map.get("KEY_ID").toString());
				hxjsyqkList = this.up.findHxjsyqk(map.get("KEY_ID").toString());
				dngsyqkList = this.up.findDngsyqk(map.get("KEY_ID").toString());
				String name = map.get("NAME").toString();
				String fileName = "";
				if (name.contains("*")) {
					String[] file = name.split("\\*");
					fileName = courseFileUrl + "\\直报\\患者信息上传\\" + map.get("PATIENTID").toString() + "-" + file[0];
				} else {
					fileName = courseFileUrl + "\\直报\\患者信息上传\\" + map.get("PATIENTID").toString() + "-" + name;
				}

				File arg79 = new File(fileName);
				arg79.mkdirs();
				StringBuffer sqlData1 = new StringBuffer();
				FileWriter sqlData3;
				File arg80;
				if (brjbxxList.size() <= 0) {
					try {
						arg80 = new File(fileName + "\\病人基本信息.txt");
						sqlData3 = new FileWriter(arg80);
						sqlData3.write(sqlData1.toString());
						sqlData3.close();
					} catch (IOException arg78) {
						arg78.printStackTrace();
					}
				} else {
					for (int sqlData2 = 0; sqlData2 < brjbxxList.size(); ++sqlData2) {
						if (brjbxxList.get(sqlData2) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) brjbxxList.get(sqlData2)).get("F1") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F1").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F2") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F2").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F3") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F3").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F4") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F4").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F5") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F5").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F6") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F6").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F7") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F7").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F8") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F8").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F9") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F9").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F10") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F10").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F11") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F11").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F12") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F12").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F13") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F13").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F14") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F14").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F15") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F15").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F16") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F16").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F17") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F17").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F18") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F18").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F19") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F19").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F20") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F20").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F21") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F21").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F22") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F22").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F23") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F23").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F24") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F24").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F25") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F25").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F26") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F26").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F27") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F27").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F28") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F28").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F29") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F29").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F30") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F30").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F31") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F31").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F32") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F32").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F33") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F33").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F34") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F34").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F35") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F35").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F36") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F36").toString());
								} else {
									sqlData1.append("\t");
								}

								if (sqlData2 != brjbxxList.size() - 1) {
									sqlData1.append("\r\n");
								}
							} else {
								if (((HashMap) brjbxxList.get(sqlData2)).get("F1") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F1").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F2") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F2").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F3") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F3").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F4") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F4").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F5") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F5").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F6") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F6").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F7") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F7").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F8") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F8").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F9") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F9").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F10") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F10").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F11") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F11").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F12") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F12").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F13") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F13").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F14") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F14").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F15") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F15").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F16") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F16").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F17") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F17").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F18") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F18").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F19") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F19").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F20") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F20").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F21") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F21").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F22") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F22").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F23") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F23").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F24") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F24").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F25") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F25").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F26") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F26").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F27") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F27").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F28") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F28").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F29") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F29").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F30") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F30").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F31") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F31").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F32") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F32").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F33") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F33").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F34") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F34").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F35") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F35").toString() + "\t");
								} else {
									sqlData1.append("\t");
								}

								if (((HashMap) brjbxxList.get(sqlData2)).get("F36") != null) {
									sqlData1.append(((HashMap) brjbxxList.get(sqlData2)).get("F36").toString());
								} else {
									sqlData1.append("\t");
								}

								if (sqlData2 != brjbxxList.size() - 1) {
									sqlData1.append("\n");
								}
							}
						}
					}

					try {
						arg80 = new File(fileName + "\\病人基本信息.txt");
						sqlData3 = new FileWriter(arg80);
						sqlData3.write(sqlData1.toString());
						sqlData3.close();
					} catch (IOException arg47) {
						arg47.printStackTrace();
					}
				}

				StringBuffer arg81 = new StringBuffer();
				FileWriter sqlData4;
				File arg83;
				if (jcjbList.size() <= 0) {
					arg83 = new File(fileName + "\\基础疾病.txt");

					try {
						sqlData4 = new FileWriter(arg83);
						sqlData4.write(arg81.toString());
						sqlData4.close();
					} catch (IOException arg76) {
						arg76.printStackTrace();
					}
				} else {
					for (int arg82 = 0; arg82 < jcjbList.size(); ++arg82) {
						if (jcjbList.get(arg82) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) jcjbList.get(arg82)).get("DIAGNOSISNAME") != null) {
									arg81.append(((HashMap) jcjbList.get(arg82)).get("DIAGNOSISNAME").toString());
								} else {
									arg81.append("\t");
								}

								if (arg82 != jcjbList.size() - 1) {
									arg81.append("\r\n");
								}
							} else {
								if (((HashMap) jcjbList.get(arg82)).get("DIAGNOSISNAME") != null) {
									arg81.append(((HashMap) jcjbList.get(arg82)).get("DIAGNOSISNAME").toString());
								} else {
									arg81.append("\t");
								}

								if (arg82 != jcjbList.size() - 1) {
									arg81.append("\n");
								}
							}
						}
					}

					try {
						arg83 = new File(fileName + "\\基础疾病.txt");
						sqlData4 = new FileWriter(arg83);
						sqlData4.write(arg81.toString());
						sqlData4.close();
					} catch (IOException arg77) {
						arg77.printStackTrace();
					}
				}

				StringBuffer arg85 = new StringBuffer();
				FileWriter sqlData5;
				File arg86;
				if (ssqkList.size() <= 0) {
					arg86 = new File(fileName + "\\手术情况.txt");

					try {
						sqlData5 = new FileWriter(arg86);
						sqlData5.write(arg85.toString());
						sqlData5.close();
					} catch (IOException arg74) {
						arg74.printStackTrace();
					}
				} else {
					for (int arg84 = 0; arg84 < ssqkList.size(); ++arg84) {
						if (ssqkList.get(arg84) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) ssqkList.get(arg84)).get("F1") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F1").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F2") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F2").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F3") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F3").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F4") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F4").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F5") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F5").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F6") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F6").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F7") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F7").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F8") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F8").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F9") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F9").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F10") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F10").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F11") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F11").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F12") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F12").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F13") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F13").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F14") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F14").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F15") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F15").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F16") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F16").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F17") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F17").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F18") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F18").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F19") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F19").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F20") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F20").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F21") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F21").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F22") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F22").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F23") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F23").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F24") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F24").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F25") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F25").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F26") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F26").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F27") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F27").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F28") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F28").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F29") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F29").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F30") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F30").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F31") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F31").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F32") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F32").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F33") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F33").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F34") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F34").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F35") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F35").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F36") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F36").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F37") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F37").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F38") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F38").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F39") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F39").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F40") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F40").toString());
								} else {
									arg85.append("\t");
								}

								if (arg84 != ssqkList.size() - 1) {
									arg85.append("\r\n");
								}
							} else {
								if (((HashMap) ssqkList.get(arg84)).get("F1") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F1").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F2") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F2").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F3") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F3").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F4") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F4").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F5") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F5").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F6") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F6").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F7") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F7").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F8") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F8").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F9") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F9").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F10") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F10").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F11") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F11").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F12") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F12").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F13") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F13").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F14") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F14").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F15") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F15").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F16") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F16").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F17") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F17").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F18") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F18").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F19") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F19").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F20") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F20").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F21") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F21").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F22") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F22").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F23") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F23").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F24") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F24").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F25") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F25").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F26") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F26").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F27") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F27").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F28") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F28").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F29") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F29").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F30") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F30").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F31") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F31").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F32") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F32").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F33") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F33").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F34") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F34").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F35") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F35").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F36") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F36").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F37") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F37").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F38") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F38").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F39") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F39").toString() + "\t");
								} else {
									arg85.append("\t");
								}

								if (((HashMap) ssqkList.get(arg84)).get("F40") != null) {
									arg85.append(((HashMap) ssqkList.get(arg84)).get("F40").toString());
								} else {
									arg85.append("\t");
								}

								if (arg84 != ssqkList.size() - 1) {
									arg85.append("\n");
								}
							}
						}
					}

					try {
						arg86 = new File(fileName + "\\手术情况.txt");
						sqlData5 = new FileWriter(arg86);
						sqlData5.write(arg85.toString());
						sqlData5.close();
					} catch (IOException arg75) {
						arg75.printStackTrace();
					}
				}

				StringBuffer arg88 = new StringBuffer();
				FileWriter sqlData6;
				File arg89;
				if (ygysList.size() <= 0) {
					arg89 = new File(fileName + "\\易感因素.txt");

					try {
						sqlData6 = new FileWriter(arg89);
						sqlData6.write(arg88.toString());
						sqlData6.close();
					} catch (IOException arg72) {
						arg72.printStackTrace();
					}
				} else {
					for (int arg87 = 0; arg87 < ygysList.size(); ++arg87) {
						if (ygysList.get(arg87) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) ygysList.get(arg87)).get("FACTOR_NAME") != null) {
									arg88.append(((HashMap) ygysList.get(arg87)).get("FACTOR_NAME").toString());
								} else {
									arg88.append("\t");
								}

								if (arg87 != ygysList.size() - 1) {
									arg88.append("\r\n");
								}
							} else {
								if (((HashMap) ygysList.get(arg87)).get("FACTOR_NAME") != null) {
									arg88.append(((HashMap) ygysList.get(arg87)).get("FACTOR_NAME").toString());
								} else {
									arg88.append("\t");
								}

								if (arg87 != ygysList.size() - 1) {
									arg88.append("\n");
								}
							}
						}
					}

					try {
						arg89 = new File(fileName + "\\易感因素.txt");
						sqlData6 = new FileWriter(arg89);
						sqlData6.write(arg88.toString());
						sqlData6.close();
					} catch (IOException arg73) {
						arg73.printStackTrace();
					}
				}

				StringBuffer arg91 = new StringBuffer();
				FileWriter sqlData7;
				File arg92;
				if (qhxczList.size() <= 0) {
					arg92 = new File(fileName + "\\侵害性操作.txt");

					try {
						sqlData7 = new FileWriter(arg92);
						sqlData7.write(arg91.toString());
						sqlData7.close();
					} catch (IOException arg70) {
						arg70.printStackTrace();
					}
				} else {
					for (int arg90 = 0; arg90 < qhxczList.size(); ++arg90) {
						if (qhxczList.get(arg90) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) qhxczList.get(arg90)).get("ORDER_NAME") != null) {
									arg91.append(((HashMap) qhxczList.get(arg90)).get("ORDER_NAME").toString());
								} else {
									arg91.append("\t");
								}

								if (arg90 != qhxczList.size() - 1) {
									arg91.append("\r\n");
								}
							} else {
								if (((HashMap) qhxczList.get(arg90)).get("ORDER_NAME") != null) {
									arg91.append(((HashMap) qhxczList.get(arg90)).get("ORDER_NAME").toString());
								} else {
									arg91.append("\t");
								}

								if (arg90 != qhxczList.size() - 1) {
									arg91.append("\n");
								}
							}
						}
					}

					try {
						arg92 = new File(fileName + "\\侵害性操作.txt");
						sqlData7 = new FileWriter(arg92);
						sqlData7.write(arg91.toString());
						sqlData7.close();
					} catch (IOException arg71) {
						arg71.printStackTrace();
					}
				}

				StringBuffer arg94 = new StringBuffer();
				FileWriter sqlData8;
				File arg95;
				if (grbwxxqkList.size() <= 0) {
					arg95 = new File(fileName + "\\感染部位详细情况.txt");

					try {
						sqlData8 = new FileWriter(arg95);
						sqlData8.write(arg94.toString());
						sqlData8.close();
					} catch (IOException arg68) {
						arg68.printStackTrace();
					}
				} else {
					for (int arg93 = 0; arg93 < grbwxxqkList.size(); ++arg93) {
						if (grbwxxqkList.get(arg93) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) grbwxxqkList.get(arg93)).get("FL_INFECT_NAME") != null) {
									arg94.append(((HashMap) grbwxxqkList.get(arg93)).get("FL_INFECT_NAME").toString()
											+ "\t");
								} else {
									arg94.append("\t");
								}

								if (((HashMap) grbwxxqkList.get(arg93)).get("INFECT_DATE") != null) {
									arg94.append(
											((HashMap) grbwxxqkList.get(arg93)).get("INFECT_DATE").toString() + "\t");
								} else {
									arg94.append("\t");
								}

								if (((HashMap) grbwxxqkList.get(arg93)).get("INFECT_DIAGN_NAME") != null) {
									arg94.append(
											((HashMap) grbwxxqkList.get(arg93)).get("INFECT_DIAGN_NAME").toString());
								} else {
									arg94.append("\t");
								}

								if (arg93 != grbwxxqkList.size() - 1) {
									arg94.append("\r\n");
								}
							} else {
								if (((HashMap) grbwxxqkList.get(arg93)).get("FL_INFECT_NAME") != null) {
									arg94.append(((HashMap) grbwxxqkList.get(arg93)).get("FL_INFECT_NAME").toString()
											+ "\t");
								} else {
									arg94.append("\t");
								}

								if (((HashMap) grbwxxqkList.get(arg93)).get("INFECT_DATE") != null) {
									arg94.append(
											((HashMap) grbwxxqkList.get(arg93)).get("INFECT_DATE").toString() + "\t");
								} else {
									arg94.append("\t");
								}

								if (((HashMap) grbwxxqkList.get(arg93)).get("INFECT_DIAGN_NAME") != null) {
									arg94.append(
											((HashMap) grbwxxqkList.get(arg93)).get("INFECT_DIAGN_NAME").toString());
								} else {
									arg94.append("\t");
								}

								if (arg93 != grbwxxqkList.size() - 1) {
									arg94.append("\n");
								}
							}
						}
					}

					try {
						arg95 = new File(fileName + "\\感染部位详细情况.txt");
						sqlData8 = new FileWriter(arg95);
						sqlData8.write(arg94.toString());
						sqlData8.close();
					} catch (IOException arg69) {
						arg69.printStackTrace();
					}
				}

				StringBuffer arg97 = new StringBuffer();
				FileWriter sqlData9;
				File arg98;
				if (grbwxgqhxczList.size() <= 0) {
					arg98 = new File(fileName + "\\感染部位相关的侵害性操作.txt");

					try {
						sqlData9 = new FileWriter(arg98);
						sqlData9.write(arg97.toString());
						sqlData9.close();
					} catch (IOException arg66) {
						arg66.printStackTrace();
					}
				} else {
					for (int arg96 = 0; arg96 < grbwxgqhxczList.size(); ++arg96) {
						if (grbwxgqhxczList.get(arg96) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) grbwxgqhxczList.get(arg96)).get("FL_INFECT_NAME") != null) {
									arg97.append(((HashMap) grbwxgqhxczList.get(arg96)).get("FL_INFECT_NAME").toString()
											+ "\t");
								} else {
									arg97.append("\t");
								}

								if (((HashMap) grbwxgqhxczList.get(arg96)).get("INFECT_DATE") != null) {
									arg97.append(((HashMap) grbwxgqhxczList.get(arg96)).get("INFECT_DATE").toString()
											+ "\t");
								} else {
									arg97.append("\t");
								}

								if (((HashMap) grbwxgqhxczList.get(arg96)).get("ORDER_NAME") != null) {
									arg97.append(((HashMap) grbwxgqhxczList.get(arg96)).get("ORDER_NAME").toString());
								} else {
									arg97.append("\t");
								}

								if (arg96 != grbwxgqhxczList.size() - 1) {
									arg97.append("\r\n");
								}
							} else {
								if (((HashMap) grbwxgqhxczList.get(arg96)).get("FL_INFECT_NAME") != null) {
									arg97.append(((HashMap) grbwxgqhxczList.get(arg96)).get("FL_INFECT_NAME").toString()
											+ "\t");
								} else {
									arg97.append("\t");
								}

								if (((HashMap) grbwxgqhxczList.get(arg96)).get("INFECT_DATE") != null) {
									arg97.append(((HashMap) grbwxgqhxczList.get(arg96)).get("INFECT_DATE").toString()
											+ "\t");
								} else {
									arg97.append("\t");
								}

								if (((HashMap) grbwxgqhxczList.get(arg96)).get("ORDER_NAME") != null) {
									arg97.append(((HashMap) grbwxgqhxczList.get(arg96)).get("ORDER_NAME").toString());
								} else {
									arg97.append("\t");
								}

								if (arg96 != grbwxgqhxczList.size() - 1) {
									arg97.append("\n");
								}
							}
						}
					}

					try {
						arg98 = new File(fileName + "\\感染部位相关的侵害性操作.txt");
						sqlData9 = new FileWriter(arg98);
						sqlData9.write(arg97.toString());
						sqlData9.close();
					} catch (IOException arg67) {
						arg67.printStackTrace();
					}
				}

				StringBuffer arg100 = new StringBuffer();
				FileWriter sqlData10;
				File arg101;
				if (jcbbxxqkList.size() <= 0) {
					arg101 = new File(fileName + "\\检测标本详细情况.txt");

					try {
						sqlData10 = new FileWriter(arg101);
						sqlData10.write(arg100.toString());
						sqlData10.close();
					} catch (IOException arg64) {
						arg64.printStackTrace();
					}
				} else {
					for (int arg99 = 0; arg99 < jcbbxxqkList.size(); ++arg99) {
						if (jcbbxxqkList.get(arg99) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) jcbbxxqkList.get(arg99)).get("ITEM_NAME") != null) {
									arg100.append(
											((HashMap) jcbbxxqkList.get(arg99)).get("ITEM_NAME").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("FL_INFECT_NAME") != null) {
									arg100.append(((HashMap) jcbbxxqkList.get(arg99)).get("FL_INFECT_NAME").toString()
											+ "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("SUBMI_AT") != null) {
									arg100.append(
											((HashMap) jcbbxxqkList.get(arg99)).get("SUBMI_AT").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("JYFF") != null) {
									arg100.append(((HashMap) jcbbxxqkList.get(arg99)).get("JYFF").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("BXB") != null) {
									arg100.append(((HashMap) jcbbxxqkList.get(arg99)).get("BXB").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("INFECT_DATE") != null) {
									arg100.append(
											((HashMap) jcbbxxqkList.get(arg99)).get("INFECT_DATE").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("TEST_RESULT") != null) {
									arg100.append(((HashMap) jcbbxxqkList.get(arg99)).get("TEST_RESULT").toString());
								} else {
									arg100.append("\t");
								}

								if (arg99 != jcbbxxqkList.size() - 1) {
									arg100.append("\r\n");
								}
							} else {
								if (((HashMap) jcbbxxqkList.get(arg99)).get("ITEM_NAME") != null) {
									arg100.append(
											((HashMap) jcbbxxqkList.get(arg99)).get("ITEM_NAME").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("FL_INFECT_NAME") != null) {
									arg100.append(((HashMap) jcbbxxqkList.get(arg99)).get("FL_INFECT_NAME").toString()
											+ "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("SUBMI_AT") != null) {
									arg100.append(
											((HashMap) jcbbxxqkList.get(arg99)).get("SUBMI_AT").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("JYFF") != null) {
									arg100.append(((HashMap) jcbbxxqkList.get(arg99)).get("JYFF").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("BXB") != null) {
									arg100.append(((HashMap) jcbbxxqkList.get(arg99)).get("BXB").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("INFECT_DATE") != null) {
									arg100.append(
											((HashMap) jcbbxxqkList.get(arg99)).get("INFECT_DATE").toString() + "\t");
								} else {
									arg100.append("\t");
								}

								if (((HashMap) jcbbxxqkList.get(arg99)).get("TEST_RESULT") != null) {
									arg100.append(((HashMap) jcbbxxqkList.get(arg99)).get("TEST_RESULT").toString());
								} else {
									arg100.append("\t");
								}

								if (arg99 != jcbbxxqkList.size() - 1) {
									arg100.append("\n");
								}
							}
						}
					}

					try {
						arg101 = new File(fileName + "\\检测标本详细情况.txt");
						sqlData10 = new FileWriter(arg101);
						sqlData10.write(arg100.toString());
						sqlData10.close();
					} catch (IOException arg65) {
						arg65.printStackTrace();
					}
				}

				StringBuffer arg103 = new StringBuffer();
				FileWriter sqlData11;
				File arg104;
				if (bbjcbytxxqkList.size() <= 0) {
					arg104 = new File(fileName + "\\标本检测出的病原体详细情况.txt");

					try {
						sqlData11 = new FileWriter(arg104);
						sqlData11.write(arg103.toString());
						sqlData11.close();
					} catch (IOException arg62) {
						arg62.printStackTrace();
					}
				} else {
					for (int arg102 = 0; arg102 < bbjcbytxxqkList.size(); ++arg102) {
						if (bbjcbytxxqkList.get(arg102) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) bbjcbytxxqkList.get(arg102)).get("ITEM_NAME") != null) {
									arg103.append(
											((HashMap) bbjcbytxxqkList.get(arg102)).get("ITEM_NAME").toString() + "\t");
								} else {
									arg103.append("\t");
								}

								if (((HashMap) bbjcbytxxqkList.get(arg102)).get("SUBMI_AT") != null) {
									arg103.append(
											((HashMap) bbjcbytxxqkList.get(arg102)).get("SUBMI_AT").toString() + "\t");
								} else {
									arg103.append("\t");
								}

								if (((HashMap) bbjcbytxxqkList.get(arg102)).get("PATHO_NAME") != null) {
									arg103.append(((HashMap) bbjcbytxxqkList.get(arg102)).get("PATHO_NAME").toString()
											+ "\t");
								} else {
									arg103.append("\t");
								}

								if (((HashMap) bbjcbytxxqkList.get(arg102)).get("ISYM") != null) {
									arg103.append(((HashMap) bbjcbytxxqkList.get(arg102)).get("ISYM").toString());
								} else {
									arg103.append("\t");
								}

								if (arg102 != bbjcbytxxqkList.size() - 1) {
									arg103.append("\r\n");
								}
							} else {
								if (((HashMap) bbjcbytxxqkList.get(arg102)).get("ITEM_NAME") != null) {
									arg103.append(
											((HashMap) bbjcbytxxqkList.get(arg102)).get("ITEM_NAME").toString() + "\t");
								} else {
									arg103.append("\t");
								}

								if (((HashMap) bbjcbytxxqkList.get(arg102)).get("SUBMI_AT") != null) {
									arg103.append(
											((HashMap) bbjcbytxxqkList.get(arg102)).get("SUBMI_AT").toString() + "\t");
								} else {
									arg103.append("\t");
								}

								if (((HashMap) bbjcbytxxqkList.get(arg102)).get("PATHO_NAME") != null) {
									arg103.append(((HashMap) bbjcbytxxqkList.get(arg102)).get("PATHO_NAME").toString()
											+ "\t");
								} else {
									arg103.append("\t");
								}

								if (((HashMap) bbjcbytxxqkList.get(arg102)).get("ISYM") != null) {
									arg103.append(((HashMap) bbjcbytxxqkList.get(arg102)).get("ISYM").toString());
								} else {
									arg103.append("\t");
								}

								if (arg102 != bbjcbytxxqkList.size() - 1) {
									arg103.append("\n");
								}
							}
						}
					}

					try {
						arg104 = new File(fileName + "\\标本检测出的病原体详细情况.txt");
						sqlData11 = new FileWriter(arg104);
						sqlData11.write(arg103.toString());
						sqlData11.close();
					} catch (IOException arg63) {
						arg63.printStackTrace();
					}
				}

				StringBuffer arg106 = new StringBuffer();
				FileWriter sqlData12;
				File arg107;
				if (ymsyjgList.size() <= 0) {
					arg107 = new File(fileName + "\\药敏试验结果.txt");

					try {
						sqlData12 = new FileWriter(arg107);
						sqlData12.write(arg106.toString());
						sqlData12.close();
					} catch (IOException arg60) {
						arg60.printStackTrace();
					}
				} else {
					for (int arg105 = 0; arg105 < ymsyjgList.size(); ++arg105) {
						if (ymsyjgList.get(arg105) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) ymsyjgList.get(arg105)).get("ITEM_NAME") != null) {
									arg106.append(
											((HashMap) ymsyjgList.get(arg105)).get("ITEM_NAME").toString() + "\t");
								} else {
									arg106.append("\t");
								}

								if (((HashMap) ymsyjgList.get(arg105)).get("SUBMI_AT") != null) {
									arg106.append(((HashMap) ymsyjgList.get(arg105)).get("SUBMI_AT").toString() + "\t");
								} else {
									arg106.append("\t");
								}

								if (((HashMap) ymsyjgList.get(arg105)).get("PATHO_NAME") != null) {
									arg106.append(
											((HashMap) ymsyjgList.get(arg105)).get("PATHO_NAME").toString() + "\t");
								} else {
									arg106.append("\t");
								}

								if (((HashMap) ymsyjgList.get(arg105)).get("ANTI_NAME") != null) {
									arg106.append(
											((HashMap) ymsyjgList.get(arg105)).get("ANTI_NAME").toString() + "\t");
								} else {
									arg106.append("\t");
								}

								if (((HashMap) ymsyjgList.get(arg105)).get("ISYM") != null) {
									arg106.append(((HashMap) ymsyjgList.get(arg105)).get("ISYM").toString());
								} else {
									arg106.append("\t");
								}

								if (arg105 != ymsyjgList.size() - 1) {
									arg106.append("\r\n");
								}
							} else {
								if (((HashMap) ymsyjgList.get(arg105)).get("ITEM_NAME") != null) {
									arg106.append(
											((HashMap) ymsyjgList.get(arg105)).get("ITEM_NAME").toString() + "\t");
								} else {
									arg106.append("\t");
								}

								if (((HashMap) ymsyjgList.get(arg105)).get("SUBMI_AT") != null) {
									arg106.append(((HashMap) ymsyjgList.get(arg105)).get("SUBMI_AT").toString() + "\t");
								} else {
									arg106.append("\t");
								}

								if (((HashMap) ymsyjgList.get(arg105)).get("PATHO_NAME") != null) {
									arg106.append(
											((HashMap) ymsyjgList.get(arg105)).get("PATHO_NAME").toString() + "\t");
								} else {
									arg106.append("\t");
								}

								if (((HashMap) ymsyjgList.get(arg105)).get("ANTI_NAME") != null) {
									arg106.append(
											((HashMap) ymsyjgList.get(arg105)).get("ANTI_NAME").toString() + "\t");
								} else {
									arg106.append("\t");
								}

								if (((HashMap) ymsyjgList.get(arg105)).get("ISYM") != null) {
									arg106.append(((HashMap) ymsyjgList.get(arg105)).get("ISYM").toString());
								} else {
									arg106.append("\t");
								}

								if (arg105 != ymsyjgList.size() - 1) {
									arg106.append("\n");
								}
							}
						}
					}

					try {
						arg107 = new File(fileName + "\\药敏试验结果.txt");
						sqlData12 = new FileWriter(arg107);
						sqlData12.write(arg106.toString());
						sqlData12.close();
					} catch (IOException arg61) {
						arg61.printStackTrace();
					}
				}

				StringBuffer arg109 = new StringBuffer();
				FileWriter sqlData13;
				File arg110;
				if (kjywsyqkList.size() <= 0) {
					arg110 = new File(fileName + "\\抗菌药物使用情况.txt");

					try {
						sqlData13 = new FileWriter(arg110);
						sqlData13.write(arg109.toString());
						sqlData13.close();
					} catch (IOException arg58) {
						arg58.printStackTrace();
					}
				} else {
					for (int arg108 = 0; arg108 < kjywsyqkList.size(); ++arg108) {
						if (kjywsyqkList.get(arg108) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) kjywsyqkList.get(arg108)).get("ORDER_NAME") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("ORDER_NAME").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("ADMIN_ROUTE_NAME") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("ADMIN_ROUTE_NAME").toString()
													+ "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("ORDER_AT") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("ORDER_AT").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("STOP_AT") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("STOP_AT").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("YYMD") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("YYMD").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("ZLYYFS") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("ZLYYFS").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("YFYYZZ") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("YFYYZZ").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("YFYYYG") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("YFYYYG").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("LHYY") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("LHYY").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("WEI") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("WEI").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("SQSJ") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("SQSJ").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("AFTERYYTS") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("AFTERYYTS").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("HLX") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("HLX").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("BHLYY") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("BHLYY").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("LX") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("LX").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("DOSE") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("DOSE").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("FREQUENCY") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("FREQUENCY").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("DOSAGE_UNIT") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("DOSAGE_UNIT").toString());
								} else {
									arg109.append("\t");
								}

								if (arg108 != kjywsyqkList.size() - 1) {
									arg109.append("\r\n");
								}
							} else {
								if (((HashMap) kjywsyqkList.get(arg108)).get("ORDER_NAME") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("ORDER_NAME").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("ADMIN_ROUTE_NAME") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("ADMIN_ROUTE_NAME").toString()
													+ "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("ORDER_AT") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("ORDER_AT").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("STOP_AT") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("STOP_AT").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("YYMD") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("YYMD").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("ZLYYFS") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("ZLYYFS").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("YFYYZZ") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("YFYYZZ").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("YFYYYG") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("YFYYYG").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("LHYY") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("LHYY").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("WEI") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("WEI").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("SQSJ") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("SQSJ").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("AFTERYYTS") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("AFTERYYTS").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("HLX") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("HLX").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("BHLYY") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("BHLYY").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("LX") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("LX").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("DOSE") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("DOSE").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("FREQUENCY") != null) {
									arg109.append(
											((HashMap) kjywsyqkList.get(arg108)).get("FREQUENCY").toString() + "\t");
								} else {
									arg109.append("\t");
								}

								if (((HashMap) kjywsyqkList.get(arg108)).get("DOSAGE_UNIT") != null) {
									arg109.append(((HashMap) kjywsyqkList.get(arg108)).get("DOSAGE_UNIT").toString());
								} else {
									arg109.append("\t");
								}

								if (arg108 != kjywsyqkList.size() - 1) {
									arg109.append("\n");
								}
							}
						}
					}

					try {
						arg110 = new File(fileName + "\\抗菌药物使用情况.txt");
						sqlData13 = new FileWriter(arg110);
						sqlData13.write(arg109.toString());
						sqlData13.close();
					} catch (IOException arg59) {
						arg59.printStackTrace();
					}
				}

				StringBuffer arg112 = new StringBuffer();
				FileWriter sqlData14;
				File arg113;
				if (icubrjbqkList.size() <= 0) {
					arg113 = new File(fileName + "\\ICU病人基本情况.txt");

					try {
						sqlData14 = new FileWriter(arg113);
						sqlData14.write(arg112.toString());
						sqlData14.close();
					} catch (IOException arg56) {
						arg56.printStackTrace();
					}
				} else {
					for (int arg111 = 0; arg111 < icubrjbqkList.size(); ++arg111) {
						if (icubrjbqkList.get(arg111) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) icubrjbqkList.get(arg111)).get("F1") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F1").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F2") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F2").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F3") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F3").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("IN_DATE") != null) {
									arg112.append(
											((HashMap) icubrjbqkList.get(arg111)).get("IN_DATE").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("INDEPTNAME") != null) {
									arg112.append(
											((HashMap) icubrjbqkList.get(arg111)).get("INDEPTNAME").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F6") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F4").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("OUT_DATE") != null) {
									arg112.append(
											((HashMap) icubrjbqkList.get(arg111)).get("OUT_DATE").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("OUTDEPTNAME") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("OUTDEPTNAME").toString());
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F9") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F9").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F10") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F10").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F11") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F11").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F12") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F12").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F13") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F13").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F14") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F14").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F15") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F15").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F16") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F16").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F17") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F17").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F18") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F18").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F19") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F19").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F20") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F20").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F21") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F21").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F22") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F22").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F23") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F23").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F24") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F24").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (arg111 != icubrjbqkList.size() - 1) {
									arg112.append("\r\n");
								}
							} else {
								if (((HashMap) icubrjbqkList.get(arg111)).get("F1") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F1").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F2") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F2").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F3") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F3").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("IN_DATE") != null) {
									arg112.append(
											((HashMap) icubrjbqkList.get(arg111)).get("IN_DATE").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("INDEPTNAME") != null) {
									arg112.append(
											((HashMap) icubrjbqkList.get(arg111)).get("INDEPTNAME").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F6") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F4").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("OUT_DATE") != null) {
									arg112.append(
											((HashMap) icubrjbqkList.get(arg111)).get("OUT_DATE").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("OUTDEPTNAME") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("OUTDEPTNAME").toString());
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F9") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F9").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F10") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F10").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F11") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F11").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F12") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F12").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F13") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F13").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F14") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F14").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F15") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F15").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F16") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F16").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F17") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F17").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F18") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F18").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F19") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F19").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F20") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F20").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F21") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F21").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F22") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F22").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F23") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F23").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (((HashMap) icubrjbqkList.get(arg111)).get("F24") != null) {
									arg112.append(((HashMap) icubrjbqkList.get(arg111)).get("F24").toString() + "\t");
								} else {
									arg112.append("\t");
								}

								if (arg111 != icubrjbqkList.size() - 1) {
									arg112.append("\n");
								}
							}
						}
					}

					try {
						arg113 = new File(fileName + "\\ICU病人基本情况.txt");
						sqlData14 = new FileWriter(arg113);
						sqlData14.write(arg112.toString());
						sqlData14.close();
					} catch (IOException arg57) {
						arg57.printStackTrace();
					}
				}

				StringBuffer arg115 = new StringBuffer();
				FileWriter sqlData15;
				File arg116;
				if (icujbzdList.size() <= 0) {
					arg116 = new File(fileName + "\\ICU的疾病诊断.txt");

					try {
						sqlData15 = new FileWriter(arg116);
						sqlData15.write(arg115.toString());
						sqlData15.close();
					} catch (IOException arg54) {
						arg54.printStackTrace();
					}
				} else {
					for (int arg114 = 0; arg114 < icujbzdList.size(); ++arg114) {
						if (icujbzdList.get(arg114) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) icujbzdList.get(arg114)).get("DIAGNOSISNAME") != null) {
									arg115.append(((HashMap) icujbzdList.get(arg114)).get("DIAGNOSISNAME").toString());
								} else {
									arg115.append("\t");
								}

								if (arg114 != icujbzdList.size() - 1) {
									arg115.append("\r\n");
								}
							} else {
								if (((HashMap) icujbzdList.get(arg114)).get("DIAGNOSISNAME") != null) {
									arg115.append(((HashMap) icujbzdList.get(arg114)).get("DIAGNOSISNAME").toString());
								} else {
									arg115.append("\t");
								}

								if (arg114 != icujbzdList.size() - 1) {
									arg115.append("\n");
								}
							}
						}
					}

					try {
						arg116 = new File(fileName + "\\ICU的疾病诊断.txt");
						sqlData15 = new FileWriter(arg116);
						sqlData15.write(arg115.toString());
						sqlData15.close();
					} catch (IOException arg55) {
						arg55.printStackTrace();
					}
				}

				StringBuffer arg118 = new StringBuffer();
				FileWriter sqlData16;
				File arg119;
				if (zxjmcgqkList.size() <= 0) {
					arg119 = new File(fileName + "\\中心静脉插管情况.txt");

					try {
						sqlData16 = new FileWriter(arg119);
						sqlData16.write(arg118.toString());
						sqlData16.close();
					} catch (IOException arg52) {
						arg52.printStackTrace();
					}
				} else {
					for (int arg117 = 0; arg117 < zxjmcgqkList.size(); ++arg117) {
						if (zxjmcgqkList.get(arg117) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) zxjmcgqkList.get(arg117)).get("DGLX") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("DGLX").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("DGQS") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("DGQS").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("BW") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("BW").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("STARTAT") != null) {
									arg118.append(
											((HashMap) zxjmcgqkList.get(arg117)).get("STARTAT").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("STOPAT") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("STOPAT").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("OPERATOR_NAME") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("OPERATOR_NAME").toString()
											+ "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("DEPTNAME") != null) {
									arg118.append(
											((HashMap) zxjmcgqkList.get(arg117)).get("DEPTNAME").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("ISINFECT") != null) {
									arg118.append(
											((HashMap) zxjmcgqkList.get(arg117)).get("ISINFECT").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("INFECT_DIAGN_NAME") != null) {
									arg118.append(
											((HashMap) zxjmcgqkList.get(arg117)).get("INFECT_DIAGN_NAME").toString()
													+ "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("INFECT_DATE") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("INFECT_DATE").toString());
								} else {
									arg118.append("\t");
								}

								if (arg117 != zxjmcgqkList.size() - 1) {
									arg118.append("\r\n");
								}
							} else {
								if (((HashMap) zxjmcgqkList.get(arg117)).get("DGLX") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("DGLX").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("DGQS") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("DGQS").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("BW") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("BW").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("STARTAT") != null) {
									arg118.append(
											((HashMap) zxjmcgqkList.get(arg117)).get("STARTAT").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("STOPAT") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("STOPAT").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("OPERATOR_NAME") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("OPERATOR_NAME").toString()
											+ "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("DEPTNAME") != null) {
									arg118.append(
											((HashMap) zxjmcgqkList.get(arg117)).get("DEPTNAME").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("ISINFECT") != null) {
									arg118.append(
											((HashMap) zxjmcgqkList.get(arg117)).get("ISINFECT").toString() + "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("INFECT_DIAGN_NAME") != null) {
									arg118.append(
											((HashMap) zxjmcgqkList.get(arg117)).get("INFECT_DIAGN_NAME").toString()
													+ "\t");
								} else {
									arg118.append("\t");
								}

								if (((HashMap) zxjmcgqkList.get(arg117)).get("INFECT_DATE") != null) {
									arg118.append(((HashMap) zxjmcgqkList.get(arg117)).get("INFECT_DATE").toString());
								} else {
									arg118.append("\t");
								}

								if (arg117 != zxjmcgqkList.size() - 1) {
									arg118.append("\n");
								}
							}
						}
					}

					try {
						arg119 = new File(fileName + "\\中心静脉插管情况.txt");
						sqlData16 = new FileWriter(arg119);
						sqlData16.write(arg118.toString());
						sqlData16.close();
					} catch (IOException arg53) {
						arg53.printStackTrace();
					}
				}

				StringBuffer arg121 = new StringBuffer();
				FileWriter file2;
				File arg122;
				if (hxjsyqkList.size() <= 0) {
					arg122 = new File(fileName + "\\呼吸机使用情况.txt");

					try {
						file2 = new FileWriter(arg122);
						file2.write(arg121.toString());
						file2.close();
					} catch (IOException arg50) {
						arg50.printStackTrace();
					}
				} else {
					for (int arg120 = 0; arg120 < hxjsyqkList.size(); ++arg120) {
						if (hxjsyqkList.get(arg120) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) hxjsyqkList.get(arg120)).get("DGLX") != null) {
									arg121.append(((HashMap) hxjsyqkList.get(arg120)).get("DGLX").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("STARTAT") != null) {
									arg121.append(((HashMap) hxjsyqkList.get(arg120)).get("STARTAT").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("STOPAT") != null) {
									arg121.append(((HashMap) hxjsyqkList.get(arg120)).get("STOPAT").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("OPERATOR_NAME") != null) {
									arg121.append(
											((HashMap) hxjsyqkList.get(arg120)).get("OPERATOR_NAME").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("DEPTNAME") != null) {
									arg121.append(
											((HashMap) hxjsyqkList.get(arg120)).get("DEPTNAME").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("ISINFECT") != null) {
									arg121.append(
											((HashMap) hxjsyqkList.get(arg120)).get("ISINFECT").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("INFECT_DIAGN_NAME") != null) {
									arg121.append(
											((HashMap) hxjsyqkList.get(arg120)).get("INFECT_DIAGN_NAME").toString()
													+ "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("INFECT_DATE") != null) {
									arg121.append(((HashMap) hxjsyqkList.get(arg120)).get("INFECT_DATE").toString());
								} else {
									arg121.append("\t");
								}

								if (arg120 != hxjsyqkList.size() - 1) {
									arg121.append("\r\n");
								}
							} else {
								if (((HashMap) hxjsyqkList.get(arg120)).get("DGLX") != null) {
									arg121.append(((HashMap) hxjsyqkList.get(arg120)).get("DGLX").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("STARTAT") != null) {
									arg121.append(((HashMap) hxjsyqkList.get(arg120)).get("STARTAT").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("STOPAT") != null) {
									arg121.append(((HashMap) hxjsyqkList.get(arg120)).get("STOPAT").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("OPERATOR_NAME") != null) {
									arg121.append(
											((HashMap) hxjsyqkList.get(arg120)).get("OPERATOR_NAME").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("DEPTNAME") != null) {
									arg121.append(
											((HashMap) hxjsyqkList.get(arg120)).get("DEPTNAME").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("ISINFECT") != null) {
									arg121.append(
											((HashMap) hxjsyqkList.get(arg120)).get("ISINFECT").toString() + "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("INFECT_DIAGN_NAME") != null) {
									arg121.append(
											((HashMap) hxjsyqkList.get(arg120)).get("INFECT_DIAGN_NAME").toString()
													+ "\t");
								} else {
									arg121.append("\t");
								}

								if (((HashMap) hxjsyqkList.get(arg120)).get("INFECT_DATE") != null) {
									arg121.append(((HashMap) hxjsyqkList.get(arg120)).get("INFECT_DATE").toString());
								} else {
									arg121.append("\t");
								}

								if (arg120 != hxjsyqkList.size() - 1) {
									arg121.append("\n");
								}
							}
						}
					}

					try {
						arg122 = new File(fileName + "\\呼吸机使用情况.txt");
						file2 = new FileWriter(arg122);
						file2.write(arg121.toString());
						file2.close();
					} catch (IOException arg51) {
						arg51.printStackTrace();
					}
				}

				StringBuffer arg124 = new StringBuffer();
				FileWriter e;
				File arg123;
				if (dngsyqkList.size() > 0) {
					for (int arg125 = 0; arg125 < dngsyqkList.size(); ++arg125) {
						if (dngsyqkList.get(arg125) != null) {
							if (os.contains("Windows")) {
								if (((HashMap) dngsyqkList.get(arg125)).get("DGLX") != null) {
									arg124.append(((HashMap) dngsyqkList.get(arg125)).get("DGLX").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("STARTAT") != null) {
									arg124.append(((HashMap) dngsyqkList.get(arg125)).get("STARTAT").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("STOPAT") != null) {
									arg124.append(((HashMap) dngsyqkList.get(arg125)).get("STOPAT").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("OPERATOR_NAME") != null) {
									arg124.append(
											((HashMap) dngsyqkList.get(arg125)).get("OPERATOR_NAME").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("DEPTNAME") != null) {
									arg124.append(
											((HashMap) dngsyqkList.get(arg125)).get("DEPTNAME").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("ISINFECT") != null) {
									arg124.append(
											((HashMap) dngsyqkList.get(arg125)).get("ISINFECT").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("INFECT_DIAGN_NAME") != null) {
									arg124.append(
											((HashMap) dngsyqkList.get(arg125)).get("INFECT_DIAGN_NAME").toString()
													+ "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("INFECT_DATE") != null) {
									arg124.append(((HashMap) dngsyqkList.get(arg125)).get("INFECT_DATE").toString());
								} else {
									arg124.append("\t");
								}

								if (arg125 != dngsyqkList.size() - 1) {
									arg124.append("\r\n");
								}
							} else {
								if (((HashMap) dngsyqkList.get(arg125)).get("DGLX") != null) {
									arg124.append(((HashMap) dngsyqkList.get(arg125)).get("DGLX").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("STARTAT") != null) {
									arg124.append(((HashMap) dngsyqkList.get(arg125)).get("STARTAT").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("STOPAT") != null) {
									arg124.append(((HashMap) dngsyqkList.get(arg125)).get("STOPAT").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("OPERATOR_NAME") != null) {
									arg124.append(
											((HashMap) dngsyqkList.get(arg125)).get("OPERATOR_NAME").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("DEPTNAME") != null) {
									arg124.append(
											((HashMap) dngsyqkList.get(arg125)).get("DEPTNAME").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("ISINFECT") != null) {
									arg124.append(
											((HashMap) dngsyqkList.get(arg125)).get("ISINFECT").toString() + "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("INFECT_DIAGN_NAME") != null) {
									arg124.append(
											((HashMap) dngsyqkList.get(arg125)).get("INFECT_DIAGN_NAME").toString()
													+ "\t");
								} else {
									arg124.append("\t");
								}

								if (((HashMap) dngsyqkList.get(arg125)).get("INFECT_DATE") != null) {
									arg124.append(((HashMap) dngsyqkList.get(arg125)).get("INFECT_DATE").toString());
								} else {
									arg124.append("\t");
								}

								if (arg125 != dngsyqkList.size() - 1) {
									arg124.append("\n");
								}
							}
						}
					}

					try {
						arg123 = new File(fileName + "\\导尿管使用情况.txt");
						e = new FileWriter(arg123);
						e.write(arg124.toString());
						e.close();
					} catch (IOException arg49) {
						arg49.printStackTrace();
					}
				} else {
					arg123 = new File(fileName + "\\导尿管使用情况.txt");

					try {
						e = new FileWriter(arg123);
						e.write(arg124.toString());
						e.close();
					} catch (IOException arg48) {
						arg48.printStackTrace();
					}
				}
			}

			return ids;
		}
	}

	public List<MkZbRecord> findZbxhlList(Date startDate, Date endDate) {
		return null;
	}

	public String b(MkZbRecord zbRecord) {
		return null;
	}

	public void bK(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.up.updateSt003ZBFlag(id, "1", f.getCurDate());
			}
		}

	}
}