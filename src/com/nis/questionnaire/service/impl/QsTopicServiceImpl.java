package com.nis.questionnaire.service.impl;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.bm;
import com.nis.comm.enums.n;
import com.nis.comm.enums.q;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.questionnaire.dao.QsTopicDao;
import com.nis.questionnaire.entity.QsSurveyResult;
import com.nis.questionnaire.entity.QsTopic;
import com.nis.questionnaire.entity.QsTopicOption;
import com.nis.questionnaire.service.QsSurveyResultService;
import com.nis.questionnaire.service.QsTopicOptionService;
import com.nis.questionnaire.service.QsTopicService;
import com.nis.questions.entity.QsRepoOptions;
import com.nis.questions.entity.QsRepoTopic;
import com.nis.questions.service.QsRepoOptionsService;
import com.nis.questions.service.QsRepoTopicService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QsTopicServiceImpl implements QsTopicService {
	@Autowired
	private QsTopicDao xs;
	@Autowired
	private QsTopicOptionService xk;
	@Autowired
	private QsRepoTopicService xx;
	@Autowired
	private QsRepoOptionsService xy;
	@Autowired
	private QsSurveyResultService xo;
	@Autowired
	private SysDictService p;

	public void a(QsTopic qsTopic, String[] repoTids) {
		String[] arg5 = repoTids;
		int arg4 = repoTids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String rtid = arg5[arg3];
			if (rtid != null) {
				QsRepoTopic qrt = this.xx.get(rtid);
				qsTopic.setTid((String) null);
				qsTopic.setTitle(qrt.getTitle());
				qsTopic.setTtype(qrt.getTtype());
				qsTopic.setAllowNull(qrt.getAllowNull());
				qsTopic.setNote(qrt.getNote());
				qsTopic.setCatId(qrt.getCatId());
				qsTopic.setCatName(qrt.getCatName());
				qsTopic.setUnitId(qrt.getUnitId());
				qsTopic.setDepNo(qrt.getDepNo());
				qsTopic.setDocNo(qrt.getDocNo());
				qsTopic.setAttr(qrt.getAttr());
				qsTopic.setMaxValue(qrt.getMaxValue());
				qsTopic.setFeedType(qrt.getFeedType());
				this.save(qsTopic);
				List qroList = this.xy.findByRtid(qrt.getTid());
				ArrayList qtoList = new ArrayList();
				Iterator arg10 = qroList.iterator();

				while (arg10.hasNext()) {
					QsRepoOptions qro = (QsRepoOptions) arg10.next();
					QsTopicOption qto = new QsTopicOption();
					qto.setOptName(qro.getOptName());
					qto.setAllowInput(qro.getAllowInput());
					qto.setOptValue(qro.getOptValue());
					qto.setUnitId(qsTopic.getUnitId());
					qto.setSortNo(qro.getSortNo());
					qto.setTid(qsTopic.getTid());
					qtoList.add(qto);
				}

				this.xk.a(qsTopic.getTid(), qtoList);
			}
		}

	}

	public void save(QsTopic qsTopic) {
		if (qsTopic.getTid() == null) {
			qsTopic.setTid(z.a(bg.nC));
		}

		this.xs.save(qsTopic);
	}

	public void delete(String tid) {
		this.xs.delete(tid);
	}

	public void a(QsTopic qsTopic, List<QsTopicOption> options) {
		if (qsTopic.getCatId() != null) {
			String maxValue = this.p.k(q.gC.getValue(), qsTopic.getCatId(), qsTopic.getUnitId());
			qsTopic.setCatName(maxValue);
		}

		if ("1".equals(qsTopic.getTtype())) {
			long maxValue1 = 0L;
			Iterator arg5 = options.iterator();

			while (arg5.hasNext()) {
				QsTopicOption wqo = (QsTopicOption) arg5.next();
				if (ab.isNotEmpty(wqo.getOptValue()) && Long.valueOf(wqo.getOptValue()).longValue() > maxValue1) {
					maxValue1 = Long.valueOf(wqo.getOptValue()).longValue();
				}
			}

			qsTopic.setMaxValue(Long.valueOf(maxValue1));
		}

		this.xs.update(qsTopic);
		this.xk.a(qsTopic.getTid(), options);
	}

	public QsTopic get(String tid) {
		return this.xs.get(tid);
	}

	public MyPage<QsTopic> a(QsTopic qsTopic) {
		int total = this.xs.findQsTopicCount(qsTopic);
		List data = null;
		if (total > 0) {
			data = this.xs.findQsTopic(qsTopic);
		}

		return new MyPage(qsTopic.getPage().intValue(), qsTopic.getSize().intValue(), total, data);
	}

	public List<QsTopic> getAll() {
		return this.xs.getAll();
	}

	public List<QsTopic> N(String qid, String rid) {
		List list = this.xs.findByQid(qid);
		if (rid != null) {
			List qt = this.xo.findByRid(rid);
			Iterator arg5 = list.iterator();

			while (arg5.hasNext()) {
				QsTopic qt1 = (QsTopic) arg5.next();
				ArrayList resultQts = new ArrayList();
				Iterator arg8 = qt.iterator();

				while (arg8.hasNext()) {
					QsSurveyResult qsr = (QsSurveyResult) arg8.next();
					if (qsr.getTid().equals(qt1.getTid())) {
						resultQts.add(qsr);
					}
				}

				qt1.setAnswers(resultQts);
				qt1.setOptions(this.xk.findByTid(qt1.getTid()));
			}
		} else {
			Iterator qt3 = list.iterator();

			while (qt3.hasNext()) {
				QsTopic qt2 = (QsTopic) qt3.next();
				qt2.setOptions(this.xk.findByTid(qt2.getTid()));
			}
		}

		return list;
	}

	public void z(List<QsTopic> qts) {
		Iterator arg2 = qts.iterator();

		while (arg2.hasNext()) {
			QsTopic qt = (QsTopic) arg2.next();
			this.xs.updateSortNo(qt.getTid(), qt.getSortNo());
		}

	}

	public Long cA(String qid) {
		List list = this.xs.findByQid(qid);
		Iterator arg3 = list.iterator();
		if (arg3.hasNext()) {
			QsTopic topic = (QsTopic) arg3.next();
			return topic.getMaxValue();
		} else {
			return null;
		}
	}

	public List<QsTopic> findByQid(String qid) {
		return this.xs.findByQid(qid);
	}

	public List<QsTopic> findTopicInfo(QsTopic qsTopic) {
		return this.xs.findTopicInfo(qsTopic);
	}

	public void updateScore(QsTopic qsTopic) {
		this.xs.updateScore(qsTopic);
	}

	public void a(QsTopic qsTopic, List<QsTopicOption> topicOptions, AcAccount account) {
		ArrayList options = new ArrayList();
		Iterator sdf = topicOptions.iterator();

		while (sdf.hasNext()) {
			QsTopicOption qsRepoTopic = (QsTopicOption) sdf.next();
			options.add(new QsRepoOptions(qsRepoTopic.getOptName(), qsRepoTopic.getOptValue(),
					qsRepoTopic.getAllowInput(), qsRepoTopic.getUnitId(), qsRepoTopic.getSortNo()));
		}

		QsRepoTopic qsRepoTopic1 = new QsRepoTopic();
		qsRepoTopic1.setTitle(qsTopic.getTitle());
		qsRepoTopic1.setTtype(qsTopic.getTtype());
		qsRepoTopic1.setAllowNull(qsTopic.getAllowNull());
		qsRepoTopic1.setNote(qsTopic.getNote());
		qsRepoTopic1.setCatId(qsTopic.getCatId());
		qsRepoTopic1.setCatName(qsTopic.getCatName());
		qsRepoTopic1.setUnitId(qsTopic.getUnitId());
		qsRepoTopic1.setDepNo(qsTopic.getDepNo());
		qsRepoTopic1.setDocNo(qsTopic.getDocNo());
		qsRepoTopic1.setAttr(qsTopic.getAttr());
		qsRepoTopic1.setMaxValue(qsTopic.getMaxValue());
		qsRepoTopic1.setFeedType(qsTopic.getFeedType());
		qsRepoTopic1.setUnitId(qsTopic.getUnitId());
		qsRepoTopic1.setState(bm.oA.getValue());
		qsRepoTopic1.setAttr(Integer.valueOf(1));
		if (ab.isEmpty(qsRepoTopic1.getTid())) {
			qsRepoTopic1.setTlevel(account.getDataScope().toString());
			qsRepoTopic1.setDepNo(account.getDepNo());
			if (account.getDataScope() == n.gj.getValue()) {
				qsRepoTopic1.setTlevel(n.gk.getValue().toString());
			} else if (account.getDataScope() == n.gm.getValue() && ab.isNotEmpty(account.getUnitId())) {
				qsRepoTopic1.setTlevel(n.gk.getValue().toString());
			}

			qsRepoTopic1.setCreateUser(account.getUsername());
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updTimeStr = sdf1.format(new Date());
		qsRepoTopic1.setUpdTime(updTimeStr);
		this.xx.a(qsRepoTopic1, options);
		QsRepoTopic qrt = this.xx.get(qsRepoTopic1.getTid());
		qsTopic.setTid((String) null);
		qsTopic.setTitle(qrt.getTitle());
		qsTopic.setTtype(qrt.getTtype());
		qsTopic.setAllowNull(qrt.getAllowNull());
		qsTopic.setNote(qrt.getNote());
		qsTopic.setCatId(qrt.getCatId());
		qsTopic.setCatName(qrt.getCatName());
		qsTopic.setUnitId(qrt.getUnitId());
		qsTopic.setDepNo(qrt.getDepNo());
		qsTopic.setDocNo(qrt.getDocNo());
		qsTopic.setAttr(qrt.getAttr());
		qsTopic.setMaxValue(qrt.getMaxValue());
		qsTopic.setFeedType(qrt.getFeedType());
		this.save(qsTopic);
		List qroList = this.xy.findByRtid(qrt.getTid());
		ArrayList qtoList = new ArrayList();
		Iterator arg11 = qroList.iterator();

		while (arg11.hasNext()) {
			QsRepoOptions qro = (QsRepoOptions) arg11.next();
			QsTopicOption qto = new QsTopicOption();
			qto.setOptName(qro.getOptName());
			qto.setAllowInput(qro.getAllowInput());
			qto.setOptValue(qro.getOptValue());
			qto.setUnitId(qsTopic.getUnitId());
			qto.setSortNo(qro.getSortNo());
			qto.setTid(qsTopic.getTid());
			qtoList.add(qto);
		}

		this.xk.a(qsTopic.getTid(), qtoList);
	}
}