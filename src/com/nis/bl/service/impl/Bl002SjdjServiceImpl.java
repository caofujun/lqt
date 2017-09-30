package com.nis.bl.service.impl;

import com.nis.bl.dao.Bl002SjdjDao;
import com.nis.bl.entity.Bl002Sjdj;
import com.nis.bl.service.Bl002SjdjService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.f;
import com.nis.comm.utils.g;
import com.nis.comm.utils.r;
import com.nis.dict.service.SysDictService;
import com.nis.zg.entity.Zg002Byks;
import com.nis.zg.service.Zg002ByksService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

@Component
public class Bl002SjdjServiceImpl implements Bl002SjdjService {
	@Autowired
	private Bl002SjdjDao cY;
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg002ByksService cZ;

	public void save(Bl002Sjdj bl002Sjdj) {
		Bl002Sjdj sjdj = this.cY.getMaxBlId();
		String blId = "ZYBL000001";
		if (sjdj != null) {
			blId = sjdj.getBlId();
			Long id = Long.valueOf(Long.parseLong(blId.replace("ZYBL", "9999")) + 1L);
			blId = ("" + id).replace("9999", "ZYBL");
		}

		bl002Sjdj.setBlId(blId);
		this.cY.save(bl002Sjdj);
	}

	public void delete(String blId) {
		this.cY.delete(blId);
	}

	public void update(Bl002Sjdj bl002Sjdj) {
		this.cY.update(bl002Sjdj);
	}

	public Bl002Sjdj get(String blId) {
		return this.cY.get(blId);
	}

	public MyPage<Bl002Sjdj> a(Bl002Sjdj bl002Sjdj) {
		int total = this.cY.findBl002SjdjCount(bl002Sjdj);
		List data = null;
		if (total > 0) {
			data = this.cY.findBl002Sjdj(bl002Sjdj);
			data = this.a(data);
		}

		return new MyPage(bl002Sjdj.getPage().intValue(), bl002Sjdj.getSize().intValue(), total, data);
	}

	private List<Bl002Sjdj> a(List<Bl002Sjdj> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			Bl002Sjdj bl002Sjdj = (Bl002Sjdj) arg2.next();
			if (StringUtils.isNotBlank(bl002Sjdj.getBlJl())) {
				bl002Sjdj.setShowBlJl(this.p.k("bl_result", bl002Sjdj.getBlJl().trim(), (String) null));
			}

			if (StringUtils.isNotBlank(bl002Sjdj.getSjState())) {
				bl002Sjdj.setShowSjState(this.p.k("bl_status", bl002Sjdj.getSjState().trim(), (String) null));
			}

			if (StringUtils.isNotBlank(bl002Sjdj.getDjDept())) {
				Zg002Byks zg002Byks = this.cZ.getByDeptId(bl002Sjdj.getDjDept());
				if (zg002Byks != null) {
					bl002Sjdj.setShowDjDept(zg002Byks.getDeptName());
				}
			}
		}

