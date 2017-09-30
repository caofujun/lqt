package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.dict.dao.TrocheKindDao;
import com.nis.dict.entity.TrocheKind;
import com.nis.dict.service.TrocheKindService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrocheKindServiceImpl implements TrocheKindService {
	@Autowired
	private TrocheKindDao qM;

	public void save(TrocheKind trocheKind) {
		trocheKind.setLastAt(f.getCurDate());
		this.qM.save(trocheKind);
	}

	public void delete(Long year, String trocheKindId) {
		this.qM.delete(year, trocheKindId);
	}

	public void update(TrocheKind trocheKind) {
		trocheKind.setLastAt(f.getCurDate());
		this.qM.update(trocheKind);
	}

	public TrocheKind get(Long year, String trocheKindId) {
		return this.qM.get(year, trocheKindId);
	}

	public MyPage<TrocheKind> a(TrocheKind trocheKind) {
		int total = this.qM.findTrocheKindCount(trocheKind);
		List data = null;
		if (total > 0) {
			data = this.qM.findTrocheKind(trocheKind);
		}

		return new MyPage(trocheKind.getPage().intValue(), trocheKind.getSize().intValue(), total, data);
	}

	public List<TrocheKind> getAll() {
		return this.qM.getAll();
	}

	public List<TrocheKind> a(Long year, String searchString) {
		TrocheKind searchTrocheKind = new TrocheKind();
		searchTrocheKind.setYear(year);
		searchTrocheKind.setSearchString(searchString);
		return this.qM.findTrocheKind(searchTrocheKind);
	}
}