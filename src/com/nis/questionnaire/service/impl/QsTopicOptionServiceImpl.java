package com.nis.questionnaire.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.questionnaire.dao.QsTopicOptionDao;
import com.nis.questionnaire.entity.QsTopicOption;
import com.nis.questionnaire.service.QsTopicOptionService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QsTopicOptionServiceImpl implements QsTopicOptionService {
	@Autowired
	private QsTopicOptionDao xr;

	public void a(String tid, List<QsTopicOption> list) {
		List orgOptions = this.findByTid(tid);
		Iterator arg4 = orgOptions.iterator();

		QsTopicOption qsTopicOption;
		while (arg4.hasNext()) {
			qsTopicOption = (QsTopicOption) arg4.next();
			boolean isDel = true;
			Iterator arg7 = list.iterator();

			while (arg7.hasNext()) {
				QsTopicOption curQto = (QsTopicOption) arg7.next();
				if (ab.isNotEmpty(curQto.getOptId()) && curQto.getOptId().equals(qsTopicOption.getOptId())) {
					isDel = false;
				}
			}

			if (isDel) {
				this.delete(qsTopicOption.getOptId());
			}
		}

		arg4 = list.iterator();

		while (arg4.hasNext()) {
			qsTopicOption = (QsTopicOption) arg4.next();
			qsTopicOption.setTid(tid);
			if (ab.isEmpty(qsTopicOption.getOptId())) {
				this.save(qsTopicOption);
			} else {
				this.update(qsTopicOption);
			}
		}

	}

	public void save(QsTopicOption qsTopicOption) {
		qsTopicOption.setOptId(z.a(bg.nD));
		this.xr.save(qsTopicOption);
	}

	public void delete(String optId) {
		this.xr.delete(optId);
	}

	public void update(QsTopicOption qsTopicOption) {
		this.xr.update(qsTopicOption);
	}

	public QsTopicOption get(String optId) {
		return this.xr.get(optId);
	}

	public MyPage<QsTopicOption> a(QsTopicOption qsTopicOption) {
		int total = this.xr.findQsTopicOptionCount(qsTopicOption);
		List data = null;
		if (total > 0) {
			data = this.xr.findQsTopicOption(qsTopicOption);
		}

		return new MyPage(qsTopicOption.getPage().intValue(), qsTopicOption.getSize().intValue(), total, data);
	}

	public List<QsTopicOption> getAll() {
		return this.xr.getAll();
	}

	public List<QsTopicOption> findByTid(String tid) {
		return this.xr.findByTid(tid);
	}
}