package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.dao.Xl014DicTrocheKindDao;
import com.nis.prevalence.entity.Xl014DicTrocheKind;
import com.nis.prevalence.service.Xl014DicTrocheKindService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl014DicTrocheKindServiceImpl implements Xl014DicTrocheKindService {
	@Autowired
	private Xl014DicTrocheKindDao xe;

	public void save(Xl014DicTrocheKind xl014DicTrocheKind) {
		this.xe.save(xl014DicTrocheKind);
	}

	public void delete(Integer year, String trochekindid) {
		this.xe.delete(year, trochekindid);
	}

	public void update(Xl014DicTrocheKind xl014DicTrocheKind) {
		this.xe.update(xl014DicTrocheKind);
	}

	public Xl014DicTrocheKind get(Integer year, String trochekindid) {
		return this.xe.get(year, trochekindid);
	}

	public MyPage<Xl014DicTrocheKind> a(Xl014DicTrocheKind xl014DicTrocheKind) {
		int total = this.xe.findXl014DicTrocheKindCount(xl014DicTrocheKind);
		List data = null;
		if (total > 0) {
			data = this.xe.findXl014DicTrocheKind(xl014DicTrocheKind);
		}

		return new MyPage(xl014DicTrocheKind.getPage().intValue(), xl014DicTrocheKind.getSize().intValue(), total,
				data);
	}

	public List<Xl014DicTrocheKind> getAll() {
		return this.xe.getAll();
	}
}