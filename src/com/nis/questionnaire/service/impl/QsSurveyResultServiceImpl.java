package com.nis.questionnaire.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.questionnaire.dao.QsSurveyResultDao;
import com.nis.questionnaire.entity.QsReportTopic;
import com.nis.questionnaire.entity.QsSurveyResult;
import com.nis.questionnaire.service.QsSurveyResultService;
import com.nis.questionnaire.service.QsTopicService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QsSurveyResultServiceImpl implements QsSurveyResultService {
	@Autowired
	private QsSurveyResultDao xw;
	@Autowired
	private QsTopicService xj;

	public void a(String rid, List<QsSurveyResult> results) {
		Iterator arg3 = results.iterator();

		QsSurveyResult qsr;
		while (arg3.hasNext()) {
			qsr = (QsSurveyResult) arg3.next();
			QsSurveyResult sr = this.xw.getbyRidAndTid(rid, qsr.getTid());
			if (sr != null) {
				qsr.setFbStatus(sr.getFbStatus());
			} else {
				qsr.setFbStatus("0");
			}
		}

		this.xw.deleteRid(rid);
		arg3 = results.iterator();

		while (arg3.hasNext()) {
			qsr = (QsSurveyResult) arg3.next();
			qsr.setRid(rid);
			this.save(qsr);
		}

	}

	private void save(QsSurveyResult qsr) {
		qsr.setId(z.a(bg.nF));
		this.xw.save(qsr);
	}

	public void update(QsSurveyResult qsSurveyResult) {
		this.xw.update(qsSurveyResult);
	}

	public QsSurveyResult get(String id) {
		return this.xw.get(id);
	}

	public MyPage<QsSurveyResult> a(QsSurveyResult qsSurveyResult) {
		int total = this.xw.findQsSurveyResultCount(qsSurveyResult);
		List data = null;
		if (total > 0) {
			data = this.xw.findQsSurveyResult(qsSurveyResult);
		}

		return new MyPage(qsSurveyResult.getPage().intValue(), qsSurveyResult.getSize().intValue(), total, data);
	}

	public List<QsSurveyResult> getAll() {
		return this.xw.getAll();
	}

	public List<QsSurveyResult> findByRid(String rid) {
		return this.xw.findByRid(rid);
	}

	public List<QsReportTopic> findQsReportDefault(QsReportTopic qsReportTopic) {
		return this.xw.findQsReportDefault(qsReportTopic);
	}

	public List<QsReportTopic> findQsReportClassify(QsReportTopic qsReportTopic) {
		return this.xw.findQsReportClassify(qsReportTopic);
	}

	public QsReportTopic findQsReportClassifyCount(QsReportTopic qsReportTopic) {
		return this.xw.findQsReportClassifyCount(qsReportTopic);
	}
}