package com.nis.questions.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.q;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.questions.dao.QsRepoTopicDao;
import com.nis.questions.entity.QsRepoOptions;
import com.nis.questions.entity.QsRepoTopic;
import com.nis.questions.service.QsRepoOptionsService;
import com.nis.questions.service.QsRepoTopicService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QsRepoTopicServiceImpl implements QsRepoTopicService {
	@Autowired
	private QsRepoTopicDao xC;
	@Autowired
	private QsRepoOptionsService xy;
	@Autowired
	private SysDictService p;

	public void a(QsRepoTopic qsRepoTopic, List<QsRepoOptions> options) {
		if (qsRepoTopic.getCatId() != null) {
			String maxValue = this.p.k(q.gC.getValue(), qsRepoTopic.getCatId(), qsRepoTopic.getUnitId());
			qsRepoTopic.setCatName(maxValue);
		}

		if ("1".equals(qsRepoTopic.getTtype())) {
			long maxValue1 = 0L;
			Iterator arg5 = options.iterator();

			while (arg5.hasNext()) {
				QsRepoOptions option = (QsRepoOptions) arg5.next();
				if (ab.isNotEmpty(option.getOptValue()) && Long.valueOf(option.getOptValue()).longValue() > maxValue1) {
					maxValue1 = Long.valueOf(option.getOptValue()).longValue();
				}
			}

			qsRepoTopic.setMaxValue(Long.valueOf(maxValue1));
		}

		if (ab.isEmpty(qsRepoTopic.getTid())) {
			qsRepoTopic.setTid(z.a(bg.nz));
			this.xC.save(qsRepoTopic);
		} else {
			this.xC.update(qsRepoTopic);
		}

		this.xy.a(qsRepoTopic.getTid(), options);
	}

	public void delete(String tid) {
		this.xC.delete(tid);
	}

	public QsRepoTopic get(String tid) {
		return this.xC.get(tid);
	}

	public MyPage<QsRepoTopic> a(QsRepoTopic qsRepoTopic) {
		int total = this.xC.findQsRepoTopicCount(qsRepoTopic);
		List data = null;
		if (total > 0) {
			data = this.xC.findQsRepoTopic(qsRepoTopic);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				QsRepoTopic qrt = (QsRepoTopic) arg4.next();
				String ttypeName = this.p.k(q.gB.getValue(), qrt.getTtype(), qsRepoTopic.getUnitId());
				qrt.setTtypeName(ttypeName);
			}
		}

		return new MyPage(qsRepoTopic.getPage().intValue(), qsRepoTopic.getSize().intValue(), total, data);
	}

	public List<QsRepoTopic> getAll() {
		return this.xC.getAll();
	}

	public MyPage<QsRepoTopic> b(QsRepoTopic qsRepoTopic) {
		int total = this.xC.findQsRepoTopicCount(qsRepoTopic);
		List data = null;
		if (total > 0) {
			data = this.xC.findQsRepoTopic(qsRepoTopic);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				QsRepoTopic qrt = (QsRepoTopic) arg4.next();
				String ttypeName = this.p.k(q.gB.getValue(), qrt.getTtype(), qsRepoTopic.getUnitId());
				qrt.setTtypeName(ttypeName);
				qrt.setOptions(this.xy.findByRtid(qrt.getTid()));
			}
		}

		return new MyPage(qsRepoTopic.getPage().intValue(), qsRepoTopic.getSize().intValue(), total, data);
	}
}