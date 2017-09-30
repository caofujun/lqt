package com.nis.questionnaire.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.access.service.AcAccountService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.q;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.organization.service.DepService;
import com.nis.questionnaire.dao.QsQuestionnaireDao;
import com.nis.questionnaire.dao.QsTopicDao;
import com.nis.questionnaire.dao.QsTopicOptionDao;
import com.nis.questionnaire.entity.QsQuestionnaire;
import com.nis.questionnaire.entity.QsTopic;
import com.nis.questionnaire.entity.QsTopicOption;
import com.nis.questionnaire.service.QsQuestionnaireService;
import com.nis.questionnaire.service.QsTopicOptionService;
import com.nis.questionnaire.service.QsTopicService;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QsQuestionnaireServiceImpl implements QsQuestionnaireService {
	@Autowired
	private QsQuestionnaireDao xq;
	@Autowired
	private QsTopicOptionDao xr;
	@Autowired
	private QsTopicDao xs;
	@Autowired
	private QsTopicService xj;
	@Autowired
	private QsTopicOptionService xk;
	@Autowired
	private SysDictService p;
	@Autowired
	private DepService e;
	@Autowired
	private AcAccountService d;

	public void b(QsQuestionnaire qsQuestionnaire) {
		if (ab.isEmpty(qsQuestionnaire.getCreateTime())) {
			qsQuestionnaire.setCreateTime(f.f(new Date()));
		}

		if (ab.isEmpty(qsQuestionnaire.getIsMod())) {
			qsQuestionnaire.setIsMod("0");
		}

		if (ab.isEmpty(qsQuestionnaire.getIsQz())) {
			qsQuestionnaire.setIsQz("0");
		}

		if (ab.isEmpty(qsQuestionnaire.getPublish())) {
			qsQuestionnaire.setPublish("0");
		}

		if (ab.isNotEmpty(qsQuestionnaire.getCatId())) {
			String catName = this.p.k(q.gD.getValue(), qsQuestionnaire.getCatId().toString(),
					qsQuestionnaire.getUnitId());
			qsQuestionnaire.setCatName(catName);
		}

		if (ab.isEmpty(qsQuestionnaire.getQid())) {
			qsQuestionnaire.setQid(z.a(bg.nB));
			this.xq.save(qsQuestionnaire);
		} else {
			this.xq.update(qsQuestionnaire);
		}

		this.updateTmCount(qsQuestionnaire.getQid());
	}

	public void delete(String qid) {
		this.xq.delete(qid);
	}

	@Transactional
	public void deleteCache() {
		this.xr.deleteCache();
		this.xs.deleteCache();
		this.xq.deleteCache();
	}

	public void updateTmCount(String qid) {
		this.xq.updateTmCount(qid);
	}

	public QsQuestionnaire get(String qid) {
		return this.xq.get(qid);
	}

	public MyPage<QsQuestionnaire> a(QsQuestionnaire qsQuestionnaire) {
		int total = this.xq.findQsQuestionnaireCount(qsQuestionnaire);
		List data = null;
		if (total > 0) {
			data = this.xq.findQsQuestionnaire(qsQuestionnaire);

			QsQuestionnaire ques;
			for (Iterator arg4 = data.iterator(); arg4.hasNext(); ques
					.setCreateUserName(this.d.getName(ques.getCreateUser()))) {
				ques = (QsQuestionnaire) arg4.next();
				ques.setStatusName(this.p.k(q.gE.getValue(), ques.getStatus().toString(), qsQuestionnaire.getUnitId()));
				if (ab.isNotEmpty(ques.getDepNo())) {
					ques.setDepNoName(this.e.H(qsQuestionnaire.getUnitId(), ques.getDepNo()));
				}
			}
		}

		return new MyPage(qsQuestionnaire.getPage().intValue(), qsQuestionnaire.getSize().intValue(), total, data);
	}

	public List<QsQuestionnaire> findQsQuestionnaireAll(QsQuestionnaire qsQuestionnaire) {
		return this.xq.findQsQuestionnaireAll(qsQuestionnaire);
	}

	public List<QsQuestionnaire> getAll() {
		return this.xq.getAll();
	}

	public List<QsQuestionnaire> getFeedback() {
		return this.xq.getFeedback();
	}

	public List<QsQuestionnaire> findByIsMod(QsQuestionnaire qsQuestionnaire) {
		return this.xq.findByIsMod(qsQuestionnaire);
	}

	public QsQuestionnaire a(String qid, AcAccount account, String isCache) {
		QsQuestionnaire qsQuestionnaire = this.get(qid);
		qsQuestionnaire.setQid((String) null);
		qsQuestionnaire.setUpdTime(f.f(new Date()));
		qsQuestionnaire.setUpdUser(account.getUsername());
		qsQuestionnaire.setCreateUser(account.getUsername());
		qsQuestionnaire.setUnitId(account.getUnitId());
		qsQuestionnaire.setDepNo(account.getDepNo());
		qsQuestionnaire.setIsMod("0");
		qsQuestionnaire.setIsQz("0");
		qsQuestionnaire.setExt1(isCache);
		this.b(qsQuestionnaire);
		List wqList = this.xj.N(qid, (String) null);
		if (wqList != null) {
			Iterator arg6 = wqList.iterator();

			while (arg6.hasNext()) {
				QsTopic qt = (QsTopic) arg6.next();
				String orgTid = qt.getTid();
				qt.setTid((String) null);
				qt.setQid(qsQuestionnaire.getQid());
				qt.setUpdTime(qsQuestionnaire.getUpdTime());
				qt.setUpdUser(qsQuestionnaire.getUpdUser());
				qt.setUnitId(qsQuestionnaire.getUnitId());
				this.xj.save(qt);
				List wqoList = this.xk.findByTid(orgTid);
				Iterator arg10 = wqoList.iterator();

				while (arg10.hasNext()) {
					QsTopicOption wqo = (QsTopicOption) arg10.next();
					wqo.setTid(qt.getTid());
					wqo.setUnitId(qsQuestionnaire.getUnitId());
					this.xk.save(wqo);
				}
			}
		}

		return qsQuestionnaire;
	}

	public void updateStatus(String qid, Integer status) {
		String startTime = f.formatDate(new Date());
		this.xq.updateStatus(qid, status, startTime);
	}

	public void updatePublish(String qid, Integer publish) {
		this.xq.updatePublish(qid, publish);
	}

	public List<QsQuestionnaire> findCanEnter(QsQuestionnaire qsQuestionnaire) {
		String endTime = f.formatDate(new Date());
		qsQuestionnaire.setEndTime(endTime);
		return this.xq.findCanEnter(qsQuestionnaire);
	}

	public void c(String qid, boolean isAdd) {
		this.xq.updateAnswerCount(qid, isAdd ? "+" : "-");
	}

	public void updateAnswerCounts(String qid, Long answerCount) {
		this.xq.updateAnswerCounts(qid, answerCount);
	}

	public List<QsQuestionnaire> findCanEnterToFollow(QsQuestionnaire qsQuestionnaire) {
		String endTime = f.formatDate(new Date());
		qsQuestionnaire.setEndTime(endTime);
		return this.xq.findCanEnterToFollow(qsQuestionnaire);
	}

	public MyPage<QsQuestionnaire> c(QsQuestionnaire qsQuestionnaire) {
		int total = this.xq.pagefindCanEnterCount(qsQuestionnaire);
		List data = null;
		if (total > 0) {
			data = this.xq.pagefindCanEnter(qsQuestionnaire);
			String endTime = f.formatDate(new Date());
			qsQuestionnaire.setEndTime(endTime);
		}

		return new MyPage(qsQuestionnaire.getPage().intValue(), qsQuestionnaire.getSize().intValue(), total, data);
	}

	public Map<String, Object> cz(String qid) {
		HashMap map = new HashMap();
		QsQuestionnaire ques = this.get(qid);
		map.put("ques", ques);
		List topics = this.xj.N(qid, (String) null);
		map.put("topics", topics);
		return map;
	}

	public QsQuestionnaire getbyCatId(String catId) {
		return this.xq.getbyCatId(catId);
	}
}