		return data;
	}

	public List<Bl002Sjdj> getAll() {
		return this.cY.getAll();
	}

	public List<Map<String, Object>> findzyblryksfb(String startDate, String endDate) {
		return this.cY.findzyblryksfb(startDate, endDate);
	}

	public List<Map<String, Object>> findzyblfsgwtj(String startDate, String endDate) {
		return this.cY.findzyblfsgwtj(startDate, endDate);
	}

	@SqlLog(p = "院感端首页--职业暴露人员构成")
	public List<Map<String, Object>> findMainExposure(Date startDate, Date endDate) {
		List list = this.cY.findMainExposure(startDate, endDate);
		boolean others = false;
		Map otherMap = null;
		Map othersMap = null;
		int temp = 0;
		if (list.size() == 1) {
			list.clear();
		}

		Iterator arg8 = list.iterator();

		while (arg8.hasNext()) {
			Map map = (Map) arg8.next();
			if ("其他".equals(map.get("name"))) {
				otherMap = map;
			} else if (!"总计".equals(map.get("name"))) {
				temp += r.d(map.get("value"));
			} else {
				int others1 = r.d(map.get("value")) - temp;
				if (others1 != 0) {
					map.put("name", "其他");
					map.put("value", Integer.valueOf(others1));
				} else {
					othersMap = map;
				}
			}
		}

		if (otherMap != null) {
			list.remove(otherMap);
		}

		if (othersMap != null) {
			list.remove(othersMap);
		}

		return list;
	}

	public Result<String> a(HttpServletRequest request, String fileName, String file) {
		Result r = new Result();
		if (file != null && file.length() != 0) {
			int startIndex = file.indexOf("base64,");
			file = file.substring(startIndex + 7);
			String rootPath = request.getSession().getServletContext().getRealPath("");
			String beforRoot = rootPath.substring(0, rootPath.lastIndexOf(File.separator));
			String path = beforRoot + "/download/zyblImages/";
			String filepath = beforRoot + "/download/zyblImages/" + fileName;
			System.err.println(" savePath : " + filepath);
			File p = new File(path);
			if (!p.exists()) {
				p.mkdirs();
			}

			File x = new File(filepath);
			if (x.exists()) {
				x.delete();
			} else {
				try {
					x.createNewFile();
				} catch (IOException arg15) {
					arg15.printStackTrace();
				}
			}

			BASE64Decoder decoder = new BASE64Decoder();

			try {
				byte[] e = decoder.decodeBuffer(file);

				for (int out = 0; out < e.length; ++out) {
					if (e[out] < 0) {
						e[out] = (byte) (e[out] + 256);
					}
				}

				FileOutputStream arg16 = new FileOutputStream(x);
				arg16.write(e);
				arg16.flush();
				arg16.close();
				r.setMsg("上传成功！");
				r.setResult("success");
				this.p.updateLastTimebyCode("data_init", "ZYBLCZLC", f.getCurDate());
				return r;
			} catch (Exception arg14) {
				arg14.printStackTrace();
				r.setMsg("上传失败！错误信息：" + arg14.getMessage());
				r.setResult("error");
				return r;
			}
		} else {
			r.setMsg("上传失败！未获取到上传图片文件！");
			r.setResult("error");
			return r;
		}
	}

	public HSSFWorkbook b(Bl002Sjdj bl002Sjdj) {
		HSSFWorkbook workbook = null;
		List bl002SjdjList = this.cY.findBl002Sjdj(bl002Sjdj);
		bl002SjdjList = this.a(bl002SjdjList);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg11 = e.createRow(0);
			g.a(arg11, 0, style, 1, "暴露者姓名");
			g.a(arg11, 1, style, 1, "暴露科室");
			g.a(arg11, 2, style, 1, "发生地点");
			g.a(arg11, 3, style, 1, "暴露时间");
			g.a(arg11, 4, style, 1, "危险程度");
			g.a(arg11, 5, style, 1, "暴露方式");
			g.a(arg11, 6, style, 1, "暴露级别");
			g.a(arg11, 7, style, 1, "登记人");
			g.a(arg11, 8, style, 1, "登记时间");
			g.a(arg11, 9, style, 1, "状态");
			Integer index = Integer.valueOf(1);
			if (bl002SjdjList != null && bl002SjdjList.size() > 0) {
				for (Iterator arg8 = bl002SjdjList.iterator(); arg8
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					Bl002Sjdj bl002SjdjTemp = (Bl002Sjdj) arg8.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, bl002SjdjTemp.getDjName());
					g.a(row, 1, style, 1, bl002SjdjTemp.getShowDjDept());
					g.a(row, 2, style, 1, bl002SjdjTemp.getEnterAddName());
					g.a(row, 3, style, 1, f.formatDate(bl002SjdjTemp.getEnterTime()));
					g.a(row, 4, style, 1, bl002SjdjTemp.getShowBlSgrade());
					g.a(row, 5, style, 1, bl002SjdjTemp.getWtjg34());
					g.a(row, 6, style, 1, bl002SjdjTemp.getBlGrade());
					g.a(row, 7, style, 1, bl002SjdjTemp.getDjMen());
					g.a(row, 8, style, 1, f.formatDate(bl002SjdjTemp.getDjTime()));
					g.a(row, 9, style, 1, bl002SjdjTemp.getShowSjState());
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg10) {
			arg10.printStackTrace();
		}

		return workbook;
	}

	public List<Bl002Sjdj> findFcMessage(String days) {
		return this.cY.findFcMessage(days);
	}

	public List<Bl002Sjdj> findFcMessageCount(String days) {
		return this.cY.findFcMessageCount(days);
	}
}