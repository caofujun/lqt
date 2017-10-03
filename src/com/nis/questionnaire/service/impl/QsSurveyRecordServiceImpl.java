package com.nis.questionnaire.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.r;
import com.nis.comm.utils.z;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.questionnaire.dao.QsSurveyRecordDao;
import com.nis.questionnaire.entity.QsQuestionnaire;
import com.nis.questionnaire.entity.QsQuestionnaireFeedback;
import com.nis.questionnaire.entity.QsReportTopic;
import com.nis.questionnaire.entity.QsSurveyRecord;
import com.nis.questionnaire.entity.QsSurveyResult;
import com.nis.questionnaire.entity.QsTopic;
import com.nis.questionnaire.entity.QsTopicOption;
import com.nis.questionnaire.service.QsQuestionnaireService;
import com.nis.questionnaire.service.QsSurveyRecordService;
import com.nis.questionnaire.service.QsSurveyResultService;
import com.nis.questionnaire.service.QsTopicOptionService;
import com.nis.questionnaire.service.QsTopicService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QsSurveyRecordServiceImpl implements QsSurveyRecordService {
	@Autowired
	private QsSurveyRecordDao xt;
	@Autowired
	private QsSurveyResultService xo;
	@Autowired
	private QsQuestionnaireService xi;
	@Autowired
	private QsTopicService xj;
	@Autowired
	private QsTopicOptionService xk;
	@Autowired
	private DepService e;
	String xu = "提交问卷时间";
	String catName = "来源";
	String ttitle = "题目详情";
	String inputValue = "其他";
	String optName = "不满意原因";
	String patientName = "患者姓名";
	String patientPhone = "联系方式";
	String[] xv;

	public QsSurveyRecordServiceImpl() {
		this.xv = new String[]{this.xu, this.catName, this.ttitle, this.optName, this.patientName, this.patientPhone,
				this.inputValue};
	}

	public QsSurveyRecord getBySSLID(String sslid) {
		return this.xt.getBySSLID(sslid);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void a(QsSurveyRecord qsSurveyRecord, List<QsSurveyResult> results) {
		boolean isAdd = false;
		int dtNum = 0;
		float sumScore = 0.0F;
		float curScore = 0.0F;
		String fxColor = "green";
		List topicList = this.xj.findByQid(qsSurveyRecord.getQid());
		if (topicList.size() != results.size()) {
			fxColor = "orange";
		}

		HashMap qsrMap = new HashMap();
		QsQuestionnaire qs = this.xi.get(qsSurveyRecord.getQid());
		Iterator arg11 = results.iterator();

		while (true) {
			while (true) {
				QsTopic qt;
				QsTopicOption qto;
				do {
					QsSurveyResult surveyRecord;
					do {
						if (!arg11.hasNext()) {
							qsSurveyRecord.setDtNum(Integer.valueOf(dtNum));
							qsSurveyRecord.setFxColor(fxColor);
							if (sumScore > 0.0F) {
								if ("1".equals(qs.getIsQz())) {
									qsSurveyRecord.setDtScore(Float.valueOf(curScore));
								} else {
									qsSurveyRecord.setDtScore(Float.valueOf(curScore / sumScore * 100.0F));
								}
							} else {
								qsSurveyRecord.setDtScore(Float.valueOf(0.0F));
							}

							if (ab.isEmpty(qsSurveyRecord.getDepType()) && ab.isNotEmpty(qsSurveyRecord.getDepNo())) {
								Dep arg14 = this.e.F(qsSurveyRecord.getUnitId(), qsSurveyRecord.getDepNo());
								if (arg14 != null) {
									qsSurveyRecord.setDepType(arg14.getDeptTypeId());
								}
							}

							QsSurveyRecord arg15 = this.xt.findByzyidAndDate(qsSurveyRecord.getZyid(),
									qsSurveyRecord.getQid(), qsSurveyRecord.getSurveyTime());
							if (arg15 != null) {
								qsSurveyRecord.setRid(arg15.getRid());
							}

							if (ab.isEmpty(qsSurveyRecord.getRid())) {
								qsSurveyRecord.setRid(z.a(bg.nE));
								this.xt.save(qsSurveyRecord);
								isAdd = true;
							} else {
								this.xt.update(qsSurveyRecord);
							}

							this.xo.a(qsSurveyRecord.getRid(), results);
							if (isAdd) {
								this.xi.c(qsSurveyRecord.getQid(), true);
							}

							return;
						}

						surveyRecord = (QsSurveyResult) arg11.next();
						qt = (QsTopic) qsrMap.get(surveyRecord.getTid());
						if (qt == null) {
							++dtNum;
							qt = this.xj.get(surveyRecord.getTid());
							qsrMap.put(surveyRecord.getTid(), qt);
						}
					} while (!"1".equals(qt.getTtype()));

					sumScore += (float) qt.getMaxValue().intValue();
					qto = this.xk.get(surveyRecord.getOptId());
					if (ab.isNotEmpty(qto.getOptName()) && qto.getOptName().equals("未执行")) {
						if (fxColor.equals("green")) {
							fxColor = "orange";
						}
					} else if (ab.isNotEmpty(qto.getOptName()) && qto.getOptName().equals("执行有缺陷")) {
						fxColor = "red";
					} else if (ab.isNotEmpty(qto.getOptName()) && qto.getOptName().indexOf("高危") > -1) {
						fxColor = "red";
					}
				} while (!ab.isNotEmpty(qto.getOptValue()));

				if ("1".equals(qs.getIsQz()) && ab.isNotEmpty(qt.getWeight())) {
					curScore += Float.valueOf(qto.getOptValue().trim()).floatValue()
							* Float.valueOf(qt.getWeight()).floatValue() / 100.0F;
				} else {
					curScore += (float) Integer.valueOf(qto.getOptValue().trim()).intValue();
				}
			}
		}
	}

	public void delete(String rid) {
		this.xt.delete(rid);
	}

	public void save(QsSurveyRecord qsSurveyRecord) {
		qsSurveyRecord.setRid(z.a(bg.nE));
		this.xt.save(qsSurveyRecord);
	}

	public void update(QsSurveyRecord qsSurveyRecord) {
		this.xt.update(qsSurveyRecord);
	}

	public QsSurveyRecord get(String rid) {
		return this.xt.get(rid);
	}

	public MyPage<QsSurveyRecord> a(QsSurveyRecord qsSurveyRecord) {
		int total = this.xt.findQsSurveyRecordCount(qsSurveyRecord);
		List data = null;
		if (total > 0) {
			data = this.xt.findQsSurveyRecord(qsSurveyRecord);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				QsSurveyRecord qsr = (QsSurveyRecord) arg4.next();
				if (ab.isNotEmpty(qsr.getDepNo())) {
					qsr.setDepNoName(this.e.H(qsr.getUnitId(), qsr.getDepNo()));
				}
			}
		}

		return new MyPage(qsSurveyRecord.getPage().intValue(), qsSurveyRecord.getSize().intValue(), total, data);
	}

	public List<QsSurveyRecord> getAll() {
		return this.xt.getAll();
	}

	public List<QsReportTopic> zhiBiaoCount(QsReportTopic qsReportTopic) {
		return this.xt.zhiBiaoCount(qsReportTopic);
	}

	public List<Map<String, Object>> b(QsSurveyRecord qsSurveyRecord) {
		List list = this.xt.sumScoreAnalyzeGroupDep(qsSurveyRecord);
		HashMap countMap = new HashMap();
		LinkedHashMap mydMap = new LinkedHashMap();
		Iterator entry = list.iterator();

		while (entry.hasNext()) {
			Map resultList = (Map) entry.next();
			Long count = r.c(resultList.get("COUNT"));
			Long count1 = this.xj.cA(resultList.get("QID").toString());
			Long map = r.c(resultList.get("SUMSCORE"));
			Double myd = a(count, count1, map);
			String depNo = ab.g(resultList.get("DEPNO"));
			if (myd != null && myd.doubleValue() > 0.0D) {
				mydMap.put(depNo,
						Double.valueOf(mydMap.get(depNo) == null
								? myd.doubleValue()
								: ((Double) mydMap.get(depNo)).doubleValue() + myd.doubleValue()));
				countMap.put(depNo, Integer
						.valueOf(countMap.get(depNo) == null ? 1 : ((Integer) countMap.get(depNo)).intValue() + 1));
			}
		}

		LinkedList resultList1 = new LinkedList();
		Iterator count2 = mydMap.entrySet().iterator();

		while (count2.hasNext()) {
			Entry entry1 = (Entry) count2.next();
			Integer count3 = (Integer) countMap.get(entry1.getKey());
			HashMap map1 = new HashMap();
			map1.put("DEPNO", entry1.getKey());
			map1.put("MYD", r.d(
					Double.valueOf(((Double) entry1.getValue()).doubleValue() / (double) count3.intValue() * 100.0D)));
			resultList1.add(map1);
		}

		return resultList1;
	}

	public String c(QsSurveyRecord qsSurveyRecord) {
		List list = this.xt.sumScore(qsSurveyRecord);
		double sumMyd = 0.0D;
		int calculate = 0;
		Iterator arg6 = list.iterator();

		while (arg6.hasNext()) {
			Map map = (Map) arg6.next();
			Long maxValue = this.xj.cA(map.get("QID").toString());
			Long sumScore = r.c(map.get("SUMSCORE"));
			Long count = r.c(map.get("COUNT"));
			if (count.longValue() > 0L && sumScore.longValue() >= 0L && maxValue.longValue() > 0L) {
				sumMyd += (double) sumScore.longValue() / (double) maxValue.longValue() * (double) count.longValue();
				++calculate;
			}
		}

		if (calculate > 0) {
			return r.d(Double.valueOf(sumMyd / (double) calculate * 100.0D));
		} else {
			return String.valueOf(0);
		}
	}

	public List<Map<String, Object>> d(QsSurveyRecord qsSurveyRecord) {
		List list = this.xt.sumScoreTrendAnalyze(qsSurveyRecord);
		HashMap countMap = new HashMap();
		LinkedHashMap mydMap = new LinkedHashMap();
		Iterator entry = list.iterator();

		while (entry.hasNext()) {
			Map resultList = (Map) entry.next();
			Long count = r.c(resultList.get("COUNT"));
			Long count1 = this.xj.cA(resultList.get("QID").toString());
			Long map = r.c(resultList.get("SUMSCORE"));
			Double myd = a(count, count1, map);
			String day = ab.g(resultList.get("DAY"));
			if (myd != null && myd.doubleValue() > 0.0D) {
				mydMap.put(day,
						Double.valueOf(mydMap.get(day) == null
								? myd.doubleValue()
								: ((Double) mydMap.get(day)).doubleValue() + myd.doubleValue()));
				countMap.put(day,
						Integer.valueOf(countMap.get(day) == null ? 1 : ((Integer) countMap.get(day)).intValue() + 1));
			}
		}

		LinkedList resultList1 = new LinkedList();
		Iterator count2 = mydMap.entrySet().iterator();

		while (count2.hasNext()) {
			Entry entry1 = (Entry) count2.next();
			Integer count3 = (Integer) countMap.get(entry1.getKey());
			HashMap map1 = new HashMap();
			map1.put("DAY", entry1.getKey());
			map1.put("MYD", r.d(
					Double.valueOf(((Double) entry1.getValue()).doubleValue() / (double) count3.intValue() * 100.0D)));
			resultList1.add(map1);
		}

		return resultList1;
	}

	private static Double a(Long count, Long maxValue, Long sumScore) {
		return count.longValue() > 0L && sumScore.longValue() >= 0L && maxValue.longValue() > 0L
				? Double.valueOf(
						(double) sumScore.longValue() / (double) maxValue.longValue() * (double) count.longValue())
				: null;
	}

	public MyPage<QsQuestionnaireFeedback> e(QsSurveyRecord qsSurveyRecord) {
		int total = this.xt.findQsQuestionnaireFeedbackCount(qsSurveyRecord);
		List data = null;
		if (total > 0) {
			data = this.xt.findQsQuestionnaireFeedback(qsSurveyRecord);
		}

		return new MyPage(qsSurveyRecord.getPage().intValue(), qsSurveyRecord.getSize().intValue(), total, data);
	}

	public List<QsQuestionnaireFeedback> f(QsSurveyRecord qsSurveyRecord) {
		return this.xt.findQsQuestionnaireFeedbackList(qsSurveyRecord);
	}

	public HSSFWorkbook D(List<QsQuestionnaireFeedback> dataList) {
		HSSFWorkbook workbook = null;

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = this.c(workbook);
			if (dataList != null && dataList.size() > 0) {
				HSSFRow row = e.createRow(0);

				int k;
				for (k = 0; k < this.xv.length; ++k) {
					this.a(row, k, style, 1, this.xv[k]);
				}

				for (k = 0; k < dataList.size(); ++k) {
					HSSFRow row1 = e.createRow(k + 1);

					for (int j = 0; j < this.xv.length; ++j) {
						this.a(row1, j, style, 1, this.a(this.xv[j], (QsQuestionnaireFeedback) dataList.get(k)));
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

	private String a(String head, QsQuestionnaireFeedback enty) {
		return this.xu.equals(head)
				? f.c(enty.getSurveyTime(), "yyyy-MM-dd HH:mm:ss")
				: (this.catName.equals(head)
						? enty.getCatName()
						: (this.ttitle.equals(head)
								? enty.getTtitle()
								: (this.inputValue.equals(head)
										? enty.getInputValue()
										: (this.patientName.equals(head)
												? enty.getPatientName()
												: (this.patientPhone.equals(head)
														? enty.getPatientPhone()
														: (this.optName.equals(head) ? enty.getOptName() : ""))))));
	}

	public QsSurveyRecord getByPidQid(String patientId, String qid) {
		return this.xt.getByPidQid(patientId, qid);
	}

	public List<QsSurveyRecord> findByVisitId(String visitId) {
		return this.xt.findByVisitId(visitId);
	}

	public List<QsSurveyRecord> findByzyId(String zyid, String startDate) {
		return this.xt.findByzyId(zyid, startDate);
	}

	public List<QsSurveyRecord> findByzyidAndQid(String zyid, String qid) {
		return this.xt.findByzyidAndQid(zyid, qid);
	}

	public QsSurveyRecord findByzyidAndDate(String zyid, String qid, Date surveyTime) {
		return this.xt.findByzyidAndDate(zyid, qid, surveyTime);
	}

	public int findByQid(String qid) {
		return this.xt.findByQid(qid);
	}
}