package com.nis.questions.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.bm;
import com.nis.comm.utils.z;
import com.nis.questions.dao.QsRepoOptionsDao;
import com.nis.questions.entity.QsRepoOptions;
import com.nis.questions.service.QsRepoOptionsService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QsRepoOptionsServiceImpl implements QsRepoOptionsService {
	@Autowired
	private QsRepoOptionsDao xB;

	public void a(String rtid, List<QsRepoOptions> options) {
		this.xB.deleteRtid(rtid);
		Iterator arg3 = options.iterator();

		while (arg3.hasNext()) {
			QsRepoOptions qro = (QsRepoOptions) arg3.next();
			qro.setOptId(z.a(bg.nz));
			qro.setRtid(rtid);
			qro.setStatus(bm.oA.getValue());
			this.xB.save(qro);
		}

	}

	public void update(QsRepoOptions qsRepoOptions) {
		this.xB.update(qsRepoOptions);
	}

	public QsRepoOptions get(String optId) {
		return this.xB.get(optId);
	}

	public MyPage<QsRepoOptions> a(QsRepoOptions qsRepoOptions) {
		int total = this.xB.findQsRepoOptionsCount(qsRepoOptions);
		List data = null;
		if (total > 0) {
			data = this.xB.findQsRepoOptions(qsRepoOptions);
		}

		return new MyPage(qsRepoOptions.getPage().intValue(), qsRepoOptions.getSize().intValue(), total, data);
	}

	public List<QsRepoOptions> getAll() {
		return this.xB.getAll();
	}

	public List<QsRepoOptions> findByRtid(String rtid) {
		return this.xB.findByRtid(rtid);
	}
}