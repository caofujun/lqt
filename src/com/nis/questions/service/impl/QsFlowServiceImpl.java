package com.nis.questions.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.questionnaire.entity.QsFlow;
import com.nis.questionnaire.service.QsFlowService;
import com.nis.questions.dao.QsFlowDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QsFlowServiceImpl implements QsFlowService {
	@Autowired
	private QsFlowDao xA;

	public void save(QsFlow qsFlow) {
		this.xA.save(qsFlow);
	}

	public void delete(String fid) {
		this.xA.delete(fid);
	}

	public void update(QsFlow qsFlow) {
		this.xA.update(qsFlow);
	}

	public QsFlow get(String fid) {
		return this.xA.get(fid);
	}

	public MyPage<QsFlow> a(QsFlow qsFlow) {
		int total = this.xA.findQsFlowCount(qsFlow);
		List data = null;
		if (total > 0) {
			data = this.xA.findQsFlow(qsFlow);
		}

		return new MyPage(qsFlow.getPage().intValue(), qsFlow.getSize().intValue(), total, data);
	}

	public List<QsFlow> getAll() {
		return null;
	}

	public List<QsFlow> b(QsFlow qsFlow) {
		return this.xA.findQsFlow(qsFlow);
	}

	public List<QsFlow> findQsFlowAll(QsFlow qsFlow) {
		return this.xA.findQsFlowAll(qsFlow);
	}
}