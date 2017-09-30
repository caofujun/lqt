package com.nis.questionnaire.service.impl;

import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.questionnaire.dao.QsAccountQuesDao;
import com.nis.questionnaire.entity.QsAccountQues;
import com.nis.questionnaire.service.QsAccountQuesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QsAccountQuesServiceImpl implements QsAccountQuesService {
	@Autowired
	private QsAccountQuesDao xp;

	public void save(QsAccountQues qsAccountQues) {
		qsAccountQues.setId(z.a(bg.ny));
		this.xp.save(qsAccountQues);
	}

	public void delete(String id) {
		this.xp.delete(id);
	}

	public void update(QsAccountQues qsAccountQues) {
		this.xp.update(qsAccountQues);
	}

	public QsAccountQues get(String id) {
		return this.xp.get(id);
	}

	public List<QsAccountQues> getAll() {
		return this.xp.getAll();
	}

	public QsAccountQues getByQidAndUserId(String qid, String userId) {
		return this.xp.getByQidAndUserId(qid, userId);
	}
}